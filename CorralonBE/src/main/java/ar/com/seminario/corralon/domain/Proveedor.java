package ar.com.seminario.corralon.domain;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import org.codehaus.jackson.annotate.JsonBackReference;

@Entity
public class Proveedor implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6935289707680271834L;

	private Long id_proveedor;
	private String razonSocial;
	private String telefono;
	private String direccion;
	private String email;
	private Set<MaterialProveedor> materialesProveedor;
	private Set<OrdenCompra> ordenesCompra;
	

	public Proveedor(){
		
	}
	
	public Proveedor(String razonSocial, String telefono, String direccion,
			String email) {
		super();
		this.razonSocial = razonSocial;
		this.telefono = telefono;
		this.direccion = direccion;
		this.email = email;
	}
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId_proveedor() {
		return id_proveedor;
	}
	public void setId_proveedor(Long id_proveedor) {
		this.id_proveedor = id_proveedor;
	}
	public String getRazonSocial() {
		return razonSocial;
	}
	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
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
	
	@JsonBackReference("proveedor-materialProveedor")
	@OneToMany(/*fetch = FetchType.EAGER*/)
	@JoinColumn(name = "id_proveedor")
	public Set<MaterialProveedor> getMaterialesProveedor() {
		return materialesProveedor;
	}
	public void setMaterialesProveedor(Set<MaterialProveedor> materialesProveedor) {
		this.materialesProveedor = materialesProveedor;
	}
	
	@JsonBackReference("proveedor-ordenCompra")
	@OneToMany()
	@JoinColumn(name = "id_proveedor")
	public Set<OrdenCompra> getOrdenesCompra() {
		return ordenesCompra;
	}

	public void setOrdenesCompra(Set<OrdenCompra> ordenesCompra) {
		this.ordenesCompra = ordenesCompra;
	}
	
	
}
