package clothesshop.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.fasterxml.jackson.databind.node.ObjectNode;

import clothesshop.dto.CartdetailsDTO;
import clothesshop.model.Cartdetails;
import clothesshop.model.Products;

public class CartdetailsDAOImpl implements CartdetailsDAO{
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Integer> checkStuffexistOrNot(ArrayList<Integer> id_list, int cart_id) {
		Session session = sessionFactory.openSession();
		String hql = "SELECT product_id FROM Cartdetails WHERE product_id IN (";
		//int[] result = Arrays.stream(id_list.split(",")).mapToInt(Integer::parseInt).toArray();
		int size = id_list.size() - 1;
		for(int i = 0; i < size; i++) hql = hql + id_list.get(i) + ", ";
		hql = hql + id_list.get(size) + ") AND cart_id = :cart_id AND status = 0";
		Query query = session.createQuery(hql);
		query.setParameter("cart_id",cart_id);
		List<Integer> list = query.list();
		session.close();
		return list;
	}

	@Override
	public String addStuffIntoCartdetails(int product_id, int cart_id, String description, int quantity, int price) {
		String kq="Success";
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		String hql = "FROM Cartdetails WHERE product_id = :product_id AND cart_id = :cart_id"
				+ " AND status = 0 AND description = :description";
		Query query = session.createQuery(hql);
		query.setParameter("product_id",product_id);
		query.setParameter("cart_id",cart_id);
		query.setParameter("description",description);
		Cartdetails instance = (Cartdetails) query.uniqueResult();
		if(instance == null) {
			instance = new Cartdetails();
			instance.setCart_id(cart_id);
			instance.setProduct_id(product_id);
			instance.setDescription(description);
			instance.setQuantity(quantity);
			instance.setTotal(price*quantity);
			try {
				session.save(instance);
				session.flush();
				session.clear();
				tx.commit();
			}
			catch (Exception e) {
				tx.rollback();
				kq = "FailtoAdd";
			}
			finally {
				session.close();
			}
		}
		else {
			
			Cartdetails cartdetail = (Cartdetails) session.get(Cartdetails.class,instance.getId());
			quantity = cartdetail.getQuantity() + quantity;
			cartdetail.setQuantity(quantity);
			cartdetail.setTotal(price * quantity);
			try {
				session.update(cartdetail);
				session.flush();
				session.clear();
				tx.commit();
			}
			catch (Exception e) {
				tx.rollback();
				kq = "FailtoAdd";
			}
			finally {
				session.close();
			}
		}
		return kq;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CartdetailsDTO> getCartdetailsById(int cart_id) {
		Session session = sessionFactory.openSession();
		String hql = "from Cartdetails  WHERE cart_id = :cart_id AND status = 0";
		Query query = session.createQuery(hql);
		query.setParameter("cart_id", cart_id);
		List<Cartdetails> lista = query.list();
		List<CartdetailsDTO> kq = new ArrayList<>();
		CartdetailsDTO tmp = new CartdetailsDTO();
		Products pd = new Products();
		int size = lista.size(),id;
		hql = "FROM Products where id =:id";
		for(int i = 0 ;i < size; i++) {
			id = lista.get(i).getProduct_id();
			query = session.createQuery(hql);
			query.setParameter("id", id);
			pd = (Products) query.uniqueResult();
			tmp.setProduct_id(id);
			tmp.setDescription(lista.get(i).getDescription());
			tmp.setQuantity(lista.get(i).getQuantity());
			tmp.setTotal(lista.get(i).getTotal());
			tmp.setPrice(pd.getPrice());
			tmp.setImage(pd.getImage());
			tmp.setName(pd.getProduct_name());
			kq.add(tmp);
			tmp = new CartdetailsDTO();
		}
		session.close();
		return kq;
	}

	@Override
	public String updateCartdetails(List<ObjectNode> list, int cart_id) {
		String kq="Success";
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		Cartdetails cds = new Cartdetails();
		String hql = "from Cartdetails  WHERE cart_id = :cart_id AND status = 0 "
				+ "AND product_id = :product_id AND description = :description";
		Query query = session.createQuery(hql);
		int size = list.size();
		int product_id,quantity,total;
		String description;
		try {
			for(int i = 0; i < size ; i++) {
				product_id = Integer.parseInt(list.get(i).get("id").asText());
				description = list.get(i).get("description").asText();
				quantity = Integer.parseInt(list.get(i).get("quantity").asText());
				query.setParameter("product_id",product_id);
				query.setParameter("cart_id",cart_id);
				query.setParameter("description",description);
				Cartdetails instance = (Cartdetails) query.uniqueResult();
				cds = (Cartdetails) session.get(Cartdetails.class,instance.getId());
				if(cds!=null) {
					if(quantity==0) {
						session.delete(cds);
						session.flush();
						session.clear();
					}
					else {
						total = Integer.parseInt(list.get(i).get("total").asText());
						cds.setQuantity(quantity);
						cds.setTotal(total);
						session.update(cds);
						session.flush();
						session.clear();
					}
				}
				if(!tx.wasCommitted()) tx.commit();
			}
		}
		catch (Exception e) {
			tx.rollback();
			kq = "Failed";
		}
		finally {
			session.close();
		}
		return kq;
	}

	@Override
	public String updateCartdetailsAfterBill(int cart_id, int tracking) {
		String kq="Success";
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		String hql = "UPDATE Cartdetails set status = 1, tracking = :tracking "  + 
	             "WHERE cart_id = :cart_id AND status = 0";
		Query query = session.createQuery(hql);
		query.setParameter("tracking", tracking);
		query.setParameter("cart_id", cart_id);
		try {
			query.executeUpdate();
			tx.commit();
		}
		catch (Exception e) {
			System.out.println(e);
			tx.rollback();
			kq = "Failed";
		}
		finally {
			session.close();
		}
		return kq;
	}
}