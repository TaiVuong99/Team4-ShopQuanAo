package clothesshop.service;

import clothesshop.dto.LoginCustomerDTO;

public interface CustomersService {
	public LoginCustomerDTO checkCustomer(String username, String password);
}
