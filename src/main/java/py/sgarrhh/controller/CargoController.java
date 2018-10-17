package py.sgarrhh.controller;




import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
//import org.springframework.web.servlet.view.jasperreports.JasperReportsXlsView;

import py.sgarrhh.models.Cargo;
import py.sgarrhh.models.CargoDetalle;
import py.sgarrhh.models.Departamento;
import py.sgarrhh.models.Funcion;
import py.sgarrhh.repository.CargoDetalleRepository;
import py.sgarrhh.repository.CargoRepository;
import py.sgarrhh.repository.DepartamentoRepository;
import py.sgarrhh.repository.FuncionRepository;
import py.sgarrhh.util.Pager;
import py.sgarrhh.pojo.SGARRHHPojo;



@Controller
public class CargoController {
	private Logger log = Logger.getLogger(CargoController.class);
	@Autowired
	private CargoRepository cr;

	@Autowired
	private CargoDetalleRepository cf;

	@Autowired
	private FuncionRepository fr;
	

	@Autowired
	private DepartamentoRepository dr;
    @Autowired
    private ApplicationContext appContext;
    @Autowired
    private DataSource dataSource;

	@RequestMapping("/listaCargos")
	public ModelAndView listaCargos(Model model, Pageable pgbl) {
        model.addAttribute("ep", new SGARRHHPojo());//comentar para probar
        model.addAttribute("allEmployees", cr.findAll(pgbl));
   //     model.addAttribute("pager", currentPage(pgbl));//comentar para probar

 //       System.out.println("currentIndex: " + currentPage(pgbl).getCurrentIndex());
        System.out.println("totalItems: " + cr.findAll(pgbl).getTotalElements());
        System.out.println("totalPageCount: " + cr.findAll(pgbl).getTotalPages());
		System.out.println("pase por lista cargo");
		ModelAndView mv= new ModelAndView("cargo/listaCargos");
		Iterable <Cargo> cargos= cr.findAll();
		mv.addObject("cargos",cargos);
		
		return mv;
	}
	//comentar para probar
	  private Pager currentPage(Pageable pgbl) {

	        String baseUrl = "/employees?page=";
	        int currentIndex = pgbl.getPageNumber();
	        int totalPageCount = cr.findAll(pgbl).getTotalPages();
	        long totalItems = cr.findAll(pgbl).getTotalElements();

	        Pager pager = new Pager();

	        if (currentIndex > totalPageCount) {
	            pager.setCurrentIndex(totalPageCount);
	        } else {
	            pager.setCurrentIndex(currentIndex);
	        }

	        pager.setTotalPageCount(totalPageCount - 1);
	        pager.setTotalItems(totalItems);
	        pager.setBaseUrl(baseUrl);
	        return pager;
	    }
	    //comentar para probar
	  /*@ExceptionHandler()
    @RequestMapping(value = "/pdf", method = RequestMethod.GET, produces = "application/pdf")
    public ModelAndView getPdf(@ModelAttribute SGARRHHPojo ep, Model model) {

        model.addAttribute("ep", ep);

        JasperReportsPdfView view = new JasperReportsPdfView();

        view.setJdbcDataSource(dataSource);
        if (ep.getGender().equals("A")) {
            view.setUrl("classpath:/static/jasper/cargos.jasper");
        } else {
            view.setUrl("classpath:/static/jasper/AllEmployeesByGen.jasper");
        }

        view.setApplicationContext(appContext);

        log.info("datasource is: " + dataSource);
        log.info("appContext is: " + appContext.getApplicationName());
        log.info("gender is: " + ep.getGender().toUpperCase());

        if (ep.getGender().equals("A")) {
            return new ModelAndView(view);
        } else {
            Map<String, Object> params = new HashMap<>();
            params.put("gender", ep.getGender().toUpperCase());
            return new ModelAndView(view, params);
        }
    }
//comentar para probar
    @ExceptionHandler()
    @RequestMapping(value = "/excel.xlsx", method = RequestMethod.GET, produces = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
    public ModelAndView getExcel(@ModelAttribute SGARRHHPojo ep, Model model) {

        model.addAttribute("ep", ep);

        JasperReportsXlsView view = new JasperReportsXlsView();

        view.setJdbcDataSource(dataSource);
        
        if (ep.getGender().equals("A")) {
            view.setUrl("classpath:/static/jasper/AllEmployees.jasper");
        } else {
            view.setUrl("classpath:/static/jasper/AllEmployeesByGen.jasper");
        }
        
        view.setApplicationContext(appContext);
        
        log.info("datasource is: " + dataSource);
        log.info("appContext is: " + appContext);
        log.info("gender is: " + ep.getGender().toUpperCase());

        if (ep.getGender().equals("A")) {
            return new ModelAndView(view);
        } else {
            Map<String, Object> params = new HashMap<>();
            params.put("gender", ep.getGender().toUpperCase());
            return new ModelAndView(view, params);
        }
    }
*/
	
	
	
	
	@RequestMapping(value="/registrarCargo", method=RequestMethod.POST)
	public String cargoPost( @Valid Cargo cargo,  BindingResult result, RedirectAttributes attributes) {
		System.out.println("pasé por guardar");
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
	    
	   /* Iterable <Funcion> funciones= fr.findAll();
	    model.addAttribute("funciones", funciones);*/
	    
	    Departamento departamento = new Departamento();
	    model.addAttribute("departamento", departamento);
	    
	    Iterable <Departamento> departamentos= dr.findAll();
	    model.addAttribute("departamentos", departamentos);
	 
	    return "cargo/formCargo";
	}
	
	/*
	@RequestMapping("/c{id}")
	private ModelAndView detalleCargo(@PathVariable("id") long id) {
		System.out.println("aaaaqui paseee");
		Cargo cargo =cr.findById(id);
		ModelAndView mvf= new ModelAndView("cargo/detalleCargo");
		mvf.addObject("cargos",cargo);
		
		Iterable <Funcion> funciones= fr.findAll();
		mvf.addObject("funciones",funciones);
		
		Iterable <Departamento> departamentos= dr.findAll();
		mvf.addObject("departamentos",departamentos);
		
		Iterable <CargoDetalle> cargoDetalles= cf.findByCargo(cargo);
		mvf.addObject("cargoDetalles",cargoDetalles);
		return mvf;
	}*/
	@RequestMapping( value="/c{id}", method=RequestMethod.POST)
	private String detalleCargoPost(CargoDetalle cargoDetalle,Cargo cargo,@RequestParam String action,@PathVariable("id") long id,  BindingResult result, RedirectAttributes attributes) {
		if(result.hasErrors()){
			attributes.addFlashAttribute("mensaje", "Verifique los campos!");
			return "redirect:/c{id}";
		}
		
		if(action.equals("guardar")) {
			cr.save(cargo);
		}else {
			cargoDetalle.setCargo(cargo);
			cf.save(cargoDetalle);
		}
		attributes.addFlashAttribute("mensaje", "Registro guardado!");
		return "redirect:/listaCargos";
	}

	
	@RequestMapping(value="/c{id}", method=RequestMethod.GET)
	public ModelAndView detalleCargo(@PathVariable("id") long id){
		System.out.println("pase por cargo detalle: "+id);
		Cargo cargo = cr.findById(id);
		ModelAndView mv = new ModelAndView("cargo/detalleCargo");
		mv.addObject("cargo", cargo);
		
		CargoDetalle cargoDetalle = new CargoDetalle();
	    mv.addObject("cargoDetalle", cargoDetalle);
		
		Iterable<Departamento> departamentos = dr.findAll();
		mv.addObject("departamentos", departamentos);
		
		Iterable <Funcion> funciones= fr.findAll();
		mv.addObject("funciones", funciones);  
		
		Iterable <CargoDetalle> cargoDetalles= cf.findByCargo(cargo);
		mv.addObject("cargoDetalles",cargoDetalles);

		return mv;
	}
	
	@RequestMapping("/eliminarCargo")
	public String eliminarCargo(long id, RedirectAttributes attributes){
		System.out.println("pasé por el primer eliminado");
		Cargo cargo = cr.findById(id);
		cr.delete(cargo);
		attributes.addFlashAttribute("mensaje", "Eliminado con exito");
		return "redirect:/listaCargos";
	}
	@RequestMapping("/eliminarDetalleCargo")
	public String eliminarDetalleCargo(long id, RedirectAttributes attributes){
		System.out.println("pasé por el detalle eliminado");
		CargoDetalle detalleCargo = cf.findById(id);
		cf.delete(detalleCargo);
		attributes.addFlashAttribute("mensaje", "Eliminado con exito");
		return "redirect:/listaCargos";
	}
		
	
}
