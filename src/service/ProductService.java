package service;

import java.util.List;

import entity.Product;
import repository.ProductRepository;

public class ProductService {
	
	private ProductRepository productRepository;
	private static ProductService instance;
	
	private ProductService() {
		productRepository = ProductRepository.getInstance();
	}
	
	public static ProductService getInstance() {
		if(instance == null) {
			instance = new ProductService();
		}
		return instance;
	}
	
	public boolean isProductNameDuplicated(String productName) {
		return productRepository.findProductByProductName(productName) != null;
	}
	
	public boolean registerProduct(Product product) {
		return productRepository.saveProduct(product) > 0;
	}
	
	public List<Product> searchProduct(String searchOption, String searchValue) {
		
		return productRepository.getSearchProductList(searchOption, searchValue);
	}
	
	public boolean removeProdcut(int productId) {
		return productRepository.deleteProduct(productId) > 0;
	}
	
	public Product getProductByProductId(int productId) {
		return productRepository.findProductByProductId(productId);
	}
	
	public boolean modifyProduct(Product product) {
		return productRepository.updateProduct(product) > 0;
	}
}
