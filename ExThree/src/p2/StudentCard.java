package p2;

public class StudentCard extends Card
{
	private String college,major,attendToSchool,register;
	public StudentCard(String nu,String na,String bi,String co,String ma,String at,String re)
	{
		super(nu,na,bi);
		college=co;
		major=ma;
		attendToSchool=at;
		register=re;
	}
	public void print()
	{
		super.print();
		System.out.print(" 学院:"+college+" 专业："+major+" 入校时间："+attendToSchool+" 注册信息："+register);
		System.out.println();
	}
}