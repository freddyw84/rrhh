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
public class Bonificacion implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer id;
	
	@OneToMany(mappedBy="bonificacion", cascade={CascadeType.PERSIST}, orphanRemoval=true)
	private List<HaberDetalle> haberDetalle;
	
	@ManyToOne
	private TipoBonificacion tipoBonificacion;
	
	private Double monto;
	
	@ManyToOne
	private Persona Persona;

	private String estado;
	@Temporal(TemporalType.TIMESTAMP)
	private Date fecha;
	
	private String observacion;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<HaberDetalle> getHaberDetalle() {
		return haberDetalle;
	}

	public void setHaberDetalle(List<HaberDetalle> haberDetalle) {
		this.haberDetalle = haberDetalle;
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
		return Persona;
	}

	public void setPersona(Persona persona) {
		Persona = persona;
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
	
		

	
	

}
