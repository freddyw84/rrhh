package py.sgarrhh.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;


import py.sgarrhh.models.Cargo;


public interface CargoRepository extends CrudRepository<Cargo, String>{
	Cargo findById(long id);
	Page<Cargo> findAll(Pageable pgbl);  
}
