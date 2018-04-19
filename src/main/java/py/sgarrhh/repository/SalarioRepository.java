package py.sgarrhh.repository;

import org.springframework.data.repository.CrudRepository;

import py.sgarrhh.models.Salario;

public interface SalarioRepository extends CrudRepository<Salario, String>{
	
}
