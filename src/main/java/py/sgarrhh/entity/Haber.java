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
@Table(name="rhlq_haber")
public class Haber implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="hab_id")
	private Integer id;
	

	@OneToMany(mappedBy="idHaber", cascade={CascadeType.PERSIST}, orphanRemoval=true)
	private Collection<HaberDetalle> haberdetalle;
	
	@Column(name="hab_monto")
	private Float monto;
	@Column(name="hab_fec")
	private Date fecha;
	@Column(name="hab_obs")
	private String observacion;
	
	@ManyToOne(cascade={CascadeType.PERSIST})
	@JoinColumn(name="hab_idrubro")
	private Rubro rubro;
	
	public Haber() {
		super();
		this.id = 0;
		this.monto = new Float(0);
		this.fecha = new Date();
		this.observacion = "";
		this.rubro= new Rubro();
	}

	public Haber(Integer numero, Float monto, Date fecha, String observacion, Rubro rubro) {
		super();
		this.id = numero;
		this.monto = monto;
		this.fecha = fecha;
		this.observacion = observacion;
		this.rubro = rubro;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	@Override
	public String toString() {
		return "Haber [id=" + id + ", monto=" + monto + ", fecha="
				+ fecha + ", observacion=" + observacion + ", rubro=" + rubro + "]";
	}



}
