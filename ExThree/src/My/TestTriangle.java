package My;

public class TestTriangle {
	public static void main(String args[])
	{
		Triangle tr = new Triangle();
		double a=Math.round((Math.random()*100)*100)/100.0;
		double b=Math.round((Math.random()*100)*100)/100.0;
		double c=Math.round((Math.random()*100)*100)/100.0;
		tr.setA(a);
		tr.setB(b);
		tr.setC(c);
		tr.getArea();
	}	
}
