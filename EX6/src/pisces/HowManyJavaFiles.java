package pisces;

import java.io.*;

public class HowManyJavaFiles {
	public static void main(String [] ars)throws IOException
	{
		File fl = new File("D:/eclipse-workspace/ShoppingMallConsumptionManagementSystem/src/goods");//File���ȡ�����ļ�����java�ļ�
		File []farr = fl.listFiles();//��file�ļ�����洢�ļ������ļ�����������ֱ��ȡ�ļ�
		BufferedReader bfr ;
		String record;
		int num = 0;
		System.out.println("java�ļ�������"+farr.length);//�����ļ����Ⱦ���Ŀ¼�µ��ļ���
		for(int i=0;i<farr.length;++i)//���ñ����ֱ��ȡÿ���ļ��е�ÿһ��
		{
			bfr = new BufferedReader(new FileReader(farr[i]));
			while((record = bfr.readLine()) != null)
			{
				if(record.indexOf("//")==-1)//��עһ����//��ͷ���ʱ��к��Բ��ƣ����Ǳ�עʱ���㽫���ݼ�¼������1
				num++;
			}
			bfr.close();
		}
		File fl2 = new File("D:/eclipse-workspace/ShoppingMallConsumptionManagementSystem/src/GUI");//File���ȡ�����ļ�����java�ļ�
		File []farr2 = fl2.listFiles();//��file�ļ�����洢�ļ������ļ�����������ֱ��ȡ�ļ�
		BufferedReader bfr2 ;
		String record2;
		System.out.println("java�ļ�������"+farr.length);//�����ļ����Ⱦ���Ŀ¼�µ��ļ���
		for(int i=0;i<farr2.length;++i)//���ñ����ֱ��ȡÿ���ļ��е�ÿһ��
		{
			bfr2 = new BufferedReader(new FileReader(farr2[i]));
			while((record2 = bfr2.readLine()) != null)
			{
				if(record2.indexOf("//")==-1)//��עһ����//��ͷ���ʱ��к��Բ��ƣ����Ǳ�עʱ���㽫���ݼ�¼������1
				num++;
			}
			bfr2.close();
		}
		File fl3 = new File("D:/eclipse-workspace/ShoppingMallConsumptionManagementSystem/src/Login");//File���ȡ�����ļ�����java�ļ�
		File []farr3 = fl3.listFiles();//��file�ļ�����洢�ļ������ļ�����������ֱ��ȡ�ļ�
		BufferedReader bfr3 ;
		String record3;
		System.out.println("java�ļ�������"+farr.length);//�����ļ����Ⱦ���Ŀ¼�µ��ļ���
		for(int i=0;i<farr3.length;++i)//���ñ����ֱ��ȡÿ���ļ��е�ÿһ��
		{
			bfr3 = new BufferedReader(new FileReader(farr3[i]));
			while((record3 = bfr3.readLine()) != null)
			{
				if(record3.indexOf("//")==-1)//��עһ����//��ͷ���ʱ��к��Բ��ƣ����Ǳ�עʱ���㽫���ݼ�¼������1
				num++;
			}
			bfr3.close();
		}
		File fl4 = new File("D:/eclipse-workspace/ShoppingMallConsumptionManagementSystem/src/people");//File���ȡ�����ļ�����java�ļ�
		File []farr4 = fl4.listFiles();//��file�ļ�����洢�ļ������ļ�����������ֱ��ȡ�ļ�
		BufferedReader bfr4 ;
		String record4;
		System.out.println("java�ļ�������"+farr.length);//�����ļ����Ⱦ���Ŀ¼�µ��ļ���
		for(int i=0;i<farr4.length;++i)//���ñ����ֱ��ȡÿ���ļ��е�ÿһ��
		{
			bfr4 = new BufferedReader(new FileReader(farr4[i]));
			while((record4 = bfr4.readLine()) != null)
			{
				if(record4.indexOf("//")==-1)//��עһ����//��ͷ���ʱ��к��Բ��ƣ����Ǳ�עʱ���㽫���ݼ�¼������1
				num++;
			}
			bfr4.close();
		}
		System.out.println("java������������"+num);
	}
}
