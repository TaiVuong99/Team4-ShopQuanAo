package clothesshop.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import clothesshop.model.Districts;

public class DistrictsDAOImpl implements DistrictsDAO{
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Districts> getAllDistricts() {
		Session session = sessionFactory.openSession();
		Query query = session.createQuery("from Districts");
		List<Districts> result = query.list();
		session.close();
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Districts> getDistrictsById(int id) {
		Session session = sessionFactory.openSession();
		Query query = session.createQuery("from Districts where city_id =:id");
		query.setParameter("id", id);
		List<Districts> result = query.list();
		session.close();
		return result;
	}
}