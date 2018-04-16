package py.sgarrhh.controller;

import javax.management.AttributeValueExp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import py.sgarrhh.entity.Funcion;
import py.sgarrhh.repository.FuncionRepository;

@Controller
public class FuncionController {
	
	@Autowired
	private FuncionRepository er;
	
	@RequestMapping(value="/registrarFuncion", method=RequestMethod.GET)
	public String form() {
		return "funcion/formFuncion";
	}

	@RequestMapping(value="/registrarFuncion", method=RequestMethod.POST)
	public String form(Funcion funcion) {
	
		er.save(funcion);
		
		return "redirect:/registrarFuncion";
	}

	@RequestMapping("/funciones")
	public ModelAndView listaFunciones() {

		ModelAndView mv= new ModelAndView("index");
		Iterable <Funcion> funciones= er.findAll();
		mv.addObject("funciones",funciones);
		return mv;
	}

	
}
