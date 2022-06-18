package p4;

public abstract class Vehicle
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