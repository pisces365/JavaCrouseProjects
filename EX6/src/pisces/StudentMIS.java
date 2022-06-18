package pisces;

import java.io.*;
import java.util.*;

public class StudentMIS {
	String name;
	String sex;
	String major;
	int stuNum;
	public StudentMIS(String na,String se,String ma,int stuN)
	{
		name = na;
		 sex = se;
		 major = ma;
		 stuNum = stuN;
	}
	public String display()
	{
		return ("姓名：" + name +" 性别：" + sex + " 专业：" + major + " 学号：" + stuNum);
	}
	public static void writeIn(ArrayList<StudentMIS> a) throws IOException
	{
		//BufferedWriter bfw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("student.txt")));
		//使用OutputStreamWriter可以避免写入中文字符时，出现乱码
		BufferedWriter bfw = new BufferedWriter((new FileWriter("student.txt")));//利用缓冲流写入文件，FileWriter可避免中文字符乱码
		for(int i=0;i<a.size();++i)
		{
			bfw.write(a.get(i).name + "," +a.get(i).sex + "," +a.get(i).major + "," +a.get(i).stuNum );//写入一行数据
			bfw.newLine();//进行一次换行
		}
		bfw.close();//文件写入后需关闭缓冲流
	}
	public static String ReadIn(String read) throws IOException
	{
		System.out.println(read);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));//InputStreamReader可以避免控制台输入的中文字符出现乱码
		return br.readLine();
	}
	public static void main (String [] args) throws IOException
	{
		ArrayList<StudentMIS> ALS = new ArrayList<StudentMIS>();
		//BufferedReader bfr = new BufferedReader(new InputStreamReader(new FileInputStream("student.txt")));
		BufferedReader bfr = new BufferedReader((new FileReader("student.txt")));
		String recordItems;
		String []recordItemsArray;
		while((recordItems = bfr.readLine()) != null)//将读入的数据每一行读入string
		{
			recordItemsArray = recordItems.split("[,]");//利用split函数和正则表达式进行数据分割
			StudentMIS st = new StudentMIS(recordItemsArray[0],recordItemsArray[1],recordItemsArray[2],Integer.parseInt(recordItemsArray[3]));
			ALS.add(st);
		}
		bfr.close();
		while(true)
		{
			System.out.println("输入数字以进行选择：1-增加，2-删除，3-修改，4-显示，5-保存，6-退出");
			String collect = (new BufferedReader(new InputStreamReader(System.in))).readLine();
			switch(collect)
			{
				case "1": 
				{
					StudentMIS st = new StudentMIS(ReadIn("请输入姓名："),ReadIn("请输入性别："),ReadIn("请输入专业："),Integer.parseInt(ReadIn("请输入学号：")));
					ALS.add(st);
					writeIn(ALS);
					break;
				}
				case "2": 
				{
					int stunum = Integer.parseInt(ReadIn("请输入学号："));
					for(int i=0;i<ALS.size();++i)//对ArrayList进行遍历
					{
						if(ALS.get(i).stuNum == stunum)//查询到相同的学号就对该节点进行移除
						{
							ALS.remove(i);
							break;
						}
					}
					writeIn(ALS);//写入文件
					break;
				}
				case "3":
				{
					int stunum = Integer.parseInt(ReadIn("请输入学号："));
					for(int i=0;i<ALS.size();++i)
					{
						if(ALS.get(i).stuNum == stunum)//遍历后查询到该学生学号，分别修改姓名性别学号
						{
							String record[] = new String [3];
							for(int ij=0;ij<3;++ij)
							{
								switch(ij)
								{
								case 0:System.out.println("请输入姓名：");break;
								case 1:System.out.println("请输入性别：");break;
								case 2:System.out.println("请输入专业：");break;
								}
								 record[ij] = (new BufferedReader(new InputStreamReader(System.in))).readLine();
							}
							ALS.get(i).name = record[0];
							ALS.get(i).sex = record[1];
							ALS.get(i).major = record[2];
							break;
						}
					}
					writeIn(ALS);//写入文件
					break;
				}
				case "4":
				{
					for(int i=0;i<ALS.size();++i)
					{
						System.out.println(ALS.get(i).display());//利用每个节点的display函数进行数据输出
					}
					break;
				}
				case "5": 
				{
					writeIn(ALS);//数据写入操作
					System.out.println("已保存！");//输出提示信息
					break;
				}
				case "6": writeIn(ALS); return;//在退出程序前再进行一次数据写入操作
			}
		}
	}
}
