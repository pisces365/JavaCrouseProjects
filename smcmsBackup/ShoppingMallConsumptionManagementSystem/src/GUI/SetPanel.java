package GUI;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class SetPanel extends JPanel
{
	private JLabel PageNameLabel,GuestInfoLabel,ConsumeLabel,GoodsLabel,PersonalLabel,AboutLabel,
			GuestInfoTextLabel,ConsumeTextLabel,GoodsTextLabel,PersonalTextLabel,AboutTextLabel;
	
	private JButton BasicInformationButton,PasswordManageButton;

	private JScrollPane BasicInformationScrollPane,PasswordManageScrollPane;
	
	private JPanel GuestInformationPanel,GuestInformationPanelNorth,BasicInformationPanel,PasswordManagePanel,
		BasicInformationOUTPanel,PasswordManageOUTPanel;
	
	private BorderLayout ThisPanel_BorderLayout;//主页面的边界布局
	private BorderLayout GuestInformationPanel_BorderLayout;//分页面的边界布局
	private BoxLayout GuestInformationPanelNorth_BoxLayout;
	
	String a1 = "查询：在搜索框中输入用户名、姓名、证件号或手机号发起查询，系统根据搜索框中关键字列出对应用户的信息数据。";
	String a2 = "添加：点击添加按钮，系统弹出用户信息添加面板，在对应文本框输入新建用户信息即可。当用户名重复时，系统会提示用户名已存在。";
	String a3 = "编辑：当选中某一用户信息时，可对用户信息进行编辑。系统弹出用户信息编辑面板，可对非关键信息进行修改。";
	String a4 = "删除：当选中某一用户信息时，点击删除按钮即可完成删除操作。";
	String GuestInfo = "<html><body>"+a1+"<br>"+a2+"<br>"+a3+"<br>"+a4+"<body></html>"; 
	
	String b1 = "查询：在用户名搜索框输入已有用户名可进行对用户所有消费记录的查询，在商品名称或购买日期搜索框进行键入可进一步搜索特定消费记录。";
	String b2 = "添加（管理员）：在已有用户名的情况下可进行消费记录添加，系统弹出增加客户消费记录面板，管理员选择商品名称后，其他款项将自动补充。";
	String b3 = "删除（管理员）：当选中某一消费记录时，点击删除按钮即可完成删除操作。";
	String consume = "<html><body>"+b1+"<br>"+b2+"<br>"+b3+"<body></html>";
	
	String c1 = "查询：在搜索框中输入商品名称或编号发起查询，系统根据搜索框中关键字列出对应的商品信息数据。";
	String cc = "购买商品（普通用户）：可根据商品列表选择喜爱商品进行购买。系统弹出商品购买面板，选择商品名称后，其他款项将自动补充。";
	String c2 = "添加（管理员）：点击添加按钮，系统弹出商品信息添加面板，在对应文本框输入新建商品信息即可。当商品名称或编号重复时，系统会提示商品名称或编号已存在。";
	String c3 = "编辑（管理员）：当选中某一商品时，点击编辑按钮，系统弹出商品信息更新面板，可对非关键信息进行修改。";
	String c4 = "删除（管理员）：当选中某一商品信息时，点击删除按钮即可完成删除操作。";
	String goodsInfo = "<html><body>"+c1+"<br>"+cc+"<br>"+c2+"<br>"+c3+"<br>"+c4+"<body></html>";
	
	String d1 = "基础信息：在对应的文本框输入，可对个人信息进行修改和保存。";
	String d2 = "密码管理：在对应的密码框输入，可对密码进行认证、修改和保存。";
	String personal = "<html><body>"+d1+"<br>"+d2+"<body></html>";
	
	String e1 = "商场VIP消费管理系统";
	String e2 = "版本：1.1.0.0";
	String system = "<html><body>"+e1+"<br>"+e2+"<body></html>";
	
	public SetPanel()
	{
		//主页面 页面主标题
		PageNameLabel = new JLabel("系统");
		PageNameLabel.setBorder(new EmptyBorder(0,0,10,0));
		PageNameLabel.setForeground(new Color(19,146,249));
		PageNameLabel.setFont(PageNameLabel.getFont().deriveFont(30.0f));
		
		//客户信息面板 北部按钮
		BasicInformationButton = new JButton("帮助");
		BasicInformationButton.setBorderPainted(false);
		BasicInformationButton.setBackground(new Color(140,189,239));
		BasicInformationButton.setFont(PageNameLabel.getFont().deriveFont(16.0f));
		
		PasswordManageButton = new JButton("系统信息");
		PasswordManageButton.setBorderPainted(false);
		PasswordManageButton.setBackground(new Color(19,146,249));
		PasswordManageButton.setFont(PageNameLabel.getFont().deriveFont(16.0f));
		
		GuestInformationPanelNorth = new JPanel();
		GuestInformationPanelNorth.setBorder(new EmptyBorder(10,0,10,0));
		GuestInformationPanelNorth_BoxLayout = new BoxLayout(GuestInformationPanelNorth,BoxLayout.X_AXIS);
		GuestInformationPanelNorth.setLayout(GuestInformationPanelNorth_BoxLayout);
		GuestInformationPanelNorth.add(BasicInformationButton);
		GuestInformationPanelNorth.add(PasswordManageButton);
		
		//GuestInfoLabel,ConsumeLabel,GoodsLabel,PersonalLabel
		//GuestInfoTextLabel,ConsumeTextLabel,GoodsTextLabel,PersonalTextLabel;
		/////基本信息面板及其组件
		GuestInfoLabel = new JLabel("会员客户信息管理（管理员）");
		GuestInfoLabel.setBorder(new EmptyBorder(10,0,10,0));
		GuestInfoLabel.setFont(GuestInfoLabel.getFont().deriveFont(14.0f));
		//标签初始化
		GuestInfoTextLabel = new JLabel(GuestInfo);
		GuestInfoTextLabel.setBorder(new EmptyBorder(0,10,0,0));
		//标签初始化
		ConsumeLabel = new JLabel("客户消费记录管理（管理员）/消费记录查询（普通用户）");
		ConsumeLabel.setBorder(new EmptyBorder(50,0,10,0));
		ConsumeLabel.setFont(ConsumeLabel.getFont().deriveFont(14.0f));
		//标签初始化
		ConsumeTextLabel = new JLabel(consume);
		ConsumeTextLabel.setBorder(new EmptyBorder(0,10,0,0));
		//标签初始化
		GoodsLabel = new JLabel("库存商品信息管理（管理员）/商品信息查询（普通用户）");
		GoodsLabel.setBorder(new EmptyBorder(50,0,10,0));
		GoodsLabel.setFont(GoodsLabel.getFont().deriveFont(14.0f));
		//标签初始化
		GoodsTextLabel = new JLabel(goodsInfo);
		GoodsTextLabel.setBorder(new EmptyBorder(0,10,0,0));
		//标签初始化
		PersonalLabel = new JLabel("个人信息概览");
		PersonalLabel.setBorder(new EmptyBorder(50,0,10,0));
		PersonalLabel.setFont(PersonalLabel.getFont().deriveFont(14.0f));
		//标签初始化
		PersonalTextLabel = new JLabel(personal);
		PersonalTextLabel.setBorder(new EmptyBorder(0,10,0,0));
		//标签初始化
		BasicInformationPanel = new JPanel();
		BasicInformationPanel.setLayout(new BoxLayout(BasicInformationPanel,BoxLayout.Y_AXIS));
		//标签初始化
		BasicInformationPanel.add(GuestInfoLabel);
		BasicInformationPanel.add(GuestInfoTextLabel);
		BasicInformationPanel.add(ConsumeLabel);
		BasicInformationPanel.add(ConsumeTextLabel);
		BasicInformationPanel.add(GoodsLabel);
		BasicInformationPanel.add(GoodsTextLabel);
		BasicInformationPanel.add(PersonalLabel);
		BasicInformationPanel.add(PersonalTextLabel);
		//标签初始化
		BasicInformationOUTPanel = new JPanel();
		BasicInformationOUTPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		BasicInformationOUTPanel.add(BasicInformationPanel);
		
		BasicInformationScrollPane = new JScrollPane(BasicInformationOUTPanel);
		BasicInformationScrollPane.setBorder(new EmptyBorder(0,0,0,0));
		
		
		/////密码修改面板及其组件
		AboutLabel = new JLabel("关于");
		AboutLabel.setBorder(new EmptyBorder(10,0,10,0));
		AboutLabel.setFont(AboutLabel.getFont().deriveFont(14.0f));
		//标签初始化
		AboutTextLabel = new JLabel(system);    
		AboutTextLabel.setBorder(new EmptyBorder(0,10,0,0));
		//面板
		PasswordManagePanel = new JPanel();
		PasswordManagePanel.setLayout(new BoxLayout(PasswordManagePanel,BoxLayout.Y_AXIS));
		PasswordManagePanel.add(AboutLabel);
		PasswordManagePanel.add(AboutTextLabel);
		//面板
		PasswordManageOUTPanel = new JPanel();
		PasswordManageOUTPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		PasswordManageOUTPanel.add(PasswordManagePanel);
		//面板
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
				SetPanel.this.GuestInformationPanel.remove(PasswordManageScrollPane);
				SetPanel.this.GuestInformationPanel.repaint();
				SetPanel.this.GuestInformationPanel.add(BasicInformationScrollPane,"Center");
				SetPanel.this.GuestInformationPanel.revalidate();
			}
		});
		
		
		/////点击密码管理按钮触发事件
		PasswordManageButton.addActionListener(new ActionListener( ) {
			public void actionPerformed(ActionEvent e)
			{
				//PasswordManageButton.setEnabled(false);
				//BasicInformationButton.setEnabled(true);
				PasswordManageButton.setBackground(new Color(140,189,239));
				BasicInformationButton.setBackground(new Color(19,146,249));
				SetPanel.this.GuestInformationPanel.remove(BasicInformationScrollPane);
				SetPanel.this.GuestInformationPanel.repaint();
				SetPanel.this.GuestInformationPanel.add(PasswordManageScrollPane,"Center");
				SetPanel.this.GuestInformationPanel.revalidate();
			}
		});		
	}
}

