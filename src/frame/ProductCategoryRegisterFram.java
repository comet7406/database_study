package frame;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;

public class ProductCategoryRegisterFram extends JFrame {

	private JPanel contentPane;
	private JTextField productNameTextField;
	private JTextField productPriceTextField;
	private JTextField productColorTextField;
	private JTextField productCategoryTextField;
	private JButton registerSubmitButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProductCategoryRegisterFram frame = new ProductCategoryRegisterFram();
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
	public ProductCategoryRegisterFram() {
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
		productColorLabel.setBounds(13, 135, 57, 31);
		contentPane.add(productColorLabel);
		
		productColorTextField = new JTextField();
		productColorTextField.setColumns(10);
		productColorTextField.setBounds(98, 135, 325, 31);
		contentPane.add(productColorTextField);
		
		JLabel productCategoryLabel = new JLabel("분류");
		productCategoryLabel.setBounds(13, 176, 57, 31);
		contentPane.add(productCategoryLabel);
		
		productCategoryTextField = new JTextField();
		productCategoryTextField.setColumns(10);
		productCategoryTextField.setBounds(98, 176, 325, 31);
		contentPane.add(productCategoryTextField);
		
		registerSubmitButton = new JButton("등록하기");
		registerSubmitButton.setBounds(13, 219, 410, 30);
		contentPane.add(registerSubmitButton);
	}
}
