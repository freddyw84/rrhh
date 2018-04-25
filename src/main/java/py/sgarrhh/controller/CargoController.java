package py.sgarrhh.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


import py.sgarrhh.models.Cargo;
import py.sgarrhh.repository.CargoRepository;




@Controller
public class CargoController {
	
	@Autowired
	private CargoRepository cr;
	
	
	
	
	
	@RequestMapping(value="/registrarCargo", method=RequestMethod.GET)
	public String form() {
		return "cargo/formCargo";
	}

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
	
	
	
	
}
