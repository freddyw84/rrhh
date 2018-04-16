package py.sgarrhh.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass

public abstract class General {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Integer id;
	
	public General() {
		
		// TODO Auto-generated constructor stub
				
	}
 

	public General(Integer id) {
		super();
		this.id = id;
	}



	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}


	@Override
	public String toString() {
		return "General [id=" + id + "]";
	}
	
	

}
