package py.sgarrhh.models;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Bonificacion implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private long id;
	
	@NotEmpty
	@NotNull
	private String descripcion;
	
	@OneToMany(mappedBy="bonificacion")
	private List<LiquidacionBonificacion> liquidacionBonificacion;
	
	
	@ManyToOne
	private TipoBonificacion tipoBonificacion;
	
	//@NotEmpty
	@NotNull
	private Double monto;
	
	@ManyToOne
	private Persona persona;
    
	@NotNull
	private String estado;
	
	@ManyToOne
	private Periodo periodo;
	
	@NotNull(message="Fecha es una informacion necesaria")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private Date fecha;
	
	private String observacion;

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

		
	public List<LiquidacionBonificacion> getLiquidacionBonificacion() {
		return liquidacionBonificacion;
	}

	public void setLiquidacionBonificacion(List<LiquidacionBonificacion> liquidacionBonificacion) {
		this.liquidacionBonificacion = liquidacionBonificacion;
	}

	public TipoBonificacion getTipoBonificacion() {
		return tipoBonificacion;
	}

	public void setTipoBonificacion(TipoBonificacion tipoBonificacion) {
		this.tipoBonificacion = tipoBonificacion;
	}

	public Double getMonto() {
		return monto;
	}

	public void setMonto(Double monto) {
		this.monto = monto;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public Periodo getPeriodo() {
		return periodo;
	}

	public void setPeriodo(Periodo periodo) {
		this.periodo = periodo;
	}

	
	
	

}
