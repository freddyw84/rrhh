package py.sgarrhh.models;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Liquidacion implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private long id;

	@OneToMany(mappedBy="liquidacion")
	private List<LiquidacionDetalle> liquidacionDetalle;

	@ManyToOne
	private Periodo periodo;
	
	@NotNull(message="Fecha es una informacion necesaria")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private Date fecha;
	
	@NotNull
	private Double monto;
	
	private String observacion;
	
	@ManyToOne
	private Persona persona;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public List<LiquidacionDetalle> getLiquidacionDetalle() {
		return liquidacionDetalle;
	}

	public void setLiquidacionDetalle(List<LiquidacionDetalle> liquidacionDetalle) {
		this.liquidacionDetalle = liquidacionDetalle;
	}

	
	public Periodo getPeriodo() {
		return periodo;
	}

	public void setPeriodo(Periodo periodo) {
		this.periodo = periodo;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Double getMonto() {
		return monto;
	}

	public void setMonto(Double monto) {
		this.monto = monto;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}
	
	
}
