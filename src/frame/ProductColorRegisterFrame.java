package frame;

import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import entity.ProductColor;
import service.ProductColorService;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;

public class ProductColorRegisterFrame extends JFrame {

	private JPanel contentPane;
	private JTextField productColorNameTextField;
	private JButton registerSubmitButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProductColorRegisterFrame frame = new ProductColorRegisterFrame();
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
	public ProductColorRegisterFrame() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 189);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel titleLabel = new JLabel("상품 색상 등록");
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setBounds(12, 10, 410, 31);
		contentPane.add(titleLabel);
		
		JLabel productColorNameLabel = new JLabel("색상명");
		productColorNameLabel.setBounds(13, 53, 57, 31);
		contentPane.add(productColorNameLabel);
		
		productColorNameTextField = new JTextField();
		productColorNameTextField.setBounds(98, 53, 325, 31);
		contentPane.add(productColorNameTextField);
		productColorNameTextField.setColumns(10);
		
		registerSubmitButton = new JButton("등록하기");
		registerSubmitButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String productColorName = productColorNameTextField.getText();
				if(isTextEmpty(productColorName)) { return; }
				if(ProductColorService.getInstance().isProductColorNameDuplicated(productColorName)) {
					JOptionPane.showMessageDialog(contentPane, "이미 존재하는 색상명입니다.", "중복오류", JOptionPane.ERROR_MESSAGE);
					return;
				}
				ProductColor productColor = ProductColor.builder()
						.productColorName(productColorName)
						.build();
				
				if(!ProductColorService.getInstance().registerProductColor(productColor)) {
					JOptionPane.showMessageDialog(contentPane, "색상등록 중 오류가 발생하였습니다.", "등록오류", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				JOptionPane.showMessageDialog(contentPane, "새로운 색상을 등록하였습니다.", "등록성공", JOptionPane.PLAIN_MESSAGE);
				clearTextField(productColorNameTextField);
			}
		});
		registerSubmitButton.setBounds(13, 102, 410, 30);
		contentPane.add(registerSubmitButton);
	}
	
	private boolean isTextEmpty(String str) {
		if(str != null) {
			if(!str.isBlank()) {
				return false;
			}
		}
		JOptionPane.showMessageDialog(contentPane, "내용을 입력하세요.", "입력오류", JOptionPane.ERROR_MESSAGE);
		return true;
	}
	
	private void clearTextField(JTextField textField) {
		textField.setText("");
	}
}
