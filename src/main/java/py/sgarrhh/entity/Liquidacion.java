package py.sgarrhh.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
@Entity
@Table(name="rhlq_liquidacion")

public class Liquidacion implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name="liq_id")
	private Integer id;
	
	@OneToMany(mappedBy="idLiquidacion", cascade={CascadeType.PERSIST}, orphanRemoval=true)
	private Collection<LiquidacionDetalle> liquidacionDetalle;
	
	@ManyToOne(cascade={CascadeType.PERSIST})
	@JoinColumn(name="liq_idper")
	private Persona persona;
	@Column(name="liq_fecha")
	private Date fecha;
	@Column(name="liq_monto")
	private Double monto;
	@Column(name="liq_obs")
	private String observacion;
	
	public Liquidacion() {
		super();
		this.id = 0;
		this.persona = new Persona();
		this.fecha = new Date ();
		this.monto = new Double(0);
		this.observacion = "";
	}

	public Liquidacion(Integer id, Date fecha, Double monto,Persona persona, String observacion) {
		super();
		this.id = id;
		this.persona = persona;
		this.fecha = fecha;
		this.monto = monto;
		this.observacion = observacion;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
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



	@Override
	public String toString() {
		return "Liquidacion [id=" + id + ", persona=" + persona + ", fecha=" + fecha + ", monto="
				+ monto + ", observacion=" + observacion + "]";
	}

	
	
	
}
