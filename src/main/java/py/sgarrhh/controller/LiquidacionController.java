package py.sgarrhh.controller;


import java.util.List;

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
import py.sgarrhh.models.Contrato;
import py.sgarrhh.models.Descuento;
import py.sgarrhh.models.Liquidacion;
import py.sgarrhh.models.LiquidacionDescuento;
import py.sgarrhh.models.LiquidacionDetalle;
import py.sgarrhh.models.Periodo;
import py.sgarrhh.models.Persona;
import py.sgarrhh.models.Salario;
import py.sgarrhh.models.TipoLiquidacion;
import py.sgarrhh.repository.BonificacionRepository;
import py.sgarrhh.repository.ContratoRepository;
import py.sgarrhh.repository.DescuentoRepository;
import py.sgarrhh.repository.LiquidacionDescuentoRepository;
import py.sgarrhh.repository.LiquidacionDetalleRepository;
import py.sgarrhh.repository.LiquidacionRepository;
import py.sgarrhh.repository.PeriodoRepository;
import py.sgarrhh.repository.PersonaRepository;
import py.sgarrhh.repository.SalarioRepository;
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
	private LiquidacionDescuentoRepository lder;
	
	@Autowired
	private PeriodoRepository per;
	
	@Autowired
	private ContratoRepository con;
	
	
	@Autowired
	private SalarioRepository sal;


	
	@RequestMapping("/listaLiquidaciones")
	public ModelAndView listaLiquidaciones() {
		

		ModelAndView mv= new ModelAndView("liquidacion/listaLiquidaciones");
		Iterable <Liquidacion> liquidaciones= lr.findAll();
		mv.addObject("liquidaciones",liquidaciones);
		return mv;
	}


	
	@RequestMapping(value="/registrarLiquidacion", method=RequestMethod.POST)
	public String liquidacionPost( @Valid Liquidacion liquidacion,  BindingResult result, RedirectAttributes attributes) {
		
		double totalLiquidacion = 0;
		String estado = "ACTIVO";
		
		/*System.out.println("pasé por aquí: "+ liquidacion.getId()+" "+liquidacion.getDeslripcion()
						+" "+liquidacion.getDepartamento()
						+" "+liquidacion.getFuncion());*/
		
		if(result.hasErrors()){
			
				attributes.addFlashAttribute("mensaje", "Verifique los campos! "+result.getFieldErrors());

			
		
			//attributes.addFlashAttribute("mensaje", "Verifique los campos!");
			return "redirect:/registrarLiquidacion";
		}
		
		lr.save(liquidacion);
		
		
		Iterable<Contrato> contrato = con.findByPersona(liquidacion.getPersona());
		
		if (contrato != null) {
			for (Contrato con:contrato) {
				
					Salario salario = sal.findById(con.getSalario().getId());
					
					
					LiquidacionDetalle liquidacionDetalle = new LiquidacionDetalle();
					liquidacionDetalle.setMonto(salario.getMonto());
					liquidacionDetalle.setLiquidacion(liquidacion);
					ldr.save(liquidacionDetalle);
					totalLiquidacion = totalLiquidacion + liquidacionDetalle.getMonto();
	
			}
		}
	
		List<Bonificacion> bonificacion = br.findByPeriodoInAndPersonaInAndEstadoIn(liquidacion.getPeriodo(), liquidacion.getPersona(), estado);
		
		
		if (bonificacion != null) {
				liquidacion.setBonificacion(bonificacion);
				liquidacion.setMonto(totalLiquidacion);
			
				for (Bonificacion bon:bonificacion) {
				
					System.out.println("pasé por aquí2222222: "+ bon.toString());

					totalLiquidacion = totalLiquidacion + bon.getMonto();

					bon.setEstado("PROCESADO");
					br.save(bon);
					
			
				}
	
				
				lr.save(liquidacion);
				
		}
		
		Descuento descuento = dr.findByPeriodoInAndPersonaIn(liquidacion.getPeriodo(), liquidacion.getPersona());
		
		if (descuento != null) {
			LiquidacionDescuento liquidacionDescuento = new LiquidacionDescuento();
			liquidacionDescuento.setDescuento(descuento);
			liquidacionDescuento.setLiquidacion(liquidacion);
			lder.save(liquidacionDescuento);
			descuento.setEstado("PROCESADO");
			dr.save(descuento);
		
			totalLiquidacion = totalLiquidacion - liquidacionDescuento.getDescuento().getMonto();
		

		}
		
		liquidacion.setMonto(totalLiquidacion);

		lr.save(liquidacion);
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
		double totalBonificaciones = 0 ;
		String estado = "PROCESADO";

		
		//System.out.println("pasé por aquí:");
		
		Liquidacion liquidacion =lr.findById(id);
		ModelAndView mv= new ModelAndView("liquidacion/detalleLiquidacion");
		mv.addObject("liquidacion",liquidacion);
		
		
        ///Detalle de las liquidaciones

		LiquidacionDetalle liquidacionDetalles = ldr.findByLiquidacion(liquidacion);
		mv.addObject("liquidacionDetalles", liquidacionDetalles);
		
		List<Bonificacion> liquidacionBonificacion = lr.findAllById((Liquidacion) liquidacion);

		
		
        ///Bonificacion de las liquidaciones
		List<Bonificacion> liquidacionBonificaciones = br.findAllById(liquidacionBonificacion);
		
		mv.addObject("liquidacionBonificaciones", liquidacionBonificaciones);
		
		if (liquidacionBonificaciones != null) {
	
		for (Bonificacion lisBon:liquidacionBonificaciones) {

				totalBonificaciones = totalBonificaciones + lisBon.getMonto();
			
			}
		
			mv.addObject("totalBonificaciones", totalBonificaciones);
		}
		
		
        ///Descuento de las liquidaciones
		LiquidacionDescuento liquidacionDescuentos = lder.findByLiquidacion(liquidacion);
		mv.addObject("liquidacionDescuentos", liquidacionDescuentos);
		
		
		/*if (liquidacionDescuentos != null) {
			
			totalDescuentos = totalDescuentos + liquidacionDescuentos.getDescuento().getMonto();
			mv.addObject("totalDescuentos", totalDescuentos);

		}*/
		
		
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
				
		ldr.save(liquidacionDetalle);
		attributes.addFlashAttribute("mensaje", "Registro guardado!");

		return "redirect:liquidacion/detalleLiquidacion";
	}

	
	@RequestMapping("/eliminarLiquidacion")
	public String eliminarLiquidacion(long id, RedirectAttributes attributes){

		Liquidacion liquidacion = lr.findById(id);
		
		LiquidacionDetalle liquidacionDetalle = ldr.findByLiquidacion(liquidacion);
		if (liquidacionDetalle != null) {
			ldr.delete(liquidacionDetalle);
			
		}
		
	
		 List<Bonificacion> liquidacionBonificacion = liquidacion.getBonificacion();
		//System.out.println("pasé por aquí:"+liquidacionBonificacion.toString());

		// lr.deleteById(liquidacionBonificacion);

		if (liquidacionBonificacion != null) {
			
			for (Bonificacion bon:liquidacionBonificacion) {
				
				bon.setEstado("ACTIVO");
				br.save(bon);
				
				
				
			}
		
		}
		
		LiquidacionDescuento liquidacionDescuento = lder.findByLiquidacion(liquidacion);
		
		if (liquidacionDescuento != null) {
			Descuento descuento = dr.findByPeriodoInAndPersonaIn(liquidacion.getPeriodo(), liquidacion.getPersona());

			descuento.setEstado("ACTIVO");
			dr.save(descuento);
			lder.delete(liquidacionDescuento);
			
		}
		
		
		
		lr.delete(liquidacion);
		attributes.addFlashAttribute("mensaje", "Eliminado con exito");
		return "redirect:/listaLiquidaciones";
	}
	
	
	
	/*@RequestMapping("/eliminarLiquidacionDetalle")
	public String eliminarLiquidacionDetalle(Liquidacion liquidacion, RedirectAttributes attributes){
		
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
		return "redirect:/detalleLiquidacion";
	}*/
	
	

}
