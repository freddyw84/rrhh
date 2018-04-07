package py.sgarrhh.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="rhco_tipodocumento")
public class TipoDocumento {
	@Id
	@Column(name="tdo_id")
	//bi-directional many-to-one association to Pedido
	
	@OneToMany(mappedBy="persona", cascade={CascadeType.PERSIST}, orphanRemoval=true)
	private Integer id;
	
	@Column(name="tdo_des")
	private String descripcion;
	
	public TipoDocumento() {
		super();
		this.id = 0;
		this.descripcion = "";	
		}
	public TipoDocumento(Integer id) {
		super();
		this.id = id;
		this.descripcion="";
	}
	public TipoDocumento(Integer id, String descripcion) {
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
		return "TipoDocumento [id=" + id + ", descripcion=" + descripcion + "]";
	}
	
	
}
