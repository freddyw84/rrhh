package py.sgarrhh.repository;

import org.springframework.data.repository.CrudRepository;

import py.sgarrhh.models.Departamento;



public interface DepartamentoRepository extends CrudRepository<Departamento, String>{
	 Departamento findById(long departamento_id);
}
