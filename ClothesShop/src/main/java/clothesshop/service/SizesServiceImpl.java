package clothesshop.service;

import java.util.List;

import clothesshop.dao.SizesDAO;
import clothesshop.model.Sizes;

public class SizesServiceImpl implements SizesService{
	private SizesDAO sizesDAO;

	public void setSizesDAO(SizesDAO sizesDAO) {
		this.sizesDAO = sizesDAO;
	}

	@Override
	public List<Sizes> getAllSizes() {
		return sizesDAO.getAllSizes();
	}

}
