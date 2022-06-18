package p3;

public class Bird extends Animal
{
	public Bird(String na,String ag,String we)
	{
		name=na;
		age=ag;
		weight=we;
	}
	public void move()
	{
		System.out.println("怎么运动：fly");
	}
	public void eat()
	{
		System.out.println("爱吃什么：果子");
	}
}
