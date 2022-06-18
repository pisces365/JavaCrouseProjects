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
		System.out.print("姓名:"+name+" 性别："+sex+" 出生日期："+birthday);
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
		System.out.print(" 学校："+school+" 工号："+teachernum);
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
		System.out.print(" 学校："+school+" 学号："+studentnum+" 专业："+major+" 年级："+grade+" 班级："+theclass);
		System.out.println();
	}
}
public class TestPerson {
	public static void main(String args[])
	{
		Teacher te = new Teacher("jack","man","1994.4.22","家里蹲大学","20190606");
		te.printlnfo();
		Student st = new Student("mick","man","2001.3.18","家里蹲大学","20200404","计算机","2020","04");
		st.printinfo();
	}
}
