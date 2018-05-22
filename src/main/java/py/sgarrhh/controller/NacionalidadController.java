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

import py.sgarrhh.models.Nacionalidad;
import py.sgarrhh.repository.NacionalidadRepository;


@Controller
public class NacionalidadController {
	
	@Autowired
	private NacionalidadRepository nr;
	
	@RequestMapping(value="/registrarNacionalidad", method=RequestMethod.GET)
	public String form() {
		return "nacionalidad/formNacionalidad";
	}

	@RequestMapping(value="/registrarNacionalidad", method=RequestMethod.POST)
	public String form(@Valid Nacionalidad nacionalidad, BindingResult result, RedirectAttributes attributes) {
		if(result.hasErrors()){
			attributes.addFlashAttribute("mensaje", "Verifique los campos!");
			return "redirect:/registrarNacionalidad";
		}
		nr.save(nacionalidad);
		attributes.addFlashAttribute("mensaje", "Registro guardado!");
	
		return "redirect:/registrarNacionalidad";
	}

	@RequestMapping("/listaNacionalidades")
	public ModelAndView listaNacionalidades() {
 
		ModelAndView mv= new ModelAndView("nacionalidad/listaNacionalidades");
		Iterable <Nacionalidad> nacionalidad= nr.findAll();
		mv.addObject("nacionalidades",nacionalidad);
		return mv;
	}
		
	@RequestMapping("/n{id}")
	private ModelAndView detalleNacionalidad(@PathVariable("id") long id) {
        Nacionalidad nacionalidad =nr.findById(id);
		ModelAndView mvd= new ModelAndView("nacionalidad/detalleNacionalidad");
		mvd.addObject("nacionalidades",nacionalidad);
		
		return mvd;
	}
	
	@RequestMapping(value="/n{id}", method=RequestMethod.POST)
	private String detalleNacionalidadPost(@PathVariable("id") long id,@Valid Nacionalidad nacionalidad, BindingResult result, RedirectAttributes attributes) {
		if(result.hasErrors()){
			attributes.addFlashAttribute("mensaje", "Verifique los campos!");
			return "redirect:/n{id}";
		}
		nr.save(nacionalidad);
		attributes.addFlashAttribute("mensaje", "Registro guardado!");
		return "redirect:/listaNacionalidades";
	}
	
	@RequestMapping("/eliminarNacionalidad")
	private String eliminarNacionalidad(long id, RedirectAttributes attributes){
		Nacionalidad nacionalidad = nr.findById(id);
		try {
			nr.delete(nacionalidad);
		} catch (Exception e) {
			attributes.addFlashAttribute("mensaje", "Nacionalidad está siendo utilizado!");
			return "redirect:/listaNacionalidades";
		}
		
		attributes.addFlashAttribute("mensaje", "Eliminado con exito");
		return "redirect:/listaNacionalidades";
	}

	
}
