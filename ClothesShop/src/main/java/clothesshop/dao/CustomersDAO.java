package clothesshop.dao;

import clothesshop.dto.LoginCustomerDTO;

public interface CustomersDAO {
	public LoginCustomerDTO checkCustomer(String username, String password);
}
