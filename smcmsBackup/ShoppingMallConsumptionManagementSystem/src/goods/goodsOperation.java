package goods;

import java.io.*;
import java.util.*;

public class goodsOperation {
	private static Vector<goods> goodsList = new Vector<goods>();//��Ʒ�ܱ� �ֶ�
	
	//����JTable��ȡȫ����Ʒ���� ��ʼҳ��չʾ
	public static Vector<Vector<String>> getAllGoods()
	{
		Vector<Vector<String>> allGoodsTable = new Vector<Vector<String>>();//JTable��Ҫ��vector��ά����
		for(int i=0;i<goodsList.size();++i)
		{
			Vector<String> goodsRecord = new Vector<String>();//��ȡÿ����Ʒ��Ŀ������

			goodsRecord.add(goodsList.get(i).getGoodsNumber());
			goodsRecord.add(goodsList.get(i).getGoodsName());
			goodsRecord.add(goodsList.get(i).getManufacturer());
			goodsRecord.add(goodsList.get(i).getDateOfProduction());
			goodsRecord.add(goodsList.get(i).getPrice());
			goodsRecord.add(goodsList.get(i).getDiscount());
			goodsRecord.add(goodsList.get(i).getInventory());
			goodsRecord.add(goodsList.get(i).getProductIntroduction());
			goodsRecord.add(goodsList.get(i).getRemarks());

			//System.out.println(goodsList.get(i).getGoodsNumber() + "," +goodsList.get(i).getGoodsName() + "," +goodsList.get(i).getManufacturer() + "," +goodsList.get(i).getDateOfProduction() + "," +goodsList.get(i).getPrice() + "," +goodsList.get(i).getDiscount()+ "," +goodsList.get(i).getInventory()+ "," +goodsList.get(i).getProductIntroduction()+ "," +goodsList.get(i).getRemarks() );//д��һ������

			allGoodsTable.add(goodsRecord);
		}
		if(allGoodsTable.isEmpty())//��ֹΪ�յ��������
			return null;
		return allGoodsTable;
	}
	
	//��ʼ�� ��ȡ��Ʒ�ļ� ��ʼ����Ʒ����
	public static void init() 
	{
		try
		{
			BufferedReader bfr = new BufferedReader(new FileReader("goodsList.txt"));//��ȡ�ļ�
			String recordItems;
			String []recordItemsArray;
			while((recordItems = bfr.readLine()) != null)//�����������ÿһ�ж���string
			{
				//System.out.println(recordItems);
				recordItemsArray = recordItems.split("[,]");//����split������������ʽ�������ݷָ�
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
	
	
	//����д�뺯��
	public static void writeIn() 
	{
		try
		{
			BufferedWriter bfw = new BufferedWriter((new FileWriter("goodsList.txt")));//���û�����д���ļ���FileWriter�ɱ��������ַ�����
			for(int i=0;i<goodsList.size();++i)
			{
				bfw.write(goodsList.get(i).getGoodsNumber() + "," +goodsList.get(i).getGoodsName() + "," +goodsList.get(i).getManufacturer() + "," +goodsList.get(i).getDateOfProduction() + "," +goodsList.get(i).getPrice() + "," +goodsList.get(i).getDiscount()+ "," +goodsList.get(i).getInventory()+ "," +goodsList.get(i).getProductIntroduction()+ "," +goodsList.get(i).getRemarks() );//д��һ������
				bfw.newLine();//����һ�λ���
			}
			bfw.close();//�ļ�д�����رջ�����
		}
		catch(IOException e)
		{
			
		}
	}
	
	public static void addGoods(goods go)//��ӿ�����Ʒ
	{
		goodsList.add(go);
	}
	
	public static void DeleteGoods(String goodsName , String goodsNum)//ɾ��������Ʒ
	{
		for(int i=0;i<goodsList.size();++i)
		{
			if(goodsList.get(i).getGoodsName().equals(goodsName) || goodsList.get(i).getGoodsNumber().equals(goodsNum) )
			{
				goodsList.remove(i);
			}
		}
	}
	
	//Ѱ���ض���Ʒ����
	public static Vector<Vector<String>> FindGoods(String FindThings)
	{
		Vector<Vector<String>> allGoodsTable = new Vector<Vector<String>>();
		for(int i=0;i<goodsList.size();++i)
		{
			Vector<String> goodsRecord = new Vector<String>();
			if(goodsList.get(i).getGoodsName().equals(FindThings) || goodsList.get(i).getGoodsNumber().equals(FindThings) )
			{//��Ʒ���ƺ���Ʒ��ŵ�ȫ�̲���
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
			{//��Ʒ������Ʒ��ŵ�ģ������
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
	
	//�ж���Ʒ�Ƿ���ڵ�bool���غ���
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
	
	//��ȡ��һ��������Ʒ�Ļ�ȡ����
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
	
	//��Ʒ���ݸ��º���
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
