package yielding;


public class Snippet {
	public static void main(String[] args) {

		Thread t = new MyThread();
		t.start();
		Thread.yield();
		
		for (int i = 1; i <= 10; i++) {
			
			System.out.println("Main Thread");
		}		
	}
}

