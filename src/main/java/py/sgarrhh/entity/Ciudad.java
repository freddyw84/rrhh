package py.sgarrhh.entity;

import java.io.Serializable;

import annotation.Column;
import annotation.DataType;
import annotation.Entity;
import annotation.GeneratedValue;
import annotation.Id;
import annotation.Table;

@Entity
@Table(name="ciudad")

public class Ciudad implements Serializable {
	

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	@Column(name="id",type=DataType.INTEGER,unique=true)
	private Integer id;
	
	
	@Column(name="descripcion",type=DataType.STRING,length=255)
	private String descripcion;
	
	
	public Ciudad() {
	}
	
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
		
}
