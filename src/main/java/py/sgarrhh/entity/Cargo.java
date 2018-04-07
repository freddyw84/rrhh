package py.sgarrhh.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="rhrl_cargo")
public class Cargo {
	
	@Id
	@Column(name="car_cod")
	private Integer codigo;
	
	@Column(name="car_des")
	private String descripcion;

	@ManyToOne(cascade={CascadeType.PERSIST})
	@JoinColumn(name="car_codfun")
	private Funcion codigoFuncion;

	@ManyToOne(cascade={CascadeType.PERSIST})
	@JoinColumn(name="codigoDepartamento")
	private Departamento codigoDepartamento;
	
	
	
	public Cargo() {
		super();
		this.codigo = 0;
		this.descripcion = "";	
		this.codigoFuncion=new Funcion();
		this.codigoDepartamento=new Departamento();
		}
	public Cargo(Integer codigo, String descripcion,Funcion funcion,Departamento departamento) {
		super();
		this.codigo = codigo;
		this.descripcion = descripcion;
		this.codigoFuncion=funcion;
		this.codigoDepartamento=departamento;
	}
	public Integer getCodigo() {
		return codigo;
	}
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	
	public Funcion getFuncion() {
		return codigoFuncion;
	}
	public void setFuncion(Funcion funcion) {
		this.codigoFuncion = funcion;
	}
	public Departamento getDepartamento() {
		return codigoDepartamento;
	}
	public void setDepartamento(Departamento departamento) {
		this.codigoDepartamento = departamento;
	}
	@Override
	public String toString() {
		return "Cargo [codigo=" + codigo + ", descripcion=" + descripcion + "]";
	}
	
	
}
