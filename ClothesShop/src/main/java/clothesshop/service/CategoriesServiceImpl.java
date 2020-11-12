package clothesshop.service;

import java.util.List;

import clothesshop.dao.CategoriesDAO;
import clothesshop.model.Categories;

public class CategoriesServiceImpl implements CategoriesService {
	private CategoriesDAO categoriesDAO;

	public void setCategoriesDAO(CategoriesDAO categoriesDAO) {
		this.categoriesDAO = categoriesDAO;
	}

	@Override
	public List<Categories> getCategories() {
		return categoriesDAO.getCategories();
	}
}
