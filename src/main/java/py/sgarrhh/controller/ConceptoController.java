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

import py.sgarrhh.models.Concepto;
import py.sgarrhh.repository.ConceptoRepository;

@Controller
public class ConceptoController {
	
	@Autowired
	private ConceptoRepository cr;
	
	
	@RequestMapping(value="/registrarConcepto", method=RequestMethod.GET)
	public String form() {
		return "concepto/formConcepto";
	}

	@RequestMapping(value="/registrarConcepto", method=RequestMethod.POST)
	public String form(@Valid Concepto concepto, BindingResult result, RedirectAttributes attributes) {
		if(result.hasErrors()){
			attributes.addFlashAttribute("mensaje", "Verifique los campos!");
			return "redirect:/con{id}";
		}
		cr.save(concepto);
		attributes.addFlashAttribute("mensaje", "Registro guardado!");
		
		return "redirect:/registrarConcepto";
	}

	@RequestMapping("/listaConceptos")
	public ModelAndView listaConceptos() {

		ModelAndView mv= new ModelAndView("concepto/listaConceptos");
		Iterable <Concepto> conceptos= cr.findAll();
		mv.addObject("conceptos",conceptos);
		return mv;
	}
	
	@RequestMapping("/con{id}")
	private ModelAndView detalleConcepto(@PathVariable("id") long id) {
        Concepto concepto =cr.findById(id);
		ModelAndView mvf= new ModelAndView("concepto/detalleConcepto");
		mvf.addObject("conceptos",concepto);
		
		return mvf;
	}
	
	@RequestMapping(value="/con{id}", method=RequestMethod.POST)
	private String detalleConceptoPost(@PathVariable("id") long id,@Valid Concepto concepto, BindingResult result, RedirectAttributes attributes) {
		if(result.hasErrors()){
			attributes.addFlashAttribute("mensaje", "Verifique los campos!");
			return "redirect:/con{id}";
		}
		cr.save(concepto);
		attributes.addFlashAttribute("mensaje", "Registro guardado!");
		return "redirect:/listaConceptos";
	}
	
	@RequestMapping("/eliminarConcepto")
	private String eliminarConcepto(long id , RedirectAttributes attributes){
		Concepto concepto = cr.findById(id);
		try {
			cr.delete(concepto);
		} catch (Exception e) {
			attributes.addFlashAttribute("mensaje", "Concepto esta siendo utilizado en cargo!");
			return "redirect:/listaConceptos";
		}
		
		
		attributes.addFlashAttribute("mensaje", "Eliminado con exito");
		return "redirect:/listaConceptos";
	}
	
	
}