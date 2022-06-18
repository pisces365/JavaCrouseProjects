package My;

abstract class Animal
{
	String name,age,weight;
	public void showinfo()
	{
		System.out.println("叫啥："+name+" 多大了："+age+" 重量："+weight);
	}
	abstract public void move();
	abstract public void eat();
}

class Bird extends Animal
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

class Dog extends Animal
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
public class TestAnimal {
	public static void main(String args[])
	{
		Animal bi = new Bird("菲菲","6","10");
		Animal dg = new Dog("蹦蹦","5","11");
		bi.showinfo();
		bi.move();
		bi.eat();
		dg.showinfo();
		dg.move();
		dg.eat();		
	}
}
