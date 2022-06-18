package p3;

public abstract class Animal
{
	String name,age,weight;
	public void showinfo()
	{
		System.out.println("叫啥："+name+" 多大了："+age+" 重量："+weight);
	}
	abstract public void move();
	abstract public void eat();
}
