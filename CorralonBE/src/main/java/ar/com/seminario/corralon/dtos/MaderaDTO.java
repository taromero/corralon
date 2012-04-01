package ar.com.seminario.corralon.dtos;

public class MaderaDTO {
	private String descripcion;
	private float largo;
	private Integer stock;
	private float sobrante;

	public MaderaDTO(String descripcion, float largo, Integer stock,
			float sobrante) {
		super();
		this.descripcion = descripcion;
		this.largo = largo;
		this.stock = stock;
		this.sobrante = sobrante;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public float getLargo() {
		return largo;
	}
	public void setLargo(float largo) {
		this.largo = largo;
	}
	public Integer getStock() {
		return stock;
	}
	public void setStock(Integer stock) {
		this.stock = stock;
	}
	public float getSobrante() {
		return sobrante;
	}
	public void setSobrante(float sobrante) {
		this.sobrante = sobrante;
	}
}
