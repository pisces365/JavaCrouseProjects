
package GUI;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import java.awt.event.*;
import goods.*;
import people.Guest;
import people.UserOperation;

import java.util.*;


class GuestAddAndEditPanel extends JFrame
{
	private JLabel AddAndEditLabel;
	
	private InformationItemAdmin Name;//�����
	private InformationItemAdmin ID;//�����
	private InformationItemAdmin Sex;//�����
	private InformationItemAdmin PhoneNum;//�����
	private InformationItemAdmin Address;//�����
	private InformationItemAdmin PostCode;//�����
	private InformationItemAdmin UserName;//�����
	private JCheckBox UpdatePassword;//�����
	
	private JButton BasicInformationPanel_ConfirmButton;
	private JPanel BasicInformationPanel;
	private GridLayout BasicInformationPanel_GridLayout;
	private JPanel BasicInformationOUTPanel;
	
	public GuestAddAndEditPanel(AdminGUI gui,Guest gu)
	{		
		this.setSize(640, 500);
		int windowWidth = this.getWidth(); //��ô��ڿ�
		int windowHeight = this.getHeight(); //��ô��ڸ�
		Toolkit kit = Toolkit.getDefaultToolkit(); //���幤�߰�
		Dimension screenSize = kit.getScreenSize(); //��ȡ��Ļ�ĳߴ�
		int screenWidth = screenSize.width; //��ȡ��Ļ�Ŀ�
		int screenHeight = screenSize.height; //��ȡ��Ļ�ĸ�
		this.setLocation(screenWidth / 2 - windowWidth / 2, screenHeight / 2 - windowHeight / 2);//���ô��ھ�����ʾ
		
		AddAndEditLabel = new JLabel("�û���Ϣ����");
		AddAndEditLabel.setBorder(new EmptyBorder(10,0,10,0));
		AddAndEditLabel.setFont(AddAndEditLabel.getFont().deriveFont(14.0f));

		if(gu != null)//��������
		{
			this.setTitle("�û���Ϣ����");
			
			UserName = new InformationItemAdmin("�û�����",gu.getUserName(),false);
			Name = new InformationItemAdmin("������",gu.getName(),false);
			ID = new InformationItemAdmin("֤���ţ�",gu.getID(),false);
			Sex = new InformationItemAdmin("�Ա�",gu.getSex(),0);
			PhoneNum = new InformationItemAdmin("�绰��",gu.getPhoneNum());
			Address = new InformationItemAdmin("��ַ��",gu.getAddress());
			PostCode = new InformationItemAdmin("�ʱࣺ",gu.getPostCode());

			UpdatePassword = new JCheckBox("��������");
			UpdatePassword.setSelected(false);
		}
		else//�������
		{
			this.setTitle("�û���Ϣ���");
			UserName = new InformationItemAdmin("�û�����","");
			Name = new InformationItemAdmin("������","");
			ID = new InformationItemAdmin("֤���ţ�","");
			Sex = new InformationItemAdmin("�Ա�","",0);
			PhoneNum = new InformationItemAdmin("�绰��","");
			Address = new InformationItemAdmin("��ַ��","");
			PostCode = new InformationItemAdmin("�ʱࣺ","");

		}
		//��ť��ʼ��
		BasicInformationPanel_ConfirmButton = new JButton("ȷ��");
		BasicInformationPanel_ConfirmButton.setBorderPainted(false);
		BasicInformationPanel_ConfirmButton.setBackground(new Color(140,189,239));
		//����ʼ��
		BasicInformationPanel = new JPanel();
		BasicInformationPanel_GridLayout = new GridLayout(0,1,5,5);
		BasicInformationPanel.setLayout(BasicInformationPanel_GridLayout);
		BasicInformationPanel.add(AddAndEditLabel);
		BasicInformationPanel.add(UserName);
		BasicInformationPanel.add(Name);
		BasicInformationPanel.add(ID);
		BasicInformationPanel.add(Sex);
		BasicInformationPanel.add(PhoneNum);
		BasicInformationPanel.add(Address);
		BasicInformationPanel.add(PostCode);
		if(gu != null)
		{
			BasicInformationPanel.add(UpdatePassword);
		}
		BasicInformationPanel.add(BasicInformationPanel_ConfirmButton);
		
		BasicInformationOUTPanel = new JPanel();//����ʼ��
		BasicInformationOUTPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		BasicInformationOUTPanel.add(BasicInformationPanel);
		//����ʼ��
		this.setContentPane(BasicInformationOUTPanel);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setResizable(false);
		
		this.setVisible(true);
		
		BasicInformationPanel_ConfirmButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				synchronized (AdminUserInformationManagement.lock)
				{	if(UserName.getText().equals("")||Name.getText().equals("")||ID.getText().equals("")||PhoneNum.getText().equals("")||Address.getText().equals("")||PostCode.getText().equals(""))
					{
						JOptionPane.showMessageDialog(GuestAddAndEditPanel.this, "���ݲ���Ϊ�գ�");
					}
					else if(gu != null)//��������
					{
						if(UpdatePassword.isSelected())
						{
							gu.setPassWord("123456");//����Ĭ������
						}
						gu.SetAllElements(Name.getText(), ID.getText(), Sex.getSex(), PhoneNum.getText(), Address.getText(), PostCode.getText());
						UserOperation.UpdateUserInformation(gu);
						UserOperation.writeIn();
						JOptionPane.showMessageDialog(GuestAddAndEditPanel.this, "�û���Ϣ���³ɹ���");
						GuestAddAndEditPanel.this.setVisible(false);
						AdminUserInformationManagement.lock.notifyAll();
						//AddAndEditPanel.this.dispose();
					}
					else if(UserOperation.isUserInformationExistence(UserName.getText()) == true)
					{
						JOptionPane.showMessageDialog(GuestAddAndEditPanel.this, "�û����Ѵ��ڣ�");
					}
					else//�������
					{
						Guest gu = new Guest(Name.getText(),ID.getText(),Sex.getSex(),PhoneNum.getText(),Address.getText(),PostCode.getText(),UserName.getText(),"123456");
						UserOperation.addUserDetail(gu);//����Ĭ������
						UserOperation.writeIn();
						JOptionPane.showMessageDialog(GuestAddAndEditPanel.this, "�û���Ϣ��ӳɹ���");
						GuestAddAndEditPanel.this.setVisible(false);
						AdminUserInformationManagement.lock.notifyAll();
						//AddAndEditPanel.this.dispose();
					}
				}
			}
		});
	}
}

public class AdminUserInformationManagement extends JPanel{
	public static Object lock = new Object();
	
	private JPanel MainPanel,CenterPanel,CenterNorthPanel;//���
	private JScrollPane CenterScrollPane;//���
	private boolean isCenterScrollPaneused;
	
	private BorderLayout MainPanel_BorderLayout;//����
	private BorderLayout CenterPanel_BorderLayout;//����
	private FlowLayout CenterNorthPanel_FlowLayout;//����
	private GridLayout ThisPanel_GridLayout;//����
	
	private JLabel PageNameLabel;
	
	private JButton searchItemButton,DeleteButton,AddButton,EditButton;//��ť
	
	private JTextField searchItemTextField;
	
	private DefaultTableModel goodsInformation_TableModel;//��������Ϣ
	private JTable GuestInformation;//��������Ϣ
	private final Vector<String> TableNames;//��������Ϣ
	private Vector<Vector<String>> TableDatas;//��������Ϣ
	
	private JLabel nothingLabel;
	private boolean isNothingLabelused;
	
	private void showTableOrLabel() 
	{
		try
		{if(searchItemTextField.getText().equals("�������û�����������֤���Ż��ֻ���"))
		{
			if(isCenterScrollPaneused = true)
			{//������
				TableDatas = null;
				CenterPanel.remove(CenterScrollPane);
				isCenterScrollPaneused = false;
				isNothingLabelused = true;
				CenterPanel.repaint();
				CenterPanel.add(nothingLabel,"Center");
				CenterPanel.revalidate();
			}
		}
		else
		{
			if(isNothingLabelused == true)
			{
				CenterPanel.remove(nothingLabel);
				isNothingLabelused = false;
				isCenterScrollPaneused = true;
				CenterPanel.repaint();
				CenterPanel.add(CenterScrollPane,"Center");
				CenterPanel.revalidate();
			}
			TableDatas = UserOperation.FindGuests(searchItemTextField.getText());	
			if(TableDatas == null)
				JOptionPane.showMessageDialog(AdminUserInformationManagement.this, "�û���Ϣ�����ڣ�");
			//����Ƴ�
			goodsInformation_TableModel = (DefaultTableModel)GuestInformation.getModel();
			goodsInformation_TableModel.setDataVector(TableDatas,TableNames);
			GuestInformation.setRowHeight(30);
			GuestInformation.setRowMargin(5);
			GuestInformation.updateUI();
			DeleteButton.setEnabled(false);
			EditButton.setEnabled(false);
		}
		}
		catch(Exception ee) {}
	}
	
	public AdminUserInformationManagement(AdminGUI gui)
	{
		TableNames = new Vector<String>();
		//������֤���š��Ա��ֻ����롢��ϵ��ַ���ʱ�
		TableNames.add("�û���");
		TableNames.add("����");
		TableNames.add("֤����");
		TableNames.add("�Ա�");
		TableNames.add("�ֻ�����");
		TableNames.add("��ϵ��ַ");
		TableNames.add("�ʱ�");
		TableNames.add("���ʱ��");

		
		TableDatas = null;
		
		goodsInformation_TableModel = new DefaultTableModel(TableDatas,TableNames){
			public boolean isCellEditable(int row, int column)
			{
				return false;
			}
		};//������� ��ʼ��
		GuestInformation = new JTable(goodsInformation_TableModel);
		GuestInformation.setRowSelectionAllowed(true);
		GuestInformation.setSelectionBackground(new Color(140,189,239));
		GuestInformation.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		GuestInformation.setRowHeight(30);
		GuestInformation.setRowMargin(5);
		GuestInformation.setShowGrid(false);
		GuestInformation.setShowHorizontalLines(false);
		GuestInformation.setShowVerticalLines(false);
		GuestInformation.getTableHeader().setBackground(new Color(19,146,249));
		GuestInformation.doLayout();
		GuestInformation.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		GuestInformation.setFillsViewportHeight(true);
		GuestInformation.getTableHeader().setReorderingAllowed(false);//���ñ��ͷ�����϶�
		
		PageNameLabel = new JLabel("��Ա�ͻ���Ϣ����");
		PageNameLabel.setForeground(new Color(19,146,249));
		PageNameLabel.setFont(PageNameLabel.getFont().deriveFont(30.0f));

		searchItemTextField = new JTextField("�������û�����������֤���Ż��ֻ���",30);
		searchItemTextField.setBorder(BorderFactory.createLineBorder(new Color(140,189,239)));
		//��ť��ʼ��
		searchItemButton = new JButton("��ѯ");
		searchItemButton.setBorderPainted(false);
		searchItemButton.setBackground(new Color(140,189,239));
		//��ť��ʼ��
		AddButton = new JButton("���");
		AddButton.setBorderPainted(false);
		AddButton.setBackground(new Color(140,189,239));
		//��ť��ʼ��
		EditButton = new JButton("�༭");
		EditButton.setBorderPainted(false);
		EditButton.setBackground(new Color(140,189,239));
		EditButton.setEnabled(false);
		//��ť��ʼ��
		DeleteButton = new JButton("ɾ��");
		DeleteButton.setBorderPainted(false);
		DeleteButton.setBackground(new Color(140,189,239));
		DeleteButton.setEnabled(false);
		//����ʼ��
		CenterNorthPanel = new JPanel();
		CenterNorthPanel_FlowLayout = new FlowLayout();
		CenterNorthPanel.setLayout(CenterNorthPanel_FlowLayout);
		CenterNorthPanel.add(searchItemTextField);
		CenterNorthPanel.add(searchItemButton);
		CenterNorthPanel.add(AddButton);
		CenterNorthPanel.add(EditButton);
		CenterNorthPanel.add(DeleteButton);
		//����ʼ��
		CenterScrollPane = new JScrollPane(GuestInformation);
		CenterScrollPane.setBorder(new EmptyBorder(0,0,0,0));
		
		nothingLabel = new JLabel("����ʲô��û�С���",JLabel.CENTER);
		isNothingLabelused = true;
		isCenterScrollPaneused = false;
		//����ʼ��
		CenterPanel = new JPanel();
		CenterPanel_BorderLayout = new BorderLayout();
		CenterPanel.setLayout(CenterPanel_BorderLayout);
		CenterPanel.add(CenterNorthPanel,"North");
		CenterPanel.add(nothingLabel,"Center");
		//����ʼ��
		MainPanel = new JPanel();
		MainPanel_BorderLayout = new BorderLayout();
		MainPanel.setLayout(MainPanel_BorderLayout);
		MainPanel.add(PageNameLabel,"North");
		MainPanel.add(CenterPanel,"Center");
		MainPanel.setBorder(new EmptyBorder(14,14,0,0));
		//����ʼ��
		ThisPanel_GridLayout = new GridLayout(1,1,0,0);
		this.setLayout(ThisPanel_GridLayout);
		this.add(MainPanel);
		
		GuestInformation.addMouseListener(new MouseAdapter(){
            public void mouseReleased(MouseEvent e)
            {
            	if(GuestInformation.getSelectedRow() != -1)
            	{
            		AdminUserInformationManagement.this.DeleteButton.setEnabled(true);
            		AdminUserInformationManagement.this.EditButton.setEnabled(true);
            	}
            }
        });
		
		this.addMouseListener(new MouseAdapter(){
            public void mousePressed(MouseEvent e)
            {
            	AdminUserInformationManagement.this.DeleteButton.setEnabled(false);
            	AdminUserInformationManagement.this.EditButton.setEnabled(false);
            	GuestInformation.clearSelection();
            }
        });
		
		AddButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				GuestAddAndEditPanel aaep = new GuestAddAndEditPanel(gui,null);
			    Thread t = new Thread() 
			    {
			        

					public void run() //�ȴ�����������ڽ��б�����
			        {
			            synchronized(lock) 
			            {
			                while (aaep.isVisible())
			                {
			                	try 
			                	{
			                		lock.wait();
			                	} 
			                	catch (InterruptedException e) 
			                	{
			                		e.printStackTrace();
			                	}
			                }
			                aaep.dispose();
			                showTableOrLabel();
			            }
			        }
			    };
			    t.start();
			}
		});
		
		EditButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				GuestAddAndEditPanel aaep = new GuestAddAndEditPanel(gui,UserOperation.FindUserInformation(GuestInformation.getValueAt(GuestInformation.getSelectedRow(), 0).toString()));
			    Thread t = new Thread() 
			    {
			        public void run() 
			        {
			            synchronized(lock) //�ȴ��������ڽ��б�����
			            {
			                while (aaep.isVisible())
			                {
			                	try 
			                	{
			                		lock.wait();
			                	} 
			                	catch (InterruptedException e) 
			                	{
			                		e.printStackTrace();
			                	}
			                }
			                aaep.dispose();
			                showTableOrLabel(); 
			            }
			        }
			    };
			    t.start();
			}
		});
		//AddAndEditPanel
		
		DeleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				try
				{
				UserOperation.DeleteUserInformation(GuestInformation.getValueAt(GuestInformation.getSelectedRow(), 0).toString());
				UserOperation.writeIn();
				JOptionPane.showMessageDialog(AdminUserInformationManagement.this, "�û���Ϣ��ɾ����");
				if(searchItemTextField.getText().equals("�������û�����������֤���Ż��ֻ���"))
				{
					if(isCenterScrollPaneused = true)//�޿�ɾ����
					{
						TableDatas = null;
						CenterPanel.remove(CenterScrollPane);
						isCenterScrollPaneused = false;
						isNothingLabelused = true;
						CenterPanel.repaint();
						CenterPanel.add(nothingLabel,"Center");
						CenterPanel.revalidate();
					}
				}
				else
				{
					if(isNothingLabelused == true)//����ɾ��
					{
						CenterPanel.remove(nothingLabel);
						isNothingLabelused = false;
						isCenterScrollPaneused = true;
						CenterPanel.repaint();
						CenterPanel.add(CenterScrollPane,"Center");
						CenterPanel.revalidate();
					}
					TableDatas = UserOperation.FindGuests(searchItemTextField.getText());
					goodsInformation_TableModel = (DefaultTableModel)GuestInformation.getModel();
					goodsInformation_TableModel.setDataVector(TableDatas,TableNames);
					GuestInformation.setRowHeight(30);//����������
					GuestInformation.setRowMargin(5);
					GuestInformation.updateUI();
					DeleteButton.setEnabled(false);
					EditButton.setEnabled(false);
				}
				}
				catch(Exception ex) {}
			}
		});
		
		searchItemButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				showTableOrLabel(); 
				DeleteButton.setEnabled(false);
				EditButton.setEnabled(false);
			}
		});
		
		searchItemTextField.addFocusListener(new FocusAdapter() {
			public void focusLost(FocusEvent e)
			{
				if(searchItemTextField.getText().equals(""))
					searchItemTextField.setText("�������û�����������֤���Ż��ֻ���");
			}
			
			public void focusGained(FocusEvent e)
			{
				if(searchItemTextField.getText().equals("�������û�����������֤���Ż��ֻ���"))
					searchItemTextField.setText("");
			}
		});
	}
}

