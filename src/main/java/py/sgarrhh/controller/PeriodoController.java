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
import py.sgarrhh.models.Periodo;
import py.sgarrhh.models.Salario;
import py.sgarrhh.models.Persona;
import py.sgarrhh.repository.CargoRepository;
import py.sgarrhh.repository.PeriodoRepository;
import py.sgarrhh.repository.SalarioRepository;
import py.sgarrhh.repository.PersonaRepository;


@Controller
public class PeriodoController {
	
	@Autowired
	private PeriodoRepository per;

	
	@RequestMapping("/listaPeriodos")
	public ModelAndView listaPeriodos() {
		

		ModelAndView mv= new ModelAndView("Periodo/listaPeriodos");
		Iterable <Periodo> periodos= per.findAll();
		mv.addObject("periodos",periodos);
		return mv;
	}


	
	@RequestMapping(value="/registrarPeriodo", method=RequestMethod.POST)
	public String PeriodoPost( @Valid Periodo periodo,  BindingResult result, RedirectAttributes attributes) {
	/*System.out.println("pasé por aquí: "+ Periodo.getId()+" "+Periodo.getDesperipcion()
						+" "+Periodo.getSalario()
						+" "+Periodo.getPersona());*/
		
		if(result.hasErrors()){
			attributes.addFlashAttribute("mensaje", "Verifique los campos!");
			return "redirect:/registrarPeriodo";
		}
		
		
		per.save(periodo);
		attributes.addFlashAttribute("mensaje", "Registro guardado!");

		return "periodo/formPeriodo";
	}
	
	@RequestMapping(value = { "/registrarPeriodo" }, method = RequestMethod.GET)
	public String PeriodoPersonas(Model model) {
		
	    Periodo form = new Periodo();
	    model.addAttribute("periodo", form);
   
	    return "periodo/formPeriodo";
	}
	
	
	@RequestMapping("/per{id}")
	private ModelAndView detallePeriodo(@PathVariable("id") long id) {
		
		Periodo periodo =per.findById(id);
		ModelAndView mvf= new ModelAndView("Periodo/detallePeriodo");
		mvf.addObject("periodos",periodo);
	
        return mvf;
		
	}
	@RequestMapping(value="/per{id}", method=RequestMethod.POST)
	private String detallePeriodoPost(@Valid Periodo periodo,  BindingResult result, RedirectAttributes attributes) {
		if(result.hasErrors()){
			attributes.addFlashAttribute("mensaje", "Verifique los campos!");
			return "redirect:/per{id}";
		}
		
		per.save(periodo);
		attributes.addFlashAttribute("mensaje", "Registro guardado!");

		return "redirect:/listaPeriodos";
	}
	
	@RequestMapping("/eliminarPeriodo")
	public String eliminarPeriodo(long id, RedirectAttributes attributes){
		Periodo periodo = per.findById(id);
		per.delete(periodo);
		attributes.addFlashAttribute("mensaje", "Eliminado con exito");
		return "redirect:/listaPeriodos";
	}
}
