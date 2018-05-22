package py.sgarrhh.models;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.security.core.GrantedAuthority;


@Entity
public class Usuario implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private long id;
	
	@NotEmpty
	@NotNull
	private String login;


	private String password;

	@ManyToMany
	@JoinTable( 
	        name = "usuarios_rol", 
	        joinColumns = @JoinColumn(
	          name = "usuario_id", referencedColumnName = "login"), 
	        inverseJoinColumns = @JoinColumn(
	          name = "rol_id", referencedColumnName = "descripcion")) 
    private List<Rol> rol;
	
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return (Collection<? extends GrantedAuthority>) this.rol;
	}


	
	public boolean isAccountNonExpired() {
		return true;
	}

	
	public boolean isAccountNonLocked() {
		return true;
	}

	
	public boolean isCredentialsNonExpired() {
		return true;
	}

	
	public boolean isEnabled() {
		return true;
	}



}
