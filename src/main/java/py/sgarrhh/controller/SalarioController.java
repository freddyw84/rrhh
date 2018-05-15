package py.sgarrhh.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import py.sgarrhh.models.Salario;
import py.sgarrhh.repository.SalarioRepository;


@Controller
public class SalarioController {
	
	@Autowired
	private SalarioRepository sr;
	
	@RequestMapping(value="/registrarSalario", method=RequestMethod.GET)
	public String form() {
		return "salario/formSalario";
	}

	@RequestMapping(value="/registrarSalario", method=RequestMethod.POST)
	public String form(Salario salario) {
	
		sr.save(salario);
		
		return "redirect:/registrarSalario";
	}

	@RequestMapping("/listaSalarios")
	public ModelAndView listaSalarios() {

		ModelAndView mv= new ModelAndView("salario/listaSalarios");
		Iterable <Salario> salarios= sr.findAll();
		mv.addObject("salarios",salarios);
		return mv;
	}
	
	@RequestMapping("/s{id}")
	private ModelAndView detalleSalario(@PathVariable("id") long id) {
        Salario salario =sr.findById(id);
		ModelAndView mvf= new ModelAndView("salario/detalleSalario");
		mvf.addObject("salarios",salario);
		
		return mvf;
	}
	@RequestMapping(value="/s{id}", method=RequestMethod.POST)
	private String detalleSalarioPost(Salario salario) {
		sr.save(salario);
		
		return "redirect:/listaSalarios";
	}
	

	@RequestMapping("/eliminarSalario")
	private String eliminarSalario(long id){
		Salario salario = sr.findById(id);
		sr.delete(salario);
		return "redirect:/listaSalarios";
	}
	
}
