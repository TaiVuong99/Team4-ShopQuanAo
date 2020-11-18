package clothesshop.service;

import clothesshop.dao.BillsDAO;

public class BillsServiceImpl implements BillsService{
	private BillsDAO billsDAO;

	public void setBillsDAO(BillsDAO billsDAO) {
		this.billsDAO = billsDAO;
	}

	@Override
	public int createBill(int id, int city, int district, String address, String note) {
		return billsDAO.createBill(id, city, district, address, note);
	}
}