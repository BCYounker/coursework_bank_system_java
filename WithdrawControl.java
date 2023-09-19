package bankybc;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class WithdrawControl {
	/**
	 * pin verify
	 */
	public int verify1(String accountNum, String pin,List<Account> list) {
		int x = -1;
		if(accountNum.equals("") || pin.equals("")) {
			x = 0;
		}else if(Control.findAccountNum(accountNum)) {
			x = -2;
			Account account = null;
			for(int i = 0; i < list.size(); i++) {
				if(list.get(i).getAccNum().equals(accountNum)) {
					account = list.get(i);
				}
			}
			if(account.overdraftState) {
				x = -3;
				if(account.getPin().equals(pin)) {
					x = 1;
				}
			}
		}
		return x;	
	}
	
    /**
     * 
     * @param accountNum
     * @param list
     * @param withdrawNum
     * @return
     */
    public int finishWithdraw(String accountNum, List<Account> list, String withdrawNum) {
		int x = 1;
		if(accountNum.equals("")||withdrawNum.equals("")) {
			x = 0;
		}else if(!Control.isNumeric(withdrawNum)) {
			x = -2;
		}else {
			Account account = null;
			for(int i = 0; i < list.size(); i++) {
				if(list.get(i).getAccNum().equals(accountNum)) {
					account = list.get(i);
				}
			}
			double withdraw = Double.parseDouble(withdrawNum);
			
			//Compare time
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			Date now=new Date();
			String currentTime = df.format(now).toString();

			if(account.getType() == 1) {// if is SaverAccount
				if(Control.getDayBetweenTwoDate(account.getOpenTime(), currentTime) <= 7) {
	        		x = -4;
				}
			}
			//verify whether openTime and withDraw time difference is larger than 7 days
			if(account.getBalance() < withdraw) {
				x = -1;
				if(account.getType()==3) {// if it is currentAccount
					if(withdraw - account.getBalance() <=500) {
					x = 1;
					}else {
						x = -3;   //Overdraft limit is 500
					}
				
				}	
			}
		}
		
		return x;
	}
	
    /**
     *save the change information to the .txt
     * @param accountNum
     * @param list
     * @param withdraw
     * @return
     */
    public Account finishWithdraw(String accountNum, List<Account> list, double withdraw) {
		Account account = null;
		for(int i = 0; i < list.size(); i++) {
			if(list.get(i).getAccNum().equals(accountNum)) {
				account = list.get(i);
			}
		}
		account.setBalance(account.getBalance() - withdraw);
		
		return account;

	}
}
