package sleeping;

public class Test {


	public static void main(String[] args) {
		try {
			System.out.println("hi");
			Thread.sleep(2000);
			System.out.println("hello");
			Thread.sleep(2000);
			System.out.println("bye");
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
