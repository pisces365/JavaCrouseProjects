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
	enum LeftSelectPanel_openPanelButtonConditionEnum//�����˵��رպʹ򿪵�ö��
	{
		CLOSE,
		OPEN
	}
	
	private JLabel JLayeredPane_backgroundLabel;
	
	private ImageIcon background;
	
	private Admin ThisAdmin;
	private JButton LeftSelectPanel_openPanelButton,//�����˵��İ�ť
			LeftSelectPanel_ReturnRightOperationPanelButton,
			LeftSelectPanel_ConsumptionRecordButton, 
			LeftSelectPanel_GoodsInformationButton, 
			LeftSelectPanel_GuestInformationButton;
	private LeftSelectPanel_openPanelButtonConditionEnum LeftSelectPanel_openPanelButtonCondition = LeftSelectPanel_openPanelButtonConditionEnum.CLOSE;
	
	private JButton RightOperation_SelectPanel_ConsumptionRecordButton, //��ҳ�˵��İ�ť
			RightOperation_SelectPanel_GoodsInformationButton, 
			RightOperation_SelectPanel_GuestInformationButton;
	private ImageIcon ConsumptionRecordIcon,GoodsInformationIcon,GuestInformationIcon;
	private ImageIcon LEFTopenPanelIcon,LEFTHomeIcon,LEFTConsumptionRecordIcon,LEFTGoodsInformationIcon,LEFTGuestInformationIcon;
	
	private JMenuBar TopMenuBar;//������
	private JMenu HelpMenu;
	private JMenuItem HelpInformation,AboutThisProgram;
	
	private JLabel RightOperationPanel_UserNameLabel,RightOperationPanel_ContextLabel;//��ӭ��ǩ
	
	private JScrollPane RightOperationPanel_ScrollPane;
	private JPanel FramePanel,MainPanel,LeftFlowLayoutPanel, LeftSelectPanel,//��ҳ����л����
			RightOperationPanel,RightOperation_SelectPanel,RightOperation_NorthPanel,//��ҳ����л����
			RightOperation_GridNorthPanel,RightOperationPanel_ContextPanel,//��ҳ����л����
			JLayeredPane_backgroundPanel;//��ҳ����л����
	private boolean isRightOperationPanelUsed = true;
	
	private AdminInfoPanel aAdminInfoPanel;//�������
	private boolean isAdminInformationPanelUsed = false;
	private AdminGoodsSearchPanel aAdminGoodsSearchPanel;//�������
	private boolean isAdminGoodsSearchPanel = false;
	private AdminPurchaseHistoryPanel aAdminPurchaseHistoryPanel;//�������
	private boolean isAdminPurchaseHistoryPanel = false;
	private AdminUserInformationManagement aAdminUserInformationManagement;//�������
	private boolean isAdminUserInformationManagement = false;
	private SetPanel aSetPanel;//�������
	private boolean isSetPanel = false;
	
	private BorderLayout MainPanel_BorderLayout, RightOperationPanel_BorderLayout;//��ʽ����
	private GridLayout FramePanel_GridLayout, LeftSelectPanel_GridLayout;//���񲼾�
	private FlowLayout LeftFlowLayoutPanel_FlowLayout;//��ʽ����
	private FlowLayout RightOperation_SelectPanelFlowLayout;//
	//�Ҳఴť����ͼƬ����
	private JButton RightOperation_SelectPanel_UserInformationManagementButton;

	private ImageIcon UserInformationManagementIcon;
	//�Ҳఴť����ͼƬ����
	private JButton LeftSelectPanel_UserInformationManagementButton;

	private ImageIcon LEFTUserInformationManagementIcon;
	//�Ҳఴť����ͼƬ����
	private JPanel LeftFlowLayoutOUTPanel;

	private BorderLayout LeftFlowLayoutOUTPanel_BorderLayout;
	//�Ҳఴť����ͼƬ����
	private JButton LeftSelectPanel_SetButton;

	private ImageIcon LEFTSetIcon;
	//Ƕ���ں����������
	private JPanel LeftFlowLayoutSouthPanel;

	private FlowLayout LeftFlowLayoutSouthPanel_FlowLayout;

	private JPanel GridPanel;

	private GridLayout GridPanel_GridLayout;

	public void setFrame()
	{
		setSize(1010, 600);
		int windowWidth = this.getWidth(); //��ô��ڿ�
		int windowHeight = this.getHeight(); //��ô��ڸ�
		Toolkit kit = Toolkit.getDefaultToolkit(); //���幤�߰�
		Dimension screenSize = kit.getScreenSize(); //��ȡ��Ļ�ĳߴ�
		int screenWidth = screenSize.width; //��ȡ��Ļ�Ŀ�
		int screenHeight = screenSize.height; //��ȡ��Ļ�ĸ�
		this.setLocation(screenWidth / 2 - windowWidth / 2, screenHeight / 2 - windowHeight / 2);//���ô��ھ�����ʾ
	}

	public AdminGUI(String UserName) throws IOException
	{
		setFrame();
		//��ʼ���������
		aSetPanel = new SetPanel();
		aAdminUserInformationManagement = new AdminUserInformationManagement(this);
		aAdminGoodsSearchPanel = new AdminGoodsSearchPanel(this);
		//��ʼ���������
		ThisAdmin = UserOperation.FindAdminInformation(UserName);
		aAdminInfoPanel = new AdminInfoPanel(AdminGUI.this.ThisAdmin);
		
		aAdminPurchaseHistoryPanel = new AdminPurchaseHistoryPanel(this);
		
		this.addWindowListener(new WindowAdapter() {//��Ӽӷ��ص�¼����ļ���
			public void windowClosing(WindowEvent e)
			{
				AdminGUI.this.dispose();
				MainClass mm = new MainClass();
				mm.BuildLoginGUI();
			}
		});
		
		//��������ʼ��
		HelpInformation = new JMenuItem("ʹ��С��ʿ");
		AboutThisProgram = new JMenuItem("���ڡ�");
		AboutThisProgram.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				JOptionPane.showMessageDialog(AdminGUI.this, "�̳�VIP���ѹ���ϵͳ\n�汾��1.1.0.0", "ϵͳ��Ϣ", -1);
			}
		});
		
		HelpMenu = new JMenu("ϵͳ��Ϣ");
		//HelpMenu.add(HelpInformation);
		HelpMenu.add(AboutThisProgram);
		
		TopMenuBar = new JMenuBar();
		TopMenuBar.add(HelpMenu);
		
		//��຺���˵� ����尴ť
		LeftSelectPanel_openPanelButton = new JButton("",LEFTopenPanelIcon = new ImageIcon(new File("").getCanonicalPath()+"/image/GuestGUIButton/openPanelIcon.png"));
		LEFTopenPanelIcon.setImage(LEFTopenPanelIcon.getImage().getScaledInstance(40,40,Image.SCALE_SMOOTH)); 
		LeftSelectPanel_openPanelButton.setHorizontalAlignment(JButton.LEFT);
		LeftSelectPanel_openPanelButton.setBorderPainted(false);
		LeftSelectPanel_openPanelButton.setBackground(new Color(140,189,239));
		LeftSelectPanel_openPanelButton.setMargin(new Insets(0,0,0,0));
		
		//��຺���˵� ��ҳ��ť
		LeftSelectPanel_ReturnRightOperationPanelButton = new JButton("   ��ҳ",LEFTHomeIcon = new ImageIcon(new File("").getCanonicalPath()+"/image/GuestGUIButton/homeIcon.png"));
		LeftSelectPanel_ReturnRightOperationPanelButton.setEnabled(false);
		LEFTHomeIcon.setImage(LEFTHomeIcon.getImage().getScaledInstance(40,40,Image.SCALE_SMOOTH)); 
		LeftSelectPanel_ReturnRightOperationPanelButton.setHorizontalAlignment(JButton.LEFT);
		LeftSelectPanel_ReturnRightOperationPanelButton.setBorderPainted(false);
		LeftSelectPanel_ReturnRightOperationPanelButton.setBackground(new Color(140,189,239));
		LeftSelectPanel_ReturnRightOperationPanelButton.setMargin(new Insets(0,0,0,0));
		
		//��຺���˵� �ͻ���Ϣ����ť
		LeftSelectPanel_UserInformationManagementButton = new JButton("   ��Ա�ͻ���Ϣ����",LEFTUserInformationManagementIcon = new ImageIcon(new File("").getCanonicalPath()+"/image/GuestGUIButton/AdminUserInformationManageIcon.png"));
		LEFTUserInformationManagementIcon.setImage(LEFTUserInformationManagementIcon.getImage().getScaledInstance(40,40,Image.SCALE_SMOOTH)); 
		LeftSelectPanel_UserInformationManagementButton.setHorizontalAlignment(JButton.LEFT);
		LeftSelectPanel_UserInformationManagementButton.setBorderPainted(false);
		LeftSelectPanel_UserInformationManagementButton.setBackground(new Color(140,189,239));
		LeftSelectPanel_UserInformationManagementButton.setMargin(new Insets(0,0,0,0));
		
		//��຺���˵� ���Ѽ�¼��ť
		LeftSelectPanel_ConsumptionRecordButton = new JButton("   �ͻ����Ѽ�¼����",LEFTConsumptionRecordIcon = new ImageIcon(new File("").getCanonicalPath()+"/image/GuestGUIButton/ConsumptionRecordIcon.png"));
		LEFTConsumptionRecordIcon.setImage(LEFTConsumptionRecordIcon.getImage().getScaledInstance(40,40,Image.SCALE_SMOOTH)); 
		LeftSelectPanel_ConsumptionRecordButton.setHorizontalAlignment(JButton.LEFT);
		LeftSelectPanel_ConsumptionRecordButton.setBorderPainted(false);
		LeftSelectPanel_ConsumptionRecordButton.setBackground(new Color(140,189,239));
		LeftSelectPanel_ConsumptionRecordButton.setMargin(new Insets(0,0,0,0));
		
		////��຺���˵� ��Ʒ��ѯ��ť
		LeftSelectPanel_GoodsInformationButton = new JButton("   �����Ʒ��Ϣ����",LEFTGoodsInformationIcon = new ImageIcon(new File("").getCanonicalPath()+"/image/GuestGUIButton/GoodsInformationIcon.png"));
		LEFTGoodsInformationIcon.setImage(LEFTGoodsInformationIcon.getImage().getScaledInstance(40,40,Image.SCALE_SMOOTH)); 
		LeftSelectPanel_GoodsInformationButton.setHorizontalAlignment(JButton.LEFT);
		LeftSelectPanel_GoodsInformationButton.setBorderPainted(false);
		LeftSelectPanel_GoodsInformationButton.setBackground(new Color(140,189,239));
		LeftSelectPanel_GoodsInformationButton.setMargin(new Insets(0,0,0,0));
		
		//��຺���˵� ������Ϣ������ť
		LeftSelectPanel_GuestInformationButton = new JButton("   ������Ϣ����",LEFTGuestInformationIcon = new ImageIcon(new File("").getCanonicalPath()+"/image/GuestGUIButton/GuestInformationIcon.png"));
		LEFTGuestInformationIcon.setImage(LEFTGuestInformationIcon.getImage().getScaledInstance(40,40,Image.SCALE_SMOOTH)); 
		LeftSelectPanel_GuestInformationButton.setHorizontalAlignment(JButton.LEFT);
		LeftSelectPanel_GuestInformationButton.setBorderPainted(false);
		LeftSelectPanel_GuestInformationButton.setBackground(new Color(140,189,239));
		LeftSelectPanel_GuestInformationButton.setMargin(new Insets(0,0,0,0));
		
		//������ò˵� ���ð�ť
		LeftSelectPanel_SetButton = new JButton("   ϵͳ",LEFTSetIcon = new ImageIcon(new File("").getCanonicalPath()+"/image/GuestGUIButton/SetIcon.png"));
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
		/////////////////////////////�Ҳ�ѡ��ť�ĳ�ʼ��
		RightOperation_SelectPanel_UserInformationManagementButton = new JButton("��Ա�ͻ���Ϣ����",UserInformationManagementIcon = new ImageIcon(new File("").getCanonicalPath()+"/image/GuestGUIButton/AdminUserInformationManageIcon.png"));
		UserInformationManagementIcon.setImage(UserInformationManagementIcon.getImage().getScaledInstance(100,100,Image.SCALE_DEFAULT)); 
		RightOperation_SelectPanel_UserInformationManagementButton.setPreferredSize(new Dimension(300,200));
		RightOperation_SelectPanel_UserInformationManagementButton.setBackground(new Color(140,189,239));
		/////////////////////////////�Ҳ�ѡ��ť�ĳ�ʼ��

		RightOperation_SelectPanel_ConsumptionRecordButton = new JButton("�ͻ����Ѽ�¼����",ConsumptionRecordIcon = new ImageIcon(new File("").getCanonicalPath()+"/image/GuestGUIButton/ConsumptionRecordIcon.png"));
		ConsumptionRecordIcon.setImage(ConsumptionRecordIcon.getImage().getScaledInstance(100,100,Image.SCALE_DEFAULT)); 
		RightOperation_SelectPanel_ConsumptionRecordButton.setPreferredSize(new Dimension(300,200));
		RightOperation_SelectPanel_ConsumptionRecordButton.setBackground(new Color(140,189,239));
        /////////////////////////////�Ҳ�ѡ��ť�ĳ�ʼ��
		RightOperation_SelectPanel_GoodsInformationButton = new JButton("�����Ʒ��Ϣ����",GoodsInformationIcon = new ImageIcon(new File("").getCanonicalPath()+"/image/GuestGUIButton/GoodsInformationIcon.png"));
		GoodsInformationIcon.setImage(GoodsInformationIcon.getImage().getScaledInstance(100,100,Image.SCALE_DEFAULT)); 
		RightOperation_SelectPanel_GoodsInformationButton.setPreferredSize(new Dimension(300,200));
		RightOperation_SelectPanel_GoodsInformationButton.setBackground(new Color(140,189,239));
        /////////////////////////////�Ҳ�ѡ��ť�ĳ�ʼ��
		RightOperation_SelectPanel_GuestInformationButton = new JButton("������Ϣ����",GuestInformationIcon = new ImageIcon(new File("").getCanonicalPath()+"/image/GuestGUIButton/GuestInformationIcon.png"));
		GuestInformationIcon.setImage(GuestInformationIcon.getImage().getScaledInstance(100,100,Image.SCALE_DEFAULT)); 
		RightOperation_SelectPanel_GuestInformationButton.setPreferredSize(new Dimension(300,200));
		RightOperation_SelectPanel_GuestInformationButton.setBackground(new Color(140,189,239));
		//�Ҳ�ѡ�����ĳ�ʼ��
		RightOperation_SelectPanel = new JPanel();
		RightOperation_SelectPanel.setLayout(RightOperation_SelectPanelFlowLayout = new FlowLayout(FlowLayout.LEFT,14,14));////
		RightOperation_SelectPanel.setPreferredSize(new Dimension(1,1));
		RightOperation_SelectPanel.add(RightOperation_SelectPanel_UserInformationManagementButton);
		RightOperation_SelectPanel.add(RightOperation_SelectPanel_ConsumptionRecordButton);
		RightOperation_SelectPanel.add(RightOperation_SelectPanel_GoodsInformationButton);
		RightOperation_SelectPanel.add(RightOperation_SelectPanel_GuestInformationButton);
		//�ϲ�������ʼ��
		JLayeredPane_backgroundLabel = new JLabel(background = new ImageIcon(new File("").getCanonicalPath()+"/image/GuestGUIBackground.png"),JLabel.RIGHT);
		
		JLayeredPane_backgroundPanel = new JPanel();
		JLayeredPane_backgroundPanel.setLayout(new GridLayout(1,1,0,0));
		JLayeredPane_backgroundPanel.add(JLayeredPane_backgroundLabel);
		JLayeredPane_backgroundPanel.setOpaque(false);
		JLayeredPane_backgroundPanel.setBorder(new EmptyBorder(50,0,0,0));
		//��ӭ�����ʼ��
		RightOperationPanel_UserNameLabel = new JLabel("��ã���������Ա"+ThisAdmin.getName()+"��");
		RightOperationPanel_UserNameLabel.setForeground(Color.white);
		RightOperationPanel_UserNameLabel.setFont(RightOperationPanel_UserNameLabel.getFont().deriveFont(30.0f));
		
		RightOperationPanel_ContextLabel = new JLabel("��������˵���ť����ҳ��ť���в���");
		RightOperationPanel_ContextLabel.setForeground(Color.white);
		RightOperationPanel_ContextLabel.setFont(RightOperationPanel_UserNameLabel.getFont().deriveFont(16.0f));
		RightOperationPanel_ContextLabel.setHorizontalAlignment(JLabel.LEFT);
		
		RightOperationPanel_ContextPanel = new JPanel();
		RightOperationPanel_ContextPanel.setLayout(new BoxLayout(RightOperationPanel_ContextPanel,BoxLayout.Y_AXIS));
		RightOperationPanel_ContextPanel.add(RightOperationPanel_UserNameLabel);//����
		RightOperationPanel_ContextPanel.add(RightOperationPanel_ContextLabel);
		RightOperationPanel_ContextPanel.setOpaque(false);
		
		//�Ϸ����
		RightOperation_NorthPanel = new JPanel();
		RightOperation_NorthPanel.setLayout(new BorderLayout());//����
		RightOperation_NorthPanel.add(RightOperationPanel_ContextPanel,"West");
		RightOperation_NorthPanel.add(JLayeredPane_backgroundPanel,"East");
		RightOperation_NorthPanel.setOpaque(false);
		//��������ʼ��
		RightOperation_GridNorthPanel = new JPanel();
		RightOperation_GridNorthPanel.setLayout(new GridLayout(1,1,0,0));//����
		RightOperation_GridNorthPanel.add(RightOperation_NorthPanel);	
		RightOperation_GridNorthPanel.setBorder(new EmptyBorder(14,14,14,14));
		RightOperation_GridNorthPanel.setBackground(new Color(19,146,249));
		//��������ʼ��
		RightOperationPanel = new JPanel();
		RightOperationPanel_BorderLayout = new BorderLayout();
		RightOperationPanel.setLayout(RightOperationPanel_BorderLayout);//����
		RightOperationPanel.add(RightOperation_GridNorthPanel,"North");
		RightOperationPanel.add(RightOperation_SelectPanel ,"Center");	
		RightOperationPanel.setPreferredSize(new Dimension(1,840));
		
		RightOperationPanel_ScrollPane = new JScrollPane(RightOperationPanel,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		RightOperationPanel_ScrollPane.setBorder(null);
		
		LeftFlowLayoutPanel = new JPanel();
		LeftFlowLayoutPanel_FlowLayout = new FlowLayout(FlowLayout.LEFT,10,10);
		LeftFlowLayoutPanel.setLayout(LeftFlowLayoutPanel_FlowLayout);//����
		LeftFlowLayoutPanel.setPreferredSize(new Dimension(65,300));
		LeftFlowLayoutPanel.add(LeftSelectPanel);
		LeftFlowLayoutPanel.setBackground(new Color(140,189,239));
		//�ײ����ð�ť����ʼ��
		GridPanel = new JPanel();
		GridPanel.setPreferredSize(new Dimension(80,40));
		GridPanel_GridLayout = new GridLayout(0,1,0,0);//����
		GridPanel.setLayout(GridPanel_GridLayout);
		GridPanel.add(LeftSelectPanel_SetButton);
		//���Ƶײ����ð�ť���仯������ʼ��
		LeftFlowLayoutSouthPanel = new JPanel();
		LeftFlowLayoutSouthPanel_FlowLayout = new FlowLayout(FlowLayout.LEFT,10,10);//����
		LeftFlowLayoutSouthPanel.setLayout(LeftFlowLayoutSouthPanel_FlowLayout);
		LeftFlowLayoutSouthPanel.add(GridPanel);
		LeftFlowLayoutSouthPanel.setBackground(new Color(140,189,239));
		//���ѡ�������ⲿǶ�����
		LeftFlowLayoutOUTPanel = new JPanel();
		LeftFlowLayoutOUTPanel_BorderLayout = new BorderLayout();//����
		LeftFlowLayoutOUTPanel.setLayout(LeftFlowLayoutOUTPanel_BorderLayout);
		LeftFlowLayoutOUTPanel.setPreferredSize(new Dimension(65,300));
		LeftFlowLayoutOUTPanel.add(LeftFlowLayoutPanel,"Center");
		LeftFlowLayoutOUTPanel.add(LeftFlowLayoutSouthPanel,"South");
		LeftFlowLayoutOUTPanel.setBackground(new Color(140,189,239));
		//�����ĳ�ʼ��
		MainPanel = new JPanel();
		MainPanel_BorderLayout = new BorderLayout();//����
		MainPanel.setLayout(MainPanel_BorderLayout);
		MainPanel.add(TopMenuBar,"North");
		MainPanel.add(LeftFlowLayoutOUTPanel,"West");
		MainPanel.add(RightOperationPanel_ScrollPane,"Center");
		isRightOperationPanelUsed = true;
		//����㱣�����ĳ�ʼ��
		FramePanel = new JPanel();
		FramePanel_GridLayout = new GridLayout(1,1);//����
		FramePanel.setLayout(FramePanel_GridLayout);
		FramePanel.add(MainPanel);
		

		setContentPane(FramePanel);

		//�����չ������尴ť
		LeftSelectPanel_openPanelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				if(LeftSelectPanel_openPanelButtonCondition == LeftSelectPanel_openPanelButtonConditionEnum.CLOSE)
				{//���򿪲���
					GridPanel.setPreferredSize(new Dimension(190,40));
					LeftFlowLayoutPanel.setPreferredSize(new Dimension(180,300));
					LeftFlowLayoutOUTPanel.setPreferredSize(new Dimension(180,300));//����ҳ��ߴ�
					LeftSelectPanel_openPanelButtonCondition = LeftSelectPanel_openPanelButtonConditionEnum.OPEN;
					LeftSelectPanel.setPreferredSize(new Dimension(190,300));
					MainPanel.repaint();
					MainPanel.revalidate();
				}
				else
				{//���رղ���
					GridPanel.setPreferredSize(new Dimension(80,40));
					LeftFlowLayoutPanel.setPreferredSize(new Dimension(65,300));
					LeftFlowLayoutOUTPanel.setPreferredSize(new Dimension(65,300));//����ҳ��ߴ�
					LeftSelectPanel_openPanelButtonCondition = LeftSelectPanel_openPanelButtonConditionEnum.CLOSE;
					LeftSelectPanel.setPreferredSize(new Dimension(80,300));
					MainPanel.repaint();
					MainPanel.revalidate();
				}
			}
		});
		
		///�����ҳ��ť
		LeftSelectPanel_ReturnRightOperationPanelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				ThisAdmin = UserOperation.FindAdminInformation(UserName);
				RightOperationPanel_UserNameLabel.setText("��ã���������Ա"+ThisAdmin.getName()+"��");
				if(isSetPanel == true)//����������ť״̬
				{
					MainPanel.remove(aSetPanel);
					isSetPanel = false;
				}
				LeftSelectPanel_SetButton.setEnabled(true);
				if(isAdminUserInformationManagement == true)//����������ť״̬
				{
					MainPanel.remove(aAdminUserInformationManagement);
					isAdminUserInformationManagement = false;
				}
				if(isAdminGoodsSearchPanel == true)//����������ť״̬
				{
					MainPanel.remove(aAdminGoodsSearchPanel);
					isAdminGoodsSearchPanel = false;
				}
				if(isAdminInformationPanelUsed == true)//����������ť״̬
				{
					MainPanel.remove(aAdminInfoPanel);
					isAdminInformationPanelUsed = false;
				}
				if(isAdminPurchaseHistoryPanel == true)//����������ť״̬
				{
					MainPanel.remove(aAdminPurchaseHistoryPanel);
					isAdminPurchaseHistoryPanel = false;
				}
				MainPanel.repaint();
				MainPanel.add(RightOperationPanel_ScrollPane,"Center");
				isRightOperationPanelUsed = true;
				MainPanel.revalidate();
				AdminGUI.this.LeftSelectPanel_GoodsInformationButton.setEnabled(true);//��Ʒ��Ϣ
				LeftSelectPanel_ReturnRightOperationPanelButton.setEnabled(false);//��ҳ
				AdminGUI.this.LeftSelectPanel_ConsumptionRecordButton.setEnabled(true);//���Ѽ�¼
				AdminGUI.this.LeftSelectPanel_GuestInformationButton.setEnabled(true);//������Ϣ
				LeftSelectPanel_UserInformationManagementButton.setEnabled(true);
				
			}
		});
		
		////��� ��Ա��Ϣ����
		LeftSelectPanel_UserInformationManagementButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				if(isSetPanel == true)//����������ť״̬
				{
					MainPanel.remove(aSetPanel);
					isSetPanel = false;
				}
				if(isRightOperationPanelUsed == true)//����������ť״̬
				{
					MainPanel.remove(RightOperationPanel_ScrollPane);
					isRightOperationPanelUsed = false;
				}
				if(isAdminInformationPanelUsed == true)//����������ť״̬
				{
					MainPanel.remove(aAdminInfoPanel);
					isAdminInformationPanelUsed = false;//����������ť״̬
				}
				if(isAdminGoodsSearchPanel == true)
				{
					MainPanel.remove(aAdminGoodsSearchPanel);//����������ť״̬
					isAdminGoodsSearchPanel = false;
				}
				if(isAdminPurchaseHistoryPanel == true)
				{
					MainPanel.remove(aAdminPurchaseHistoryPanel);//����������ť״̬
					isAdminPurchaseHistoryPanel = false;
				}
				MainPanel.repaint();
				MainPanel.add(aAdminUserInformationManagement,"Center");
				isAdminUserInformationManagement = true;
				AdminGUI.this.setVisible(true);
				MainPanel.revalidate();
				LeftSelectPanel_UserInformationManagementButton.setEnabled(false);
				AdminGUI.this.LeftSelectPanel_ConsumptionRecordButton.setEnabled(true);//���Ѽ�¼
				AdminGUI.this.LeftSelectPanel_GoodsInformationButton.setEnabled(true);//��Ʒ��Ϣ
				LeftSelectPanel_ReturnRightOperationPanelButton.setEnabled(true);//��ҳ
				AdminGUI.this.LeftSelectPanel_GuestInformationButton.setEnabled(true);//������Ϣ
				LeftSelectPanel_SetButton.setEnabled(true);
			}
		});
		
		////�Ҳ� ��Ա��Ϣ����
		RightOperation_SelectPanel_UserInformationManagementButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				if(isSetPanel == true)//����������ť״̬
				{
					MainPanel.remove(aSetPanel);
					isSetPanel = false;
				}
				if(isRightOperationPanelUsed == true)//����������ť״̬
				{
					MainPanel.remove(RightOperationPanel_ScrollPane);
					isRightOperationPanelUsed = false;
				}
				if(isAdminInformationPanelUsed == true)//����������ť״̬
				{
					MainPanel.remove(aAdminInfoPanel);
					isAdminInformationPanelUsed = false;
				}
				if(isAdminGoodsSearchPanel == true)//����������ť״̬
				{
					MainPanel.remove(aAdminGoodsSearchPanel);
					isAdminGoodsSearchPanel = false;
				}
				if(isAdminPurchaseHistoryPanel == true)//����������ť״̬
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
				AdminGUI.this.LeftSelectPanel_ConsumptionRecordButton.setEnabled(true);//���Ѽ�¼
				AdminGUI.this.LeftSelectPanel_GoodsInformationButton.setEnabled(true);//��Ʒ��Ϣ
				LeftSelectPanel_ReturnRightOperationPanelButton.setEnabled(true);//��ҳ
				AdminGUI.this.LeftSelectPanel_GuestInformationButton.setEnabled(true);//������Ϣ
				LeftSelectPanel_SetButton.setEnabled(true);
			}
		});
		
		
		////��� ���Ѽ�¼             ------------------------------------aGuestPurchaseHistoryPanel
		LeftSelectPanel_ConsumptionRecordButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				if(isSetPanel == true)
				{//����������ť״̬
					MainPanel.remove(aSetPanel);
					isSetPanel = false;
				}
				if(isAdminUserInformationManagement == true)//����������ť״̬
				{
					MainPanel.remove(aAdminUserInformationManagement);
					isAdminUserInformationManagement = false;
				}
				if(isRightOperationPanelUsed == true)//����������ť״̬
				{
					MainPanel.remove(RightOperationPanel_ScrollPane);
					isRightOperationPanelUsed = false;
				}
				if(isAdminInformationPanelUsed == true)//����������ť״̬
				{
					MainPanel.remove(aAdminInfoPanel);
					isAdminInformationPanelUsed = false;
				}
				if(isAdminGoodsSearchPanel == true)//����������ť״̬
				{
					MainPanel.remove(aAdminGoodsSearchPanel);
					isAdminGoodsSearchPanel = false;
				}
				MainPanel.repaint();
				MainPanel.add(aAdminPurchaseHistoryPanel,"Center");
				isAdminPurchaseHistoryPanel = true;
				AdminGUI.this.setVisible(true);
				MainPanel.revalidate();
				AdminGUI.this.LeftSelectPanel_ConsumptionRecordButton.setEnabled(false);//���Ѽ�¼
				AdminGUI.this.LeftSelectPanel_GoodsInformationButton.setEnabled(true);//��Ʒ��Ϣ
				LeftSelectPanel_ReturnRightOperationPanelButton.setEnabled(true);//��ҳ
				AdminGUI.this.LeftSelectPanel_GuestInformationButton.setEnabled(true);//������Ϣ
				LeftSelectPanel_UserInformationManagementButton.setEnabled(true);
				LeftSelectPanel_SetButton.setEnabled(true);
			}
		});
		
		////�Ҳ� ���Ѽ�¼
		RightOperation_SelectPanel_ConsumptionRecordButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				if(isSetPanel == true)//����������ť״̬
				{
					MainPanel.remove(aSetPanel);
					isSetPanel = false;
				}
				if(isAdminUserInformationManagement == true)//����������ť״̬
				{
					MainPanel.remove(aAdminUserInformationManagement);
					isAdminUserInformationManagement = false;
				}
				if(isRightOperationPanelUsed == true)//����������ť״̬
				{
					MainPanel.remove(RightOperationPanel_ScrollPane);
					isRightOperationPanelUsed = false;
				}
				if(isAdminInformationPanelUsed == true)//����������ť״̬
				{
					MainPanel.remove(aAdminInfoPanel);
					isAdminInformationPanelUsed = false;
				}
				if(isAdminGoodsSearchPanel == true)//����������ť״̬
				{
					MainPanel.remove(aAdminGoodsSearchPanel);
					isAdminGoodsSearchPanel = false;
				}
				MainPanel.repaint();
				MainPanel.add(aAdminPurchaseHistoryPanel,"Center");
				isAdminPurchaseHistoryPanel = true;
				AdminGUI.this.setVisible(true);
				MainPanel.revalidate();
				AdminGUI.this.LeftSelectPanel_ConsumptionRecordButton.setEnabled(false);//���Ѽ�¼
				AdminGUI.this.LeftSelectPanel_GoodsInformationButton.setEnabled(true);//��Ʒ��Ϣ
				LeftSelectPanel_ReturnRightOperationPanelButton.setEnabled(true);//��ҳ
				AdminGUI.this.LeftSelectPanel_GuestInformationButton.setEnabled(true);//������Ϣ
				LeftSelectPanel_UserInformationManagementButton.setEnabled(true);
				LeftSelectPanel_SetButton.setEnabled(true);
			}
		});
		
		////������ ��Ʒ��Ϣ��ѯ��ť
		LeftSelectPanel_GoodsInformationButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				//	private GuestGoodsSearchPanel aGuestGoodsSearchPanel;
				//  private boolean isGuestGoodsSearchPanel = false;
				if(isSetPanel == true)//����������ť״̬
				{
					MainPanel.remove(aSetPanel);
					isSetPanel = false;
				}
				if(isAdminUserInformationManagement == true)//����������ť״̬
				{
					MainPanel.remove(aAdminUserInformationManagement);
					isAdminUserInformationManagement = false;
				}
				if(isRightOperationPanelUsed == true)//����������ť״̬
				{
					MainPanel.remove(RightOperationPanel_ScrollPane);
					isRightOperationPanelUsed = false;
				}
				if(isAdminInformationPanelUsed == true)//����������ť״̬
				{
					MainPanel.remove(aAdminInfoPanel);
					isAdminInformationPanelUsed = false;
				}
				if(isAdminPurchaseHistoryPanel == true)//����������ť״̬
				{
					MainPanel.remove(aAdminPurchaseHistoryPanel);
					isAdminPurchaseHistoryPanel = false;
				}
				MainPanel.repaint();
				MainPanel.add(aAdminGoodsSearchPanel,"Center");
				isAdminGoodsSearchPanel = true;
				AdminGUI.this.setVisible(true);
				MainPanel.revalidate();
				AdminGUI.this.LeftSelectPanel_GoodsInformationButton.setEnabled(false);//��Ʒ��Ϣ
				LeftSelectPanel_ReturnRightOperationPanelButton.setEnabled(true);//��ҳ
				AdminGUI.this.LeftSelectPanel_ConsumptionRecordButton.setEnabled(true);//���Ѽ�¼
				AdminGUI.this.LeftSelectPanel_GuestInformationButton.setEnabled(true);//������Ϣ
				LeftSelectPanel_UserInformationManagementButton.setEnabled(true);
				LeftSelectPanel_SetButton.setEnabled(true);
			}
		});
		////�Ҳ���� ��Ʒ��Ϣ��ѯ��ť
		RightOperation_SelectPanel_GoodsInformationButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				//	private GuestGoodsSearchPanel aGuestGoodsSearchPanel;
				//  private boolean isGuestGoodsSearchPanel = false;
				if(isSetPanel == true)//����������ť״̬
				{
					MainPanel.remove(aSetPanel);
					isSetPanel = false;
				}
				if(isAdminUserInformationManagement == true)//����������ť״̬
				{
					MainPanel.remove(aAdminUserInformationManagement);
					isAdminUserInformationManagement = false;
				}
				if(isRightOperationPanelUsed == true)//����������ť״̬
				{
					MainPanel.remove(RightOperationPanel_ScrollPane);
					isRightOperationPanelUsed = false;
				}
				if(isAdminInformationPanelUsed == true)//����������ť״̬
				{
					MainPanel.remove(aAdminInfoPanel);
					isAdminInformationPanelUsed = false;
				}
				if(isAdminPurchaseHistoryPanel == true)//����������ť״̬
				{
					MainPanel.remove(aAdminPurchaseHistoryPanel);
					isAdminPurchaseHistoryPanel = false;
				}
				MainPanel.repaint();
				MainPanel.add(aAdminGoodsSearchPanel,"Center");
				isAdminGoodsSearchPanel = true;
				AdminGUI.this.setVisible(true);
				MainPanel.revalidate();
				AdminGUI.this.LeftSelectPanel_GoodsInformationButton.setEnabled(false);//��Ʒ��Ϣ
				LeftSelectPanel_ReturnRightOperationPanelButton.setEnabled(true);//��ҳ
				AdminGUI.this.LeftSelectPanel_ConsumptionRecordButton.setEnabled(true);//���Ѽ�¼
				AdminGUI.this.LeftSelectPanel_GuestInformationButton.setEnabled(true);//������Ϣ
				LeftSelectPanel_UserInformationManagementButton.setEnabled(true);
				LeftSelectPanel_SetButton.setEnabled(true);
			}
		});
		////
		
		////������ ������Ϣ������ť
		LeftSelectPanel_GuestInformationButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				if(isSetPanel == true)//����������ť״̬
				{
					MainPanel.remove(aSetPanel);
					isSetPanel = false;
				}
				if(isAdminUserInformationManagement == true)//����������ť״̬
				{
					MainPanel.remove(aAdminUserInformationManagement);
					isAdminUserInformationManagement = false;
				}
				if(isAdminGoodsSearchPanel == true)//����������ť״̬
				{
					MainPanel.remove(aAdminGoodsSearchPanel);//����������ť״̬
					isAdminGoodsSearchPanel = false;
				}
				if(isRightOperationPanelUsed == true)//����������ť״̬
				{
					MainPanel.remove(RightOperationPanel_ScrollPane);
					isRightOperationPanelUsed = false;
				}
				if(isAdminPurchaseHistoryPanel == true)//����������ť״̬
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
				AdminGUI.this.LeftSelectPanel_GoodsInformationButton.setEnabled(true);//��Ʒ��Ϣ
				LeftSelectPanel_ReturnRightOperationPanelButton.setEnabled(true);//��ҳ
				AdminGUI.this.LeftSelectPanel_ConsumptionRecordButton.setEnabled(true);//���Ѽ�¼
				AdminGUI.this.LeftSelectPanel_GuestInformationButton.setEnabled(false);//������Ϣ
				LeftSelectPanel_UserInformationManagementButton.setEnabled(true);
				LeftSelectPanel_SetButton.setEnabled(true);
			}
		});
		////�Ҳ���� ������Ϣ������ť
		RightOperation_SelectPanel_GuestInformationButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				if(isSetPanel == true)//����������ť״̬
				{
					MainPanel.remove(aSetPanel);
					isSetPanel = false;
				}
				if(isAdminUserInformationManagement == true)//����������ť״̬
				{
					MainPanel.remove(aAdminUserInformationManagement);
					isAdminUserInformationManagement = false;
				}
				if(isAdminGoodsSearchPanel == true)//����������ť״̬
				{
					MainPanel.remove(aAdminGoodsSearchPanel);
					isAdminGoodsSearchPanel = false;
				}
				if(isRightOperationPanelUsed == true)//����������ť״̬
				{
					MainPanel.remove(RightOperationPanel_ScrollPane);
					isRightOperationPanelUsed = false;
				}
				if(isAdminPurchaseHistoryPanel == true)//����������ť״̬
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
				AdminGUI.this.LeftSelectPanel_GoodsInformationButton.setEnabled(true);//��Ʒ��Ϣ
				LeftSelectPanel_ReturnRightOperationPanelButton.setEnabled(true);//��ҳ
				AdminGUI.this.LeftSelectPanel_ConsumptionRecordButton.setEnabled(true);//���Ѽ�¼
				AdminGUI.this.LeftSelectPanel_GuestInformationButton.setEnabled(false);//������Ϣ
				LeftSelectPanel_UserInformationManagementButton.setEnabled(true);
				LeftSelectPanel_SetButton.setEnabled(true);
			}
		});
		
		
		LeftSelectPanel_SetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				if(isAdminInformationPanelUsed == true)//����������ť״̬
				{
					MainPanel.remove(aAdminInfoPanel);
					isAdminInformationPanelUsed = false;
				}
				if(isAdminUserInformationManagement == true)//����������ť״̬
				{
					MainPanel.remove(aAdminUserInformationManagement);
					isAdminUserInformationManagement = false;
				}
				if(isAdminGoodsSearchPanel == true)//����������ť״̬
				{
					MainPanel.remove(aAdminGoodsSearchPanel);
					isAdminGoodsSearchPanel = false;
				}
				if(isRightOperationPanelUsed == true)//����������ť״̬
				{
					MainPanel.remove(RightOperationPanel_ScrollPane);
					isRightOperationPanelUsed = false;
				}
				if(isAdminPurchaseHistoryPanel == true)//����������ť״̬
				{
					MainPanel.remove(aAdminPurchaseHistoryPanel);
					isAdminPurchaseHistoryPanel = false;
				}
				MainPanel.repaint();
				MainPanel.add(aSetPanel,"Center");
				isSetPanel = true;
				MainPanel.revalidate();
				LeftSelectPanel_SetButton.setEnabled(false);
				AdminGUI.this.LeftSelectPanel_GoodsInformationButton.setEnabled(true);//��Ʒ��Ϣ
				LeftSelectPanel_ReturnRightOperationPanelButton.setEnabled(true);//��ҳ
				AdminGUI.this.LeftSelectPanel_ConsumptionRecordButton.setEnabled(true);//���Ѽ�¼
				AdminGUI.this.LeftSelectPanel_GuestInformationButton.setEnabled(true);//������Ϣ
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

