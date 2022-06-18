package MyFirst;

public class Complex {
	public int realPart,imaginPart;
	public Complex()
	{
		realPart = 0;
		imaginPart = 0;
	}
	public Complex(int r,int i)
	{
		realPart = r;
		imaginPart = i;
	}
	public Complex complexADD(Complex a)
	{
		Complex co = new Complex();
		co.realPart=this.realPart+a.realPart;
		co.imaginPart=this.imaginPart+a.imaginPart;
		return co;
	}
	public String toString()
	{
		String st=""+realPart+"+"+imaginPart+"*i";
		return st;
	}
	
	public static void main(String args[])
	{
		Complex c1 =new Complex(1,2);
		Complex c2 =new Complex(3,4);
		Complex c3 =new Complex();
		c3=c1.complexADD(c2);
		System.out.println(c3.toString());
	}
}
