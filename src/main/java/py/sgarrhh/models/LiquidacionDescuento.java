package py.sgarrhh.models;

import java.io.Serializable;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;


@Entity

public class LiquidacionDescuento implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private long id;
	
	@NotNull
	@ManyToOne
	private Liquidacion liquidacion;
	
	@NotNull
	@ManyToOne
	private Descuento descuento;
		

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Liquidacion getLiquidacion() {
		return liquidacion;
	}

	public void setLiquidacion(Liquidacion liquidacion) {
		this.liquidacion = liquidacion;
	}

		
	public Descuento getDescuento() {
		return descuento;
	}

	public void setDescuento(Descuento descuento) {
		this.descuento = descuento;
	}

		
	
	
}
