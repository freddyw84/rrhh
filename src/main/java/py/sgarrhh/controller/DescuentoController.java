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

import py.sgarrhh.models.Descuento;
import py.sgarrhh.models.Periodo;
import py.sgarrhh.models.Persona;
import py.sgarrhh.models.TipoDescuento;
import py.sgarrhh.repository.DescuentoRepository;
import py.sgarrhh.repository.PeriodoRepository;
import py.sgarrhh.repository.PersonaRepository;
import py.sgarrhh.repository.TipoDescuentoRepository;


@Controller
public class DescuentoController {
	
	@Autowired
	private DescuentoRepository dr;

	@Autowired
	private TipoDescuentoRepository tdr;
	

	@Autowired
	private PersonaRepository pr;
	
	@Autowired
	private PeriodoRepository prr;
	
	


	@RequestMapping("/listaDescuentos")
	public ModelAndView listaDescuentos() {
		

		ModelAndView mv= new ModelAndView("descuento/listaDescuentos");
		Iterable <Descuento> descuentos= dr.findAll();
		mv.addObject("descuentos",descuentos);
		return mv;
	}


	
	@RequestMapping(value="/registrarDescuento", method=RequestMethod.POST)
	public String descuentoPost( @Valid Descuento descuento,  BindingResult result, RedirectAttributes attributes) {
	/*System.out.println("pasé por aquí: "+ cargo.getId()+" "+cargo.getDescripcion()
						+" "+cargo.getDepartamento()
						+" "+cargo.getFuncion());*/
		
		if(result.hasErrors()){
			attributes.addFlashAttribute("mensaje", "Verifique los campos!");
			return "redirect:/registrarDescuento";
		}
		
		
		dr.save(descuento);
		attributes.addFlashAttribute("mensaje", "Registro guardado!");

		return "descuento/formDescuento";
	}
	
	@RequestMapping(value = { "/registrarDescuento" }, method = RequestMethod.GET)
	public String descuentoFunciones(Model model) {
	 
		Descuento form = new Descuento();
	    model.addAttribute("descuento", form);
	 
	    Iterable <TipoDescuento> tipoDescuentos= tdr.findAll();
	    model.addAttribute("tipoDescuentos", tipoDescuentos);
	    
	    Persona persona = new Persona();
	    model.addAttribute("persona", persona);
	    
	    
	    Iterable <Persona> personas= pr.findAll();
	    model.addAttribute("personas", personas);
	    
	    Periodo periodo = new Periodo();
	    model.addAttribute("periodo", periodo);
	    Iterable <Periodo> periodos= prr.findAll();
	    model.addAttribute("periodos", periodos);  
	    
	    
	
	    return "descuento/formDescuento";
	}
	
	
	@RequestMapping("/de{id}")
	private ModelAndView detalleDescuento(@PathVariable("id") long id) {
		Descuento descuentos =dr.findById(id);
		ModelAndView mvf= new ModelAndView("descuento/detalleDescuento");
		mvf.addObject("descuentos",descuentos);
		
		Iterable <TipoDescuento> tipoDescuentos= tdr.findAll();
		mvf.addObject("tipoDescuentos",tipoDescuentos);
		
		Iterable <Persona> personas= pr.findAll();
		mvf.addObject("personas",personas);
		
		   
	     Iterable <Periodo> periodos= prr.findAll();
		 mvf.addObject("periodos", periodos);  
			
		
		return mvf;
	}
	@RequestMapping(value="/de{id}", method=RequestMethod.POST)
	private String detalleDescuentoPost(@Valid Descuento descuento,  BindingResult result, RedirectAttributes attributes) {
		if(result.hasErrors()){
			attributes.addFlashAttribute("mensaje", "Verifique los campos!");
			return "redirect:/d{id}";
		}
		
		dr.save(descuento);
		attributes.addFlashAttribute("mensaje", "Registro guardado!");

		return "redirect:/listaDescuentos";
	}
	
	@RequestMapping("/eliminarDescuento")
	public String eliminarDescuento(long id, RedirectAttributes attributes){
		try {
		Descuento descuento = dr.findById(id);
		dr.delete(descuento);
		attributes.addFlashAttribute("mensaje", "Eliminado con exito");
		} catch (Exception e) {
			attributes.addFlashAttribute("mensaje", "No se ha podido eliminar");
		}
		return "redirect:/listaDescuentos";
	}
}
