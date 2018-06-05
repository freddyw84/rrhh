package py.sgarrhh.repository;

import org.springframework.data.repository.CrudRepository;

import py.sgarrhh.models.Haber;
import py.sgarrhh.models.HaberDetalle;

public interface HaberDetalleRepository extends CrudRepository<HaberDetalle, String>{
	//Iterable<HaberDetalle> findByHaber(Haber haber);
	
	HaberDetalle findByHaber(Haber haber);
}
