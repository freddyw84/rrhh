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

import py.sgarrhh.models.Rubro;
import py.sgarrhh.repository.RubroRepository;


@Controller
public class RubroController {
	
	@Autowired
	private RubroRepository rr;
	
	@RequestMapping(value="/registrarRubro", method=RequestMethod.GET)
	public String form() {
		return "rubro/formRubro";
	}

	@RequestMapping(value="/registrarRubro", method=RequestMethod.POST)
	public String form(@Valid Rubro rubro, BindingResult result, RedirectAttributes attributes) {
		if(result.hasErrors()){
			attributes.addFlashAttribute("mensaje", "Verifique los campos!");
			return "redirect:/registrarRubro";
		}
		rr.save(rubro);
		attributes.addFlashAttribute("mensaje", "Registro guardado!");
	
		return "redirect:/registrarRubro";
	}

	@RequestMapping("/listaRubros")
	public ModelAndView listaRubros() {
 
		ModelAndView mv= new ModelAndView("rubro/listaRubros");
		Iterable <Rubro> rubro= rr.findAll();
		mv.addObject("rubros",rubro);
		return mv;
	}
		
	@RequestMapping("/r{id}")
	private ModelAndView detalleRubro(@PathVariable("id") long id) {
        Rubro rubro =rr.findById(id);
		ModelAndView mvd= new ModelAndView("rubro/detalleRubro");
		mvd.addObject("rubros",rubro);
		
		return mvd;
	}
	
	@RequestMapping(value="/r{id}", method=RequestMethod.POST)
	private String detalleRubroPost(@PathVariable("id") long id,@Valid Rubro rubro, BindingResult result, RedirectAttributes attributes) {
		if(result.hasErrors()){
			attributes.addFlashAttribute("mensaje", "Verifique los campos!");
			return "redirect:/r{id}";
		}
		rr.save(rubro);
		attributes.addFlashAttribute("mensaje", "Registro guardado!");
		return "redirect:/listaRubros";
	}
	
	@RequestMapping("/eliminarRubro")
	private String eliminarRubro(long id, RedirectAttributes attributes){
		Rubro rubro = rr.findById(id);
		try {
			rr.delete(rubro);
		} catch (Exception e) {
			attributes.addFlashAttribute("mensaje", "Rubro está siendo utilizado!");
			return "redirect:/listaRubros";
		}
		
		attributes.addFlashAttribute("mensaje", "Eliminado con exito");
		return "redirect:/listaRubros";
	}

	
}
