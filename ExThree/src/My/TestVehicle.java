package My;

abstract class Vehicle
{
	String name,brand,loadCapacity,load,maxSpeed,speed;
	public Vehicle(String na,String br,String loc,String lo,String ma,String sp)
	{
		name=na;
		brand=br;
		loadCapacity=loc;
		load=lo;
		maxSpeed=ma;
		speed=sp;
	}
	abstract public void move();
	abstract public void stop();
	abstract public void speedUp();
	abstract public void slowDown();
}

class Plane extends Vehicle
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

class Car extends Vehicle
{
	public Car(String na,String br,String loc,String lo,String ma,String sp)
	{
		super(na,br,loc,lo,ma,sp);
	}
	public void move()
	{
		System.out.println(name+"怎么动：跑");
	}
	public void stop()
	{
		System.out.println(name+"怎么停下：刹车");
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
public class TestVehicle {
	public static void main(String args[])
	{
		Vehicle vehicle;
		vehicle= new Plane("plane","Boeing","10000","1000","1000","942");
		vehicle.move();
		vehicle.stop();
		vehicle.speedUp();
		vehicle.slowDown();
		vehicle= new Car("car","bench","100","56","120","80");
		vehicle.move();
		vehicle.stop();
		vehicle.speedUp();
		vehicle.slowDown();
	}
}
