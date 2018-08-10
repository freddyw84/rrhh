package py.sgarrhh.repository;

import org.springframework.data.repository.CrudRepository;

import py.sgarrhh.models.Ausencia;


public interface AusenciaRepository extends CrudRepository<Ausencia, String>{
	Ausencia findById(long id);
}
