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

import py.sgarrhh.models.Bonificacion;
import py.sgarrhh.models.Descuento;
import py.sgarrhh.models.Liquidacion;
import py.sgarrhh.models.LiquidacionBonificacion;
import py.sgarrhh.models.LiquidacionDescuento;
import py.sgarrhh.models.LiquidacionDetalle;
import py.sgarrhh.models.Periodo;
import py.sgarrhh.models.Persona;
import py.sgarrhh.models.TipoLiquidacion;
import py.sgarrhh.repository.BonificacionRepository;
import py.sgarrhh.repository.DescuentoRepository;
import py.sgarrhh.repository.LiquidacionBonificacionRepository;
import py.sgarrhh.repository.LiquidacionDescuentoRepository;
import py.sgarrhh.repository.LiquidacionDetalleRepository;
import py.sgarrhh.repository.LiquidacionRepository;
import py.sgarrhh.repository.PeriodoRepository;
import py.sgarrhh.repository.PersonaRepository;
import py.sgarrhh.repository.TipoLiquidacionRepository;


@Controller
public class LiquidacionController {
	
	@Autowired
	private LiquidacionRepository lr;

	@Autowired
	private PersonaRepository pr;
	
		
	@Autowired
	private BonificacionRepository br;
	
	@Autowired
	private DescuentoRepository dr;

	@Autowired
	private TipoLiquidacionRepository tlr;
	
	@Autowired
	private LiquidacionDetalleRepository ldr;
	
	@Autowired
	private LiquidacionBonificacionRepository lbr;
	
	@Autowired
	private LiquidacionDescuentoRepository lder;
	
	@Autowired
	private PeriodoRepository per;

	@RequestMapping("/listaLiquidaciones")
	public ModelAndView listaLiquidaciones() {
		

		ModelAndView mv= new ModelAndView("liquidacion/listaLiquidaciones");
		Iterable <Liquidacion> liquidaciones= lr.findAll();
		mv.addObject("liquidaciones",liquidaciones);
		return mv;
	}


	
	@RequestMapping(value="/registrarLiquidacion", method=RequestMethod.POST)
	public String liquidacionPost( @Valid Liquidacion liquidacion,  BindingResult result, RedirectAttributes attributes) {
	/*System.out.println("pasé por aquí: "+ liquidacion.getId()+" "+liquidacion.getDeslripcion()
						+" "+liquidacion.getDepartamento()
						+" "+liquidacion.getFuncion());*/
		
		if(result.hasErrors()){
			attributes.addFlashAttribute("mensaje", "Verifique los campos!");
			return "redirect:/registrarLiquidacion";
		}
		
		lr.save(liquidacion);
			
		Bonificacion bonificacion = br.findByPeriodo(liquidacion.getPeriodo());
		
		LiquidacionBonificacion liquidacionBonificacion = new LiquidacionBonificacion();
		liquidacionBonificacion.setBonificacion(bonificacion);
		liquidacionBonificacion.setLiquidacion(liquidacion);
		lbr.save(liquidacionBonificacion);
		
		Descuento descuento = dr.findByPeriodo(liquidacion.getPeriodo());
		
		LiquidacionDescuento liquidacionDescuento = new LiquidacionDescuento();
		liquidacionDescuento.setDescuento(descuento);
		liquidacionDescuento.setLiquidacion(liquidacion);
		lder.save(liquidacionDescuento);
		
		
		
		attributes.addFlashAttribute("mensaje", "Registro guardado!");

		return "liquidacion/formLiquidacion";
	}
	
	@RequestMapping(value = { "/registrarLiquidacion" }, method = RequestMethod.GET)
	public String liquidacionFK(Model model) {
	 
	    Liquidacion form = new Liquidacion();
	    model.addAttribute("liquidacion", form);
	   
	    Persona persona = new Persona();
	    model.addAttribute("persona", persona);
	    Iterable <Persona> personas= pr.findAll();
	    model.addAttribute("personas", personas);
	    
	    
	    TipoLiquidacion tipoLiquidacion = new TipoLiquidacion();
	    model.addAttribute("tipoLiquidacion", tipoLiquidacion);
	    Iterable <TipoLiquidacion> tipoLiquidaciones= tlr.findAll();
	    model.addAttribute("tipoLiquidaciones", tipoLiquidaciones);
	    
	    
	    Periodo periodo = new Periodo();
	    model.addAttribute("periodo", periodo);
	 
	    Iterable <Periodo> periodos= per.findAll();
	    model.addAttribute("periodos", periodos);
	 
	    return "liquidacion/formLiquidacion";
	}
	
	
	@RequestMapping(value = {"/lq{id}"} , method = RequestMethod.GET)
	private ModelAndView detalleLiquidacion(@PathVariable("id") long id) {
		//System.out.println("pasé por aquí:");
		
		Liquidacion liquidacion =lr.findById(id);
		ModelAndView mv= new ModelAndView("liquidacion/detalleLiquidacion");
		mv.addObject("liquidacion",liquidacion);
		
		
        ///Detalle de las liquidaciones

		LiquidacionDetalle liquidacionDetalles = ldr.findByLiquidacion(liquidacion);
		mv.addObject("liquidacionDetalles", liquidacionDetalles);
		
	
        ///Bonificacion de las liquidaciones

		LiquidacionBonificacion liquidacionBonificaciones = lbr.findByLiquidacion(liquidacion);
		mv.addObject("liquidacionBonificaciones", liquidacionBonificaciones);
		
		
        ///Descuento de las liquidaciones
		LiquidacionDescuento liquidacionDescuentos = lder.findByLiquidacion(liquidacion);
		mv.addObject("liquidacionDescuentos", liquidacionDescuentos);
		
		
		return mv;
	}	
	@RequestMapping(value="/lq{id}", method=RequestMethod.POST)
	private String detalleLiquidacionPost(@PathVariable("id") long id, @Valid LiquidacionDetalle liquidacionDetalle,  BindingResult result, RedirectAttributes attributes) {
		
		//System.out.println("pasé por aquí:");
		if(result.hasErrors()){
			attributes.addFlashAttribute("mensaje", "Verifique los campos!");
			return "redirect:/lq{id}";
		}
		
		
		Liquidacion liquidacion = lr.findById(id);
		liquidacionDetalle.setLiquidacion(liquidacion);
		ldr.save(liquidacionDetalle);
				
//		ldr.save(liquidacionDetalle);
		attributes.addFlashAttribute("mensaje", "Registro guardado!");

		return "redirect:liquidacion/detalleLiquidacion";
	}
	
	
	@RequestMapping("/eliminarLiquidacion")
	public String eliminarLiquidacion(long id, RedirectAttributes attributes){
		Liquidacion liquidacion = lr.findById(id);
		lr.delete(liquidacion);
		attributes.addFlashAttribute("mensaje", "Eliminado con exito");
		return "redirect:/listaLiquidaciones";
	}
	
	
	
	@RequestMapping("/eliminarLiquidacionDetalle")
	public String eliminarLiquidacionDetalle(long id, RedirectAttributes attributes){
		
		String codigo="";
		long codigoLong = 0L;
		
		LiquidacionDetalle liquidacionDetalle = ldr.findById(id);
		if (liquidacionDetalle != null) {
			ldr.delete(liquidacionDetalle);
			Liquidacion liquidacion = liquidacionDetalle.getLiquidacion();
			codigoLong = liquidacion.getId();
			codigo = "" + codigoLong;
		}
	
		
		
		LiquidacionBonificacion liquidacionBonificacion  = lbr.findById(id);
		
		if (liquidacionBonificacion != null) {
			lbr.delete(liquidacionBonificacion);
			Liquidacion liquidacion = liquidacionBonificacion.getLiquidacion();
			codigoLong = liquidacion.getId();
			codigo = "" + codigoLong;
		}
		
		LiquidacionDescuento liquidacionDescuento = lder.findById(id);
		
		if (liquidacionDescuento != null) {
			lder.delete(liquidacionDescuento);
			Liquidacion liquidacion = liquidacionDescuento.getLiquidacion();
			codigoLong = liquidacion.getId();
			codigo = "" + codigoLong;
		}
		
		return "redirect:/lq" + codigo;
		
		/*Liquidacion liquidacion =lr.findById(id);
		
		LiquidacionDetalle liquidacionDetalle = ldr.findByLiquidacion(liquidacion);
		
		
		ldr.delete(liquidacionDetalle);
		attributes.addFlashAttribute("mensaje", "Eliminado con exito");
		return "redirect:/detalleLiquidacion";*/
	}
	
	

}
