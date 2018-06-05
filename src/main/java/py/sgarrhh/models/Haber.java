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

public class Haber implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private long id;
	

	@OneToMany(mappedBy="haber")
	private List<HaberDetalle> haberDetalle;
	
	private Float monto;
	@NotNull(message="Fecha es una informacion necesaria")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)	
	private Date fecha;
	
	
	private String observacion;
	
	@ManyToOne
	private Rubro rubro;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public List<HaberDetalle> getHaberDetalle() {
		return haberDetalle;
	}

	public void setHaberDetalle(List<HaberDetalle> haberDetalle) {
		this.haberDetalle = haberDetalle;
	}

	public Float getMonto() {
		return monto;
	}

	public void setMonto(Float monto) {
		this.monto = monto;
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

	public Rubro getRubro() {
		return rubro;
	}

	public void setRubro(Rubro rubro) {
		this.rubro = rubro;
	}


}
