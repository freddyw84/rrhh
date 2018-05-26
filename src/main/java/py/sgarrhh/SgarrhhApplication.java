package py.sgarrhh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@ComponentScan(basePackages= {"py.sgarrhh.controller"})
//@ComponentScan(basePackageClasses=FuncionController.class)

public class SgarrhhApplication {

	public static void main(String[] args) {
		SpringApplication.run(SgarrhhApplication.class, args);
		//System.out.print("pase por aqui");
		//System.out.print(new BCryptPasswordEncoder().encode("123"));
	}
}
