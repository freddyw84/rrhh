package py.sgarrhh.repository;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import py.sgarrhh.models.Contrato;
import py.sgarrhh.models.Liquidacion;
import py.sgarrhh.models.Persona;

public interface ContratoRepository extends CrudRepository<Contrato, String>{
	List<Contrato> findByPersonaInAndEstadoIn(Persona persona, String estado);
	Contrato findById(long id);
	
	List<Contrato> findAllByLiquidacion(Liquidacion liquidacion);


 }
