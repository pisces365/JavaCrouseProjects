package Login;

import goods.*;

import javax.swing.JFrame;

import GUI.*;
import Login.*;
import people.*;

import java.awt.Toolkit;
import java.io.*;

public class MainClass {
	public void BuildLoginGUI()//构建登陆界面的函数
	{
		LoginGUI gi = new LoginGUI();
		gi.setTitle("商场VIP消费管理系统-登录");
		gi.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gi.setResizable(false);
		gi.setVisible(true);
	}
	
	public void BuildGuestGUI(String UserName) //构建普通用户界面的函数
	{
		try
		{	
			GuestGUI guse = new GuestGUI(UserName);
			guse.setTitle("商场VIP消费管理系统-普通用户");
			guse.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			guse.setVisible(true);
		}
		catch(IOException e)
		{
			
		}
	}
	
	public void BuildAdminGUI(String UserName) //构建管理员用户界面的函数
	{
		try
		{	
			AdminGUI Ad = new AdminGUI(UserName);
			Ad.setTitle("商场VIP消费管理系统-管理员");
			Ad.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			Ad.setVisible(true);
		}
		catch(IOException e)
		{
			
		}
	}
	
	public static void main(String [] args) throws IOException
	{
		UserOperation.init();//调用初始化函数
		goodsOperation.init();
		MainClass m = new MainClass();
		m.BuildLoginGUI();//首先主动的构建用户界面
		

	}
}
