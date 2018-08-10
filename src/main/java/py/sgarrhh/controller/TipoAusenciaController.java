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

import py.sgarrhh.models.TipoAusencia;
import py.sgarrhh.repository.TipoAusenciaRepository;


@Controller
public class TipoAusenciaController {
	
	@Autowired
	private TipoAusenciaRepository tar;
	
	@RequestMapping(value="/registrarTipoAusencia", method=RequestMethod.GET)
	public String form() {
		return "tipoAusencia/formTipoAusencia";
	}

	@RequestMapping(value="/registrarTipoAusencia", method=RequestMethod.POST)
	public String form(@Valid TipoAusencia tipoAusencia, BindingResult result, RedirectAttributes attributes) {
		if(result.hasErrors()){
			attributes.addFlashAttribute("mensaje", "Verifique los campos!");
			return "redirect:/registrarTipoAusencia";
		}
		tar.save(tipoAusencia);
		attributes.addFlashAttribute("mensaje", "Registro guardado!");
	
		return "redirect:/registrarTipoAusencia";
	}

	@RequestMapping("/listaTipoAusencias")
	public ModelAndView listaTipoAusencias() {
		
		ModelAndView mv= new ModelAndView("tipoAusencia/listaTipoAusencias");
		
		Iterable <TipoAusencia> tipoAusencias= tar.findAll();
		mv.addObject("tipoAusencias",tipoAusencias);
		return mv;
	}
		
	@RequestMapping("/ta{id}")
	private ModelAndView detalleTipoAusencia(@PathVariable("id") long id) {
		
		TipoAusencia tipoAusencia = tar.findById(id);
		ModelAndView mvd= new ModelAndView("tipoAusencia/detalleTipoAusencia");
		mvd.addObject("tipoAusencias",tipoAusencia);
		
		return mvd;
	}
	
	@RequestMapping(value="/ta{id}", method=RequestMethod.POST)
	private String detalleTipoAusenciaPost(@PathVariable("id") long id,@Valid TipoAusencia tipoAusencia, BindingResult result, RedirectAttributes attributes) {
		if(result.hasErrors()){
			attributes.addFlashAttribute("mensaje", "Verifique los campos!");
			return "redirect:/ta{id}";
		}
		tar.save(tipoAusencia);
		attributes.addFlashAttribute("mensaje", "Registro guardado!");
		return "redirect:/listaTipoAusencias";
	}
	
	@RequestMapping("/eliminarTipoAusencia")
	private String eliminarTipoAusencia(long id, RedirectAttributes attributes){
		TipoAusencia tipoAusencia = tar.findById(id);
		try {
			tar.delete(tipoAusencia);
		} catch (Exception e) {
			attributes.addFlashAttribute("mensaje", "Tipo Ausencia está siendo utilizado!");
			return "redirect:/listaTipoAusencias";
		}
		
		attributes.addFlashAttribute("mensaje", "Eliminado con exito");
		return "redirect:/listaTipoAusencias";
	}

	
}
