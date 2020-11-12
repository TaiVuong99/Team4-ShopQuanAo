package clothesshop.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import clothesshop.model.Categories;

public class CategoriesDAOImpl implements CategoriesDAO{
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public List<Categories> getCategories() {
		Session session = sessionFactory.openSession();
		Query query = session.createQuery("from Categories");
		List result = query.list();
		session.close();
		return result;
	}

}
