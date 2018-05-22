package py.sgarrhh.repository;

import org.springframework.data.repository.CrudRepository;

import py.sgarrhh.models.Persona;

public interface PersonaRepository extends CrudRepository<Persona, String>{
	Persona findById(long id);
}
