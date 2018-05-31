package py.sgarrhh.repository;



import org.springframework.data.repository.CrudRepository;

import py.sgarrhh.models.Concepto;

public interface ConceptoRepository extends CrudRepository<Concepto, String>{

	 Concepto findById(long id);
	
}
