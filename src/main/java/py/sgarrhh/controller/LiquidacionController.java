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
import py.sgarrhh.models.Periodo;
import py.sgarrhh.models.Persona;
import py.sgarrhh.models.TipoLiquidacion;
import py.sgarrhh.repository.BonificacionRepository;
import py.sgarrhh.repository.ContratoRepository;
import py.sgarrhh.repository.DescuentoRepository;
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
	private PeriodoRepository per;
	
	@Autowired
	private ContratoRepository cr;
	
	
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
		
		
		List<Contrato> contrato = cr.findByPersonaInAndEstadoIn(liquidacion.getPersona(), estado);
		
		if (contrato != null) {
			
			liquidacion.setContrato(contrato);
			liquidacion.setMonto(totalLiquidacion);
			
			for (Contrato con:contrato) {
									
					totalLiquidacion = totalLiquidacion + con.getSalario().getMonto();
	
					con.setEstado("PROCESADO");
					cr.save(con);
				
					
					lr.save(liquidacion);
					totalLiquidacion = totalLiquidacion + liquidacion.getMonto();
	
			}
		}
	
		List<Bonificacion> bonificacion = br.findByPeriodoInAndPersonaInAndEstadoIn(liquidacion.getPeriodo(), liquidacion.getPersona(), estado);
		
		
		if (bonificacion != null) {
				liquidacion.setBonificacion(bonificacion);
				liquidacion.setMonto(totalLiquidacion);
			
				for (Bonificacion bon:bonificacion) {
				

					totalLiquidacion = totalLiquidacion + bon.getMonto();

					bon.setEstado("PROCESADO");
					br.save(bon);
					
			
				}
	
				
				lr.save(liquidacion);
				
		}
		
		
		

		List<Descuento> descuento = dr.findByPeriodoInAndPersonaInAndEstadoIn(liquidacion.getPeriodo(), liquidacion.getPersona(), estado);
		
		
		if (descuento != null) {
				liquidacion.setDescuento(descuento);
				liquidacion.setMonto(totalLiquidacion);
			
				for (Descuento des:descuento) {
				
					System.out.println("pasé por aquí2222222: "+ des.toString());

					totalLiquidacion = totalLiquidacion - des.getMonto();

					des.setEstado("PROCESADO");
					dr.save(des);
					
			
				}
	
				
				lr.save(liquidacion);
				
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
		double totalDescuentos = 0 ;
		double totalContratos = 0;
		
		//System.out.println("pasé por aquí:");
		
		Liquidacion liquidacion =lr.findById(id);
		ModelAndView mv= new ModelAndView("liquidacion/detalleLiquidacion");
		mv.addObject("liquidacion",liquidacion);
		
		
        ///Contratos de las liquidaciones
		List<Contrato> liquidacionContratos = cr.findAllByLiquidacion(liquidacion);

				
		if (liquidacionContratos != null) {
			
			for (Contrato lisCon:liquidacionContratos) {

					totalContratos = totalContratos + lisCon.getSalario().getMonto();
				
				}
			
				mv.addObject("totalContratos", totalContratos);
			}
		
	
		mv.addObject("liquidacionContratos", liquidacionContratos);
		
	  ///Bonificacion de las liquidaciones
		List<Bonificacion> liquidacionBonificaciones = br.findAllByLiquidacion(liquidacion);
		
		mv.addObject("liquidacionBonificaciones", liquidacionBonificaciones);
		
		if (liquidacionBonificaciones != null) {
	
		for (Bonificacion lisBon:liquidacionBonificaciones) {

				totalBonificaciones = totalBonificaciones + lisBon.getMonto();
			
			}
		
			mv.addObject("totalBonificaciones", totalBonificaciones);
		}
		
		
        ///Descuento de las liquidaciones
		
		List<Descuento> liquidacionDescuentos = dr.findAllByLiquidacion(liquidacion);
		
		mv.addObject("liquidacionDescuentos", liquidacionDescuentos);
		
		if (liquidacionDescuentos != null) {
	
			for (Descuento lisDes:liquidacionDescuentos) {

				totalDescuentos = totalDescuentos + lisDes.getMonto();
			
			}
		
			mv.addObject("totalDescuentos", totalDescuentos);
			
		}
		
		
		
		return mv;
	}	
//	@RequestMapping(value="/lq{id}", method=RequestMethod.POST)
//	private String detalleLiquidacionPost(@PathVariable("id") long id,  BindingResult result, RedirectAttributes attributes) {
//		
//		//System.out.println("pasé por aquí:");
//		if(result.hasErrors()){
//			attributes.addFlashAttribute("mensaje", "Verifique los campos!");
//			return "redirect:/lq{id}";
//		}
//		
//
//		Liquidacion liquidacion = lr.findById(id);
//		liquidacionDetalle.setLiquidacion(liquidacion);
//		ldr.save(liquidacionDetalle);
//				
//		ldr.save(liquidacionDetalle);
//		attributes.addFlashAttribute("mensaje", "Registro guardado!");
//
//		return "redirect:liquidacion/detalleLiquidacion";
//	}

	
	@RequestMapping("/eliminarLiquidacion")
	public String eliminarLiquidacion(long id, RedirectAttributes attributes){

		Liquidacion liquidacion = lr.findById(id);
		
		 List<Contrato> liquidacionContrato = liquidacion.getContrato();
			

			if (liquidacionContrato != null) {
				
				for (Contrato con:liquidacionContrato) {
					
					con.setEstado("ACTIVO");
					cr.save(con);
					
					
					
				}
			
			}
		
		
		
	
		 List<Bonificacion> liquidacionBonificacion = liquidacion.getBonificacion();
		

		if (liquidacionBonificacion != null) {
			
			for (Bonificacion bon:liquidacionBonificacion) {
				
				bon.setEstado("ACTIVO");
				br.save(bon);
				
				
				
			}
		
		}
		
		
		
		 List<Descuento> liquidacionDescuento = liquidacion.getDescuento();
			//System.out.println("pasé por aquí:"+liquidacionBonificacion.toString());

			// lr.deleteById(liquidacionBonificacion);

			if (liquidacionDescuento != null) {
				
				for (Descuento des:liquidacionDescuento) {
					
					des.setEstado("ACTIVO");
					dr.save(des);
				
				}
			
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
