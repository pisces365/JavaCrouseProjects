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
		System.out.println(name+"��ô������");
	}
	public void stop()
	{
		System.out.println(name+"��ôͣ�£���������ܣ�ɲס~");
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

class Car extends Vehicle
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
