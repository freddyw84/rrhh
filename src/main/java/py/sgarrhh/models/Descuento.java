package py.sgarrhh.entity;

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
@Table(name="rhlq_descuento")
public class Descuento {
	@Id
	@Column(name="deb_num")
	private Integer numero;
	
//	
//	@ManyToOne(cascade={CascadeType.PERSIST})
//	@JoinColumn(name="codigoDescuento")
//	private Collection<TDescuento> tipodescuento;
	
	@ManyToOne(cascade={CascadeType.PERSIST})
	@JoinColumn(name="deb_codtpdes")
	private TipoDescuento tipoDescuento;
	
	@Column(name="deb_monto")
	private Double monto;
	
	@Column(name="deb_fechahora")
	private Date fecha;
	
	@ManyToOne(cascade={CascadeType.PERSIST})
	@JoinColumn(name="deb_codper")
	private Persona codigoPersona;
	
	@Column(name="deb_estado")
	private String estado;
	
	@Column(name="deb_obs")
	private String observacion;
	
		
	public Descuento() {
		super();
		this.numero = 0;
		this.tipoDescuento = new TipoDescuento();
		this.monto = new Double(0);
		this.codigoPersona = new Persona();
		this.estado = "";
		this.fecha = new Date();
		this.observacion = "";
	}


	public Descuento(Integer numero, TipoDescuento codigoTipoDescuento, Double monto,
			Persona codigoPersona, String estado, Date fecha, String observacion) {
		super();
		this.numero = numero;
		this.tipoDescuento = codigoTipoDescuento;
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



	public TipoDescuento getcodigoTipoDescuento() {
		return tipoDescuento;
	}



	public void setcodigoTipoDescuento(TipoDescuento codigoTipoDescuento) {
		this.tipoDescuento = codigoTipoDescuento;
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
		return "Bonificacion [numero=" + numero + ", codigoTipoDescuento=" + tipoDescuento
				+ ", monto=" + monto + ", codigoPersona=" + codigoPersona + ", estado=" + estado + ", fecha=" + fecha
				+ ", observacion=" + observacion + "]";
	}
	
	
	

}
