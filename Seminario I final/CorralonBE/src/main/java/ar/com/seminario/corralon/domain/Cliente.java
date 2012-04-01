package ar.com.seminario.corralon.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import org.codehaus.jackson.annotate.JsonBackReference;

@Entity
public class Cliente implements Serializable{

	/*Un cliente conoce los pedidos que realiza*/
	
	private static final long serialVersionUID = -7020622409007188886L;
	
	private Long id_cliente;//bigint en postgres
	private Long dni;
	private String nombre;
	private String apellido;
	private String telefono;
	private String direccion;
	private String email;
	private boolean isRegular;
	private Double descuento;//maximo 1.00 (es gratis)
	private Set<Presupuesto> presupuestos = new HashSet<Presupuesto>();
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId_cliente() {
		return id_cliente;
	}
	
	@SuppressWarnings("unused")
	private void setId_cliente(Long id_cliente) {
		this.id_cliente = id_cliente;
	}
		
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	@JsonBackReference("cliente-presupuesto")
	@OneToMany(fetch = FetchType.EAGER)//que al obtener un cliente, obtenga los presupuestos
	@JoinColumn(name = "id_cliente")
	public Set<Presupuesto> getPresupuestos() {
		return presupuestos;
	}
	public void setPresupuestos(Set<Presupuesto> presupuestos) {
		this.presupuestos = presupuestos;
	}
	
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public boolean getIsRegular() {
		return isRegular;
	}
	public void setIsRegular(boolean isRegular) {
		this.isRegular = isRegular;
	}
	public Double getDescuento() {
		return descuento;
	}

	public void setDescuento(Double descuento) {
		this.descuento = descuento;
	}

	public Long getDni() {
		return dni;
	}

	public void setDni(Long dni) {
		this.dni = dni;
	}
}
