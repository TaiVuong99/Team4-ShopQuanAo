package clothesshop.service;

import clothesshop.dao.CustomersDAO;
import clothesshop.dto.CustomerDTO;
import clothesshop.dto.LoginCustomerDTO;

public class CustomersServiceImpl implements CustomersService{
	private CustomersDAO customersDAO;
	
	public void setCustomersDAO(CustomersDAO customersDAO) {
		this.customersDAO = customersDAO;
	}

	@Override
	public LoginCustomerDTO checkCustomer(String username, String password) {
		return customersDAO.checkCustomer(username, password);
	}

	@Override
	public CustomerDTO getCustomerById(int id) {
		return customersDAO.getCustomerById(id);
	}

	@Override
	public String checkUsername(String username) {
		return customersDAO.checkUsername(username);
	}

	@Override
	public String createCustomer(String username, String pw, String mail, String phone) {
		return customersDAO.createCustomer(username, pw, mail, phone);
	}

	@Override
	public int updateCoupon(int id) {
		return customersDAO.updateCoupon(id);
	}

}
