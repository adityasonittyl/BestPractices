package synchronizedmethod;

public class Test {

	
	public static void main(String[] args) {
		Display d = new Display();
		MyThread t1 = new MyThread(d, "AKASH");
		MyThread t2 = new MyThread(d, "SKY");
		t1.start();
		t2.start();
	}

}
