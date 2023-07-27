package frame;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import entity.Product;
import entity.ProductCategory;
import entity.ProductColor;
import repository.ProductCategoryRepository;
import service.ProductCateogryService;
import service.ProductColorService;
import service.ProductService;
import utils.CustomSwingComboBoxUtil;
import utils.CustomSwingTextUtil;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ProductRegisterFrame extends JFrame {

	private JPanel contentPane;
	private JTextField productNameTextField;
	private JTextField productPriceTextField;
	private JButton registerSubmitButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProductRegisterFrame frame = new ProductRegisterFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ProductRegisterFrame() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel titleLabel = new JLabel("상품 등록");
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setBounds(12, 10, 410, 31);
		contentPane.add(titleLabel);
		
		JLabel productNameLabel = new JLabel("상품명");
		productNameLabel.setBounds(13, 53, 57, 31);
		contentPane.add(productNameLabel);
		
		productNameTextField = new JTextField();
		productNameTextField.setBounds(98, 53, 325, 31);
		contentPane.add(productNameTextField);
		productNameTextField.setColumns(10);
		
		
		JLabel productPriceLabel = new JLabel("가격");
		productPriceLabel.setBounds(13, 94, 57, 31);
		contentPane.add(productPriceLabel);
		
		productPriceTextField = new JTextField();
		productPriceTextField.setColumns(10);
		productPriceTextField.setBounds(98, 94, 325, 31);
		contentPane.add(productPriceTextField);
		
		JLabel productColorLabel = new JLabel("색상");
		productColorLabel.setBounds(13, 134, 57, 31);
		contentPane.add(productColorLabel);
		
		JComboBox colorComboBox = new JComboBox();
		CustomSwingComboBoxUtil.setComboBoxModel(colorComboBox, ProductColorService.getInstance().getProductColorNameList());
		colorComboBox.setBounds(98, 138, 324, 23);
		contentPane.add(colorComboBox);
		
		JLabel productCategoryLabel = new JLabel("카테고리");
		productCategoryLabel.setBounds(13, 176, 57, 31);
		contentPane.add(productCategoryLabel);
		
		JComboBox categoryComboBox = new JComboBox();
		CustomSwingComboBoxUtil.setComboBoxModel(categoryComboBox, ProductCateogryService.getInstance().getProductCategoryNameList());
		categoryComboBox.setBounds(98, 180, 324, 23);
		contentPane.add(categoryComboBox);
		
		registerSubmitButton = new JButton("등록하기");
		registerSubmitButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String productName = productNameTextField.getText();
				if(CustomSwingTextUtil.isTextEmpty(contentPane, productName)) { return; } 
				
				String productPrice = productPriceTextField.getText();
				if(CustomSwingTextUtil.isTextEmpty(contentPane, productPrice)) { return; }
				
				String productColorName = (String) colorComboBox.getSelectedItem();
				if(CustomSwingTextUtil.isTextEmpty(contentPane, productColorName)) { return; }
				
				String productCategoryName = (String) categoryComboBox.getSelectedItem();
				if(CustomSwingTextUtil.isTextEmpty(contentPane, productCategoryName)) { return; }
				
				Product product = Product.builder()
						.productName(productName)
						.productPrice(Integer.parseInt(productPrice))
						.productColor(ProductColor.builder().productColorName(productColorName).build())
						.productCategory(ProductCategory.builder().productCategoryName(productCategoryName).build())
						.build();
				
				if(!ProductService.getInstance().registerProduct(product)) {
					JOptionPane.showMessageDialog(contentPane, "상품등록 중 오류가 발생하였습니다.", "등록오류", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				JOptionPane.showMessageDialog(contentPane, "새로운 상품을 등록하였습니다.", "등록성공", JOptionPane.PLAIN_MESSAGE);
				CustomSwingTextUtil.clearTextField(productNameTextField);
				CustomSwingTextUtil.clearTextField(productPriceTextField);
				colorComboBox.setSelectedIndex(0);
				categoryComboBox.setSelectedIndex(0);
			}
		});
		registerSubmitButton.setBounds(13, 219, 410, 30);
		contentPane.add(registerSubmitButton);
		
		
		

	}
}
