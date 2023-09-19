package bankybc;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.swing.JOptionPane;



	


public class OpenControl {
	
	public boolean isHave(String[] strs, String s) {
		for(int i=0;i<strs.length;i++) {
			if(strs[i].indexOf(s)!=-1) {
				return true;}
		}
			return false;
	}
		
	public int Verify1(String name, String address, String birth, int type) {
	String months[] = {"01","03","05","07","08","10","12"};
	
	if(name.equals("")||address.equals("")||birth.equals("")) {
		return 0;
	}
	
	if(!Control.isCharacter(name)) {
		return -1;
	}
	
	if(!Control.isAddress(address)) {
		return -2;
	}
	
	try {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date date = df.parse(birth);
		Date now=new Date();
		
		if(now.before(date)) {
			// verify whether the Date of Birth is ahead the current data
			JOptionPane.showConfirmDialog(null,"Birthday fill in error", "prompt",
					JOptionPane.WARNING_MESSAGE);
			return 2;
		} else if(Integer.parseInt(birth.split("-")[1])== 02 && Integer.parseInt(birth.split("-")[2]) > 28) {
			JOptionPane.showConfirmDialog(null,"Wrong input date.", "prompt",
					JOptionPane.WARNING_MESSAGE);
			return 2;
		} else if(isHave(months,birth.split("-")[1]) && Integer.parseInt(birth.split("-")[2]) > 31) {
			JOptionPane.showConfirmDialog(null,"Wrong input date.", "prompt",
					JOptionPane.WARNING_MESSAGE);
			return 2;
		}else if(!isHave(months,birth.split("-")[1]) && Integer.parseInt(birth.split("-")[2]) > 30) {
			JOptionPane.showConfirmDialog(null,"Wrong input date.", "prompt",
					JOptionPane.WARNING_MESSAGE);
			return 2;
		}
		
	}
	catch (ParseException e1) {
		JOptionPane.showConfirmDialog(null,"Date format is yyyy-MM-dd", "prompt",
				JOptionPane.WARNING_MESSAGE);
		return 2;
	}
	
	return 1;
	}
	
    /**
	 *  Verify credit age                      
	 */
	public boolean agencyVerify(String birth,int type) {
		SimpleDateFormat dataformat = new SimpleDateFormat("yyyy-MM-dd");
		String nowDate = dataformat.format(new Date());
		int currentYear = Integer.parseInt(nowDate.split("-")[0]);  // get system year
		
		if(type == 2) {
			int births = Integer.parseInt(birth.split("-")[0]);  //get user input year
			if(currentYear - births > 16)
			return false; 
		}
		
		return true;
	}
	
	
	
	/**
	 * PIN verify
	 */
	public int PINVerify(String pin1,String pin2,String balance) {
		if(pin1.equals("")||pin2.equals("")||balance.equals("")) {
			return 0;
		}
		if(!Control.isNumeric(pin1)) {
			return -3;
		}
		if(!(pin1.equals(pin2))) {
			return -1;
		}
		if(!Control.isNumeric(balance)) {
			return -2;
		}
		if(Integer.valueOf(balance) < 200) {
			return -4;
		}
		
		return 1;
	}
	
	
	/**
	 * open account class
	 */
	
	public Account openaccount(int type,String name,String address,String birth,String openTime,String pin,double balance) {
		String accountNum = createNum();
		try {
			addAccountNum(accountNum);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		Account new_account = null;
		if(type == 1){
			new_account = new SaverAccount(accountNum,name,address,birth,openTime,pin,balance);          
		}else if(type ==2) {
			new_account = new JuniorAccount(accountNum,name,address,birth,openTime,pin,balance);
		}else if(type ==3) {
			new_account = new CurrentAccount(accountNum,name,address,birth,openTime,pin,balance);
		}else {
			System.out.println("There is some problems.");
		}
		
		return new_account;
	}
	
	
	/**
     * create an unique accNum(expected unique, I adopt XXXX as accNum)
     * @return accNum
     */
    public String createNum(){
		int num=0;
		String accountNum="";
		Random randval=new Random();
		for(int count=1; count<=4; count++){
			num=randval.nextInt(9)+1; 
			accountNum+=Integer.toString(num);
		}
		return accountNum;
	}
	
	
	 /**
     * save the account number
     * @param accountNum
     * @throws IOException
     */
    public void addAccountNum(String accountNum) throws IOException{
		
		File fout = new File("Account Number.txt");
		try {
			FileOutputStream fos = new FileOutputStream(fout,true);
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));      
			bw.write(accountNum);
			bw.newLine();       
			bw.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
        
	}
    
	
    /**
     * add new user to the system
     * @param account
     * @throws IOException
     */
    public void addNewUser(Account account) throws IOException{        
		File outFile = new File("Account Information.txt");

		Writer out;
		try {
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile,true), "utf-8"), 10240);
		        
	        out.write(
		                "\n[type]"+account.getType()+"\r\n[AccountNumber]"+account.getAccNum()+"\r\n[name]"
		                +account.getAccName()+"\r\n[address]"+account.getAddress()+"\r\n[birth]"+account.getBirth()
		                +"\r\n[openTime]"+account.getOpenTime()+"\r\n[pin]"+account.getPin()+"\r\n[balance]"+account.getBalance()+"\r\n[state]"+account.overdraftState+"\r\n[unclear]"+account.uncleared+"\r\n");
		    out.write("\r\n");
		        
		    out.flush();
		    out.close();
		    } catch (Exception e1) {
		        e1.printStackTrace();
		    }    
        
	}
}
