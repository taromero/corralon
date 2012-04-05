package ar.com.seminario.corralon.dtos;

public class MaterialDTO {
	private Long id_material;
	private String descripcion;
	
	public MaterialDTO(){}
	
	public MaterialDTO(Long id_material, String descripcion, Double precioVenta) {
		super();
		this.id_material = id_material;
		this.descripcion = descripcion;
		this.precioVenta = precioVenta;
	}
	
	public Long getId_material() {
		return id_material;
	}
	public void setId_material(Long id_material) {
		this.id_material = id_material;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Double getPrecioVenta() {
		return precioVenta;
	}
	public void setPrecioVenta(Double precioVenta) {
		this.precioVenta = precioVenta;
	}
	private Double precioVenta;
}
