package MyPackage;

public class Example {
	String str = new String("hello");
	char ch[] = {'d','b','c'};
	int a=18;
	public static void main(String args[])
	{
		Example ex = new Example();
		
		ex.change(ex.str, ex.ch,ex.a);
		System.out.println(ex.str+" and "+ex.ch[0]+" "+ex.a);
		
	}
	
	public void change (String str,char ch[],int a)
	{
		str = "world";
		ch[0] = 'a';
		a= 6;
		System.out.println(str+" and "+ch[0]+" "+a);
	}
}
