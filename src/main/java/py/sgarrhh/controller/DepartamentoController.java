package py.sgarrhh.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import py.sgarrhh.models.Departamento;
import py.sgarrhh.repository.DepartamentoRepository;


@Controller
public class DepartamentoController {
	
	@Autowired
	private DepartamentoRepository er;
	
	@RequestMapping(value="/registrarDepartamento", method=RequestMethod.GET)
	public String form() {
		return "departamento/formDepartamento";
	}

	@RequestMapping(value="/registrarDepartamento", method=RequestMethod.POST)
	public String form(Departamento departamento) {
	
		er.save(departamento);
	
		return "redirect:/registrarDepartamento";
	}

	@RequestMapping("/listaDepartamentos")
	public ModelAndView listaDepartamentos() {
 
		ModelAndView mv= new ModelAndView("departamento/listaDepartamentos");
		Iterable <Departamento> departamento= er.findAll();
		mv.addObject("departamentos",departamento);
		return mv;
	}

	@RequestMapping("/{id1}")
	public ModelAndView detalleDepartamento(@PathVariable("id") long id) {
        Departamento f =er.findById(id);
		ModelAndView mv= new ModelAndView("departamento/detalleDepartamento");
		mv.addObject("departamentos",f);
		
		return mv;
	}
	@RequestMapping(value="/{id1}", method=RequestMethod.POST)
	public String detalleDepartamentoPost(Departamento departamento) {
		er.save(departamento);
		
		return "redirect:/listaDepartamentos";
	}
	
	
}
