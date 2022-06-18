package GUI;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.io.*;

import people.*;

class InformationItem extends JPanel
{
	private JLabel name;
	private JTextField text;
	private BoxLayout ThisPanelBoxLayout;
	private JPasswordField password;
	
	public void setPassword(String para)
	{
		password.setText(para);
	}
	
	public String getText()
	{
		return text.getText();
	}
	
	public String getPassword()
	{
		if(String.valueOf(password.getPassword()).equals(""))
		{
			return null;
		}
		return String.valueOf(password.getPassword());
	}
	
	public InformationItem(String JLabelName, String JTextFieldInfo)
	{
		name = new JLabel(JLabelName);
		name.setPreferredSize(new Dimension(100,20));
		text = new JTextField(JTextFieldInfo);
		text.setPreferredSize(new Dimension(500,20));
		
		ThisPanelBoxLayout = new BoxLayout(this,BoxLayout.X_AXIS);
		this.setLayout(ThisPanelBoxLayout);
				
		this.add(name);
		this.add(text);
	}
	
	public InformationItem(String JLabelName)
	{
		name = new JLabel(JLabelName);
		password = new JPasswordField();
		name.setPreferredSize(new Dimension(100,20));
		password.setPreferredSize(new Dimension(500,20));
		
		ThisPanelBoxLayout = new BoxLayout(this,BoxLayout.X_AXIS);
		this.setLayout(ThisPanelBoxLayout);
		
		this.add(name);
		this.add(password);
	}
	
	public InformationItem(String JLabelName, String JTextFieldInfo, boolean isEditable)
	{
		name = new JLabel(JLabelName);
		text = new JTextField(JTextFieldInfo);
		text.setEditable(isEditable);
		name.setPreferredSize(new Dimension(100,20));
		text.setPreferredSize(new Dimension(500,20));
		
		ThisPanelBoxLayout = new BoxLayout(this,BoxLayout.X_AXIS);
		this.setLayout(ThisPanelBoxLayout);
		
		this.add(name);
		this.add(text);
	}
}


public class GuestInformationPanel extends JPanel
{
	private JLabel PageNameLabel,BasicVIPLabel,BasicRegisterLabel,PassWordUpdateLabel;//页面主标题
	
	private JButton BasicInformationButton,PasswordManageButton,
		BasicInformationPanel_ConfirmButton,PasswordManagePanel_ConfirmButton;
	
	private InformationItem Name,ID,PhoneNum,Address,PostCode,BecomeVIPtime;
	public InformationItem OldPassWord,NewPassWord,ConfirmNewPassWord;

	private InformationItemAdmin Sex;
	
	private JScrollPane BasicInformationScrollPane,PasswordManageScrollPane;
	
	private JPanel GuestInformationPanel,GuestInformationPanelNorth,BasicInformationPanel,PasswordManagePanel,
		BasicInformationOUTPanel,PasswordManageOUTPanel;
	
	private BorderLayout ThisPanel_BorderLayout;//主页面的边界布局
	private BorderLayout GuestInformationPanel_BorderLayout;//分页面的边界布局
	private BoxLayout GuestInformationPanelNorth_BoxLayout;
	private GridLayout BasicInformationPanel_GridLayout;
	private GridLayout PasswordManagePanel_GridLayout;
	
	public GuestInformationPanel(Guest ThisGuest)
	{
		//主页面 页面主标题
		PageNameLabel = new JLabel("个人信息概览");
		PageNameLabel.setBorder(new EmptyBorder(0,0,10,0));
		PageNameLabel.setForeground(new Color(19,146,249));
		PageNameLabel.setFont(PageNameLabel.getFont().deriveFont(30.0f));
		
		
		//客户信息面板 北部按钮
		BasicInformationButton = new JButton("基础信息");
		BasicInformationButton.setBorderPainted(false);
		BasicInformationButton.setBackground(new Color(140,189,239));
		BasicInformationButton.setFont(PageNameLabel.getFont().deriveFont(16.0f));
		
		PasswordManageButton = new JButton("密码管理");
		PasswordManageButton.setBorderPainted(false);
		PasswordManageButton.setBackground(new Color(19,146,249));
		PasswordManageButton.setFont(PageNameLabel.getFont().deriveFont(16.0f));
		
		GuestInformationPanelNorth = new JPanel();
		GuestInformationPanelNorth.setBorder(new EmptyBorder(10,0,10,0));
		GuestInformationPanelNorth_BoxLayout = new BoxLayout(GuestInformationPanelNorth,BoxLayout.X_AXIS);
		GuestInformationPanelNorth.setLayout(GuestInformationPanelNorth_BoxLayout);
		GuestInformationPanelNorth.add(BasicInformationButton);
		GuestInformationPanelNorth.add(PasswordManageButton);
		
		
		/////基本信息面板及其组件
		BasicVIPLabel = new JLabel("会员信息");
		BasicVIPLabel.setBorder(new EmptyBorder(10,0,10,0));
		BasicVIPLabel.setFont(PageNameLabel.getFont().deriveFont(14.0f));

		Name = new InformationItem("姓名：",ThisGuest.getName());
		Sex = new InformationItemAdmin("性别：",ThisGuest.getSex(),0);
		PhoneNum = new InformationItem("电话号码：",ThisGuest.getPhoneNum());
		Address = new InformationItem("地址：",ThisGuest.getAddress());
		PostCode = new InformationItem("邮编：",ThisGuest.getPostCode());
		/////基本信息面板及其组件
		BasicRegisterLabel = new JLabel("注册信息");
		BasicRegisterLabel.setBorder(new EmptyBorder(10,0,10,0));
		BasicRegisterLabel.setFont(PageNameLabel.getFont().deriveFont(14.0f));
		/////基本信息面板及其组件
		ID = new InformationItem("用户ID：",ThisGuest.getID(),false);
		BecomeVIPtime = new InformationItem("会员注册时间：",ThisGuest.getBecomeVIPtime(),false);
		/////基本信息面板及其组件
		BasicInformationPanel_ConfirmButton = new JButton("确定");
		BasicInformationPanel_ConfirmButton.setBorderPainted(false);
		BasicInformationPanel_ConfirmButton.setBackground(new Color(140,189,239));
		/////基本信息面板及其组件
		BasicInformationPanel = new JPanel();
		BasicInformationPanel_GridLayout = new GridLayout(0,1,5,5);
		BasicInformationPanel.setLayout(BasicInformationPanel_GridLayout);
		BasicInformationPanel.add(BasicVIPLabel);
		BasicInformationPanel.add(Name);
		BasicInformationPanel.add(Sex);
		BasicInformationPanel.add(PhoneNum);
		BasicInformationPanel.add(Address);
		BasicInformationPanel.add(PostCode);
		BasicInformationPanel.add(BasicRegisterLabel);
		BasicInformationPanel.add(ID);
		BasicInformationPanel.add(BecomeVIPtime);
		BasicInformationPanel.add(BasicInformationPanel_ConfirmButton);
		/////基本信息面板及其组件
		BasicInformationOUTPanel = new JPanel();
		BasicInformationOUTPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		BasicInformationOUTPanel.add(BasicInformationPanel);
		
		BasicInformationScrollPane = new JScrollPane(BasicInformationOUTPanel);
		BasicInformationScrollPane.setBorder(new EmptyBorder(0,0,0,0));
		
		
		/////密码修改面板及其组件
		PassWordUpdateLabel = new JLabel("密码更新");
		PassWordUpdateLabel.setBorder(new EmptyBorder(10,0,10,0));
		PassWordUpdateLabel.setFont(PageNameLabel.getFont().deriveFont(14.0f));
		
		OldPassWord = new InformationItem("当前密码：");
		NewPassWord = new InformationItem("新密码：");
		ConfirmNewPassWord = new InformationItem("确认新密码：");
	    /////密码修改面板及其组件
		PasswordManagePanel_ConfirmButton = new JButton("确定");
		PasswordManagePanel_ConfirmButton.setBorderPainted(false);
		PasswordManagePanel_ConfirmButton.setBackground(new Color(140,189,239));
		/////密码修改面板及其组件
		PasswordManagePanel = new JPanel();
		PasswordManagePanel_GridLayout = new GridLayout(0,1,5,5);
		PasswordManagePanel.setLayout(PasswordManagePanel_GridLayout);
		PasswordManagePanel.add(PassWordUpdateLabel);
		PasswordManagePanel.add(OldPassWord);
		PasswordManagePanel.add(NewPassWord);
		PasswordManagePanel.add(ConfirmNewPassWord);
		PasswordManagePanel.add(PasswordManagePanel_ConfirmButton);
		/////密码修改面板及其组件
		PasswordManageOUTPanel = new JPanel();
		PasswordManageOUTPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		PasswordManageOUTPanel.add(PasswordManagePanel);
		/////密码修改面板及其组件	
		PasswordManageScrollPane = new JScrollPane(PasswordManageOUTPanel);
		PasswordManageScrollPane.setBorder(new EmptyBorder(0,0,0,0));
		JScrollBar bar = PasswordManageScrollPane.getVerticalScrollBar();
		bar.setForeground(Color.black);
		
		/////基础信息与密码管理分页面
		GuestInformationPanel = new JPanel();
		GuestInformationPanel_BorderLayout = new BorderLayout();
		GuestInformationPanel.setLayout(GuestInformationPanel_BorderLayout);
		GuestInformationPanel.add(GuestInformationPanelNorth,"North");
		GuestInformationPanel.add(BasicInformationScrollPane,"Center");
		
		
		/////主面板
		ThisPanel_BorderLayout = new BorderLayout();
		this.setLayout(ThisPanel_BorderLayout);
		this.add(PageNameLabel,"North");
		this.add(GuestInformationPanel,"Center");
		this.setBorder(new EmptyBorder(14,14,0,0));
		
		/////点击基础信息按钮触发事件
		BasicInformationButton.addActionListener(new ActionListener( ) {
			public void actionPerformed(ActionEvent e)
			{
				//BasicInformationButton.setEnabled(false);
				//PasswordManageButton.setEnabled(true);
				BasicInformationButton.setBackground(new Color(140,189,239));
				PasswordManageButton.setBackground(new Color(19,146,249));
				GuestInformationPanel.this.GuestInformationPanel.remove(PasswordManageScrollPane);
				GuestInformationPanel.this.GuestInformationPanel.repaint();
				GuestInformationPanel.this.GuestInformationPanel.add(BasicInformationScrollPane,"Center");
				GuestInformationPanel.this.GuestInformationPanel.revalidate();
			}
		});
		
		
		/////点击密码管理按钮触发事件
		PasswordManageButton.addActionListener(new ActionListener( ) {
			public void actionPerformed(ActionEvent e)
			{
				OldPassWord.setPassword("");
				NewPassWord.setPassword("");
				ConfirmNewPassWord.setPassword("");
				//PasswordManageButton.setEnabled(false);
				//BasicInformationButton.setEnabled(true);
				PasswordManageButton.setBackground(new Color(140,189,239));
				BasicInformationButton.setBackground(new Color(19,146,249));
				GuestInformationPanel.this.GuestInformationPanel.remove(BasicInformationScrollPane);
				GuestInformationPanel.this.GuestInformationPanel.repaint();
				GuestInformationPanel.this.GuestInformationPanel.add(PasswordManageScrollPane,"Center");
				GuestInformationPanel.this.GuestInformationPanel.revalidate();
			}
		});
		
		
		/////点击基础信息确认按钮触发事件
		BasicInformationPanel_ConfirmButton.addActionListener(new ActionListener( ) {
			public void actionPerformed(ActionEvent e)
			{
				JOptionPane.showMessageDialog(GuestInformationPanel.this, "基础信息修改成功！");
				ThisGuest.SetAllElements(Name.getText(), ID.getText(), Sex.getSex(), PhoneNum.getText(), Address.getText(), PostCode.getText());
				UserOperation.UpdateUserInformation(ThisGuest);
				UserOperation.writeIn();	
			}
		});
		
		
		/////点击密码管理确认按钮触发事件
		PasswordManagePanel_ConfirmButton.addActionListener(new ActionListener( ) {
			public void actionPerformed(ActionEvent e)
			{
				if(OldPassWord.getPassword() == null || NewPassWord.getPassword() == null || ConfirmNewPassWord.getPassword() == null)
				{
					JOptionPane.showMessageDialog(GuestInformationPanel.this, "密码不可为空");
					return;
				}
				if(OldPassWord.getPassword().equals(ThisGuest.getPassWord()) && NewPassWord.getPassword() != null && ConfirmNewPassWord.getPassword() != null && NewPassWord.getPassword().equals(ConfirmNewPassWord.getPassword()))
				{
					ThisGuest.setPassWord(NewPassWord.getPassword());
					UserOperation.UpdateUserInformation(ThisGuest);
					UserOperation.writeIn();
					JOptionPane.showMessageDialog(GuestInformationPanel.this, "密码修改成功！");
					OldPassWord.setPassword("");
					NewPassWord.setPassword("");
					ConfirmNewPassWord.setPassword("");
				}
				else
				{
					JOptionPane.showMessageDialog(GuestInformationPanel.this, "当前密码不正确或新密码不一致！");
				}
			}
		});
	}
}
