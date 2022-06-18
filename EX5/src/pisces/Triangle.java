package pisces;

public class Triangle {
	double a,b,c;
	public Triangle(double ap,double bp,double cp)
	{
		a = ap;
		b = bp;
		c = cp;
	}
	public boolean isTriangle()
	{
		if((a+b)>c&&(a+c)>b&&(c+b)>a)
		{
			return true;
		}
		else 
		{
			return false;
			
		}
	}
	
	public static void main(String args[])
	{
		double a = Math.random()*100;
		double b = Math.random()*100;
		double c = Math.random()*100;
		System.out.println("三角形的三边为："+a+" "+b+" "+c);
		Triangle tr = new Triangle(a,b,c);
		try
		{
			System.out.println("三角形的状态为："+tr.isTriangle());
			if(!tr.isTriangle()) throw new IllegalArgumentException("输入的三个参数不能构成三角形");
		}
		catch(IllegalArgumentException iae)
		{
			System.out.println(iae.getMessage());
		}
	}
}
