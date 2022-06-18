package people;

import java.util.*;
import goods.*;

import java.io.*;

public class UserOperation  //�û����ݿ������
{
	private static ArrayList<Guest> UserStorageDatabase = new ArrayList<Guest>();//������ͨ�û����ֶ�����
	private static ArrayList<Admin> AdminStorageDatabase = new ArrayList<Admin>();//�������Ա���ֶ�����
	
	public static void init() throws IOException
	{
		BufferedReader bfr = new BufferedReader(new FileReader("UserStorageDatabase.txt"));
		String recordItems;
		String []recordItemsArray;
		while((recordItems = bfr.readLine()) != null)//�����������ÿһ�ж���string
		{
			//System.out.println(recordItems);
			recordItemsArray = recordItems.split("[,]");//����split������������ʽ�������ݷָ�
			Guest st = new Guest(recordItemsArray[0],recordItemsArray[1],recordItemsArray[2],recordItemsArray[3],recordItemsArray[4],recordItemsArray[5],recordItemsArray[6],recordItemsArray[7],recordItemsArray[8]);
			UserStorageDatabase.add(st);
		}
		bfr.close();
		BufferedReader Adminbfr = new BufferedReader(new FileReader("AdminStorageDatabase.txt"));
		String AdminrecordItems;
		String []AdminrecordItemsArray;
		while((AdminrecordItems = Adminbfr.readLine()) != null)//�����������ÿһ�ж���string
		{
			//System.out.println(recordItems);
			AdminrecordItemsArray = AdminrecordItems.split("[,]");//����split������������ʽ�������ݷָ�
			Admin ad = new Admin(AdminrecordItemsArray[0],AdminrecordItemsArray[1],AdminrecordItemsArray[2],AdminrecordItemsArray[3],AdminrecordItemsArray[4],AdminrecordItemsArray[5],AdminrecordItemsArray[6],AdminrecordItemsArray[7]);
			AdminStorageDatabase.add(ad);
		}
		Adminbfr.close();
	}
	
	//����û���Ϣ
	public static void addUserDetail(Guest gu)//����û���Ϣ
	{
		UserStorageDatabase.add(gu);
	}

	//��ӹ���Ա��Ϣ
	public static void addAdminDetail(Admin ad)
	{
		AdminStorageDatabase.add(ad);
	}
	
	//�����û���Ϣ
	public static Guest FindUserInformation(String FindThings)//�����û���Ϣ
	{
		for(int i = 0; i < UserStorageDatabase.size(); ++i)
		{
			if(UserStorageDatabase.get(i).getUserName().equals(FindThings)||UserStorageDatabase.get(i).getName().equals(FindThings)||UserStorageDatabase.get(i).getID().equals(FindThings)||UserStorageDatabase.get(i).getPhoneNum().equals(FindThings))
			{
				////�ͷ�������û���ȫ����Ϣ
				return UserStorageDatabase.get(i);
			}
		}
		return null;
	}
	
	public static Vector<Vector<String>> FindGuests(String FindThings)
	{
		Vector<Vector<String>> allGuestTable = new Vector<Vector<String>>();
		for(int i=0;i<UserStorageDatabase.size();++i)
		{
			Vector<String> GuestRecord = new Vector<String>();
			if(UserStorageDatabase.get(i).getUserName().equals(FindThings)||UserStorageDatabase.get(i).getName().equals(FindThings)||UserStorageDatabase.get(i).getID().equals(FindThings)||UserStorageDatabase.get(i).getPhoneNum().equals(FindThings))
			{//ȫ�Ʋ���
				//������֤���š��Ա��ֻ����롢��ϵ��ַ���ʱ�
				GuestRecord.add(UserStorageDatabase.get(i).getUserName());
				GuestRecord.add(UserStorageDatabase.get(i).getName());
				GuestRecord.add(UserStorageDatabase.get(i).getID());
				GuestRecord.add(UserStorageDatabase.get(i).getSex());
				GuestRecord.add(UserStorageDatabase.get(i).getPhoneNum());
				GuestRecord.add(UserStorageDatabase.get(i).getAddress());
				GuestRecord.add(UserStorageDatabase.get(i).getPostCode());
				GuestRecord.add(UserStorageDatabase.get(i).getBecomeVIPtime());
			}//ģ������ ���� �û��� ID �绰��
			else if(UserStorageDatabase.get(i).getUserName().indexOf(FindThings) != -1 || UserStorageDatabase.get(i).getName().indexOf(FindThings) != -1 || UserStorageDatabase.get(i).getID().indexOf(FindThings) != -1||UserStorageDatabase.get(i).getPhoneNum().indexOf(FindThings) != -1)
			{
				GuestRecord.add(UserStorageDatabase.get(i).getUserName());
				GuestRecord.add(UserStorageDatabase.get(i).getName());
				GuestRecord.add(UserStorageDatabase.get(i).getID());
				GuestRecord.add(UserStorageDatabase.get(i).getSex());
				GuestRecord.add(UserStorageDatabase.get(i).getPhoneNum());
				GuestRecord.add(UserStorageDatabase.get(i).getAddress());
				GuestRecord.add(UserStorageDatabase.get(i).getPostCode());
				GuestRecord.add(UserStorageDatabase.get(i).getBecomeVIPtime());
			}
			else continue;
			allGuestTable.add(GuestRecord);
		}
		if(allGuestTable.isEmpty())
			return null;
		return allGuestTable;	
	}
	
	//���ҹ���Ա��Ϣ
	public static Admin FindAdminInformation(String UserName)
	{
		for(int i = 0; i < AdminStorageDatabase.size(); ++i)
		{
			if(AdminStorageDatabase.get(i).getUserName().equals(UserName))
			{
				////�ͷ�������û���ȫ����Ϣ
				return AdminStorageDatabase.get(i);
			}
		}
		return null;
	}
	
	//�����û�ȫ�����Ѽ�¼������Աר�ù��ܣ�
	public static Vector<Vector<String>> FindPurchaseInformation(String UserName)//�����û����Ѽ�¼
	{																											
		for(int i = 0; i < UserStorageDatabase.size(); ++i)
		{
			if(UserStorageDatabase.get(i).getUserName().equals(UserName))
			{
				return UserStorageDatabase.get(i).getAllGoods();
			}
		}
		return null;
	}
	
	//�����û��ض����Ѽ�¼������Աר�ù��ܣ�
	public static Vector<Vector<String>> FindPurchaseInformation(String UserName, String goodsNameORNumber)//�����û����Ѽ�¼
	{																											
		for(int i = 0; i < UserStorageDatabase.size(); ++i)
		{
			if(UserStorageDatabase.get(i).getUserName().equals(UserName))
			{
				return UserStorageDatabase.get(i).FindGoods(goodsNameORNumber);
			}
		}
		return null;
	}
	
	//ɾ���û���Ϣ
	public static void DeleteUserInformation(String UserName)//ɾ���û���Ϣ
	{
		for(int i = 0; i < UserStorageDatabase.size(); ++i)
		{
			if(UserStorageDatabase.get(i).getUserName().equals(UserName))
			{
				try
				{//��ȡ��Ŀ¼
					File file = new File(new File("").getCanonicalPath()+"/"+UserStorageDatabase.get(i).getUserName());
					File txt = new File(new File("").getCanonicalPath()+"/"+UserStorageDatabase.get(i).getUserName(), "GuestPurchaseGoods.txt");
					txt.delete();
					file.delete();
				}
				catch(IOException io) {}
				UserStorageDatabase.remove(i);
			}
		}
	}
	
	//ɾ������Ա��Ϣ
	public static void DeleteAdminInformation(String UserName)//ɾ������Ա��Ϣ
	{
		for(int i = 0; i < AdminStorageDatabase.size(); ++i)
		{
			if(AdminStorageDatabase.get(i).getUserName().equals(UserName))
			{
				AdminStorageDatabase.remove(i);
			}
		}
	}
	
	//�û���Ϣ����
	public static void UpdateUserInformation(Guest gu)
	{
		for(int i = 0; i < UserStorageDatabase.size(); ++i)
		{
			if(UserStorageDatabase.get(i).getUserName().equals(gu.getUserName()))
			{
				UserStorageDatabase.get(i).SetAllElements(gu.getName(), gu.getID(), gu.getSex(), gu.getPhoneNum(), gu.getAddress(), gu.getPostCode(), gu.getPassWord());
				//�û���ƥ����޸�ȫ����Ϣ
			}
		}
	}
	
	//�û���Ϣ�Ƿ����
	public static boolean isUserInformationExistence(String UserName)
	{
		for(int i = 0; i < UserStorageDatabase.size(); ++i)
		{
			if(UserStorageDatabase.get(i).getUserName().equals(UserName))
			{
				return true;
			}
		}
		return false;
	}
	
	//����Ա��Ϣ����
	public static void UpdateAdminInformation(Admin gu)
	{
		for(int i = 0; i < AdminStorageDatabase.size(); ++i)
		{
			if(AdminStorageDatabase.get(i).getUserName().equals(gu.getUserName()))
			{
				AdminStorageDatabase.get(i).SetAllElements(gu.getName(), gu.getID(), gu.getSex(), gu.getPhoneNum(), gu.getAddress(), gu.getPostCode());
				AdminStorageDatabase.get(i).setPassWord(gu.getPassWord());
				//�û���ƥ����޸�ȫ����Ϣ
			}
		}
	}
	
	//���û���Ϣ����д�����
	public static void writeIn() 
	{
		try
		{
			BufferedWriter bfw = new BufferedWriter((new FileWriter("UserStorageDatabase.txt")));//���û�����д���ļ���FileWriter�ɱ��������ַ�����
			for(int i=0;i<UserStorageDatabase.size();++i)
			{
				bfw.write(UserStorageDatabase.get(i).getName() + "," +UserStorageDatabase.get(i).getID() + "," +UserStorageDatabase.get(i).getSex() + "," +UserStorageDatabase.get(i).getPhoneNum() + "," +UserStorageDatabase.get(i).getAddress() + "," +UserStorageDatabase.get(i).getPostCode()+ "," +UserStorageDatabase.get(i).getUserName()+ "," +UserStorageDatabase.get(i).getPassWord()+ "," +UserStorageDatabase.get(i).getBecomeVIPtime() );//д��һ������
				bfw.newLine();//����һ�λ���
			}
			bfw.close();//�ļ�д�����رջ�����
		}
		catch(IOException e)
		{
			
		}
	}
	
	///�Թ���Ա��Ϣ����д�����
	public static void AdminwriteIn() 
	{
		try
		{
			BufferedWriter bfw = new BufferedWriter((new FileWriter("AdminStorageDatabase.txt")));//���û�����д���ļ���FileWriter�ɱ��������ַ�����
			for(int i=0;i<AdminStorageDatabase.size();++i)
			{
				bfw.write(AdminStorageDatabase.get(i).getName() + "," +AdminStorageDatabase.get(i).getID() + "," +AdminStorageDatabase.get(i).getSex() + "," +AdminStorageDatabase.get(i).getPhoneNum() + "," +AdminStorageDatabase.get(i).getAddress() + "," +AdminStorageDatabase.get(i).getPostCode()+ "," +AdminStorageDatabase.get(i).getUserName()+ "," +AdminStorageDatabase.get(i).getPassWord() );//д��һ������
				bfw.newLine();//����һ�λ���
			}
			bfw.close();//�ļ�д�����رջ�����
		}
		catch(IOException e)
		{
			
		}
	}
}
