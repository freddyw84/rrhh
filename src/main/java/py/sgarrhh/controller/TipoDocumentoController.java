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

import py.sgarrhh.models.TipoDocumento;
import py.sgarrhh.repository.TipoDocumentoRepository;


@Controller
public class TipoDocumentoController {
	
	@Autowired
	private TipoDocumentoRepository tdr;
	
	@RequestMapping(value="/registrarTipoDocumento", method=RequestMethod.GET)
	public String form() {
		return "tipoDocumento/formTipoDocumento";
	}

	@RequestMapping(value="/registrarTipoDocumento", method=RequestMethod.POST)
	public String form(@Valid TipoDocumento tipoDocumento, BindingResult result, RedirectAttributes attributes) {
		if(result.hasErrors()){
			attributes.addFlashAttribute("mensaje", "Verifique los campos!");
			return "redirect:/registrarTipoDocumento";
		}
		tdr.save(tipoDocumento);
		attributes.addFlashAttribute("mensaje", "Registro guardado!");
	
		return "redirect:/registrarTipoDocumento";
	}

	@RequestMapping("/listaTipoDocumentos")
	public ModelAndView listaTipoDocumentos() {
 
		ModelAndView mv= new ModelAndView("tipoDocumento/listaTipoDocumentos");
		Iterable <TipoDocumento> tipoDocumento= tdr.findAll();
		mv.addObject("tipoDocumentos",tipoDocumento);
		return mv;
	}
		
	@RequestMapping("/td{id}")
	private ModelAndView detalleTipoDocumento(@PathVariable("id") long id) {
        TipoDocumento tipoDocumento =tdr.findById(id);
		ModelAndView mvd= new ModelAndView("tipoDocumento/detalleTipoDocumento");
		mvd.addObject("tipoDocumentos",tipoDocumento);
		
		return mvd;
	}
	
	@RequestMapping(value="/td{id}", method=RequestMethod.POST)
	private String detalleTipoDocumentoPost(@PathVariable("id") long id,@Valid TipoDocumento tipoDocumento, BindingResult result, RedirectAttributes attributes) {
		if(result.hasErrors()){
			attributes.addFlashAttribute("mensaje", "Verifique los campos!");
			return "redirect:/td{id}";
		}
		tdr.save(tipoDocumento);
		attributes.addFlashAttribute("mensaje", "Registro guardado!");
		return "redirect:/listaTipoDocumentos";
	}
	
	@RequestMapping("/eliminarTipoDocumento")
	private String eliminarTipoDocumento(long id, RedirectAttributes attributes){
		TipoDocumento tipoDocumento = tdr.findById(id);
		try {
			tdr.delete(tipoDocumento);
		} catch (Exception e) {
			attributes.addFlashAttribute("mensaje", "TipoDocumento está siendo utilizado!");
			return "redirect:/listaTipoDocumentos";
		}
		
		attributes.addFlashAttribute("mensaje", "Eliminado con exito");
		return "redirect:/listaTipoDocumentos";
	}

	
}
