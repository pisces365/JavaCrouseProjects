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
		System.out.print(" ѧԺ:"+college+" רҵ��"+major+" ��Уʱ�䣺"+attendToSchool+" ע����Ϣ��"+register);
		System.out.println();
	}
}