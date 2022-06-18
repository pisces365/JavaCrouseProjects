package Login;

import goods.*;

import javax.swing.JFrame;

import GUI.*;
import Login.*;
import people.*;

import java.awt.Toolkit;
import java.io.*;

public class MainClass {
	public void BuildLoginGUI()//������½����ĺ���
	{
		LoginGUI gi = new LoginGUI();
		gi.setTitle("�̳�VIP���ѹ���ϵͳ-��¼");
		gi.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gi.setResizable(false);
		gi.setVisible(true);
	}
	
	public void BuildGuestGUI(String UserName) //������ͨ�û�����ĺ���
	{
		try
		{	
			GuestGUI guse = new GuestGUI(UserName);
			guse.setTitle("�̳�VIP���ѹ���ϵͳ-��ͨ�û�");
			guse.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			guse.setVisible(true);
		}
		catch(IOException e)
		{
			
		}
	}
	
	public void BuildAdminGUI(String UserName) //��������Ա�û�����ĺ���
	{
		try
		{	
			AdminGUI Ad = new AdminGUI(UserName);
			Ad.setTitle("�̳�VIP���ѹ���ϵͳ-����Ա");
			Ad.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			Ad.setVisible(true);
		}
		catch(IOException e)
		{
			
		}
	}
	
	public static void main(String [] args) throws IOException
	{
		UserOperation.init();//���ó�ʼ������
		goodsOperation.init();
		MainClass m = new MainClass();
		m.BuildLoginGUI();//���������Ĺ����û�����
		

	}
}
