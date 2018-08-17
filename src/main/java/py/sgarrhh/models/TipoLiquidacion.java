package py.sgarrhh.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
public class TipoLiquidacion implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private long id;
	@NotEmpty
	@NotNull
	private String descripcion;

	@OneToMany(mappedBy="tipoLiquidacion")
	private List<Liquidacion> liquidacion;
	
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public List<Liquidacion> getLiquidacion() {
		return liquidacion;
	}

	public void setLiquidacion(List<Liquidacion> liquidacion) {
		this.liquidacion = liquidacion;
	}


	
	
	
}
