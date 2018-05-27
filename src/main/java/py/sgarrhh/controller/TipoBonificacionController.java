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

import py.sgarrhh.models.TipoBonificacion;
import py.sgarrhh.repository.TipoBonificacionRepository;


@Controller
public class TipoBonificacionController {
	
	@Autowired
	private TipoBonificacionRepository tbr;
	
	@RequestMapping(value="/registrarTipoBonificacion", method=RequestMethod.GET)
	public String form() {
		return "tipoBonificacion/formTipoBonificacion";
	}

	@RequestMapping(value="/registrarTipoBonificacion", method=RequestMethod.POST)
	public String form(@Valid TipoBonificacion tipoBonificacion, BindingResult result, RedirectAttributes attributes) {
		if(result.hasErrors()){
			attributes.addFlashAttribute("mensaje", "Verifique los campos!");
			return "redirect:/registrarTipoBonificacion";
		}
		tbr.save(tipoBonificacion);
		attributes.addFlashAttribute("mensaje", "Registro guardado!");
	
		return "redirect:/registrarTipoBonificacion";
	}

	@RequestMapping("/listaTipoBonificaciones")
	public ModelAndView listaTipoBonificaciones() {
		
		ModelAndView mv= new ModelAndView("tipoBonificacion/listaTipoBonificaciones");
		
		Iterable <TipoBonificacion> tipoBonificacion= tbr.findAll();
		mv.addObject("tipoBonificaciones",tipoBonificacion);
		return mv;
	}
		
	@RequestMapping("/tb{id}")
	private ModelAndView detalleTipoBonificacion(@PathVariable("id") long id) {
		
		TipoBonificacion tipoBonificacion =tbr.findById(id);
		ModelAndView mvd= new ModelAndView("tipoBonificacion/detalleTipoBonificacion");
		mvd.addObject("tipoBonificaciones",tipoBonificacion);
		
		return mvd;
	}
	
	@RequestMapping(value="/tb{id}", method=RequestMethod.POST)
	private String detalleTipoBonificacionPost(@PathVariable("id") long id,@Valid TipoBonificacion tipoBonificacion, BindingResult result, RedirectAttributes attributes) {
		if(result.hasErrors()){
			attributes.addFlashAttribute("mensaje", "Verifique los campos!");
			return "redirect:/tb{id}";
		}
		tbr.save(tipoBonificacion);
		attributes.addFlashAttribute("mensaje", "Registro guardado!");
		return "redirect:/listaTipoBonificaciones";
	}
	
	@RequestMapping("/eliminarTipoBonificacion")
	private String eliminarTipoBonificacion(long id, RedirectAttributes attributes){
		TipoBonificacion tipoBonificacion = tbr.findById(id);
		try {
			tbr.delete(tipoBonificacion);
		} catch (Exception e) {
			attributes.addFlashAttribute("mensaje", "TipoBonificacion está siendo utilizado!");
			return "redirect:/listaTipoBonificaciones";
		}
		
		attributes.addFlashAttribute("mensaje", "Eliminado con exito");
		return "redirect:/listaTipoBonificaciones";
	}

	
}
