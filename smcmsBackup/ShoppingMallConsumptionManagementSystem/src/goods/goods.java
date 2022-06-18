package goods;

public class goods 
{
	private String goodsNumber;//商品编号
	private String goodsName;//名称
	private String manufacturer;//制造商
	private String dateOfProduction;//生产日期
	private String price;//价格
	private String discount;//折扣率
	private String inventory;//库存
	private String productIntroduction;//商品简介
	private String remarks;//备注
	
	//创建商品的构造函数
	public goods(String goodsNumber,String goodsName,
			String manufacturer,String dateOfProduction,
			String price,String discount,
			String inventory,String productIntroduction,
			String remarks)
	{
		this.goodsNumber = goodsNumber;
		this.goodsName = goodsName;
		this.manufacturer = manufacturer;
		this.dateOfProduction = dateOfProduction;
		this.price = price;
		this.discount = discount;
		this.inventory = inventory;
		this.productIntroduction = productIntroduction;
		this.remarks = remarks;
	}
	
	//适用于创建消费记录的构造函数
	public goods(String goodsName,String price,
			String dateOfProduction,String manufacturer,
			String discount,
			String productIntroduction)
	{
		this.goodsNumber = "--";
		this.goodsName = goodsName;
		this.manufacturer = manufacturer;
		this.dateOfProduction = dateOfProduction;
		this.price = price;
		this.discount = discount;
		this.inventory = "--";
		this.productIntroduction = productIntroduction;
		this.remarks = "--";
	}
	
	////字段设置函数
	public void setGoodsNumber(String set)
	{
		this.goodsNumber = set;
	}
	
	public void setGoodsName(String set)
	{
		this.goodsName = set;
	}
	
	public void setManufacturer(String set)
	{
		this.manufacturer = set;
	}
	
	public void setDateOfProduction(String set)
	{
		this.dateOfProduction = set;
	}
	
	public void setPrice(String set)
	{
		this.price = set;
	}
	
	public void setDiscount(String set)
	{
		this.discount = set;
	}
	
	public void setInventory(String set)
	{
		this.inventory = set;
	}
	
	public void setProductIntroduction(String set)
	{
		this.productIntroduction = set;
	}
	
	public void setRemarks(String set)
	{
		this.remarks = set;
	}
	
	////字段获取函数
	public String getGoodsNumber()
	{
		return this.goodsNumber;
	}
	
	public String getGoodsName()
	{
		return this.goodsName;
	}
	
	public String getManufacturer()
	{
		return this.manufacturer;
	}
	
	public String getDateOfProduction()
	{
		return this.dateOfProduction;
	}
	
	public String getPrice()
	{
		return this.price;
	}
	
	public String getDiscount()
	{
		return this.discount;
	}
	
	public String getInventory()
	{
		return this.inventory;
	}
	
	public String getProductIntroduction()
	{
		return this.productIntroduction;
	}
	
	public String getRemarks()
	{
		return this.remarks;
	}
	
	
	////显示人所有信息
	
}
