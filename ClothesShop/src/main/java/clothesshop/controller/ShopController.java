package clothesshop.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.node.ObjectNode;

import clothesshop.model.Categories;
import clothesshop.model.Colors;
import clothesshop.model.Products;
import clothesshop.model.Sizes;
import clothesshop.service.CartdetailsService;
import clothesshop.service.CartsService;
import clothesshop.service.CategoriesService;
import clothesshop.service.ColorsService;
import clothesshop.service.ProductsService;
import clothesshop.service.SizesService;

@Controller
public class ShopController {
	private ProductsService productsService;
	private CategoriesService categoriesService;
	private ColorsService colorsService;
	private SizesService sizesService;
	private CartsService cartsService;
	private CartdetailsService cartdetailsService;
	
	@Autowired(required = true)
	@Qualifier(value = "productsService")
	public void setProductsService(ProductsService productsService) {
		this.productsService = productsService;
	}
	
	@Autowired(required = true)
	@Qualifier(value = "categoriesService")
	public void setCategoriesService(CategoriesService categoriesService) {
		this.categoriesService = categoriesService;
	}
	
	@Autowired(required = true)
	@Qualifier(value = "colorsService")
	public void setColorsService(ColorsService colorsService) {
		this.colorsService = colorsService;
	}
	
	@Autowired(required = true)
	@Qualifier(value = "sizesService")
	public void setSizesService(SizesService sizesService) {
		this.sizesService = sizesService;
	}
	
	@Autowired(required = true)
	@Qualifier(value = "cartsService")
	public void setCartsService(CartsService cartsService) {
		this.cartsService = cartsService;
	}
	
	@Autowired(required = true)
	@Qualifier(value = "cartdetailsService")
	public void setCartdetailsService(CartdetailsService cartdetailsService) {
		this.cartdetailsService = cartdetailsService;
	}

	@RequestMapping(value = "/shop", method = RequestMethod.GET)
	public String getShop(Model model, HttpServletRequest request, HttpServletResponse response) {
		List<Products> list = productsService.getAllProducts();
		List<Categories> cates = categoriesService.getCategories();
		List<Colors> colors = colorsService.getAllColors();
		List<Sizes> sizes = sizesService.getAllSizes();
		model.addAttribute("colors",colors);
		model.addAttribute("sizes",sizes);
		model.addAttribute("cates",cates);
		model.addAttribute("list",list);
		return "shop";
	}
	
	@RequestMapping(value = "/getProductById", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Products getProductById(@RequestParam("id") int id) {
		Products product = productsService.getProductById(id);
		return product;
	}
	
	@RequestMapping(value = "/addStuffIntoCart", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String addStuffIntoCart(@RequestBody ObjectNode objectNode, HttpServletRequest request, HttpSession session) {
		session = request.getSession();
		if(session.getAttribute("id") == null) {
			session.invalidate();
			return "Error";
		}
		int cart_id = (int) session.getAttribute("id");
		int product_id = Integer.parseInt(objectNode.get("product_id").asText());
		int price = Integer.parseInt(objectNode.get("price").asText());
		int quantity = Integer.parseInt(objectNode.get("quantity").asText());
		String description = objectNode.get("description").asText();
		String kq = cartdetailsService.addStuffIntoCartdetails(product_id, cart_id, description, quantity, price);
		if(kq=="Success") cartsService.updateCart(cart_id);
		return kq;
	}
	
	@RequestMapping(value = "/checkStuffexistOrNot", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public List<Integer> checkStuffexistOrNot(@RequestBody ArrayList<Integer> id_list, HttpServletRequest request,
			HttpSession session) {
		  session = request.getSession(); 
		  Object object = session.getAttribute("id");
		  if(object == null) { 
			  session.invalidate(); 
			  return null; 
		  }
		  int cart_id = (int) object;
		  List<Integer> kq = cartdetailsService.checkStuffexistOrNot(id_list, cart_id);
		  return kq;
	}
	
	@RequestMapping(value = "/getShopViewByCate", method = RequestMethod.GET)
	public String getShopViewByCate(Model model, HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		session = request.getSession();
		int cate = (int) session.getAttribute("cate");
		List<Products> list = productsService.getListProductsByCate(cate);
		session.removeAttribute("cate");
		if(session.getAttribute("id") == null) {
			session.invalidate();
		}

		List<Categories> cates = categoriesService.getCategories();
		List<Colors> colors = colorsService.getAllColors();
		List<Sizes> sizes = sizesService.getAllSizes();
		model.addAttribute("colors",colors);
		model.addAttribute("sizes",sizes);
		model.addAttribute("cates",cates);
		model.addAttribute("list",list);
		return "shop";
	}
	
	@RequestMapping(value = "/getshopcate", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String getShopByCate(@RequestParam("id") int id, HttpServletRequest request, HttpServletResponse respons, HttpSession session) {
		session.setAttribute("cate", id);
		return "getShopViewByCate";
	}
}