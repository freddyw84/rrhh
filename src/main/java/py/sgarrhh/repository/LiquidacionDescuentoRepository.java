package py.sgarrhh.repository;

import org.springframework.data.repository.CrudRepository;

import py.sgarrhh.models.Concepto;
import py.sgarrhh.models.Liquidacion;
import py.sgarrhh.models.LiquidacionDescuento;


public interface LiquidacionDescuentoRepository extends CrudRepository<LiquidacionDescuento, String>{
	
	LiquidacionDescuento findByLiquidacion(Liquidacion liquidacion);

	LiquidacionDescuento findById(long id);

	
		
}
