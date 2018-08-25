package py.sgarrhh.repository;

import org.springframework.data.repository.CrudRepository;

import py.sgarrhh.models.Concepto;
import py.sgarrhh.models.Liquidacion;
import py.sgarrhh.models.LiquidacionBonificacion;


public interface LiquidacionBonificacionRepository extends CrudRepository<LiquidacionBonificacion, String>{
	
	LiquidacionBonificacion findByLiquidacion(Liquidacion liquidacion);

	LiquidacionBonificacion findById(long id);
	
}
