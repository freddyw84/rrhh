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

import py.sgarrhh.models.Ausencia;
import py.sgarrhh.models.Persona;
import py.sgarrhh.models.TipoAusencia;
import py.sgarrhh.repository.AusenciaRepository;
import py.sgarrhh.repository.PersonaRepository;
import py.sgarrhh.repository.TipoAusenciaRepository;


@Controller
public class AusenciaController {
	
	@Autowired
	private AusenciaRepository ar;

	
	@Autowired
	private TipoAusenciaRepository tar;

	@Autowired
	private PersonaRepository pr;
	

	@RequestMapping("/listaAusencias")
	public ModelAndView listaAusencias() {
		

		ModelAndView mv= new ModelAndView("Ausencia/listaAusencias");
		Iterable <Ausencia> ausencias= ar.findAll();
		mv.addObject("ausencias",ausencias);
		return mv;
	}


	
	@RequestMapping(value="/registrarAusencia", method=RequestMethod.POST)
	public String AusenciaPost( @Valid Ausencia ausencia,  BindingResult result, RedirectAttributes attributes) {
	/*System.out.println("pasé por aquí: "+ Ausencia.getId()+" "+Ausencia.getDescripcion()
						+" "+Ausencia.getDepartamento()
						+" "+Ausencia.getFuncion());*/
		
		if(result.hasErrors()){
			attributes.addFlashAttribute("mensaje", "Verifique los campos!");
			return "redirect:/registrarAusencia";
		}
		
		
		ar.save(ausencia);
		attributes.addFlashAttribute("mensaje", "Registro guardado!");

		return "ausencia/formAusencia";
	}
	
	@RequestMapping(value = { "/registrarAusencia" }, method = RequestMethod.GET)
	public String AusenciaFunciones(Model model) {
	 
	    Ausencia form = new Ausencia();
	    model.addAttribute("ausencia", form);
	   
	    Iterable <TipoAusencia> tipoAusencia= tar.findAll();
	    model.addAttribute("tipoAusencia", tipoAusencia);
	    
	    Persona persona = new Persona();
	    model.addAttribute("persona", persona);
	    
	    Iterable <Persona> personas= pr.findAll();
	    model.addAttribute("personas", personas);
	 
	    return "ausencia/formAusencia";
	}
	
	
	@RequestMapping("/a{id}")
	private ModelAndView detalleAusencia(@PathVariable("id") long id) {
        Ausencia ausencia =ar.findById(id);
		ModelAndView mvf= new ModelAndView("ausencia/detalleAusencia");
		mvf.addObject("ausencia",ausencia);
		
		Iterable <TipoAusencia> tipoAusencias= tar.findAll();
		mvf.addObject("tipoAusencias",tipoAusencias);
		
		Iterable <Persona> personas= pr.findAll();
		mvf.addObject("personas",personas);
		
		
		return mvf;
	}
	@RequestMapping(value="/a{id}", method=RequestMethod.POST)
	private String detalleAusenciaPost(@Valid Ausencia ausencia,  BindingResult result, RedirectAttributes attributes) {
		if(result.hasErrors()){
			attributes.addFlashAttribute("mensaje", "Verifique los campos!");
			return "redirect:/a{id}";
		}
		
		ar.save(ausencia);
		attributes.addFlashAttribute("mensaje", "Registro guardado!");

		return "redirect:/listaAusencias";
	}
	
	@RequestMapping("/eliminarAusencia")
	public String eliminarAusencia(long id, RedirectAttributes attributes){
		Ausencia ausencia = ar.findById(id);
		ar.delete(ausencia);
		attributes.addFlashAttribute("mensaje", "Eliminado con exito");
		return "redirect:/listaAusencias";
	}
}
