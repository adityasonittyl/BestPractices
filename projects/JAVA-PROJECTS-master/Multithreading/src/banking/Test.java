package banking;

public class Test {

	public static void main(String[] args) {

		final Account account = new Account();
		account.setBalance(9000);

		Thread c1 = new Customer("withdraw", account, 10000);
		Thread c2 = new Customer("deposit", account, 5000);
		c1.start();
		c2.start();
	}
}
