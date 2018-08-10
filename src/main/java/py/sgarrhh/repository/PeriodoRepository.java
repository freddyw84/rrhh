package py.sgarrhh.repository;
import org.springframework.data.repository.CrudRepository;


import py.sgarrhh.models.Periodo;

public interface PeriodoRepository extends CrudRepository<Periodo, String>{
	Periodo findById(long id);

 }
