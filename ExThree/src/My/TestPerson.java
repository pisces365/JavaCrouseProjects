package My;

class Person
{
	protected String name,sex,birthday;
	public Person(String na,String se,String bi)
	{
		name=na;
		sex=se;
		birthday=bi;
	}
	public void printlnfo()
	{
		System.out.print("����:"+name+" �Ա�"+sex+" �������ڣ�"+birthday);
	}
}

class Teacher extends Person
{
	private String school,teachernum;
	public Teacher(String na,String se,String bi,String sc,String te)
	{
		super(na,se,bi);
		school = sc;
		teachernum = te;
	
	}
	public void printlnfo()
	{
		super.printlnfo();
		System.out.print(" ѧУ��"+school+" ���ţ�"+teachernum);
		System.out.println();
	}
}

class Student extends Person
{
	private String school,studentnum,major,grade,theclass;
	public Student(String na,String se,String bi,String sc,String stunum,String ma,String gr,String thecl)
	{
		super(na,se,bi);
		school = sc;
		studentnum=stunum;
		major=ma;
		grade=gr;
		theclass=thecl;
	
	}
	public void printinfo()
	{
		super.printlnfo();
		System.out.print(" ѧУ��"+school+" ѧ�ţ�"+studentnum+" רҵ��"+major+" �꼶��"+grade+" �༶��"+theclass);
		System.out.println();
	}
}
public class TestPerson {
	public static void main(String args[])
	{
		Teacher te = new Teacher("jack","man","1994.4.22","����״�ѧ","20190606");
		te.printlnfo();
		Student st = new Student("mick","man","2001.3.18","����״�ѧ","20200404","�����","2020","04");
		st.printinfo();
	}
}
