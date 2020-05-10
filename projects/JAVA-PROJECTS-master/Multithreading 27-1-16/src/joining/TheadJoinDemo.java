package joining;

public class TheadJoinDemo {
	public static void main(String[] args) throws InterruptedException {
		MyThread t1 = new MyThread();
		t1.start();
		t1.join();

		for (int i = 1; i < 10; i++) {
			System.out.println("Main");
		}
	}

}
