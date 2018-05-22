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

import py.sgarrhh.models.Ciudad;
import py.sgarrhh.repository.CiudadRepository;


@Controller
public class CiudadController {
	
	@Autowired
	private CiudadRepository cr;
	
	@RequestMapping(value="/registrarCiudad", method=RequestMethod.GET)
	public String form() {
		return "ciudad/formCiudad";
	}

	@RequestMapping(value="/registrarCiudad", method=RequestMethod.POST)
	public String form(@Valid Ciudad ciudad, BindingResult result, RedirectAttributes attributes) {
		if(result.hasErrors()){
			attributes.addFlashAttribute("mensaje", "Verifique los campos!");
			return "redirect:/registrarCiudad";
		}
		cr.save(ciudad);
		attributes.addFlashAttribute("mensaje", "Registro guardado!");
	
		return "redirect:/registrarCiudad";
	}

	@RequestMapping("/listaCiudades")
	public ModelAndView listaCiudades() {
 
		ModelAndView mv= new ModelAndView("ciudad/listaCiudades");
		Iterable <Ciudad> ciudad= cr.findAll();
		mv.addObject("ciudades",ciudad);
		return mv;
	}
		
	@RequestMapping("/cd{id}")
	private ModelAndView detalleCiudad(@PathVariable("id") long id) {
        Ciudad ciudad =cr.findById(id);
		ModelAndView mvd= new ModelAndView("ciudad/detalleCiudad");
		mvd.addObject("ciudades",ciudad);
		
		return mvd;
	}
	
	@RequestMapping(value="/cd{id}", method=RequestMethod.POST)
	private String detalleCiudadPost(@PathVariable("id") long id,@Valid Ciudad ciudad, BindingResult result, RedirectAttributes attributes) {
		if(result.hasErrors()){
			attributes.addFlashAttribute("mensaje", "Verifique los campos!");
			return "redirect:/cd{id}";
		}
		cr.save(ciudad);
		attributes.addFlashAttribute("mensaje", "Registro guardado!");
		return "redirect:/listaCiudades";
	}
	
	@RequestMapping("/eliminarCiudad")
	private String eliminarCiudad(long id, RedirectAttributes attributes){
		Ciudad ciudad = cr.findById(id);
		try {
			cr.delete(ciudad);
		} catch (Exception e) {
			attributes.addFlashAttribute("mensaje", "Ciudad está siendo utilizado!");
			return "redirect:/listaCiudades";
		}
		
		attributes.addFlashAttribute("mensaje", "Eliminado con exito");
		return "redirect:/listaCiudades";
	}

	
}
