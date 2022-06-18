package pisces;

import java.io.*;
import java.util.*;

public class StudentInfoManager {
	String name;
	long stno;
	String sex;
	public StudentInfoManager(String na,long st,String se)
	{
		name = na;
		stno = st;
		sex = se;
	}
	public String display()
	{
		return "姓名：" + name + " 学号：" + stno + " 性别：" + sex;
	}
	public static String input(String thing)throws IOException
	{
		System.out.println(thing);
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		String in = bfr.readLine();
		return in;
	} 
	public static StudentInfoManager inputStudentItems()throws IOException
	{
		String name = input("请输入姓名：");
		String stno = input("请输入学号：");
		String sex = input("请输入性别：");
		return new StudentInfoManager(name,Long.parseLong(stno),sex);
	}
	public static void main(String [] args)throws IOException//必须要抛出异常才能使用缓冲流等输入输出流
	{
		Vector<StudentInfoManager> vc = new Vector<StudentInfoManager>();
		while(true)
		{
			String collect = StudentInfoManager.input("输入数字以进行选择：1-增加，2-删除，3-显示，4-退出");
			switch(collect)
			{
				case "1": vc.add(StudentInfoManager.inputStudentItems()); break;
				case "2": 
				{
					long stunum = Long.parseLong(input("请输入学号："));
					for(int i=0;i<vc.size();++i)
					{
						if(vc.get(i).stno == stunum)
						{
							vc.removeElementAt(i);//查询到相同的学号就对该节点进行移除
							break;
						}
					}
					break;
				}
				case "3":
				{
					for(int i=0;i<vc.size();++i)
					{
						System.out.println(vc.get(i).display());//利用每个节点的display函数进行数据输出
					}
					break;
				}
				case "4": return;
			}
		}
	}
}
