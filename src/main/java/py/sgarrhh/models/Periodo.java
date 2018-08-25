package py.sgarrhh.models;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;


@Entity
public class Periodo  implements Serializable {
	
	public List<Bonificacion> getBonificacion() {
		return bonificacion;
	}

	public void setBonificacion(List<Bonificacion> bonificacion) {
		this.bonificacion = bonificacion;
	}

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private long id;
	
	@NotEmpty
	@NotNull
	private String descripcion;
	
		
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private Date fechaInicio;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private Date fechaFin;

	
	@OneToMany(mappedBy="periodo") //, cascade={CascadeType.PERSIST}, orphanRemoval=true)
	private List<Liquidacion> liquidacion;
	
	@OneToMany(mappedBy="periodo") //, cascade={CascadeType.PERSIST}, orphanRemoval=true)
	private List<Bonificacion> bonificacion;
	
	
	@OneToMany(mappedBy="periodo") //, cascade={CascadeType.PERSIST}, orphanRemoval=true)
	private List<Descuento> descuento;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public List<Liquidacion> getLiquidacion() {
		return liquidacion;
	}

	public void setLiquidacion(List<Liquidacion> liquidacion) {
		this.liquidacion = liquidacion;
	}
	
	
}
