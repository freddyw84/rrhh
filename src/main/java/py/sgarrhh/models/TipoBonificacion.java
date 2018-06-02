package py.sgarrhh.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
@Entity

public class TipoBonificacion implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)

	private long id;
	
	@OneToMany(mappedBy="tipoBonificacion")
	private List<Bonificacion> bonificacion;
	
	@NotNull
	@NotEmpty
	private String descripcion;
	
	private Float porcentaje;

	
	

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public List<Bonificacion> getBonificacion() {
		return bonificacion;
	}

	public void setBonificacion(List<Bonificacion> bonificacion) {
		this.bonificacion = bonificacion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Float getPorcentaje() {
		return porcentaje;
	}

	public void setPorcentaje(Float porcentaje) {
		this.porcentaje = porcentaje;
	}

	
}
