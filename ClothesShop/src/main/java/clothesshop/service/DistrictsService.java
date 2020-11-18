package clothesshop.service;

import java.util.List;

import clothesshop.model.Districts;

public interface DistrictsService {
	public List<Districts> getAllDistricts();
	public List<Districts> getDistrictsById(int id);
}