package synchronizedmethod;

public class MyThread extends Thread {

	Display d;
	String name;

	public MyThread(Display d, String name) {
		super();
		this.d = d;
		this.name = name;
	}

	@Override
	public void run() {
		d.print(name);
	}
}
