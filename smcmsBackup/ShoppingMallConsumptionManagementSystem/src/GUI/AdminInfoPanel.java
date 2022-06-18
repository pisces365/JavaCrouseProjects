package GUI;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import people.*;




public class AdminInfoPanel extends JPanel
{
	private JLabel PageNameLabel,BasicVIPLabel,BasicRegisterLabel,PassWordUpdateLabel;//页面主标题
	
	private JButton BasicInformationButton,PasswordManageButton,//主要按钮
		BasicInformationPanel_ConfirmButton,PasswordManagePanel_ConfirmButton;
	
	private InformationItemAdmin Name,ID,Sex,PhoneNum,Address,PostCode;//组件类
	public InformationItemAdmin OldPassWord,NewPassWord,ConfirmNewPassWord;

	private JScrollPane BasicInformationScrollPane,PasswordManageScrollPane;
	
	private JPanel GuestInformationPanel,GuestInformationPanelNorth,BasicInformationPanel,PasswordManagePanel,
		BasicInformationOUTPanel,PasswordManageOUTPanel;//功能页面面板
	
	private BorderLayout ThisPanel_BorderLayout;//主页面的边界布局
	private BorderLayout GuestInformationPanel_BorderLayout;//分页面的边界布局
	private BoxLayout GuestInformationPanelNorth_BoxLayout;//盒式布局
	private GridLayout BasicInformationPanel_GridLayout;//网格布局
	private GridLayout PasswordManagePanel_GridLayout;
	
	public AdminInfoPanel(Admin ThisGuest)
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
		BasicVIPLabel = new JLabel("管理员信息");
		BasicVIPLabel.setBorder(new EmptyBorder(10,0,10,0));
		BasicVIPLabel.setFont(PageNameLabel.getFont().deriveFont(14.0f));

		Name = new InformationItemAdmin("姓名：",ThisGuest.getName());
		Sex = new InformationItemAdmin("性别：",ThisGuest.getSex(),0);
		PhoneNum = new InformationItemAdmin("电话号码：",ThisGuest.getPhoneNum());
		Address = new InformationItemAdmin("地址：",ThisGuest.getAddress());
		PostCode = new InformationItemAdmin("邮编：",ThisGuest.getPostCode());
		
		BasicRegisterLabel = new JLabel("身份信息");
		BasicRegisterLabel.setBorder(new EmptyBorder(10,0,10,0));
		BasicRegisterLabel.setFont(PageNameLabel.getFont().deriveFont(14.0f));
		
		ID = new InformationItemAdmin("用户ID：",ThisGuest.getID(),false);
	
		BasicInformationPanel_ConfirmButton = new JButton("确定");
		BasicInformationPanel_ConfirmButton.setBorderPainted(false);
		BasicInformationPanel_ConfirmButton.setBackground(new Color(140,189,239));
		//基本面板进行组件添加
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
		BasicInformationPanel.add(BasicInformationPanel_ConfirmButton);
		//外部嵌套面板
		BasicInformationOUTPanel = new JPanel();
		BasicInformationOUTPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		BasicInformationOUTPanel.add(BasicInformationPanel);
		//滚动面板初始化
		BasicInformationScrollPane = new JScrollPane(BasicInformationOUTPanel);
		BasicInformationScrollPane.setBorder(new EmptyBorder(0,0,0,0));
		
		
		/////密码修改面板及其组件
		PassWordUpdateLabel = new JLabel("密码更新");
		PassWordUpdateLabel.setBorder(new EmptyBorder(10,0,10,0));
		PassWordUpdateLabel.setFont(PageNameLabel.getFont().deriveFont(14.0f));
		
		OldPassWord = new InformationItemAdmin("当前密码：");
		NewPassWord = new InformationItemAdmin("新密码：");
		ConfirmNewPassWord = new InformationItemAdmin("确认新密码：");
		//确定按钮初始化
		PasswordManagePanel_ConfirmButton = new JButton("确定");
		PasswordManagePanel_ConfirmButton.setBorderPainted(false);
		PasswordManagePanel_ConfirmButton.setBackground(new Color(140,189,239));
		//密码管理面板初始化
		PasswordManagePanel = new JPanel();
		PasswordManagePanel_GridLayout = new GridLayout(0,1,5,5);
		PasswordManagePanel.setLayout(PasswordManagePanel_GridLayout);
		PasswordManagePanel.add(PassWordUpdateLabel);
		PasswordManagePanel.add(OldPassWord);
		PasswordManagePanel.add(NewPassWord);
		PasswordManagePanel.add(ConfirmNewPassWord);
		PasswordManagePanel.add(PasswordManagePanel_ConfirmButton);
		//外部嵌套面板初始化
		PasswordManageOUTPanel = new JPanel();
		PasswordManageOUTPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		PasswordManageOUTPanel.add(PasswordManagePanel);
		
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
				AdminInfoPanel.this.GuestInformationPanel.remove(PasswordManageScrollPane);
				AdminInfoPanel.this.GuestInformationPanel.repaint();
				AdminInfoPanel.this.GuestInformationPanel.add(BasicInformationScrollPane,"Center");
				AdminInfoPanel.this.GuestInformationPanel.revalidate();
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
				AdminInfoPanel.this.GuestInformationPanel.remove(BasicInformationScrollPane);
				AdminInfoPanel.this.GuestInformationPanel.repaint();
				AdminInfoPanel.this.GuestInformationPanel.add(PasswordManageScrollPane,"Center");
				AdminInfoPanel.this.GuestInformationPanel.revalidate();
			}
		});
		
		
		/////点击基础信息确认按钮触发事件
		BasicInformationPanel_ConfirmButton.addActionListener(new ActionListener( ) {
			public void actionPerformed(ActionEvent e)
			{
				JOptionPane.showMessageDialog(AdminInfoPanel.this, "基础信息修改成功！");
				ThisGuest.SetAllElements(Name.getText(), ID.getText(), Sex.getSex(), PhoneNum.getText(), Address.getText(), PostCode.getText());
				UserOperation.UpdateAdminInformation(ThisGuest);
				UserOperation.AdminwriteIn();	
			}
		});
		
		
		/////点击密码管理确认按钮触发事件
		PasswordManagePanel_ConfirmButton.addActionListener(new ActionListener( ) {
			public void actionPerformed(ActionEvent e)
			{
				if(OldPassWord.getPassword() == null || NewPassWord.getPassword() == null || ConfirmNewPassWord.getPassword() == null)
				{
					JOptionPane.showMessageDialog(AdminInfoPanel.this, "密码不可为空");
					return;
				}
				if(OldPassWord.getPassword().equals(ThisGuest.getPassWord()) && NewPassWord.getPassword() != null && ConfirmNewPassWord.getPassword() != null && NewPassWord.getPassword().equals(ConfirmNewPassWord.getPassword()))
				{//验证新旧密码符合标准
					ThisGuest.setPassWord(NewPassWord.getPassword());
					UserOperation.UpdateAdminInformation(ThisGuest);
					UserOperation.AdminwriteIn();
					JOptionPane.showMessageDialog(AdminInfoPanel.this, "密码修改成功！");
					OldPassWord.setPassword("");
					NewPassWord.setPassword("");
					ConfirmNewPassWord.setPassword("");
				}
				else
				{
					JOptionPane.showMessageDialog(AdminInfoPanel.this, "当前密码不正确或新密码不一致！");
				}
			}
		});
	}
}

