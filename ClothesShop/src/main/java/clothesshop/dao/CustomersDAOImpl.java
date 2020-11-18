package clothesshop.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import clothesshop.dto.CustomerDTO;
import clothesshop.dto.LoginCustomerDTO;
import clothesshop.model.Carts;
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
		LoginCustomerDTO customer_js = new LoginCustomerDTO();
		if(customer!=null) {
			if(customer.getPass_word().equals(password)) {
				hql = "SELECT price FROM Carts C WHERE C.id = :id";
				query = session.createQuery(hql);
				query.setParameter("id",customer.getId());
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
		session.close();
		return customer_js;
	}

	@Override
	public CustomerDTO getCustomerById(int id) {
		Session session = sessionFactory.openSession();
		String hql = "FROM Customers C WHERE C.id = :id";
		Query query = session.createQuery(hql);
		query.setParameter("id",id);
		Customers customer =  (Customers) query.uniqueResult();
		CustomerDTO customer_info = new CustomerDTO();
		customer_info.setCoupon(customer.getCoupon());
		customer_info.setPhone(customer.getPhone());
		customer_info.setMail(customer.getMail());
		session.close();
		return customer_info;
	}
	
	@Override
	public String checkUsername(String username) {
		Session session = sessionFactory.openSession();
		String hql = "FROM Customers C WHERE C.username = :username";
		Query query = session.createQuery(hql);
		query.setParameter("username",username);
		Customers customer =  (Customers) query.uniqueResult();
		session.close();
		if(customer != null) return "Exist";
		return "OK";
	}

	@Override
	public String createCustomer(String username, String pw, String mail, String phone) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		String kq = "Success";
		Customers ct = new Customers();
		Carts cart = new Carts();
		ct.setUsername(username);
		ct.setPass_word(pw);
		ct.setMail(mail);
		ct.setPhone(phone);
		try {
			session.save(ct);
			session.flush();
			session.clear();
			String hql = "SELECT id FROM Customers C WHERE C.username = :username";
			Query query = session.createQuery(hql);
			query.setParameter("username",username);
			int id = (int) query.uniqueResult();
			cart.setId(id);
			session.save(cart);
			session.flush();
			session.clear();
			tx.commit();
		}
		catch (Exception e){
			tx.rollback();
			kq = "Failed";
		}
		finally {
			session.close();
		}
		return kq;
	}

	@Override
	public int updateCoupon(int id) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		int kq;
		Customers cs = (Customers) session.get(Customers.class, id);
		int coupon = cs.getCoupon() + 1;
		kq = coupon;
		cs.setCoupon(coupon);
		try {
			session.update(cs);
			session.flush();
			session.clear();
			tx.commit();
		}
		catch (Exception e){
			tx.rollback();
			kq = -1;
		}
		finally {
			session.close();
		}
		return kq;
	}
}