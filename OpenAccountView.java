package bankybc;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JEditorPane;
import java.awt.SystemColor;

public class OpenAccountView {

	public JFrame frameOpenAccountView;
	public JTextField textName;
	public JTextField textAddr; 
	public JTextField textDOB;
	public JTextField textPIN;
	public JTextField textPINAGAIN;
	public JTextField textPAmount;
	public int typeNum = 1;
	

	

	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OpenAccountView window = new OpenAccountView();
					window.frameOpenAccountView.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public OpenAccountView() {
		initialize();
	
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frameOpenAccountView = new JFrame();
		frameOpenAccountView.setBounds(100, 100, 645, 635);
		frameOpenAccountView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameOpenAccountView.getContentPane().setLayout(null);
		frameOpenAccountView.setLocationRelativeTo(null);
		
		JLabel label = new JLabel("Banking_SystemX");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setForeground(new Color(0, 153, 255));
		label.setFont(new Font("Arial Black", Font.BOLD | Font.ITALIC, 18));
		label.setBackground(Color.WHITE);
		label.setBounds(10, 8, 251, 84);
		frameOpenAccountView.getContentPane().add(label);
		
		JLabel lblOpenAccount = new JLabel("Open Account");
		lblOpenAccount.setHorizontalAlignment(SwingConstants.CENTER);
		lblOpenAccount.setFont(new Font("Trajan Pro", Font.BOLD, 16));
		lblOpenAccount.setBounds(364, 32, 236, 46);
		frameOpenAccountView.getContentPane().add(lblOpenAccount);
		
		JLabel lblNewLabel = new JLabel("Please input your information below:");
		lblNewLabel.setFont(new Font("Bookman Old Style", Font.BOLD, 16));
		lblNewLabel.setBounds(46, 172, 367, 51);
		frameOpenAccountView.getContentPane().add(lblNewLabel);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setFont(new Font("Arial", Font.BOLD, 14));
		lblName.setBounds(46, 240, 113, 33);
		frameOpenAccountView.getContentPane().add(lblName);
		
		JLabel lblAddress = new JLabel("Address:");
		lblAddress.setFont(new Font("Arial", Font.BOLD, 14));
		lblAddress.setBounds(46, 298, 113, 33);
		frameOpenAccountView.getContentPane().add(lblAddress);
		
		JLabel lblDateOfBirth = new JLabel("Date of birth:");
		lblDateOfBirth.setFont(new Font("Arial", Font.BOLD, 14));
		lblDateOfBirth.setBounds(323, 240, 113, 33);
		frameOpenAccountView.getContentPane().add(lblDateOfBirth);
		
		JLabel lblAccounttype = new JLabel("AccountType:");
		lblAccounttype.setFont(new Font("Arial", Font.BOLD, 14));
		lblAccounttype.setBounds(323, 298, 113, 33);
		frameOpenAccountView.getContentPane().add(lblAccounttype);
		
		textName = new JTextField();
		textName.setBounds(131, 241, 170, 33);
		frameOpenAccountView.getContentPane().add(textName);
		textName.setColumns(10);
		
		textAddr = new JTextField();
		textAddr.setColumns(10);
		textAddr.setBounds(131, 299, 170, 33);
		frameOpenAccountView.getContentPane().add(textAddr);
		
		textDOB = new JTextField();
		textDOB.setColumns(10);
		textDOB.setBounds(446, 241, 170, 33);
		frameOpenAccountView.getContentPane().add(textDOB);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"SaverAccount", "JuniorAccount", "CurrentAccount"}));
		comboBox.setBounds(446, 304, 170, 23);
		frameOpenAccountView.getContentPane().add(comboBox);
	
		
		JLabel lblPin = new JLabel("PIN:");
		lblPin.setFont(new Font("Arial", Font.BOLD, 14));
		lblPin.setBounds(46, 348, 113, 33);
		frameOpenAccountView.getContentPane().add(lblPin);
		lblPin.setVisible(false);
		
		JLabel lblPinConfirm = new JLabel("PIN Again:");
		lblPinConfirm.setFont(new Font("Arial", Font.BOLD, 14));
		lblPinConfirm.setBounds(46, 402, 113, 33);
		frameOpenAccountView.getContentPane().add(lblPinConfirm);
		lblPinConfirm.setVisible(false);
		
		JLabel lblPrestoredAmount = new JLabel("PreStored Amount:");
		lblPrestoredAmount.setFont(new Font("Arial", Font.BOLD, 14));
		lblPrestoredAmount.setBounds(46, 463, 158, 33);
		frameOpenAccountView.getContentPane().add(lblPrestoredAmount);
		lblPrestoredAmount.setVisible(false);
		
		textPIN = new JTextField();
		textPIN.setColumns(10);
		textPIN.setBounds(131, 349, 170, 33);
		frameOpenAccountView.getContentPane().add(textPIN);
		textPIN.setVisible(false);
		
		textPINAGAIN = new JTextField();
		textPINAGAIN.setColumns(10);
		textPINAGAIN.setBounds(131, 403, 170, 33);
		frameOpenAccountView.getContentPane().add(textPINAGAIN);
		textPINAGAIN.setVisible(false);
		
		textPAmount = new JTextField();
		textPAmount.setColumns(10);
		textPAmount.setBounds(189, 464, 170, 33);
		frameOpenAccountView.getContentPane().add(textPAmount);
		textPAmount.setVisible(false);
		
		
		//Submit button
		
		JButton btnSubmit = new JButton("Subit");
		btnSubmit.setEnabled(false);
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OpenControl openAccount = new OpenControl();  
				String name = textName.getText();
				String address= textAddr.getText();                            
				String birth= textDOB.getText();  
				//get time
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				Date now=new Date();
				String openTime = df.format(now).toString();
				String pin1= textPIN.getText();                            
				String pin2= textPINAGAIN.getText();  
				String balance= textPAmount.getText();
				
				// multiple choose
				String type=(String)comboBox.getSelectedItem();
				if(type=="SaverAccount"){
					typeNum = 1;
				}else if(type=="JuniorAccount"){
					typeNum = 2;
				}else {
					typeNum = 3;
				}
				
				Account temp = null;
				
				
				int verify2 = openAccount.PINVerify(pin1, pin2, balance);
	        	if(verify2 == 0) {
	        		JOptionPane.showMessageDialog(null, "Your information can't have blank.",  birth, JOptionPane.ERROR_MESSAGE); 
	        	}else if(verify2 == -1) {
	        		JOptionPane.showMessageDialog(null, "Your pins are different.",  birth, JOptionPane.ERROR_MESSAGE); 
	        	}else if(verify2 == -2) {
	        		JOptionPane.showMessageDialog(null, "Your balance needs to be numbers.",  birth, JOptionPane.ERROR_MESSAGE); 
	        	}else if(verify2 == -3) {
	        		JOptionPane.showMessageDialog(null, "Your PIN needs to be numbers.",  birth, JOptionPane.ERROR_MESSAGE); 
	        	}else if(verify2 == -4) {
	        		JOptionPane.showMessageDialog(null, "Your preStored amount must be at least 200.",  birth, JOptionPane.ERROR_MESSAGE); 
	        	}else {
	        		
	        		double balances = Double.parseDouble(balance);
	        		temp = openAccount.openaccount(typeNum, name, address, birth, openTime, pin1, balances);
	        		JOptionPane.showMessageDialog(null, "You open your new account successfully!\n Your account number is "+ temp.getAccNum() + "\n Please keep in mind!!!");
	        		try {
	        			openAccount.addNewUser(temp);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
	        		
	        		frameOpenAccountView.dispose();
	        		BankView ob = new BankView();
					ob.frameBankView.setVisible(true);
	        	}
				
			}
		});
		btnSubmit.setBounds(458, 419, 142, 46);
		frameOpenAccountView.getContentPane().add(btnSubmit);
		
		
		//Quit Button
		
		JButton btnQuit = new JButton("Quit");
		btnQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frameOpenAccountView.dispose();
				BankView ob = new BankView();
				ob.frameBankView.setVisible(true);
				
			}
		});
		btnQuit.setBounds(458, 482, 142, 46);
		frameOpenAccountView.getContentPane().add(btnQuit);
		
		
		
		//Confirm Button
		
		JButton btnConfirm = new JButton("Confirm");
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
			    // multiple choose
				String type=(String)comboBox.getSelectedItem();
				if(type=="SaverAccount"){
					typeNum = 1;
				}else if(type=="JuniorAccount"){
					typeNum = 2;
				}else {
					typeNum = 3;
				}
				
				OpenControl openaccount = new OpenControl();  
				String name = textName.getText();
				String address= textAddr.getText();                            
				String birth= textDOB.getText();  
				int verify1 = openaccount.Verify1(name,address,birth,typeNum);
				//System.out.print(typeNum);
				
	        	if(verify1 == 0) {
	        		JOptionPane.showMessageDialog(null, "Your information can't have blank.",  birth, JOptionPane.ERROR_MESSAGE); 
	        	}else if(verify1 == -1){
	        		JOptionPane.showMessageDialog(null, "Your Name must be a string.",  birth, JOptionPane.ERROR_MESSAGE);
	        	}else if(verify1 == -2){
	        		JOptionPane.showMessageDialog(null, "Your Address must be made up of letters and numbers.",  birth, JOptionPane.ERROR_MESSAGE);
	        	}else if(verify1 == 1) {
	        		if(openaccount.agencyVerify(birth, typeNum)) {
	        			btnConfirm.setVisible(false);
	    				textPIN.setVisible(true);
	    				textPINAGAIN.setVisible(true);
	    				textPAmount.setVisible(true);
	    				textAddr.setEditable(false);
	    				textDOB.setEditable(false);
	    				textName.setEditable(false);
	    				comboBox.setEnabled(false);
	    				lblPin.setVisible(true);
	    				lblPinConfirm.setVisible(true);
	    				lblPrestoredAmount.setVisible(true);
			        	JOptionPane.showMessageDialog(null, "You passed Credit Agency Verification"); 
			        	//After the first information is right, then the Submit is enabled.
			        	btnSubmit.setEnabled(true);
	        		}else {
	        			JOptionPane.showMessageDialog(null, "Only under 16 years old can open Junior account"); 
		        	}
		        	
	        	}else if(verify1 == 2) {
		        	
		        	JOptionPane.showMessageDialog(null, "You birth is wrong"); 
	        	}else {
	        		JOptionPane.showMessageDialog(null, "Your information is not allowed to open an account.",  birth, JOptionPane.ERROR_MESSAGE); 
	        	}
				
	        
				
			}
		});
		btnConfirm.setBounds(458, 363, 142, 46);
		frameOpenAccountView.getContentPane().add(btnConfirm);
		
		JEditorPane dtrpnPleaseFirstClick = new JEditorPane();
		dtrpnPleaseFirstClick.setText("1.Please first input four basic information\r\n2.Then click Confirm to continue\r\n3.Enter your PIN number and enter again \r\n4.Then click Submit to finish");
		dtrpnPleaseFirstClick.setForeground(Color.BLACK);
		dtrpnPleaseFirstClick.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 14));
		dtrpnPleaseFirstClick.setEditable(false);
		dtrpnPleaseFirstClick.setBackground(SystemColor.controlHighlight);
		dtrpnPleaseFirstClick.setBounds(20, 77, 297, 97);
		frameOpenAccountView.getContentPane().add(dtrpnPleaseFirstClick);
		
		JEditorPane dtrpnpinNumberMust = new JEditorPane();
		dtrpnpinNumberMust.setText("1.DOB:yyyy-mm-dd!\r\n2.Address must be numbers and letters!\r\n2.PIN number must be the same!\r\n3.Pestored Amount must be at least 200!");
		dtrpnpinNumberMust.setForeground(Color.BLACK);
		dtrpnpinNumberMust.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 14));
		dtrpnpinNumberMust.setEditable(false);
		dtrpnpinNumberMust.setBackground(SystemColor.controlHighlight);
		dtrpnpinNumberMust.setBounds(326, 77, 290, 97);
		frameOpenAccountView.getContentPane().add(dtrpnpinNumberMust);
	}
}
