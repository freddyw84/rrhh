package py.sgarrhh.repository;

import org.springframework.data.repository.CrudRepository;


import py.sgarrhh.models.LiquidacionDetalle;


public interface LiquidacionDetalleRepository extends CrudRepository<LiquidacionDetalle, String>{
	LiquidacionDetalle findById(long id);
}
