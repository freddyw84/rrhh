package py.sgarrhh.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import py.sgarrhh.models.Salario;
import py.sgarrhh.repository.SalarioRepository;


@Controller
public class SalarioController {
	
	@Autowired
	private SalarioRepository er;
	
	@RequestMapping(value="/registrarSalario", method=RequestMethod.GET)
	public String form() {
		return "salario/formSalario";
	}

	@RequestMapping(value="/registrarSalario", method=RequestMethod.POST)
	public String form(Salario salario) {
	
		er.save(salario);
		
		return "redirect:/registrarSalario";
	}

	@RequestMapping("/listaSalarios")
	public ModelAndView listaSalarios() {

		ModelAndView mv= new ModelAndView("salario/listaSalarios");
		Iterable <Salario> salarios= er.findAll();
		mv.addObject("salarios",salarios);
		return mv;
	}

	
}
