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
		LoginGUI gi = new LoginGUI();//实例化
		gi.setTitle("商场VIP消费管理系统-登录");//设置标题
		gi.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//默认关闭状态
		gi.setResizable(false);//窗口是否可拉伸
		gi.setVisible(true);//是否可见
	}
	
	public void BuildGuestGUI(String UserName) //构建普通用户界面的函数
	{
		try
		{	
			GuestGUI guse = new GuestGUI(UserName);//实例化
			guse.setTitle("商场VIP消费管理系统-普通用户");//设置标题
			guse.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);//默认关闭状态
			guse.setVisible(true);//是否可见
		}
		catch(IOException e)
		{
			
		}
	}
	
	public void BuildAdminGUI(String UserName) //构建管理员用户界面的函数
	{
		try
		{	
			AdminGUI Ad = new AdminGUI(UserName);//实例化
			Ad.setTitle("商场VIP消费管理系统-管理员");//设置标题
			Ad.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);//默认关闭状态
			Ad.setVisible(true);//是否可见
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
