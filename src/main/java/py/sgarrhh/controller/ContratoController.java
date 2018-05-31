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

import py.sgarrhh.models.Cargo;
import py.sgarrhh.models.Contrato;
import py.sgarrhh.models.Salario;
import py.sgarrhh.models.Persona;
import py.sgarrhh.repository.CargoRepository;
import py.sgarrhh.repository.ContratoRepository;
import py.sgarrhh.repository.SalarioRepository;
import py.sgarrhh.repository.PersonaRepository;


@Controller
public class ContratoController {
	
	@Autowired
	private ContratoRepository cr;

	@Autowired
	private CargoRepository car;


	@Autowired
	private PersonaRepository pr;
	

	@Autowired
	private SalarioRepository sr;
	


	@RequestMapping("/listaContratos")
	public ModelAndView listaContratos() {
		

		ModelAndView mv= new ModelAndView("contrato/listaContratos");
		Iterable <Contrato> contratos= cr.findAll();
		mv.addObject("contratos",contratos);
		return mv;
	}


	
	@RequestMapping(value="/registrarContrato", method=RequestMethod.POST)
	public String contratoPost( @Valid Contrato contrato,  BindingResult result, RedirectAttributes attributes) {
	/*System.out.println("pasé por aquí: "+ contrato.getId()+" "+contrato.getDescripcion()
						+" "+contrato.getSalario()
						+" "+contrato.getPersona());*/
		
		if(result.hasErrors()){
			attributes.addFlashAttribute("mensaje", "Verifique los campos!");
			return "redirect:/registrarContrato";
		}
		
		
		cr.save(contrato);
		attributes.addFlashAttribute("mensaje", "Registro guardado!");

		return "contrato/formContrato";
	}
	
	@RequestMapping(value = { "/registrarContrato" }, method = RequestMethod.GET)
	public String contratoPersonas(Model model) {
		
	    Contrato form = new Contrato();
	    model.addAttribute("contrato", form);
	  	    
	    Salario salario = new Salario();
	    model.addAttribute("salario", salario);
	    
	    Iterable <Salario> salarios= sr.findAll();
	    model.addAttribute("salarios", salarios);
	    
	    Cargo cargo = new Cargo();
	    model.addAttribute("cargo", cargo);
	    
	    Iterable <Cargo> cargos= car.findAll();
	    model.addAttribute("cargos", cargos);
	 
	    
	    Persona persona = new Persona();
	    model.addAttribute("persona", persona);
	    
	    Iterable <Persona> personas= pr.findAll();
	    model.addAttribute("personas", personas);
	    
	    return "contrato/formContrato";
	}
	
	
	@RequestMapping("/cn{id}")
	private ModelAndView detalleContrato(@PathVariable("id") long id) {
		
		Contrato contrato =cr.findById(id);
		ModelAndView mvf= new ModelAndView("contrato/detalleContrato");
		mvf.addObject("contratos",contrato);
		
		Iterable <Persona> personas= pr.findAll();
		mvf.addObject("personas",personas);
		
		Iterable <Salario> salarios= sr.findAll();
		mvf.addObject("salarios",salarios);
		
		Iterable <Cargo> cargos= car.findAll();
		mvf.addObject("cargos",cargos);
		
		
        return mvf;
		
	}
	@RequestMapping(value="/cn{id}", method=RequestMethod.POST)
	private String detalleContratoPost(@Valid Contrato contrato,  BindingResult result, RedirectAttributes attributes) {
		if(result.hasErrors()){
			attributes.addFlashAttribute("mensaje", "Verifique los campos!");
			return "redirect:/cn{id}";
		}
		
		cr.save(contrato);
		attributes.addFlashAttribute("mensaje", "Registro guardado!");

		return "redirect:/listaContratos";
	}
	
	@RequestMapping("/eliminarContrato")
	public String eliminarContrato(long id, RedirectAttributes attributes){
		Contrato contrato = cr.findById(id);
		cr.delete(contrato);
		attributes.addFlashAttribute("mensaje", "Eliminado con exito");
		return "redirect:/listaContratos";
	}
}
