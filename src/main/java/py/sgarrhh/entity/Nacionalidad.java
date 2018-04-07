package py.sgarrhh.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="rhrl_persona")
public class Nacionalidad {
	
	private Integer id;
	private String descripcion;
	public Nacionalidad() {
		super();
		this.id = 0;
		this.descripcion = "";	
		}
	public Nacionalidad(Integer id, String descripcion) {
		super();
		this.id = id;
		this.descripcion = descripcion;
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
	@Override
	public String toString() {
		return "Nacionalidad [id=" + id + ", descripcion=" + descripcion + "]";
	}
	
	
}
