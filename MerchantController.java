package com.capgemini.adminstore.controllers;

import java.util.List;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


import com.capgemini.adminstore.beans.Merchant;
import com.capgemini.adminstore.beans.Product;

import com.capgemini.adminstore.exceptions.ProductNotFoundException;
import com.capgemini.adminstore.services.MerchantService;


@Controller
@RequestMapping("/Merchant")
public class MerchantController {
	
	
	@Autowired
	private MerchantService merchantservice;
	
	@GetMapping("/")
	public ModelAndView homePage()
	{
		ModelAndView mv = new ModelAndView("merchantHome"); 
		return mv;
		
	}
	
	@GetMapping("/AddingProduct")
	/*To create an object for creating an account */
	public ModelAndView AddProduct()
	{
		return new ModelAndView("addproduct", "product", new Product());
	}
	
	@PostMapping("/AddingProduct")
	/*To call the method for adding an account to the table*/
	public ModelAndView saveProduct(@Valid @ModelAttribute("product")Product product)
	{
		ModelAndView mv=new ModelAndView("productAdded");
		mv.addObject(merchantservice.addNewProduct(product));
		return mv;
	}
	
	@GetMapping("/RemoveProduct")
	/*To create an object for deleting account*/
	public ModelAndView removeProduct()
	{
		Product product = new Product();
		ModelAndView mv=new ModelAndView("removeproduct","product",product);
		return mv;
	}
	
	@PostMapping("/RemoveProduct")
	/*To call the method for deleting an account*/
	public ModelAndView deleteProduct(@RequestParam int no) throws ProductNotFoundException
	{
		String message=merchantservice.deleteProduct(no);
		if(message.equals("deleted product"))
			message="Account No : "+no+"Deleted Successfully";
		else
			message="Account No : "+no+" has not been deleted";
		ModelAndView mv=new ModelAndView("earful");
		return mv;
	}

	@GetMapping("/allproducts")
	public ModelAndView getAllProducts()
	{
		Merchant merchant = new Merchant();
		ModelAndView mv=new ModelAndView("merchantIdForProducts","merchant",merchant);
		return mv;
	}
	
	  @PostMapping("/allproducts")
	    /*Method used to call the method for printing all the transactions*/
	    public ModelAndView getAllProductsByMerchantId(@PathVariable int no)
	    {
	    	ModelAndView mv=new ModelAndView("printTransaction");
	    	Merchant merchant = merchantservice.findById(no);
	    	List<Product> pList = merchant.getProducts();
	    	mv.addObject("pList",pList);
	    	return mv;
	    }
	    
	  
}
