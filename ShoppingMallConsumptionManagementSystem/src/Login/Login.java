package Login;

import goods.*;
import people.*;

public class Login 
{
	//��֤�û���Ϣ��ȷ��
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
	
	//��֤����Ա��Ϣ�Ƿ���ȷ
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
