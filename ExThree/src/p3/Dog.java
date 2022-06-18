package p3;

public class Dog extends Animal
{
	public Dog(String na,String ag,String we)
	{
		name=na;
		age=ag;
		weight=we;
	}
	public void move()
	{
		System.out.println("怎么运动：run");
	}
	public void eat()
	{
		System.out.println("爱吃什么：骨头");
	}
}
