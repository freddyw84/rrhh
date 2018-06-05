package py.sgarrhh.repository;

import org.springframework.data.repository.CrudRepository;

import py.sgarrhh.models.Descuento;

public interface DescuentoRepository extends CrudRepository<Descuento, String>{
	Descuento findById(long id);
}
