package pisces;

import java.util.Scanner;

public class Add {
	public static void main(String args[])
	{
		try
		{
			Scanner sc = new Scanner(System.in);
			String str;
			str = sc.nextLine();//�������ݱ������ַ�����
			if(str.lastIndexOf('+')==str.length()-1)
			{
				throw new Exception();//�������һ���ַ�Ϊ+�����׳��쳣
			}
			String spl[]= str.split("[+]");//����split������ʽ����+��ǰ�����ֵ
			int sum = 0;
			for(int i=0;i<spl.length;++i)
			{
				sum+=Integer.parseInt(spl[i]);
			}
			System.out.println("���ʽ�����" + sum);
		}
		catch(Exception e)
		{
			System.out.println("�����ʽ����");
		}
	}
}
