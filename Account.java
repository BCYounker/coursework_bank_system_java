package bankybc;

/**
 * 
 * @author Bochen
 *
 */

public abstract class Account {
	int type;
	String accNum;
	String address;
	String accName;
	String pin;
	String birth;
	String openTime;
	double uncleared=0;
	double balance=0;
	boolean overdraftState=true;
	
	
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getAccNum() {
		return accNum;
	}
	public void setAccNum(String accNum) {
		this.accNum = accNum;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getAccName() {
		return accName;
	}
	public void setAccName(String accName) {
		this.accName = accName;
	}
	public String getPin() {
		return pin;
	}
	public void setPin(String pin) {
		this.pin = pin;
	}
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
	public String getOpenTime() {
		return openTime;
	}
	public void setOpenTime(String openTime) {
		this.openTime = openTime;
	}
	public double getUncleared() {
		return uncleared;
	}
	public void setUncleared(double uncleared) {
		this.uncleared = uncleared;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public boolean isOverdraftState() {
		return overdraftState;
	}
	public void setOverdraftState(boolean overdraftState) {
		this.overdraftState = overdraftState;
	}
	
	
	/**
	 out.write(
     		"\n[type]"+account.getClass().getName()+"\r\n[AccountNumber]"+account.accNum+
     		"\r\n[Name]"+account.accName+"\r\n[Balance]"+account.balance+
    		"\r\n[State]"+account.overdraftState); 
    */
	
}