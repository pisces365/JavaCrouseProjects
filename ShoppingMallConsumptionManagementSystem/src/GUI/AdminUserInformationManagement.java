
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
	
	private InformationItemAdmin Name;//组件组
	private InformationItemAdmin ID;//组件组
	private InformationItemAdmin Sex;//组件组
	private InformationItemAdmin PhoneNum;//组件组
	private InformationItemAdmin Address;//组件组
	private InformationItemAdmin PostCode;//组件组
	private InformationItemAdmin UserName;//组件组
	private JCheckBox UpdatePassword;//组件组
	
	private JButton BasicInformationPanel_ConfirmButton;
	private JPanel BasicInformationPanel;
	private GridLayout BasicInformationPanel_GridLayout;
	private JPanel BasicInformationOUTPanel;
	
	public GuestAddAndEditPanel(AdminGUI gui,Guest gu)
	{		
		this.setSize(640, 500);
		int windowWidth = this.getWidth(); //获得窗口宽
		int windowHeight = this.getHeight(); //获得窗口高
		Toolkit kit = Toolkit.getDefaultToolkit(); //定义工具包
		Dimension screenSize = kit.getScreenSize(); //获取屏幕的尺寸
		int screenWidth = screenSize.width; //获取屏幕的宽
		int screenHeight = screenSize.height; //获取屏幕的高
		this.setLocation(screenWidth / 2 - windowWidth / 2, screenHeight / 2 - windowHeight / 2);//设置窗口居中显示
		
		AddAndEditLabel = new JLabel("用户信息款项");
		AddAndEditLabel.setBorder(new EmptyBorder(10,0,10,0));
		AddAndEditLabel.setFont(AddAndEditLabel.getFont().deriveFont(14.0f));

		if(gu != null)//更新数据
		{
			this.setTitle("用户信息更新");
			
			UserName = new InformationItemAdmin("用户名：",gu.getUserName(),false);
			Name = new InformationItemAdmin("姓名：",gu.getName(),false);
			ID = new InformationItemAdmin("证件号：",gu.getID(),false);
			Sex = new InformationItemAdmin("性别：",gu.getSex(),0);
			PhoneNum = new InformationItemAdmin("电话：",gu.getPhoneNum());
			Address = new InformationItemAdmin("地址：",gu.getAddress());
			PostCode = new InformationItemAdmin("邮编：",gu.getPostCode());

			UpdatePassword = new JCheckBox("重置密码");
			UpdatePassword.setSelected(false);
		}
		else//添加数据
		{
			this.setTitle("用户信息添加");
			UserName = new InformationItemAdmin("用户名：","");
			Name = new InformationItemAdmin("姓名：","");
			ID = new InformationItemAdmin("证件号：","");
			Sex = new InformationItemAdmin("性别：","",0);
			PhoneNum = new InformationItemAdmin("电话：","");
			Address = new InformationItemAdmin("地址：","");
			PostCode = new InformationItemAdmin("邮编：","");

		}
		//按钮初始化
		BasicInformationPanel_ConfirmButton = new JButton("确定");
		BasicInformationPanel_ConfirmButton.setBorderPainted(false);
		BasicInformationPanel_ConfirmButton.setBackground(new Color(140,189,239));
		//面板初始化
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
		
		BasicInformationOUTPanel = new JPanel();//面板初始化
		BasicInformationOUTPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		BasicInformationOUTPanel.add(BasicInformationPanel);
		//面板初始化
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
						JOptionPane.showMessageDialog(GuestAddAndEditPanel.this, "数据不可为空！");
					}
					else if(gu != null)//更新数据
					{
						if(UpdatePassword.isSelected())
						{
							gu.setPassWord("123456");//设置默认密码
						}
						gu.SetAllElements(Name.getText(), ID.getText(), Sex.getSex(), PhoneNum.getText(), Address.getText(), PostCode.getText());
						UserOperation.UpdateUserInformation(gu);
						UserOperation.writeIn();
						JOptionPane.showMessageDialog(GuestAddAndEditPanel.this, "用户信息更新成功！");
						GuestAddAndEditPanel.this.setVisible(false);
						AdminUserInformationManagement.lock.notifyAll();
						//AddAndEditPanel.this.dispose();
					}
					else if(UserOperation.isUserInformationExistence(UserName.getText()) == true)
					{
						JOptionPane.showMessageDialog(GuestAddAndEditPanel.this, "用户名已存在！");
					}
					else//添加数据
					{
						Guest gu = new Guest(Name.getText(),ID.getText(),Sex.getSex(),PhoneNum.getText(),Address.getText(),PostCode.getText(),UserName.getText(),"123456");
						UserOperation.addUserDetail(gu);//设置默认密码
						UserOperation.writeIn();
						JOptionPane.showMessageDialog(GuestAddAndEditPanel.this, "用户信息添加成功！");
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
	
	private JPanel MainPanel,CenterPanel,CenterNorthPanel;//面板
	private JScrollPane CenterScrollPane;//面板
	private boolean isCenterScrollPaneused;
	
	private BorderLayout MainPanel_BorderLayout;//布局
	private BorderLayout CenterPanel_BorderLayout;//布局
	private FlowLayout CenterNorthPanel_FlowLayout;//布局
	private GridLayout ThisPanel_GridLayout;//布局
	
	private JLabel PageNameLabel;
	
	private JButton searchItemButton,DeleteButton,AddButton,EditButton;//按钮
	
	private JTextField searchItemTextField;
	
	private DefaultTableModel goodsInformation_TableModel;//表单数据信息
	private JTable GuestInformation;//表单数据信息
	private final Vector<String> TableNames;//表单数据信息
	private Vector<Vector<String>> TableDatas;//表单数据信息
	
	private JLabel nothingLabel;
	private boolean isNothingLabelused;
	
	private void showTableOrLabel() 
	{
		try
		{if(searchItemTextField.getText().equals("请输入用户名、姓名、证件号或手机号"))
		{
			if(isCenterScrollPaneused = true)
			{//面板添加
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
				JOptionPane.showMessageDialog(AdminUserInformationManagement.this, "用户信息不存在！");
			//面板移除
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
		//姓名、证件号、性别、手机号码、联系地址、邮编
		TableNames.add("用户名");
		TableNames.add("姓名");
		TableNames.add("证件号");
		TableNames.add("性别");
		TableNames.add("手机号码");
		TableNames.add("联系地址");
		TableNames.add("邮编");
		TableNames.add("入会时间");

		
		TableDatas = null;
		
		goodsInformation_TableModel = new DefaultTableModel(TableDatas,TableNames){
			public boolean isCellEditable(int row, int column)
			{
				return false;
			}
		};//表格设置 初始化
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
		GuestInformation.getTableHeader().setReorderingAllowed(false);//设置表格头不可拖动
		
		PageNameLabel = new JLabel("会员客户信息管理");
		PageNameLabel.setForeground(new Color(19,146,249));
		PageNameLabel.setFont(PageNameLabel.getFont().deriveFont(30.0f));

		searchItemTextField = new JTextField("请输入用户名、姓名、证件号或手机号",30);
		searchItemTextField.setBorder(BorderFactory.createLineBorder(new Color(140,189,239)));
		//按钮初始化
		searchItemButton = new JButton("查询");
		searchItemButton.setBorderPainted(false);
		searchItemButton.setBackground(new Color(140,189,239));
		//按钮初始化
		AddButton = new JButton("添加");
		AddButton.setBorderPainted(false);
		AddButton.setBackground(new Color(140,189,239));
		//按钮初始化
		EditButton = new JButton("编辑");
		EditButton.setBorderPainted(false);
		EditButton.setBackground(new Color(140,189,239));
		EditButton.setEnabled(false);
		//按钮初始化
		DeleteButton = new JButton("删除");
		DeleteButton.setBorderPainted(false);
		DeleteButton.setBackground(new Color(140,189,239));
		DeleteButton.setEnabled(false);
		//面板初始化
		CenterNorthPanel = new JPanel();
		CenterNorthPanel_FlowLayout = new FlowLayout();
		CenterNorthPanel.setLayout(CenterNorthPanel_FlowLayout);
		CenterNorthPanel.add(searchItemTextField);
		CenterNorthPanel.add(searchItemButton);
		CenterNorthPanel.add(AddButton);
		CenterNorthPanel.add(EditButton);
		CenterNorthPanel.add(DeleteButton);
		//面板初始化
		CenterScrollPane = new JScrollPane(GuestInformation);
		CenterScrollPane.setBorder(new EmptyBorder(0,0,0,0));
		
		nothingLabel = new JLabel("这里什么都没有……",JLabel.CENTER);
		isNothingLabelused = true;
		isCenterScrollPaneused = false;
		//面板初始化
		CenterPanel = new JPanel();
		CenterPanel_BorderLayout = new BorderLayout();
		CenterPanel.setLayout(CenterPanel_BorderLayout);
		CenterPanel.add(CenterNorthPanel,"North");
		CenterPanel.add(nothingLabel,"Center");
		//面板初始化
		MainPanel = new JPanel();
		MainPanel_BorderLayout = new BorderLayout();
		MainPanel.setLayout(MainPanel_BorderLayout);
		MainPanel.add(PageNameLabel,"North");
		MainPanel.add(CenterPanel,"Center");
		MainPanel.setBorder(new EmptyBorder(14,14,0,0));
		//面板初始化
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
			        

					public void run() //等待面板完成添加在进行表单更新
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
			            synchronized(lock) //等待面板完成在进行表单更新
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
				JOptionPane.showMessageDialog(AdminUserInformationManagement.this, "用户信息已删除！");
				if(searchItemTextField.getText().equals("请输入用户名、姓名、证件号或手机号"))
				{
					if(isCenterScrollPaneused = true)//无可删除项
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
					if(isNothingLabelused == true)//进行删除
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
					GuestInformation.setRowHeight(30);//表单更新设置
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
					searchItemTextField.setText("请输入用户名、姓名、证件号或手机号");
			}
			
			public void focusGained(FocusEvent e)
			{
				if(searchItemTextField.getText().equals("请输入用户名、姓名、证件号或手机号"))
					searchItemTextField.setText("");
			}
		});
	}
}

