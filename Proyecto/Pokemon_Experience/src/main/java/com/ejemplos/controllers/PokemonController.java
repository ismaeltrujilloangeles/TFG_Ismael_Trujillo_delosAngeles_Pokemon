package com.ejemplos.controllers;

import java.util.ArrayList;
import java.util.Base64;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ejemplos.models.entity.Pokemon;
import com.ejemplos.models.entity.PokemonCombates;
import com.ejemplos.models.entity.PokemonHabilidad;
import com.ejemplos.models.entity.Producto;
import com.ejemplos.models.entity.Registro;
import com.ejemplos.models.entity.Usuario;
import com.ejemplos.models.service.IPokemonCombatesService;
import com.ejemplos.models.service.IPokemonHabilidadesService;
import com.ejemplos.models.service.IPokemonService;
import com.ejemplos.models.service.IUsuarioService;
import com.ejemplos.models.service.IProductoService;
import com.ejemplos.models.service.IRegistroService;

//Con sessionAtributes("cliente") estamos creando un atributo de sesión
//mantenemos el cliente en la sesión hasta que estemos en el método guardar
//Lo eliminamos con SessionStatus

@Controller
@SessionAttributes("pokemon")
public class PokemonController {

	@Autowired
	private IPokemonService pokemonService;

	@Autowired
	private IUsuarioService usuarioService;

	@Autowired
	private IPokemonCombatesService pokemonCombatesService;

	@Autowired
	private IProductoService productoService;

	@Autowired
	private IPokemonHabilidadesService pokemonHabilidadesService;

	@Autowired
	private IRegistroService registroService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String portada(Model model, HttpServletRequest request) {

		HttpSession sesion = request.getSession(true);
		if (sesion.getAttribute("usuario") != null) {
			model.addAttribute("pageTitle", "Pokemon Experience");
			model.addAttribute("usuario", sesion.getAttribute("usuario"));
		} else {
			model.addAttribute("pageTitle", "Pokemon Experience");
			model.addAttribute("usuario", null);
		}

		// Comprobar si ha iniciado sesión correctamente y así mostrar o no mensaje
		if (sesion.getAttribute("iniciado") != null) {
			if (sesion.getAttribute("iniciado").equals(true)) {
				model.addAttribute("iniciado", true);
			} else {
				model.addAttribute("iniciado", false);
			}
		}

		// Comprobar si ha cerrado sesión correctamente y así mostrar o no mensaje
		if (sesion.getAttribute("cerrado") != null) {
			if (sesion.getAttribute("cerrado").equals(true)) {
				model.addAttribute("cerrado", true);
			} else {
				model.addAttribute("cerrado", false);
			}
		}

		// Comprobar si ha modificado el usuario correctamente y así mostrar o no
		// mensaje
		if (sesion.getAttribute("modificado") != null) {
			if (sesion.getAttribute("modificado").equals(true)) {
				model.addAttribute("modificado", true);
			} else {
				model.addAttribute("modificado", false);
			}
		}

		sesion.setAttribute("iniciado", false);
		sesion.setAttribute("cerrado", false);
		sesion.setAttribute("modificado", false);

		return "inicio";
	}

	@RequestMapping(value = "/iniciosesion", method = RequestMethod.GET)
	public String iniciosesion(Model model, HttpServletRequest request) {

		HttpSession sesion = request.getSession(true);
		if (sesion.getAttribute("usuario") != null) {
			model.addAttribute("titulo", "Bienvenido");
			;
			model.addAttribute("usuario", sesion.getAttribute("usuario"));
		} else {
			model.addAttribute("pageTitle", "Inicio de sesión");
			model.addAttribute("usuario", null);
		}

		return "iniciosesion";
	}

	@RequestMapping(value = "/iniciasesion", method = RequestMethod.POST)
	public String iniciasesion(String nombreUsuario, String contrasena, Model model, RedirectAttributes flash,
			HttpServletRequest request) throws Exception {

		HttpSession sesion = request.getSession(true);
		Usuario usuario = null;
		
		String encriptada = encrypt(contrasena);

		if (nombreUsuario != null && encriptada != null) {
			usuario = (Usuario) usuarioService.findUsuario(nombreUsuario, encriptada);
			if (usuario == null) {
				flash.addFlashAttribute("error", "Fallo gravísimo");
				model.addAttribute("titulo", "Bienvenido");
				model.addAttribute("pageTitle", "Inicio de sesión");
				model.addAttribute("fallo", "No se ha encontrado el usuario");
				return "iniciosesion";
			}
		} else {
			flash.addFlashAttribute("error", "Fallo gravísimo");
			model.addAttribute("titulo", "Bienvenido");
			model.addAttribute("pageTitle", "Inicio de sesión");
			model.addAttribute("fallo", "Rellene todos los campos correctamente");
			return "iniciosesion";
		}

		model.addAttribute("usuario", usuario);
		sesion.setAttribute("usuario", usuario);
		sesion.setAttribute("iniciado", true);
		sesion.setAttribute("cerrado", false);

		return "redirect:/";
	}

	@RequestMapping(value = "/cerrarsesion", method = RequestMethod.GET)
	public String cerrarsesion(Model model, HttpServletRequest request) {

		HttpSession sesion = request.getSession(true);
		Usuario usuario = (Usuario) sesion.getAttribute("usuario");

		model.addAttribute("pageTitle", "Pokemon Experience");
		model.addAttribute("usuario", usuario);
		sesion.setAttribute("usuario", null);
		sesion.setAttribute("iniciado", false);
		sesion.setAttribute("cerrado", true);

		return "redirect:/";
	}

	@RequestMapping(value = "/registro", method = RequestMethod.GET)
	public String registro(Model model, HttpServletRequest request) {

		HttpSession sesion = request.getSession(true);
		if (sesion.getAttribute("usuario") != null) {
			model.addAttribute("titulo", "Registro");
			model.addAttribute("pageTitle", "Registro de usuario");
			model.addAttribute("usuario", sesion.getAttribute("usuario"));
		} else {
			model.addAttribute("pageTitle", "Registro de usuario");
			model.addAttribute("titulo", "Registro");
			model.addAttribute("usuario", null);
		}

		return "registro";
	}

	@RequestMapping(value = "/registrar", method = RequestMethod.POST)
	public String registrar(String nombre, String apellidos, String nombreUsuario, String contrasena, Long edad,
			Model model, SessionStatus status, HttpServletRequest request) throws Exception {

		HttpSession sesion = request.getSession(true);
		Usuario usuario = null;
		List<Usuario> usuarios = usuarioService.findAllUsuariosUsuarios();

		for (Usuario usu : usuarios) {
			if (usu.getUsuario().equals(nombreUsuario)) {
				model.addAttribute("fallo", "Nombre de usuario ya en uso");
				return "registro";
			}
		}

		if (nombre != null && apellidos != null && nombreUsuario != null && contrasena != null && edad != null
				&& edad > 14 && edad < 99) {
			String con = encrypt(contrasena);
			usuario = new Usuario(nombre, apellidos, nombreUsuario, con, edad);
			usuarioService.save(usuario);
		} else {
			model.addAttribute("pageTitle", "Registro de usuario");
			model.addAttribute("fallo", "Rellene todos los campos correctamente");
			return "registro";
		}

		Usuario usuarioIniciado = (Usuario) sesion.getAttribute("usuario");
		if (usuarioIniciado != null && usuarioIniciado.getId() == 1) {
			model.addAttribute("pageTitle", "Gestión usuarios");
			model.addAttribute("usuarios", usuarioService.findAll());
			return "redirect:/gestionUsuarios";
		} else {
			model.addAttribute("pageTitle", "Registro");
			model.addAttribute("usuario", usuario);
			sesion.setAttribute("usuario", usuario);
			sesion.setAttribute("iniciado", true);
			sesion.setAttribute("cerrado", false);

			return "redirect:/";
		}
	}

	@RequestMapping(value = "/pokedex", method = RequestMethod.GET)
	public String pokedex(Model model, HttpServletRequest request) {

		HttpSession sesion = request.getSession(true);
		if (sesion.getAttribute("usuario") != null) {
			model.addAttribute("usuario", sesion.getAttribute("usuario"));
		} else {
			model.addAttribute("usuario", null);
		}

		model.addAttribute("pageTitle", "Pokedex 1st Generation");

		model.addAttribute("pokemons", pokemonService.findAllPokemons());

		return "pokedex";
	}

	@RequestMapping(value = "/pokedexFiltrar", method = RequestMethod.POST)
	public String pokedexFiltrar(String filtro, Model model, HttpServletRequest request) {

		HttpSession sesion = request.getSession(true);
		if (sesion.getAttribute("usuario") != null) {
			model.addAttribute("usuario", sesion.getAttribute("usuario"));
		} else {
			model.addAttribute("usuario", null);
		}

		if (filtro.equals("id")) {
			model.addAttribute("pokemons", pokemonService.findAllPokemons());
		} else if (filtro.equals("nombre")) {
			model.addAttribute("pokemons", pokemonService.findAllPokemonsNombre());
		} else if (filtro.equals("idCategoria")) {
			model.addAttribute("pokemons", pokemonService.findAllPokemonsCategoria());
		} else if (filtro.equals("tipo")) {
			model.addAttribute("pokemons", pokemonService.findAllPokemonsTipos());
		} else {
			model.addAttribute("pokemons", pokemonService.findAllPokemons());
		}

		model.addAttribute("pageTitle", "Pokedex 1st Generation");

		return "pokedex";
	}

	@RequestMapping(value = "/productos", method = RequestMethod.GET)
	public String productos(Model model, HttpServletRequest request) {

		HttpSession sesion = request.getSession(true);
		if (sesion.getAttribute("usuario") != null) {
			model.addAttribute("usuario", sesion.getAttribute("usuario"));
		} else {
			model.addAttribute("usuario", null);
		}

		model.addAttribute("pageTitle", "Productos recomendados");
		model.addAttribute("productos", productoService.findAll());

		return "productos";
	}

	@RequestMapping(value = "/anadirProducto", method = RequestMethod.GET)
	public String anadirProducto(Model model, HttpServletRequest request) {

		HttpSession sesion = request.getSession(true);
		if (sesion.getAttribute("usuario") != null) {
			model.addAttribute("usuario", sesion.getAttribute("usuario"));
		} else {
			model.addAttribute("usuario", null);
		}

		model.addAttribute("pageTitle", "Productos recomendados");
		model.addAttribute("productos", productoService.findAll());

		return "anadirProducto";
	}

	@RequestMapping(value = "/saveProducto", method = RequestMethod.POST)
	public String saveProducto(String nombre, String descripcion, String imagen, String url1, String titulo1,
			String url2, String titulo2, Model model, SessionStatus status, HttpServletRequest request) {

		HttpSession sesion = request.getSession(true);
		if (sesion.getAttribute("usuario") != null) {
			model.addAttribute("usuario", sesion.getAttribute("usuario"));
		} else {
			model.addAttribute("usuario", null);
		}

		Producto producto = null;

		if (!nombre.isEmpty() && !descripcion.isEmpty() && !imagen.isEmpty() && !url1.isEmpty() && !titulo1.isEmpty()
				&& !url2.isEmpty() && !titulo2.isEmpty()) {
			producto = new Producto(nombre, descripcion, imagen, url1, titulo1, url2, titulo2);
			productoService.save(producto);
		} else {
			model.addAttribute("pageTitle", "Añadir producto");
			model.addAttribute("fallo", "Rellene todos los campos correctamente");
			return "anadirProducto";
		}

		model.addAttribute("pageTitle", "Productos recomendados");
		model.addAttribute("productos", productoService.findAll());

		// Comprobar si ha iniciado sesión correctamente y así mostrar o no mensaje
		if (sesion.getAttribute("anadido") != null) {
			if (sesion.getAttribute("anadido").equals(true)) {
				model.addAttribute("anadido", true);
			} else {
				model.addAttribute("anadido", false);
			}
		}

		return "productos";
	}

	@RequestMapping(value = "/seleccionPokemon", method = RequestMethod.GET)
	public String seleccionPokemon(Model model, HttpServletRequest request) {

		HttpSession sesion = request.getSession(true);
		if (sesion.getAttribute("usuario") != null) {
			model.addAttribute("usuario", sesion.getAttribute("usuario"));
		} else {
			model.addAttribute("usuario", null);
		}

		model.addAttribute("pageTitle", "Combate pokemon: selección");
		model.addAttribute("pokemonsCombates", pokemonCombatesService.findAllPokemonsCombates());

		return "seleccionPokemon";
	}

	@RequestMapping(value = "/seleccionPokemonFiltrar", method = RequestMethod.POST)
	public String seleccionPokemonFiltrar(String filtro, Model model, HttpServletRequest request) {

		HttpSession sesion = request.getSession(true);
		if (sesion.getAttribute("usuario") != null) {
			model.addAttribute("usuario", sesion.getAttribute("usuario"));
		} else {
			model.addAttribute("usuario", null);
		}

		if (filtro.equals("id")) {
			model.addAttribute("pokemonsCombates", pokemonCombatesService.findAllPokemonsCombates());
		} else if (filtro.equals("HP")) {
			model.addAttribute("pokemonsCombates", pokemonCombatesService.findAllPokemonsCombatesHP());
		} else if (filtro.equals("attack")) {
			model.addAttribute("pokemonsCombates", pokemonCombatesService.findAllPokemonsCombatesAttack());
		} else if (filtro.equals("defense")) {
			model.addAttribute("pokemonsCombates", pokemonCombatesService.findAllPokemonsCombatesDefense());
		} else if (filtro.equals("spattack")) {
			model.addAttribute("pokemonsCombates", pokemonCombatesService.findAllPokemonsCombatesSpAttack());
		} else if (filtro.equals("spdefense")) {
			model.addAttribute("pokemonsCombates", pokemonCombatesService.findAllPokemonsCombatesSpDefense());
		} else if (filtro.equals("speed")) {
			model.addAttribute("pokemonsCombates", pokemonCombatesService.findAllPokemonsCombatesSpeed());
		} else {
			model.addAttribute("pokemonsCombates", pokemonCombatesService.findAllPokemonsCombates());
		}

		model.addAttribute("pageTitle", "Combate pokemon: selección");

		return "seleccionPokemon";
	}

	@RequestMapping(value = "/combate/{id}", method = RequestMethod.GET)
	public String combate(@PathVariable(value = "id") Long id, Model model, HttpServletRequest request) {

		HttpSession sesion = request.getSession(true);
		if (sesion.getAttribute("usuario") != null) {
			model.addAttribute("usuario", sesion.getAttribute("usuario"));
		} else {
			model.addAttribute("usuario", null);
		}

		// Selección aleatorio de pokemon para la cpu
		List<?> pokemonsCombates = pokemonCombatesService.findAllPokemonsCombates();
		// Para mezlcar la lista aleatoriamente y quedarnos con la primera posición
		Collections.shuffle(pokemonsCombates);
		Object[] pokemon = (Object[]) pokemonsCombates.get(0);

		// Información del pokemon de la CPU
		model.addAttribute("pokemonCombateCPU", pokemon);
		sesion.setAttribute("pokemonCombateCPU", pokemon);
		sesion.setAttribute("vidaMaximaCPU", pokemon[1]);
		model.addAttribute("vidaMaximaCPU", pokemon[1]);
		model.addAttribute("pokemonCPU", pokemonService.findOne((long) pokemon[0]));
		sesion.setAttribute("pokemonCPU", pokemonService.findOne((long) pokemon[0]));
		model.addAttribute("habilidadesCPU", pokemonHabilidadesService.findAllPokemonHabilidades((long) pokemon[0]));
		sesion.setAttribute("habilidadesCPU", pokemonHabilidadesService.findAllPokemonHabilidades((long) pokemon[0]));
		model.addAttribute("porcentajeCPU", 100);
		sesion.setAttribute("porcentajeCPU", 100);

		// Información de nuestro pokemon
		model.addAttribute("pageTitle", "Combate pokemon");
		model.addAttribute("pokemonCombate", pokemonCombatesService.findOne(id));
		sesion.setAttribute("pokemonCombate", pokemonCombatesService.findOne(id));
		sesion.setAttribute("vidaMaxima", pokemonCombatesService.findOne(id).getHP());
		model.addAttribute("vidaMaxima", pokemonCombatesService.findOne(id).getHP());
		model.addAttribute("pokemon", pokemonService.findOne(id));
		sesion.setAttribute("pokemon", pokemonService.findOne(id));
		model.addAttribute("habilidades", pokemonHabilidadesService.findAllPokemonHabilidades(id));
		sesion.setAttribute("habilidades", pokemonHabilidadesService.findAllPokemonHabilidades(id));
		model.addAttribute("porcentaje", 100);
		sesion.setAttribute("porcentaje", 100);
		model.addAttribute("tipoBarraCPU", "success");
		model.addAttribute("tipoBarra", "success");
		model.addAttribute("vivoCPU", true);
		model.addAttribute("vivo", true);

		// Apartado de mensajes
		// Para cuando de comienzo el combate
		model.addAttribute("primero", true);

		// Número aleatorio para el fondo de página mientras se combate
		int numero = (int) Math.floor(Math.random() * (4 - 1) + 1);
		model.addAttribute("numero", numero);
		sesion.setAttribute("numero", numero);

		return "combate";
	}

	@RequestMapping(value = "/ataque/{id}", method = RequestMethod.GET)
	public String ataque(@PathVariable(value = "id") Long id, Model model, HttpServletRequest request) {

		HttpSession sesion = request.getSession(true);
		Usuario usuario = null;
		if (sesion.getAttribute("usuario") != null) {
			usuario = (Usuario) sesion.getAttribute("usuario");
			model.addAttribute("usuario", usuario);
		} else {
			model.addAttribute("usuario", null);
		}

		/*
		 * Si posee más de un ataque, el primer ataque producirá un daño del 15% de su
		 * ataque físico Si posee segundo ataque, éste producirá un 15% de daño sobre su
		 * ataque especial Si posee tercer ataque, éste producirá un 10% de daño sobre
		 * su ataque físico y ataque especial en conjunto
		 */

		// Cogemos los valores actuales del pokemon, para actualizarlos al recibir el
		// ataque.
		Object[] pokemonCombateCPU = (Object[]) sesion.getAttribute("pokemonCombateCPU");
		Pokemon pokemonCPU = (Pokemon) sesion.getAttribute("pokemonCPU");
		ArrayList<?> habilidadesCPU = (ArrayList<?>) sesion.getAttribute("habilidadesCPU");

		PokemonCombates pokemonCombate = (PokemonCombates) sesion.getAttribute("pokemonCombate");
		Pokemon pokemon = (Pokemon) sesion.getAttribute("pokemon");
		ArrayList<?> habilidades = (ArrayList<?>) sesion.getAttribute("habilidades");

		// Selección aleatorio del ataque que realiza la cpu (Baja la vida de tu
		// Pokemon)
		int ataqueAleatorio = (int) Math.floor(Math.random() * (habilidadesCPU.size() - 0) + 0);
		PokemonHabilidad ataqueCPU = (PokemonHabilidad) habilidadesCPU.get(ataqueAleatorio);

		double danoCPU = 0.0;
		// Condiciones del daño que hace dependiendo de que ataque sea
		model.addAttribute("ataqueAleatorio", ataqueAleatorio);
		if (ataqueAleatorio == 0) {
			danoCPU = (0.15 * (long) pokemonCombateCPU[2]);
		} else if (ataqueAleatorio == 1) {
			danoCPU = (0.15 * (long) pokemonCombateCPU[2]);
		} else if (ataqueAleatorio == 2) {
			danoCPU = (0.10 * ((long) pokemonCombateCPU[2] + (long) pokemonCombateCPU[4]));
		}

		model.addAttribute("danoCPU", danoCPU);

		// Actualización de la vida del Pokemon enemigo tras recibir nuestro ataque
		pokemonCombate.setHP(pokemonCombate.getHP() - (long) Double.valueOf(danoCPU).longValue());

		// Porcentaje de vida restante de nuestro pokemon tras el ataque enemigo
		long porcentaje = 0;
		if ((long) pokemonCombate.getHP() <= 0) {
			pokemonCombate.setHP((long) 0);
			model.addAttribute("poncentaje", 0);
			model.addAttribute("vivo", false);
			sesion.setAttribute("vivo", false);

		} else {
			// Porcentaje de vida restante del enemigo tras nuestro ataque
			porcentaje = (100 * (long) pokemonCombate.getHP()) / (long) sesion.getAttribute("vidaMaxima");
			model.addAttribute("porcentaje", porcentaje);
			model.addAttribute("vivo", true);
			sesion.setAttribute("vivo", true);
		}

		// Para controlar el color de la barra dependiendo de la cantidad de vida
		if (porcentaje <= 100 && porcentaje > 50) {
			model.addAttribute("tipoBarra", "success");
		} else if (porcentaje <= 50 && porcentaje > 20) {
			model.addAttribute("tipoBarra", "warning");
		} else if (porcentaje <= 20) {
			model.addAttribute("tipoBarra", "danger");
		}

		// Acción que produce tu ataque (Baja la vida del contrincante)
		int index = 0;
		for (int i = 0; i < habilidades.size(); i++) {
			PokemonHabilidad p = (PokemonHabilidad) habilidades.get(i);
			if (p.getHabilidad().getIdHabilidad() == id) {
				index = i;
			}
		}

		int ataqueElegido = index;
		model.addAttribute("ataqueElegido", ataqueElegido);
		PokemonHabilidad pokHab = (PokemonHabilidad) habilidades.get(ataqueElegido);
		double dano = 0.0;
		// Condiciones del daño que hace dependiendo de que ataque sea
		model.addAttribute("ataqueElegido", ataqueElegido);
		if (ataqueElegido == 0) {
			dano = (0.15 * (long) pokemonCombate.getAttack());
		} else if (ataqueElegido == 1) {
			dano = (0.15 * (long) pokemonCombate.getSpattack());
		} else if (ataqueElegido == 2) {
			dano = (0.10 * ((long) pokemonCombate.getAttack() + (long) pokemonCombate.getSpattack()));
		}

		model.addAttribute("dano", dano);

		// Actualización de la vida de nuestro Pokemon trás recibir el ataque
		pokemonCombateCPU[1] = (long) pokemonCombateCPU[1] - (long) Double.valueOf(dano).longValue();
		long porcentajeCPU = 0;
		if ((long) pokemonCombateCPU[1] <= 0) {
			pokemonCombateCPU[1] = 0;
			model.addAttribute("poncentajeCPU", 0);
			model.addAttribute("vivoCPU", false);
			sesion.setAttribute("vivoCPU", false);
		} else {
			// Porcentaje de vida restante del enemigo tras nuestro ataque
			porcentajeCPU = (100 * (long) pokemonCombateCPU[1]) / (long) sesion.getAttribute("vidaMaximaCPU");
			model.addAttribute("porcentajeCPU", porcentajeCPU);
			model.addAttribute("vivoCPU", true);
			sesion.setAttribute("vivoCPU", true);
		}

		// Para controlar el color de la barra dependiendo de la cantidad de vida
		if (porcentajeCPU <= 100 && porcentajeCPU > 50) {
			model.addAttribute("tipoBarraCPU", "success");
		} else if (porcentajeCPU <= 50 && porcentajeCPU > 20) {
			model.addAttribute("tipoBarraCPU", "warning");
		} else if (porcentajeCPU <= 20) {
			model.addAttribute("tipoBarraCPU", "danger");
		}

		// Actualizamos la información de ambos pokemons a la actual
		sesion.setAttribute("pokemonCombate", pokemonCombate);
		sesion.setAttribute("pokemonCombateCPU", pokemonCombateCPU);

		// Registro de mensajes
		String[] registros = new String[5];
		if ((long) pokemonCombate.getSpeed() >= (long) pokemonCombateCPU[6]) {
			registros[0] = "Comienza el turno atacando: " + pokemon.getNombre();
			registros[1] = "Ataca " + pokemon.getNombre() + " usando: " + pokHab.getHabilidad().getNombre();
			registros[2] = "El ataque de " + pokemon.getNombre() + ", a causado " + Math.round(dano * 100) / 100
					+ " puntos de daño";
			registros[3] = "Ataca " + pokemonCPU.getNombre() + " usando: " + ataqueCPU.getHabilidad().getNombre();
			registros[4] = "El ataque de " + pokemonCPU.getNombre() + ", a causado " + Math.round(danoCPU * 100) / 100
					+ " puntos de daño";

		} else if ((long) pokemonCombateCPU[6] > (long) pokemonCombate.getSpeed()) {
			registros[0] = "Comienza el turno atacando: " + pokemonCPU.getNombre();
			registros[1] = "Ataca " + pokemonCPU.getNombre() + " usando: " + ataqueCPU.getHabilidad().getNombre();
			registros[2] = "El ataque de " + pokemonCPU.getNombre() + ", a causado " + Math.round(danoCPU * 100) / 100
					+ " puntos de daño";
			registros[3] = "Ataca " + pokemon.getNombre() + " usando: " + pokHab.getHabilidad().getNombre();
			registros[4] = "El ataque de " + pokemon.getNombre() + ", a causado " + Math.round(dano * 100) / 100
					+ " puntos de daño";

		}

		// Enviamos los registros para que se muestren los ataques realizados
		model.addAttribute("registros", registros);

		// Información actualizada del pokemon de la CPU
		model.addAttribute("pokemonCombateCPU", sesion.getAttribute("pokemonCombateCPU"));
		model.addAttribute("pokemonCPU", sesion.getAttribute("pokemonCPU"));
		model.addAttribute("habilidadesCPU", sesion.getAttribute("habilidadesCPU"));

		// Información actualizada de nuestro pokemon
		model.addAttribute("pageTitle", "Combate pokemon");
		model.addAttribute("pokemonCombate", sesion.getAttribute("pokemonCombate"));
		model.addAttribute("pokemon", sesion.getAttribute("pokemon"));
		model.addAttribute("habilidades", sesion.getAttribute("habilidades"));

		// Obtener y pasar el fondo para el combate
		model.addAttribute("numero", sesion.getAttribute("numero"));

		// Guardar registro de combate
		if ((Boolean) sesion.getAttribute("vivo") == true && (Boolean) sesion.getAttribute("vivoCPU") == false) {
			registroService
					.save(new Registro(usuario.getId(), usuario.getUsuario(), "Victoria", pokemonCombate.getIdPokemon(),
							pokemon.getNombre(), (long) pokemonCombateCPU[0], pokemonCPU.getNombre()));
		} else if ((Boolean) sesion.getAttribute("vivo") == false && (Boolean) sesion.getAttribute("vivoCPU") == true) {
			registroService
					.save(new Registro(usuario.getId(), usuario.getUsuario(), "Derrota", pokemonCombate.getIdPokemon(),
							pokemon.getNombre(), (long) pokemonCombateCPU[0], pokemonCPU.getNombre()));
		} else if ((Boolean) sesion.getAttribute("vivo") == false
				&& (Boolean) sesion.getAttribute("vivoCPU") == false) {
			registroService
					.save(new Registro(usuario.getId(), usuario.getUsuario(), "Empate", pokemonCombate.getIdPokemon(),
							pokemon.getNombre(), (long) pokemonCombateCPU[0], pokemonCPU.getNombre()));
		}

		// Para enviar y comprobar que ha atacado correctamente
		model.addAttribute("ataque", "SI");

		return "combate";
	}

	@RequestMapping(value = "/gestionUsuarios", method = RequestMethod.GET)
	public String gestionUsuarios(Model model, HttpServletRequest request) {

		HttpSession sesion = request.getSession(true);
		if (sesion.getAttribute("usuario") != null) {
			model.addAttribute("usuario", sesion.getAttribute("usuario"));
		} else {
			model.addAttribute("usuario", null);
		}

		model.addAttribute("pageTitle", "Gestión usuarios");
		model.addAttribute("usuarios", usuarioService.findAll());

		return "gestionUsuarios";
	}

	@RequestMapping(value = "/gestionUsuariosFiltrar", method = RequestMethod.POST)
	public String gestionUsuariosFiltrar(String filtro, Model model, HttpServletRequest request) {

		HttpSession sesion = request.getSession(true);
		if (sesion.getAttribute("usuario") != null) {
			model.addAttribute("usuario", sesion.getAttribute("usuario"));
		} else {
			model.addAttribute("usuario", null);
		}

		if (filtro.equals("id")) {
			model.addAttribute("usuarios", usuarioService.findAll());
		} else if (filtro.equals("nombre")) {
			model.addAttribute("usuarios", usuarioService.findAllUsuariosNombres());
		} else if (filtro.equals("apellidos")) {
			model.addAttribute("usuarios", usuarioService.findAllUsuariosApellidos());
		} else if (filtro.equals("usuario")) {
			model.addAttribute("usuarios", usuarioService.findAllUsuariosUsuarios());
		} else if (filtro.equals("edad")) {
			model.addAttribute("usuarios", usuarioService.findAllUsuariosEdades());
		} else {
			model.addAttribute("usuarios", usuarioService.findAll());
		}

		model.addAttribute("pageTitle", "Gestión usuarios");

		return "gestionUsuarios";
	}

	@RequestMapping(value = "/eliminar/{id}", method = RequestMethod.GET)
	public String eliminar(@PathVariable(value = "id") Long id, Model model, HttpServletRequest request) {

		HttpSession sesion = request.getSession(true);
		if (sesion.getAttribute("usuario") != null) {
			model.addAttribute("usuario", sesion.getAttribute("usuario"));
		} else {
			model.addAttribute("usuario", null);
		}

		model.addAttribute("pageTitle", "Gestión usuarios");
		usuarioService.delete(id);
		model.addAttribute("usuarios", usuarioService.findAll());

		return "gestionUsuarios";
	}

	@RequestMapping(value = "/modificacion/{id}", method = RequestMethod.GET)
	public String modficacion(@PathVariable(value = "id") Long id, Model model, HttpServletRequest request) throws Exception {

		HttpSession sesion = request.getSession(true);
		Usuario usuarioMod = (Usuario) usuarioService.findOne(id);
		Usuario usuario = (Usuario) sesion.getAttribute("usuario");
		if (usuario != null) {
			model.addAttribute("pageTitle", "Modificación de usuario");
			model.addAttribute("titulo", "Modificación de usuario");
			model.addAttribute("usuario", usuario);
			usuarioMod.setContrasena(decrypt(usuarioMod.getContrasena()));
			model.addAttribute("usuarioMod", usuarioMod);
		} else {
			model.addAttribute("pageTitle", "Modificación de usuario");
			model.addAttribute("titulo", "Modificación de usuario");
			model.addAttribute("usuario", null);
		}

		return "modificacion";
	}

	@RequestMapping(value = "/modificar/{id}", method = RequestMethod.POST)
	public String modificar(@PathVariable(value = "id") Long id, String nombre, String apellidos, String nombreUsuario,
			String contrasena, Long edad, Model model, SessionStatus status, HttpServletRequest request) throws Exception {

		HttpSession sesion = request.getSession(true);
		if (sesion.getAttribute("usuario") != null) {
			model.addAttribute("titulo", "Modificación de usuario");
			model.addAttribute("usuario", sesion.getAttribute("usuario"));
		} else {
			model.addAttribute("pageTitle", "Modificación de usuario");
			model.addAttribute("usuario", null);
		}

		Usuario usuario = (Usuario) usuarioService.findOne(id);

		List<Usuario> usuarios = usuarioService.findAllUsuariosUsuarios();

		for (Usuario usu : usuarios) {
			if (usu.getUsuario().equals(nombreUsuario) && !nombreUsuario.equals(usuario.getUsuario())) {
				model.addAttribute("fallo", "Nombre de usuario ya en uso");
				Usuario usuarioMod = (Usuario) usuarioService.findOne(id);
				model.addAttribute("pageTitle", "Modificación de usuario");
				model.addAttribute("titulo", "Modificación de usuario");
				model.addAttribute("usuario", usuario);
				model.addAttribute("usuarioMod", usuarioMod);

				return "modificacion";
			}
		}

		if (nombre != null && apellidos != null && nombreUsuario != null && contrasena != null && edad != null
				&& edad > 14 && edad < 99) {
			usuario.setNombre(nombre);
			usuario.setApellidos(apellidos);
			String con = encrypt(contrasena);
			usuario.setContrasena(con);
			usuario.setEdad(edad);
			usuario.setUsuario(nombreUsuario);
			usuarioService.save(usuario);
			model.addAttribute("modificado", true);
		} else {
			model.addAttribute("pageTitle", "Modificar usuario");
			model.addAttribute("fallo", "Rellene todos los campos correctamente");
			model.addAttribute("usuario", sesion.getAttribute("usuario"));
			Usuario usuarioMod = (Usuario) usuarioService.findOne(id);
			model.addAttribute("usuarioMod", usuarioMod);

			return "modificacion";
		}

		Usuario usuarioIniciado = (Usuario) sesion.getAttribute("usuario");
		if (usuarioIniciado.getId() == 1) {
			model.addAttribute("pageTitle", "Gestión usuarios");
			model.addAttribute("usuarios", usuarioService.findAll());
			return "redirect:/gestionUsuarios";
		} else {
			model.addAttribute("pageTitle", "Modificar");
			model.addAttribute("usuario", usuario);
			sesion.setAttribute("modificado", true);
			sesion.setAttribute("iniciado", false);
			sesion.setAttribute("cerrado", false);

			return "redirect:/";
		}
	}

	@RequestMapping(value = "/registroCombate", method = RequestMethod.GET)
	public String registroCombate(Model model, HttpServletRequest request) {

		HttpSession sesion = request.getSession(true);
		Usuario usuario = null;
		if (sesion.getAttribute("usuario") != null) {
			model.addAttribute("usuario", sesion.getAttribute("usuario"));
			usuario = (Usuario) sesion.getAttribute("usuario");
		} else {
			model.addAttribute("usuario", null);
		}

		model.addAttribute("pageTitle", "Registro de combates realizados");
		if (usuario.getId() == 1) {
			model.addAttribute("registrosCombates", registroService.findAll());
		} else {
			model.addAttribute("registrosCombates", registroService.findAllUnUsuario(usuario.getId()));
		}

		return "registroCombates";
	}

	@RequestMapping(value = "/registrosFiltrar", method = RequestMethod.POST)
	public String registrosFiltrar(String filtro, Model model, HttpServletRequest request) {

		HttpSession sesion = request.getSession(true);
		if (sesion.getAttribute("usuario") != null) {
			model.addAttribute("usuario", sesion.getAttribute("usuario"));
		} else {
			model.addAttribute("usuario", null);
		}

		if (filtro.equals("fecha")) {
			model.addAttribute("registrosCombates", registroService.findAll());
		} else if (filtro.equals("usuario")) {
			model.addAttribute("registrosCombates", registroService.findAllRegistrosUsuarios());
		} else if (filtro.equals("victoria")) {
			model.addAttribute("registrosCombates", registroService.findAllRegistrosVictoria());
		} else if (filtro.equals("derrota")) {
			model.addAttribute("registrosCombates", registroService.findAllRegistrosDerrota());
		} else if (filtro.equals("empate")) {
			model.addAttribute("registrosCombates", registroService.findAllRegistrosEmpate());
		} else if (filtro.equals("elegido")) {
			model.addAttribute("registrosCombates", registroService.findAllRegistrosElegido());
		} else if (filtro.equals("contrincante")) {
			model.addAttribute("registrosCombates", registroService.findAllRegistrosContrincante());
		} else {
			model.addAttribute("registrosCombates", registroService.findAll());
		}

		model.addAttribute("pageTitle", "Registro de combates realizados");

		return "registroCombates";
	}

	
	// MÉTODOS PARA ENCRIPTAR Y DESENCRIPTAR CONTRASEÑA

	private static String encrypt(String text) throws Exception {
		return Base64.getEncoder().encodeToString(text.getBytes());
	}
	

	private static String decrypt(String encrypted) throws Exception {
		byte[] desencryptedBytes = Base64.getDecoder().decode(encrypted);
		return new String(desencryptedBytes);
	}
	

}
