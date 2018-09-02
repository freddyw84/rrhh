package py.sgarrhh.repository;

import org.springframework.data.repository.CrudRepository;

import py.sgarrhh.models.Concepto;
import py.sgarrhh.models.Liquidacion;
import py.sgarrhh.models.LiquidacionDetalle;


public interface LiquidacionDetalleRepository extends CrudRepository<LiquidacionDetalle, String>{
	
	LiquidacionDetalle findByLiquidacion(Liquidacion liquidacion);
	//LiquidacionDetalle findByLiquidacion(long id);

	
	//LiquidacionDetalle findById(long id);



	
		
}