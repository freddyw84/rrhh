package py.sgarrhh.repository;

import org.springframework.data.repository.CrudRepository;

import py.sgarrhh.models.Cargo;
import py.sgarrhh.models.Departamento;


public interface DepartamentoRepository extends CrudRepository<Departamento, String>{
	 Departamento findById(long id);
	 Departamento findByCargo(Cargo cargo);
}
