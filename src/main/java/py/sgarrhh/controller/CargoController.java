package py.sgarrhh.controller;




import javax.sound.midi.Soundbank;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import py.sgarrhh.models.Cargo;
import py.sgarrhh.models.CargoDetalle;
import py.sgarrhh.models.Departamento;
import py.sgarrhh.models.Funcion;
import py.sgarrhh.repository.CargoDetalleRepository;
import py.sgarrhh.repository.CargoRepository;
import py.sgarrhh.repository.DepartamentoRepository;
import py.sgarrhh.repository.FuncionRepository;



@Controller
public class CargoController {
	
	@Autowired
	private CargoRepository cr;

	@Autowired
	private CargoDetalleRepository cf;

	@Autowired
	private FuncionRepository fr;
	

	@Autowired
	private DepartamentoRepository dr;
	


	@RequestMapping("/listaCargos")
	public ModelAndView listaCargos() {
		
System.out.println("pase por lista cargo");
		ModelAndView mv= new ModelAndView("cargo/listaCargos");
		Iterable <Cargo> cargos= cr.findAll();
		mv.addObject("cargos",cargos);
		
		return mv;
	}

	@RequestMapping(value="/registrarCargo", method=RequestMethod.POST)
	public String cargoPost( @Valid Cargo cargo,  BindingResult result, RedirectAttributes attributes) {
		System.out.println("pasé por guardar");
		if(result.hasErrors()){
			attributes.addFlashAttribute("mensaje", "Verifique los campos!");
			return "redirect:/registrarCargo";
		}
		
		cr.save(cargo);
		attributes.addFlashAttribute("mensaje", "Registro guardado!");

		return "cargo/formCargo";
	}
	
	@RequestMapping(value = { "/registrarCargo" }, method = RequestMethod.GET)
	public String cargoFunciones(Model model) {
	 
	    Cargo form = new Cargo();
	    model.addAttribute("cargo", form);
	    
	    Iterable <Funcion> funciones= fr.findAll();
	    model.addAttribute("funciones", funciones);
	    
	    Departamento departamento = new Departamento();
	    model.addAttribute("departamento", departamento);
	    
	    Iterable <Departamento> departamentos= dr.findAll();
	    model.addAttribute("departamentos", departamentos);
	 
	    return "cargo/formCargo";
	}
	
	/*
	@RequestMapping("/c{id}")
	private ModelAndView detalleCargo(@PathVariable("id") long id) {
		System.out.println("aaaaqui paseee");
		Cargo cargo =cr.findById(id);
		ModelAndView mvf= new ModelAndView("cargo/detalleCargo");
		mvf.addObject("cargos",cargo);
		
		Iterable <Funcion> funciones= fr.findAll();
		mvf.addObject("funciones",funciones);
		
		Iterable <Departamento> departamentos= dr.findAll();
		mvf.addObject("departamentos",departamentos);
		
		Iterable <CargoDetalle> cargoDetalles= cf.findByCargo(cargo);
		mvf.addObject("cargoDetalles",cargoDetalles);
		return mvf;
	}*/
	@RequestMapping( value="/c{id}", method=RequestMethod.POST)
	private String detalleCargoPost(Cargo cargo,@RequestParam String action,@PathVariable("id") long id, @Valid Funcion funcion,  BindingResult result, RedirectAttributes attributes) {
		
		if(result.hasErrors()){
			attributes.addFlashAttribute("mensaje", "Verifique los campos!");
			return "redirect:/c{id}";
		}
		
		if(action.equals("guardar")) {
			cr.save(cargo);
		}else {
			System.out.println("funcion id "+funcion.getId());
			System.out.println("otro id "+id);
			CargoDetalle cargoDetalle=new CargoDetalle();
			cargoDetalle.setCargo(cargo);
			cargoDetalle.setFuncion(funcion);
			cf.save(cargoDetalle);
		}
		
		attributes.addFlashAttribute("mensaje", "Registro guardado!");
		return "redirect:/listaCargos";
	}

	
	@RequestMapping(value="/c{id}", method=RequestMethod.GET)
	public ModelAndView detalleCargo(@PathVariable("id") long id){
		System.out.println("pase por cargo detalle");
		Cargo cargo = cr.findById(id);
		ModelAndView mv = new ModelAndView("cargo/detalleCargo");
		mv.addObject("cargo", cargo);
		
		Iterable<Departamento> departamentos = dr.findAll();
		mv.addObject("departamentos", departamentos);
			    
		Iterable<Funcion> funciones = fr.findAll();
		mv.addObject("funciones", funciones);
		for(Funcion fu:funciones) {
			System.out.println("f: "+fu.getId()+" "+fu.getDescripcion());
		}
		
		Iterable <CargoDetalle> cargoDetalles= cf.findByCargo(cargo);
		mv.addObject("cargoDetalles",cargoDetalles);
		
		/*Iterable <CargoDetalle> funciones= cf.findByFuncion(cargoDetalles);
		mv.addObject("funciones",funciones);
		*/
		
		
		return mv;
	}
	
	@RequestMapping("/eliminarCargo")
	public String eliminarCargo(long id, RedirectAttributes attributes){
		System.out.println("pasé por el primer eliminado");
		Cargo cargo = cr.findById(id);
		cr.delete(cargo);
		attributes.addFlashAttribute("mensaje", "Eliminado con exito");
		return "redirect:/listaCargos";
	}
	@RequestMapping("/eliminarDetalleCargo")
	public String eliminarDetalleCargo(long id, RedirectAttributes attributes){
		System.out.println("pasé por el detalle eliminado");
		CargoDetalle detalleCargo = cf.findById(id);
		cf.delete(detalleCargo);
		attributes.addFlashAttribute("mensaje", "Eliminado con exito");
		return "redirect:/listaCargos";
	}
		
	
}
