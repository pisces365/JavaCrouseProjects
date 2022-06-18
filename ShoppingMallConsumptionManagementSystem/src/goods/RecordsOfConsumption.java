package goods;

import java.util.Calendar;
import java.util.Vector;

public class RecordsOfConsumption
{
	private goods GoodsDetails;///记录商品的具体细节
	private final String PurchaseDate;///记录商品的购买日期
	
	public RecordsOfConsumption(goods GoodsDetails, String date)//初始化全部字段 历史消费记录创建
	{
		this.GoodsDetails = GoodsDetails;
		PurchaseDate = date;
	}
	
	public RecordsOfConsumption(goods GoodsDetails)//初始化全部字段 新消费记录创建
	{
		this.GoodsDetails = GoodsDetails;
		Calendar c = Calendar.getInstance();
		PurchaseDate = getPurchaseDate(c);
	}
	
	private String getPurchaseDate(Calendar PurchaseDate)//获取支付日期具体内容
	{
		return String.format("%1$tY-%1$tm-%1$td %1$tH:%1$tM:%1$tS", PurchaseDate);
	}
	
	public String getGoodsName()//用于Guest获取当前购买商品的商品名
	{
		return GoodsDetails.getGoodsName();
	}
	
	public goods getGoodsDetails()//用于Guest获取当前购买商品
	{
		return GoodsDetails;
	}
	
	public String getPurchaseDate()//用于Guest获取当前购买商品的支付日期
	{
		return PurchaseDate;
	}
	
	public Vector<String> showRecordsOfConsumptionDetails()//获取当前购买商品的所有详细信息
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

