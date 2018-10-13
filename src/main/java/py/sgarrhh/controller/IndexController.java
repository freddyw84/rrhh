package py.sgarrhh.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;




@Controller
@RequestMapping("/")
public class IndexController {
	

	
	@RequestMapping(value = "")
	public String index() {

		return "index";
	}
	
/*	@RequestMapping("/login")
	public String login() {
		return "login";
	}*/
}
