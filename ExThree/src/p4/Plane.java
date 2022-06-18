package p4;

public class Plane extends Vehicle
{
	public Plane(String na,String br,String loc,String lo,String ma,String sp)
	{
		super(na,br,loc,lo,ma,sp);
	}
	public void move()
	{
		System.out.println(name+"怎么动：飞");
	}
	public void stop()
	{
		System.out.println(name+"怎么停下：放下起落架，刹住~");
	}
	public void speedUp()
	{
		System.out.println("我要加速啦");
	}
	public void slowDown()
	{
		System.out.println("我要减速啦");
	}
}
