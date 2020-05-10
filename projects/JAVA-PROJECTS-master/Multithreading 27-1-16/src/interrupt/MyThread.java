package interrupt;

public class MyThread extends Thread {

	@Override
	public void run() {
		super.run();
		for (int i = 1; i <= 10; i++) {
			try {
				System.out.println("Lazy thread");
				Thread.sleep(5000);
			} catch (Exception e) {
				System.out.println("Interrupted");
			}
		}
	}
}
