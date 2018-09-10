package py.sgarrhh.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import py.sgarrhh.models.Descuento;
import py.sgarrhh.models.Liquidacion;
import py.sgarrhh.models.Periodo;
import py.sgarrhh.models.Persona;

public interface DescuentoRepository extends CrudRepository<Descuento, String>{
	Descuento findById(long id);
	List<Descuento> findByPeriodoInAndPersonaInAndEstadoIn(Periodo periodo, Persona persona, String estado);
	List<Descuento> findAllByLiquidacion(Liquidacion liquidacion);

	//Descuento findByPeriodoInAndPersonaIn(Periodo periodo, Persona persona);

}
