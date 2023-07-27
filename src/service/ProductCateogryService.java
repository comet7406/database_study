package service;

import java.util.ArrayList;
import java.util.List;

import entity.ProductCategory;
import repository.ProductCategoryRepository;

public class ProductCateogryService {
	
	private ProductCategoryRepository productCategoryRepository;
	private static ProductCateogryService instance;
	
	private ProductCateogryService() {
		productCategoryRepository = ProductCategoryRepository.getInstance();
	}
	
	public static ProductCateogryService getInstance() {
		if(instance == null) {
			instance = new ProductCateogryService();
		}		
		return instance;
	}
	
	public List<String> getProductCategoryNameList() {
		List<String> productCategoryNameList = new ArrayList<>();
		
		productCategoryRepository.getProductCategoryListAll().forEach(productColor -> {
			productCategoryNameList.add(productColor.getProductCategoryName());
		});
		
		return productCategoryNameList;
	}
	
	public boolean isProductCategoryNameDuplicated(String productCategoryName) {
		boolean result = false;
		result = productCategoryRepository.findProductCategoryByProductCategoryName(productCategoryName) != null;
		return result;
	}
	
	public boolean registerProductCategory(ProductCategory productCategory) {
		boolean result = false;
		result = productCategoryRepository.saveProductCategory(productCategory) > 0;
		return result;
	}
}
