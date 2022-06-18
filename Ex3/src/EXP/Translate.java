package EXP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Translate {
	String a,b;
	int a1,b1;
	String x[]={"zero","one","two", "three","four", "five","six","seven", "eight","nine"} ;  
	String y[]={"ten","eleven","twelve","thirteen","fourteen","fifteen", 
	"sixteen","seventeen","eighteen","nineteen" };
	String z[]={"twenty","thirty","fourty","fifty",  "sixty","seventy", 
	"eighty","ninety"};
	
	public Translate()
	{
		a=null;
		b=null;
		a1=0;
		b1=0;
	}
	
	public void tran (int num)
	{
		if(num<10) System.out.println(x[num]);
		else if(num>=10&&num<20) System.out.println(y[num-10]);
		else
		{
			b1 = num%10;
			a1 = num-b1;
			b = x[b1];
			a = z[a1/10-2];
			System.out.println(a+' '+b);
			
		}
	}
	
	public void tran (String num)
	{
		if(num.indexOf(' ')!=-1)
		{
			a = num.substring(0, num.indexOf(' '));
			b = num.substring(num.indexOf(' ')+1);
			
			for(int i=0;i<z.length;++i)
			{
				if(a.equals(z[i]))
				{
					a1 = (i+2)*10;
					break;
				}
			}
			for(int i=0;i<x.length;++i)
			{
				if(b.equals(x[i]))
				{
					a1 += i;
					break;
				}
			}
			System.out.println(a1);
		}
		else
		{
			int flag=0;
			for(int i=0;i<x.length;++i)
			{
				if(num.equals(x[i]))
				{
					a1 = i;
					flag=1;
					break;
				}
			}
			if(flag==0) 
			{
				for(int i=0;i<y.length;++i)
				{
					if(num.equals(y[i]))
					{
						a1 = i + 10;
						flag=1;
						break;
					}
				}
			}
			if(flag==1) System.out.println(a1);
		}
	}
	
	public static void main(String args[]) throws IOException
	{
		//Scanner scn = new Scanner(System.in);
		//int num = scn.nextInt();
		
		Translate tr = new Translate();
		int num=0;
		//tr.tran(num);
		while(true)
		{
			BufferedReader bu = new BufferedReader(new InputStreamReader(System.in));
			
			String num2 = bu.readLine();
			if(num2.equals("-1")) break;
			if(num2.length()<=2)
			{
				num = Integer.parseInt(num2);
				tr.tran(num);
			}
			else tr.tran(num2);
		}
	}
}
