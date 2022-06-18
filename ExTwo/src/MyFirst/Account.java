package MyFirst;

//import java.math.BigDecimal;

public class Account {
	private String id,owner;//账号，拥有者
	private double balance;//余额
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
			System.out.println("账号未知！");
			
		}
		else if(this.id==id)
		{	
			this.balance += Math.round(amount*100)/100.0;
			System.out.println("成功存款"+Math.round(amount*100)/100.0+"元，当前余额为"+balance+"元！");
			
		}
		else System.out.println("账号错误！");
			
	}
	public void withdraw(String id,double amount)
	{
		if(this.id==null)
		{
			System.out.println("账号未知！");
			
		}
		else if(this.id!=id)
		{
			System.out.println("账号错误！");
		}
		else if(this.balance<amount)
		{
			System.out.println("余额不足！");
		}
		else 
		{
			this.balance -= Math.round(amount*100)/100.0;
			this.balance=Math.round(balance*100)/100.0;
			System.out.println("成功取款"+Math.round(amount*100)/100.0+"元，当前余额为"+balance+"元！");
		}
	}
	public void queryBalance()
	{
		System.out.println("账号："+id+" 拥有者："+owner+" 余额："+balance+"元！");
	}
	public double getBalance (String id)
	{
		if(this.id!=id)
		{
			System.out.println("账号错误！");
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
		ac.setOwner("我");
		ac.deposit("20190606", des);
		ac.withdraw("20190606", wit);
		System.out.println("当前余额："+ac.getBalance("20190606")+"元！");
		ac.queryBalance();
		
		System.out.println();
		
		Account ao = new Account("20190607","ta",des);
		ao.withdraw("20190607", wit);
		System.out.println("当前余额："+ao.getBalance("20190607")+"元！");
		ao.queryBalance();
	}
}
