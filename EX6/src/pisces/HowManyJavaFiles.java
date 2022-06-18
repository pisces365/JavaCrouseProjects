package pisces;

import java.io.*;

public class HowManyJavaFiles {
	public static void main(String [] ars)throws IOException
	{
		File fl = new File("D:/eclipse-workspace/ShoppingMallConsumptionManagementSystem/src/goods");//File类读取本地文件夹下java文件
		File []farr = fl.listFiles();//用file文件数组存储文件夹下文件，方便后续分别读取文件
		BufferedReader bfr ;
		String record;
		int num = 0;
		System.out.println("java文件总数："+farr.length);//数组文件长度就是目录下的文件数
		for(int i=0;i<farr.length;++i)//利用遍历分别读取每个文件中的每一行
		{
			bfr = new BufferedReader(new FileReader(farr[i]));
			while((record = bfr.readLine()) != null)
			{
				if(record.indexOf("//")==-1)//备注一般以//开头，故本行忽略不计，当非备注时，便将数据记录变量加1
				num++;
			}
			bfr.close();
		}
		File fl2 = new File("D:/eclipse-workspace/ShoppingMallConsumptionManagementSystem/src/GUI");//File类读取本地文件夹下java文件
		File []farr2 = fl2.listFiles();//用file文件数组存储文件夹下文件，方便后续分别读取文件
		BufferedReader bfr2 ;
		String record2;
		System.out.println("java文件总数："+farr.length);//数组文件长度就是目录下的文件数
		for(int i=0;i<farr2.length;++i)//利用遍历分别读取每个文件中的每一行
		{
			bfr2 = new BufferedReader(new FileReader(farr2[i]));
			while((record2 = bfr2.readLine()) != null)
			{
				if(record2.indexOf("//")==-1)//备注一般以//开头，故本行忽略不计，当非备注时，便将数据记录变量加1
				num++;
			}
			bfr2.close();
		}
		File fl3 = new File("D:/eclipse-workspace/ShoppingMallConsumptionManagementSystem/src/Login");//File类读取本地文件夹下java文件
		File []farr3 = fl3.listFiles();//用file文件数组存储文件夹下文件，方便后续分别读取文件
		BufferedReader bfr3 ;
		String record3;
		System.out.println("java文件总数："+farr.length);//数组文件长度就是目录下的文件数
		for(int i=0;i<farr3.length;++i)//利用遍历分别读取每个文件中的每一行
		{
			bfr3 = new BufferedReader(new FileReader(farr3[i]));
			while((record3 = bfr3.readLine()) != null)
			{
				if(record3.indexOf("//")==-1)//备注一般以//开头，故本行忽略不计，当非备注时，便将数据记录变量加1
				num++;
			}
			bfr3.close();
		}
		File fl4 = new File("D:/eclipse-workspace/ShoppingMallConsumptionManagementSystem/src/people");//File类读取本地文件夹下java文件
		File []farr4 = fl4.listFiles();//用file文件数组存储文件夹下文件，方便后续分别读取文件
		BufferedReader bfr4 ;
		String record4;
		System.out.println("java文件总数："+farr.length);//数组文件长度就是目录下的文件数
		for(int i=0;i<farr4.length;++i)//利用遍历分别读取每个文件中的每一行
		{
			bfr4 = new BufferedReader(new FileReader(farr4[i]));
			while((record4 = bfr4.readLine()) != null)
			{
				if(record4.indexOf("//")==-1)//备注一般以//开头，故本行忽略不计，当非备注时，便将数据记录变量加1
				num++;
			}
			bfr4.close();
		}
		System.out.println("java代码总行数："+num);
	}
}
