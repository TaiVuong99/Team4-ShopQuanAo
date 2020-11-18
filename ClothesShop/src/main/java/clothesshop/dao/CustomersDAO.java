package clothesshop.dao;

import clothesshop.dto.CustomerDTO;
import clothesshop.dto.LoginCustomerDTO;

public interface CustomersDAO {
	public LoginCustomerDTO checkCustomer(String username, String password);
	public CustomerDTO getCustomerById(int id);
	public String checkUsername(String username);
	public String createCustomer(String username, String pw, String mail, String phone);
	public int updateCoupon(int id);
}
