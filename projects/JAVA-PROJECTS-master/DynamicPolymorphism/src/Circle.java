public class Circle extends Shape {

	private double radius;
	private final double pi = 3.14; 

	
	public Circle() {
		super();
		radius=0;
	}

	public Circle(double radius) {
		super();
		this.radius = radius;
	}

	public double getRadius() {
		return radius;
	}

	public void setRadius(double radius) {
		this.radius = radius;
	}

	@Override
	public double area() {
		return pi * radius * radius;
	}

}
