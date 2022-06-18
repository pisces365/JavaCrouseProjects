package My;

class Card
{
	protected String num,name,birth;
	public Card(String nu,String na,String bi) 
	{
		num=nu;
		name=na;
		birth=bi;
	}
	public void print()
	{
		System.out.print("编号:"+num+" 姓名："+name+" 出生日期："+birth);
	}
}

class StudentCard extends Card
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

class TeacherCard extends Card
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
public class TestCard {
	public static void main(String args[])
	{
		StudentCard sc = new StudentCard("001","杨同学","2001.4.1","计算机","软件工程","2019.9.1","已注册");
		sc.print();
		TeacherCard tc = new TeacherCard("002","周老师","1980.4.1","智能计算中心","教授","2000.9.1");
		tc.print();
	}
}
