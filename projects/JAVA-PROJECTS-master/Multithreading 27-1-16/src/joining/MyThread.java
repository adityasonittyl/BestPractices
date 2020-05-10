package joining;

public class MyThread extends Thread {

	@Override
	public void run() {
		super.run();
		for (int i = 1; i <= 10; i++) {
			System.out.println("Child");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
