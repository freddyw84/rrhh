package py.sgarrhh.repository;



import org.springframework.data.repository.CrudRepository;

import py.sgarrhh.models.Cargo;
import py.sgarrhh.models.Funcion;

public interface FuncionRepository extends CrudRepository<Funcion, String>{
	Iterable<Funcion> findByCargo(Cargo cargo);
	Funcion findById(long id);
}
