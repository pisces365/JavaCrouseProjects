package goods;

public class goods 
{
	private String goodsNumber;//��Ʒ���
	private String goodsName;//����
	private String manufacturer;//������
	private String dateOfProduction;//��������
	private String price;//�۸�
	private String discount;//�ۿ���
	private String inventory;//���
	private String productIntroduction;//��Ʒ���
	private String remarks;//��ע
	
	//������Ʒ�Ĺ��캯��
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
	
	//�����ڴ������Ѽ�¼�Ĺ��캯��
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
	
	////�ֶ����ú���
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
	
	////�ֶλ�ȡ����
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
	
	
	////��ʾ��������Ϣ
	
}
