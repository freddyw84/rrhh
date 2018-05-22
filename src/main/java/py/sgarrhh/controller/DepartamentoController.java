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
	public String form(@Valid Departamento departamento, BindingResult result, RedirectAttributes attributes) {
		if(result.hasErrors()){
			attributes.addFlashAttribute("mensaje", "Verifique los campos!");
			return "redirect:/registrarDepartamento";
		}
		dr.save(departamento);
		attributes.addFlashAttribute("mensaje", "Registro guardado!");
	
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
	
	@RequestMapping(value="/d{id}", method=RequestMethod.POST)
	private String detalleDepartamentoPost(@PathVariable("id") long id,@Valid Departamento departamento, BindingResult result, RedirectAttributes attributes) {
		if(result.hasErrors()){
			attributes.addFlashAttribute("mensaje", "Verifique los campos!");
			return "redirect:/d{id}";
		}
		dr.save(departamento);
		attributes.addFlashAttribute("mensaje", "Registro guardado!");
		return "redirect:/listaDepartamentos";
	}
	
	@RequestMapping("/eliminarDepartamento")
	private String eliminarDepartamento(long id, RedirectAttributes attributes){
		Departamento departamento = dr.findById(id);
		try {
			dr.delete(departamento);
		} catch (Exception e) {
			attributes.addFlashAttribute("mensaje", "Departamento está siendo utilizado en cargo!");
			return "redirect:/listaDepartamentos";
		}
		
		attributes.addFlashAttribute("mensaje", "Eliminado con exito");
		return "redirect:/listaDepartamentos";
	}

	
}
