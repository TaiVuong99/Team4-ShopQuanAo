package clothesshop.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import clothesshop.model.Categories;
import clothesshop.model.Colors;
import clothesshop.model.Products;
import clothesshop.model.Sizes;
import clothesshop.service.CategoriesService;
import clothesshop.service.ColorsService;
import clothesshop.service.ProductsService;
import clothesshop.service.SizesService;

@Controller
public class HomeController {
	private ProductsService productsService;
	private CategoriesService categoriesService;
	private ColorsService colorsService;
	private SizesService sizesService;
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
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
	
	@RequestMapping(value = {"", "/", "home"}, method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		String formattedDate = dateFormat.format(date);
		model.addAttribute("serverTime", formattedDate );
		List<Products> list = productsService.getListProductsforIndex();
		List<Categories> cates = categoriesService.getCategories();
		List<Colors> colors = colorsService.getAllColors();
		List<Sizes> sizes = sizesService.getAllSizes();
		model.addAttribute("colors",colors);
		model.addAttribute("sizes",sizes);
		model.addAttribute("cates",cates);
		model.addAttribute("list",list);
		return "index";
	}
}