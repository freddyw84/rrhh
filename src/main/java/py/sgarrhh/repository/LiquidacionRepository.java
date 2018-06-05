package py.sgarrhh.repository;

import org.springframework.data.repository.CrudRepository;


import py.sgarrhh.models.Liquidacion;


public interface LiquidacionRepository extends CrudRepository<Liquidacion, String>{
	Liquidacion findById(long id);
}
