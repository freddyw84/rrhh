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
public class TipoEntradaSalida implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer id;
	private String descripcion;
	
	@OneToMany(mappedBy="tipoEntradaSalida", cascade={CascadeType.PERSIST}, orphanRemoval=true)
	private List<EntradaSalida> entradaSalida;
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
	public List<EntradaSalida> getEntradaSalida() {
		return entradaSalida;
	}
	public void setEntradaSalida(List<EntradaSalida> entradaSalida) {
		this.entradaSalida = entradaSalida;
	}
	
	
}
