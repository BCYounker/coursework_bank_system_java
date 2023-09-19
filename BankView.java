package bankybc;

import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JEditorPane;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import java.awt.SystemColor;

/**
 * This is the entrance of the whole system
 * @author Bochen Yang
 */

public class BankView {

	public JFrame frameBankView;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BankView window = new BankView();
					window.frameBankView.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public BankView() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frameBankView = new JFrame();
		frameBankView.setBounds(100, 100, 636, 636);
		frameBankView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameBankView.getContentPane().setLayout(null);
		frameBankView.setLocationRelativeTo(null);
		
		JLabel label = new JLabel("Banking_SystemX");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setForeground(new Color(0, 153, 255));
		label.setFont(new Font("Arial Black", Font.BOLD | Font.ITALIC, 18));
		label.setBackground(Color.WHITE);
		label.setBounds(23, 10, 251, 84);
		frameBankView.getContentPane().add(label);
		
		JLabel lblWelcomePage = new JLabel("Welcome Page");
		lblWelcomePage.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcomePage.setFont(new Font("Trajan Pro", Font.BOLD, 16));
		lblWelcomePage.setBounds(306, 34, 236, 46);
		frameBankView.getContentPane().add(lblWelcomePage);
		
		JButton btnNewButton = new JButton("Open Account");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frameBankView.dispose();
				OpenAccountView ob = new OpenAccountView();
				ob.frameOpenAccountView.setVisible(true);				
			}
		});
		btnNewButton.setBounds(49, 257, 213, 46);
		frameBankView.getContentPane().add(btnNewButton);
		
		JButton btnClearFunds = new JButton("Clear Funds");
		btnClearFunds.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frameBankView.dispose();
				ClearView ob = new ClearView();
				ob.frameClearView.setVisible(true);
			}
		});
		btnClearFunds.setBounds(49, 344, 213, 46);
		frameBankView.getContentPane().add(btnClearFunds);
		
		JButton btnSuspendAccount = new JButton("Suspend Account");
		btnSuspendAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frameBankView.dispose();
				SuspendView ob = new SuspendView();
				ob.frameSuspendView.setVisible(true);
			}
		});
		btnSuspendAccount.setBounds(49, 436, 213, 46);
		frameBankView.getContentPane().add(btnSuspendAccount);
		
		JButton btnDepositFunds = new JButton("Deposit Funds");
		btnDepositFunds.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frameBankView.dispose();
				DepositeView ob = new DepositeView();
				ob.frameDepositeView.setVisible(true);
			}
		});
		btnDepositFunds.setBounds(329, 257, 213, 46);
		frameBankView.getContentPane().add(btnDepositFunds);
		
		JButton btnWithdrawFunds = new JButton("Withdraw Funds");
		btnWithdrawFunds.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frameBankView.dispose();
				WithdrawView ob = new WithdrawView();
				ob.frameWithdrawView.setVisible(true);
			}
		});
		btnWithdrawFunds.setBounds(329, 344, 213, 46);
		frameBankView.getContentPane().add(btnWithdrawFunds);
		
		JButton btnCloseAccount = new JButton("Close Account");
		btnCloseAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frameBankView.dispose();
				CloseView ob = new CloseView();
				ob.frameCloseView.setVisible(true);
			}
		});
		btnCloseAccount.setBounds(329, 436, 213, 46);
		frameBankView.getContentPane().add(btnCloseAccount);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Information", TitledBorder.LEADING, TitledBorder.TOP, null, Color.MAGENTA));
		panel.setBounds(43, 72, 505, 163);
		frameBankView.getContentPane().add(panel);
		panel.setLayout(null);
		
		JEditorPane dtrpnWelcomeYouTo = new JEditorPane();
		dtrpnWelcomeYouTo.setBackground(SystemColor.controlHighlight);
		dtrpnWelcomeYouTo.setForeground(Color.BLACK);
		dtrpnWelcomeYouTo.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 14));
		dtrpnWelcomeYouTo.setEditable(false);
		dtrpnWelcomeYouTo.setBounds(6, 17, 493, 136);
		panel.add(dtrpnWelcomeYouTo);
		dtrpnWelcomeYouTo.setText("Welcome you to use Banking_SystemX!\r\n1. Our system has six functions that you can use.\r\n2.There are three Account Type you can choose.\r\n3.All your information will be saved into txt files.\r\n4.We will keep your information private.\r\n5.Thanks for using our system and best wishes!");
	}
}
