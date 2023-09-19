package bankybc;

import java.io.*;
import java.util.*;
import java.text.*;

public class Control {
	
	/**
     * determine whether the string is numeric
     * @param str
     * @return
     */
    public static boolean isNumeric(String str){
	    for (int i = str.length();i>=0;i--){  
	        if (!Character.isDigit(str.charAt(i))){
	            return false;
	        }
	   }
	  return true;
	 }
    
	/**
     * determine whether the string is all Character
     * @param str
     * @return
     */
    public static boolean isCharacter(String str){
	    for (int i = str.length();i>=0;i--){  
	        if (!Character.isLetter(str.charAt(i))){
	            return false;
	        }
	    }
	  return true;
	 }
    
    /**
     * use simple method to verify whether the string is address
     * @param str
     * @return boolean
     */
    public static boolean isAddress(String str) {
	    boolean Digitflag = false;//define a boolean value, to verify whether it contains number
	    boolean Letterflag = false;//define a boolean value, to verify whether it contains letter

	    for (int i = 0; i < str.length(); i++) {
	        if (Character.isDigit(str.charAt(i))) { 
	           Digitflag = true;
	        } else if (Character.isLetter(str.charAt(i))) {
	            Letterflag = true;
	        } 
	    }
	    String regex = "^[a-zA-Z0-9]{1,100}$";
	    boolean isRight = Digitflag && Letterflag && str.matches(regex);
	    return isRight; 
	}
    /**
     * Calculate the number of days between the two dates
     * @param String String
     * @return integer
     */
    public static int getDayBetweenTwoDate(String startDateStr, String endDateStr) {
        SimpleDateFormat DateFormat = new SimpleDateFormat("yyyy-MM-dd");
        int days = 0;
        try {
            Date startDate = DateFormat.parse(startDateStr);
            Date endDate = DateFormat.parse(endDateStr);
            Calendar startCalendar = Calendar.getInstance();
            startCalendar.setTime(startDate);
            Calendar endCalendar = Calendar.getInstance();
            endCalendar.setTime(endDate);
            days = ((int) (startCalendar.getTime().getTime() / 1000) - (int) (endCalendar.getTime().getTime() / 1000)) / (86400);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return days;
    }
    /**
     * determine the existence of account number
     * @param accNum
     * @return 
     */
    public static boolean findAccountNum(String accNum) {//
		boolean verifyResult=false;
		File file=new File("Account Number.txt"); 
		BufferedReader reader=null;  
		String temp=null;  
		int line=1; 
		try{  
              reader=new BufferedReader(new FileReader(file));  
              while((temp=reader.readLine())!=null){  
              	if(temp.equals(accNum)==true) {
              		verifyResult=true;
              		break;
              	}
                  line++;  
              }  
		}  
		catch(Exception e){  
          e.printStackTrace();  
		}  
		finally{  
			if(reader!=null){  
              try{  
                  reader.close();  
              }  
              catch(Exception e){  
                  e.printStackTrace();  
              }  
			}  
			}  
		
		return verifyResult;	
	}

    
    /**
     * read the TXT file into ArrayList
     * @return
     * @throws Exception
     */
    public static List<Account> readTxtData() throws Exception {

        FileInputStream fis;
        fis = new FileInputStream("Account Information.txt"); 
        BufferedReader in = new BufferedReader(new InputStreamReader(fis, "utf-8"));
        StringBuffer sb = new StringBuffer();
        String line = "";
        while ((line = in.readLine()) != null) {
            if (!line.equals("")) {
                sb.append(line);
            }
        }

        String all = sb.toString().trim();
        List<Account> list = new ArrayList<>();
        String accounts[] = all.split("\\[type\\]");

        for (int i=1;i<accounts.length;i++) {
            Account account = null;
            String type = accounts[i].substring(0, accounts[i].indexOf("[AccountNumber]"));
            if(type.equals("1")) {
            	account = new SaverAccount();
            	account.setType(1);
            }else if(type.equals("2")) {
            	account = new JuniorAccount();
            	account.setType(2);
            }else if(type.equals("3")) {
            	account = new CurrentAccount();
            	account.setType(3);
            }else {
            	System.out.println("");
            }
           

            int number_start = accounts[i].indexOf("[AccountNumber]")+15;
            int number_end = accounts[i].indexOf("[name]");
            String accNum = accounts[i].substring(number_start,number_end);
            account.setAccNum(accNum);
            
            int nane_start = accounts[i].indexOf("[name]")+6;
            int name_end = accounts[i].indexOf("[address]");
            String accName = accounts[i].substring(nane_start,name_end);
            account.setAccName(accName);
            
            int address_start = accounts[i].indexOf("[address]")+9;
            int address_end = accounts[i].indexOf("[birth]");
            String address = accounts[i].substring(address_start,address_end);
            account.setAddress(address);
            
            
            int birth_start = accounts[i].indexOf("[birth]")+7;
            int birth_end = accounts[i].indexOf("[openTime]");
            String birth = accounts[i].substring(birth_start,birth_end);
            account.setBirth(birth);
            
            int openTime_start = accounts[i].indexOf("[openTime]")+10;
            int openTime_end = accounts[i].indexOf("[pin]");
            String openTime = accounts[i].substring(openTime_start,openTime_end);
            account.setOpenTime(openTime);
            
            
            int pin_start = accounts[i].indexOf("[pin]")+5;
            int pin_end = accounts[i].indexOf("[balance]");
            String pin = accounts[i].substring(pin_start,pin_end);
            account.setPin(pin);
            
            
            int balance_start = accounts[i].indexOf("[balance]")+9;
            int balance_end = accounts[i].indexOf("[state]");
            String balance = accounts[i].substring(balance_start,balance_end);
            double balances = Double.parseDouble(balance);
            account.setBalance(balances);
            
            int state_start = accounts[i].indexOf("[state]")+7;
            int state_end = accounts[i].indexOf("[unclear]");
            String state = accounts[i].substring(state_start,state_end);
            boolean states = Boolean.parseBoolean(state);
            account.setOverdraftState(states);
            
            int unclear_start = accounts[i].indexOf("[unclear]")+9;
            String unclear = accounts[i].substring(unclear_start);
            double unclears = Double.parseDouble(unclear);
            account.setUncleared(unclears);
            
           
            list.add(account);
        }
        return list;
    }
    
    /**
	 * save the change to .txt
	 * @return 
	 */
	public static void writeTxtData(List<Account> list) throws Exception{
		File outFile = new File("Account Information.txt");

	    Writer out;
	    try { 
	        out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile), "utf-8"), 10240);

	        for(int i = 0 ; i < list.size(); i++) {
	        	out.write(
	                    "\n[type]"+list.get(i).getType()+"\r\n[AccountNumber]"+list.get(i).getAccNum()+"\r\n[name]"
	                    +list.get(i).getAccName()+"\r\n[address]"+list.get(i).getAddress()+"\r\n[birth]"+list.get(i).getBirth()
	                    +"\r\n[openTime]"+list.get(i).getOpenTime()+"\r\n[pin]"+list.get(i).getPin()+"\r\n[balance]"+list.get(i).getBalance()+"\r\n[state]"+list.get(i).overdraftState+"\r\n[unclear]"+list.get(i).uncleared+"\r\n");
	            out.write("\r\n");
	            out.flush();
	        }

	        
	        out.close();
	    } catch (Exception e1) {
	        // TODO Auto-generated catch block
	        e1.printStackTrace();
	    }
	}
	

    /**
     * get the info of this accNum
     * @param accNum
     * @param list
     * @return
     */
    public static Account showInformation(String accNum, List<Account> list) {
		Account account = null;
		for(int i = 0; i < list.size(); i++) {
			if(list.get(i).getAccNum().equals(accNum)) {
				account = list.get(i);
			}
		}
		return account;
	}
    
}
