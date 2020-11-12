package clothesshop.service;

import java.util.List;

import clothesshop.dao.ColorsDAO;
import clothesshop.model.Colors;

public class ColorsServiceImpl implements ColorsService {
	private ColorsDAO colorsDAO;
	
	public void setColorsDAO(ColorsDAO colorsDAO) {
		this.colorsDAO = colorsDAO;
	}

	@Override
	public List<Colors> getAllColors() {
		return colorsDAO.getAllColors();
	}

}
