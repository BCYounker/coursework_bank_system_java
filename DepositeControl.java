package bankybc;
import java.util.List;


public class DepositeControl {
	/**
	 *  the verification of PIN
	 */
	public int verify1(String accountNum,List<Account> list) {
		int x = -1;
		if(accountNum.equals("") ) {
			x = 0;
		}else if(Control.findAccountNum(accountNum)) {
			x = -2;
			Account account = null;
			for(int i = 0; i < list.size(); i++) {
				if(list.get(i).getAccNum().equals(accountNum)) {
					account = list.get(i);
				}
			}
			if(account.isOverdraftState()) {
				x=1;
			}
			
		}
		return x;	
		
		
	}
	

	/**
	 *deposit verify
	 */
	public int verify2(String depositNum,String unclearedNum) {
		if(depositNum.equals("") || unclearedNum.equals("")) {
			return 0;
		}
		if((!Control.isNumeric(depositNum)) ||( !(Control.isNumeric(unclearedNum)))) {
			return -1;
		}
		return 1;
	}
	
	/**
	 * verify numbers
	 */
	public boolean verify3(double num1,double num2) {
		if(num1<num2) {
			return false;
		}
		return true;
	}
	
    /**
     * save information to TXT file
     * 
     * @param accountNum
     * @param list
     * @param deposit
     * @param uncleared_deposit
     * @return
     */
    public Account finishChange(String accountNum, List<Account> list, double deposit, double uncleared_deposit) {
		Account account = null;
		for(int i = 0; i < list.size(); i++) {
			if(list.get(i).getAccNum().equals(accountNum)) {
				account = list.get(i);
			}
		}
		account.setBalance(account.getBalance() + deposit - uncleared_deposit);
		account.setUncleared(account.getUncleared() + uncleared_deposit);
		return account;

	}
}
