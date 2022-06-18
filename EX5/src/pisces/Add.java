package pisces;

import java.util.Scanner;

public class Add {
	public static void main(String args[])
	{
		try
		{
			Scanner sc = new Scanner(System.in);
			String str;
			str = sc.nextLine();//输入数据保存在字符串中
			if(str.lastIndexOf('+')==str.length()-1)
			{
				throw new Exception();//倘若最后一个字符为+，则抛出异常
			}
			String spl[]= str.split("[+]");//利用split正则表达式分离+号前后的数值
			int sum = 0;
			for(int i=0;i<spl.length;++i)
			{
				sum+=Integer.parseInt(spl[i]);
			}
			System.out.println("表达式结果：" + sum);
		}
		catch(Exception e)
		{
			System.out.println("输入格式错误！");
		}
	}
}
