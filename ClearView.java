package bankybc;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;

public class ClearView {

	public JFrame frameClearView;
	private JTextField textField;
	private JTextField textField_1;

	List<Account> list = new ArrayList<Account>();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClearView window = new ClearView();
					window.frameClearView.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ClearView() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frameClearView = new JFrame();
		frameClearView.setBounds(100, 100, 646, 646);
		frameClearView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameClearView.setLocationRelativeTo(null);
		frameClearView.getContentPane().setLayout(null);
		

		try {
			list = Control.readTxtData();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		JLabel label = new JLabel("Banking_SystemX");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setForeground(new Color(0, 153, 255));
		label.setFont(new Font("Arial Black", Font.BOLD | Font.ITALIC, 18));
		label.setBackground(Color.WHITE);
		label.setBounds(10, 10, 251, 84);
		frameClearView.getContentPane().add(label);
		
		JLabel lblClearFunds = new JLabel("CLEAR FUNDS");
		lblClearFunds.setHorizontalAlignment(SwingConstants.CENTER);
		lblClearFunds.setFont(new Font("Trajan Pro", Font.BOLD, 16));
		lblClearFunds.setBounds(325, 34, 236, 46);
		frameClearView.getContentPane().add(lblClearFunds);
		
		JLabel label_1 = new JLabel("Please fill in your information below:");
		label_1.setFont(new Font("Bookman Old Style", Font.BOLD, 16));
		label_1.setBounds(36, 95, 367, 51);
		frameClearView.getContentPane().add(label_1);
		
		JLabel label_2 = new JLabel("Account number:");
		label_2.setFont(new Font("Arial", Font.BOLD, 14));
		label_2.setBounds(36, 168, 175, 33);
		frameClearView.getContentPane().add(label_2);
		
		JLabel lblPin = new JLabel("PIN:");
		lblPin.setFont(new Font("Arial", Font.BOLD, 14));
		lblPin.setBounds(36, 227, 175, 33);
		frameClearView.getContentPane().add(lblPin);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(200, 169, 170, 33);
		frameClearView.getContentPane().add(textField);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(200, 227, 170, 33);
		frameClearView.getContentPane().add(textField_1);
		
		JLabel lblYourInformations = new JLabel("Your information:");
		lblYourInformations.setFont(new Font("Arial", Font.BOLD, 14));
		lblYourInformations.setBounds(36, 294, 175, 33);
		frameClearView.getContentPane().add(lblYourInformations);
		
		
		JLabel label_3 = new JLabel("");
		label_3.setFont(new Font("Arial", Font.BOLD, 14));
		label_3.setBounds(36, 347, 334, 33);
		frameClearView.getContentPane().add(label_3);
		
		JLabel label_4 = new JLabel("");
		label_4.setFont(new Font("Arial", Font.BOLD, 14));
		label_4.setBounds(36, 414, 334, 33);
		frameClearView.getContentPane().add(label_4);
		
		JLabel label_5 = new JLabel("");
		label_5.setFont(new Font("Arial", Font.BOLD, 14));
		label_5.setBounds(36, 476, 334, 33);
		frameClearView.getContentPane().add(label_5);
		
		//Clear button
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ClearControl clearCon = new ClearControl();   
				String accountNum = textField.getText();                          
				clearCon.finishChange(accountNum, list);
			
    			try {
					Control.writeTxtData(list);
					
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
    			JOptionPane.showMessageDialog(null, "You clear your funds successfully!"); 
    			frameClearView.dispose();
    			BankView ob = new BankView();
				ob.frameBankView.setVisible(true);
			}
		});
		btnClear.setBounds(442, 431, 142, 46);
		frameClearView.getContentPane().add(btnClear);
		btnClear.setEnabled(false);
		
		//Confirm button
		JButton btnConfirm = new JButton("Confirm");
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ClearControl clearCon = new ClearControl();   
				String accountNum = textField.getText();                          
				String pin = textField_1.getText();
				Account temp = null;
				
				int verify1 = clearCon.verify1(accountNum,pin,list);
	        	if(verify1 == 0) {
	        		JOptionPane.showMessageDialog(null, "Your information can't have blank.",  accountNum, JOptionPane.ERROR_MESSAGE); 
	        	}else if(verify1 == -1) {
	        		JOptionPane.showMessageDialog(null, "We can not find your information.",  accountNum, JOptionPane.ERROR_MESSAGE); 
	        	}else if(verify1 == -3) {
	        		JOptionPane.showMessageDialog(null, "Your password is wrong.",  accountNum, JOptionPane.ERROR_MESSAGE); 
	        	}else if(verify1 == -2) {
	        		JOptionPane.showMessageDialog(null, "Your account is suspended.",  accountNum, JOptionPane.ERROR_MESSAGE); 
	        	}else if(verify1 == 1){
	        		
	        		temp = Control.showInformation(accountNum, list);
	        		label_3.setText(" You Account number is "+ temp.getAccNum());
	        		label_4.setText(" You have "+ temp.getUncleared() +" uncleared.");
	        		label_5.setText(" You have "+ temp.getBalance() +" balance.");
	        		btnClear.setEnabled(true);
	        		btnConfirm.setEnabled(false);
	        		
	        	}else {
	        		System.out.println("There is problem on check your information!");
	        	}
				
			}
		});
		btnConfirm.setBounds(442, 355, 142, 46);
		frameClearView.getContentPane().add(btnConfirm);
		
		
		
		//Quit button
		JButton btnQuit = new JButton("Quit");
		btnQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frameClearView.dispose();
				BankView ob = new BankView();
				ob.frameBankView.setVisible(true);
			}
		});
		btnQuit.setBounds(442, 517, 142, 46);
		frameClearView.getContentPane().add(btnQuit);	
		
	}

}
