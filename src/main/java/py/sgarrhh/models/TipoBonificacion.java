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

public class TipoBonificacion implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)

	private Integer id;
	
	@OneToMany(mappedBy="TipoBonificacion", cascade={CascadeType.PERSIST}, orphanRemoval=true)
	private List<Bonificacion> bonificacion;
	
	private String descripcion;
	
	private Float porcentaje;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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
