package MyPack;

public class Person
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
		System.out.print("����:"+name+" �Ա�"+sex+" �������ڣ�"+birthday);
	}
}