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
		System.out.println("�����ε�����Ϊ��"+a+" "+b+" "+c);
		Triangle tr = new Triangle(a,b,c);
		try
		{
			System.out.println("�����ε�״̬Ϊ��"+tr.isTriangle());
			if(!tr.isTriangle()) throw new IllegalArgumentException("����������������ܹ���������");
		}
		catch(IllegalArgumentException iae)
		{
			System.out.println(iae.getMessage());
		}
	}
}
