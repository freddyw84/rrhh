package py.sgarrhh.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="rhlq_liquidacion_detalle")

public class LiquidacionDetalle implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="lqd_id")
	private Integer id;
	
	
	@ManyToOne(cascade={CascadeType.PERSIST})
	@JoinColumn(name="lqd_idliquidacion")
	private Liquidacion liquidacion;
	
	@ManyToOne(cascade={CascadeType.PERSIST})
	@JoinColumn(name="lqd_idconcepto")
	private Concepto concepto;

	@Column(name="lqd_montoparcial")
	private Double montoParcial;
	
	public LiquidacionDetalle() {
		super();
		this.id =0;
		this.concepto = new Concepto();
		this.liquidacion = new Liquidacion();
		this.montoParcial = new Double(0);
	}
	public LiquidacionDetalle(Integer id, Concepto concepto, Liquidacion liquidacion, Double montoParcial) {
		super();
		this.id = id;
		this.concepto = concepto;
		this.liquidacion = liquidacion;
		this.montoParcial = montoParcial;
		
	}
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Concepto getConcepto() {
		return concepto;
	}
	public void setConcepto(Concepto concepto) {
		this.concepto = concepto;
	}
	public Liquidacion getLiquidacion() {
		return liquidacion;
	}
	public void setLiquidacion(Liquidacion liquidacion) {
		this.liquidacion = liquidacion;
	}
	public Double getMontoParcial() {
		return montoParcial;
	}
	public void setMontoParcial(Double montoParcial) {
		this.montoParcial = montoParcial;
	}
	@Override
	public String toString() {
		return "LiquidacionDetalle [concepto=" + concepto + ", liquidacion=" + liquidacion
				+ ", montoParcial=" + montoParcial + "]";
	}
	
	
	
}
