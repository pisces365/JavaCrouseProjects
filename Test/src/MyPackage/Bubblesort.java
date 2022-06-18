
package MyPackage;
import java.util.*;

class A
{
	public A()
	{
		System.out.print("0");
	}
}
class B extends A
{
	public B()
	{
		System.out.print("k");
	}
}

public class Bubblesort {
	public static void main1(String []args)
	{
		int a[] = {20,10,50,40,30,70,60,80,90,100};
		for(int i=0;i<a.length-1;++i)
		{
			for(int j=0;j<a.length-i-1;++j)
			{
				if(a[j]<a[j+1])
				{
					int  v=a[j];
					a[j] = a[j+1];
					a[j+1] = v;
				}
			}
		}
		for(int i=0;i<a.length;++i)
		{
			System.out.print(a[i]+" ");
		}
		System.out.print(~(0xa5)&(0xaa));
	}
	
	public static void main2(String []args)
	{
		String str = "good";
		char [] ch = {'a','b','c'};
		str.toUpperCase();//有返回值时才改变字符串，此时不会改变str原有字符大小写
		ch[1] = 'g';
		System.out.print(str + " and ");
		System.out.print(ch);;
	}
	
	public static void main(String []args)
	{
		Bbb b = new Bbb();
	}
}
