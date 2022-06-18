package GUI;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import Login.Login;
import Login.MainClass;
import people.*;
import java.io.*;



public class AdminGUI extends JFrame implements MainGUIInterface
{
	enum LeftSelectPanel_openPanelButtonConditionEnum//汉堡菜单关闭和打开的枚举
	{
		CLOSE,
		OPEN
	}
	
	private JLabel JLayeredPane_backgroundLabel;
	
	private ImageIcon background;
	
	private Admin ThisAdmin;
	private JButton LeftSelectPanel_openPanelButton,//汉堡菜单的按钮
			LeftSelectPanel_ReturnRightOperationPanelButton,
			LeftSelectPanel_ConsumptionRecordButton, 
			LeftSelectPanel_GoodsInformationButton, 
			LeftSelectPanel_GuestInformationButton;
	private LeftSelectPanel_openPanelButtonConditionEnum LeftSelectPanel_openPanelButtonCondition = LeftSelectPanel_openPanelButtonConditionEnum.CLOSE;
	
	private JButton RightOperation_SelectPanel_ConsumptionRecordButton, //主页菜单的按钮
			RightOperation_SelectPanel_GoodsInformationButton, 
			RightOperation_SelectPanel_GuestInformationButton;
	private ImageIcon ConsumptionRecordIcon,GoodsInformationIcon,GuestInformationIcon;
	private ImageIcon LEFTopenPanelIcon,LEFTHomeIcon,LEFTConsumptionRecordIcon,LEFTGoodsInformationIcon,LEFTGuestInformationIcon;
	
	private JMenuBar TopMenuBar;//工具栏
	private JMenu HelpMenu;
	private JMenuItem HelpInformation,AboutThisProgram;
	
	private JLabel RightOperationPanel_UserNameLabel,RightOperationPanel_ContextLabel;//欢迎标签
	
	private JScrollPane RightOperationPanel_ScrollPane;
	private JPanel FramePanel,MainPanel,LeftFlowLayoutPanel, LeftSelectPanel,//主页面的切换面板
			RightOperationPanel,RightOperation_SelectPanel,RightOperation_NorthPanel,//主页面的切换面板
			RightOperation_GridNorthPanel,RightOperationPanel_ContextPanel,//主页面的切换面板
			JLayeredPane_backgroundPanel;//主页面的切换面板
	private boolean isRightOperationPanelUsed = true;
	
	private AdminInfoPanel aAdminInfoPanel;//功能面板
	private boolean isAdminInformationPanelUsed = false;
	private AdminGoodsSearchPanel aAdminGoodsSearchPanel;//功能面板
	private boolean isAdminGoodsSearchPanel = false;
	private AdminPurchaseHistoryPanel aAdminPurchaseHistoryPanel;//功能面板
	private boolean isAdminPurchaseHistoryPanel = false;
	private AdminUserInformationManagement aAdminUserInformationManagement;//功能面板
	private boolean isAdminUserInformationManagement = false;
	private SetPanel aSetPanel;//功能面板
	private boolean isSetPanel = false;
	
	private BorderLayout MainPanel_BorderLayout, RightOperationPanel_BorderLayout;//盒式布局
	private GridLayout FramePanel_GridLayout, LeftSelectPanel_GridLayout;//网格布局
	private FlowLayout LeftFlowLayoutPanel_FlowLayout;//流式布局
	private FlowLayout RightOperation_SelectPanelFlowLayout;//
	//右侧按钮和其图片变量
	private JButton RightOperation_SelectPanel_UserInformationManagementButton;

	private ImageIcon UserInformationManagementIcon;
	//右侧按钮和其图片变量
	private JButton LeftSelectPanel_UserInformationManagementButton;

	private ImageIcon LEFTUserInformationManagementIcon;
	//右侧按钮和其图片变量
	private JPanel LeftFlowLayoutOUTPanel;

	private BorderLayout LeftFlowLayoutOUTPanel_BorderLayout;
	//右侧按钮和其图片变量
	private JButton LeftSelectPanel_SetButton;

	private ImageIcon LEFTSetIcon;
	//嵌套在汉堡面板的面板
	private JPanel LeftFlowLayoutSouthPanel;

	private FlowLayout LeftFlowLayoutSouthPanel_FlowLayout;

	private JPanel GridPanel;

	private GridLayout GridPanel_GridLayout;

	public void setFrame()
	{
		setSize(1010, 600);
		int windowWidth = this.getWidth(); //获得窗口宽
		int windowHeight = this.getHeight(); //获得窗口高
		Toolkit kit = Toolkit.getDefaultToolkit(); //定义工具包
		Dimension screenSize = kit.getScreenSize(); //获取屏幕的尺寸
		int screenWidth = screenSize.width; //获取屏幕的宽
		int screenHeight = screenSize.height; //获取屏幕的高
		this.setLocation(screenWidth / 2 - windowWidth / 2, screenHeight / 2 - windowHeight / 2);//设置窗口居中显示
	}

	public AdminGUI(String UserName) throws IOException
	{
		setFrame();
		//初始化功能面板
		aSetPanel = new SetPanel();
		aAdminUserInformationManagement = new AdminUserInformationManagement(this);
		aAdminGoodsSearchPanel = new AdminGoodsSearchPanel(this);
		//初始化功能面板
		ThisAdmin = UserOperation.FindAdminInformation(UserName);
		aAdminInfoPanel = new AdminInfoPanel(AdminGUI.this.ThisAdmin);
		
		aAdminPurchaseHistoryPanel = new AdminPurchaseHistoryPanel(this);
		
		this.addWindowListener(new WindowAdapter() {//添加加返回登录界面的监听
			public void windowClosing(WindowEvent e)
			{
				AdminGUI.this.dispose();
				MainClass mm = new MainClass();
				mm.BuildLoginGUI();
			}
		});
		
		//工具栏初始化
		HelpInformation = new JMenuItem("使用小贴士");
		AboutThisProgram = new JMenuItem("关于…");
		AboutThisProgram.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				JOptionPane.showMessageDialog(AdminGUI.this, "商场VIP消费管理系统\n版本：1.1.0.0", "系统信息", -1);
			}
		});
		
		HelpMenu = new JMenu("系统信息");
		//HelpMenu.add(HelpInformation);
		HelpMenu.add(AboutThisProgram);
		
		TopMenuBar = new JMenuBar();
		TopMenuBar.add(HelpMenu);
		
		//左侧汉堡菜单 打开面板按钮
		LeftSelectPanel_openPanelButton = new JButton("",LEFTopenPanelIcon = new ImageIcon(new File("").getCanonicalPath()+"/image/GuestGUIButton/openPanelIcon.png"));
		LEFTopenPanelIcon.setImage(LEFTopenPanelIcon.getImage().getScaledInstance(40,40,Image.SCALE_SMOOTH)); 
		LeftSelectPanel_openPanelButton.setHorizontalAlignment(JButton.LEFT);
		LeftSelectPanel_openPanelButton.setBorderPainted(false);
		LeftSelectPanel_openPanelButton.setBackground(new Color(140,189,239));
		LeftSelectPanel_openPanelButton.setMargin(new Insets(0,0,0,0));
		
		//左侧汉堡菜单 主页按钮
		LeftSelectPanel_ReturnRightOperationPanelButton = new JButton("   主页",LEFTHomeIcon = new ImageIcon(new File("").getCanonicalPath()+"/image/GuestGUIButton/homeIcon.png"));
		LeftSelectPanel_ReturnRightOperationPanelButton.setEnabled(false);
		LEFTHomeIcon.setImage(LEFTHomeIcon.getImage().getScaledInstance(40,40,Image.SCALE_SMOOTH)); 
		LeftSelectPanel_ReturnRightOperationPanelButton.setHorizontalAlignment(JButton.LEFT);
		LeftSelectPanel_ReturnRightOperationPanelButton.setBorderPainted(false);
		LeftSelectPanel_ReturnRightOperationPanelButton.setBackground(new Color(140,189,239));
		LeftSelectPanel_ReturnRightOperationPanelButton.setMargin(new Insets(0,0,0,0));
		
		//左侧汉堡菜单 客户信息管理按钮
		LeftSelectPanel_UserInformationManagementButton = new JButton("   会员客户信息管理",LEFTUserInformationManagementIcon = new ImageIcon(new File("").getCanonicalPath()+"/image/GuestGUIButton/AdminUserInformationManageIcon.png"));
		LEFTUserInformationManagementIcon.setImage(LEFTUserInformationManagementIcon.getImage().getScaledInstance(40,40,Image.SCALE_SMOOTH)); 
		LeftSelectPanel_UserInformationManagementButton.setHorizontalAlignment(JButton.LEFT);
		LeftSelectPanel_UserInformationManagementButton.setBorderPainted(false);
		LeftSelectPanel_UserInformationManagementButton.setBackground(new Color(140,189,239));
		LeftSelectPanel_UserInformationManagementButton.setMargin(new Insets(0,0,0,0));
		
		//左侧汉堡菜单 消费记录按钮
		LeftSelectPanel_ConsumptionRecordButton = new JButton("   客户消费记录管理",LEFTConsumptionRecordIcon = new ImageIcon(new File("").getCanonicalPath()+"/image/GuestGUIButton/ConsumptionRecordIcon.png"));
		LEFTConsumptionRecordIcon.setImage(LEFTConsumptionRecordIcon.getImage().getScaledInstance(40,40,Image.SCALE_SMOOTH)); 
		LeftSelectPanel_ConsumptionRecordButton.setHorizontalAlignment(JButton.LEFT);
		LeftSelectPanel_ConsumptionRecordButton.setBorderPainted(false);
		LeftSelectPanel_ConsumptionRecordButton.setBackground(new Color(140,189,239));
		LeftSelectPanel_ConsumptionRecordButton.setMargin(new Insets(0,0,0,0));
		
		////左侧汉堡菜单 商品查询按钮
		LeftSelectPanel_GoodsInformationButton = new JButton("   库存商品信息管理",LEFTGoodsInformationIcon = new ImageIcon(new File("").getCanonicalPath()+"/image/GuestGUIButton/GoodsInformationIcon.png"));
		LEFTGoodsInformationIcon.setImage(LEFTGoodsInformationIcon.getImage().getScaledInstance(40,40,Image.SCALE_SMOOTH)); 
		LeftSelectPanel_GoodsInformationButton.setHorizontalAlignment(JButton.LEFT);
		LeftSelectPanel_GoodsInformationButton.setBorderPainted(false);
		LeftSelectPanel_GoodsInformationButton.setBackground(new Color(140,189,239));
		LeftSelectPanel_GoodsInformationButton.setMargin(new Insets(0,0,0,0));
		
		//左侧汉堡菜单 个人信息概览按钮
		LeftSelectPanel_GuestInformationButton = new JButton("   个人信息概览",LEFTGuestInformationIcon = new ImageIcon(new File("").getCanonicalPath()+"/image/GuestGUIButton/GuestInformationIcon.png"));
		LEFTGuestInformationIcon.setImage(LEFTGuestInformationIcon.getImage().getScaledInstance(40,40,Image.SCALE_SMOOTH)); 
		LeftSelectPanel_GuestInformationButton.setHorizontalAlignment(JButton.LEFT);
		LeftSelectPanel_GuestInformationButton.setBorderPainted(false);
		LeftSelectPanel_GuestInformationButton.setBackground(new Color(140,189,239));
		LeftSelectPanel_GuestInformationButton.setMargin(new Insets(0,0,0,0));
		
		//左侧设置菜单 设置按钮
		LeftSelectPanel_SetButton = new JButton("   系统",LEFTSetIcon = new ImageIcon(new File("").getCanonicalPath()+"/image/GuestGUIButton/SetIcon.png"));
		LEFTSetIcon.setImage(LEFTSetIcon.getImage().getScaledInstance(40,40,Image.SCALE_SMOOTH)); 
		LeftSelectPanel_SetButton.setHorizontalAlignment(JButton.LEFT);
		LeftSelectPanel_SetButton.setBorderPainted(false);
		LeftSelectPanel_SetButton.setBackground(new Color(140,189,239));
		LeftSelectPanel_SetButton.setMargin(new Insets(0,0,0,0));
		
		LeftSelectPanel = new JPanel();
		LeftSelectPanel.setPreferredSize(new Dimension(80,300));
		LeftSelectPanel_GridLayout = new GridLayout(0,1,0,0);
		LeftSelectPanel.setLayout(LeftSelectPanel_GridLayout);
		LeftSelectPanel.add(LeftSelectPanel_openPanelButton);
		LeftSelectPanel.add(LeftSelectPanel_ReturnRightOperationPanelButton);
		LeftSelectPanel.add(LeftSelectPanel_UserInformationManagementButton);
		LeftSelectPanel.add(LeftSelectPanel_ConsumptionRecordButton);
		LeftSelectPanel.add(LeftSelectPanel_GoodsInformationButton);
		LeftSelectPanel.add(LeftSelectPanel_GuestInformationButton);
		//LeftSelectPanel.setBorder(BorderFactory.createLineBorder(Color.RED,2));
		LeftSelectPanel.setOpaque(false);
		/////////////////////////////右侧选择按钮的初始化
		RightOperation_SelectPanel_UserInformationManagementButton = new JButton("会员客户信息管理",UserInformationManagementIcon = new ImageIcon(new File("").getCanonicalPath()+"/image/GuestGUIButton/AdminUserInformationManageIcon.png"));
		UserInformationManagementIcon.setImage(UserInformationManagementIcon.getImage().getScaledInstance(100,100,Image.SCALE_DEFAULT)); 
		RightOperation_SelectPanel_UserInformationManagementButton.setPreferredSize(new Dimension(300,200));
		RightOperation_SelectPanel_UserInformationManagementButton.setBackground(new Color(140,189,239));
		/////////////////////////////右侧选择按钮的初始化

		RightOperation_SelectPanel_ConsumptionRecordButton = new JButton("客户消费记录管理",ConsumptionRecordIcon = new ImageIcon(new File("").getCanonicalPath()+"/image/GuestGUIButton/ConsumptionRecordIcon.png"));
		ConsumptionRecordIcon.setImage(ConsumptionRecordIcon.getImage().getScaledInstance(100,100,Image.SCALE_DEFAULT)); 
		RightOperation_SelectPanel_ConsumptionRecordButton.setPreferredSize(new Dimension(300,200));
		RightOperation_SelectPanel_ConsumptionRecordButton.setBackground(new Color(140,189,239));
        /////////////////////////////右侧选择按钮的初始化
		RightOperation_SelectPanel_GoodsInformationButton = new JButton("库存商品信息管理",GoodsInformationIcon = new ImageIcon(new File("").getCanonicalPath()+"/image/GuestGUIButton/GoodsInformationIcon.png"));
		GoodsInformationIcon.setImage(GoodsInformationIcon.getImage().getScaledInstance(100,100,Image.SCALE_DEFAULT)); 
		RightOperation_SelectPanel_GoodsInformationButton.setPreferredSize(new Dimension(300,200));
		RightOperation_SelectPanel_GoodsInformationButton.setBackground(new Color(140,189,239));
        /////////////////////////////右侧选择按钮的初始化
		RightOperation_SelectPanel_GuestInformationButton = new JButton("个人信息概览",GuestInformationIcon = new ImageIcon(new File("").getCanonicalPath()+"/image/GuestGUIButton/GuestInformationIcon.png"));
		GuestInformationIcon.setImage(GuestInformationIcon.getImage().getScaledInstance(100,100,Image.SCALE_DEFAULT)); 
		RightOperation_SelectPanel_GuestInformationButton.setPreferredSize(new Dimension(300,200));
		RightOperation_SelectPanel_GuestInformationButton.setBackground(new Color(140,189,239));
		//右侧选择面板的初始化
		RightOperation_SelectPanel = new JPanel();
		RightOperation_SelectPanel.setLayout(RightOperation_SelectPanelFlowLayout = new FlowLayout(FlowLayout.LEFT,14,14));////
		RightOperation_SelectPanel.setPreferredSize(new Dimension(1,1));
		RightOperation_SelectPanel.add(RightOperation_SelectPanel_UserInformationManagementButton);
		RightOperation_SelectPanel.add(RightOperation_SelectPanel_ConsumptionRecordButton);
		RightOperation_SelectPanel.add(RightOperation_SelectPanel_GoodsInformationButton);
		RightOperation_SelectPanel.add(RightOperation_SelectPanel_GuestInformationButton);
		//上部背景初始化
		JLayeredPane_backgroundLabel = new JLabel(background = new ImageIcon(new File("").getCanonicalPath()+"/image/GuestGUIBackground.png"),JLabel.RIGHT);
		
		JLayeredPane_backgroundPanel = new JPanel();
		JLayeredPane_backgroundPanel.setLayout(new GridLayout(1,1,0,0));
		JLayeredPane_backgroundPanel.add(JLayeredPane_backgroundLabel);
		JLayeredPane_backgroundPanel.setOpaque(false);
		JLayeredPane_backgroundPanel.setBorder(new EmptyBorder(50,0,0,0));
		//欢迎标语初始化
		RightOperationPanel_UserNameLabel = new JLabel("你好！超级管理员"+ThisAdmin.getName()+"！");
		RightOperationPanel_UserNameLabel.setForeground(Color.white);
		RightOperationPanel_UserNameLabel.setFont(RightOperationPanel_UserNameLabel.getFont().deriveFont(30.0f));
		
		RightOperationPanel_ContextLabel = new JLabel("点击汉堡菜单按钮或主页按钮进行操作");
		RightOperationPanel_ContextLabel.setForeground(Color.white);
		RightOperationPanel_ContextLabel.setFont(RightOperationPanel_UserNameLabel.getFont().deriveFont(16.0f));
		RightOperationPanel_ContextLabel.setHorizontalAlignment(JLabel.LEFT);
		
		RightOperationPanel_ContextPanel = new JPanel();
		RightOperationPanel_ContextPanel.setLayout(new BoxLayout(RightOperationPanel_ContextPanel,BoxLayout.Y_AXIS));
		RightOperationPanel_ContextPanel.add(RightOperationPanel_UserNameLabel);//布局
		RightOperationPanel_ContextPanel.add(RightOperationPanel_ContextLabel);
		RightOperationPanel_ContextPanel.setOpaque(false);
		
		//上方面板
		RightOperation_NorthPanel = new JPanel();
		RightOperation_NorthPanel.setLayout(new BorderLayout());//布局
		RightOperation_NorthPanel.add(RightOperationPanel_ContextPanel,"West");
		RightOperation_NorthPanel.add(JLayeredPane_backgroundPanel,"East");
		RightOperation_NorthPanel.setOpaque(false);
		//中心面板初始化
		RightOperation_GridNorthPanel = new JPanel();
		RightOperation_GridNorthPanel.setLayout(new GridLayout(1,1,0,0));//布局
		RightOperation_GridNorthPanel.add(RightOperation_NorthPanel);	
		RightOperation_GridNorthPanel.setBorder(new EmptyBorder(14,14,14,14));
		RightOperation_GridNorthPanel.setBackground(new Color(19,146,249));
		//操作面板初始化
		RightOperationPanel = new JPanel();
		RightOperationPanel_BorderLayout = new BorderLayout();
		RightOperationPanel.setLayout(RightOperationPanel_BorderLayout);//布局
		RightOperationPanel.add(RightOperation_GridNorthPanel,"North");
		RightOperationPanel.add(RightOperation_SelectPanel ,"Center");	
		RightOperationPanel.setPreferredSize(new Dimension(1,840));
		
		RightOperationPanel_ScrollPane = new JScrollPane(RightOperationPanel,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		RightOperationPanel_ScrollPane.setBorder(null);
		
		LeftFlowLayoutPanel = new JPanel();
		LeftFlowLayoutPanel_FlowLayout = new FlowLayout(FlowLayout.LEFT,10,10);
		LeftFlowLayoutPanel.setLayout(LeftFlowLayoutPanel_FlowLayout);//布局
		LeftFlowLayoutPanel.setPreferredSize(new Dimension(65,300));
		LeftFlowLayoutPanel.add(LeftSelectPanel);
		LeftFlowLayoutPanel.setBackground(new Color(140,189,239));
		//底部设置按钮面板初始化
		GridPanel = new JPanel();
		GridPanel.setPreferredSize(new Dimension(80,40));
		GridPanel_GridLayout = new GridLayout(0,1,0,0);//布局
		GridPanel.setLayout(GridPanel_GridLayout);
		GridPanel.add(LeftSelectPanel_SetButton);
		//控制底部设置按钮不变化的面板初始化
		LeftFlowLayoutSouthPanel = new JPanel();
		LeftFlowLayoutSouthPanel_FlowLayout = new FlowLayout(FlowLayout.LEFT,10,10);//布局
		LeftFlowLayoutSouthPanel.setLayout(LeftFlowLayoutSouthPanel_FlowLayout);
		LeftFlowLayoutSouthPanel.add(GridPanel);
		LeftFlowLayoutSouthPanel.setBackground(new Color(140,189,239));
		//左侧选择面板的外部嵌套面板
		LeftFlowLayoutOUTPanel = new JPanel();
		LeftFlowLayoutOUTPanel_BorderLayout = new BorderLayout();//布局
		LeftFlowLayoutOUTPanel.setLayout(LeftFlowLayoutOUTPanel_BorderLayout);
		LeftFlowLayoutOUTPanel.setPreferredSize(new Dimension(65,300));
		LeftFlowLayoutOUTPanel.add(LeftFlowLayoutPanel,"Center");
		LeftFlowLayoutOUTPanel.add(LeftFlowLayoutSouthPanel,"South");
		LeftFlowLayoutOUTPanel.setBackground(new Color(140,189,239));
		//主面板的初始化
		MainPanel = new JPanel();
		MainPanel_BorderLayout = new BorderLayout();//布局
		MainPanel.setLayout(MainPanel_BorderLayout);
		MainPanel.add(TopMenuBar,"North");
		MainPanel.add(LeftFlowLayoutOUTPanel,"West");
		MainPanel.add(RightOperationPanel_ScrollPane,"Center");
		isRightOperationPanelUsed = true;
		//最外层保留面板的初始化
		FramePanel = new JPanel();
		FramePanel_GridLayout = new GridLayout(1,1);//布局
		FramePanel.setLayout(FramePanel_GridLayout);
		FramePanel.add(MainPanel);
		

		setContentPane(FramePanel);

		//点击扩展汉堡面板按钮
		LeftSelectPanel_openPanelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				if(LeftSelectPanel_openPanelButtonCondition == LeftSelectPanel_openPanelButtonConditionEnum.CLOSE)
				{//面板打开操作
					GridPanel.setPreferredSize(new Dimension(190,40));
					LeftFlowLayoutPanel.setPreferredSize(new Dimension(180,300));
					LeftFlowLayoutOUTPanel.setPreferredSize(new Dimension(180,300));//更改页面尺寸
					LeftSelectPanel_openPanelButtonCondition = LeftSelectPanel_openPanelButtonConditionEnum.OPEN;
					LeftSelectPanel.setPreferredSize(new Dimension(190,300));
					MainPanel.repaint();
					MainPanel.revalidate();
				}
				else
				{//面板关闭操作
					GridPanel.setPreferredSize(new Dimension(80,40));
					LeftFlowLayoutPanel.setPreferredSize(new Dimension(65,300));
					LeftFlowLayoutOUTPanel.setPreferredSize(new Dimension(65,300));//更改页面尺寸
					LeftSelectPanel_openPanelButtonCondition = LeftSelectPanel_openPanelButtonConditionEnum.CLOSE;
					LeftSelectPanel.setPreferredSize(new Dimension(80,300));
					MainPanel.repaint();
					MainPanel.revalidate();
				}
			}
		});
		
		///点击主页按钮
		LeftSelectPanel_ReturnRightOperationPanelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				ThisAdmin = UserOperation.FindAdminInformation(UserName);
				RightOperationPanel_UserNameLabel.setText("你好！超级管理员"+ThisAdmin.getName()+"！");
				if(isSetPanel == true)//设置其他按钮状态
				{
					MainPanel.remove(aSetPanel);
					isSetPanel = false;
				}
				LeftSelectPanel_SetButton.setEnabled(true);
				if(isAdminUserInformationManagement == true)//设置其他按钮状态
				{
					MainPanel.remove(aAdminUserInformationManagement);
					isAdminUserInformationManagement = false;
				}
				if(isAdminGoodsSearchPanel == true)//设置其他按钮状态
				{
					MainPanel.remove(aAdminGoodsSearchPanel);
					isAdminGoodsSearchPanel = false;
				}
				if(isAdminInformationPanelUsed == true)//设置其他按钮状态
				{
					MainPanel.remove(aAdminInfoPanel);
					isAdminInformationPanelUsed = false;
				}
				if(isAdminPurchaseHistoryPanel == true)//设置其他按钮状态
				{
					MainPanel.remove(aAdminPurchaseHistoryPanel);
					isAdminPurchaseHistoryPanel = false;
				}
				MainPanel.repaint();
				MainPanel.add(RightOperationPanel_ScrollPane,"Center");
				isRightOperationPanelUsed = true;
				MainPanel.revalidate();
				AdminGUI.this.LeftSelectPanel_GoodsInformationButton.setEnabled(true);//商品信息
				LeftSelectPanel_ReturnRightOperationPanelButton.setEnabled(false);//主页
				AdminGUI.this.LeftSelectPanel_ConsumptionRecordButton.setEnabled(true);//消费记录
				AdminGUI.this.LeftSelectPanel_GuestInformationButton.setEnabled(true);//个人信息
				LeftSelectPanel_UserInformationManagementButton.setEnabled(true);
				
			}
		});
		
		////左侧 会员信息管理
		LeftSelectPanel_UserInformationManagementButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				if(isSetPanel == true)//设置其他按钮状态
				{
					MainPanel.remove(aSetPanel);
					isSetPanel = false;
				}
				if(isRightOperationPanelUsed == true)//设置其他按钮状态
				{
					MainPanel.remove(RightOperationPanel_ScrollPane);
					isRightOperationPanelUsed = false;
				}
				if(isAdminInformationPanelUsed == true)//设置其他按钮状态
				{
					MainPanel.remove(aAdminInfoPanel);
					isAdminInformationPanelUsed = false;//设置其他按钮状态
				}
				if(isAdminGoodsSearchPanel == true)
				{
					MainPanel.remove(aAdminGoodsSearchPanel);//设置其他按钮状态
					isAdminGoodsSearchPanel = false;
				}
				if(isAdminPurchaseHistoryPanel == true)
				{
					MainPanel.remove(aAdminPurchaseHistoryPanel);//设置其他按钮状态
					isAdminPurchaseHistoryPanel = false;
				}
				MainPanel.repaint();
				MainPanel.add(aAdminUserInformationManagement,"Center");
				isAdminUserInformationManagement = true;
				AdminGUI.this.setVisible(true);
				MainPanel.revalidate();
				LeftSelectPanel_UserInformationManagementButton.setEnabled(false);
				AdminGUI.this.LeftSelectPanel_ConsumptionRecordButton.setEnabled(true);//消费记录
				AdminGUI.this.LeftSelectPanel_GoodsInformationButton.setEnabled(true);//商品信息
				LeftSelectPanel_ReturnRightOperationPanelButton.setEnabled(true);//主页
				AdminGUI.this.LeftSelectPanel_GuestInformationButton.setEnabled(true);//个人信息
				LeftSelectPanel_SetButton.setEnabled(true);
			}
		});
		
		////右侧 会员信息管理
		RightOperation_SelectPanel_UserInformationManagementButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				if(isSetPanel == true)//设置其他按钮状态
				{
					MainPanel.remove(aSetPanel);
					isSetPanel = false;
				}
				if(isRightOperationPanelUsed == true)//设置其他按钮状态
				{
					MainPanel.remove(RightOperationPanel_ScrollPane);
					isRightOperationPanelUsed = false;
				}
				if(isAdminInformationPanelUsed == true)//设置其他按钮状态
				{
					MainPanel.remove(aAdminInfoPanel);
					isAdminInformationPanelUsed = false;
				}
				if(isAdminGoodsSearchPanel == true)//设置其他按钮状态
				{
					MainPanel.remove(aAdminGoodsSearchPanel);
					isAdminGoodsSearchPanel = false;
				}
				if(isAdminPurchaseHistoryPanel == true)//设置其他按钮状态
				{
					MainPanel.remove(aAdminPurchaseHistoryPanel);
					isAdminPurchaseHistoryPanel = false;
				}
				MainPanel.repaint();
				MainPanel.add(aAdminUserInformationManagement,"Center");
				isAdminUserInformationManagement = true;
				AdminGUI.this.setVisible(true);
				MainPanel.revalidate();
				LeftSelectPanel_UserInformationManagementButton.setEnabled(false);
				AdminGUI.this.LeftSelectPanel_ConsumptionRecordButton.setEnabled(true);//消费记录
				AdminGUI.this.LeftSelectPanel_GoodsInformationButton.setEnabled(true);//商品信息
				LeftSelectPanel_ReturnRightOperationPanelButton.setEnabled(true);//主页
				AdminGUI.this.LeftSelectPanel_GuestInformationButton.setEnabled(true);//个人信息
				LeftSelectPanel_SetButton.setEnabled(true);
			}
		});
		
		
		////左侧 消费记录             ------------------------------------aGuestPurchaseHistoryPanel
		LeftSelectPanel_ConsumptionRecordButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				if(isSetPanel == true)
				{//设置其他按钮状态
					MainPanel.remove(aSetPanel);
					isSetPanel = false;
				}
				if(isAdminUserInformationManagement == true)//设置其他按钮状态
				{
					MainPanel.remove(aAdminUserInformationManagement);
					isAdminUserInformationManagement = false;
				}
				if(isRightOperationPanelUsed == true)//设置其他按钮状态
				{
					MainPanel.remove(RightOperationPanel_ScrollPane);
					isRightOperationPanelUsed = false;
				}
				if(isAdminInformationPanelUsed == true)//设置其他按钮状态
				{
					MainPanel.remove(aAdminInfoPanel);
					isAdminInformationPanelUsed = false;
				}
				if(isAdminGoodsSearchPanel == true)//设置其他按钮状态
				{
					MainPanel.remove(aAdminGoodsSearchPanel);
					isAdminGoodsSearchPanel = false;
				}
				MainPanel.repaint();
				MainPanel.add(aAdminPurchaseHistoryPanel,"Center");
				isAdminPurchaseHistoryPanel = true;
				AdminGUI.this.setVisible(true);
				MainPanel.revalidate();
				AdminGUI.this.LeftSelectPanel_ConsumptionRecordButton.setEnabled(false);//消费记录
				AdminGUI.this.LeftSelectPanel_GoodsInformationButton.setEnabled(true);//商品信息
				LeftSelectPanel_ReturnRightOperationPanelButton.setEnabled(true);//主页
				AdminGUI.this.LeftSelectPanel_GuestInformationButton.setEnabled(true);//个人信息
				LeftSelectPanel_UserInformationManagementButton.setEnabled(true);
				LeftSelectPanel_SetButton.setEnabled(true);
			}
		});
		
		////右侧 消费记录
		RightOperation_SelectPanel_ConsumptionRecordButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				if(isSetPanel == true)//设置其他按钮状态
				{
					MainPanel.remove(aSetPanel);
					isSetPanel = false;
				}
				if(isAdminUserInformationManagement == true)//设置其他按钮状态
				{
					MainPanel.remove(aAdminUserInformationManagement);
					isAdminUserInformationManagement = false;
				}
				if(isRightOperationPanelUsed == true)//设置其他按钮状态
				{
					MainPanel.remove(RightOperationPanel_ScrollPane);
					isRightOperationPanelUsed = false;
				}
				if(isAdminInformationPanelUsed == true)//设置其他按钮状态
				{
					MainPanel.remove(aAdminInfoPanel);
					isAdminInformationPanelUsed = false;
				}
				if(isAdminGoodsSearchPanel == true)//设置其他按钮状态
				{
					MainPanel.remove(aAdminGoodsSearchPanel);
					isAdminGoodsSearchPanel = false;
				}
				MainPanel.repaint();
				MainPanel.add(aAdminPurchaseHistoryPanel,"Center");
				isAdminPurchaseHistoryPanel = true;
				AdminGUI.this.setVisible(true);
				MainPanel.revalidate();
				AdminGUI.this.LeftSelectPanel_ConsumptionRecordButton.setEnabled(false);//消费记录
				AdminGUI.this.LeftSelectPanel_GoodsInformationButton.setEnabled(true);//商品信息
				LeftSelectPanel_ReturnRightOperationPanelButton.setEnabled(true);//主页
				AdminGUI.this.LeftSelectPanel_GuestInformationButton.setEnabled(true);//个人信息
				LeftSelectPanel_UserInformationManagementButton.setEnabled(true);
				LeftSelectPanel_SetButton.setEnabled(true);
			}
		});
		
		////左侧面板 商品信息查询按钮
		LeftSelectPanel_GoodsInformationButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				//	private GuestGoodsSearchPanel aGuestGoodsSearchPanel;
				//  private boolean isGuestGoodsSearchPanel = false;
				if(isSetPanel == true)//设置其他按钮状态
				{
					MainPanel.remove(aSetPanel);
					isSetPanel = false;
				}
				if(isAdminUserInformationManagement == true)//设置其他按钮状态
				{
					MainPanel.remove(aAdminUserInformationManagement);
					isAdminUserInformationManagement = false;
				}
				if(isRightOperationPanelUsed == true)//设置其他按钮状态
				{
					MainPanel.remove(RightOperationPanel_ScrollPane);
					isRightOperationPanelUsed = false;
				}
				if(isAdminInformationPanelUsed == true)//设置其他按钮状态
				{
					MainPanel.remove(aAdminInfoPanel);
					isAdminInformationPanelUsed = false;
				}
				if(isAdminPurchaseHistoryPanel == true)//设置其他按钮状态
				{
					MainPanel.remove(aAdminPurchaseHistoryPanel);
					isAdminPurchaseHistoryPanel = false;
				}
				MainPanel.repaint();
				MainPanel.add(aAdminGoodsSearchPanel,"Center");
				isAdminGoodsSearchPanel = true;
				AdminGUI.this.setVisible(true);
				MainPanel.revalidate();
				AdminGUI.this.LeftSelectPanel_GoodsInformationButton.setEnabled(false);//商品信息
				LeftSelectPanel_ReturnRightOperationPanelButton.setEnabled(true);//主页
				AdminGUI.this.LeftSelectPanel_ConsumptionRecordButton.setEnabled(true);//消费记录
				AdminGUI.this.LeftSelectPanel_GuestInformationButton.setEnabled(true);//个人信息
				LeftSelectPanel_UserInformationManagementButton.setEnabled(true);
				LeftSelectPanel_SetButton.setEnabled(true);
			}
		});
		////右侧面板 商品信息查询按钮
		RightOperation_SelectPanel_GoodsInformationButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				//	private GuestGoodsSearchPanel aGuestGoodsSearchPanel;
				//  private boolean isGuestGoodsSearchPanel = false;
				if(isSetPanel == true)//设置其他按钮状态
				{
					MainPanel.remove(aSetPanel);
					isSetPanel = false;
				}
				if(isAdminUserInformationManagement == true)//设置其他按钮状态
				{
					MainPanel.remove(aAdminUserInformationManagement);
					isAdminUserInformationManagement = false;
				}
				if(isRightOperationPanelUsed == true)//设置其他按钮状态
				{
					MainPanel.remove(RightOperationPanel_ScrollPane);
					isRightOperationPanelUsed = false;
				}
				if(isAdminInformationPanelUsed == true)//设置其他按钮状态
				{
					MainPanel.remove(aAdminInfoPanel);
					isAdminInformationPanelUsed = false;
				}
				if(isAdminPurchaseHistoryPanel == true)//设置其他按钮状态
				{
					MainPanel.remove(aAdminPurchaseHistoryPanel);
					isAdminPurchaseHistoryPanel = false;
				}
				MainPanel.repaint();
				MainPanel.add(aAdminGoodsSearchPanel,"Center");
				isAdminGoodsSearchPanel = true;
				AdminGUI.this.setVisible(true);
				MainPanel.revalidate();
				AdminGUI.this.LeftSelectPanel_GoodsInformationButton.setEnabled(false);//商品信息
				LeftSelectPanel_ReturnRightOperationPanelButton.setEnabled(true);//主页
				AdminGUI.this.LeftSelectPanel_ConsumptionRecordButton.setEnabled(true);//消费记录
				AdminGUI.this.LeftSelectPanel_GuestInformationButton.setEnabled(true);//个人信息
				LeftSelectPanel_UserInformationManagementButton.setEnabled(true);
				LeftSelectPanel_SetButton.setEnabled(true);
			}
		});
		////
		
		////左侧面板 个人信息概览按钮
		LeftSelectPanel_GuestInformationButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				if(isSetPanel == true)//设置其他按钮状态
				{
					MainPanel.remove(aSetPanel);
					isSetPanel = false;
				}
				if(isAdminUserInformationManagement == true)//设置其他按钮状态
				{
					MainPanel.remove(aAdminUserInformationManagement);
					isAdminUserInformationManagement = false;
				}
				if(isAdminGoodsSearchPanel == true)//设置其他按钮状态
				{
					MainPanel.remove(aAdminGoodsSearchPanel);//设置其他按钮状态
					isAdminGoodsSearchPanel = false;
				}
				if(isRightOperationPanelUsed == true)//设置其他按钮状态
				{
					MainPanel.remove(RightOperationPanel_ScrollPane);
					isRightOperationPanelUsed = false;
				}
				if(isAdminPurchaseHistoryPanel == true)//设置其他按钮状态
				{
					MainPanel.remove(aAdminPurchaseHistoryPanel);
					isAdminPurchaseHistoryPanel = false;
				}
				MainPanel.repaint();
				MainPanel.add(aAdminInfoPanel,"Center");
				aAdminInfoPanel.OldPassWord.setPassword("");
				aAdminInfoPanel.NewPassWord.setPassword("");
				aAdminInfoPanel.ConfirmNewPassWord.setPassword("");
				isAdminInformationPanelUsed = true;
				MainPanel.revalidate();
				AdminGUI.this.LeftSelectPanel_GoodsInformationButton.setEnabled(true);//商品信息
				LeftSelectPanel_ReturnRightOperationPanelButton.setEnabled(true);//主页
				AdminGUI.this.LeftSelectPanel_ConsumptionRecordButton.setEnabled(true);//消费记录
				AdminGUI.this.LeftSelectPanel_GuestInformationButton.setEnabled(false);//个人信息
				LeftSelectPanel_UserInformationManagementButton.setEnabled(true);
				LeftSelectPanel_SetButton.setEnabled(true);
			}
		});
		////右侧面板 个人信息概览按钮
		RightOperation_SelectPanel_GuestInformationButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				if(isSetPanel == true)//设置其他按钮状态
				{
					MainPanel.remove(aSetPanel);
					isSetPanel = false;
				}
				if(isAdminUserInformationManagement == true)//设置其他按钮状态
				{
					MainPanel.remove(aAdminUserInformationManagement);
					isAdminUserInformationManagement = false;
				}
				if(isAdminGoodsSearchPanel == true)//设置其他按钮状态
				{
					MainPanel.remove(aAdminGoodsSearchPanel);
					isAdminGoodsSearchPanel = false;
				}
				if(isRightOperationPanelUsed == true)//设置其他按钮状态
				{
					MainPanel.remove(RightOperationPanel_ScrollPane);
					isRightOperationPanelUsed = false;
				}
				if(isAdminPurchaseHistoryPanel == true)//设置其他按钮状态
				{
					MainPanel.remove(aAdminPurchaseHistoryPanel);
					isAdminPurchaseHistoryPanel = false;
				}
				MainPanel.repaint();
				MainPanel.add(aAdminInfoPanel,"Center");
				aAdminInfoPanel.OldPassWord.setPassword("");
				aAdminInfoPanel.NewPassWord.setPassword("");
				aAdminInfoPanel.ConfirmNewPassWord.setPassword("");
				isAdminInformationPanelUsed = true;
				MainPanel.revalidate();
				AdminGUI.this.LeftSelectPanel_GoodsInformationButton.setEnabled(true);//商品信息
				LeftSelectPanel_ReturnRightOperationPanelButton.setEnabled(true);//主页
				AdminGUI.this.LeftSelectPanel_ConsumptionRecordButton.setEnabled(true);//消费记录
				AdminGUI.this.LeftSelectPanel_GuestInformationButton.setEnabled(false);//个人信息
				LeftSelectPanel_UserInformationManagementButton.setEnabled(true);
				LeftSelectPanel_SetButton.setEnabled(true);
			}
		});
		
		
		LeftSelectPanel_SetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				if(isAdminInformationPanelUsed == true)//设置其他按钮状态
				{
					MainPanel.remove(aAdminInfoPanel);
					isAdminInformationPanelUsed = false;
				}
				if(isAdminUserInformationManagement == true)//设置其他按钮状态
				{
					MainPanel.remove(aAdminUserInformationManagement);
					isAdminUserInformationManagement = false;
				}
				if(isAdminGoodsSearchPanel == true)//设置其他按钮状态
				{
					MainPanel.remove(aAdminGoodsSearchPanel);
					isAdminGoodsSearchPanel = false;
				}
				if(isRightOperationPanelUsed == true)//设置其他按钮状态
				{
					MainPanel.remove(RightOperationPanel_ScrollPane);
					isRightOperationPanelUsed = false;
				}
				if(isAdminPurchaseHistoryPanel == true)//设置其他按钮状态
				{
					MainPanel.remove(aAdminPurchaseHistoryPanel);
					isAdminPurchaseHistoryPanel = false;
				}
				MainPanel.repaint();
				MainPanel.add(aSetPanel,"Center");
				isSetPanel = true;
				MainPanel.revalidate();
				LeftSelectPanel_SetButton.setEnabled(false);
				AdminGUI.this.LeftSelectPanel_GoodsInformationButton.setEnabled(true);//商品信息
				LeftSelectPanel_ReturnRightOperationPanelButton.setEnabled(true);//主页
				AdminGUI.this.LeftSelectPanel_ConsumptionRecordButton.setEnabled(true);//消费记录
				AdminGUI.this.LeftSelectPanel_GuestInformationButton.setEnabled(true);//个人信息
				LeftSelectPanel_UserInformationManagementButton.setEnabled(true);

			}
		});
		
		try 
		{
			//windows.WindowsLookAndFeel
			//nimbus.NumbusLookAndFeel
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NumbusLookAndFeel");
			SwingUtilities.updateComponentTreeUI(this);
		}
		catch(Exception e) {}
	}
	
}

