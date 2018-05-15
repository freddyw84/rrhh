package py.sgarrhh.controller;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


import py.sgarrhh.models.Cargo;
import py.sgarrhh.models.Departamento;
import py.sgarrhh.models.Funcion;
import py.sgarrhh.repository.CargoRepository;
import py.sgarrhh.repository.DepartamentoRepository;
import py.sgarrhh.repository.FuncionRepository;





@Controller
public class CargoController {
	
	@Autowired
	private CargoRepository cr;


	@Autowired
	private FuncionRepository fr;
	

	@Autowired
	private DepartamentoRepository dr;
	
/*	@RequestMapping(value="/registrarCargo", method=RequestMethod.GET)
	public String form() {
	
		return "cargo/formCargo";
		
	
	}*/
//	@RequestMapping(value="/registrarCargo", method=RequestMethod.POST)
//	public String form(Cargo cargo) {
//
//
//		cr.save(cargo);
//		
//		return "redirect:/registrarCargo";
//	}

	@RequestMapping("/listaCargos")
	public ModelAndView listaCargos() {
		

		ModelAndView mv= new ModelAndView("cargo/listaCargos");
		Iterable <Cargo> cargos= cr.findAll();
		mv.addObject("cargos",cargos);
		return mv;
	}

	@RequestMapping("/eliminarCargo")
	public String eliminarCargo(long id){
		Cargo cargo = cr.findById(id);
		cr.delete(cargo);
		return "redirect:/listaCargos";
	}
	/*@RequestMapping(value="/registrarCargo", method=RequestMethod.GET)
	public ModelAndView cargoFunciones() {
		System.out.println("get cargo");
		ModelAndView mv= new ModelAndView("cargo/formCargo");
		Iterable <Funcion> funciones= fr.findAll();
		for(Funcion fu:funciones) {
			System.out.println("f: "+fu.getId()+" "+fu.getDescripcion());
		}
		mv.addObject("funciones",funciones);
		return mv;
		
		
	}*/
	
	@RequestMapping(value="/registrarCargo", method=RequestMethod.POST)
	public String cargoPost(Cargo cargo) {
	System.out.println("pasé por aquí: "+ cargo.getId()+" "+cargo.getDescripcion()
						+" "+cargo.getDepartamento()
						+" "+cargo.getFuncion());
		cr.save(cargo);
		
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
	
	
	@RequestMapping("/c{id}")
	private ModelAndView detalleCargo(@PathVariable("id") long id) {
        Cargo cargo =cr.findById(id);
		ModelAndView mvf= new ModelAndView("cargo/detalleCargo");
		mvf.addObject("cargos",cargo);
		
		Iterable <Funcion> funciones= fr.findAll();
		mvf.addObject("funciones",funciones);
		
		Iterable <Departamento> departamentos= dr.findAll();
		mvf.addObject("departamentos",departamentos);
		
		
		return mvf;
	}
	@RequestMapping(value="/c{id}", method=RequestMethod.POST)
	private String detalleCargoPost(Cargo cargo) {
		cr.save(cargo);
		
		return "redirect:/listaCargos";
	}
	
}
