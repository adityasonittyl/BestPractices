package interthreadcommunication;

public class ThreadB extends ThreadA {
	int total = 0;

	public void run() {
		synchronized (this) {
			System.out.println("Child Thread Starts notification");
			for (int i = 1; i <= 10; i++) {
				total += i;
			}
			System.out.println("Child thread trying to give notification");
			this.notify();
		}
	}
}
