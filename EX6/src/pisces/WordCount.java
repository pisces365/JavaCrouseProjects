package pisces;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class WordCount {
	public static void main(String [] args)throws IOException
	{
		int numberRecord = 0;//��¼��ȡ�����ı���
		String str;//��¼ÿһ�ζ������ݵ���ʱ����
		List<String> arr = new ArrayList<>();//����arraylist��������е����е���
		File fl = new File("article.txt");//����File�ļ���article�ļ�
		if(fl.exists()==false)
			fl.createNewFile();//����ļ������ھʹ����µ��ļ�
		BufferedReader bfr = new BufferedReader (new FileReader(fl));//���û����������ļ���FileReader�ɱ��������ַ�����
		str = bfr.readLine();
		while(str!=null)//��Ҫ��bfr.readLine()��Ϊ�п�����
		{
			String spl[]= str.split(" ");//��һ��Ӣ�ľ��ӻ���Ϊ���ɵ���
			for(int i=0;i<spl.length;++i)
			arr.add(spl[i]);//��ÿ��������ӵ�arraylist��
			str = bfr.readLine();
		}
		bfr.close();
		String record1,record2;
		for(int i=0;i<arr.size();++i)
		{
			record1 = arr.get(i);//record1��ȡ������еĵ���
			record2 = record1.toLowerCase();//record2��¼������ĸת��ΪСд�������
			arr.set(i, record2);//��������е���������Ϊ�޴�д��״̬
			if(record2.indexOf("hello")!=-1)
			{
				numberRecord++;//ֻҪ�����ͼ�¼һ��
			}
		}
		System.out.println("����hello������article�г��ֵĴ���Ϊ��" +numberRecord);
	}
}
