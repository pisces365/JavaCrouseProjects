package p4;

public class Car extends Vehicle
{
	public Car(String na,String br,String loc,String lo,String ma,String sp)
	{
		super(na,br,loc,lo,ma,sp);
	}
	public void move()
	{
		System.out.println(name+"��ô������");
	}
	public void stop()
	{
		System.out.println(name+"��ôͣ�£�ɲ��");
	}
	public void speedUp()
	{
		System.out.println("��Ҫ������");
	}
	public void slowDown()
	{
		System.out.println("��Ҫ������");
	}
}
