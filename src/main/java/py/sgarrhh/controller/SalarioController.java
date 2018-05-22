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
	public String form(@Valid Salario salario,  BindingResult result, RedirectAttributes attributes) {
		if(result.hasErrors()){
			attributes.addFlashAttribute("mensaje", "Verifique los campos!");
			return "redirect:/registrarSalario";
		}
		
			sr.save(salario);
		
		
		attributes.addFlashAttribute("mensaje", "Registro guardado!");
		
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
	/*
	@RequestMapping(value="/s{id}", method=RequestMethod.POST)
	private String detalleSalarioPost(Salario salario) {
		sr.save(salario);
		
		return "redirect:/listaSalarios";
	}*/
	
	
	
	@RequestMapping(value="/s{id}", method=RequestMethod.POST)
	public String detalleSalarioPost(@PathVariable("id") long id, @Valid Salario salario,  BindingResult result, RedirectAttributes attributes){
		if(result.hasErrors()){
			attributes.addFlashAttribute("mensaje", "Verifique los campos!");
			return "redirect:/s{id}";
		}
		
			sr.save(salario);
		
		
		attributes.addFlashAttribute("mensaje", "Registro guardado!");
		return "redirect:/listaSalarios";
	}
	
	

	@RequestMapping("/eliminarSalario")
	private String eliminarSalario(long id, RedirectAttributes attributes){
		
		Salario salario = sr.findById(id);
		sr.delete(salario);
		attributes.addFlashAttribute("mensaje", "Eliminado con exito");

		return "redirect:/listaSalarios";
	}
	
}
