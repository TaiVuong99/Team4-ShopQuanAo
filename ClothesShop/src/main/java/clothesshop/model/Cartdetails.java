package clothesshop.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name="cartdetails")
@IdClass(CartdetailsPK.class)
public class Cartdetails {
	@Id
	@Column(name="id")
	private int id;
	
	@Id
	@Column(name="product_id")
	private int product_id;
	
	@Id
	@Column(name="description")
	private String description;
	
	@Column(name="cart_id")
	private int cart_id;
	
	@Column(name="bill_id")
	private int bill_id;
	
	@Column(name="amount")
	private int amount;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getProduct_id() {
		return product_id;
	}

	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getCart_id() {
		return cart_id;
	}

	public void setCart_id(int cart_id) {
		this.cart_id = cart_id;
	}

	public int getBill_id() {
		return bill_id;
	}

	public void setBill_id(int bill_id) {
		this.bill_id = bill_id;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}
}
