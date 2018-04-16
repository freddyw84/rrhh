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
@Table(name="rhlq_bonificacion")
public class Bonificacion implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="bod_num")
	@OneToMany(mappedBy="HaberDetalle", cascade={CascadeType.PERSIST}, orphanRemoval=true)
	private Integer numero;
	
	@OneToMany(mappedBy="codigoBonificacion", cascade={CascadeType.PERSIST}, orphanRemoval=true)
	private Collection<HaberDetalle> Haberdetalle;
	
	@ManyToOne(cascade={CascadeType.PERSIST})
	@JoinColumn(name="bod_codtpbon")
	private TipoBonificacion codigoTipoBonificacion;
	
	@Column(name="bod_monto")
	private Double monto;
	
	@ManyToOne(cascade={CascadeType.PERSIST})
	@JoinColumn(name="bod_codper")
	private Persona codigoPersona;

	@Column(name="bod_estado")
	private String estado;
	
	@Column(name="bod_fechahora")
	private Date fecha;
	
	@Column(name="bod_obs")
	private String observacion;
	
		
	public Bonificacion() {
		super();
		this.numero = 0;
		this.codigoTipoBonificacion = new TipoBonificacion();
		this.monto = new Double(0);
		this.codigoPersona = new Persona();
		this.estado = "";
		this.fecha = new Date();
		this.observacion = "";
	}



	public Bonificacion(Integer numero, TipoBonificacion codigoTipoBonificacion, Double monto,
			Persona codigoPersona, String estado, Date fecha, String observacion) {
		super();
		this.numero = numero;
		this.codigoTipoBonificacion = codigoTipoBonificacion;
		this.monto = monto;
		this.codigoPersona = codigoPersona;
		this.estado = estado;
		this.fecha = fecha;
		this.observacion = observacion;
	}



	public Integer getCodigo() {
		return numero;
	}



	public void setCodigo(Integer codigo) {
		this.numero = codigo;
	}



	public TipoBonificacion getCodigoTipoBonificacion() {
		return codigoTipoBonificacion;
	}



	public void setCodigoTipoBonificacion(TipoBonificacion codigoTipoBonificacion) {
		this.codigoTipoBonificacion = codigoTipoBonificacion;
	}



	public Double getMonto() {
		return monto;
	}



	public void setMonto(Double monto) {
		this.monto = monto;
	}



	public Persona getCodigoPersona() {
		return codigoPersona;
	}



	public void setCodigoPersona(Persona codigoPersona) {
		this.codigoPersona = codigoPersona;
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



	@Override
	public String toString() {
		return "Bonificacion [numero=" + numero + ", codigoTipoBonificacion=" + codigoTipoBonificacion
				+ ", monto=" + monto + ", codigoPersona=" + codigoPersona + ", estado=" + estado + ", fecha=" + fecha
				+ ", observacion=" + observacion + "]";
	}
	
	
	

}
