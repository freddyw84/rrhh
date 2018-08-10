package py.sgarrhh.repository;
import org.springframework.data.repository.CrudRepository;


import py.sgarrhh.models.Contrato;
import py.sgarrhh.models.Persona;

public interface ContratoRepository extends CrudRepository<Contrato, String>{
	Iterable<Contrato> findByPersona(Persona persona);
	Contrato findById(long id);

 }
