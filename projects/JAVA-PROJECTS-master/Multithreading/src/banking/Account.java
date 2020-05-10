package banking;

public class Account {

	int balance;

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public void transaction() {
		synchronized (this) {

			
		}
	}

	public synchronized void withdraw(int amount) {
		if (amount > balance) {
			System.out.println("Insufficient balance");
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			balance = balance - amount;
			System.out.println("Amount withdrawn " + amount);
			System.out.println("Balance " + balance);

		}
	}

	public synchronized void deposit(int amount) {
		balance = balance + amount;
		System.out.println("Amount deposited " + amount);
		System.out.println("Balance " + balance);
		notify();
	}
}
