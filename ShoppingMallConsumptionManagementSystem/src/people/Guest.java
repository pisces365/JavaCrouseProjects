package people;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import goods.*;

public class Guest extends people
{
	private final String BecomeVIPtime;//入会时间
	//存储所有商品的信息数组
	private Vector<RecordsOfConsumption> goodsList;
	
	private String getBecomeVIPtime(Calendar c)//设置入会时间
	{
		return String.format("%1$tY-%1$tm-%1$td|%1$tH:%1$tM:%1$tS", c);
	}
	
	//用于新建普通用户的构造函数
	public Guest(String Name,String ID,String Sex,String PhoneNum,String Address,String PostCode,String UserName,String PassWord)
	{
		super(Name, ID, Sex, PhoneNum, Address, PostCode, UserName, PassWord);
		Calendar c = Calendar.getInstance();
		this.BecomeVIPtime = getBecomeVIPtime(c);
		goodsList = new Vector<RecordsOfConsumption>();
		init();
	}
	
	//用于读取已有用户的创建账户函数
	public Guest(String Name,String ID,String Sex,String PhoneNum,String Address,String PostCode,String UserName,String PassWord,String BecomeVIPtime)
	{
		super(Name, ID, Sex, PhoneNum, Address, PostCode, UserName, PassWord);
		this.BecomeVIPtime = BecomeVIPtime;
		goodsList = new Vector<RecordsOfConsumption>();
		init();
	}
	
	//获取入会时间函数
	public String getBecomeVIPtime()
	{
		return BecomeVIPtime;
	}
	
	//获取用户所有字段的函数
	public void SetAllElements(String Name,String ID,String Sex,String PhoneNum,String Address,String PostCode,String password)
	{
		super.SetAllElements(Name, ID, Sex, PhoneNum, Address, PostCode);
		setPassWord(password);
	}
	
	/////////////////////////////////////////////////////////
	//查找所有消费记录
	public Vector<Vector<String>> getAllGoods()
	{
		Vector<Vector<String>> allGoodsTable = new Vector<Vector<String>>();
		for(int i=0;i<goodsList.size();++i)
		{
			allGoodsTable.add(goodsList.get(i).showRecordsOfConsumptionDetails());
		}
		if(allGoodsTable.isEmpty() == true )
			return null;
		return allGoodsTable;
	}
	
	//	//消费记录初始化
	public void init() 
	{
		try
		{
			File f = new File(new File("").getCanonicalPath()+"/"+this.getUserName());
			if(f.exists() == false)//判断普通用户文件夹是否为空
			{
				f.mkdirs();
			}
			File i = new File(new File("").getCanonicalPath()+"/"+this.getUserName(), "GuestPurchaseGoods.txt");
			if(i.exists() == false)//判断储存消费记录文件是否存在
			{
				i.createNewFile();
			}
			BufferedReader bfr = new BufferedReader(new FileReader(i));
			String recordItems;
			String []recordItemsArray;
			while((recordItems = bfr.readLine()) != null)//将读入的数据每一行读入string
			{
				//System.out.println(recordItems);
				recordItemsArray = recordItems.split("[,]");//利用split函数和正则表达式进行数据分割
				goods go = new goods(recordItemsArray[0],recordItemsArray[1],recordItemsArray[2],recordItemsArray[3],recordItemsArray[4],recordItemsArray[5]);
				RecordsOfConsumption roc = new RecordsOfConsumption(go, recordItemsArray[6]);
				goodsList.add(roc);
			}
			bfr.close();
		}
		catch(IOException e)
		{
		
		}

	}
	
	//消费记录写入
	public void writeIn() 
	{
		try
		{
			File f = new File(new File("").getCanonicalPath()+"/"+this.getUserName(), "GuestPurchaseGoods.txt");
			BufferedWriter bfw = new BufferedWriter((new FileWriter(f)));//利用缓冲流写入文件，FileWriter可避免中文字符乱码
			for(int i=0;i<goodsList.size();++i)
			{
				bfw.write(goodsList.get(i).getGoodsDetails().getGoodsName() + "," 
						+goodsList.get(i).getGoodsDetails().getPrice() + "," 
						+goodsList.get(i).getGoodsDetails().getDateOfProduction() + "," 
						+goodsList.get(i).getGoodsDetails().getManufacturer() + "," 
						+goodsList.get(i).getGoodsDetails().getDiscount() + "," 
						+goodsList.get(i).getGoodsDetails().getProductIntroduction()+ "," 
						+goodsList.get(i).getPurchaseDate());//写入一行数据
				bfw.newLine();//进行一次换行
			}
			bfw.close();//文件写入后需关闭缓冲流
		}
		catch(IOException e)
		{
			
		}
	}
	
	//添加消费记录
	public void addGoods(RecordsOfConsumption roc)//添加消费记录
	{
		goodsList.add(roc);
	}
	
	//删除消费记录
	public void DeleteGoods(String purchaseTime)//删除消费记录
	{
		for(int i=0;i<goodsList.size();++i)
		{
			if(goodsList.get(i).getPurchaseDate().equals(purchaseTime))
			{
				goodsList.remove(i);
				return;
			}
		}
	}
	
	//查找特定消费记录
	public Vector<Vector<String>> FindGoods(String FindThings)
	{
		Vector<Vector<String>> allGoodsTable = new Vector<Vector<String>>();
		for(int i=0;i<goodsList.size();++i)
		{//根据商品名和支付记录进行全称查询
			if(goodsList.get(i).getGoodsName().equals(FindThings) || goodsList.get(i).getPurchaseDate().equals(FindThings) )
			{
				allGoodsTable.add(goodsList.get(i).showRecordsOfConsumptionDetails());
			}//根据商品名和支付记录进行模糊查询
			else if(goodsList.get(i).getGoodsName().indexOf(FindThings) != -1 || goodsList.get(i).getPurchaseDate().indexOf(FindThings) != -1)
			{
				allGoodsTable.add(goodsList.get(i).showRecordsOfConsumptionDetails());
			}
			else continue;
			
		}
		if(allGoodsTable.isEmpty() == true )//不可忽略的空值情况
			return null;
		return allGoodsTable;	
	}
}
