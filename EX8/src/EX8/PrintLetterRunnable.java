package EX8;

public class PrintLetterRunnable implements Runnable{
	public void run()
	{
		for(int i = 1;i <= 26; i++)
		{
			System.out.println((char)(96+i));
			try
			{
				Thread.sleep(1000);
			}
			catch(InterruptedException e)
			{
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String []args)
	{
		PrintLetterThread plt = new PrintLetterThread();
		Thread th = new Thread(plt);
		th.start();
	}
}
