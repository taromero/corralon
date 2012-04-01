package ar.com.seminario.corralon.dtos;

public class CorteMaderaDTO {
	private Long idMadMadre;
	private Double precio;
	private Integer cantidadCortadas;
	
	public CorteMaderaDTO(Long idMadMadre, Double precio,
			Integer cantidadCortadas) {
		super();
		this.idMadMadre = idMadMadre;
		this.precio = precio;
		this.cantidadCortadas = cantidadCortadas;
	}
	public Long getIdMadMadre() {
		return idMadMadre;
	}
	public void setIdMadMadre(Long idMadMadre) {
		this.idMadMadre = idMadMadre;
	}
	public Double getPrecio() {
		return precio;
	}
	public void setPrecio(Double precio) {
		this.precio = precio;
	}
	public Integer getCantidadCortadas() {
		return cantidadCortadas;
	}
	public void setCantidadCortadas(Integer cantidadCortadas) {
		this.cantidadCortadas = cantidadCortadas;
	}
	
}
