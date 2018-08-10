package py.sgarrhh.repository;

import org.springframework.data.repository.CrudRepository;

import py.sgarrhh.models.TipoAusencia;


public interface TipoAusenciaRepository extends CrudRepository<TipoAusencia, String>{
	TipoAusencia findById(long id);

}
