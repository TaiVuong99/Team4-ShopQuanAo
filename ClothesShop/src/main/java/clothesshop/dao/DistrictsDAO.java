package clothesshop.dao;

import java.util.List;

import clothesshop.model.Districts;

public interface DistrictsDAO {
	public List<Districts> getAllDistricts();
	public List<Districts> getDistrictsById(int id);
}
