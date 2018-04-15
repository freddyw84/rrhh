package py.sgarrhh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import py.sgarrhh.controller.FuncionController;

@SpringBootApplication
//@ComponentScan(basePackages= {"py.sgarrhh.controller"})
//@ComponentScan(basePackageClasses=FuncionController.class)

public class SgarrhhApplication {

	public static void main(String[] args) {
		SpringApplication.run(SgarrhhApplication.class, args);
	}
}
