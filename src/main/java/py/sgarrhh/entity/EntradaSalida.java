package py.sgarrhh.entity;

import java.util.Date;

public class EntradaSalida {
	private Integer codigo;
	private Persona persona;
	private Date fechaEntrada;
	private Date fechaSalida;
	private TipoEntradaSalida tipoEntradaSalida;
	private Date horaEntrada;
	private Date horaSalida;
	public EntradaSalida() {
		super();
		this.codigo = 0;
		this.persona = new Persona();
		this.fechaEntrada = new Date();
		this.fechaSalida = new Date();
		this.tipoEntradaSalida = new TipoEntradaSalida();
		this.horaEntrada = new Date();
		this.horaSalida = new Date();	}
	public EntradaSalida(Integer codigo, Persona persona, Date fechaEntrada, Date fechaSalida,
			TipoEntradaSalida tipoEntradaSalida, Date horaEntrada, Date horaSalida) {
		super();
		this.codigo = codigo;
		this.persona = persona;
		this.fechaEntrada = fechaEntrada;
		this.fechaSalida = fechaSalida;
		this.tipoEntradaSalida = tipoEntradaSalida;
		this.horaEntrada = horaEntrada;
		this.horaSalida = horaSalida;
	}
	public Integer getCodigo() {
		return codigo;
	}
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
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
	@Override
	public String toString() {
		return "EntradaSalida [codigo=" + codigo + ", persona=" + persona + ", fechaEntrada=" + fechaEntrada
				+ ", fechaSalida=" + fechaSalida + ", tipoEntradaSalida=" + tipoEntradaSalida + ", horaEntrada="
				+ horaEntrada + ", horaSalida=" + horaSalida + "]";
	}
	
	
}
