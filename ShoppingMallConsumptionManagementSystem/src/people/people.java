package people;

abstract public class people 
{
	//姓名、证件号、性别、手机号码、联系地址、邮编
	private String Name;
	private String ID;
	private String Sex;
	private String PhoneNum;
	private String Address;
	private String PostCode;
	
	private String UserName;
	private String PassWord;
	
	public people(String Name,String ID,String Sex,String PhoneNum,String Address,String PostCode,String UserName,String PassWord)
	{
		this.Name = Name;
		this.ID = ID;
		this.Sex = Sex;
		this.PhoneNum = PhoneNum;
		this.Address = Address;
		this.PostCode = PostCode;
		this.UserName = UserName;
		this.PassWord = PassWord;
	}
	
	///public void setUserName(String set)
	//{
	//	this.UserName = set;
	//}
	
	public void setPassWord(String set)
	{
		this.PassWord = set;
	}
	
	public void setName(String set)
	{
		this.Name = set;
	}
	
	public void setID(String set)
	{
		this.ID = set;
	}
	
	public void setSex(String set)
	{
		this.Sex = set;
	}
	
	public void setPhoneNum(String set)
	{
		this.PhoneNum = set;
	}
	
	public void setAddress(String set)
	{
		this.Address = set;
	}
	
	public void setPostCode(String set)
	{
		this.PostCode = set;
	}
	
	
	////字段获取函数
	public String getName()
	{
		return this.Name;
	}
	
	public String getID()
	{
		return this.ID;
	}
	
	public String getSex()
	{
		return this.Sex;
	}
	
	public String getPhoneNum()
	{
		return this.PhoneNum;
	}
	
	public String getAddress()
	{
		return this.Address;
	}
	
	public String getPostCode()
	{
		return this.PostCode;
	}
	
	public String getUserName()
	{
		return this.UserName;
	}
	
	public String getPassWord()
	{
		return this.PassWord;
	}
	
	public void SetAllElements(String Name,String ID,String Sex,String PhoneNum,String Address,String PostCode)
	{
		this.Name = Name;
		this.ID = ID;
		this.Sex = Sex;
		this.PhoneNum = PhoneNum;
		this.Address = Address;
		this.PostCode = PostCode;
	}

}
