package bankybc;

public class SaverAccount extends Account {
	
	public SaverAccount() {
		
	} 
	public SaverAccount(String accNum, String accName,String address,String birth,String openTime,String pin,double balance) {
			this.type = 1;
			this.accNum = accNum;
			this.accName = accName;
			this.address = address;
			this.birth = birth;
			this.openTime = openTime;
			this.pin = pin;
			this.balance = balance;
			this.overdraftState= true;
			this.uncleared = 0;
			
		}
	@Override
	public String toString() {
		return "SaverAccount [type=" + type + ", accNum=" + accNum + ", address=" + address + ", accName=" + accName
				+ ", pin=" + pin + ", birth=" + birth + ", openTime=" + openTime + ", uncleared=" + uncleared
				+ ", balance=" + balance + ", overdraftState=" + overdraftState + "]";
	}
	 
}
