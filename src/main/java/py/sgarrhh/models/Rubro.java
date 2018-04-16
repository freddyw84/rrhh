package py.sgarrhh.entity;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
@Entity
@Table(name="rhco_rubro")
public class Rubro implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name="rub_id")
	private Integer id;
		
	@OneToMany(mappedBy="idRubro", cascade={CascadeType.PERSIST}, orphanRemoval=true)
	private Collection<Haber> haber;
	
	
	@Column(name="rub_des")
	private String descripcion;
	
	public Rubro() {
		super();
		this.id = 0;
		this.descripcion = "";	
		}
	public Rubro(Integer id, String descripcion) {
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
		return "Rubro [id=" + id + ", descripcion=" + descripcion + "]";
	}
	
	
}
