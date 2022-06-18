package EX8;

import java.util.Scanner;

public class ATM implements Runnable{
	private static double money = 200;
	private static Object lock = new Object();//���������
	public int mode;
	
	public double cash;
	
	public void run()
	{
		synchronized(lock)//ͬ������
		{
			System.out.println("��ǰ��"+getCash());
			System.out.print("ģʽѡ��1-��Ǯ��2-ȡǮ ��ѡ��");
			Scanner scn1 =new Scanner(System.in);
			mode = scn1.nextInt();
			System.out.print("�����");
			Scanner scn2 =new Scanner(System.in);
			cash = scn2.nextDouble();
			selectMode(mode,cash);//��ǮȡǮѡ����
		}

	}
	
	public double getCash()
	{
		return money;
	}
	
	public void addCash(double cash)//��Ǯ
	{
		money += cash;
	}
	
	public void catchCash(double cash)//ȡǮ
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
		System.out.println("��ǰ��"+getCash()); break;
		case 2:catchCash(cash);
		System.out.println("��ǰ��"+getCash()); break;
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
