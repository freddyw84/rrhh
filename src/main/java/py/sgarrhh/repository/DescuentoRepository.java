package py.sgarrhh.repository;

import org.springframework.data.repository.CrudRepository;

import py.sgarrhh.models.Descuento;
import py.sgarrhh.models.Periodo;

public interface DescuentoRepository extends CrudRepository<Descuento, String>{
	Descuento findById(long id);

	Descuento findByPeriodo(Periodo periodo);
}
