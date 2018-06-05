package py.sgarrhh.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import py.sgarrhh.models.Haber;
import py.sgarrhh.models.HaberDetalle;
import py.sgarrhh.models.Rubro;
import py.sgarrhh.repository.RubroRepository;
import py.sgarrhh.repository.HaberDetalleRepository;
import py.sgarrhh.repository.HaberRepository;

@Controller
public class HaberController {

	@Autowired
	private HaberRepository hr;

	@Autowired
	private RubroRepository rr;
	
	@Autowired
	private HaberDetalleRepository hdr;


	@RequestMapping("/listaHaberes")
	public ModelAndView listaHaberes() {
		ModelAndView mv = new ModelAndView("haber/listaHaberes");
		Iterable<Haber> haberes = hr.findAll();
		mv.addObject("haberes", haberes);
		return mv;
	}

	@RequestMapping(value = "/registrarHaber", method = RequestMethod.POST)
	public String haberPost(@Valid Haber haber, BindingResult result, RedirectAttributes attributes) {
		/*
		 * System.out.println("pasé por aquí: "+
		 * haber.getId()+" "+haber.getDescripcion() +" "+haber.getDepartamento()
		 * +" "+haber.getRubro());
		 */

		if (result.hasErrors()) {
			attributes.addFlashAttribute("mensaje", "Verifique los campos!");
			return "redirect:/registrarHaber";
		}

		hr.save(haber);
		attributes.addFlashAttribute("mensaje", "Registro guardado!");

		return "haber/formHaber";
	}

	@RequestMapping(value = { "/registrarHaber" }, method = RequestMethod.GET)
	public String haberRubros(Model model) {

		Haber form = new Haber();
		model.addAttribute("haber", form);
		Iterable<Rubro> rubros = rr.findAll();
		model.addAttribute("rubros", rubros);
		return "haber/formHaber";
	}


	@RequestMapping(value = {"/h{id}"}, method = RequestMethod.GET)
	private ModelAndView detalleHaber(@PathVariable("id") long id) {
		// System.out.println("pasé por aquí:");
		
		
		Haber haber = hr.findById(id);
		ModelAndView mvf = new ModelAndView("haber/detalleHaber");
		mvf.addObject("haber", haber);

		HaberDetalle haberDetalle = hdr.findByHaber(haber);
		mvf.addObject("haberDetalle", haberDetalle);

		return mvf;
	}

	@RequestMapping(value = "/h{id}", method = RequestMethod.POST)
	private String detalleHaberPost(@Valid Haber haber, BindingResult result, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			attributes.addFlashAttribute("mensaje", "Verifique los campos!");
			return "redirect:/h{id}";
		}

		hr.save(haber);
		attributes.addFlashAttribute("mensaje", "Registro guardado!");

		return "redirect:/listaHaberes";
	}

	@RequestMapping("/eliminarHaber")
	public String eliminarHaber(long id, RedirectAttributes attributes) {
		Haber haber = hr.findById(id);
		hr.delete(haber);
		attributes.addFlashAttribute("mensaje", "Eliminado con exito");
		return "redirect:/listaHaberes";
	}
	
	@RequestMapping("/eliminarHaberDetalle")
	public String eliminarHaberDetalle(long id, RedirectAttributes attributes) {
		Haber haber=hr.findById(id);
		System.out.println("id haber:: "+id);
		HaberDetalle haberDetalle = hdr.findByHaber(haber);
		
		hdr.delete(haberDetalle);
		
		attributes.addFlashAttribute("mensaje", "Eliminado con exito");
		return "redirect:/listaHaberes";
	}
	
	
	
	
	
}
