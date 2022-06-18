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
	enum LeftSelectPanel_openPanelButtonConditionEnum//���𺺱��˵��򿪵�����
	{
		CLOSE,
		OPEN
	}
	
	private JLabel JLayeredPane_backgroundLabel;
	
	private ImageIcon background;
	
	private Guest ThisGuest;
	private JButton LeftSelectPanel_openPanelButton,//���ѡ��ť�ĳ�ʼ��ϵ��
			LeftSelectPanel_ReturnRightOperationPanelButton,
			LeftSelectPanel_ConsumptionRecordButton, 
			LeftSelectPanel_GoodsInformationButton, 
			LeftSelectPanel_GuestInformationButton;
	private LeftSelectPanel_openPanelButtonConditionEnum LeftSelectPanel_openPanelButtonCondition = LeftSelectPanel_openPanelButtonConditionEnum.CLOSE;
	
	private JButton RightOperation_SelectPanel_ConsumptionRecordButton, //�Ҳ�ѡ��ť�ֶ�
			RightOperation_SelectPanel_GoodsInformationButton, 
			RightOperation_SelectPanel_GuestInformationButton;
	private ImageIcon ConsumptionRecordIcon,GoodsInformationIcon,GuestInformationIcon;
	private ImageIcon LEFTopenPanelIcon,LEFTHomeIcon,LEFTConsumptionRecordIcon,LEFTGoodsInformationIcon,LEFTGuestInformationIcon;
	
	private JMenuBar TopMenuBar;//�˵���
	private JMenu HelpMenu;
	private JMenuItem HelpInformation,AboutThisProgram;
	
	private JLabel RightOperationPanel_UserNameLabel,RightOperationPanel_ContextLabel;
	
	private JScrollPane RightOperationPanel_ScrollPane;
	private JPanel FramePanel,MainPanel,LeftFlowLayoutPanel, LeftSelectPanel,//��ҳ���������
			RightOperationPanel,RightOperation_SelectPanel,RightOperation_NorthPanel,
			RightOperation_GridNorthPanel,RightOperationPanel_ContextPanel,
			JLayeredPane_backgroundPanel;
	private boolean isRightOperationPanelUsed = true;
	
	private GuestInformationPanel gip;//�������
	private boolean isGuestInformationPanelUsed = false;
	private GuestGoodsSearchPanel aGuestGoodsSearchPanel;//�������
	private boolean isGuestGoodsSearchPanel = false;
	private GuestPurchaseHistoryPanel aGuestPurchaseHistoryPanel;//�������
	private boolean isGuestPurchaseHistoryPanel = false;
	private SetPanel aSetPanel;//�������
	private boolean isSetPanel = false;
	
	private BorderLayout MainPanel_BorderLayout, RightOperationPanel_BorderLayout;//����
	private GridLayout FramePanel_GridLayout, LeftSelectPanel_GridLayout;//����
	private FlowLayout LeftFlowLayoutPanel_FlowLayout;//����
	private FlowLayout RightOperation_SelectPanelFlowLayout;//����

	private JButton LeftSelectPanel_SetButton;

	private ImageIcon LEFTSetIcon;

	private JPanel LeftFlowLayoutOUTPanel;//Ƕ�����

	private BorderLayout LeftFlowLayoutOUTPanel_BorderLayout;

	private JPanel LeftFlowLayoutSouthPanel;//Ƕ�����

	private FlowLayout LeftFlowLayoutSouthPanel_FlowLayout;

	private JPanel GridPanel;//�������

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
	
	public GuestGUI(String UserName) throws IOException
	{
		setFrame();
		//���й�������ʼ��
		ThisGuest = UserOperation.FindUserInformation(UserName);
		gip = new GuestInformationPanel(GuestGUI.this.ThisGuest);
		aGuestPurchaseHistoryPanel = new GuestPurchaseHistoryPanel(ThisGuest);
		aGuestGoodsSearchPanel = new GuestGoodsSearchPanel(this,ThisGuest);
		aSetPanel = new SetPanel();
		
		this.addWindowListener(new WindowAdapter() {//���ص�¼����ļ���
			public void windowClosing(WindowEvent e)
			{
				GuestGUI.this.dispose();
				MainClass mm = new MainClass();
				mm.BuildLoginGUI();
			}
		});
		
		
		HelpInformation = new JMenuItem("ʹ��С��ʿ");
		AboutThisProgram = new JMenuItem("���ڡ�");
		AboutThisProgram.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				JOptionPane.showMessageDialog(GuestGUI.this, "�̳�VIP���ѹ���ϵͳ\n�汾��1.1.0.0", "ϵͳ��Ϣ", -1);
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
		
		//��຺���˵� ���Ѽ�¼��ť
		LeftSelectPanel_ConsumptionRecordButton = new JButton("   ���Ѽ�¼��ѯ",LEFTConsumptionRecordIcon = new ImageIcon(new File("").getCanonicalPath()+"/image/GuestGUIButton/ConsumptionRecordIcon.png"));
		LEFTConsumptionRecordIcon.setImage(LEFTConsumptionRecordIcon.getImage().getScaledInstance(40,40,Image.SCALE_SMOOTH)); 
		LeftSelectPanel_ConsumptionRecordButton.setHorizontalAlignment(JButton.LEFT);
		LeftSelectPanel_ConsumptionRecordButton.setBorderPainted(false);
		LeftSelectPanel_ConsumptionRecordButton.setBackground(new Color(140,189,239));
		LeftSelectPanel_ConsumptionRecordButton.setMargin(new Insets(0,0,0,0));
		
		////��຺���˵� ��Ʒ��ѯ��ť
		LeftSelectPanel_GoodsInformationButton = new JButton("   ��Ʒ��Ϣ��ѯ",LEFTGoodsInformationIcon = new ImageIcon(new File("").getCanonicalPath()+"/image/GuestGUIButton/GoodsInformationIcon.png"));
		LEFTGoodsInformationIcon.setImage(LEFTGoodsInformationIcon.getImage().getScaledInstance(40,40,Image.SCALE_SMOOTH)); //����ͼƬ��С
		LeftSelectPanel_GoodsInformationButton.setHorizontalAlignment(JButton.LEFT);
		LeftSelectPanel_GoodsInformationButton.setBorderPainted(false);
		LeftSelectPanel_GoodsInformationButton.setBackground(new Color(140,189,239));
		LeftSelectPanel_GoodsInformationButton.setMargin(new Insets(0,0,0,0));
		
		//��຺���˵� ������Ϣ������ť
		LeftSelectPanel_GuestInformationButton = new JButton("   ������Ϣ����",LEFTGuestInformationIcon = new ImageIcon(new File("").getCanonicalPath()+"/image/GuestGUIButton/GuestInformationIcon.png"));
		LEFTGuestInformationIcon.setImage(LEFTGuestInformationIcon.getImage().getScaledInstance(40,40,Image.SCALE_SMOOTH)); //����ͼƬ��С
		LeftSelectPanel_GuestInformationButton.setHorizontalAlignment(JButton.LEFT);
		LeftSelectPanel_GuestInformationButton.setBorderPainted(false);
		LeftSelectPanel_GuestInformationButton.setBackground(new Color(140,189,239));
		LeftSelectPanel_GuestInformationButton.setMargin(new Insets(0,0,0,0));
		
		//��� ����
		LeftSelectPanel_SetButton = new JButton("   ϵͳ",LEFTSetIcon = new ImageIcon(new File("").getCanonicalPath()+"/image/GuestGUIButton/SetIcon.png"));
		LEFTSetIcon.setImage(LEFTSetIcon.getImage().getScaledInstance(40,40,Image.SCALE_SMOOTH)); //����ͼƬ��С
		LeftSelectPanel_SetButton.setHorizontalAlignment(JButton.LEFT);
		LeftSelectPanel_SetButton.setBorderPainted(false);
		LeftSelectPanel_SetButton.setBackground(new Color(140,189,239));
		LeftSelectPanel_SetButton.setMargin(new Insets(0,0,0,0));
		//ѡ������ʼ��
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
		//�Ҳ����ѡ��ť��ʼ��
		RightOperation_SelectPanel_ConsumptionRecordButton = new JButton("���Ѽ�¼��ѯ",ConsumptionRecordIcon = new ImageIcon(new File("").getCanonicalPath()+"/image/GuestGUIButton/ConsumptionRecordIcon.png"));
		ConsumptionRecordIcon.setImage(ConsumptionRecordIcon.getImage().getScaledInstance(100,100,Image.SCALE_DEFAULT)); 
		RightOperation_SelectPanel_ConsumptionRecordButton.setPreferredSize(new Dimension(300,200));
		RightOperation_SelectPanel_ConsumptionRecordButton.setBackground(new Color(140,189,239));
		//�Ҳ����ѡ��ť��ʼ��
		RightOperation_SelectPanel_GoodsInformationButton = new JButton("��Ʒ��Ϣ��ѯ",GoodsInformationIcon = new ImageIcon(new File("").getCanonicalPath()+"/image/GuestGUIButton/GoodsInformationIcon.png"));
		GoodsInformationIcon.setImage(GoodsInformationIcon.getImage().getScaledInstance(100,100,Image.SCALE_DEFAULT)); 
		RightOperation_SelectPanel_GoodsInformationButton.setPreferredSize(new Dimension(300,200));
		RightOperation_SelectPanel_GoodsInformationButton.setBackground(new Color(140,189,239));
		//�Ҳ����ѡ��ť��ʼ��
		RightOperation_SelectPanel_GuestInformationButton = new JButton("������Ϣ����",GuestInformationIcon = new ImageIcon(new File("").getCanonicalPath()+"/image/GuestGUIButton/GuestInformationIcon.png"));
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
		//��������ʼ��
		JLayeredPane_backgroundPanel = new JPanel();
		JLayeredPane_backgroundPanel.setLayout(new GridLayout(1,1,0,0));
		JLayeredPane_backgroundPanel.add(JLayeredPane_backgroundLabel);
		JLayeredPane_backgroundPanel.setOpaque(false);
		JLayeredPane_backgroundPanel.setBorder(new EmptyBorder(50,0,0,0));
		//������������ʼ��
		RightOperationPanel_UserNameLabel = new JLabel("��ӭ������"+ThisGuest.getName()+"��");
		RightOperationPanel_UserNameLabel.setForeground(Color.white);
		RightOperationPanel_UserNameLabel.setFont(RightOperationPanel_UserNameLabel.getFont().deriveFont(30.0f));
		
		RightOperationPanel_ContextLabel = new JLabel("����������Щʲô�أ�");
		RightOperationPanel_ContextLabel.setForeground(Color.white);
		RightOperationPanel_ContextLabel.setFont(RightOperationPanel_UserNameLabel.getFont().deriveFont(16.0f));
		RightOperationPanel_ContextLabel.setHorizontalAlignment(JLabel.LEFT);
		
		RightOperationPanel_ContextPanel = new JPanel();
		RightOperationPanel_ContextPanel.setLayout(new BoxLayout(RightOperationPanel_ContextPanel,BoxLayout.Y_AXIS));
		RightOperationPanel_ContextPanel.add(RightOperationPanel_UserNameLabel);
		RightOperationPanel_ContextPanel.add(RightOperationPanel_ContextLabel);
		RightOperationPanel_ContextPanel.setOpaque(false);
		
		//�Ϸ����
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
		//�Ҳ��������ʼ��
		RightOperationPanel = new JPanel();
		RightOperationPanel_BorderLayout = new BorderLayout();
		RightOperationPanel.setLayout(RightOperationPanel_BorderLayout);
		RightOperationPanel.add(RightOperation_GridNorthPanel,"North");
		RightOperationPanel.add(RightOperation_SelectPanel ,"Center");	
		RightOperationPanel.setPreferredSize(new Dimension(1,840));
		
		RightOperationPanel_ScrollPane = new JScrollPane(RightOperationPanel,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		RightOperationPanel_ScrollPane.setBorder(null);
		//�ⲿǶ�װ�ť��ʼ��
		LeftFlowLayoutPanel = new JPanel();
		LeftFlowLayoutPanel_FlowLayout = new FlowLayout(FlowLayout.LEFT,10,10);
		LeftFlowLayoutPanel.setLayout(LeftFlowLayoutPanel_FlowLayout);
		LeftFlowLayoutPanel.setPreferredSize(new Dimension(65,200));
		LeftFlowLayoutPanel.add(LeftSelectPanel);
		LeftFlowLayoutPanel.setBackground(new Color(140,189,239));
		//���ð�ť��ʼ�����
		GridPanel = new JPanel();
		GridPanel.setPreferredSize(new Dimension(80,40));
		GridPanel_GridLayout = new GridLayout(0,1,0,0);
		GridPanel.setLayout(GridPanel_GridLayout);
		GridPanel.add(LeftSelectPanel_SetButton);
		//Ƕ�׵ײ���������ʼ��������ʼ��
		LeftFlowLayoutSouthPanel = new JPanel();
		LeftFlowLayoutSouthPanel_FlowLayout = new FlowLayout(FlowLayout.LEFT,10,10);
		LeftFlowLayoutSouthPanel.setLayout(LeftFlowLayoutSouthPanel_FlowLayout);
		LeftFlowLayoutSouthPanel.add(GridPanel);
		LeftFlowLayoutSouthPanel.setBackground(new Color(140,189,239));
		//������Ƕ������ʼ��
		LeftFlowLayoutOUTPanel = new JPanel();
		LeftFlowLayoutOUTPanel_BorderLayout = new BorderLayout();
		LeftFlowLayoutOUTPanel.setLayout(LeftFlowLayoutOUTPanel_BorderLayout);
		LeftFlowLayoutOUTPanel.setPreferredSize(new Dimension(65,300));
		LeftFlowLayoutOUTPanel.add(LeftFlowLayoutPanel,"Center");
		LeftFlowLayoutOUTPanel.add(LeftFlowLayoutSouthPanel,"South");
		LeftFlowLayoutOUTPanel.setBackground(new Color(140,189,239));
		//ס����ʼ��
		MainPanel = new JPanel();
		MainPanel_BorderLayout = new BorderLayout();
		MainPanel.setLayout(MainPanel_BorderLayout);
		MainPanel.add(TopMenuBar,"North");
		MainPanel.add(LeftFlowLayoutOUTPanel,"West");
		MainPanel.add(RightOperationPanel_ScrollPane,"Center");
		isRightOperationPanelUsed = true;
		//ҳ�����������ʼ��
		FramePanel = new JPanel();
		FramePanel_GridLayout = new GridLayout(1,1);
		FramePanel.setLayout(FramePanel_GridLayout);
		FramePanel.add(MainPanel);
		

		setContentPane(FramePanel);

		//�����չ������尴ť
		LeftSelectPanel_openPanelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				if(LeftSelectPanel_openPanelButtonCondition == LeftSelectPanel_openPanelButtonConditionEnum.CLOSE)
				{//��������״̬
					GridPanel.setPreferredSize(new Dimension(190,40));
					LeftFlowLayoutOUTPanel.setPreferredSize(new Dimension(180,200));
					LeftFlowLayoutPanel.setPreferredSize(new Dimension(180,200));
					LeftSelectPanel_openPanelButtonCondition = LeftSelectPanel_openPanelButtonConditionEnum.OPEN;
					LeftSelectPanel.setPreferredSize(new Dimension(160,250));
					MainPanel.repaint();
					MainPanel.revalidate();
				}
				else//�������ر�״̬
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
		
		///�����ҳ��ť
		LeftSelectPanel_ReturnRightOperationPanelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				ThisGuest = UserOperation.FindUserInformation(UserName);
				RightOperationPanel_UserNameLabel.setText("��ӭ������"+ThisGuest.getName()+"��");
				if(isSetPanel == true)//����������ť״̬
				{
					MainPanel.remove(aSetPanel);
					isSetPanel = false;
				}
				LeftSelectPanel_SetButton.setEnabled(true);
				if(isGuestGoodsSearchPanel == true)//����������ť״̬
				{
					MainPanel.remove(aGuestGoodsSearchPanel);
					isGuestGoodsSearchPanel = false;
				}
				if(isGuestInformationPanelUsed == true)//����������ť״̬
				{
					MainPanel.remove(gip);
					isGuestInformationPanelUsed = false;
				}
				if(isGuestPurchaseHistoryPanel == true)//����������ť״̬
				{
					MainPanel.remove(aGuestPurchaseHistoryPanel);
					isGuestPurchaseHistoryPanel = false;
				}
				MainPanel.repaint();
				MainPanel.add(RightOperationPanel_ScrollPane,"Center");
				isRightOperationPanelUsed = true;
				MainPanel.revalidate();
				GuestGUI.this.LeftSelectPanel_ConsumptionRecordButton.setEnabled(true);
				GuestGUI.this.LeftSelectPanel_GoodsInformationButton.setEnabled(true);//��Ʒ��Ϣ
				LeftSelectPanel_ReturnRightOperationPanelButton.setEnabled(false);//��ҳ
				GuestGUI.this.LeftSelectPanel_ConsumptionRecordButton.setEnabled(true);//���Ѽ�¼
				GuestGUI.this.LeftSelectPanel_GuestInformationButton.setEnabled(true);//������Ϣ
			}
		});
		
		////��� ���Ѽ�¼             ------------------------------------aGuestPurchaseHistoryPanel
		LeftSelectPanel_ConsumptionRecordButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				if(isSetPanel == true)//����������ť״̬
				{
					MainPanel.remove(aSetPanel);
					isSetPanel = false;
				}
				LeftSelectPanel_SetButton.setEnabled(true);
				if(isRightOperationPanelUsed == true)//����������ť״̬
				{
					MainPanel.remove(RightOperationPanel_ScrollPane);
					isRightOperationPanelUsed = false;
				}
				if(isGuestInformationPanelUsed == true)//����������ť״̬
				{
					MainPanel.remove(gip);
					isGuestInformationPanelUsed = false;
				}
				if(isGuestGoodsSearchPanel == true)//����������ť״̬
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
				GuestGUI.this.LeftSelectPanel_ConsumptionRecordButton.setEnabled(false);//���Ѽ�¼
				GuestGUI.this.LeftSelectPanel_GoodsInformationButton.setEnabled(true);//��Ʒ��Ϣ
				LeftSelectPanel_ReturnRightOperationPanelButton.setEnabled(true);//��ҳ
				GuestGUI.this.LeftSelectPanel_GuestInformationButton.setEnabled(true);//������Ϣ
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
				LeftSelectPanel_SetButton.setEnabled(true);
				if(isRightOperationPanelUsed == true)//����������ť״̬
				{
					MainPanel.remove(RightOperationPanel_ScrollPane);
					isRightOperationPanelUsed = false;
				}
				if(isGuestInformationPanelUsed == true)//����������ť״̬
				{
					MainPanel.remove(gip);
					isGuestInformationPanelUsed = false;
				}
				if(isGuestGoodsSearchPanel == true)//����������ť״̬
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
				GuestGUI.this.LeftSelectPanel_ConsumptionRecordButton.setEnabled(false);//���Ѽ�¼
				GuestGUI.this.LeftSelectPanel_GoodsInformationButton.setEnabled(true);//��Ʒ��Ϣ
				LeftSelectPanel_ReturnRightOperationPanelButton.setEnabled(true);//��ҳ
				GuestGUI.this.LeftSelectPanel_GuestInformationButton.setEnabled(true);//������Ϣ
			}
		});
		
		////������ ��Ʒ��Ϣ��ѯ��ť
		LeftSelectPanel_GoodsInformationButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				if(isSetPanel == true)//����������ť״̬
				{
					MainPanel.remove(aSetPanel);
					isSetPanel = false;
				}
				LeftSelectPanel_SetButton.setEnabled(true);
				//	private GuestGoodsSearchPanel aGuestGoodsSearchPanel;
				//  private boolean isGuestGoodsSearchPanel = false;
				if(isRightOperationPanelUsed == true)//����������ť״̬
				{
					MainPanel.remove(RightOperationPanel_ScrollPane);
					isRightOperationPanelUsed = false;
				}
				if(isGuestInformationPanelUsed == true)//����������ť״̬
				{
					MainPanel.remove(gip);
					isGuestInformationPanelUsed = false;
				}
				if(isGuestPurchaseHistoryPanel == true)//����������ť״̬
				{
					MainPanel.remove(aGuestPurchaseHistoryPanel);
					isGuestPurchaseHistoryPanel = false;
				}
				MainPanel.repaint();
				MainPanel.add(aGuestGoodsSearchPanel,"Center");
				isGuestGoodsSearchPanel = true;
				GuestGUI.this.setVisible(true);
				MainPanel.revalidate();
				GuestGUI.this.LeftSelectPanel_GoodsInformationButton.setEnabled(false);//��Ʒ��Ϣ
				LeftSelectPanel_ReturnRightOperationPanelButton.setEnabled(true);//��ҳ
				GuestGUI.this.LeftSelectPanel_ConsumptionRecordButton.setEnabled(true);//���Ѽ�¼
				GuestGUI.this.LeftSelectPanel_GuestInformationButton.setEnabled(true);//������Ϣ
				GuestGUI.this.LeftSelectPanel_ConsumptionRecordButton.setEnabled(true);

			}
		});
		////�Ҳ���� ��Ʒ��Ϣ��ѯ��ť
		RightOperation_SelectPanel_GoodsInformationButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				if(isSetPanel == true)//����������ť״̬
				{
					MainPanel.remove(aSetPanel);
					isSetPanel = false;
				}
				LeftSelectPanel_SetButton.setEnabled(true);
				//	private GuestGoodsSearchPanel aGuestGoodsSearchPanel;
				//  private boolean isGuestGoodsSearchPanel = false;
				if(isRightOperationPanelUsed == true)//����������ť״̬
				{
					MainPanel.remove(RightOperationPanel_ScrollPane);
					isRightOperationPanelUsed = false;
				}
				if(isGuestInformationPanelUsed == true)//����������ť״̬
				{
					MainPanel.remove(gip);
					isGuestInformationPanelUsed = false;
				}
				if(isGuestPurchaseHistoryPanel == true)//����������ť״̬
				{
					MainPanel.remove(aGuestPurchaseHistoryPanel);
					isGuestPurchaseHistoryPanel = false;
				}
				MainPanel.repaint();
				MainPanel.add(aGuestGoodsSearchPanel,"Center");
				isGuestGoodsSearchPanel = true;
				GuestGUI.this.setVisible(true);
				MainPanel.revalidate();
				GuestGUI.this.LeftSelectPanel_GoodsInformationButton.setEnabled(false);//��Ʒ��Ϣ
				LeftSelectPanel_ReturnRightOperationPanelButton.setEnabled(true);//��ҳ
				GuestGUI.this.LeftSelectPanel_ConsumptionRecordButton.setEnabled(true);//���Ѽ�¼
				GuestGUI.this.LeftSelectPanel_GuestInformationButton.setEnabled(true);//������Ϣ
				GuestGUI.this.LeftSelectPanel_ConsumptionRecordButton.setEnabled(true);

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
				LeftSelectPanel_SetButton.setEnabled(true);
				if(isGuestGoodsSearchPanel == true)//����������ť״̬
				{
					MainPanel.remove(aGuestGoodsSearchPanel);
					isGuestGoodsSearchPanel = false;
				}
				if(isRightOperationPanelUsed == true)//����������ť״̬
				{
					MainPanel.remove(RightOperationPanel_ScrollPane);
					isRightOperationPanelUsed = false;
				}
				if(isGuestPurchaseHistoryPanel == true)//����������ť״̬
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
				GuestGUI.this.LeftSelectPanel_GoodsInformationButton.setEnabled(true);//��Ʒ��Ϣ
				LeftSelectPanel_ReturnRightOperationPanelButton.setEnabled(true);//��ҳ
				GuestGUI.this.LeftSelectPanel_ConsumptionRecordButton.setEnabled(true);//���Ѽ�¼
				GuestGUI.this.LeftSelectPanel_GuestInformationButton.setEnabled(false);//������Ϣ
				GuestGUI.this.LeftSelectPanel_ConsumptionRecordButton.setEnabled(true);

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
				LeftSelectPanel_SetButton.setEnabled(true);
				if(isGuestGoodsSearchPanel == true)//����������ť״̬
				{
					MainPanel.remove(aGuestGoodsSearchPanel);
					isGuestGoodsSearchPanel = false;
				}
				if(isRightOperationPanelUsed == true)//����������ť״̬
				{
					MainPanel.remove(RightOperationPanel_ScrollPane);
					isRightOperationPanelUsed = false;
				}
				if(isGuestPurchaseHistoryPanel == true)//����������ť״̬
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
				GuestGUI.this.LeftSelectPanel_GoodsInformationButton.setEnabled(true);//��Ʒ��Ϣ
				LeftSelectPanel_ReturnRightOperationPanelButton.setEnabled(true);//��ҳ
				GuestGUI.this.LeftSelectPanel_ConsumptionRecordButton.setEnabled(true);//���Ѽ�¼
				GuestGUI.this.LeftSelectPanel_GuestInformationButton.setEnabled(false);//������Ϣ
				GuestGUI.this.LeftSelectPanel_ConsumptionRecordButton.setEnabled(true);

			}
		});
		
		LeftSelectPanel_SetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				if(isGuestGoodsSearchPanel == true)//����������ť״̬
				{
					MainPanel.remove(aGuestGoodsSearchPanel);
					isGuestGoodsSearchPanel = false;
				}
				if(isRightOperationPanelUsed == true)//����������ť״̬
				{
					MainPanel.remove(RightOperationPanel_ScrollPane);
					isRightOperationPanelUsed = false;
				}
				if(isGuestPurchaseHistoryPanel == true)//����������ť״̬
				{
					MainPanel.remove(aGuestPurchaseHistoryPanel);
					isGuestPurchaseHistoryPanel = false;
				}
				if(isGuestInformationPanelUsed == true)//����������ť״̬
				{
					MainPanel.remove(gip);
					isGuestInformationPanelUsed = false;
				}
				MainPanel.repaint();
				MainPanel.add(aSetPanel,"Center");
				isSetPanel = true;
				MainPanel.revalidate();
				GuestGUI.this.LeftSelectPanel_GoodsInformationButton.setEnabled(true);//��Ʒ��Ϣ
				LeftSelectPanel_ReturnRightOperationPanelButton.setEnabled(true);//��ҳ
				GuestGUI.this.LeftSelectPanel_ConsumptionRecordButton.setEnabled(true);//���Ѽ�¼
				GuestGUI.this.LeftSelectPanel_GuestInformationButton.setEnabled(true);//������Ϣ
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
