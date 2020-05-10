package interthreadcommunication;

public class ThreadA extends Thread {
	public static void main(String[] args) throws InterruptedException {
		ThreadB b = new ThreadB();
		b.start();
		//Thread.sleep(2000);
		
		synchronized (b) {
			System.out.println("Main thread trying to call wait");
			b.wait(1000);
			System.out.println("Main thread got notification");
			System.out.println(b.total);
		}
	}
}
