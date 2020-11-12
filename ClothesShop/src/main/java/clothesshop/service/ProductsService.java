package clothesshop.service;

import java.util.List;

import clothesshop.model.Products;

public interface ProductsService {
	public List<Products> getAllProducts();
	public Products getProductById(int id);
}
