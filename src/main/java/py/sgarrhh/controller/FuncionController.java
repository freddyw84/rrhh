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
	public String form(@Valid Funcion funcion, BindingResult result, RedirectAttributes attributes) {
		if(result.hasErrors()){
			attributes.addFlashAttribute("mensaje", "Verifique los campos!");
			return "redirect:/f{id}";
		}
		fr.save(funcion);
		attributes.addFlashAttribute("mensaje", "Registro guardado!");
		
		return "redirect:/registrarFuncion";
	}

	@RequestMapping("/listaFunciones")
	public ModelAndView listaFunciones() {

		ModelAndView mv= new ModelAndView("funcion/listaFunciones");
		Iterable <Funcion> funciones= fr.findAll();
		mv.addObject("funciones",funciones);
		return mv;
	}
	
	@RequestMapping("/f{id}")
	private ModelAndView detalleFuncion(@PathVariable("id") long id) {
        Funcion funcion =fr.findById(id);
		ModelAndView mvf= new ModelAndView("funcion/detalleFuncion");
		mvf.addObject("funciones",funcion);
		
		return mvf;
	}
	
	@RequestMapping(value="/f{id}", method=RequestMethod.POST)
	private String detalleFuncionPost(@PathVariable("id") long id,@Valid Funcion funcion, BindingResult result, RedirectAttributes attributes) {
		if(result.hasErrors()){
			attributes.addFlashAttribute("mensaje", "Verifique los campos!");
			return "redirect:/f{id}";
		}
		fr.save(funcion);
		attributes.addFlashAttribute("mensaje", "Registro guardado!");
		return "redirect:/listaFunciones";
	}
	
	@RequestMapping("/eliminarFuncion")
	private String eliminarFuncion(long id , RedirectAttributes attributes){
		Funcion funcion = fr.findById(id);
		fr.delete(funcion);
		attributes.addFlashAttribute("mensaje", "Eliminado con exito");
		return "redirect:/listaFunciones";
	}
	
	
}