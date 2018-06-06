package py.sgarrhh.controller;

import javax.validation.Valid;

import org.assertj.core.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import py.sgarrhh.models.Concepto;
import py.sgarrhh.models.Liquidacion;
import py.sgarrhh.models.LiquidacionDetalle;
import py.sgarrhh.models.Persona;
import py.sgarrhh.repository.ConceptoRepository;
import py.sgarrhh.repository.LiquidacionDetalleRepository;
import py.sgarrhh.repository.LiquidacionRepository;
import py.sgarrhh.repository.PersonaRepository;


@Controller
public class LiquidacionController {
	
	@Autowired
	private LiquidacionRepository lr;

	@Autowired
	private ConceptoRepository cr;
	
	@Autowired
	private PersonaRepository pr;
	
	@Autowired
	private LiquidacionDetalleRepository ldr;

	@RequestMapping("/listaLiquidaciones")
	public ModelAndView listaLiquidaciones() {
		

		ModelAndView mv= new ModelAndView("liquidacion/listaLiquidaciones");
		Iterable <Liquidacion> liquidaciones= lr.findAll();
		mv.addObject("liquidaciones",liquidaciones);
		return mv;
	}


	
	@RequestMapping(value="/registrarLiquidacion", method=RequestMethod.POST)
	public String liquidacionPost( @Valid Liquidacion liquidacion,  BindingResult result, RedirectAttributes attributes) {
	/*System.out.println("pasé por aquí: "+ liquidacion.getId()+" "+liquidacion.getDeslripcion()
						+" "+liquidacion.getDepartamento()
						+" "+liquidacion.getFuncion());*/
		
		if(result.hasErrors()){
			attributes.addFlashAttribute("mensaje", "Verifique los campos!");
			return "redirect:/registrarLiquidacion";
		}
		
		
		lr.save(liquidacion);
		attributes.addFlashAttribute("mensaje", "Registro guardado!");

		return "liquidacion/formLiquidacion";
	}
	
	@RequestMapping(value = { "/registrarLiquidacion" }, method = RequestMethod.GET)
	public String liquidacionFK(Model model) {
	 
	    Liquidacion form = new Liquidacion();
	    model.addAttribute("liquidacion", form);
	   
	    Persona persona = new Persona();
	    model.addAttribute("persona", persona);
	    Iterable <Persona> personas= pr.findAll();
	    model.addAttribute("personas", personas);
	 
	    return "liquidacion/formLiquidacion";
	}
	
	
	@RequestMapping(value = {"/lq{id}"} , method = RequestMethod.GET)
	private ModelAndView detalleLiquidacion(@PathVariable("id") long id) {
		//System.out.println("pasé por aquí:");
		
		Liquidacion liquidacion =lr.findById(id);
		ModelAndView mvf= new ModelAndView("liquidacion/detalleLiquidacion");
		mvf.addObject("liquidacion",liquidacion);
		
		
		LiquidacionDetalle liquidacionDetalle =ldr.findByLiquidacion(liquidacion);
		mvf.addObject("liquidacionDetalle",liquidacionDetalle);
	  
		
	    Iterable <Concepto> concepto= cr.findAll();
	   /* for(Concepto s:conceptos) {
	    	System.out.println("concepto: "+s.getId()+" "+s.getDescripcion()+" "+s.getHaberDetalle()+" "+s.getLiquidaciondetalle());
	    }*/
	    mvf.addObject("concepto", concepto);
		
		
		return mvf;
	}
	
	@RequestMapping(value="/lq{id}", method=RequestMethod.POST)
	private String detalleLiquidacionPost(@Valid LiquidacionDetalle liquidacionDetalle,  BindingResult result, RedirectAttributes attributes) {
		
		//System.out.println("pasé por aquí:");
		if(result.hasErrors()){
			attributes.addFlashAttribute("mensaje", "Verifique los campos!");
			return "redirect:/lq{id}";
		}
		
		ldr.save(liquidacionDetalle);
		attributes.addFlashAttribute("mensaje", "Registro guardado!");

		return "redirect:liquidacion/detalleLiquidacion";
	}
	
	
	@RequestMapping("/eliminarLiquidacion")
	public String eliminarLiquidacion(long id, RedirectAttributes attributes){
		Liquidacion liquidacion = lr.findById(id);
		lr.delete(liquidacion);
		attributes.addFlashAttribute("mensaje", "Eliminado con exito");
		return "redirect:/listaLiquidaciones";
	}
	
	@RequestMapping("/eliminarLiquidacionDetalle")
	public String eliminarLiquidacionDetalle(long id, RedirectAttributes attributes){
		
		Liquidacion liquidacion =lr.findById(id);
		
		LiquidacionDetalle liquidacionDetalle = ldr.findByLiquidacion(liquidacion);
		ldr.delete(liquidacionDetalle);
		attributes.addFlashAttribute("mensaje", "Eliminado con exito");
		return "redirect:liquidacion/detalleLiquidacion";
	}
}
