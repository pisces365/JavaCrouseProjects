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
	private final String BecomeVIPtime;//���ʱ��
	//�洢������Ʒ����Ϣ����
	private Vector<RecordsOfConsumption> goodsList;
	
	private String getBecomeVIPtime(Calendar c)//�������ʱ��
	{
		return String.format("%1$tY-%1$tm-%1$td|%1$tH:%1$tM:%1$tS", c);
	}
	
	//�����½���ͨ�û��Ĺ��캯��
	public Guest(String Name,String ID,String Sex,String PhoneNum,String Address,String PostCode,String UserName,String PassWord)
	{
		super(Name, ID, Sex, PhoneNum, Address, PostCode, UserName, PassWord);
		Calendar c = Calendar.getInstance();
		this.BecomeVIPtime = getBecomeVIPtime(c);
		goodsList = new Vector<RecordsOfConsumption>();
		init();
	}
	
	//���ڶ�ȡ�����û��Ĵ����˻�����
	public Guest(String Name,String ID,String Sex,String PhoneNum,String Address,String PostCode,String UserName,String PassWord,String BecomeVIPtime)
	{
		super(Name, ID, Sex, PhoneNum, Address, PostCode, UserName, PassWord);
		this.BecomeVIPtime = BecomeVIPtime;
		goodsList = new Vector<RecordsOfConsumption>();
		init();
	}
	
	//��ȡ���ʱ�亯��
	public String getBecomeVIPtime()
	{
		return BecomeVIPtime;
	}
	
	//��ȡ�û������ֶεĺ���
	public void SetAllElements(String Name,String ID,String Sex,String PhoneNum,String Address,String PostCode,String password)
	{
		super.SetAllElements(Name, ID, Sex, PhoneNum, Address, PostCode);
		setPassWord(password);
	}
	
	/////////////////////////////////////////////////////////
	//�����������Ѽ�¼
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
	
	//	//���Ѽ�¼��ʼ��
	public void init() 
	{
		try
		{
			File f = new File(new File("").getCanonicalPath()+"/"+this.getUserName());
			if(f.exists() == false)//�ж���ͨ�û��ļ����Ƿ�Ϊ��
			{
				f.mkdirs();
			}
			File i = new File(new File("").getCanonicalPath()+"/"+this.getUserName(), "GuestPurchaseGoods.txt");
			if(i.exists() == false)//�жϴ������Ѽ�¼�ļ��Ƿ����
			{
				i.createNewFile();
			}
			BufferedReader bfr = new BufferedReader(new FileReader(i));
			String recordItems;
			String []recordItemsArray;
			while((recordItems = bfr.readLine()) != null)//�����������ÿһ�ж���string
			{
				//System.out.println(recordItems);
				recordItemsArray = recordItems.split("[,]");//����split������������ʽ�������ݷָ�
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
	
	//���Ѽ�¼д��
	public void writeIn() 
	{
		try
		{
			File f = new File(new File("").getCanonicalPath()+"/"+this.getUserName(), "GuestPurchaseGoods.txt");
			BufferedWriter bfw = new BufferedWriter((new FileWriter(f)));//���û�����д���ļ���FileWriter�ɱ��������ַ�����
			for(int i=0;i<goodsList.size();++i)
			{
				bfw.write(goodsList.get(i).getGoodsDetails().getGoodsName() + "," 
						+goodsList.get(i).getGoodsDetails().getPrice() + "," 
						+goodsList.get(i).getGoodsDetails().getDateOfProduction() + "," 
						+goodsList.get(i).getGoodsDetails().getManufacturer() + "," 
						+goodsList.get(i).getGoodsDetails().getDiscount() + "," 
						+goodsList.get(i).getGoodsDetails().getProductIntroduction()+ "," 
						+goodsList.get(i).getPurchaseDate());//д��һ������
				bfw.newLine();//����һ�λ���
			}
			bfw.close();//�ļ�д�����رջ�����
		}
		catch(IOException e)
		{
			
		}
	}
	
	//������Ѽ�¼
	public void addGoods(RecordsOfConsumption roc)//������Ѽ�¼
	{
		goodsList.add(roc);
	}
	
	//ɾ�����Ѽ�¼
	public void DeleteGoods(String purchaseTime)//ɾ�����Ѽ�¼
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
	
	//�����ض����Ѽ�¼
	public Vector<Vector<String>> FindGoods(String FindThings)
	{
		Vector<Vector<String>> allGoodsTable = new Vector<Vector<String>>();
		for(int i=0;i<goodsList.size();++i)
		{//������Ʒ����֧����¼����ȫ�Ʋ�ѯ
			if(goodsList.get(i).getGoodsName().equals(FindThings) || goodsList.get(i).getPurchaseDate().equals(FindThings) )
			{
				allGoodsTable.add(goodsList.get(i).showRecordsOfConsumptionDetails());
			}//������Ʒ����֧����¼����ģ����ѯ
			else if(goodsList.get(i).getGoodsName().indexOf(FindThings) != -1 || goodsList.get(i).getPurchaseDate().indexOf(FindThings) != -1)
			{
				allGoodsTable.add(goodsList.get(i).showRecordsOfConsumptionDetails());
			}
			else continue;
			
		}
		if(allGoodsTable.isEmpty() == true )//���ɺ��ԵĿ�ֵ���
			return null;
		return allGoodsTable;	
	}
}
