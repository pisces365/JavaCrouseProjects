import MyPack.Student;
import MyPack.Teacher;

public class TestPerson {
	public static void main(String args[])
	{
		Teacher te = new Teacher("jack","man","1996.4.23","家里蹲大学","20190606");
		te.printlnfo();
		Student st = new Student("mick","man","2001.3.18","家里蹲大学","20200404","计算机","2020","04");
		st.printinfo();
	}
}