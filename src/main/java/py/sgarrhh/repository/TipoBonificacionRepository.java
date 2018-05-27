package py.sgarrhh.repository;

import org.springframework.data.repository.CrudRepository;

import py.sgarrhh.models.TipoBonificacion;

public interface TipoBonificacionRepository extends CrudRepository<TipoBonificacion, String>{
	TipoBonificacion findById(long id);

}
