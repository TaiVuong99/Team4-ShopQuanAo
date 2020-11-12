package clothesshop.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="bills")
public class Bills {
	@Id
	@Column(name = "id")
	private int id;
	
	@Column(name = "subtotal")
	private int subtotal;
	
	@Column(name = "total")
	private Date total;
	
	@Column(name= "timing")
	private Date timing;
	
	@Column(name= "address")
	private String address;
	
	@Column(name = "ship_fee")
	private int ship_fee;
	
	@Column(name="coupon")
	private int coupon;
	
	@Column(name="customer_id")
	private int customer_id;
	
	@Column(name = "cart_id")
	private int cart_id;
	
	@Column(name = "city_id")
	private int city_id;
	
	@Column(name = "district_id")
	private int district_id;
	
	@Column(name="status")
	private int status;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(int subtotal) {
		this.subtotal = subtotal;
	}

	public Date getTotal() {
		return total;
	}

	public void setTotal(Date total) {
		this.total = total;
	}

	public Date getTiming() {
		return timing;
	}

	public void setTiming(Date timing) {
		this.timing = timing;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getShip_fee() {
		return ship_fee;
	}

	public void setShip_fee(int ship_fee) {
		this.ship_fee = ship_fee;
	}

	public int getCoupon() {
		return coupon;
	}

	public void setCoupon(int coupon) {
		this.coupon = coupon;
	}

	public int getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}

	public int getCart_id() {
		return cart_id;
	}

	public void setCart_id(int cart_id) {
		this.cart_id = cart_id;
	}

	public int getCity_id() {
		return city_id;
	}

	public void setCity_id(int city_id) {
		this.city_id = city_id;
	}

	public int getDistrict_id() {
		return district_id;
	}

	public void setDistrict_id(int district_id) {
		this.district_id = district_id;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
}
