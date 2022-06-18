package goods;

import java.io.*;
import java.util.*;

public class goodsOperation {
	private static Vector<goods> goodsList = new Vector<goods>();//商品总表单 字段
	
	//用于JTable获取全部商品数据 初始页面展示
	public static Vector<Vector<String>> getAllGoods()
	{
		Vector<Vector<String>> allGoodsTable = new Vector<Vector<String>>();//JTable需要的vector二维数组
		for(int i=0;i<goodsList.size();++i)
		{
			Vector<String> goodsRecord = new Vector<String>();//读取每个商品条目的数组

			goodsRecord.add(goodsList.get(i).getGoodsNumber());
			goodsRecord.add(goodsList.get(i).getGoodsName());
			goodsRecord.add(goodsList.get(i).getManufacturer());
			goodsRecord.add(goodsList.get(i).getDateOfProduction());
			goodsRecord.add(goodsList.get(i).getPrice());
			goodsRecord.add(goodsList.get(i).getDiscount());
			goodsRecord.add(goodsList.get(i).getInventory());
			goodsRecord.add(goodsList.get(i).getProductIntroduction());
			goodsRecord.add(goodsList.get(i).getRemarks());

			//System.out.println(goodsList.get(i).getGoodsNumber() + "," +goodsList.get(i).getGoodsName() + "," +goodsList.get(i).getManufacturer() + "," +goodsList.get(i).getDateOfProduction() + "," +goodsList.get(i).getPrice() + "," +goodsList.get(i).getDiscount()+ "," +goodsList.get(i).getInventory()+ "," +goodsList.get(i).getProductIntroduction()+ "," +goodsList.get(i).getRemarks() );//写入一行数据

			allGoodsTable.add(goodsRecord);
		}
		if(allGoodsTable.isEmpty())//防止为空的情况发生
			return null;
		return allGoodsTable;
	}
	
	//初始化 读取商品文件 初始化商品数组
	public static void init() 
	{
		try
		{
			BufferedReader bfr = new BufferedReader(new FileReader("goodsList.txt"));//读取文件
			String recordItems;
			String []recordItemsArray;
			while((recordItems = bfr.readLine()) != null)//将读入的数据每一行读入string
			{
				//System.out.println(recordItems);
				recordItemsArray = recordItems.split("[,]");//利用split函数和正则表达式进行数据分割
				goods st = new goods(recordItemsArray[0],recordItemsArray[1],recordItemsArray[2],recordItemsArray[3],recordItemsArray[4],recordItemsArray[5],recordItemsArray[6],recordItemsArray[7],recordItemsArray[8]);
				goodsList.add(st);
			}
			bfr.close();
		}
		catch(IOException e)
		{
			e.getMessage();
		}

	}
	
	
	//数据写入函数
	public static void writeIn() 
	{
		try
		{
			BufferedWriter bfw = new BufferedWriter((new FileWriter("goodsList.txt")));//利用缓冲流写入文件，FileWriter可避免中文字符乱码
			for(int i=0;i<goodsList.size();++i)
			{
				bfw.write(goodsList.get(i).getGoodsNumber() + "," +goodsList.get(i).getGoodsName() + "," +goodsList.get(i).getManufacturer() + "," +goodsList.get(i).getDateOfProduction() + "," +goodsList.get(i).getPrice() + "," +goodsList.get(i).getDiscount()+ "," +goodsList.get(i).getInventory()+ "," +goodsList.get(i).getProductIntroduction()+ "," +goodsList.get(i).getRemarks() );//写入一行数据
				bfw.newLine();//进行一次换行
			}
			bfw.close();//文件写入后需关闭缓冲流
		}
		catch(IOException e)
		{
			
		}
	}
	
	public static void addGoods(goods go)//添加库中商品
	{
		goodsList.add(go);
	}
	
	public static void DeleteGoods(String goodsName , String goodsNum)//删除库中商品
	{
		for(int i=0;i<goodsList.size();++i)
		{
			if(goodsList.get(i).getGoodsName().equals(goodsName) || goodsList.get(i).getGoodsNumber().equals(goodsNum) )
			{
				goodsList.remove(i);
			}
		}
	}
	
	//寻找特定商品函数
	public static Vector<Vector<String>> FindGoods(String FindThings)
	{
		Vector<Vector<String>> allGoodsTable = new Vector<Vector<String>>();
		for(int i=0;i<goodsList.size();++i)
		{
			Vector<String> goodsRecord = new Vector<String>();
			if(goodsList.get(i).getGoodsName().equals(FindThings) || goodsList.get(i).getGoodsNumber().equals(FindThings) )
			{//商品名称和商品编号的全程查找
				goodsRecord.add(goodsList.get(i).getGoodsNumber());
				goodsRecord.add(goodsList.get(i).getGoodsName());
				goodsRecord.add(goodsList.get(i).getManufacturer());
				goodsRecord.add(goodsList.get(i).getDateOfProduction());
				goodsRecord.add(goodsList.get(i).getPrice());
				goodsRecord.add(goodsList.get(i).getDiscount());
				goodsRecord.add(goodsList.get(i).getInventory());
				goodsRecord.add(goodsList.get(i).getProductIntroduction());
				goodsRecord.add(goodsList.get(i).getRemarks());
			}
			else if(goodsList.get(i).getGoodsName().indexOf(FindThings) != -1 || goodsList.get(i).getGoodsNumber().indexOf(FindThings) != -1)
			{//商品名和商品编号的模糊查找
				goodsRecord.add(goodsList.get(i).getGoodsNumber());
				goodsRecord.add(goodsList.get(i).getGoodsName());
				goodsRecord.add(goodsList.get(i).getManufacturer());
				goodsRecord.add(goodsList.get(i).getDateOfProduction());
				goodsRecord.add(goodsList.get(i).getPrice());
				goodsRecord.add(goodsList.get(i).getDiscount());
				goodsRecord.add(goodsList.get(i).getInventory());
				goodsRecord.add(goodsList.get(i).getProductIntroduction());
				goodsRecord.add(goodsList.get(i).getRemarks());
			}
			else continue;
			allGoodsTable.add(goodsRecord);
		}
		return allGoodsTable;	
	}
	
	//判断商品是否存在的bool返回函数
	public static boolean isGoodsExistence(String FindThings)
	{
		for(int i=0;i<goodsList.size();++i)
		{
			if(goodsList.get(i).getGoodsName().equals(FindThings) || goodsList.get(i).getGoodsNumber().equals(FindThings) )
			{
				return true;
			}
		}	
		return false;
	}
	
	//获取第一个存在商品的获取函数
	public static goods GetExistenceGoods(String FindThings)
	{
		for(int i=0;i<goodsList.size();++i)
		{
			if(goodsList.get(i).getGoodsName().equals(FindThings) || goodsList.get(i).getGoodsNumber().equals(FindThings) )
			{
				return goodsList.get(i);
			}
		}	
		return null;
	}
	
	//商品数据更新函数
	public static void UpdateGoods(goods go)
	{
		for(int i=0;i<goodsList.size();++i)
		{
			if( goodsList.get(i).getGoodsNumber().equals(go.getGoodsNumber()) )
			{
				goodsList.remove(i);
				goodsList.add(i, go);
			}
		}	
	}
}
