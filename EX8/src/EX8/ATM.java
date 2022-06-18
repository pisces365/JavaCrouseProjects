package EX8;

import java.util.Scanner;

public class ATM implements Runnable{
	private static double money = 200;
	private static Object lock = new Object();//定义对象锁
	public int mode;
	
	public double cash;
	
	public void run()
	{
		synchronized(lock)//同步语句块
		{
			System.out.println("当前金额："+getCash());
			System.out.print("模式选择：1-存钱；2-取钱 请选择：");
			Scanner scn1 =new Scanner(System.in);
			mode = scn1.nextInt();
			System.out.print("输入金额：");
			Scanner scn2 =new Scanner(System.in);
			cash = scn2.nextDouble();
			selectMode(mode,cash);//存钱取钱选择函数
		}

	}
	
	public double getCash()
	{
		return money;
	}
	
	public void addCash(double cash)//存钱
	{
		money += cash;
	}
	
	public void catchCash(double cash)//取钱
	{
		try
		{
			if(money-cash<0)
			{
				throw new Exception();
			}
			else
			{
				money-=cash;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void selectMode(int num,double cash)
	{
		switch(num)
		{
		case 1:addCash(cash);
		System.out.println("当前金额："+getCash()); break;
		case 2:catchCash(cash);
		System.out.println("当前金额："+getCash()); break;
		}
	}
	
	public static void main(String []args)
	{
		ATM a = new ATM();
		Thread t1 = new Thread(a);
		Thread t2 = new Thread(a);
		t1.start();
		t2.start();
	}
}
