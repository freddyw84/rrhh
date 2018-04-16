package py.sgarrhh.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
@Entity
@Table(name="rhrl_persona")
public class Persona  implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name="per_id")
	private Integer id;
	
	@OneToMany(mappedBy="idPersona", cascade={CascadeType.PERSIST}, orphanRemoval=true)
	private Collection<Liquidacion> liquidacion;
	
	@OneToMany(mappedBy="idPersona", cascade={CascadeType.PERSIST}, orphanRemoval=true)
	private Collection<Bonificacion> bonificacion;
	

//	@OneToMany(mappedBy="ausencia", cascade={CascadeType.PERSIST}, orphanRemoval=true)
//	private Collection<Ausencia> ausencia;
	
	@OneToMany(mappedBy="idPersona", cascade={CascadeType.PERSIST}, orphanRemoval=true)
	private Collection<Contrato> contrato;
	
	@OneToMany(mappedBy="idPersona", cascade={CascadeType.PERSIST}, orphanRemoval=true)
	private Collection<Descuento> descuento;
//	
//	@OneToMany(mappedBy="entradasalida", cascade={CascadeType.PERSIST}, orphanRemoval=true)
//	private Collection<EntradaSalida> entradasalida;
//	
	
	@Column(name="per_docu")
	private String documento;
	@Column(name="per_nom")
	private String nombre;
	@Column(name="per_ape")
	private String apellido;
	@Column(name="per_fecnac")
	private Date fec_nacimiento;
	@Column(name="per_nacion")
	private String nacionalidad;
	@Column(name="per_domi")
	private String domicilio;
	@Column(name="per_ciu")
	private String ciudad;
	@Column(name="per_estciv")
	private String estadoCivil;
	@Column(name="per_sex")
	private String sexo;
	@Column(name="per_tel")
	private String telefono;
	@Column(name="per_email")
	private String email;
	@Column(name="per_obs")
	private String observacion;
	@Column(name="per_est")
	private String estado;	
	//bi-directional many-to-one association to Pedido
	
	@ManyToOne(cascade={CascadeType.PERSIST})
	@JoinColumn(name="per_idtpdocu")
	private TipoDocumento tipoDocumento;
	
	
	@Column(name="per_imagen")
	private byte[] imagen;
	public Persona() {
		super();
		this.id = 0;
		this.documento = "";
		this.nombre = "";
		this.apellido = "";
		this.fec_nacimiento = new Date();
		this.nacionalidad = "";
		this.domicilio = "";
		this.ciudad = "";
		this.estadoCivil ="";
		this.sexo = "";
		this.telefono = "";
		this.email = "";
		this.observacion = "";
		this.estado = "";
		this.tipoDocumento = new TipoDocumento();
		this.imagen=null;
	}
	public Persona(Integer id, String documento, String nombre, String apellido, Date fec_nacimiento,
			String nacionalidad, String domicilio, String ciudad, String estadoCivil, String sexo,
			String telefono, String email, String observacion, String estado, TipoDocumento tipoDocumento,byte[] imagen) {
		super();
		this.id = id;
		this.documento = documento;
		this.nombre = nombre;
		this.apellido = apellido;
		this.fec_nacimiento = fec_nacimiento;
		this.nacionalidad = nacionalidad;
		this.domicilio = domicilio;
		this.ciudad = ciudad;
		this.estadoCivil = estadoCivil;
		this.sexo = sexo;
		this.telefono = telefono;
		this.email = email;
		this.observacion = observacion;
		this.estado = estado;
		this.tipoDocumento = tipoDocumento;
		this.imagen=imagen;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDocumento() {
		return documento;
	}
	public void setDocumento(String documento) {
		this.documento = documento;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public Date getFec_nacimiento() {
		return fec_nacimiento;
	}
	public void setFec_nacimiento(Date fec_nacimiento) {
		this.fec_nacimiento = fec_nacimiento;
	}
	public String getNacionalidad() {
		return nacionalidad;
	}
	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}
	public String getDomicilio() {
		return domicilio;
	}
	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}
	public String getCiudad() {
		return ciudad;
	}
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	public String getEstadoCivil() {
		return estadoCivil;
	}
	public void setEstadoCivil(String estadoCivil) {
		this.estadoCivil = estadoCivil;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getObservacion() {
		return observacion;
	}
	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public TipoDocumento getTipoDocumento() {
		return tipoDocumento;
	}
	public void setTipoDocumento(TipoDocumento tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}
	
	public byte[] getImagen() {
		return imagen;
	}
	public void setImagen(byte[] imagen) {
		this.imagen = imagen;
	}
	@Override
	public String toString() {
		return "Persona [id=" + id + ", documento=" + documento + ", nombre=" + nombre + ", apellido="
				+ apellido + ", fec_nacimiento=" + fec_nacimiento + ", nacionalidad=" + nacionalidad + ", domicilio="
				+ domicilio + ", ciudad=" + ciudad + ", estadoCivil=" + estadoCivil + ", sexo=" + sexo + ", telefono="
				+ telefono + ", email=" + email + ", observacion=" + observacion + ", estado=" + estado
				+ ", tipoDocumento=" + tipoDocumento + "]";
	}
	
	
	
	
}
