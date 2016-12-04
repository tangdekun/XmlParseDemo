package bean;

/**
 * @author John
 * 
 *
 */
public class Book {

	public static final String BOOKS = "books";
	public static final String BOOK = "book";
	public static final String ID ="id";
	public static final String NAME =  "name";
	public static final String PRICE = "price";
	
	private int id;
	private String name;
	private float price;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	
	
}
