package clothesshop.service;

import java.util.List;

import clothesshop.dao.DistrictsDAO;
import clothesshop.model.Districts;

public class DistrictsServiceImpl implements DistrictsService{
	private DistrictsDAO districtsDAO;

	public void setDistrictsDAO(DistrictsDAO districtsDAO) {
		this.districtsDAO = districtsDAO;
	}

	@Override
	public List<Districts> getAllDistricts() {
		return districtsDAO.getAllDistricts();
	}

	@Override
	public List<Districts> getDistrictsById(int id) {
		return districtsDAO.getDistrictsById(id);
	}
}