package py.sgarrhh.entity;

import java.util.Date;

public class Ausencia {
	
	private Integer id;
	private Persona idPersona;
	private Date fechaInicio;
	private Date fechaFin;
    private String descripcion;
    private TipoAusencia idtipoAusencia;
	
    
    public Ausencia() {
		super();
		this.id = 0;
		this.idPersona = new Persona();
		this.fechaInicio = new Date();
		this.fechaFin = new Date();
		this.descripcion = "";
		this.idtipoAusencia = new TipoAusencia();	}


	public Ausencia(Integer codigo, Persona codPersona, Date fechaInicio, Date fechaFin, String descripcion,
			TipoAusencia tipoAusencia) {
		super();
		this.id = codigo;
		this.idPersona = codPersona;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.descripcion = descripcion;
		this.idtipoAusencia = tipoAusencia;
	}


	public Integer getCodigo() {
		return id;
	}


	public void setCodigo(Integer codigo) {
		this.id = codigo;
	}


	public Persona getCodPersona() {
		return idPersona;
	}


	public void setCodPersona(Persona codPersona) {
		this.idPersona = codPersona;
	}


	public Date getFechaInicio() {
		return fechaInicio;
	}


	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}


	public Date getFechaFin() {
		return fechaFin;
	}


	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}


	public String getDescripcion() {
		return descripcion;
	}


	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	public TipoAusencia getTipoAusencia() {
		return idtipoAusencia;
	}


	public void setTipoAusencia(TipoAusencia tipoAusencia) {
		this.idtipoAusencia = tipoAusencia;
	}


	@Override
	public String toString() {
		return "Ausencia [codigo=" + id + ", codPersona=" + idPersona + ", fechaInicio=" + fechaInicio
				+ ", fechaFin=" + fechaFin + ", descripcion=" + descripcion + ", tipoAusencia=" + idtipoAusencia + "]";
	}
    
	
	
	
    
    

}
