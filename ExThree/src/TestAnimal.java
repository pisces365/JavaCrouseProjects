import p3.Animal;
import p3.Bird;
import p3.Dog;

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
