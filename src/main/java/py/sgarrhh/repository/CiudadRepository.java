package py.sgarrhh.repository;

import org.springframework.data.repository.CrudRepository;

import py.sgarrhh.models.Ciudad;



public interface CiudadRepository extends CrudRepository<Ciudad, String>{
	 Ciudad findById(long id);
}
