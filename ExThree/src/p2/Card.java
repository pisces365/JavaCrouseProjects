package p2;

public class Card
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
