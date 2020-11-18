package clothesshop.dao;

import java.util.List;

import clothesshop.model.Products;

public interface ProductsDAO {
	public List<Products> getAllProducts();
	public Products getProductById(int id);
	public List<Products> getListProductsByCate(int id);
	public List<Products> getListProductsforIndex();
}
