package clothesshop.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import clothesshop.model.Products;

public class ProductsDAOImpl implements ProductsDAO{
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Products> getAllProducts() {
		Session session = sessionFactory.openSession();
		Query query = session.createQuery("from Products");
		List<Products> result = query.list();
		session.close();
		return result;
	}

	@Override
	public Products getProductById(int id) {
		Session session = sessionFactory.openSession();
		String hql = "from Products where id =:id";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		Products product = (Products) query.uniqueResult();
		session.close();
		return product;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Products> getListProductsByCate(int id) {
		Session session = sessionFactory.openSession();
		Query query = session.createQuery("from Products where cate_id = :id");
		query.setParameter("id", id);
		List<Products> result = query.list();
		session.close();
		return result;
	}

	@Override
	public List<Products> getListProductsforIndex() {
		Session session = sessionFactory.openSession();
		Query query = session.createQuery("from Products");
		query.setMaxResults(8);
		List<Products> result = query.list();
		session.close();
		return result;
	}
}