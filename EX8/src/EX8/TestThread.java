package EX8;

class PrintThread implements Runnable
{
	public void run()
	{
		for(int i=0;i<5;++i)
		{
			System.out.println(Thread.currentThread().getName()+"正在运行");
		}
	}
}

public class TestThread {
	public static void main(String []args)
	{
		PrintThread pt = new PrintThread();
		Thread t1 = new Thread(pt);
		Thread t2 = new Thread(pt);
		t1.setName("线程1");
		t2.setName("线程2");
		t1.start();
		t2.start();
	}
}
