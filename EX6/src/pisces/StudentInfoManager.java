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
		return "������" + name + " ѧ�ţ�" + stno + " �Ա�" + sex;
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
		String name = input("������������");
		String stno = input("������ѧ�ţ�");
		String sex = input("�������Ա�");
		return new StudentInfoManager(name,Long.parseLong(stno),sex);
	}
	public static void main(String [] args)throws IOException//����Ҫ�׳��쳣����ʹ�û����������������
	{
		Vector<StudentInfoManager> vc = new Vector<StudentInfoManager>();
		while(true)
		{
			String collect = StudentInfoManager.input("���������Խ���ѡ��1-���ӣ�2-ɾ����3-��ʾ��4-�˳�");
			switch(collect)
			{
				case "1": vc.add(StudentInfoManager.inputStudentItems()); break;
				case "2": 
				{
					long stunum = Long.parseLong(input("������ѧ�ţ�"));
					for(int i=0;i<vc.size();++i)
					{
						if(vc.get(i).stno == stunum)
						{
							vc.removeElementAt(i);//��ѯ����ͬ��ѧ�žͶԸýڵ�����Ƴ�
							break;
						}
					}
					break;
				}
				case "3":
				{
					for(int i=0;i<vc.size();++i)
					{
						System.out.println(vc.get(i).display());//����ÿ���ڵ��display���������������
					}
					break;
				}
				case "4": return;
			}
		}
	}
}
