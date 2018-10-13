package py.sgarrhh.security;

import py.sgarrhh.repository.UsuarioRepository;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.stereotype.Repository;


import py.sgarrhh.models.Usuario;


@Repository
@Transactional
public class ImplementsUserDetailsService implements UserDetailsService{

	
	@Autowired
	private UsuarioRepository ur;
	

	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
		
		
		System.out.println("pasé por aquí: "+login);
		Usuario usuario = ur.findByLogin(login);
	
		System.out.println("pasé por aquí: "+usuario);
		
		if(usuario == null){
			System.out.println("pasé por aquí NULOO: "+usuario);

			throw new UsernameNotFoundException("Usuario o senha incorrecta!");
			
		}
		return new User(usuario.getLogin(), usuario.getPassword(), true, true, true, true, usuario.getAuthorities());
		
	}
	
}
