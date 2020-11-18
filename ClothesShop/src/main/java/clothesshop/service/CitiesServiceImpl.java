package clothesshop.service;

import java.util.List;

import clothesshop.dao.CitiesDAO;
import clothesshop.model.Cities;

public class CitiesServiceImpl implements CitiesService{
	private CitiesDAO citiesDAO;

	public void setCitiesDAO(CitiesDAO citiesDAO) {
		this.citiesDAO = citiesDAO;
	}

	@Override
	public List<Cities> getAllCities() {
		return citiesDAO.getAllCities();
	}
}
