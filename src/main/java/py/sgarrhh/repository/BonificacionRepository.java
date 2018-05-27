package py.sgarrhh.repository;

import org.springframework.data.repository.CrudRepository;

import py.sgarrhh.models.Bonificacion;

public interface BonificacionRepository extends CrudRepository<Bonificacion, String>{
	Bonificacion findById(long id);

}
