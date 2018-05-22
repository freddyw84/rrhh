package py.sgarrhh.repository;

import org.springframework.data.repository.CrudRepository;

import py.sgarrhh.models.TipoDocumento;



public interface TipoDocumentoRepository extends CrudRepository<TipoDocumento, String>{
	TipoDocumento findById(long id);
}