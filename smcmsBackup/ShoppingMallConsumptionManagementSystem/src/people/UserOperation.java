package people;

import java.util.*;
import goods.*;

import java.io.*;

public class UserOperation  //用户数据库操作类
{
	private static ArrayList<Guest> UserStorageDatabase = new ArrayList<Guest>();//储存普通用户的字段数组
	private static ArrayList<Admin> AdminStorageDatabase = new ArrayList<Admin>();//储存管理员的字段数组
	
	public static void init() throws IOException
	{
		BufferedReader bfr = new BufferedReader(new FileReader("UserStorageDatabase.txt"));
		String recordItems;
		String []recordItemsArray;
		while((recordItems = bfr.readLine()) != null)//将读入的数据每一行读入string
		{
			//System.out.println(recordItems);
			recordItemsArray = recordItems.split("[,]");//利用split函数和正则表达式进行数据分割
			Guest st = new Guest(recordItemsArray[0],recordItemsArray[1],recordItemsArray[2],recordItemsArray[3],recordItemsArray[4],recordItemsArray[5],recordItemsArray[6],recordItemsArray[7],recordItemsArray[8]);
			UserStorageDatabase.add(st);
		}
		bfr.close();
		BufferedReader Adminbfr = new BufferedReader(new FileReader("AdminStorageDatabase.txt"));
		String AdminrecordItems;
		String []AdminrecordItemsArray;
		while((AdminrecordItems = Adminbfr.readLine()) != null)//将读入的数据每一行读入string
		{
			//System.out.println(recordItems);
			AdminrecordItemsArray = AdminrecordItems.split("[,]");//利用split函数和正则表达式进行数据分割
			Admin ad = new Admin(AdminrecordItemsArray[0],AdminrecordItemsArray[1],AdminrecordItemsArray[2],AdminrecordItemsArray[3],AdminrecordItemsArray[4],AdminrecordItemsArray[5],AdminrecordItemsArray[6],AdminrecordItemsArray[7]);
			AdminStorageDatabase.add(ad);
		}
		Adminbfr.close();
	}
	
	//添加用户信息
	public static void addUserDetail(Guest gu)//添加用户信息
	{
		UserStorageDatabase.add(gu);
	}

	//添加管理员信息
	public static void addAdminDetail(Admin ad)
	{
		AdminStorageDatabase.add(ad);
	}
	
	//查找用户信息
	public static Guest FindUserInformation(String FindThings)//查找用户信息
	{
		for(int i = 0; i < UserStorageDatabase.size(); ++i)
		{
			if(UserStorageDatabase.get(i).getUserName().equals(FindThings)||UserStorageDatabase.get(i).getName().equals(FindThings)||UserStorageDatabase.get(i).getID().equals(FindThings)||UserStorageDatabase.get(i).getPhoneNum().equals(FindThings))
			{
				////就返回这个用户的全部信息
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
			{//全称查找
				//姓名、证件号、性别、手机号码、联系地址、邮编
				GuestRecord.add(UserStorageDatabase.get(i).getUserName());
				GuestRecord.add(UserStorageDatabase.get(i).getName());
				GuestRecord.add(UserStorageDatabase.get(i).getID());
				GuestRecord.add(UserStorageDatabase.get(i).getSex());
				GuestRecord.add(UserStorageDatabase.get(i).getPhoneNum());
				GuestRecord.add(UserStorageDatabase.get(i).getAddress());
				GuestRecord.add(UserStorageDatabase.get(i).getPostCode());
				GuestRecord.add(UserStorageDatabase.get(i).getBecomeVIPtime());
			}//模糊查找 姓名 用户名 ID 电话号
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
	
	//查找管理员信息
	public static Admin FindAdminInformation(String UserName)
	{
		for(int i = 0; i < AdminStorageDatabase.size(); ++i)
		{
			if(AdminStorageDatabase.get(i).getUserName().equals(UserName))
			{
				////就返回这个用户的全部信息
				return AdminStorageDatabase.get(i);
			}
		}
		return null;
	}
	
	//查找用户全部消费记录（管理员专用功能）
	public static Vector<Vector<String>> FindPurchaseInformation(String UserName)//查找用户消费记录
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
	
	//查找用户特定消费记录（管理员专用功能）
	public static Vector<Vector<String>> FindPurchaseInformation(String UserName, String goodsNameORNumber)//查找用户消费记录
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
	
	//删除用户信息
	public static void DeleteUserInformation(String UserName)//删除用户信息
	{
		for(int i = 0; i < UserStorageDatabase.size(); ++i)
		{
			if(UserStorageDatabase.get(i).getUserName().equals(UserName))
			{
				try
				{//获取根目录
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
	
	//删除管理员信息
	public static void DeleteAdminInformation(String UserName)//删除管理员信息
	{
		for(int i = 0; i < AdminStorageDatabase.size(); ++i)
		{
			if(AdminStorageDatabase.get(i).getUserName().equals(UserName))
			{
				AdminStorageDatabase.remove(i);
			}
		}
	}
	
	//用户信息更新
	public static void UpdateUserInformation(Guest gu)
	{
		for(int i = 0; i < UserStorageDatabase.size(); ++i)
		{
			if(UserStorageDatabase.get(i).getUserName().equals(gu.getUserName()))
			{
				UserStorageDatabase.get(i).SetAllElements(gu.getName(), gu.getID(), gu.getSex(), gu.getPhoneNum(), gu.getAddress(), gu.getPostCode(), gu.getPassWord());
				//用户名匹配便修改全部信息
			}
		}
	}
	
	//用户信息是否存在
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
	
	//管理员信息更新
	public static void UpdateAdminInformation(Admin gu)
	{
		for(int i = 0; i < AdminStorageDatabase.size(); ++i)
		{
			if(AdminStorageDatabase.get(i).getUserName().equals(gu.getUserName()))
			{
				AdminStorageDatabase.get(i).SetAllElements(gu.getName(), gu.getID(), gu.getSex(), gu.getPhoneNum(), gu.getAddress(), gu.getPostCode());
				AdminStorageDatabase.get(i).setPassWord(gu.getPassWord());
				//用户名匹配便修改全部信息
			}
		}
	}
	
	//对用户信息进行写入操作
	public static void writeIn() 
	{
		try
		{
			BufferedWriter bfw = new BufferedWriter((new FileWriter("UserStorageDatabase.txt")));//利用缓冲流写入文件，FileWriter可避免中文字符乱码
			for(int i=0;i<UserStorageDatabase.size();++i)
			{
				bfw.write(UserStorageDatabase.get(i).getName() + "," +UserStorageDatabase.get(i).getID() + "," +UserStorageDatabase.get(i).getSex() + "," +UserStorageDatabase.get(i).getPhoneNum() + "," +UserStorageDatabase.get(i).getAddress() + "," +UserStorageDatabase.get(i).getPostCode()+ "," +UserStorageDatabase.get(i).getUserName()+ "," +UserStorageDatabase.get(i).getPassWord()+ "," +UserStorageDatabase.get(i).getBecomeVIPtime() );//写入一行数据
				bfw.newLine();//进行一次换行
			}
			bfw.close();//文件写入后需关闭缓冲流
		}
		catch(IOException e)
		{
			
		}
	}
	
	///对管理员信息进行写入操作
	public static void AdminwriteIn() 
	{
		try
		{
			BufferedWriter bfw = new BufferedWriter((new FileWriter("AdminStorageDatabase.txt")));//利用缓冲流写入文件，FileWriter可避免中文字符乱码
			for(int i=0;i<AdminStorageDatabase.size();++i)
			{
				bfw.write(AdminStorageDatabase.get(i).getName() + "," +AdminStorageDatabase.get(i).getID() + "," +AdminStorageDatabase.get(i).getSex() + "," +AdminStorageDatabase.get(i).getPhoneNum() + "," +AdminStorageDatabase.get(i).getAddress() + "," +AdminStorageDatabase.get(i).getPostCode()+ "," +AdminStorageDatabase.get(i).getUserName()+ "," +AdminStorageDatabase.get(i).getPassWord() );//写入一行数据
				bfw.newLine();//进行一次换行
			}
			bfw.close();//文件写入后需关闭缓冲流
		}
		catch(IOException e)
		{
			
		}
	}
}
