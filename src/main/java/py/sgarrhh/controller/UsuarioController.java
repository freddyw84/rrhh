package py.sgarrhh.controller;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import py.sgarrhh.models.Usuario;
import py.sgarrhh.repository.UsuarioRepository;


@Controller
public class UsuarioController {
	
	@Autowired
	private UsuarioRepository ur;
	
	@RequestMapping(value="/registrarUsuario", method=RequestMethod.GET)
	public String form() {
		return "usuario/formUsuario";
	}

	@RequestMapping(value="/registrarUsuario", method=RequestMethod.POST)
	public String form(@Valid Usuario usuario, BindingResult result, RedirectAttributes attributes) {
		if(result.hasErrors()){
			attributes.addFlashAttribute("mensaje", "Verifique los campos!");
			return "redirect:/registrarUsuario";
		}
		ur.save(usuario);
		attributes.addFlashAttribute("mensaje", "Registro guardado!");
	
		return "redirect:/registrarUsuario";
	}

	@RequestMapping("/listaUsuarios")
	public ModelAndView listaUsuarios() {
 
		ModelAndView mv= new ModelAndView("usuario/listaUsuarios");
		Iterable <Usuario> usuario= ur.findAll();
		mv.addObject("usuarios",usuario);
		return mv;
	}
		
	@RequestMapping("/u{login}")
	private ModelAndView detalleUsuario(@PathVariable("id") String login) {
        Usuario usuario =ur.findByLogin(login);
		ModelAndView mvd= new ModelAndView("usuario/detalleUsuario");
		mvd.addObject("usuarios",usuario);
		
		return mvd;
	}
	
	@RequestMapping(value="/u{id}", method=RequestMethod.POST)
	private String detalleUsuarioPost(@PathVariable("id") long id,@Valid Usuario usuario, BindingResult result, RedirectAttributes attributes) {
		if(result.hasErrors()){
			attributes.addFlashAttribute("mensaje", "Verifique los campos!");
			return "redirect:/u{id}";
		}
		ur.save(usuario);
		attributes.addFlashAttribute("mensaje", "Registro guardado!");
		return "redirect:/listaUsuarios";
	}
	
	@RequestMapping("/eliminarUsuario")
	private String eliminarUsuario(String login, RedirectAttributes attributes){
		Usuario usuario = ur.findByLogin(login);
		try {
			ur.delete(usuario);
		} catch (Exception e) {
			attributes.addFlashAttribute("mensaje", "Usuario está siendo utilizado!");
			return "redirect:/listaUsuarios";
		}
		
		attributes.addFlashAttribute("mensaje", "Eliminado con exito");
		return "redirect:/listaUsuarios";
	}

	
}
