package clothesshop.dao;

import java.math.BigInteger;
import java.util.Date;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import clothesshop.model.Bills;

public class BillsDAOImpl implements BillsDAO{
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public int createBill(int id, int city, int district, String address, String note) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		int kq = -1;
		Bills bill = new Bills();
		bill.setCart_id(id);
		bill.setCity_id(city);
		bill.setDistrict_id(district);
		bill.setNote(note);
		bill.setAddress(address);
		bill.setShipfee(0);
		java.sql.Date date = new java.sql.Date(new Date().getTime());
		bill.setTiming(date);
		String hql = "select price from Carts where id = :id";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		int subtotal = (int) query.uniqueResult();
		hql = "select coupon from Customers where id = :id";
		query = session.createQuery(hql);
		query.setParameter("id", id);
		int coupon = (int) query.uniqueResult();
		float tmp = subtotal - (coupon*subtotal/100);
		int total = Math.round(tmp);
		bill.setCoupon(coupon);
		bill.setSubtotal(subtotal);
		bill.setTotal(total);
		try {
			session.save(bill);
			session.flush();
			session.clear();
			tx.commit();
			BigInteger result = (BigInteger) session.createSQLQuery("SELECT LAST_INSERT_ID()").uniqueResult();
			kq = result.intValue();
		}
		catch (Exception e){
			tx.rollback();
		}
		finally {
			session.close();
		}
		return kq;
	}
}