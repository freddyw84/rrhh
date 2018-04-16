package py.sgarrhh.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="rhrl_contrato")
public class Contrato {
	
	@Id
	@Column(name="con_num")
	private Integer numero;
	private String descripcion;
	
	@ManyToOne(cascade={CascadeType.PERSIST})
	@JoinColumn(name="con_codsal")
	private Salario codSalario;
	
	@ManyToOne(cascade={CascadeType.PERSIST})
	@JoinColumn(name="con_codper")
	private Persona codigoPersona;
	
	private Cargo codCargo;
	private Date fechaIngreso;
	
	public Contrato() {
		this.numero = 0;
		this.descripcion = "";
		this.codSalario = new Salario();
		this.codigoPersona = new Persona();
		this.codCargo = new Cargo();
		this.fechaIngreso = new Date();	}

	public Contrato(Integer numero, String descripcion, Salario codSalario, Persona codPersona, Cargo codCargo,
			Date fechaIngreso) {
		super();
		this.numero = numero;
		this.descripcion = descripcion;
		this.codSalario = codSalario;
		this.codigoPersona = codPersona;
		this.codCargo = codCargo;
		this.fechaIngreso = fechaIngreso;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Salario getCodSalario() {
		return codSalario;
	}

	public void setCodSalario(Salario codSalario) {
		this.codSalario = codSalario;
	}

	public Persona getCodPersona() {
		return codigoPersona;
	}

	public void setCodPersona(Persona codPersona) {
		this.codigoPersona = codPersona;
	}

	public Cargo getCodCargo() {
		return codCargo;
	}

	public void setCodCargo(Cargo codCargo) {
		this.codCargo = codCargo;
	}

	public Date getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	@Override
	public String toString() {
		return "Contrato [numero=" + numero + ", descripcion=" + descripcion + ", codPersona=" + codigoPersona
				+ ", codCargo=" + codCargo + ", fechaIngreso=" + fechaIngreso + "]";
	}

}
