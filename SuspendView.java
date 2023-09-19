package bankybc;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;

import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;

public class SuspendView {

	public JFrame frameSuspendView;
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
					SuspendView window = new SuspendView();
					window.frameSuspendView.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public SuspendView() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frameSuspendView = new JFrame();
		frameSuspendView.setBounds(100, 100, 654, 652);
		frameSuspendView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameSuspendView.setLocationRelativeTo(null);
		frameSuspendView.getContentPane().setLayout(null);
		
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
		frameSuspendView.getContentPane().add(label);
		
		JLabel lblSuspendAccount = new JLabel("Suspend Account");
		lblSuspendAccount.setHorizontalAlignment(SwingConstants.CENTER);
		lblSuspendAccount.setFont(new Font("Trajan Pro", Font.BOLD, 16));
		lblSuspendAccount.setBounds(332, 34, 236, 46);
		frameSuspendView.getContentPane().add(lblSuspendAccount);
		
		JLabel label_1 = new JLabel("Please input your information below:");
		label_1.setFont(new Font("Bookman Old Style", Font.BOLD, 16));
		label_1.setBounds(22, 131, 367, 51);
		frameSuspendView.getContentPane().add(label_1);
		
		JLabel label_2 = new JLabel("Account number:");
		label_2.setFont(new Font("Arial", Font.BOLD, 14));
		label_2.setBounds(22, 204, 175, 33);
		frameSuspendView.getContentPane().add(label_2);
		
		JLabel label_3 = new JLabel("PIN:");
		label_3.setFont(new Font("Arial", Font.BOLD, 14));
		label_3.setBounds(22, 261, 113, 33);
		frameSuspendView.getContentPane().add(label_3);
		
		JLabel label_4 = new JLabel("Your information:");
		label_4.setFont(new Font("Arial", Font.BOLD, 14));
		label_4.setBounds(22, 317, 175, 33);
		frameSuspendView.getContentPane().add(label_4);
		
		JLabel label_5 = new JLabel("");
		label_5.setFont(new Font("Arial", Font.BOLD, 14));
		label_5.setBounds(22, 360, 332, 33);
		frameSuspendView.getContentPane().add(label_5);
		
		JLabel label_6 = new JLabel("");
		label_6.setFont(new Font("Arial", Font.BOLD, 14));
		label_6.setBounds(22, 414, 332, 33);
		frameSuspendView.getContentPane().add(label_6);
		
		JLabel label_7 = new JLabel("");
		label_7.setFont(new Font("Arial", Font.BOLD, 14));
		label_7.setBounds(22, 457, 332, 33);
		frameSuspendView.getContentPane().add(label_7);
		
		//Suspend button
		JButton btnSuspend = new JButton("Option");
		btnSuspend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SuspendControl suspendCon = new SuspendControl(); 
				String accountNum = textField.getText();
				Account temp = null;
        		temp = Control.showInformation(accountNum, list);
				if(temp.isOverdraftState()==true) {
					suspendCon.finishChange(accountNum, list);
					try {
						Control.writeTxtData(list);
						
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
	    			JOptionPane.showMessageDialog(null, "You suspend successfully!"); 
				}else if(temp.isOverdraftState()==false){
					suspendCon.finishChange2(accountNum, list);
					try {
						Control.writeTxtData(list);
						
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
	    			JOptionPane.showMessageDialog(null, "You re-instate successfully!"); 
				}
											  			
    			frameSuspendView.dispose();
				BankView ob = new BankView();
				ob.frameBankView.setVisible(true);
			}
		});
		btnSuspend.setBounds(450, 466, 142, 46);
		frameSuspendView.getContentPane().add(btnSuspend);
		btnSuspend.setEnabled(false);
				
		
		//Confirm button
		JButton btnConfirm = new JButton("Confirm");
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SuspendControl suspendCon = new SuspendControl();   
				String accountNum = textField.getText();                          
				String pin = textField_1.getText();
				Account temp = null;
				
				int verify1 = suspendCon.verify1(accountNum,pin,list);
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
	        		if(temp.isOverdraftState()==true) {// if account is active, then option is to suspend
	        			btnSuspend.setText("Suspend");
	        		}else if(temp.isOverdraftState()==false) {//if account is suspended, then option is to re-instate
	        			btnSuspend.setText("Re-instate");
	        		}
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
	        		btnConfirm.setEnabled(false);
	        		btnSuspend.setEnabled(true);

	        		
	        	}else {
	        		System.out.println("There is a problem on checking your information!");
	        	}
				
			}
		});
		btnConfirm.setBounds(450, 386, 142, 46);
		frameSuspendView.getContentPane().add(btnConfirm);
		
		//Quit button
		JButton btnQuit = new JButton("Quit");
		btnQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frameSuspendView.dispose();
				BankView ob = new BankView();
				ob.frameBankView.setVisible(true);
			}
		});
		btnQuit.setBounds(450, 547, 142, 46);
		frameSuspendView.getContentPane().add(btnQuit);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(179, 204, 170, 33);
		frameSuspendView.getContentPane().add(textField);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(179, 261, 170, 33);
		frameSuspendView.getContentPane().add(textField_1);
		
		
	}

}
