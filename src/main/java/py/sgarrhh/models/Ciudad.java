package py.sgarrhh.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Ciudad implements Serializable {
	

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer id;
	
	
	private String descripcion;
	
	@OneToMany(mappedBy="ciudad", cascade={CascadeType.PERSIST}, orphanRemoval=true)
	private List<Persona> persona;
	

	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getDescripcion() {
		return descripcion;
	}


	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	public List<Persona> getPersona() {
		return persona;
	}


	public void setPersona(List<Persona> persona) {
		this.persona = persona;
	}
	
	

		
}
