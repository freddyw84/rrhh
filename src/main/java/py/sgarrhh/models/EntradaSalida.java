package py.sgarrhh.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
@Entity
public class EntradaSalida implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer id;
	@ManyToOne
	private Persona persona;
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaEntrada;
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaSalida;
	@ManyToOne
	private TipoEntradaSalida tipoEntradaSalida;
	@Temporal(TemporalType.TIMESTAMP)
	private Date horaEntrada;
	@Temporal(TemporalType.TIMESTAMP)
	private Date horaSalida;
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
	public Date getFechaEntrada() {
		return fechaEntrada;
	}
	public void setFechaEntrada(Date fechaEntrada) {
		this.fechaEntrada = fechaEntrada;
	}
	public Date getFechaSalida() {
		return fechaSalida;
	}
	public void setFechaSalida(Date fechaSalida) {
		this.fechaSalida = fechaSalida;
	}
	public TipoEntradaSalida getTipoEntradaSalida() {
		return tipoEntradaSalida;
	}
	public void setTipoEntradaSalida(TipoEntradaSalida tipoEntradaSalida) {
		this.tipoEntradaSalida = tipoEntradaSalida;
	}
	public Date getHoraEntrada() {
		return horaEntrada;
	}
	public void setHoraEntrada(Date horaEntrada) {
		this.horaEntrada = horaEntrada;
	}
	public Date getHoraSalida() {
		return horaSalida;
	}
	public void setHoraSalida(Date horaSalida) {
		this.horaSalida = horaSalida;
	}

	
}
