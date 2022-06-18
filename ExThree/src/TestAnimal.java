import p3.Animal;
import p3.Bird;
import p3.Dog;

public class TestAnimal {
	public static void main(String args[])
	{
		Animal bi = new Bird("·Æ·Æ","6","10");
		Animal dg = new Dog("±Ä±Ä","5","11");
		bi.showinfo();
		bi.move();
		bi.eat();
		dg.showinfo();
		dg.move();
		dg.eat();		
	}
}
