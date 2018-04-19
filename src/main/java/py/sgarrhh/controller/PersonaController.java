package py.sgarrhh.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import py.sgarrhh.models.Persona;
import py.sgarrhh.repository.PersonaRepository;


@Controller
public class PersonaController {
	
	@Autowired
	private PersonaRepository er;
	
	@RequestMapping(value="/registrarPersona", method=RequestMethod.GET)
	public String form() {
		return "persona/formPersona";
	}

	@RequestMapping(value="/registrarPersona", method=RequestMethod.POST)
	public String form(Persona persona) {
	
		er.save(persona);
		
		return "redirect:/registrarPersona";
	}

	@RequestMapping("/listaPersonas")
	public ModelAndView listaPersonas() {

		ModelAndView mv= new ModelAndView("departamento/listaDepartamentos");
		Iterable <Persona> personas= er.findAll();
		mv.addObject("personas",personas);
		return mv;
	}

	
}
