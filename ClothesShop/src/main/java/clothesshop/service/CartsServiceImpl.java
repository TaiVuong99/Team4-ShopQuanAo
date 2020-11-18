package clothesshop.service;

import clothesshop.dao.CartsDAO;

public class CartsServiceImpl implements CartsService{
	private CartsDAO cartsDAO;

	public void setCartsDAO(CartsDAO cartsDAO) {
		this.cartsDAO = cartsDAO;
	}

	@Override
	public int updateCart(int cart_id) {
		return cartsDAO.updateCart(cart_id);
	}

	@Override
	public int getTotal(int id) {
		return cartsDAO.getTotal(id);
	}
}
