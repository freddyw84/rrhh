package py.sgarrhh.models;

import java.io.Serializable;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;


@Entity

public class LiquidacionBonificacion implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private long id;
	
	@NotNull
	@ManyToOne
	private Liquidacion liquidacion;
	
	@NotNull
	@ManyToOne
	private Concepto concepto;
	
	@NotNull
	@ManyToOne
	private Bonificacion bonificacion;
	
	//@NotEmpty
	@NotNull
	private Double monto;

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

	
	public Concepto getConcepto() {
		return concepto;
	}

	public void setConcepto(Concepto concepto) {
		this.concepto = concepto;
	}

	

	public Double getMonto() {
		return monto;
	}

	public void setMonto(Double monto) {
		this.monto = monto;
	}

	public Bonificacion getBonificacion() {
		return bonificacion;
	}

	public void setBonificacion(Bonificacion bonificacion) {
		this.bonificacion = bonificacion;
	}
	
	
}
