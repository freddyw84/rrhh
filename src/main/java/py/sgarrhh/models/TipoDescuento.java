package py.sgarrhh.entity;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
@Entity
@Table(name="rhco_tipodescuento")
public class TipoDescuento {
	@Id
	@Column(name="tdes_id")
	private Integer id;
	
		
	@OneToMany(mappedBy="tipoDescuento", cascade={CascadeType.PERSIST}, orphanRemoval=true)
	private Collection<Descuento> descuento;
	
	@Column(name="tdes_des")
	private String descripcion;
	
	@Column(name="tdes_porcentaje")
	private Float porcentaje;
	
	public TipoDescuento() {
		super();
		this.id = 0;
		this.descripcion = "";
		this.porcentaje = new Float(0);
	}

	public TipoDescuento(Integer id, String descripcion, Float porcentaje) {
		super();
		this.id = id;
		this.descripcion = descripcion;
		this.porcentaje = porcentaje;
	}

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

	public Float getPorcentaje() {
		return porcentaje;
	}

	public void setPorcentaje(Float porcentaje) {
		this.porcentaje = porcentaje;
	}

	@Override
	public String toString() {
		return "TipoDescuento [id=" + id + ", descripcion=" + descripcion + ", porcentaje=" + porcentaje + "]";
	}

	
	
}
