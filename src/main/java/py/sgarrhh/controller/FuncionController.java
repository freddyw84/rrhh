package py.sgarrhh.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import py.sgarrhh.models.Funcion;
import py.sgarrhh.repository.FuncionRepository;

@Controller
public class FuncionController {
	
	@Autowired
	private FuncionRepository fr;
	
	@RequestMapping(value="/registrarFuncion", method=RequestMethod.GET)
	public String form() {
		return "funcion/formFuncion";
	}

	@RequestMapping(value="/registrarFuncion", method=RequestMethod.POST)
	public String form(Funcion funcion) {
	
		fr.save(funcion);
		
		return "redirect:/registrarFuncion";
	}

	@RequestMapping("/listaFunciones")
	public ModelAndView listaFunciones() {

		ModelAndView mv= new ModelAndView("funcion/listaFunciones");
		Iterable <Funcion> funciones= fr.findAll();
		mv.addObject("funciones",funciones);
		return mv;
	}
	@RequestMapping("/{id}")
	public ModelAndView detalleFuncion(@PathVariable("id") long id) {
        Funcion f =fr.findById(id);
		ModelAndView mv= new ModelAndView("funcion/detalleFuncion");
		mv.addObject("funciones",f);
		
		return mv;
	}
	@RequestMapping(value="/{id}", method=RequestMethod.POST)
	public String detalleFuncionPost(Funcion funcion) {
	System.out.println("pasé por aquí: "+ funcion.getId()+" "+funcion.getDescripcion());
		fr.save(funcion);
		
		return "redirect:/listaFunciones";
	}
	

	
	/*@RequestMapping(value="/{funciones}", method=RequestMethod.GET)
	public ModelAndView selectFunciones() {
		ModelAndView mv= new ModelAndView("cargo/formCargo");
		Iterable <Funcion> funciones= fr.findAll();
		mv.addObject("funciones",funciones);
		return mv;
	}
	*/
}