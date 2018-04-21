package py.sgarrhh.models;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Descuento implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer id;
	

	@ManyToOne
	private TipoDescuento tipoDescuento;
	
	private Double monto;
	@Temporal(TemporalType.TIMESTAMP)
	private Date fecha;
	
	@ManyToOne
	private Persona persona;
	
	private String estado;
	
	private String observacion;
	
	@OneToMany(mappedBy="descuento", cascade={CascadeType.PERSIST}, orphanRemoval=true)
	private List<HaberDetalle> haberDetalle;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	

	public TipoDescuento getTipoDescuento() {
		return tipoDescuento;
	}

	public void setTipoDescuento(TipoDescuento tipoDescuento) {
		this.tipoDescuento = tipoDescuento;
	}

	public Double getMonto() {
		return monto;
	}

	public void setMonto(Double monto) {
		this.monto = monto;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
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

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public List<HaberDetalle> getHaberDetalle() {
		return haberDetalle;
	}

	public void setHaberDetalle(List<HaberDetalle> haberDetalle) {
		this.haberDetalle = haberDetalle;
	}
	


}
