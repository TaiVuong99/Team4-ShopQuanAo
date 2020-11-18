package clothesshop.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import clothesshop.model.Carts;

public class CartsDAOImpl implements CartsDAO{
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public int updateCart(int cart_id) {
		Session session = sessionFactory.openSession();
		int kq = 1;
		Transaction tx = session.beginTransaction();
		int price;
		Carts cart = (Carts) session.get(Carts.class, cart_id);
		Query query = session.createQuery("SELECT SUM(total) FROM Cartdetails WHERE cart_id = :cart_id AND status = 0");
		query.setParameter("cart_id", cart_id);
		Object obj = query.uniqueResult();
		if(obj == null) price = 0;
		else price = Math.toIntExact((long) obj);
		query = session.createQuery("SELECT COUNT(*) FROM Cartdetails WHERE cart_id = :cart_id AND status = 0");
		query.setParameter("cart_id", cart_id);
		int quantum = Math.toIntExact((long) query.uniqueResult());
		cart.setPrice(price);
		cart.setQuantum(quantum);
		try {
			session.save(cart);
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

	@Override
	public int getTotal(int id) {
		Session session = sessionFactory.openSession();
		Query query = session.createQuery("SELECT price FROM Carts WHERE id = :id");
		query.setParameter("id", id);
		int kq = (int) query.uniqueResult();
		return kq;
	}
}
