package ar.com.seminario.corralon.dtos;

public class MaterialSinUnidadDTO extends MaterialDTO{
	private Integer stock;
	
	public MaterialSinUnidadDTO(){}

	public MaterialSinUnidadDTO(Integer stock) {
		super();
		this.stock = stock;
	}
	
	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}
}
