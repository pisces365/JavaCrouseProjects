package MyPackage;

public class SString {
	public String a[] = {"a","b","c","d","e"};
	public String b="123";
	public void change(String a[])
	{
		a[0] = "mmm";
		System.out.println(a[0]);
	}
	public void change2(String b)
	{
		b = "mm";
		System.out.println(b);
	}
	
	public static void main(String args[])
	{
		SString ss= new SString();
		ss.change(ss.a);
		System.out.println(ss.a[0]);
		ss.change2(ss.b);
		System.out.println(ss.b);
	}
}
