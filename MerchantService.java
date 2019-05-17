package com.capgemini.adminstore.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.capgemini.adminstore.beans.Coupon;
import com.capgemini.adminstore.beans.Discount;
import com.capgemini.adminstore.beans.Merchant;
import com.capgemini.adminstore.beans.Product;
import com.capgemini.adminstore.exceptions.CouponNotFoundException;
import com.capgemini.adminstore.exceptions.DiscountNotFoundException;
import com.capgemini.adminstore.exceptions.ProductNotFoundException;

@Service
public interface MerchantService 
{
	public Product addNewProduct( Product product);
	
	public String deleteProduct(int productid) throws ProductNotFoundException;

    public void save( Product product);
    
    public Coupon addCoupon(Coupon coupon);
    
    public Discount addDiscount(Discount discount);
    
    public String removeDiscount(int no) throws DiscountNotFoundException;
    
    public String removeCoupon(int no) throws CouponNotFoundException;

	//public List<Product> findById1(int productId);

	public Merchant findById(int merchantId);
    
}
