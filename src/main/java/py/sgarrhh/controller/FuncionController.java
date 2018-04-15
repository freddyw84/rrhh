package py.sgarrhh.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FuncionController {
	@RequestMapping("/registrarFuncion")
	public String form() {
		return "funcion/formFuncion";
	}

}
