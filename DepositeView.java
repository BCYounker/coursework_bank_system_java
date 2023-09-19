package bankybc;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;

import javax.swing.JButton;
import javax.swing.JEditorPane;
import java.awt.SystemColor;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;

public class DepositeView {

	public JFrame frameDepositeView;
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
					DepositeView window = new DepositeView();
					window.frameDepositeView.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public DepositeView() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		try {
			list = Control.readTxtData();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		frameDepositeView = new JFrame();
		frameDepositeView.setBounds(100, 100, 643, 640);
		frameDepositeView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameDepositeView.getContentPane().setLayout(null);
		frameDepositeView.setLocationRelativeTo(null);
		
		JLabel label = new JLabel("Banking_SystemX");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setForeground(new Color(0, 153, 255));
		label.setFont(new Font("Arial Black", Font.BOLD | Font.ITALIC, 18));
		label.setBackground(Color.WHITE);
		label.setBounds(10, 10, 251, 84);
		frameDepositeView.getContentPane().add(label);
		
		JLabel lblDeposite = new JLabel("DEPOSITE\r\n");
		lblDeposite.setHorizontalAlignment(SwingConstants.CENTER);
		lblDeposite.setFont(new Font("Trajan Pro", Font.BOLD, 16));
		lblDeposite.setBounds(341, 30, 236, 46);
		frameDepositeView.getContentPane().add(lblDeposite);
		
		
		
		JLabel lblPleaseFillIn = new JLabel("Please fill in your information below:");
		lblPleaseFillIn.setFont(new Font("Bookman Old Style", Font.BOLD, 16));
		lblPleaseFillIn.setBounds(32, 104, 367, 51);
		frameDepositeView.getContentPane().add(lblPleaseFillIn);
		
		JLabel lblAccountNumber = new JLabel("Account number:");
		lblAccountNumber.setFont(new Font("Arial", Font.BOLD, 14));
		lblAccountNumber.setBounds(32, 176, 175, 33);
		frameDepositeView.getContentPane().add(lblAccountNumber);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(217, 176, 170, 33);
		frameDepositeView.getContentPane().add(textField);
		
		
		JLabel lblDepositeNumber = new JLabel("Deposite number:");
		lblDepositeNumber.setFont(new Font("Arial", Font.BOLD, 14));
		lblDepositeNumber.setBounds(32, 254, 175, 33);
		frameDepositeView.getContentPane().add(lblDepositeNumber);
		
		JLabel lblUnclearedNumber = new JLabel("Uncleared number:");
		lblUnclearedNumber.setFont(new Font("Arial", Font.BOLD, 14));
		lblUnclearedNumber.setBounds(32, 338, 175, 33);
		frameDepositeView.getContentPane().add(lblUnclearedNumber);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(217, 255, 170, 33);
		frameDepositeView.getContentPane().add(textField_1);
		textField_1.setEditable(false);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(217, 338, 170, 33);
		frameDepositeView.getContentPane().add(textField_2);
		textField_2.setEditable(false);
		
		

		//Submit button
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DepositeControl depositetCon = new DepositeControl(); 
				String accountNum = textField.getText();                          
				String depositNum= textField_1.getText(); 
				String unclearedNum = textField_2.getText();
				Account temp = null;
				
				int verify2 = depositetCon.verify2(depositNum,unclearedNum);
				if(verify2 == 0) {
	        		JOptionPane.showMessageDialog(null, "Your information can't have blank.",  depositNum, JOptionPane.ERROR_MESSAGE); 
	        	}else if(verify2 == -1) {
	        		JOptionPane.showMessageDialog(null, "The information need be numbers.",  depositNum, JOptionPane.ERROR_MESSAGE); 
	        	}else if(verify2 == 1){
	        		
	        		double deposit = Double.parseDouble(depositNum);
	        		double uncleared_deposit = Double.parseDouble(unclearedNum);
	        		if(depositetCon.verify3(deposit,uncleared_deposit)) {
	        			JOptionPane.showMessageDialog(null, "You deposit "+  deposit  +" successfully!");
	            		temp = depositetCon.finishChange(accountNum, list, deposit, uncleared_deposit);
	        			try {
							Control.writeTxtData(list);
							
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
	        			
	        			frameDepositeView.dispose();
	        			BankView ob = new BankView();
	    				ob.frameBankView.setVisible(true);
	        		}else {
	        			JOptionPane.showMessageDialog(null, "Deposit number need to be bigger than uncleared number.",  depositNum, JOptionPane.ERROR_MESSAGE); 
	        		}
		
	        		
	        	}else {
	        		System.out.println("There is problem on check numbers information!");
	        	}
				
			}
		});
		btnSubmit.setBounds(419, 423, 142, 46);
		frameDepositeView.getContentPane().add(btnSubmit);
		btnSubmit.setEnabled(false);
		
		
		
		//Confirm button
		JButton btnConfirm = new JButton("Confirm");
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DepositeControl depositetCon = new DepositeControl();   
				String accountNum = textField.getText();                          
				
				int verify1 = 1;
	        	try {
	        		verify1 = depositetCon.verify1(accountNum,list);	
	        	}catch(Exception e1) {
	        	}
	        	
	        	if(verify1 == 0) {
	        		JOptionPane.showMessageDialog(null, "Your information can't have blank.",  accountNum, JOptionPane.ERROR_MESSAGE); 
	        	}else if(verify1 == -1) {
	        		JOptionPane.showMessageDialog(null, "We can not find your information.",  accountNum, JOptionPane.ERROR_MESSAGE); 
	        	}else if(verify1 == -2) {
	        		JOptionPane.showMessageDialog(null, "Your account is suspended.",  accountNum, JOptionPane.ERROR_MESSAGE); 
	        	}else if(verify1 == 1){
	        		textField.setEditable(false);
	        		textField_1.setEditable(true);
	        		textField_2.setEditable(true);
	        		btnSubmit.setEnabled(true);
	        		btnConfirm.setEnabled(false);
	        	}else {
	        		System.out.println("There is a problem on check your information!");
	        	}
				
			}
		});
		btnConfirm.setBounds(419, 338, 142, 46);
		frameDepositeView.getContentPane().add(btnConfirm);
		
		
		//Quit button
		JButton btnQuit = new JButton("Quit");
		btnQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frameDepositeView.dispose();
				BankView ob = new BankView();
				ob.frameBankView.setVisible(true);
				
			}
		});
		btnQuit.setBounds(419, 501, 142, 46);
		frameDepositeView.getContentPane().add(btnQuit);
		
		JEditorPane dtrpnpleaseFirstInput = new JEditorPane();
		dtrpnpleaseFirstInput.setText("1.Please first input Account number\r\n2.Then click Confirm to continue\r\n3.Enter your Deposite number\r\n4.Enter your Uncleared number\r\n5.Then click Submit to finish");
		dtrpnpleaseFirstInput.setForeground(Color.BLACK);
		dtrpnpleaseFirstInput.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 14));
		dtrpnpleaseFirstInput.setEditable(false);
		dtrpnpleaseFirstInput.setBackground(SystemColor.controlHighlight);
		dtrpnpleaseFirstInput.setBounds(32, 410, 333, 115);
		frameDepositeView.getContentPane().add(dtrpnpleaseFirstInput);
	}
}
