package py.sgarrhh.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;



@Entity
public class Cargo  implements Serializable {
	
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private long id;
	
	@NotEmpty
	@NotNull
	private String descripcion;

	
	@NotNull
	@ManyToOne
	private Departamento departamento;
	
	@OneToMany
	private List<Funcion> funciones;
	@OneToMany(mappedBy="cargo") //, cascade={CascadeType.PERSIST}, orphanRemoval=true)
	private List<Contrato> contrato;
	

	@OneToMany(mappedBy="cargo")
	private List<CargoDetalle> cargoDetalle;
	
	public List<Funcion> getFunciones() {
		return funciones;
	}

	public void setFunciones(List<Funcion> funciones) {
		this.funciones = funciones;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	
	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

	public List<Contrato> getContrato() {
		return contrato;
	}

	public void setContrato(List<Contrato> contrato) {
		this.contrato = contrato;
	}
	
	
	
	
}
