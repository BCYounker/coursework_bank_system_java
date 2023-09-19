package bankybc;

import java.util.List;


public class ClearControl {
	/**
	 * The verification of PIN
	 * @param accountNum
	 * @param pin
	 * @param list
	 * @return
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
     * save the change information to txt
     * @param accountNum
     * @param list
     */
    public void finishChange(String accountNum, List<Account> list) {
		for(int i = 0; i < list.size(); i++) {
			if(list.get(i).getAccNum().equals(accountNum)) {
				list.get(i).setBalance(list.get(i).getBalance() + list.get(i).getUncleared());
				list.get(i).setUncleared(0);
			}
		}

	}
}
