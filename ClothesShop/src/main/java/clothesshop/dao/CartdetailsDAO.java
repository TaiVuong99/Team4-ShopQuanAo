package clothesshop.dao;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.node.ObjectNode;

import clothesshop.dto.CartdetailsDTO;

public interface CartdetailsDAO {
	public List<Integer> checkStuffexistOrNot(ArrayList<Integer> id_list, int cart_id);
	public String addStuffIntoCartdetails(int product_id, int cart_id, String description, int quantity, int price);
	public List<CartdetailsDTO> getCartdetailsById(int cart_id);
	public String updateCartdetails(List<ObjectNode> list, int cart_id);
	public String updateCartdetailsAfterBill(int cart_id, int tracking);
}
