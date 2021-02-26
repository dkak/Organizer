
public class Product {

	private String title;
	private String price;
	private String quantity;

	
	
	public Product(String title,String quantity,String price) {
		this.title=title;
		this.price=price;
		this.quantity=quantity;
	}
	
	public String getTitle() {
		return title;
	}
	
	
	public String getPrice() {
		return this.price;
	}
	
	public String getQuantity() {
		return this.quantity;
	}
}
