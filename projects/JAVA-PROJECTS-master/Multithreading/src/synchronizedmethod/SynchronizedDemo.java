package synchronizedmethod;

public class SynchronizedDemo {

	public static void main(String[] args) {
		Display d1 = new Display();
		Display d2 = new Display();
		
		Thread t1 = new MyThread(d1, "AKASH");
		Thread t2 = new MyThread(d1, "AVINANDAN");
		
		t1.start();
		t2.start();
	}

}
