package clothesshop.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import clothesshop.model.Sizes;

public class SizesDAOImpl implements SizesDAO{
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Sizes> getAllSizes() {
		Session session = sessionFactory.openSession();
		Query query = session.createQuery("from Sizes");
		List<Sizes> result = query.list();
		session.close();
		return result;
	}
}