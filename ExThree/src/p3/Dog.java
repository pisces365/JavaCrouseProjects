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
		System.out.println("��ô�˶���run");
	}
	public void eat()
	{
		System.out.println("����ʲô����ͷ");
	}
}
