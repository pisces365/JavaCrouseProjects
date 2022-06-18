package people;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

//管理员初始化函数
public class Admin extends people{
	public Admin(String Name,String ID,String Sex,String PhoneNum,String Address,String PostCode,String UserName,String PassWord)
	{
		super(Name, ID, Sex, PhoneNum, Address, PostCode, UserName, PassWord);
	}
}
