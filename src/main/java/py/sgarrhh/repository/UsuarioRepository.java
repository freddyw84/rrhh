package py.sgarrhh.repository;

import org.springframework.data.repository.CrudRepository;

import py.sgarrhh.models.Usuario;



public interface UsuarioRepository extends CrudRepository<Usuario, String>{
	 Usuario findByLogin(String login);
}
