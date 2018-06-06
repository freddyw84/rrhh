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

public class Concepto implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	

	
	@OneToMany(mappedBy="concepto")
	private List<LiquidacionDetalle> liquidacionDetalle;
	

	@OneToMany(mappedBy="concepto")
	private List<HaberDetalle> haberDetalle;
	

	@NotEmpty
	@NotNull
	private String descripcion;



	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public List<LiquidacionDetalle> getLiquidacionDetalle() {
		return liquidacionDetalle;
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
