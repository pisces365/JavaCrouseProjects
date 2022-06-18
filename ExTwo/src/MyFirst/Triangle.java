package MyFirst;

public class Triangle {
	private double a,b,c,p,min;
	public Triangle()
	{
		a=Math.random()*100;
		b=Math.random()*100;
		c=Math.random()*100;
		p=0;
		min=0;
		System.out.println(this.a+" "+this.b+" "+this.c);
	}
	public double calculate()
	{
		if((a+b)>c&&(a+c)>b&&(c+b)>a)
		{
			p=(a+b+c)/2;
			return Math.sqrt(p*(p-a)*(p-b)*(p-c));
		}
		else 
		{
			min=a<b?(a<c?a:c):(b<c?b:c);
			System.out.println(min);
			p=(3*min)/2;
			return Math.sqrt(p*(p-min)*(p-min)*(p-min));
		}
	}
	
	public static void main(String args[])
	{
		Triangle tag =new Triangle();
		System.out.println(tag.calculate());
	}
}
