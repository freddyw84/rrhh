package py.sgarrhh.repository;

import org.springframework.data.repository.CrudRepository;

import py.sgarrhh.models.Nacionalidad;


public interface NacionalidadRepository extends CrudRepository<Nacionalidad, String>{
	Nacionalidad findById(long id);
}
