package goods;

import java.util.Calendar;
import java.util.Vector;

public class RecordsOfConsumption
{
	private goods GoodsDetails;///��¼��Ʒ�ľ���ϸ��
	private final String PurchaseDate;///��¼��Ʒ�Ĺ�������
	
	public RecordsOfConsumption(goods GoodsDetails, String date)//��ʼ��ȫ���ֶ� ��ʷ���Ѽ�¼����
	{
		this.GoodsDetails = GoodsDetails;
		PurchaseDate = date;
	}
	
	public RecordsOfConsumption(goods GoodsDetails)//��ʼ��ȫ���ֶ� �����Ѽ�¼����
	{
		this.GoodsDetails = GoodsDetails;
		Calendar c = Calendar.getInstance();
		PurchaseDate = getPurchaseDate(c);
	}
	
	private String getPurchaseDate(Calendar PurchaseDate)//��ȡ֧�����ھ�������
	{
		return String.format("%1$tY-%1$tm-%1$td %1$tH:%1$tM:%1$tS", PurchaseDate);
	}
	
	public String getGoodsName()//����Guest��ȡ��ǰ������Ʒ����Ʒ��
	{
		return GoodsDetails.getGoodsName();
	}
	
	public goods getGoodsDetails()//����Guest��ȡ��ǰ������Ʒ
	{
		return GoodsDetails;
	}
	
	public String getPurchaseDate()//����Guest��ȡ��ǰ������Ʒ��֧������
	{
		return PurchaseDate;
	}
	
	public Vector<String> showRecordsOfConsumptionDetails()//��ȡ��ǰ������Ʒ��������ϸ��Ϣ
	{
		Vector<String> ThisGoods = new Vector<String>();
		ThisGoods.add(GoodsDetails.getGoodsName());
		ThisGoods.add(GoodsDetails.getPrice());
		ThisGoods.add(GoodsDetails.getDateOfProduction());
		ThisGoods.add(GoodsDetails.getManufacturer());
		ThisGoods.add(GoodsDetails.getDiscount());
		ThisGoods.add(GoodsDetails.getProductIntroduction());
		ThisGoods.add(PurchaseDate);
		return ThisGoods;
	}
}

