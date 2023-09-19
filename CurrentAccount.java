package bankybc;

public class CurrentAccount extends Account{
	
	/**
	 * @param type=3
	 * @param accNum
	 * @param accName
	 * @param pin
	 * @param balance
	 * @param birth 
	 * @param address 
	 */
	public CurrentAccount() {
		
	}
	public CurrentAccount (String accNum, String accName,String address,String birth,String openTime,String pin,double balance) {
		this.type=3;
		this.accNum = accNum;
		this.accName = accName;
		this.address = address;
		this.birth = birth;
		this.openTime=openTime;
		this.pin = pin;
		this.balance = balance;
		this.overdraftState= true;
		this.uncleared = 0;
	}
	@Override
	public String toString() {
		return "CurrentAccount [type=" + type + ", accNum=" + accNum + ", address=" + address + ", accName=" + accName
				+ ", pin=" + pin + ", birth=" + birth + ", openTime=" + openTime + ", uncleared=" + uncleared
				+ ", balance=" + balance + ", overdraftState=" + overdraftState + "]";
	}
	
	
	

}