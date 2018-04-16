package py.sgarrhh.entity;

public class EstadoCivil {
	private Integer codigo;
	private String descripcion;
	public EstadoCivil() {
		super();
		this.codigo = 0;
		this.descripcion = "";	
		}
	public EstadoCivil(Integer codigo, String descripcion) {
		super();
		this.codigo = codigo;
		this.descripcion = descripcion;
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
	@Override
	public String toString() {
		return "EstadoCivil [codigo=" + codigo + ", descripcion=" + descripcion + "]";
	}
	
	
}
