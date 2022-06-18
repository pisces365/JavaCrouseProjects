package My;

abstract class Animal
{
	String name,age,weight;
	public void showinfo()
	{
		System.out.println("��ɶ��"+name+" ����ˣ�"+age+" ������"+weight);
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
		System.out.println("��ô�˶���fly");
	}
	public void eat()
	{
		System.out.println("����ʲô������");
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
		System.out.println("��ô�˶���run");
	}
	public void eat()
	{
		System.out.println("����ʲô����ͷ");
	}
}
public class TestAnimal {
	public static void main(String args[])
	{
		Animal bi = new Bird("�Ʒ�","6","10");
		Animal dg = new Dog("�ı�","5","11");
		bi.showinfo();
		bi.move();
		bi.eat();
		dg.showinfo();
		dg.move();
		dg.eat();		
	}
}
