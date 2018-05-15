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
	private DepartamentoRepository dr;
	
	@RequestMapping(value="/registrarDepartamento", method=RequestMethod.GET)
	public String form() {
		return "departamento/formDepartamento";
	}

	@RequestMapping(value="/registrarDepartamento", method=RequestMethod.POST)
	public String form(Departamento departamento) {
	
		dr.save(departamento);
	
		return "redirect:/registrarDepartamento";
	}

	@RequestMapping("/listaDepartamentos")
	public ModelAndView listaDepartamentos() {
 
		ModelAndView mv= new ModelAndView("departamento/listaDepartamentos");
		Iterable <Departamento> departamento= dr.findAll();
		mv.addObject("departamentos",departamento);
		return mv;
	}
		
	@RequestMapping("/d{id}")
	private ModelAndView detalleDepartamento(@PathVariable("id") long id) {
        Departamento departamento =dr.findById(id);
		ModelAndView mvd= new ModelAndView("departamento/detalleDepartamento");
		mvd.addObject("departamentos",departamento);
		
		return mvd;
	}
	
	@RequestMapping(value="/d{idDepartamento}", method=RequestMethod.POST)
	private String detalleDepartamentoPost(Departamento departamento) {
		dr.save(departamento);
		
		return "redirect:/listaDepartamentos";
	}
	
	@RequestMapping("/eliminarDepartamento")
	private String eliminarDepartamento(long id){
		Departamento departamento = dr.findById(id);
		dr.delete(departamento);
		return "redirect:/listaDepartamentos";
	}
	
}
