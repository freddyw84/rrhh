package py.sgarrhh.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
//import org.springframework.web.servlet.view.jasperreports.JasperReportsXlsView;

import py.sgarrhh.models.Cargo;
import py.sgarrhh.models.CargoDetalle;
import py.sgarrhh.models.Departamento;
import py.sgarrhh.models.Funcion;
import py.sgarrhh.repository.CargoDetalleRepository;
import py.sgarrhh.repository.CargoRepository;
import py.sgarrhh.repository.DepartamentoRepository;
import py.sgarrhh.repository.FuncionRepository;
import py.sgarrhh.pojo.SGARRHHPojo;

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
	public ModelAndView listaCargos(Model model, Pageable pgbl) {
        model.addAttribute("ep", new SGARRHHPojo());//comentar para probar
        model.addAttribute("allEmployees", cr.findAll(pgbl));
   //     model.addAttribute("pager", currentPage(pgbl));//comentar para probar

 //       System.out.println("currentIndex: " + currentPage(pgbl).getCurrentIndex());
        System.out.println("totalItems: " + cr.findAll(pgbl).getTotalElements());
        System.out.println("totalPageCount: " + cr.findAll(pgbl).getTotalPages());
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
	
	
	@RequestMapping( value="/c{id}", method=RequestMethod.POST)
	private String detalleCargoPost(CargoDetalle cargoDetalle,Cargo cargo,@RequestParam String action,@PathVariable("id") long id,  BindingResult result, RedirectAttributes attributes) {
		if(result.hasErrors()){
			attributes.addFlashAttribute("mensaje", "Verifique los campos!");
			return "redirect:/c{id}";
		}
		
		if(action.equals("guardar")) {
			cr.save(cargo);
		}else {
			cargoDetalle.setCargo(cargo);
			cf.save(cargoDetalle);
		}
		attributes.addFlashAttribute("mensaje", "Registro guardado!");
		return "redirect:/listaCargos";
	}

	
	@RequestMapping(value="/c{id}", method=RequestMethod.GET)
	public ModelAndView detalleCargo(@PathVariable("id") long id){
		System.out.println("pase por cargo detalle: "+id);
		Cargo cargo = cr.findById(id);
		ModelAndView mv = new ModelAndView("cargo/detalleCargo");
		mv.addObject("cargo", cargo);
		
		CargoDetalle cargoDetalle = new CargoDetalle();
	    mv.addObject("cargoDetalle", cargoDetalle);
		
		Iterable<Departamento> departamentos = dr.findAll();
		mv.addObject("departamentos", departamentos);
		
		Iterable <Funcion> funciones= fr.findAll();
		mv.addObject("funciones", funciones);  
		
		Iterable <CargoDetalle> cargoDetalles= cf.findByCargo(cargo);
		mv.addObject("cargoDetalles",cargoDetalles);

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
