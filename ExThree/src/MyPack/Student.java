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
		System.out.print(" ѧУ��"+school+" ѧ�ţ�"+studentnum+" רҵ��"+major+" �꼶��"+grade+" �༶��"+theclass);
		System.out.println();
	}
}
