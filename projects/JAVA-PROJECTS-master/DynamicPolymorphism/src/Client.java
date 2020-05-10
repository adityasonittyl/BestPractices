import java.util.Scanner;

public class Client {

	public static double area(Shape s) {
		return s.area();
	}

	public static void main(String[] args) {

		Scanner s = new Scanner(System.in);
		Shape shape;

		System.out.println("Enter radius of circle");

		shape = new Circle(s.nextDouble());
		System.out.println(Client.area(shape));

		System.out.println("Enter length and width");
		shape = new Rectangle(s.nextDouble(), s.nextDouble());
		System.out.println(Client.area(shape));

	}

}
