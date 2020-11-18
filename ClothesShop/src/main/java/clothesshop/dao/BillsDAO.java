package clothesshop.dao;

public interface BillsDAO {
	public int createBill(int id, int city, int district, String address, String note);
}