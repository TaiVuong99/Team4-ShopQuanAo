package clothesshop.service;

import clothesshop.dao.CustomersDAO;
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

}
