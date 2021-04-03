package FinalPrep;

public class Circle {
	float rad = 0;
	public Circle(float i ) {
		rad = i;
	}
	public void setRad(float r) {
		rad = r;
	}
	public float getRad() {
		return rad;
	}
	public static void main (String args[]) {
		Circle circleD = new Circle(10);
		Circle circleX = new Circle(23);
		Circle circleP = circleX;
		System.out.println(circleD.getRad() + " " + circleX.getRad() 
		                                        + " " + circleP.getRad());
		circleP.setRad(15);
		circleP = circleD;
		System.out.println(circleD.getRad() + " " + circleX.getRad() 
		                                        + " " + circleP.getRad());
		circleD.setRad(17);
		circleP.setRad(9);
		System.out.println(circleD.getRad() + " " + circleX.getRad() 
		                                        + " " + circleP.getRad());
		circleX = circleP;
		System.out.println(circleD.getRad() + " " + circleX.getRad() 
		                                        + " " + circleP.getRad());
	}
}
