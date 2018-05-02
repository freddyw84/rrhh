package py.sgarrhh.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;


@Entity
public class Departamento  implements Serializable {
	
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private long id;
	
	@NotEmpty
	private String descripcion;
	
	@OneToMany(mappedBy="departamento", cascade={CascadeType.PERSIST}, orphanRemoval=true)
	private List<Cargo> cargo;
	

	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	


	public List<Cargo> getCargo() {
		return cargo;
	}


	public void setCargo(List<Cargo> cargo) {
		this.cargo = cargo;
	}


	public String getDescripcion() {
		return descripcion;
	}


	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	
	
	
}
