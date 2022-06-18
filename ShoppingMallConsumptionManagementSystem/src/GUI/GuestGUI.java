package GUI;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import Login.Login;
import Login.MainClass;
import people.Guest;
import people.UserOperation;
import java.io.*;



public class GuestGUI extends JFrame implements MainGUIInterface
{
	enum LeftSelectPanel_openPanelButtonConditionEnum//负责汉堡菜单打开的美剧
	{
		CLOSE,
		OPEN
	}
	
	private JLabel JLayeredPane_backgroundLabel;
	
	private ImageIcon background;
	
	private Guest ThisGuest;
	private JButton LeftSelectPanel_openPanelButton,//左侧选择按钮的初始化系列
			LeftSelectPanel_ReturnRightOperationPanelButton,
			LeftSelectPanel_ConsumptionRecordButton, 
			LeftSelectPanel_GoodsInformationButton, 
			LeftSelectPanel_GuestInformationButton;
	private LeftSelectPanel_openPanelButtonConditionEnum LeftSelectPanel_openPanelButtonCondition = LeftSelectPanel_openPanelButtonConditionEnum.CLOSE;
	
	private JButton RightOperation_SelectPanel_ConsumptionRecordButton, //右侧选择按钮字段
			RightOperation_SelectPanel_GoodsInformationButton, 
			RightOperation_SelectPanel_GuestInformationButton;
	private ImageIcon ConsumptionRecordIcon,GoodsInformationIcon,GuestInformationIcon;
	private ImageIcon LEFTopenPanelIcon,LEFTHomeIcon,LEFTConsumptionRecordIcon,LEFTGoodsInformationIcon,LEFTGuestInformationIcon;
	
	private JMenuBar TopMenuBar;//菜单栏
	private JMenu HelpMenu;
	private JMenuItem HelpInformation,AboutThisProgram;
	
	private JLabel RightOperationPanel_UserNameLabel,RightOperationPanel_ContextLabel;
	
	private JScrollPane RightOperationPanel_ScrollPane;
	private JPanel FramePanel,MainPanel,LeftFlowLayoutPanel, LeftSelectPanel,//主页面所有面板
			RightOperationPanel,RightOperation_SelectPanel,RightOperation_NorthPanel,
			RightOperation_GridNorthPanel,RightOperationPanel_ContextPanel,
			JLayeredPane_backgroundPanel;
	private boolean isRightOperationPanelUsed = true;
	
	private GuestInformationPanel gip;//功能面板
	private boolean isGuestInformationPanelUsed = false;
	private GuestGoodsSearchPanel aGuestGoodsSearchPanel;//功能面板
	private boolean isGuestGoodsSearchPanel = false;
	private GuestPurchaseHistoryPanel aGuestPurchaseHistoryPanel;//功能面板
	private boolean isGuestPurchaseHistoryPanel = false;
	private SetPanel aSetPanel;//功能面板
	private boolean isSetPanel = false;
	
	private BorderLayout MainPanel_BorderLayout, RightOperationPanel_BorderLayout;//布局
	private GridLayout FramePanel_GridLayout, LeftSelectPanel_GridLayout;//布局
	private FlowLayout LeftFlowLayoutPanel_FlowLayout;//布局
	private FlowLayout RightOperation_SelectPanelFlowLayout;//布局

	private JButton LeftSelectPanel_SetButton;

	private ImageIcon LEFTSetIcon;

	private JPanel LeftFlowLayoutOUTPanel;//嵌套面板

	private BorderLayout LeftFlowLayoutOUTPanel_BorderLayout;

	private JPanel LeftFlowLayoutSouthPanel;//嵌套面板

	private FlowLayout LeftFlowLayoutSouthPanel_FlowLayout;

	private JPanel GridPanel;//设置面板

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
	
	public GuestGUI(String UserName) throws IOException
	{
		setFrame();
		//所有功能面板初始化
		ThisGuest = UserOperation.FindUserInformation(UserName);
		gip = new GuestInformationPanel(GuestGUI.this.ThisGuest);
		aGuestPurchaseHistoryPanel = new GuestPurchaseHistoryPanel(ThisGuest);
		aGuestGoodsSearchPanel = new GuestGoodsSearchPanel(this,ThisGuest);
		aSetPanel = new SetPanel();
		
		this.addWindowListener(new WindowAdapter() {//返回登录界面的监听
			public void windowClosing(WindowEvent e)
			{
				GuestGUI.this.dispose();
				MainClass mm = new MainClass();
				mm.BuildLoginGUI();
			}
		});
		
		
		HelpInformation = new JMenuItem("使用小贴士");
		AboutThisProgram = new JMenuItem("关于…");
		AboutThisProgram.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				JOptionPane.showMessageDialog(GuestGUI.this, "商场VIP消费管理系统\n版本：1.1.0.0", "系统信息", -1);
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
		
		//左侧汉堡菜单 消费记录按钮
		LeftSelectPanel_ConsumptionRecordButton = new JButton("   消费记录查询",LEFTConsumptionRecordIcon = new ImageIcon(new File("").getCanonicalPath()+"/image/GuestGUIButton/ConsumptionRecordIcon.png"));
		LEFTConsumptionRecordIcon.setImage(LEFTConsumptionRecordIcon.getImage().getScaledInstance(40,40,Image.SCALE_SMOOTH)); 
		LeftSelectPanel_ConsumptionRecordButton.setHorizontalAlignment(JButton.LEFT);
		LeftSelectPanel_ConsumptionRecordButton.setBorderPainted(false);
		LeftSelectPanel_ConsumptionRecordButton.setBackground(new Color(140,189,239));
		LeftSelectPanel_ConsumptionRecordButton.setMargin(new Insets(0,0,0,0));
		
		////左侧汉堡菜单 商品查询按钮
		LeftSelectPanel_GoodsInformationButton = new JButton("   商品信息查询",LEFTGoodsInformationIcon = new ImageIcon(new File("").getCanonicalPath()+"/image/GuestGUIButton/GoodsInformationIcon.png"));
		LEFTGoodsInformationIcon.setImage(LEFTGoodsInformationIcon.getImage().getScaledInstance(40,40,Image.SCALE_SMOOTH)); //设置图片大小
		LeftSelectPanel_GoodsInformationButton.setHorizontalAlignment(JButton.LEFT);
		LeftSelectPanel_GoodsInformationButton.setBorderPainted(false);
		LeftSelectPanel_GoodsInformationButton.setBackground(new Color(140,189,239));
		LeftSelectPanel_GoodsInformationButton.setMargin(new Insets(0,0,0,0));
		
		//左侧汉堡菜单 个人信息概览按钮
		LeftSelectPanel_GuestInformationButton = new JButton("   个人信息概览",LEFTGuestInformationIcon = new ImageIcon(new File("").getCanonicalPath()+"/image/GuestGUIButton/GuestInformationIcon.png"));
		LEFTGuestInformationIcon.setImage(LEFTGuestInformationIcon.getImage().getScaledInstance(40,40,Image.SCALE_SMOOTH)); //设置图片大小
		LeftSelectPanel_GuestInformationButton.setHorizontalAlignment(JButton.LEFT);
		LeftSelectPanel_GuestInformationButton.setBorderPainted(false);
		LeftSelectPanel_GuestInformationButton.setBackground(new Color(140,189,239));
		LeftSelectPanel_GuestInformationButton.setMargin(new Insets(0,0,0,0));
		
		//左侧 设置
		LeftSelectPanel_SetButton = new JButton("   系统",LEFTSetIcon = new ImageIcon(new File("").getCanonicalPath()+"/image/GuestGUIButton/SetIcon.png"));
		LEFTSetIcon.setImage(LEFTSetIcon.getImage().getScaledInstance(40,40,Image.SCALE_SMOOTH)); //设置图片大小
		LeftSelectPanel_SetButton.setHorizontalAlignment(JButton.LEFT);
		LeftSelectPanel_SetButton.setBorderPainted(false);
		LeftSelectPanel_SetButton.setBackground(new Color(140,189,239));
		LeftSelectPanel_SetButton.setMargin(new Insets(0,0,0,0));
		//选择面板初始化
		LeftSelectPanel = new JPanel();
		LeftSelectPanel.setPreferredSize(new Dimension(80,250));
		LeftSelectPanel_GridLayout = new GridLayout(0,1,0,0);
		LeftSelectPanel.setLayout(LeftSelectPanel_GridLayout);
		LeftSelectPanel.add(LeftSelectPanel_openPanelButton);
		LeftSelectPanel.add(LeftSelectPanel_ReturnRightOperationPanelButton);
		LeftSelectPanel.add(LeftSelectPanel_ConsumptionRecordButton);
		LeftSelectPanel.add(LeftSelectPanel_GoodsInformationButton);
		LeftSelectPanel.add(LeftSelectPanel_GuestInformationButton);
		//LeftSelectPanel.setBorder(BorderFactory.createLineBorder(Color.RED,2));
		LeftSelectPanel.setOpaque(false);
		//右侧操作选择按钮初始化
		RightOperation_SelectPanel_ConsumptionRecordButton = new JButton("消费记录查询",ConsumptionRecordIcon = new ImageIcon(new File("").getCanonicalPath()+"/image/GuestGUIButton/ConsumptionRecordIcon.png"));
		ConsumptionRecordIcon.setImage(ConsumptionRecordIcon.getImage().getScaledInstance(100,100,Image.SCALE_DEFAULT)); 
		RightOperation_SelectPanel_ConsumptionRecordButton.setPreferredSize(new Dimension(300,200));
		RightOperation_SelectPanel_ConsumptionRecordButton.setBackground(new Color(140,189,239));
		//右侧操作选择按钮初始化
		RightOperation_SelectPanel_GoodsInformationButton = new JButton("商品信息查询",GoodsInformationIcon = new ImageIcon(new File("").getCanonicalPath()+"/image/GuestGUIButton/GoodsInformationIcon.png"));
		GoodsInformationIcon.setImage(GoodsInformationIcon.getImage().getScaledInstance(100,100,Image.SCALE_DEFAULT)); 
		RightOperation_SelectPanel_GoodsInformationButton.setPreferredSize(new Dimension(300,200));
		RightOperation_SelectPanel_GoodsInformationButton.setBackground(new Color(140,189,239));
		//右侧操作选择按钮初始化
		RightOperation_SelectPanel_GuestInformationButton = new JButton("个人信息概览",GuestInformationIcon = new ImageIcon(new File("").getCanonicalPath()+"/image/GuestGUIButton/GuestInformationIcon.png"));
		GuestInformationIcon.setImage(GuestInformationIcon.getImage().getScaledInstance(100,100,Image.SCALE_DEFAULT)); 
		RightOperation_SelectPanel_GuestInformationButton.setPreferredSize(new Dimension(300,200));
		RightOperation_SelectPanel_GuestInformationButton.setBackground(new Color(140,189,239));
		
		RightOperation_SelectPanel = new JPanel();
		RightOperation_SelectPanel.setLayout(RightOperation_SelectPanelFlowLayout = new FlowLayout(FlowLayout.LEFT,14,14));////
		RightOperation_SelectPanel.setPreferredSize(new Dimension(1,1));
		RightOperation_SelectPanel.add(RightOperation_SelectPanel_ConsumptionRecordButton);
		RightOperation_SelectPanel.add(RightOperation_SelectPanel_GoodsInformationButton);
		RightOperation_SelectPanel.add(RightOperation_SelectPanel_GuestInformationButton);
		
		JLayeredPane_backgroundLabel = new JLabel(background = new ImageIcon(new File("").getCanonicalPath()+"/image/GuestGUIBackground.png"),JLabel.RIGHT);
		//背景面板初始化
		JLayeredPane_backgroundPanel = new JPanel();
		JLayeredPane_backgroundPanel.setLayout(new GridLayout(1,1,0,0));
		JLayeredPane_backgroundPanel.add(JLayeredPane_backgroundLabel);
		JLayeredPane_backgroundPanel.setOpaque(false);
		JLayeredPane_backgroundPanel.setBorder(new EmptyBorder(50,0,0,0));
		//操作面板组件初始化
		RightOperationPanel_UserNameLabel = new JLabel("欢迎回来！"+ThisGuest.getName()+"！");
		RightOperationPanel_UserNameLabel.setForeground(Color.white);
		RightOperationPanel_UserNameLabel.setFont(RightOperationPanel_UserNameLabel.getFont().deriveFont(30.0f));
		
		RightOperationPanel_ContextLabel = new JLabel("今天又买了些什么呢？");
		RightOperationPanel_ContextLabel.setForeground(Color.white);
		RightOperationPanel_ContextLabel.setFont(RightOperationPanel_UserNameLabel.getFont().deriveFont(16.0f));
		RightOperationPanel_ContextLabel.setHorizontalAlignment(JLabel.LEFT);
		
		RightOperationPanel_ContextPanel = new JPanel();
		RightOperationPanel_ContextPanel.setLayout(new BoxLayout(RightOperationPanel_ContextPanel,BoxLayout.Y_AXIS));
		RightOperationPanel_ContextPanel.add(RightOperationPanel_UserNameLabel);
		RightOperationPanel_ContextPanel.add(RightOperationPanel_ContextLabel);
		RightOperationPanel_ContextPanel.setOpaque(false);
		
		//上方面板
		RightOperation_NorthPanel = new JPanel();
		RightOperation_NorthPanel.setLayout(new BorderLayout());
		RightOperation_NorthPanel.add(RightOperationPanel_ContextPanel,"West");
		RightOperation_NorthPanel.add(JLayeredPane_backgroundPanel,"East");
		RightOperation_NorthPanel.setOpaque(false);
		
		RightOperation_GridNorthPanel = new JPanel();
		RightOperation_GridNorthPanel.setLayout(new GridLayout(1,1,0,0));
		RightOperation_GridNorthPanel.add(RightOperation_NorthPanel);	
		RightOperation_GridNorthPanel.setBorder(new EmptyBorder(14,14,14,14));
		RightOperation_GridNorthPanel.setBackground(new Color(19,146,249));
		//右侧操作面板初始化
		RightOperationPanel = new JPanel();
		RightOperationPanel_BorderLayout = new BorderLayout();
		RightOperationPanel.setLayout(RightOperationPanel_BorderLayout);
		RightOperationPanel.add(RightOperation_GridNorthPanel,"North");
		RightOperationPanel.add(RightOperation_SelectPanel ,"Center");	
		RightOperationPanel.setPreferredSize(new Dimension(1,840));
		
		RightOperationPanel_ScrollPane = new JScrollPane(RightOperationPanel,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		RightOperationPanel_ScrollPane.setBorder(null);
		//外部嵌套按钮初始化
		LeftFlowLayoutPanel = new JPanel();
		LeftFlowLayoutPanel_FlowLayout = new FlowLayout(FlowLayout.LEFT,10,10);
		LeftFlowLayoutPanel.setLayout(LeftFlowLayoutPanel_FlowLayout);
		LeftFlowLayoutPanel.setPreferredSize(new Dimension(65,200));
		LeftFlowLayoutPanel.add(LeftSelectPanel);
		LeftFlowLayoutPanel.setBackground(new Color(140,189,239));
		//设置按钮初始化面板
		GridPanel = new JPanel();
		GridPanel.setPreferredSize(new Dimension(80,40));
		GridPanel_GridLayout = new GridLayout(0,1,0,0);
		GridPanel.setLayout(GridPanel_GridLayout);
		GridPanel.add(LeftSelectPanel_SetButton);
		//嵌套底部设置面板初始化的面板初始化
		LeftFlowLayoutSouthPanel = new JPanel();
		LeftFlowLayoutSouthPanel_FlowLayout = new FlowLayout(FlowLayout.LEFT,10,10);
		LeftFlowLayoutSouthPanel.setLayout(LeftFlowLayoutSouthPanel_FlowLayout);
		LeftFlowLayoutSouthPanel.add(GridPanel);
		LeftFlowLayoutSouthPanel.setBackground(new Color(140,189,239));
		//左侧外层嵌套面板初始化
		LeftFlowLayoutOUTPanel = new JPanel();
		LeftFlowLayoutOUTPanel_BorderLayout = new BorderLayout();
		LeftFlowLayoutOUTPanel.setLayout(LeftFlowLayoutOUTPanel_BorderLayout);
		LeftFlowLayoutOUTPanel.setPreferredSize(new Dimension(65,300));
		LeftFlowLayoutOUTPanel.add(LeftFlowLayoutPanel,"Center");
		LeftFlowLayoutOUTPanel.add(LeftFlowLayoutSouthPanel,"South");
		LeftFlowLayoutOUTPanel.setBackground(new Color(140,189,239));
		//住面板初始化
		MainPanel = new JPanel();
		MainPanel_BorderLayout = new BorderLayout();
		MainPanel.setLayout(MainPanel_BorderLayout);
		MainPanel.add(TopMenuBar,"North");
		MainPanel.add(LeftFlowLayoutOUTPanel,"West");
		MainPanel.add(RightOperationPanel_ScrollPane,"Center");
		isRightOperationPanelUsed = true;
		//页面最外层面板初始化
		FramePanel = new JPanel();
		FramePanel_GridLayout = new GridLayout(1,1);
		FramePanel.setLayout(FramePanel_GridLayout);
		FramePanel.add(MainPanel);
		

		setContentPane(FramePanel);

		//点击扩展汉堡面板按钮
		LeftSelectPanel_openPanelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				if(LeftSelectPanel_openPanelButtonCondition == LeftSelectPanel_openPanelButtonConditionEnum.CLOSE)
				{//设置面板打开状态
					GridPanel.setPreferredSize(new Dimension(190,40));
					LeftFlowLayoutOUTPanel.setPreferredSize(new Dimension(180,200));
					LeftFlowLayoutPanel.setPreferredSize(new Dimension(180,200));
					LeftSelectPanel_openPanelButtonCondition = LeftSelectPanel_openPanelButtonConditionEnum.OPEN;
					LeftSelectPanel.setPreferredSize(new Dimension(160,250));
					MainPanel.repaint();
					MainPanel.revalidate();
				}
				else//设置面板关闭状态
				{
					GridPanel.setPreferredSize(new Dimension(80,40));
					LeftFlowLayoutOUTPanel.setPreferredSize(new Dimension(65,200));
					LeftFlowLayoutPanel.setPreferredSize(new Dimension(65,200));
					LeftSelectPanel_openPanelButtonCondition = LeftSelectPanel_openPanelButtonConditionEnum.CLOSE;
					LeftSelectPanel.setPreferredSize(new Dimension(80,250));
					MainPanel.repaint();
					MainPanel.revalidate();
				}
			}
		});
		
		///点击主页按钮
		LeftSelectPanel_ReturnRightOperationPanelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				ThisGuest = UserOperation.FindUserInformation(UserName);
				RightOperationPanel_UserNameLabel.setText("欢迎回来！"+ThisGuest.getName()+"！");
				if(isSetPanel == true)//设置其他按钮状态
				{
					MainPanel.remove(aSetPanel);
					isSetPanel = false;
				}
				LeftSelectPanel_SetButton.setEnabled(true);
				if(isGuestGoodsSearchPanel == true)//设置其他按钮状态
				{
					MainPanel.remove(aGuestGoodsSearchPanel);
					isGuestGoodsSearchPanel = false;
				}
				if(isGuestInformationPanelUsed == true)//设置其他按钮状态
				{
					MainPanel.remove(gip);
					isGuestInformationPanelUsed = false;
				}
				if(isGuestPurchaseHistoryPanel == true)//设置其他按钮状态
				{
					MainPanel.remove(aGuestPurchaseHistoryPanel);
					isGuestPurchaseHistoryPanel = false;
				}
				MainPanel.repaint();
				MainPanel.add(RightOperationPanel_ScrollPane,"Center");
				isRightOperationPanelUsed = true;
				MainPanel.revalidate();
				GuestGUI.this.LeftSelectPanel_ConsumptionRecordButton.setEnabled(true);
				GuestGUI.this.LeftSelectPanel_GoodsInformationButton.setEnabled(true);//商品信息
				LeftSelectPanel_ReturnRightOperationPanelButton.setEnabled(false);//主页
				GuestGUI.this.LeftSelectPanel_ConsumptionRecordButton.setEnabled(true);//消费记录
				GuestGUI.this.LeftSelectPanel_GuestInformationButton.setEnabled(true);//个人信息
			}
		});
		
		////左侧 消费记录             ------------------------------------aGuestPurchaseHistoryPanel
		LeftSelectPanel_ConsumptionRecordButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				if(isSetPanel == true)//设置其他按钮状态
				{
					MainPanel.remove(aSetPanel);
					isSetPanel = false;
				}
				LeftSelectPanel_SetButton.setEnabled(true);
				if(isRightOperationPanelUsed == true)//设置其他按钮状态
				{
					MainPanel.remove(RightOperationPanel_ScrollPane);
					isRightOperationPanelUsed = false;
				}
				if(isGuestInformationPanelUsed == true)//设置其他按钮状态
				{
					MainPanel.remove(gip);
					isGuestInformationPanelUsed = false;
				}
				if(isGuestGoodsSearchPanel == true)//设置其他按钮状态
				{
					MainPanel.remove(aGuestGoodsSearchPanel);
					isGuestGoodsSearchPanel = false;
				}
				MainPanel.repaint();
				MainPanel.add(aGuestPurchaseHistoryPanel,"Center");
				aGuestPurchaseHistoryPanel.TableUpdate(ThisGuest);
				isGuestPurchaseHistoryPanel = true;
				GuestGUI.this.setVisible(true);
				MainPanel.revalidate();
				GuestGUI.this.LeftSelectPanel_ConsumptionRecordButton.setEnabled(false);//消费记录
				GuestGUI.this.LeftSelectPanel_GoodsInformationButton.setEnabled(true);//商品信息
				LeftSelectPanel_ReturnRightOperationPanelButton.setEnabled(true);//主页
				GuestGUI.this.LeftSelectPanel_GuestInformationButton.setEnabled(true);//个人信息
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
				LeftSelectPanel_SetButton.setEnabled(true);
				if(isRightOperationPanelUsed == true)//设置其他按钮状态
				{
					MainPanel.remove(RightOperationPanel_ScrollPane);
					isRightOperationPanelUsed = false;
				}
				if(isGuestInformationPanelUsed == true)//设置其他按钮状态
				{
					MainPanel.remove(gip);
					isGuestInformationPanelUsed = false;
				}
				if(isGuestGoodsSearchPanel == true)//设置其他按钮状态
				{
					MainPanel.remove(aGuestGoodsSearchPanel);
					isGuestGoodsSearchPanel = false;
				}
				MainPanel.repaint();
				MainPanel.add(aGuestPurchaseHistoryPanel,"Center");
				aGuestPurchaseHistoryPanel.TableUpdate(ThisGuest);
				isGuestPurchaseHistoryPanel = true;
				GuestGUI.this.setVisible(true);
				MainPanel.revalidate();
				GuestGUI.this.LeftSelectPanel_ConsumptionRecordButton.setEnabled(false);//消费记录
				GuestGUI.this.LeftSelectPanel_GoodsInformationButton.setEnabled(true);//商品信息
				LeftSelectPanel_ReturnRightOperationPanelButton.setEnabled(true);//主页
				GuestGUI.this.LeftSelectPanel_GuestInformationButton.setEnabled(true);//个人信息
			}
		});
		
		////左侧面板 商品信息查询按钮
		LeftSelectPanel_GoodsInformationButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				if(isSetPanel == true)//设置其他按钮状态
				{
					MainPanel.remove(aSetPanel);
					isSetPanel = false;
				}
				LeftSelectPanel_SetButton.setEnabled(true);
				//	private GuestGoodsSearchPanel aGuestGoodsSearchPanel;
				//  private boolean isGuestGoodsSearchPanel = false;
				if(isRightOperationPanelUsed == true)//设置其他按钮状态
				{
					MainPanel.remove(RightOperationPanel_ScrollPane);
					isRightOperationPanelUsed = false;
				}
				if(isGuestInformationPanelUsed == true)//设置其他按钮状态
				{
					MainPanel.remove(gip);
					isGuestInformationPanelUsed = false;
				}
				if(isGuestPurchaseHistoryPanel == true)//设置其他按钮状态
				{
					MainPanel.remove(aGuestPurchaseHistoryPanel);
					isGuestPurchaseHistoryPanel = false;
				}
				MainPanel.repaint();
				MainPanel.add(aGuestGoodsSearchPanel,"Center");
				isGuestGoodsSearchPanel = true;
				GuestGUI.this.setVisible(true);
				MainPanel.revalidate();
				GuestGUI.this.LeftSelectPanel_GoodsInformationButton.setEnabled(false);//商品信息
				LeftSelectPanel_ReturnRightOperationPanelButton.setEnabled(true);//主页
				GuestGUI.this.LeftSelectPanel_ConsumptionRecordButton.setEnabled(true);//消费记录
				GuestGUI.this.LeftSelectPanel_GuestInformationButton.setEnabled(true);//个人信息
				GuestGUI.this.LeftSelectPanel_ConsumptionRecordButton.setEnabled(true);

			}
		});
		////右侧面板 商品信息查询按钮
		RightOperation_SelectPanel_GoodsInformationButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				if(isSetPanel == true)//设置其他按钮状态
				{
					MainPanel.remove(aSetPanel);
					isSetPanel = false;
				}
				LeftSelectPanel_SetButton.setEnabled(true);
				//	private GuestGoodsSearchPanel aGuestGoodsSearchPanel;
				//  private boolean isGuestGoodsSearchPanel = false;
				if(isRightOperationPanelUsed == true)//设置其他按钮状态
				{
					MainPanel.remove(RightOperationPanel_ScrollPane);
					isRightOperationPanelUsed = false;
				}
				if(isGuestInformationPanelUsed == true)//设置其他按钮状态
				{
					MainPanel.remove(gip);
					isGuestInformationPanelUsed = false;
				}
				if(isGuestPurchaseHistoryPanel == true)//设置其他按钮状态
				{
					MainPanel.remove(aGuestPurchaseHistoryPanel);
					isGuestPurchaseHistoryPanel = false;
				}
				MainPanel.repaint();
				MainPanel.add(aGuestGoodsSearchPanel,"Center");
				isGuestGoodsSearchPanel = true;
				GuestGUI.this.setVisible(true);
				MainPanel.revalidate();
				GuestGUI.this.LeftSelectPanel_GoodsInformationButton.setEnabled(false);//商品信息
				LeftSelectPanel_ReturnRightOperationPanelButton.setEnabled(true);//主页
				GuestGUI.this.LeftSelectPanel_ConsumptionRecordButton.setEnabled(true);//消费记录
				GuestGUI.this.LeftSelectPanel_GuestInformationButton.setEnabled(true);//个人信息
				GuestGUI.this.LeftSelectPanel_ConsumptionRecordButton.setEnabled(true);

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
				LeftSelectPanel_SetButton.setEnabled(true);
				if(isGuestGoodsSearchPanel == true)//设置其他按钮状态
				{
					MainPanel.remove(aGuestGoodsSearchPanel);
					isGuestGoodsSearchPanel = false;
				}
				if(isRightOperationPanelUsed == true)//设置其他按钮状态
				{
					MainPanel.remove(RightOperationPanel_ScrollPane);
					isRightOperationPanelUsed = false;
				}
				if(isGuestPurchaseHistoryPanel == true)//设置其他按钮状态
				{
					MainPanel.remove(aGuestPurchaseHistoryPanel);
					isGuestPurchaseHistoryPanel = false;
				}
				MainPanel.repaint();
				MainPanel.add(gip,"Center");
				gip.OldPassWord.setPassword("");
				gip.NewPassWord.setPassword("");
				gip.ConfirmNewPassWord.setPassword("");
				isGuestInformationPanelUsed = true;
				MainPanel.revalidate();
				GuestGUI.this.LeftSelectPanel_GoodsInformationButton.setEnabled(true);//商品信息
				LeftSelectPanel_ReturnRightOperationPanelButton.setEnabled(true);//主页
				GuestGUI.this.LeftSelectPanel_ConsumptionRecordButton.setEnabled(true);//消费记录
				GuestGUI.this.LeftSelectPanel_GuestInformationButton.setEnabled(false);//个人信息
				GuestGUI.this.LeftSelectPanel_ConsumptionRecordButton.setEnabled(true);

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
				LeftSelectPanel_SetButton.setEnabled(true);
				if(isGuestGoodsSearchPanel == true)//设置其他按钮状态
				{
					MainPanel.remove(aGuestGoodsSearchPanel);
					isGuestGoodsSearchPanel = false;
				}
				if(isRightOperationPanelUsed == true)//设置其他按钮状态
				{
					MainPanel.remove(RightOperationPanel_ScrollPane);
					isRightOperationPanelUsed = false;
				}
				if(isGuestPurchaseHistoryPanel == true)//设置其他按钮状态
				{
					MainPanel.remove(aGuestPurchaseHistoryPanel);
					isGuestPurchaseHistoryPanel = false;
				}
				MainPanel.repaint();
				MainPanel.add(gip,"Center");
				gip.OldPassWord.setPassword("");
				gip.NewPassWord.setPassword("");
				gip.ConfirmNewPassWord.setPassword("");
				isGuestInformationPanelUsed = true;
				MainPanel.revalidate();
				GuestGUI.this.LeftSelectPanel_GoodsInformationButton.setEnabled(true);//商品信息
				LeftSelectPanel_ReturnRightOperationPanelButton.setEnabled(true);//主页
				GuestGUI.this.LeftSelectPanel_ConsumptionRecordButton.setEnabled(true);//消费记录
				GuestGUI.this.LeftSelectPanel_GuestInformationButton.setEnabled(false);//个人信息
				GuestGUI.this.LeftSelectPanel_ConsumptionRecordButton.setEnabled(true);

			}
		});
		
		LeftSelectPanel_SetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				if(isGuestGoodsSearchPanel == true)//设置其他按钮状态
				{
					MainPanel.remove(aGuestGoodsSearchPanel);
					isGuestGoodsSearchPanel = false;
				}
				if(isRightOperationPanelUsed == true)//设置其他按钮状态
				{
					MainPanel.remove(RightOperationPanel_ScrollPane);
					isRightOperationPanelUsed = false;
				}
				if(isGuestPurchaseHistoryPanel == true)//设置其他按钮状态
				{
					MainPanel.remove(aGuestPurchaseHistoryPanel);
					isGuestPurchaseHistoryPanel = false;
				}
				if(isGuestInformationPanelUsed == true)//设置其他按钮状态
				{
					MainPanel.remove(gip);
					isGuestInformationPanelUsed = false;
				}
				MainPanel.repaint();
				MainPanel.add(aSetPanel,"Center");
				isSetPanel = true;
				MainPanel.revalidate();
				GuestGUI.this.LeftSelectPanel_GoodsInformationButton.setEnabled(true);//商品信息
				LeftSelectPanel_ReturnRightOperationPanelButton.setEnabled(true);//主页
				GuestGUI.this.LeftSelectPanel_ConsumptionRecordButton.setEnabled(true);//消费记录
				GuestGUI.this.LeftSelectPanel_GuestInformationButton.setEnabled(true);//个人信息
				GuestGUI.this.LeftSelectPanel_ConsumptionRecordButton.setEnabled(true);
				LeftSelectPanel_SetButton.setEnabled(false);
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
