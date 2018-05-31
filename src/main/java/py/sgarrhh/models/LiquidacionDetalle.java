package py.sgarrhh.models;

import java.io.Serializable;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Entity

public class LiquidacionDetalle implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)

	private Integer id;
	

	@NotEmpty
	@NotNull
	@ManyToOne
	private Liquidacion liquidacion;
	

	@NotEmpty
	@NotNull
	@ManyToOne
	private Concepto concepto;


	@NotEmpty
	@NotNull
	private Double montoParcial;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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

	public Double getMontoParcial() {
		return montoParcial;
	}

	public void setMontoParcial(Double montoParcial) {
		this.montoParcial = montoParcial;
	}
	
	
	
	
}
