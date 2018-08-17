package py.sgarrhh.repository;

import org.springframework.data.repository.CrudRepository;

import py.sgarrhh.models.TipoLiquidacion;




public interface TipoLiquidacionRepository extends CrudRepository<TipoLiquidacion, String>{
	TipoLiquidacion findById(long id);
}