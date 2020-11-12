package clothesshop.model;

import java.io.Serializable;

public class CartdetailsPK implements Serializable{
	protected int id;
	protected int product_id;
	protected String description;
	
	public CartdetailsPK(){}
	
	public CartdetailsPK(int id, int product_id, String description){
		this.id = id;
		this.product_id = product_id;
		this.description = description;
	}
}
