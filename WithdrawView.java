package bankybc;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;


import javax.swing.JEditorPane;
import java.awt.SystemColor;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.awt.event.ActionEvent;

public class WithdrawView {

	public JFrame frameWithdrawView;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	List<Account> list = new ArrayList<Account>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WithdrawView window = new WithdrawView();
					window.frameWithdrawView.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public WithdrawView() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frameWithdrawView = new JFrame();
		frameWithdrawView.setBounds(100, 100, 655, 650);
		frameWithdrawView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameWithdrawView.setLocationRelativeTo(null);
		frameWithdrawView.getContentPane().setLayout(null);
		
		
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
		frameWithdrawView.getContentPane().add(label);
		
		JLabel lblWithdraw = new JLabel("Withdraw");
		lblWithdraw.setHorizontalAlignment(SwingConstants.CENTER);
		lblWithdraw.setFont(new Font("Trajan Pro", Font.BOLD, 16));
		lblWithdraw.setBounds(344, 25, 236, 46);
		frameWithdrawView.getContentPane().add(lblWithdraw);
		
		JLabel label_1 = new JLabel("Please input your information below:");
		label_1.setFont(new Font("Bookman Old Style", Font.BOLD, 16));
		label_1.setBounds(35, 205, 367, 51);
		frameWithdrawView.getContentPane().add(label_1);
		
		JEditorPane dtrpnpleaseFirstInput = new JEditorPane();
		dtrpnpleaseFirstInput.setText("1.Please input Account number and PIN\r\n2.Then click Confirm to continue\r\n3.Enter your Withdraw number\r\n4.Then click Withdraw to finish");
		dtrpnpleaseFirstInput.setForeground(Color.BLACK);
		dtrpnpleaseFirstInput.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 14));
		dtrpnpleaseFirstInput.setEditable(false);
		dtrpnpleaseFirstInput.setBackground(SystemColor.controlHighlight);
		dtrpnpleaseFirstInput.setBounds(32, 86, 297, 97);
		frameWithdrawView.getContentPane().add(dtrpnpleaseFirstInput);
		
		JEditorPane dtrpnOnlyCurrentAccount = new JEditorPane();
		dtrpnOnlyCurrentAccount.setText("Only Current account may withdraw\r\nadditional funds up to overdraft limits\r\nwhich is 500.");
		dtrpnOnlyCurrentAccount.setForeground(Color.BLACK);
		dtrpnOnlyCurrentAccount.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 14));
		dtrpnOnlyCurrentAccount.setEditable(false);
		dtrpnOnlyCurrentAccount.setBackground(SystemColor.controlHighlight);
		dtrpnOnlyCurrentAccount.setBounds(344, 86, 290, 97);
		frameWithdrawView.getContentPane().add(dtrpnOnlyCurrentAccount);
		
		JLabel lblPin = new JLabel("PIN:");
		lblPin.setFont(new Font("Arial", Font.BOLD, 14));
		lblPin.setBounds(35, 331, 113, 33);
		frameWithdrawView.getContentPane().add(lblPin);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(197, 266, 170, 33);
		frameWithdrawView.getContentPane().add(textField);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(197, 332, 170, 33);
		frameWithdrawView.getContentPane().add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10); 
		textField_2.setBounds(197, 573, 170, 33);
		frameWithdrawView.getContentPane().add(textField_2);
		textField_2.setEditable(false);
		
		
		JLabel label_4 = new JLabel("");
		label_4.setToolTipText("123");
		label_4.setFont(new Font("Arial", Font.BOLD, 14));
		label_4.setBounds(35, 498, 334, 33);
		frameWithdrawView.getContentPane().add(label_4);
		
		JLabel label_5 = new JLabel("");
		label_5.setFont(new Font("Arial", Font.BOLD, 14));
		label_5.setBounds(35, 530, 334, 33);
		frameWithdrawView.getContentPane().add(label_5);
		
		JLabel label_6 =  new JLabel("");
		label_6.setFont(new Font("Arial", Font.BOLD, 14));
		label_6.setBounds(35, 436, 332, 33);
		frameWithdrawView.getContentPane().add(label_6);
		
		JLabel label_7 = new JLabel("");
		label_7.setFont(new Font("Arial", Font.BOLD, 14));
		label_7.setBounds(35, 467, 332, 33);
		frameWithdrawView.getContentPane().add(label_7);
		
		JButton btnWithdraw = new JButton("Withdraw");
		btnWithdraw.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WithdrawControl withdrawCon = new WithdrawControl();   
				String accountNum = textField.getText();                          
				String withdrawNum = textField_2.getText();
				Account temp = null;				
    			
				
				int verify2 = withdrawCon.finishWithdraw(accountNum, list, withdrawNum);
	        	if(verify2 == 0) {
	        		JOptionPane.showMessageDialog(null, "Your information can't have blank.", withdrawNum , JOptionPane.ERROR_MESSAGE); 
	        	}else if(verify2 == -2) {
	        		JOptionPane.showMessageDialog(null, "The withdraw amount needs to be numbers.",  withdrawNum, JOptionPane.ERROR_MESSAGE); 
	        	}else if(verify2 == -1) {
	        		JOptionPane.showMessageDialog(null, "You can't withdraw overhead.",  withdrawNum, JOptionPane.ERROR_MESSAGE); 
	        	}else if(verify2 == -3) {
	        		JOptionPane.showMessageDialog(null, "You can only overdraft 500.",  withdrawNum, JOptionPane.ERROR_MESSAGE);   		
	        	}else if(verify2 == -4) {
	        		JOptionPane.showMessageDialog(null, "For SaverAccount, the difference between Register date and Withdraw date should be larger than 7.",  accountNum, JOptionPane.ERROR_MESSAGE); 
	        		btnWithdraw.setEnabled(false);
	        		textField_2.setText("");
	        		textField_2.setEditable(false);
	        	}else if(verify2 == 1){
	        		
	        		double withdraw = Double.parseDouble(withdrawNum);
	        			        		        			
	        		JOptionPane.showMessageDialog(null, "You withdraw "+  withdraw  +" successfully!");
	        	
	        		try {
						Control.writeTxtData(list);
						
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
	        			
	        		frameWithdrawView.dispose();
	    			BankView ob = new BankView();
	    			ob.frameBankView.setVisible(true);
	        	
	        	}else {
	        		System.out.println("There is problem on check numbers information!");
	        	}
	        	
				
				
			}
		});
		btnWithdraw.setBounds(456, 464, 142, 46);
		frameWithdrawView.getContentPane().add(btnWithdraw);
		btnWithdraw.setEnabled(false);
		
		JButton btnConfirm = new JButton("Confirm");
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int verify1 = 1;
				WithdrawControl withdrawCon = new WithdrawControl();   
				String accountNum = textField.getText();                          
				String pin= textField_1.getText(); 
				Account temp = null;				
				
	        	try{
	        		verify1 = withdrawCon.verify1(accountNum,pin,list);
	        	}catch(Exception e1) {
	        		
	        	}
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
	        		label_4.setText(" Your balance is "+ temp.getBalance());
	        		label_5.setText(" Your have "+ temp.getUncleared() +" uncleared");
	        		label_7.setText(" Your register time is  "+ temp.getOpenTime());
	        		System.out.print(temp.getOpenTime());
	        		if(temp.getType() == 1) {
	        			label_6.setText("Your AccountType is SaverAccount");
	        		}else if(temp.getType() == 2) {
	        			label_6.setText("Your AccountType is JuniorAccount");
	        		}else if(temp.getType() == 3) {
	        			label_6.setText("Your AccountType is CurrentAccount");
	        		}
	        		textField.setEditable(false);
	        		textField_1.setEditable(false);
	        		textField_2.setEditable(true);
	        		btnConfirm.setEnabled(false);   
	        		btnWithdraw.setEnabled(true);
	        	
	        	}else {
	        		System.out.println("There is a problem on check your information!");
	        	}
			}
		});
		btnConfirm.setBounds(456, 398, 142, 46);
		frameWithdrawView.getContentPane().add(btnConfirm);
		
		
		
		JButton button_2 = new JButton("Quit");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frameWithdrawView.dispose();
				BankView ob = new BankView();
				ob.frameBankView.setVisible(true);
			}
		});
		button_2.setBounds(456, 532, 142, 46);
		frameWithdrawView.getContentPane().add(button_2);
		
		JLabel label_2 = new JLabel("Account number:");
		label_2.setFont(new Font("Arial", Font.BOLD, 14));
		label_2.setBounds(34, 266, 175, 33);
		frameWithdrawView.getContentPane().add(label_2);
		
		JLabel label_3 = new JLabel("Your information:");
		label_3.setFont(new Font("Arial", Font.BOLD, 14));
		label_3.setBounds(35, 393, 175, 33);
		frameWithdrawView.getContentPane().add(label_3);
		
		
		
		JLabel lblWithdrawNumber = new JLabel("Withdraw number:");
		lblWithdrawNumber.setFont(new Font("Arial", Font.BOLD, 14));
		lblWithdrawNumber.setBounds(32, 572, 175, 33);
		frameWithdrawView.getContentPane().add(lblWithdrawNumber);
		
	
		
		
	}
}
