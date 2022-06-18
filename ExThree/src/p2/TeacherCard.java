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
		System.out.print(" 部门:"+apartment+" 职务："+major+" 签发工作日期："+register);
		System.out.println();
	}
}
