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
		System.out.print("���:"+num+" ������"+name+" �������ڣ�"+birth);
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
		System.out.print(" ѧԺ:"+college+" רҵ��"+major+" ��Уʱ�䣺"+attendToSchool+" ע����Ϣ��"+register);
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
		System.out.print(" ����:"+apartment+" ְ��"+major+" ǩ���������ڣ�"+register);
		System.out.println();
	}
}
public class TestCard {
	public static void main(String args[])
	{
		StudentCard sc = new StudentCard("001","��ͬѧ","2001.4.1","�����","�������","2019.9.1","��ע��");
		sc.print();
		TeacherCard tc = new TeacherCard("002","����ʦ","1980.4.1","���ܼ�������","����","2000.9.1");
		tc.print();
	}
}
