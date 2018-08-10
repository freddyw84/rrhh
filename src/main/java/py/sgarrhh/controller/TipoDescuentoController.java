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

import py.sgarrhh.models.TipoDescuento;
import py.sgarrhh.repository.TipoDescuentoRepository;


@Controller
public class TipoDescuentoController {
	
	@Autowired
	private TipoDescuentoRepository tdr;
	
	@RequestMapping(value="/registrarTipoDescuento", method=RequestMethod.GET)
	public String form() {
		return "tipoDescuento/formTipoDescuento";
	}

	@RequestMapping(value="/registrarTipoDescuento", method=RequestMethod.POST)
	public String form(@Valid TipoDescuento tipoDescuento, BindingResult result, RedirectAttributes attributes) {
		if(result.hasErrors()){
			attributes.addFlashAttribute("mensaje", "Verifique los campos!");
			return "redirect:/registrarTipoDescuento";
		}
		tdr.save(tipoDescuento);
		attributes.addFlashAttribute("mensaje", "Registro guardado!");
	
		return "redirect:/registrarTipoDescuento";
	}

	@RequestMapping("/listaTipoDescuentos")
	public ModelAndView listaTipoDescuentos() {
		
		ModelAndView mv= new ModelAndView("tipoDescuento/listaTipoDescuentos");
		
		Iterable <TipoDescuento> tipoDescuento= tdr.findAll();
		mv.addObject("tipoDescuentos",tipoDescuento);
		return mv;
	}
		
	@RequestMapping("/tde{id}")
	private ModelAndView detalleTipoDescuento(@PathVariable("id") long id) {
		
		TipoDescuento tipoDescuento = tdr.findById(id);
		ModelAndView mvd= new ModelAndView("tipoDescuento/detalleTipoDescuento");
		mvd.addObject("tipoDescuentos",tipoDescuento);
		
		return mvd;
	}
	
	@RequestMapping(value="/tde{id}", method=RequestMethod.POST)
	private String detalleTipoDescuentoPost(@PathVariable("id") long id,@Valid TipoDescuento tipoDescuento, BindingResult result, RedirectAttributes attributes) {
		if(result.hasErrors()){
			attributes.addFlashAttribute("mensaje", "Verifique los campos!");
			return "redirect:/td{id}";
		}
		tdr.save(tipoDescuento);
		attributes.addFlashAttribute("mensaje", "Registro guardado!");
		return "redirect:/listaTipoDescuentos";
	}
	
	@RequestMapping("/eliminarTipoDescuento")
	private String eliminarTipoDescuento(long id, RedirectAttributes attributes){
		TipoDescuento tipoDescuento = tdr.findById(id);
		try {
			tdr.delete(tipoDescuento);
		} catch (Exception e) {
			attributes.addFlashAttribute("mensaje", "TipoDescuento está siendo utilizado!");
			return "redirect:/listaTipoDescuentos";
		}
		
		attributes.addFlashAttribute("mensaje", "Eliminado con exito");
		return "redirect:/listaTipoDescuentos";
	}

	
}
