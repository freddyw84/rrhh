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

import py.sgarrhh.models.TipoLiquidacion;
import py.sgarrhh.repository.TipoLiquidacionRepository;


@Controller
public class TipoLiquidacionController {
	
	@Autowired
	private TipoLiquidacionRepository tlr;
	
	@RequestMapping(value="/registrarTipoLiquidacion", method=RequestMethod.GET)
	public String form() {
		return "tipoLiquidacion/formTipoLiquidacion";
	}

	@RequestMapping(value="/registrarTipoLiquidacion", method=RequestMethod.POST)
	public String form(@Valid TipoLiquidacion tipoLiquidacion, BindingResult result, RedirectAttributes attributes) {
		if(result.hasErrors()){
			attributes.addFlashAttribute("mensaje", "Verifique los campos!");
			return "redirect:/registrarTipoLiquidacion";
		}
		tlr.save(tipoLiquidacion);
		attributes.addFlashAttribute("mensaje", "Registro guardado!");
	
		return "redirect:/registrarTipoLiquidacion";
	}

	@RequestMapping("/listaTipoLiquidaciones")
	public ModelAndView listaTipoLiquidaciones() {
 
		ModelAndView mv= new ModelAndView("tipoLiquidacion/listaTipoLiquidaciones");
		Iterable <TipoLiquidacion> tipoLiquidaciones= tlr.findAll();
		mv.addObject("tipoLiquidaciones",tipoLiquidaciones);
		return mv;
	}
		
	@RequestMapping("/tl{id}")
	private ModelAndView detalleTipoLiquidacion(@PathVariable("id") long id) {
        TipoLiquidacion tipoLiquidaciones =tlr.findById(id);
		ModelAndView mvd= new ModelAndView("tipoLiquidacion/detalleTipoLiquidacion");
		mvd.addObject("tipoLiquidaciones",tipoLiquidaciones);
		
		return mvd;
	}
	
	@RequestMapping(value="/tl{id}", method=RequestMethod.POST)
	private String detalleTipoLiquidacionPost(@PathVariable("id") long id,@Valid TipoLiquidacion tipoLiquidacion, BindingResult result, RedirectAttributes attributes) {
		if(result.hasErrors()){
			attributes.addFlashAttribute("mensaje", "Verifique los campos!");
			return "redirect:/tl{id}";
		}
		tlr.save(tipoLiquidacion);
		attributes.addFlashAttribute("mensaje", "Registro guardado!");
		return "redirect:/listaTipoLiquidaciones";
	}
	
	@RequestMapping("/eliminartipoLiquidacion")
	private String eliminartipoLiquidacion(long id, RedirectAttributes attributes){
		TipoLiquidacion tipoLiquidacion = tlr.findById(id);
		try {
			tlr.delete(tipoLiquidacion);
		} catch (Exception e) {
			attributes.addFlashAttribute("mensaje", "Liquidacion está siendo utilizado!");
			return "redirect:/listaTipoLiquidaciones";
		}
		
		attributes.addFlashAttribute("mensaje", "Eliminado con exito");
		return "redirect:/listaTipoLiquidaciones";
	}

	
}
