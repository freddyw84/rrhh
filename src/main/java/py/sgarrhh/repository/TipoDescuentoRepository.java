package py.sgarrhh.repository;

import org.springframework.data.repository.CrudRepository;

import py.sgarrhh.models.TipoDescuento;



public interface TipoDescuentoRepository extends CrudRepository<TipoDescuento, String>{
	TipoDescuento findById(long id);
}