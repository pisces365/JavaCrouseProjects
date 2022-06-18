package MyPack;

public class Student extends Person
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
