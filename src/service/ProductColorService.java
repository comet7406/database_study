package service;

import entity.ProductColor;
import repository.ProductColorRepository;

public class ProductColorService {
	
	private ProductColorRepository productColorRepository;
	private static ProductColorService instance;
	
	private ProductColorService () {
		productColorRepository = ProductColorRepository.getInstance();
	}
	
	public static ProductColorService getInstance() {
		if(instance == null) {
			instance = new ProductColorService();
		}
		return instance;
	}
	
	public boolean isProductColorNameDuplicated(String productColorName) {
		boolean result = false;
		result = productColorRepository.findProductColorByProductColorName(productColorName) != null;
		return result;
	}
	
	public boolean  registerProductColor(ProductColor productColor) {
		boolean result = false;
		result = productColorRepository.saveProductColor(productColor) > 0;
		return result;
	}
}
