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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	


	@RequestMapping("/listaCargos")
	public ModelAndView listaCargos() {
		

		ModelAndView mv= new ModelAndView("cargo/listaCargos");
		Iterable <Cargo> cargos= cr.findAll();
		mv.addObject("cargos",cargos);
		return mv;
	}

	@RequestMapping(value="/registrarCargo", method=RequestMethod.POST)
	public String cargoPost( @Valid Cargo cargo,  BindingResult result, RedirectAttributes attributes) {
		System.out.println("pos1");

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
	
	
	@RequestMapping("/c{id}")
	private ModelAndView detalleCargo(@PathVariable("id") long id) {
		Cargo cargos =cr.findById(id);
		ModelAndView mvf= new ModelAndView("cargo/detalleCargo");
		mvf.addObject("cargos",cargos);
		
		Iterable <Funcion> funciones= fr.findAll();
		mvf.addObject("funciones",funciones);
		
		Iterable <Departamento> departamentos= dr.findAll();
		mvf.addObject("departamentos",departamentos);
		
		
		return mvf;
	}
	@RequestMapping(value="/c{id}", method=RequestMethod.POST)
	private String detalleCargoPost(@PathVariable("id") long id, @Valid Funcion funcion,  BindingResult result, RedirectAttributes attributes) {
		
		System.out.println("pasé por posttttt: "+result);
		if(result.hasErrors()){
			attributes.addFlashAttribute("mensaje", "Verifique los campos!");
			return "redirect:/c{id}";
		}
		
		Cargo cargo = cr.findById(id);
		funcion.setCargo(cargo);
		fr.save(funcion);
		attributes.addFlashAttribute("mensaje", "Registro guardado!");
		return "redirect:/listaCargos";
	}

	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ModelAndView detallesCargo(@PathVariable("id") long id){
		Cargo cargo = cr.findById(id);
		ModelAndView mv = new ModelAndView("cargo/detalleCargo");
		mv.addObject("cargo", cargo);

		Iterable<Funcion> funciones = fr.findByCargo(cargo);
		mv.addObject("funciones", funciones);
		
		return mv;
	}
	
	@RequestMapping("/eliminarCargo")
	public String eliminarCargo(long id, RedirectAttributes attributes){
		Cargo cargo = cr.findById(id);
		cr.delete(cargo);
		attributes.addFlashAttribute("mensaje", "Eliminado con exito");
		return "redirect:/listaCargos";
	}
	@RequestMapping("/eliminarFuncionDeCargo")
	public String deletarFuncion(long id){
		Funcion funcion = fr.findById(id);
		fr.delete(funcion);
		
		Cargo cargo = funcion.getCargo();
		long codigoLong = cargo.getId();
		String codigo = "" + codigoLong;
		return "redirect:/" + codigo;
	}
}
