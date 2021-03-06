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

import py.sgarrhh.models.Ciudad;
import py.sgarrhh.models.Nacionalidad;
import py.sgarrhh.models.Persona;
import py.sgarrhh.models.TipoDocumento;
import py.sgarrhh.repository.CiudadRepository;
import py.sgarrhh.repository.NacionalidadRepository;
import py.sgarrhh.repository.PersonaRepository;
import py.sgarrhh.repository.TipoDocumentoRepository;


@Controller
public class PersonaController {
	
	@Autowired
	private PersonaRepository pr;
	@Autowired
	private NacionalidadRepository nr;
	@Autowired
	private TipoDocumentoRepository tdr;
	@Autowired
	private CiudadRepository cr;
	/*
	@RequestMapping(value="/registrarPersona", method=RequestMethod.GET)
	public String form() {
		return "persona/formPersona";
	}
*/
	

	@RequestMapping(value = { "/registrarPersona" }, method = RequestMethod.GET)
	public String personaItems(Model model) {
	 
	    Persona form = new Persona();
	    model.addAttribute("persona", form);
	    
	    Iterable <TipoDocumento> tipoDocumentos= tdr.findAll();
	    model.addAttribute("tipoDocumentos", tipoDocumentos);
	   
	    
	    Iterable <Nacionalidad> nacionalidades= nr.findAll();
	    model.addAttribute("nacionalidades", nacionalidades);
	    
	    Iterable <Ciudad> ciudades= cr.findAll();
	    model.addAttribute("ciudades", ciudades);
	 
	    return "persona/formPersona";
	}
	

	@RequestMapping(value="/registrarPersona", method=RequestMethod.POST)
	public String formPersona(@Valid Persona persona , BindingResult result, RedirectAttributes attributes) {
		/*System.out.println("nombre> "+persona.getNombre());
		System.out.println("errrorrr"+result.getFieldError());
		System.out.println("errrorrr"+result.toString());
		System.out.println("fec_nacimiento> "+persona.getFec_nacimiento());*/
		if(result.hasErrors()){
			attributes.addFlashAttribute("mensaje", "Verifique los campos!");
			return "redirect:/registrarPersona";
		}

		pr.save(persona);
		attributes.addFlashAttribute("mensaje", "Registro guardado!");
		return "redirect:/registrarPersona";
	}

	
	
	@RequestMapping("/listaPersonas")
	public ModelAndView listaPersonas() {

		ModelAndView mv= new ModelAndView("persona/listaPersonas");
		Iterable <Persona> personas= pr.findAll();
		mv.addObject("personas",personas);
		return mv;
	}
	@RequestMapping("/p{id}")
	private ModelAndView detallePersona(@PathVariable("id") long id) {
        Persona persona =pr.findById(id);
		ModelAndView mvf= new ModelAndView("persona/detallePersona");
		mvf.addObject("personas",persona);
		

	    Iterable <TipoDocumento> tipoDocumentos= tdr.findAll();
	    mvf.addObject("tipoDocumentos", tipoDocumentos);
	   
	    
	    Iterable <Nacionalidad> nacionalidades= nr.findAll();
	    mvf.addObject("nacionalidades", nacionalidades);
	    
	    Iterable <Ciudad> ciudades= cr.findAll();
	    mvf.addObject("ciudades", ciudades);
		
		return mvf;
	}
	
	@RequestMapping(value="/p{id}", method=RequestMethod.POST)
	private String detallePersonaPost(@PathVariable("id") long id,@Valid Persona persona, BindingResult result, RedirectAttributes attributes) {
		if(result.hasErrors()){
			attributes.addFlashAttribute("mensaje", "Verifique los campos!");
			return "redirect:/p{id}";
		}
		pr.save(persona);
		attributes.addFlashAttribute("mensaje", "Registro guardado!");
		return "redirect:/listaPersonas";
	}

	@RequestMapping("/eliminarPersona")
	private String eliminarPersona(long id, RedirectAttributes attributes){
		Persona persona = pr.findById(id);
		try {
			pr.delete(persona);
		} catch (Exception e) {
			attributes.addFlashAttribute("mensaje", "Persona está siendo utilizado!");
			return "redirect:/listaPersonas";
		}
		attributes.addFlashAttribute("mensaje", "Eliminado con exito");
		return "redirect:/listaPersonas";
	}
}
