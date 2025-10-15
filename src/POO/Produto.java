package POO;

public class Produto {
	private String product;
	private Double price;
	private Integer estoque;

	public Produto(String product, Double price, Integer estoque) {
		this.product = product;
		this.price = price;
		this.estoque = estoque;

	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {

		if (price < 0) {
			System.out.println("Preço inválido.");
		} 
		else {
			this.price = price;
		}
	}

	public Integer getEstoque() {
		return estoque;
	}

	public void setEstoque(Integer estoque) {

		if (estoque < 0) {
			System.out.println("Número de itens inválidos.");
		} 
		else {
			this.estoque = estoque;
		}
	}

	public String toString() {
		return ("Nome: " + this.product + "\nPreço: R$" + this.price + "\nEstoque: " + this.estoque);
	}
}
