package py.sgarrhh.repository;




import java.util.List;

import org.springframework.data.repository.CrudRepository;


import py.sgarrhh.models.Bonificacion;
import py.sgarrhh.models.Liquidacion;
import py.sgarrhh.models.Periodo;
import py.sgarrhh.models.Persona;

public interface BonificacionRepository extends CrudRepository<Bonificacion, String>{
	Bonificacion findById(long id);
	List<Bonificacion> findByPeriodoInAndPersonaInAndEstadoIn(Periodo periodo, Persona persona, String estado);
	List<Bonificacion> findAllById(List<Bonificacion> bonificacion);
	List<Bonificacion> findAllByLiquidacion(Liquidacion liquidacion);
	

}
