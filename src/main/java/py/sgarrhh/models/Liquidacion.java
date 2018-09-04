package py.sgarrhh.models;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
	
	/*@OneToMany(mappedBy="liquidacion")
	private List<LiquidacionBonificacion> liquidacionBonificacion;*/
	
	@OneToMany(mappedBy="liquidacion")
	private List<LiquidacionDescuento> liquidacionDescuento;
	
	
	@ManyToMany//(mappedBy="bonificacion")
	/*@JoinTable( 
	        name = "liquidaciones_bonificaciones", 
	        joinColumns = @JoinColumn(
	          name = "liquidacion_id", referencedColumnName = "id"), 
	        inverseJoinColumns = @JoinColumn(
	          name = "bonificacion_id", referencedColumnName = "id")) */
	private List<Bonificacion> bonificacion;
	


	@ManyToOne
	private Periodo periodo;
	
	@ManyToOne
	private TipoLiquidacion tipoLiquidacion;
	
	@NotNull(message="Fecha es una informacion necesaria")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private Date fecha;
	
	
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

	/*public List<LiquidacionBonificacion> getLiquidacionBonificacion() {
		return liquidacionBonificacion;
	}

	public void setLiquidacionBonificacion(List<LiquidacionBonificacion> liquidacionBonificacion) {
		this.liquidacionBonificacion = liquidacionBonificacion;
	}
*/
	public List<LiquidacionDescuento> getLiquidacionDescuento() {
		return liquidacionDescuento;
	}

	public void setLiquidacionDescuento(List<LiquidacionDescuento> liquidacionDescuento) {
		this.liquidacionDescuento = liquidacionDescuento;
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

	public TipoLiquidacion getTipoLiquidacion() {
		return tipoLiquidacion;
	}

	public void setTipoLiquidacion(TipoLiquidacion tipoLiquidacion) {
		this.tipoLiquidacion = tipoLiquidacion;
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
	
	public List<Bonificacion> getBonificacion() {
		return bonificacion;
	}

	public void setBonificacion(List<Bonificacion> bonificacion) {
		this.bonificacion = bonificacion;
	}


	
}
