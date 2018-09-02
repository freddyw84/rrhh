package py.sgarrhh.repository;

import org.springframework.data.repository.CrudRepository;

import py.sgarrhh.models.Cargo;
import py.sgarrhh.models.CargoDetalle;
import py.sgarrhh.models.Funcion;


public interface CargoDetalleRepository extends CrudRepository<CargoDetalle, String>{
	CargoDetalle findById(long id);
	Iterable<CargoDetalle> findByCargo(Cargo cargo);
	

	
}
