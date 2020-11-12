package clothesshop.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import clothesshop.dto.LoginCustomerDTO;
import clothesshop.model.Customers;

public class CustomersDAOImpl implements CustomersDAO{
	private SessionFactory sessionFactory;
	
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}


	@Override
	public LoginCustomerDTO checkCustomer(String username, String password) {
		Session session = sessionFactory.openSession();
		String hql = "FROM Customers C WHERE C.username = :username";
		Query query = session.createQuery(hql);
		query.setParameter("username",username);
		Customers customer = (Customers) query.uniqueResult();
		session.close();
		LoginCustomerDTO customer_js = new LoginCustomerDTO();
		if(customer!=null) {
			if(customer.getPass_word().equals(password)) {
				customer_js.setStatus(2);
				customer_js.setResponse("Đăng nhập thành công");
				customer_js.setId(customer.getId());
				customer_js.setUsername(username);
			}
			else {
				customer_js.setStatus(1);
				customer_js.setResponse("Sai mật khẩu");
			}
		}
		else {
			customer_js.setStatus(0);
			customer_js.setResponse("Tài khoản không tồn tại");
		}
		return customer_js;
	}

}
