/**
 * 
 */
package py.sgarrhh.entity;

/**
 * @author cvargas
 *
 */
import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="rhlq_haber_detalle")

public class HaberDetalle implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	@Id
	@Column(name="had_id")
	private Integer id;
	
	@ManyToOne(cascade={CascadeType.PERSIST})
	@JoinColumn(name="had_idhaber")
	private Haber haber;
	
	@ManyToOne(cascade={CascadeType.PERSIST})
	@JoinColumn(name="had_idconcep")
	private Concepto concepto;
	
	@ManyToOne(cascade={CascadeType.PERSIST})
	@JoinColumn(name="had_idliq")
	private Liquidacion liquidacion;
	
	@ManyToOne(cascade={CascadeType.PERSIST})
	@JoinColumn(name="had_iddescue")
	private Descuento descuento;
	
	@ManyToOne(cascade={CascadeType.PERSIST})
	@JoinColumn(name="had_idboni")
	private Bonificacion bonificacion;
	
	
	
	public HaberDetalle() {
		// TODO Auto-generated constructor stub
		
		super();
		this.haber = new Haber();
		this.concepto = new Concepto();
		this.liquidacion = new Liquidacion();
		this.descuento = new Descuento() ;
		this.bonificacion = new Bonificacion();
		
	}

	public HaberDetalle(Haber haber, Concepto concepto, Liquidacion liquidacion,
			Descuento descuento, Bonificacion bonificacion) {
		super();
		this.haber = haber;
		this.concepto = concepto;
		this.liquidacion = liquidacion;
		this.descuento = descuento;
		this.bonificacion = bonificacion;
	}

	

	public Haber getHaber() {
		return haber;
	}

	public void setHaber(Haber haber) {
		this.haber = haber;
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

	public Descuento getDescuento() {
		return descuento;
	}

	public void setidDescuento(Descuento descuento) {
		this.descuento = descuento;
	}

	public Bonificacion getBonificacion() {
		return bonificacion;
	}

	public void setBonificacion(Bonificacion bonificacion) {
		this.bonificacion = bonificacion;
	}

	@Override
	public String toString() {
		return "HaberDetalle [id=" + haber + ", concepto=" + concepto + ", liquidacion="
				+ liquidacion + ", descuento=" + descuento + ", bonificacion="
				+ bonificacion + "]";
	}
    
	
	
}
