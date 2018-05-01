package py.sgarrhh.controller;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
	
	
	
	@RequestMapping(value="/registrarCargo", method=RequestMethod.GET)
	public String form() {
		return "cargo/formCargo";
	
	}
	@RequestMapping(value="/registrarCargo", method=RequestMethod.POST)
	public String form(Cargo cargo) {
		System.out.println("pase por aca cargo");

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

	
/*	@RequestMapping(value="/{funciones}", method=RequestMethod.GET)
	public ModelAndView listaFunciones() {
		ModelAndView mv= new ModelAndView("cargo/formCargo");
		Iterable <Funcion> funciones= fr.findAll();
		mv.addObject("funciones",funciones);
		return mv;
		
		
		
	}*/
	
}
