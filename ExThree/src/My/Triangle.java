package My;

public class Triangle {
		private double a,b,c,p;
		public void setA(double a)
		{
			this.a=a;
		}
		public void setB(double b)
		{
			this.b=b;
		}
		public void setC(double c)
		{
			this.c=c;
		}
		public double getA()
		{
			return a;
		}
		public double getB()
		{
			return b;
		}
		public double getC()
		{
			return c;
		}
		private boolean isTriangle()
		{
			if(a+b>c&&a+c>b&&c+b>a)
				return true;
			else return false;
		}
		public void getArea()
		{
			if(isTriangle()==true)
			{
				
				p=(a+b+c)/2;
				double sum=Math.sqrt(p*(p-a)*(p-b)*(p-c));
				sum=Math.round(sum*100)/100.0;
				System.out.println("边长为"+a+","+b+"和"+c+"的三条边构成"+"的三角形面积是："+sum);
			}
			else
			{
				System.out.println("边长为"+a+","+b+"和"+c+"的三条边无法构成三角形");
			}
		}
}



