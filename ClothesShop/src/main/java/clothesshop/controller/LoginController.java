package clothesshop.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.node.ObjectNode;

import clothesshop.dto.LoginCustomerDTO;
import clothesshop.service.CustomersService;

@Controller
public class LoginController {
	private CustomersService customersService;
	
	@Autowired(required = true)
	@Qualifier(value = "customersService")
	public void setCustomersService(CustomersService customersService) {
		this.customersService = customersService;
	}

	@RequestMapping(value="/requestlogin", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public LoginCustomerDTO requestLogin(@RequestBody ObjectNode objectNode, HttpSession session, 
			HttpServletRequest request, HttpServletResponse response) {
		String username = objectNode.get("username").asText();
		String password = objectNode.get("pass_word").asText();
		String requestsource = objectNode.get("request").asText();
		LoginCustomerDTO checked = customersService.checkCustomer(username, password);
		checked.setRequest(requestsource);
		session = request.getSession();
		if(checked.getStatus()==2) {
			session.setAttribute("id", checked.getId());
			session.setAttribute("username", username);
		}
		else session.invalidate();
		return checked;
	}
	
	@RequestMapping(value="/requestlogout", method = RequestMethod.GET)
	@ResponseBody
	public void requestLogout(HttpSession session, HttpServletRequest request) {
		session = request.getSession();
		session.invalidate();
	}
	
	@RequestMapping(value = "/checkUsername", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String checkUsername(@RequestParam("username") String username) {
		String kq = customersService.checkUsername(username);
		return kq;
	}
	
	@RequestMapping(value = "/requestsignup", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String requestSignUp(@RequestBody ObjectNode objectNode) {
		String username = objectNode.get("username").asText();
		String password = objectNode.get("pass_word").asText();
		String mail = objectNode.get("mail").asText();
		String phone = objectNode.get("phone").asText();
		String kq = customersService.createCustomer(username, password, mail, phone);
		return kq;
	}
}
