package pisces;

import java.util.*;
abstract class Shape
{
	public abstract double getArea();
}

class Circle extends Shape
{
	double r;
	public Circle(double rp)
	{
		r = rp;
	}
	public double getArea()
	{
		if(r>0)
		return Math.PI*r*r;
		else throw new IllegalArgumentException("输入的参数不能构成圆形");
	}
}
class Rectangle extends Shape
{
	double l,w;
	public Rectangle(double lp,double wp)
	{
		l = lp;
		w = wp;
	}
	public double getArea()
	{
		if(l>0&&w>0)
		return l*w;
		else throw new IllegalArgumentException("输入的参数不能构成长方形");
	}
}
class Trianglet extends Shape
{
	double a,b,c;
	public Trianglet(double ap,double bp,double cp)
	{
		a = ap;
		b = bp;
		c = cp;
	}
	public double getArea()
	{
		if((a+b)>c&&(a+c)>b&&(c+b)>a)
		{
			double p=(a+b+c)/2;
			return Math.sqrt(p*(p-a)*(p-b)*(p-c));
		}
		else
		{
			throw new IllegalArgumentException("输入的三个参数不能构成三角形");
		}
	}
}

public class TeatShape {
	public static void main(String args[])
	{
		try
		{
			Shape sh ;
			System.out.println("请输入参数：");
			Scanner sc = new Scanner(System.in);
			String str = sc.nextLine();
			if(str.length()==0) throw new Exception("参数错误或参数个数错误");
			String spl[] = str.split(" ");
			double arr[] = new double [spl.length];
			for(int i=0;i<spl.length;++i)
			{
				arr[i] = Integer.parseInt(spl[i]);
			}
		
		
			if(spl.length==3)
			{
				sh = new Trianglet(arr[0],arr[1],arr[2]);
				System.out.println("三角形的面积为："+sh.getArea());
			}
			else if(spl.length==2)
			{
				sh = new Rectangle(arr[0],arr[1]);
				System.out.println("长方形的面积为："+sh.getArea());
			}
			else if(spl.length==1)
			{
				sh = new Circle(arr[0]);
				System.out.println("圆形的面积为："+sh.getArea());
			}
			else
			{
				throw new Exception("参数错误或参数个数错误");
			}

		}
		catch(IllegalArgumentException iae)
		{
			System.out.println(iae.getMessage());
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
}
