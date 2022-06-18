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
	private JLabel PageNameLabel,BasicVIPLabel,BasicRegisterLabel,PassWordUpdateLabel;//ҳ��������
	
	private JButton BasicInformationButton,PasswordManageButton,
		BasicInformationPanel_ConfirmButton,PasswordManagePanel_ConfirmButton;
	
	private InformationItem Name,ID,PhoneNum,Address,PostCode,BecomeVIPtime;
	public InformationItem OldPassWord,NewPassWord,ConfirmNewPassWord;

	private InformationItemAdmin Sex;
	
	private JScrollPane BasicInformationScrollPane,PasswordManageScrollPane;
	
	private JPanel GuestInformationPanel,GuestInformationPanelNorth,BasicInformationPanel,PasswordManagePanel,
		BasicInformationOUTPanel,PasswordManageOUTPanel;
	
	private BorderLayout ThisPanel_BorderLayout;//��ҳ��ı߽粼��
	private BorderLayout GuestInformationPanel_BorderLayout;//��ҳ��ı߽粼��
	private BoxLayout GuestInformationPanelNorth_BoxLayout;
	private GridLayout BasicInformationPanel_GridLayout;
	private GridLayout PasswordManagePanel_GridLayout;
	
	public GuestInformationPanel(Guest ThisGuest)
	{
		//��ҳ�� ҳ��������
		PageNameLabel = new JLabel("������Ϣ����");
		PageNameLabel.setBorder(new EmptyBorder(0,0,10,0));
		PageNameLabel.setForeground(new Color(19,146,249));
		PageNameLabel.setFont(PageNameLabel.getFont().deriveFont(30.0f));
		
		
		//�ͻ���Ϣ��� ������ť
		BasicInformationButton = new JButton("������Ϣ");
		BasicInformationButton.setBorderPainted(false);
		BasicInformationButton.setBackground(new Color(140,189,239));
		BasicInformationButton.setFont(PageNameLabel.getFont().deriveFont(16.0f));
		
		PasswordManageButton = new JButton("�������");
		PasswordManageButton.setBorderPainted(false);
		PasswordManageButton.setBackground(new Color(19,146,249));
		PasswordManageButton.setFont(PageNameLabel.getFont().deriveFont(16.0f));
		
		GuestInformationPanelNorth = new JPanel();
		GuestInformationPanelNorth.setBorder(new EmptyBorder(10,0,10,0));
		GuestInformationPanelNorth_BoxLayout = new BoxLayout(GuestInformationPanelNorth,BoxLayout.X_AXIS);
		GuestInformationPanelNorth.setLayout(GuestInformationPanelNorth_BoxLayout);
		GuestInformationPanelNorth.add(BasicInformationButton);
		GuestInformationPanelNorth.add(PasswordManageButton);
		
		
		/////������Ϣ��弰�����
		BasicVIPLabel = new JLabel("��Ա��Ϣ");
		BasicVIPLabel.setBorder(new EmptyBorder(10,0,10,0));
		BasicVIPLabel.setFont(PageNameLabel.getFont().deriveFont(14.0f));

		Name = new InformationItem("������",ThisGuest.getName());
		Sex = new InformationItemAdmin("�Ա�",ThisGuest.getSex(),0);
		PhoneNum = new InformationItem("�绰���룺",ThisGuest.getPhoneNum());
		Address = new InformationItem("��ַ��",ThisGuest.getAddress());
		PostCode = new InformationItem("�ʱࣺ",ThisGuest.getPostCode());
		/////������Ϣ��弰�����
		BasicRegisterLabel = new JLabel("ע����Ϣ");
		BasicRegisterLabel.setBorder(new EmptyBorder(10,0,10,0));
		BasicRegisterLabel.setFont(PageNameLabel.getFont().deriveFont(14.0f));
		/////������Ϣ��弰�����
		ID = new InformationItem("�û�ID��",ThisGuest.getID(),false);
		BecomeVIPtime = new InformationItem("��Աע��ʱ�䣺",ThisGuest.getBecomeVIPtime(),false);
		/////������Ϣ��弰�����
		BasicInformationPanel_ConfirmButton = new JButton("ȷ��");
		BasicInformationPanel_ConfirmButton.setBorderPainted(false);
		BasicInformationPanel_ConfirmButton.setBackground(new Color(140,189,239));
		/////������Ϣ��弰�����
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
		/////������Ϣ��弰�����
		BasicInformationOUTPanel = new JPanel();
		BasicInformationOUTPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		BasicInformationOUTPanel.add(BasicInformationPanel);
		
		BasicInformationScrollPane = new JScrollPane(BasicInformationOUTPanel);
		BasicInformationScrollPane.setBorder(new EmptyBorder(0,0,0,0));
		
		
		/////�����޸���弰�����
		PassWordUpdateLabel = new JLabel("�������");
		PassWordUpdateLabel.setBorder(new EmptyBorder(10,0,10,0));
		PassWordUpdateLabel.setFont(PageNameLabel.getFont().deriveFont(14.0f));
		
		OldPassWord = new InformationItem("��ǰ���룺");
		NewPassWord = new InformationItem("�����룺");
		ConfirmNewPassWord = new InformationItem("ȷ�������룺");
	    /////�����޸���弰�����
		PasswordManagePanel_ConfirmButton = new JButton("ȷ��");
		PasswordManagePanel_ConfirmButton.setBorderPainted(false);
		PasswordManagePanel_ConfirmButton.setBackground(new Color(140,189,239));
		/////�����޸���弰�����
		PasswordManagePanel = new JPanel();
		PasswordManagePanel_GridLayout = new GridLayout(0,1,5,5);
		PasswordManagePanel.setLayout(PasswordManagePanel_GridLayout);
		PasswordManagePanel.add(PassWordUpdateLabel);
		PasswordManagePanel.add(OldPassWord);
		PasswordManagePanel.add(NewPassWord);
		PasswordManagePanel.add(ConfirmNewPassWord);
		PasswordManagePanel.add(PasswordManagePanel_ConfirmButton);
		/////�����޸���弰�����
		PasswordManageOUTPanel = new JPanel();
		PasswordManageOUTPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		PasswordManageOUTPanel.add(PasswordManagePanel);
		/////�����޸���弰�����	
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
				GuestInformationPanel.this.GuestInformationPanel.remove(PasswordManageScrollPane);
				GuestInformationPanel.this.GuestInformationPanel.repaint();
				GuestInformationPanel.this.GuestInformationPanel.add(BasicInformationScrollPane,"Center");
				GuestInformationPanel.this.GuestInformationPanel.revalidate();
			}
		});
		
		
		/////����������ť�����¼�
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
		
		
		/////���������Ϣȷ�ϰ�ť�����¼�
		BasicInformationPanel_ConfirmButton.addActionListener(new ActionListener( ) {
			public void actionPerformed(ActionEvent e)
			{
				JOptionPane.showMessageDialog(GuestInformationPanel.this, "������Ϣ�޸ĳɹ���");
				ThisGuest.SetAllElements(Name.getText(), ID.getText(), Sex.getSex(), PhoneNum.getText(), Address.getText(), PostCode.getText());
				UserOperation.UpdateUserInformation(ThisGuest);
				UserOperation.writeIn();	
			}
		});
		
		
		/////����������ȷ�ϰ�ť�����¼�
		PasswordManagePanel_ConfirmButton.addActionListener(new ActionListener( ) {
			public void actionPerformed(ActionEvent e)
			{
				if(OldPassWord.getPassword() == null || NewPassWord.getPassword() == null || ConfirmNewPassWord.getPassword() == null)
				{
					JOptionPane.showMessageDialog(GuestInformationPanel.this, "���벻��Ϊ��");
					return;
				}
				if(OldPassWord.getPassword().equals(ThisGuest.getPassWord()) && NewPassWord.getPassword() != null && ConfirmNewPassWord.getPassword() != null && NewPassWord.getPassword().equals(ConfirmNewPassWord.getPassword()))
				{
					ThisGuest.setPassWord(NewPassWord.getPassword());
					UserOperation.UpdateUserInformation(ThisGuest);
					UserOperation.writeIn();
					JOptionPane.showMessageDialog(GuestInformationPanel.this, "�����޸ĳɹ���");
					OldPassWord.setPassword("");
					NewPassWord.setPassword("");
					ConfirmNewPassWord.setPassword("");
				}
				else
				{
					JOptionPane.showMessageDialog(GuestInformationPanel.this, "��ǰ���벻��ȷ�������벻һ�£�");
				}
			}
		});
	}
}
