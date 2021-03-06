package clothesshop.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import clothesshop.model.Colors;

public class ColorsDAOImpl implements ColorsDAO {
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Colors> getAllColors() {
		Session session = sessionFactory.openSession();
		Query query = session.createQuery("from Colors");
		List<Colors> result = query.list();
		session.close();
		return result;
	}
}