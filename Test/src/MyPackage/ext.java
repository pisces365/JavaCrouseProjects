package MyPackage;

public class ext {
	int i;
	public void abc()
	{
		System.out.println("1:"+i);
	}
	public ext()
	{
		i = 1;
	}
	
	
	public static void main(String []args)
	{
		ext ext1 = new ex2();
		ext1.abc();
		System.out.print(ext1.i);
	}
}

class ex2 extends ext
{
	int i;
	public void abc()
	{
		System.out.println("ex2"+i);
	}
	public ex2()
	{
		i = 2;
	}
}