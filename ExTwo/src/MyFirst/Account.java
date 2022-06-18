package MyFirst;

//import java.math.BigDecimal;

public class Account {
	private String id,owner;//�˺ţ�ӵ����
	private double balance;//���
	public Account()
	{
		id=null;
		owner=null;
		balance=0.00;
	}
	public Account(String id,String owner,double amount)
	{
		//BigDecimal b= new BigDecimal(amount);  
		//double amountx= b.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue(); 
		this.id=id;
		this.owner=owner;
		this.balance=Math.round(amount*100)/100.0;
	}
	public void setID(String id)
	{
		this.id=id;
	}
	public void setOwner(String owner)
	{
		this.owner=owner;
	}
	public void deposit(String id,double amount)
	{
		if(this.id==null)
		{
			System.out.println("�˺�δ֪��");
			
		}
		else if(this.id==id)
		{	
			this.balance += Math.round(amount*100)/100.0;
			System.out.println("�ɹ����"+Math.round(amount*100)/100.0+"Ԫ����ǰ���Ϊ"+balance+"Ԫ��");
			
		}
		else System.out.println("�˺Ŵ���");
			
	}
	public void withdraw(String id,double amount)
	{
		if(this.id==null)
		{
			System.out.println("�˺�δ֪��");
			
		}
		else if(this.id!=id)
		{
			System.out.println("�˺Ŵ���");
		}
		else if(this.balance<amount)
		{
			System.out.println("���㣡");
		}
		else 
		{
			this.balance -= Math.round(amount*100)/100.0;
			this.balance=Math.round(balance*100)/100.0;
			System.out.println("�ɹ�ȡ��"+Math.round(amount*100)/100.0+"Ԫ����ǰ���Ϊ"+balance+"Ԫ��");
		}
	}
	public void queryBalance()
	{
		System.out.println("�˺ţ�"+id+" ӵ���ߣ�"+owner+" ��"+balance+"Ԫ��");
	}
	public double getBalance (String id)
	{
		if(this.id!=id)
		{
			System.out.println("�˺Ŵ���");
			return -1;
		}
		else 
		{
			return Math.round(balance*100)/100.0;
		}
	}
	
	public static void main(String args[])
	{
		double des=Math.random()*100;
		double wit=Math.random()*100;
		Account ac = new Account();
		ac.setID("20190606");
		ac.setOwner("��");
		ac.deposit("20190606", des);
		ac.withdraw("20190606", wit);
		System.out.println("��ǰ��"+ac.getBalance("20190606")+"Ԫ��");
		ac.queryBalance();
		
		System.out.println();
		
		Account ao = new Account("20190607","ta",des);
		ao.withdraw("20190607", wit);
		System.out.println("��ǰ��"+ao.getBalance("20190607")+"Ԫ��");
		ao.queryBalance();
	}
}
