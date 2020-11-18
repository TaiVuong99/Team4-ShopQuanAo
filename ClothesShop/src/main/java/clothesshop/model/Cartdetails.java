package clothesshop.model;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="cartdetails")
public class Cartdetails {
	@Id
	@Column(name="id")
	private int id;
	
	@Column(name="cart_id")
	private int cart_id;
	
	@Column(name="product_id")
	private int product_id;
	
//	@JoinColumn(name = "products_id")
//	@ManyToOne(targetEntity = Products.class, fetch = FetchType.LAZY)
//	private Products products;

	@Column(name="status")
	private int status;
	
	@Column(name="description")
	private String description;
	
	@Column(name="quantity")
	private int quantity;
	
	@Column(name="tracking")
	private int tracking;

	@Column(name="total")
	private int total;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCart_id() {
		return cart_id;
	}

	public void setCart_id(int cart_id) {
		this.cart_id = cart_id;
	}

	public int getProduct_id() {
		return product_id;
	}

	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getTracking() {
		return tracking;
	}

	public void setTracking(int tracking) {
		this.tracking = tracking;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}
}
