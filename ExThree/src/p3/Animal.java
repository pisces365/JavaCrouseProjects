package p3;

public abstract class Animal
{
	String name,age,weight;
	public void showinfo()
	{
		System.out.println("��ɶ��"+name+" ����ˣ�"+age+" ������"+weight);
	}
	abstract public void move();
	abstract public void eat();
}
