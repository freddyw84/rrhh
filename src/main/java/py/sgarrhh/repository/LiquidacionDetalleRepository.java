package py.sgarrhh.repository;

import org.springframework.data.repository.CrudRepository;

import py.sgarrhh.models.Concepto;
import py.sgarrhh.models.Liquidacion;
import py.sgarrhh.models.LiquidacionDetalle;


public interface LiquidacionDetalleRepository extends CrudRepository<LiquidacionDetalle, String>{
	
	LiquidacionDetalle findByLiquidacion(Liquidacion liquidacion);
	Iterable<LiquidacionDetalle> findByConcepto(Concepto concepto);

	LiquidacionDetalle findById(long id);

	
		
}
