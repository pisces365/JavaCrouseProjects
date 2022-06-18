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
		return ("������" + name +" �Ա�" + sex + " רҵ��" + major + " ѧ�ţ�" + stuNum);
	}
	public static void writeIn(ArrayList<StudentMIS> a) throws IOException
	{
		//BufferedWriter bfw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("student.txt")));
		//ʹ��OutputStreamWriter���Ա���д�������ַ�ʱ����������
		BufferedWriter bfw = new BufferedWriter((new FileWriter("student.txt")));//���û�����д���ļ���FileWriter�ɱ��������ַ�����
		for(int i=0;i<a.size();++i)
		{
			bfw.write(a.get(i).name + "," +a.get(i).sex + "," +a.get(i).major + "," +a.get(i).stuNum );//д��һ������
			bfw.newLine();//����һ�λ���
		}
		bfw.close();//�ļ�д�����رջ�����
	}
	public static String ReadIn(String read) throws IOException
	{
		System.out.println(read);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));//InputStreamReader���Ա������̨����������ַ���������
		return br.readLine();
	}
	public static void main (String [] args) throws IOException
	{
		ArrayList<StudentMIS> ALS = new ArrayList<StudentMIS>();
		//BufferedReader bfr = new BufferedReader(new InputStreamReader(new FileInputStream("student.txt")));
		BufferedReader bfr = new BufferedReader((new FileReader("student.txt")));
		String recordItems;
		String []recordItemsArray;
		while((recordItems = bfr.readLine()) != null)//�����������ÿһ�ж���string
		{
			recordItemsArray = recordItems.split("[,]");//����split������������ʽ�������ݷָ�
			StudentMIS st = new StudentMIS(recordItemsArray[0],recordItemsArray[1],recordItemsArray[2],Integer.parseInt(recordItemsArray[3]));
			ALS.add(st);
		}
		bfr.close();
		while(true)
		{
			System.out.println("���������Խ���ѡ��1-���ӣ�2-ɾ����3-�޸ģ�4-��ʾ��5-���棬6-�˳�");
			String collect = (new BufferedReader(new InputStreamReader(System.in))).readLine();
			switch(collect)
			{
				case "1": 
				{
					StudentMIS st = new StudentMIS(ReadIn("������������"),ReadIn("�������Ա�"),ReadIn("������רҵ��"),Integer.parseInt(ReadIn("������ѧ�ţ�")));
					ALS.add(st);
					writeIn(ALS);
					break;
				}
				case "2": 
				{
					int stunum = Integer.parseInt(ReadIn("������ѧ�ţ�"));
					for(int i=0;i<ALS.size();++i)//��ArrayList���б���
					{
						if(ALS.get(i).stuNum == stunum)//��ѯ����ͬ��ѧ�žͶԸýڵ�����Ƴ�
						{
							ALS.remove(i);
							break;
						}
					}
					writeIn(ALS);//д���ļ�
					break;
				}
				case "3":
				{
					int stunum = Integer.parseInt(ReadIn("������ѧ�ţ�"));
					for(int i=0;i<ALS.size();++i)
					{
						if(ALS.get(i).stuNum == stunum)//�������ѯ����ѧ��ѧ�ţ��ֱ��޸������Ա�ѧ��
						{
							String record[] = new String [3];
							for(int ij=0;ij<3;++ij)
							{
								switch(ij)
								{
								case 0:System.out.println("������������");break;
								case 1:System.out.println("�������Ա�");break;
								case 2:System.out.println("������רҵ��");break;
								}
								 record[ij] = (new BufferedReader(new InputStreamReader(System.in))).readLine();
							}
							ALS.get(i).name = record[0];
							ALS.get(i).sex = record[1];
							ALS.get(i).major = record[2];
							break;
						}
					}
					writeIn(ALS);//д���ļ�
					break;
				}
				case "4":
				{
					for(int i=0;i<ALS.size();++i)
					{
						System.out.println(ALS.get(i).display());//����ÿ���ڵ��display���������������
					}
					break;
				}
				case "5": 
				{
					writeIn(ALS);//����д�����
					System.out.println("�ѱ��棡");//�����ʾ��Ϣ
					break;
				}
				case "6": writeIn(ALS); return;//���˳�����ǰ�ٽ���һ������д�����
			}
		}
	}
}
