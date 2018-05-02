package py.sgarrhh.controller;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


import py.sgarrhh.models.Cargo;
import py.sgarrhh.models.Funcion;
import py.sgarrhh.repository.CargoRepository;
import py.sgarrhh.repository.FuncionRepository;





@Controller
public class CargoController {
	
	@Autowired
	private CargoRepository cr;


	@Autowired
	private FuncionRepository fr;
	
/*	@RequestMapping(value="/registrarCargo", method=RequestMethod.GET)
	public String form() {
	
		return "cargo/formCargo";
		
	
	}*/
	@RequestMapping(value="/registrarCargo", method=RequestMethod.POST)
	public String form(Cargo cargo) {


		cr.save(cargo);
		
		return "redirect:/registrarCargo";
	}

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
	
	@RequestMapping(value = { "/registrarCargo" }, method = RequestMethod.GET)
	public String cargoFunciones(Model model) {
	 
	    Cargo form = new Cargo();
	    model.addAttribute("cargo", form);
	    Iterable <Funcion> funciones= fr.findAll();
	   
	    model.addAttribute("funciones", funciones);
	 
	    return "cargo/formCargo";
	}
	
}
