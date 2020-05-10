package yielding;

public class MyThread extends Thread {

	@Override
	public void run() {
		super.run();
		for (int i = 1; i <= 10; i++) {
		
				Thread.yield();
				System.out.println("Child");
			
		}
	}
}
