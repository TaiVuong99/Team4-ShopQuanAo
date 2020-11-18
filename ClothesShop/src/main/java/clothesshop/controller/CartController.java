package clothesshop.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.node.ObjectNode;

import clothesshop.dto.CartdetailsDTO;
import clothesshop.model.Cities;
import clothesshop.model.Districts;
import clothesshop.service.BillsService;
import clothesshop.service.CartdetailsService;
import clothesshop.service.CartsService;
import clothesshop.service.CitiesService;
import clothesshop.service.CustomersService;
import clothesshop.service.DistrictsService;


@Controller
public class CartController {
	private CartsService cartsService;
	private CartdetailsService cartdetailsService;
	private CitiesService citiesService;
	private DistrictsService districtsService;
	private BillsService billsService;
	private CustomersService customersService;
	
	@Autowired(required = true)
	@Qualifier(value = "cartsService")
	public void setCartsService(CartsService cartsService) {
		this.cartsService = cartsService;
	}
	
	@Autowired(required = true)
	@Qualifier(value = "citiesService")
	public void setCitiesService(CitiesService citiesService) {
		this.citiesService = citiesService;
	}
	
	@Autowired(required = true)
	@Qualifier(value = "districtsService")
	public void setDistrictsService(DistrictsService districtsService) {
		this.districtsService = districtsService;
	}
	
	@Autowired(required = true)
	@Qualifier(value = "cartdetailsService")
	public void setCartdetailsService(CartdetailsService cartdetailsService) {
		this.cartdetailsService = cartdetailsService;
	}
	
	@Autowired(required = true)
	@Qualifier(value = "billsService")
	public void setBillsService(BillsService billsService) {
		this.billsService = billsService;
	}
	
	@Autowired(required = true)
	@Qualifier(value = "customersService")
	public void setCustomersService(CustomersService customersService) {
		this.customersService = customersService;
	}
	
	@RequestMapping(value = "/getCartView", method = RequestMethod.GET)
	public String getCartView(Model model, HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		session = request.getSession();
		if(session.getAttribute("id") == null) {
			session.invalidate();
			return "index";
		}
		int cart_id = (int) session.getAttribute("id");
		List<CartdetailsDTO> list = cartdetailsService.getCartdetailsById(cart_id);
		List<Cities> cities = citiesService.getAllCities();
		int total = cartsService.getTotal(cart_id);
		int coupon = customersService.getCustomerById(cart_id).getCoupon();
		model.addAttribute("list",list);
		model.addAttribute("cities",cities);
		model.addAttribute("subtotal",total);
		model.addAttribute("coupon",coupon);
		return "cart";
	}
	
	@RequestMapping(value="/requestCart", method=RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String requestCart(HttpServletRequest request, HttpSession session) {
		session = request.getSession();
		if(session.getAttribute("id") == null) {
			session.invalidate();
			return "Error";
		}
		return "getCartView";
	}
	
	@RequestMapping(value = "/updateCart", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public List<CartdetailsDTO> updateCart(@RequestBody List<ObjectNode> list, HttpServletRequest request, HttpSession session) {
		session = request.getSession();
		int cart_id = (int) session.getAttribute("id");
		String kq = cartdetailsService.updateCartdetails(list, cart_id);
		if(kq == "Success") cartsService.updateCart(cart_id);
		List<CartdetailsDTO> result_list = cartdetailsService.getCartdetailsById(cart_id);
		return result_list;
	}
	
	@RequestMapping(value="/getdistrictsbyid", method=RequestMethod.GET, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public List<Districts> getDistrictsById(@RequestParam("id") int id) {
		List<Districts> list = districtsService.getDistrictsById(id);
		return list;
	}
	
	@RequestMapping(value = "/solvebill", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public int solveBill(@RequestBody ObjectNode obj, HttpServletRequest request, HttpSession session) {
		session = request.getSession();
		int cart_id = (int) session.getAttribute("id");
		int city = Integer.parseInt(obj.get("city").asText());
		int district = Integer.parseInt(obj.get("district").asText());
		int subtotal = Integer.parseInt(obj.get("subtotal").asText());
		String address = obj.get("address").asText();
		String note = obj.get("note").asText();
		int tracking = billsService.createBill(cart_id, city, district, address, note);
		int kq = -1;
		if(tracking != -1) {
			String step2 = cartdetailsService.updateCartdetailsAfterBill(cart_id, tracking);
			if(step2 == "Success") {
				int step3 = cartsService.updateCart(cart_id);
				if(subtotal >= 200 && step3 != -1) {
					kq = customersService.updateCoupon(cart_id);
				}
				else kq = 1;
			}
		}
		return kq;
	}
}