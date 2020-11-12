package clothesshop.dao;

import java.util.List;

import clothesshop.model.Products;

public interface ProductsDAO {
	public List<Products> getAllProducts();
	public Products getProductById(int id);
}
