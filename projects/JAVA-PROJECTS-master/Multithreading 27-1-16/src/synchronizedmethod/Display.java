package synchronizedmethod;

public class Display {

	
	public synchronized void print(String name){
		for (int i = 0; i < 10; i++) {
			System.out.print("Hello: ");
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(name);
		}
	}
}
