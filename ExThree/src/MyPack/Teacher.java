package MyPack;

public class Teacher extends Person
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
