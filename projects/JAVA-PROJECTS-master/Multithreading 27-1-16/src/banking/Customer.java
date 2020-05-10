package banking;

public class Customer extends Thread {

	Account account = null;
	int amount = 0;

	Customer(String type, Account account, int amount) {
		super(type);
		this.account = account;
		this.amount = amount;
	}

	@Override
	public void run() {
		if (Thread.currentThread().getName().equalsIgnoreCase("deposit")) {
			account.deposit(amount);
		} else if (Thread.currentThread().getName()
				.equalsIgnoreCase("withdraw")) {
			account.withdraw(amount);
		}
	}
}
