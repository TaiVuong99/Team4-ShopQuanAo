package clothesshop.service;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.node.ObjectNode;

import clothesshop.dao.CartdetailsDAO;
import clothesshop.dto.CartdetailsDTO;

public class CartdetailsServiceImpl implements CartdetailsService{
	private CartdetailsDAO cartdetailsDAO;
	
	public void setCartdetailsDAO(CartdetailsDAO cartdetailsDAO) {
		this.cartdetailsDAO = cartdetailsDAO;
	}

	@Override
	public List<Integer> checkStuffexistOrNot(ArrayList<Integer> id_list, int cart_id) {
		return cartdetailsDAO.checkStuffexistOrNot(id_list, cart_id);
	}

	@Override
	public String addStuffIntoCartdetails(int product_id, int cart_id, String description, int quantity, int price) {
		return cartdetailsDAO.addStuffIntoCartdetails(product_id, cart_id, description, quantity, price);
	}

	@Override
	public List<CartdetailsDTO> getCartdetailsById(int cart_id) {
		return cartdetailsDAO.getCartdetailsById(cart_id);
	}

	@Override
	public String updateCartdetails(List<ObjectNode> list, int cart_id) {
		return cartdetailsDAO.updateCartdetails(list, cart_id);
	}

	@Override
	public String updateCartdetailsAfterBill(int cart_id, int tracking) {
		return cartdetailsDAO.updateCartdetailsAfterBill(cart_id, tracking);
	}

}
