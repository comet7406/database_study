package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.mysql.cj.protocol.Resultset;

import config.DBConnectionMgr;
import entity.ProductColor;

public class ProductColorRepository {
	
	private DBConnectionMgr pool;
	private static ProductColorRepository instance;
	
	private ProductColorRepository() {
		pool = DBConnectionMgr.getInstance();
	}
	
	public static ProductColorRepository getInstance() {
		if(instance == null) {
			instance = new ProductColorRepository();
		}
		return instance;
	}
	
	public ProductColor findProductColorByProductColorName(String productColorName) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ProductColor productColor = null;
		
		try {
			con = pool.getConnection();
			String sql = "select "
					+ "product_color_id, "
					+ "product_color_name "
					+ "from "
					+ "product_color_tb "
					+ "where "
					+ "product_color_name = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, productColorName);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				productColor = ProductColor.builder()
						.productColorId(rs.getInt(1))
						.productColorName(rs.getString(2))
						.build();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		
		return productColor;
	}
	
	public int saveProductColor(ProductColor productColor) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int successCount = 0;
		
		try {
			con = pool.getConnection();
			String sql = "insert into product_color_tb values(0, ?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, productColor.getProductColorName());
			successCount = pstmt.executeUpdate();
					
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt);
		}
		
		return successCount;
	}
}
