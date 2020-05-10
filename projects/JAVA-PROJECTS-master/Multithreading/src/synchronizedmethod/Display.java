package synchronizedmethod;

public class Display {

	public synchronized void wish(String name) {
		for (int i = 1; i <= 10; i++) {
			System.out.print("Good morning:");
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
			
			}

			System.out.println(name);

		}
	}
}
