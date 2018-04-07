package py.sgarrhh.entity;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
@Entity
@Table(name="rhco_salario")
public class Salario {
	
	@Id
	@Column(name="sal_id")
	private Integer id;
	
	@OneToMany(mappedBy="idSalario", cascade={CascadeType.PERSIST}, orphanRemoval=true)
	private Collection<Contrato> contrato;
		
	@Column(name="sal_des")
	private String descripcion;
	
	@Column(name="sal_monto")
	private Double monto;
	
	public Salario() {
		super();
		this.id = 0;
		this.descripcion = "";	
		this.monto = new Double(0);	
		}
	
	public Salario(Integer id, String descripcion, Double monto) {
		super();
		this.id = id;
		this.descripcion = descripcion;
		this.monto= monto;
	}

		
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Double getMonto() {
		return monto;
	}
	public void setMonto(Double monto) {
		this.monto = monto;
	}
	@Override
	public String toString() {
		return "Salario [id=" + id + ", descripcion=" + descripcion + ", monto=" + monto + "]";
	}
	
	
}
