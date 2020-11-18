package clothesshop.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import clothesshop.dto.CustomerDTO;
import clothesshop.service.CustomersService;

@Controller
public class AccountController {
	private CustomersService customersService;
	
	@Autowired(required = true)
	@Qualifier(value = "customersService")
	public void setCustomersService(CustomersService customersService) {
		this.customersService = customersService;
	}
	
	@RequestMapping(value="/getAccountView", method=RequestMethod.GET)
	public String getAccountView(Model model, HttpServletRequest request, HttpServletResponse response, HttpSession session) {
//		response.setHeader("Access-Control-Allow-Origin", "*");
//		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
//		response.setHeader("Pragma", "No-cache"); // HTTP 1.0.
//		response.setHeader("Expires", "0"); // Proxies.
		session = request.getSession();
		if(session.getAttribute("id") == null) {
			session.invalidate();
			return "index";
		}
		int id = (int) session.getAttribute("id");
		CustomerDTO customer_info = customersService.getCustomerById(id);
		model.addAttribute("coupon", customer_info.getCoupon());
		model.addAttribute("phone", customer_info.getPhone());
		model.addAttribute("mail", customer_info.getMail());
		return "account";
	}
	
	@RequestMapping(value="/requestAccount", method=RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String requestAccount(HttpServletRequest request, HttpSession session) {
		session = request.getSession();
		if(session.getAttribute("id") == null) {
			session.invalidate();
			return "Error";
		}
		return "getAccountView";
	}
}
