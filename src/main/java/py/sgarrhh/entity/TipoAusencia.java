package py.sgarrhh.entity;

public class TipoAusencia {
	private Integer id;
	private String descripcion;
	public TipoAusencia() {
		super();
		this.id = 0;
		this.descripcion = "";	}
	public TipoAusencia(Integer id, String descripcion) {
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
		return "TipoAusencia [id=" + id + ", descripcion=" + descripcion + "]";
	}
	
}
