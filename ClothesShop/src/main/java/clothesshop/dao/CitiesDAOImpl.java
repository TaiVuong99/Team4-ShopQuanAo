package clothesshop.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import clothesshop.model.Cities;

public class CitiesDAOImpl implements CitiesDAO{
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Cities> getAllCities() {
		Session session = sessionFactory.openSession();
		Query query = session.createQuery("from Cities");
		List<Cities> result = query.list();
		session.close();
		return result;
	}
}