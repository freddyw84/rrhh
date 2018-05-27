package py.sgarrhh.repository;
import org.springframework.data.repository.CrudRepository;


import py.sgarrhh.models.Contrato;

public interface ContratoRepository extends CrudRepository<Contrato, String>{
	Contrato findById(long id);

 }
