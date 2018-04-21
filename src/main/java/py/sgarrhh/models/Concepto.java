package py.sgarrhh.models;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;


@Entity

public class Concepto implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	private Integer codigo;
	
	@OneToMany(mappedBy="concepto", cascade={CascadeType.PERSIST}, orphanRemoval=true)
	private List<LiquidacionDetalle> liquidacionDetalle;
	
	
	@OneToMany(mappedBy="concepto", cascade={CascadeType.PERSIST}, orphanRemoval=true)
	private List<HaberDetalle> haberDetalle;
	
	private String descripcion;

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public List<LiquidacionDetalle> getLiquidaciondetalle() {
		return liquidacionDetalle;
	}

	public void setLiquidacionDetalle(List<LiquidacionDetalle> liquidacionDetalle) {
		this.liquidacionDetalle = liquidacionDetalle;
	}

	public List<HaberDetalle> getHaberDetalle() {
		return haberDetalle;
	}

	public void setHaberDetalle(List<HaberDetalle> haberDetalle) {
		this.haberDetalle = haberDetalle;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	
}
