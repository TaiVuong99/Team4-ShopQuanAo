package clothesshop.service;

import java.util.List;

import clothesshop.dao.ProductsDAO;
import clothesshop.model.Products;

public class ProductsServiceImpl implements ProductsService{
	private ProductsDAO productsDAO;
	
	public void setProductsDAO(ProductsDAO productsDAO) {
		this.productsDAO = productsDAO;
	}

	@Override
	public List<Products> getAllProducts() {
		return productsDAO.getAllProducts();
	}

	@Override
	public Products getProductById(int id) {
		return productsDAO.getProductById(id);
	}

}
