import MyPack.Student;
import MyPack.Teacher;

public class TestPerson {
	public static void main(String args[])
	{
		Teacher te = new Teacher("jack","man","1996.4.23","����״�ѧ","20190606");
		te.printlnfo();
		Student st = new Student("mick","man","2001.3.18","����״�ѧ","20200404","�����","2020","04");
		st.printinfo();
	}
}