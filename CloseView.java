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

public class CloseView {

	public JFrame frameCloseView;
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
					CloseView window = new CloseView();
					window.frameCloseView.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public CloseView() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frameCloseView = new JFrame();
		frameCloseView.setBounds(100, 100, 653, 652);
		frameCloseView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameCloseView.setLocationRelativeTo(null);
		frameCloseView.getContentPane().setLayout(null);
		

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
		label.setBounds(25, 27, 251, 84);
		frameCloseView.getContentPane().add(label);
		
		JLabel lblCloseAccount = new JLabel("Close Account");
		lblCloseAccount.setHorizontalAlignment(SwingConstants.CENTER);
		lblCloseAccount.setFont(new Font("Trajan Pro", Font.BOLD, 16));
		lblCloseAccount.setBounds(346, 51, 236, 46);
		frameCloseView.getContentPane().add(lblCloseAccount);
		
		JLabel label_1 = new JLabel("Please input your information below:");
		label_1.setFont(new Font("Bookman Old Style", Font.BOLD, 16));
		label_1.setBounds(25, 123, 367, 51);
		frameCloseView.getContentPane().add(label_1);
		
		JLabel label_2 = new JLabel("Account number:");
		label_2.setFont(new Font("Arial", Font.BOLD, 14));
		label_2.setBounds(25, 206, 175, 33);
		frameCloseView.getContentPane().add(label_2);
		
		JLabel label_3 = new JLabel("PIN:");
		label_3.setFont(new Font("Arial", Font.BOLD, 14));
		label_3.setBounds(25, 265, 113, 33);
		frameCloseView.getContentPane().add(label_3);
		
		JLabel label_4 = new JLabel("Your information:");
		label_4.setFont(new Font("Arial", Font.BOLD, 14));
		label_4.setBounds(25, 320, 175, 33);
		frameCloseView.getContentPane().add(label_4);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(198, 206, 170, 33);
		frameCloseView.getContentPane().add(textField);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(198, 265, 170, 33);
		frameCloseView.getContentPane().add(textField_1);
		
		JLabel label_5 = new JLabel("");
		label_5.setFont(new Font("Arial", Font.BOLD, 14));
		label_5.setBounds(25, 366, 332, 33);
		frameCloseView.getContentPane().add(label_5);
		
		JLabel label_6 = new JLabel("");
		label_6.setFont(new Font("Arial", Font.BOLD, 14));
		label_6.setBounds(25, 409, 332, 33);
		frameCloseView.getContentPane().add(label_6);
		
		JLabel label_7 = new JLabel("");
		label_7.setFont(new Font("Arial", Font.BOLD, 14));
		label_7.setBounds(25, 452, 332, 33);
		frameCloseView.getContentPane().add(label_7);
		
		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CloseControl closeCon = new CloseControl();  
				String accountNum = textField.getText();   
				System.out.println(closeCon.finishChange(accountNum, list));
	        	if(!closeCon.finishChange(accountNum, list)) {
	        		JOptionPane.showMessageDialog(null, "You balance is not null, you can not close the account!"); 
	        	}else {
	    			try {
						Control.writeTxtData(list);
						
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
	    			frameCloseView.dispose();
	    			BankView ob = new BankView();
					ob.frameBankView.setVisible(true);
	        	}
				
			}
		});
		btnClose.setBounds(459, 479, 142, 46);
		frameCloseView.getContentPane().add(btnClose);
		btnClose.setEnabled(false);
		
		JButton btnConfirm = new JButton("Confirm");
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CloseControl closeCon = new CloseControl();   
				String accountNum = textField.getText();                          
				String pin = textField_1.getText();
				Account temp = null;
				
				int verify1 = closeCon.verify1(accountNum,pin,list);
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
	        		label_6.setText(" Your balance is "+ temp.getBalance());
	        		label_7.setText(" Your have "+ temp.getUncleared() +" uncleared");
	        		if(temp.getType() == 1) {
	        			label_5.setText("Your AccountType is SaverAccount");
	        		}else if(temp.getType() == 2) {
	        			label_5.setText("Your AccountType is JuniorAccount");
	        		}else if(temp.getType() == 3) {
	        			label_5.setText("Your AccountType is CurrentAccount");
	        		}
	        		
	        		textField.setEditable(false);
	        		textField_1.setEditable(false);
	        		btnClose.setEnabled(true);
	        		btnConfirm.setEnabled(false);
	        		
	        		
	        	}else {
	        		System.out.println("There is problem on check your information!");
	        	}
			}
		});
		btnConfirm.setBounds(459, 411, 142, 46);
		frameCloseView.getContentPane().add(btnConfirm);
		
		
		JButton btnQuit = new JButton("Quit");
		btnQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frameCloseView.dispose();
				BankView ob = new BankView();
				ob.frameBankView.setVisible(true);
				
				
			}
		});
		btnQuit.setBounds(459, 549, 142, 46);
		frameCloseView.getContentPane().add(btnQuit);
	}

}
