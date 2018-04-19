package py.sgarrhh.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import py.sgarrhh.models.Cargo;
import py.sgarrhh.models.Contrato;
import py.sgarrhh.repository.ContratoRepository;

@Controller
public class ContratoController {

	@Autowired
	private ContratoRepository er;
	
	@RequestMapping(value="/registrarContrato", method=RequestMethod.GET)
	public String form() {
		return "contrato/formContrato";
	}

	@RequestMapping(value="/registrarContrato", method=RequestMethod.POST)
	public String form(Contrato contrato) {
	
		er.save(contrato);
		
		return "redirect:/registrarContrato";
	}

	@RequestMapping("/listaContratos")
	public ModelAndView listaContratos() {

		ModelAndView mv= new ModelAndView("cargo/listaContratos");
		Iterable <Contrato> contratos= er.findAll();
		mv.addObject("contratos",contratos);
		return mv;
	}
		
}
