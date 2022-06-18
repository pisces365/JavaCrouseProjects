package Login;

import goods.*;
import people.*;

public class Login 
{
	//验证用户信息正确性
	public static boolean isLoginInformationCorrect(String UserName, String Password)
	{
		Guest g;
		if((g = UserOperation.FindUserInformation(UserName)) != null)
		{
			if(g.getPassWord().equals(Password))
			{
				return true;
			}
		}
		return false;
	}
	
	//验证管理员信息是否正确
	public static boolean isAdminLoginInformationCorrect(String UserName, String Password)
	{
		Admin g;
		if((g = UserOperation.FindAdminInformation(UserName)) != null)
		{
			if(g.getPassWord().equals(Password))
			{
				return true;
			}
		}
		return false;
	}
}
