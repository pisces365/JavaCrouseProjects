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
	
	private BorderLayout ThisPanel_BorderLayout;//��ҳ��ı߽粼��
	private BorderLayout GuestInformationPanel_BorderLayout;//��ҳ��ı߽粼��
	private BoxLayout GuestInformationPanelNorth_BoxLayout;
	
	String a1 = "��ѯ�����������������û�����������֤���Ż��ֻ��ŷ����ѯ��ϵͳ�����������йؼ����г���Ӧ�û�����Ϣ���ݡ�";
	String a2 = "��ӣ������Ӱ�ť��ϵͳ�����û���Ϣ�����壬�ڶ�Ӧ�ı��������½��û���Ϣ���ɡ����û����ظ�ʱ��ϵͳ����ʾ�û����Ѵ��ڡ�";
	String a3 = "�༭����ѡ��ĳһ�û���Ϣʱ���ɶ��û���Ϣ���б༭��ϵͳ�����û���Ϣ�༭��壬�ɶԷǹؼ���Ϣ�����޸ġ�";
	String a4 = "ɾ������ѡ��ĳһ�û���Ϣʱ�����ɾ����ť�������ɾ��������";
	String GuestInfo = "<html><body>"+a1+"<br>"+a2+"<br>"+a3+"<br>"+a4+"<body></html>"; 
	
	String b1 = "��ѯ�����û������������������û����ɽ��ж��û��������Ѽ�¼�Ĳ�ѯ������Ʒ���ƻ���������������м���ɽ�һ�������ض����Ѽ�¼��";
	String b2 = "��ӣ�����Ա�����������û���������¿ɽ������Ѽ�¼��ӣ�ϵͳ�������ӿͻ����Ѽ�¼��壬����Աѡ����Ʒ���ƺ���������Զ����䡣";
	String b3 = "ɾ��������Ա������ѡ��ĳһ���Ѽ�¼ʱ�����ɾ����ť�������ɾ��������";
	String consume = "<html><body>"+b1+"<br>"+b2+"<br>"+b3+"<body></html>";
	
	String c1 = "��ѯ������������������Ʒ���ƻ��ŷ����ѯ��ϵͳ�����������йؼ����г���Ӧ����Ʒ��Ϣ���ݡ�";
	String cc = "������Ʒ����ͨ�û������ɸ�����Ʒ�б�ѡ��ϲ����Ʒ���й���ϵͳ������Ʒ������壬ѡ����Ʒ���ƺ���������Զ����䡣";
	String c2 = "��ӣ�����Ա���������Ӱ�ť��ϵͳ������Ʒ��Ϣ�����壬�ڶ�Ӧ�ı��������½���Ʒ��Ϣ���ɡ�����Ʒ���ƻ����ظ�ʱ��ϵͳ����ʾ��Ʒ���ƻ����Ѵ��ڡ�";
	String c3 = "�༭������Ա������ѡ��ĳһ��Ʒʱ������༭��ť��ϵͳ������Ʒ��Ϣ������壬�ɶԷǹؼ���Ϣ�����޸ġ�";
	String c4 = "ɾ��������Ա������ѡ��ĳһ��Ʒ��Ϣʱ�����ɾ����ť�������ɾ��������";
	String goodsInfo = "<html><body>"+c1+"<br>"+cc+"<br>"+c2+"<br>"+c3+"<br>"+c4+"<body></html>";
	
	String d1 = "������Ϣ���ڶ�Ӧ���ı������룬�ɶԸ�����Ϣ�����޸ĺͱ��档";
	String d2 = "��������ڶ�Ӧ����������룬�ɶ����������֤���޸ĺͱ��档";
	String personal = "<html><body>"+d1+"<br>"+d2+"<body></html>";
	
	String e1 = "�̳�VIP���ѹ���ϵͳ";
	String e2 = "�汾��1.1.0.0";
	String system = "<html><body>"+e1+"<br>"+e2+"<body></html>";
	
	public SetPanel()
	{
		//��ҳ�� ҳ��������
		PageNameLabel = new JLabel("ϵͳ");
		PageNameLabel.setBorder(new EmptyBorder(0,0,10,0));
		PageNameLabel.setForeground(new Color(19,146,249));
		PageNameLabel.setFont(PageNameLabel.getFont().deriveFont(30.0f));
		
		//�ͻ���Ϣ��� ������ť
		BasicInformationButton = new JButton("����");
		BasicInformationButton.setBorderPainted(false);
		BasicInformationButton.setBackground(new Color(140,189,239));
		BasicInformationButton.setFont(PageNameLabel.getFont().deriveFont(16.0f));
		
		PasswordManageButton = new JButton("ϵͳ��Ϣ");
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
		/////������Ϣ��弰�����
		GuestInfoLabel = new JLabel("��Ա�ͻ���Ϣ��������Ա��");
		GuestInfoLabel.setBorder(new EmptyBorder(10,0,10,0));
		GuestInfoLabel.setFont(GuestInfoLabel.getFont().deriveFont(14.0f));
		//��ǩ��ʼ��
		GuestInfoTextLabel = new JLabel(GuestInfo);
		GuestInfoTextLabel.setBorder(new EmptyBorder(0,10,0,0));
		//��ǩ��ʼ��
		ConsumeLabel = new JLabel("�ͻ����Ѽ�¼��������Ա��/���Ѽ�¼��ѯ����ͨ�û���");
		ConsumeLabel.setBorder(new EmptyBorder(50,0,10,0));
		ConsumeLabel.setFont(ConsumeLabel.getFont().deriveFont(14.0f));
		//��ǩ��ʼ��
		ConsumeTextLabel = new JLabel(consume);
		ConsumeTextLabel.setBorder(new EmptyBorder(0,10,0,0));
		//��ǩ��ʼ��
		GoodsLabel = new JLabel("�����Ʒ��Ϣ��������Ա��/��Ʒ��Ϣ��ѯ����ͨ�û���");
		GoodsLabel.setBorder(new EmptyBorder(50,0,10,0));
		GoodsLabel.setFont(GoodsLabel.getFont().deriveFont(14.0f));
		//��ǩ��ʼ��
		GoodsTextLabel = new JLabel(goodsInfo);
		GoodsTextLabel.setBorder(new EmptyBorder(0,10,0,0));
		//��ǩ��ʼ��
		PersonalLabel = new JLabel("������Ϣ����");
		PersonalLabel.setBorder(new EmptyBorder(50,0,10,0));
		PersonalLabel.setFont(PersonalLabel.getFont().deriveFont(14.0f));
		//��ǩ��ʼ��
		PersonalTextLabel = new JLabel(personal);
		PersonalTextLabel.setBorder(new EmptyBorder(0,10,0,0));
		//��ǩ��ʼ��
		BasicInformationPanel = new JPanel();
		BasicInformationPanel.setLayout(new BoxLayout(BasicInformationPanel,BoxLayout.Y_AXIS));
		//��ǩ��ʼ��
		BasicInformationPanel.add(GuestInfoLabel);
		BasicInformationPanel.add(GuestInfoTextLabel);
		BasicInformationPanel.add(ConsumeLabel);
		BasicInformationPanel.add(ConsumeTextLabel);
		BasicInformationPanel.add(GoodsLabel);
		BasicInformationPanel.add(GoodsTextLabel);
		BasicInformationPanel.add(PersonalLabel);
		BasicInformationPanel.add(PersonalTextLabel);
		//��ǩ��ʼ��
		BasicInformationOUTPanel = new JPanel();
		BasicInformationOUTPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		BasicInformationOUTPanel.add(BasicInformationPanel);
		
		BasicInformationScrollPane = new JScrollPane(BasicInformationOUTPanel);
		BasicInformationScrollPane.setBorder(new EmptyBorder(0,0,0,0));
		
		
		/////�����޸���弰�����
		AboutLabel = new JLabel("����");
		AboutLabel.setBorder(new EmptyBorder(10,0,10,0));
		AboutLabel.setFont(AboutLabel.getFont().deriveFont(14.0f));
		//��ǩ��ʼ��
		AboutTextLabel = new JLabel(system);    
		AboutTextLabel.setBorder(new EmptyBorder(0,10,0,0));
		//���
		PasswordManagePanel = new JPanel();
		PasswordManagePanel.setLayout(new BoxLayout(PasswordManagePanel,BoxLayout.Y_AXIS));
		PasswordManagePanel.add(AboutLabel);
		PasswordManagePanel.add(AboutTextLabel);
		//���
		PasswordManageOUTPanel = new JPanel();
		PasswordManageOUTPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		PasswordManageOUTPanel.add(PasswordManagePanel);
		//���
		PasswordManageScrollPane = new JScrollPane(PasswordManageOUTPanel);
		PasswordManageScrollPane.setBorder(new EmptyBorder(0,0,0,0));
		JScrollBar bar = PasswordManageScrollPane.getVerticalScrollBar();
		bar.setForeground(Color.black);
		
		/////������Ϣ����������ҳ��
		GuestInformationPanel = new JPanel();
		GuestInformationPanel_BorderLayout = new BorderLayout();
		GuestInformationPanel.setLayout(GuestInformationPanel_BorderLayout);
		GuestInformationPanel.add(GuestInformationPanelNorth,"North");
		GuestInformationPanel.add(BasicInformationScrollPane,"Center");
		
		
		/////�����
		ThisPanel_BorderLayout = new BorderLayout();
		this.setLayout(ThisPanel_BorderLayout);
		this.add(PageNameLabel,"North");
		this.add(GuestInformationPanel,"Center");
		this.setBorder(new EmptyBorder(14,14,0,0));
		
		/////���������Ϣ��ť�����¼�
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
		
		
		/////����������ť�����¼�
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

