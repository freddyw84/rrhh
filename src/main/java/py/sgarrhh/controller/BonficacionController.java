package py.sgarrhh.controller;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import py.sgarrhh.models.Bonificacion;
import py.sgarrhh.models.Persona;
import py.sgarrhh.models.TipoBonificacion;
import py.sgarrhh.repository.BonificacionRepository;
import py.sgarrhh.repository.PersonaRepository;
import py.sgarrhh.repository.TipoBonificacionRepository;


@Controller
public class BonficacionController {
	
	@Autowired
	private BonificacionRepository br;
	
	@Autowired
	private TipoBonificacionRepository tbr;
	
	@Autowired
	private PersonaRepository pr;
	
	/*@RequestMapping(value="/registrarBonificacion", method=RequestMethod.GET)
	public String form() {
		return "bonificacion/formBonificacion";
	}*/

	@RequestMapping(value = { "/registrarBonificacion" }, method = RequestMethod.GET)
	public String BonificacionesFK (Model model) {
		//System.out.print("pase por aqui");
	    Bonificacion form = new Bonificacion();
	   
	    model.addAttribute("bonificacion", form);
	    
	    TipoBonificacion tipoBonificacion = new TipoBonificacion();
	    model.addAttribute("tipoBonificacion", tipoBonificacion);
	    Iterable <TipoBonificacion> tipoBonificaciones= tbr.findAll();
	    model.addAttribute("tipoBonificaciones", tipoBonificaciones);
	   
	    
	    Persona persona = new Persona();
	    model.addAttribute("persona", persona);
	    Iterable <Persona> personas= pr.findAll();
	    model.addAttribute("personas", personas);  
	    
	    
	    
	    return "bonificacion/formBonificacion";
	}
	

	@RequestMapping(value="/registrarBonificacion", method=RequestMethod.POST)
	public String form(@Valid Bonificacion bonificacion,  BindingResult result, RedirectAttributes attributes) {
	
	/*	System.out.println("pasé por aquí: "+ bonificacion.getId()+" "+bonificacion.getDescripcion()
		+" "+bonificacion.getMonto()
		+" "+bonificacion.getEstado()
		+" "+bonificacion.getObservacion()
		+" "+bonificacion.getFecha()
		+" "+bonificacion.getPersona()
		+" "+bonificacion.getTipoBonificacion());*/
		
		if(result.hasErrors()){
			attributes.addFlashAttribute("mensaje", "Verifique los campos!");
			return "redirect:/registrarBonificacion";
		}
		
			br.save(bonificacion);
		
		
		attributes.addFlashAttribute("mensaje", "Registro guardado!");
		
		return "redirect:/registrarBonificacion";
	}

	@RequestMapping("/listaBonificaciones")
	public ModelAndView listaBonificaciones() {

		ModelAndView mv= new ModelAndView("bonificacion/listaBonificaciones");
		Iterable <Bonificacion> bonificaciones= br.findAll();
		mv.addObject("bonificaciones",bonificaciones);
		return mv;
	}
	
	@RequestMapping("/b{id}")
	private ModelAndView detalleBonificacion(@PathVariable("id") long id) {
        Bonificacion bonificacion =br.findById(id);
		ModelAndView mvf= new ModelAndView("bonificacion/detalleBonificacion");
		mvf.addObject("bonificaciones",bonificacion);
		
		return mvf;
	}
	
	
	@RequestMapping(value="/b{id}", method=RequestMethod.POST)
	public String detalleBonificacionPost(@PathVariable("id") long id, @Valid Bonificacion bonificacion,  BindingResult result, RedirectAttributes attributes){
		if(result.hasErrors()){
			attributes.addFlashAttribute("mensaje", "Verifique los campos!");
			return "redirect:/b{id}";
		}
		
			br.save(bonificacion);
		
		
		attributes.addFlashAttribute("mensaje", "Registro guardado!");
		return "redirect:/listaBonificaciones";
	}
	
	

	@RequestMapping("/eliminarBonificacion")
	private String eliminarBonificacion(long id, RedirectAttributes attributes){
		
		Bonificacion bonificacion = br.findById(id);
		br.delete(bonificacion);
		attributes.addFlashAttribute("mensaje", "Eliminado con exito");

		return "redirect:/listaBonificaciones";
	}
	
}
