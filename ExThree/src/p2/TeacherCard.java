package p2;

public class TeacherCard extends Card
{
	private String apartment,major,register;
	public TeacherCard(String nu,String na,String bi,String ap,String ma,String re)
	{
		super(nu,na,bi);
		apartment=ap;
		major=ma;
		register=re;
	}
	public void print()
	{
		super.print();
		System.out.print(" ����:"+apartment+" ְ��"+major+" ǩ���������ڣ�"+register);
		System.out.println();
	}
}
