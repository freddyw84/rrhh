package py.sgarrhh.repository;




import org.springframework.data.repository.CrudRepository;


import py.sgarrhh.models.Bonificacion;
import py.sgarrhh.models.Periodo;

public interface BonificacionRepository extends CrudRepository<Bonificacion, String>{
	Bonificacion findById(long id);
	Bonificacion findByPeriodo(Periodo periodo);
	
	
}
