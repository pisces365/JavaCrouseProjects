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
		LoginGUI gi = new LoginGUI();//ʵ����
		gi.setTitle("�̳�VIP���ѹ���ϵͳ-��¼");//���ñ���
		gi.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//Ĭ�Ϲر�״̬
		gi.setResizable(false);//�����Ƿ������
		gi.setVisible(true);//�Ƿ�ɼ�
	}
	
	public void BuildGuestGUI(String UserName) //������ͨ�û�����ĺ���
	{
		try
		{	
			GuestGUI guse = new GuestGUI(UserName);//ʵ����
			guse.setTitle("�̳�VIP���ѹ���ϵͳ-��ͨ�û�");//���ñ���
			guse.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);//Ĭ�Ϲر�״̬
			guse.setVisible(true);//�Ƿ�ɼ�
		}
		catch(IOException e)
		{
			
		}
	}
	
	public void BuildAdminGUI(String UserName) //��������Ա�û�����ĺ���
	{
		try
		{	
			AdminGUI Ad = new AdminGUI(UserName);//ʵ����
			Ad.setTitle("�̳�VIP���ѹ���ϵͳ-����Ա");//���ñ���
			Ad.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);//Ĭ�Ϲر�״̬
			Ad.setVisible(true);//�Ƿ�ɼ�
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
