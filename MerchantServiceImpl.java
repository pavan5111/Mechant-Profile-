package com.capgemini.adminstore.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.capgemini.adminstore.beans.Coupon;
import com.capgemini.adminstore.beans.Discount;
import com.capgemini.adminstore.beans.Merchant;
import com.capgemini.adminstore.beans.Product;
import com.capgemini.adminstore.exceptions.CouponNotFoundException;
import com.capgemini.adminstore.exceptions.DiscountNotFoundException;
import com.capgemini.adminstore.exceptions.ProductNotFoundException;
import com.capgemini.adminstore.repo.CouponRepo;
import com.capgemini.adminstore.repo.DiscountRepo;
import com.capgemini.adminstore.repo.MerchantRepo;
import com.capgemini.adminstore.repo.ProductRepo;

@Service
public class MerchantServiceImpl implements MerchantService {

	@Autowired
	private ProductRepo productRepos;

	@Autowired
	private CouponRepo couponRepo;

	@Autowired
	private DiscountRepo discountRepo;

	@Autowired
	private MerchantRepo merchantRepo;

	/*
	 * @Override public List<Product> findById1(int productId) {
	 * 
	 * List<Product> prolist = (List<Product>)
	 * productRepos.findById(productId).get();
	 * 
	 * return prolist;
	 * 
	 * }
	 */
	@Override
	public void save(Product pro) {

		productRepos.save(pro);

	}

	@Override

	public Merchant findById(int merchantId) {

		Merchant merchant = merchantRepo.findById(merchantId).get();

		return merchant;

	}

	@Override
	public Product addNewProduct(Product product) {
		product = new Product();

		return productRepos.save(product);
	}

	@Override
	public String deleteProduct(@PathVariable int no) throws ProductNotFoundException {
		return productRepos.findById(no).map(account -> {
			productRepos.delete(account);
			return "deleted product";
		}).orElseThrow(() -> new ProductNotFoundException("Account No " + no + " not found"));
	}

	@Override
	public String removeDiscount(@PathVariable int number) throws DiscountNotFoundException {
		return discountRepo.findById(number).map(account -> {
			discountRepo.delete(account);
			return "deleted discount";
		}).orElseThrow(() -> new DiscountNotFoundException("Account No " + number + " not found"));

	}

	@Override
	public String removeCoupon(@PathVariable int no) throws CouponNotFoundException {

		return couponRepo.findById(no).map(account -> {

			couponRepo.delete(account);

			return "deleted";

		}).orElseThrow(() -> new CouponNotFoundException("Account No " + no + " not found"));

	}

	@Override

	public Coupon addCoupon(Coupon coupon) {

		coupon = new Coupon();

		return couponRepo.save(coupon);

	}

	@Override

	public Discount addDiscount(Discount discount) {

		discount = new Discount();

		return discountRepo.save(discount);

	}

}
