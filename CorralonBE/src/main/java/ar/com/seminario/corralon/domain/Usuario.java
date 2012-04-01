package ar.com.seminario.corralon.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class Usuario implements Serializable{

	/*No me interesa guardar las ordenes de compra ni los presupuestos que genera
	 * son muchas, solo el presupuesto y la orden de ocmpra conocen al usuario,
	 * es unidireccional la relacion*/
	
	private static final long serialVersionUID = -8009555774947998127L;
	public static String ROL_EMPLEADO = "empleado";
	public static String ROL_GERENTE = "gerente";
	public static String ROL_JEFE_COMPRAS = "jefe de compras";
	//completar
	
	private Long id_usuario;
	private String email;
	private String nombre;
	private String apellido;
	private Long dni;//Lo uso para loguear
	private String clave;//Lo uso para loguear
	private String rol;
	private String resourceOwner;
	private String externalId;
	private String calendarId;
	private String calendarIFrame;
	
	private List<Long> idsMaderasPresupuestandose;
	private List<Madera> maderasParaOC;
	
	public Usuario() {
		super();
		this.idsMaderasPresupuestandose = new ArrayList<Long>();
		this.maderasParaOC = new ArrayList<Madera>();
	}
	
	public Usuario(Usuario user){
		this.nombre = user.getNombre();
		this.apellido = user.getApellido();
		this.dni = user.getDni();
		this.clave = user.getClave();
		this.rol = user.getRol();
	}
	
	@Transient
	public List<Long> getIdsMaderasPresupuestandose() {
		return idsMaderasPresupuestandose;
	}

	public void setIdsMaderasPresupuestandose(
			List<Long> idsMaderasPresupuestandose) {
		this.idsMaderasPresupuestandose = idsMaderasPresupuestandose;
	}
	
	@Transient
	public List<Madera> getMaderasParaOC() {
		return maderasParaOC;
	}

	public void setMaderasParaOC(List<Madera> maderasParaOC) {
		this.maderasParaOC = maderasParaOC;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId_usuario() {
		return id_usuario;
	}
	public void setId_usuario(Long id_usuario) {
		this.id_usuario = id_usuario;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getDni() {
		return dni;
	}
	public void setDni(Long dni) {
		this.dni = dni;
	}
	public String getClave() {
		return clave;
	}
	public void setClave(String clave) {
		this.clave = clave;
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
	public String getRol() {
		return rol;
	}
	public void setRol(String rol) {
		this.rol = rol;
	}

	public String getResourceOwner() {
		return resourceOwner;
	}

	public void setResourceOwner(String resourceOwner) {
		this.resourceOwner = resourceOwner;
	}

	public String getExternalId() {
		return externalId;
	}

	public void setExternalId(String externalId) {
		this.externalId = externalId;
	}

	public String getCalendarId() {
		return calendarId;
	}

	public void setCalendarId(String calendarId) {
		this.calendarId = calendarId;
	}

	public String getCalendarIFrame() {
		return calendarIFrame;
	}

	public void setCalendarIFrame(String calendarIFrame) {
		this.calendarIFrame = calendarIFrame;
	}
	
}
