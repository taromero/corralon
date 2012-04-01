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
	private String nombre;
	private String apellido;
	private Long dni;//Lo uso para loguear
	private String clave;//Lo uso para loguear
	private String rol;
	
	private List<Madera> maderasPresupuestandose;
	private List<Madera> maderasParaOC;
	
	public Usuario() {
		super();
		this.maderasPresupuestandose = new ArrayList<Madera>();
		this.maderasParaOC = new ArrayList<Madera>();
	}
	
	@Transient
	public List<Madera> getMaderasPresupuestandose() {
		return maderasPresupuestandose;
	}
	
	public void setMaderasPresupuestandose(List<Madera> maderasPresupuestandose) {
		this.maderasPresupuestandose = maderasPresupuestandose;
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
	
}
