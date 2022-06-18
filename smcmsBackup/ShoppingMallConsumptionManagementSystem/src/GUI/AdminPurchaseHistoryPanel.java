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


public class AdminPurchaseHistoryPanel extends JPanel{
	public static Object add = new Object();
	
	private Guest gu;
	
	private JPanel MainPanel,CenterPanel,CenterNorthPanel;
	private JScrollPane CenterScrollPane;//滚动面板
	private boolean isCenterScrollPaneused;
	
	private BorderLayout MainPanel_BorderLayout;//边界布局
	private BorderLayout CenterPanel_BorderLayout;//边界布局
	private FlowLayout CenterNorthPanel_FlowLayout;//流式布局
	private GridLayout ThisPanel_GridLayout;//网格布局
	
	private JLabel PageNameLabel;//页面标签
	
	private JButton searchItemButton,DeleteButton,AddButton;//功能按钮
	
	private JTextField searchGuestNameTextField,searchItemTextField;//搜索框
	private JLabel nothingLabel;//标签
	private boolean isNothingLabelused;
	
	private DefaultTableModel goodsInformation_TableModel;//表单默认值
	private JTable goodsInformation;
	private final Vector<String> TableNames;//表头
	private Vector<Vector<String>> TableDatas;//表格
	
	public void TabelCreat(Guest ThisGuest)
	{
		if(ThisGuest != null)//表格初始化
			TableDatas = ThisGuest.getAllGoods();
		else
			TableDatas = null;
		
		goodsInformation_TableModel = new DefaultTableModel(TableDatas,TableNames){
			public boolean isCellEditable(int row, int column)
			{
				return false;//设置表格不可编辑
			}
		};
		goodsInformation = new JTable(goodsInformation_TableModel);
		goodsInformation.setRowSelectionAllowed(true);
		goodsInformation.setSelectionBackground(new Color(140,189,239));
		goodsInformation.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		goodsInformation.setRowHeight(30);
		goodsInformation.setRowMargin(5);
		goodsInformation.setShowGrid(false);
		goodsInformation.setShowHorizontalLines(false);
		goodsInformation.setShowVerticalLines(false);
		goodsInformation.getTableHeader().setBackground(new Color(19,146,249));//表头颜色
		goodsInformation.doLayout();
		goodsInformation.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		goodsInformation.setFillsViewportHeight(true);
		goodsInformation.getTableHeader().setReorderingAllowed(false);//设置表格头不可拖动
	}
	
	public void showTable()
	{
		try
		{if(searchGuestNameTextField.getText().equals("请输入用户名、姓名、证件号或手机号"))
		{	
			if(isCenterScrollPaneused == true)
			{
				CenterPanel.remove(CenterScrollPane);//当没有输入时，移除表格组件
				isCenterScrollPaneused = false;
				isNothingLabelused = true;
				CenterPanel.repaint();
				CenterPanel.add(nothingLabel,"Center");
				CenterPanel.revalidate();
			}
			nothingLabel.setText("这里什么也没有……");
		}			
		else
		{
			if((gu = UserOperation.FindUserInformation(searchGuestNameTextField.getText())) == null)
			{
				JOptionPane.showMessageDialog(AdminPurchaseHistoryPanel.this, "用户信息不存在！");
				if(isCenterScrollPaneused == true)
				{
					CenterPanel.remove(CenterScrollPane);//信息不存在时，移除表格组件
					isCenterScrollPaneused = false;
					isNothingLabelused = true;
					CenterPanel.repaint();
					CenterPanel.add(nothingLabel,"Center");
					CenterPanel.revalidate();
				}
				nothingLabel.setText("这里什么也没有……");
				return;
			}
			if(isNothingLabelused == true)
			{
				CenterPanel.remove(nothingLabel);//有输入数据，移除标签
				isNothingLabelused = false;
				isCenterScrollPaneused = true;
				CenterPanel.repaint();
				CenterPanel.add(CenterScrollPane,"Center");//添加表格
				CenterPanel.revalidate();
			}
			if((gu = UserOperation.FindUserInformation(searchGuestNameTextField.getText())) != null && !searchItemTextField.getText().equals("请输入商品名称或购买日期"))
			{
					TableDatas = gu.FindGoods(searchItemTextField.getText());
					if(TableDatas == null)//没有搜索到
					{
						JOptionPane.showMessageDialog(AdminPurchaseHistoryPanel.this, "特定消费记录无搜索数据！");
					}
					goodsInformation_TableModel = (DefaultTableModel)goodsInformation.getModel();
					goodsInformation_TableModel.setDataVector(TableDatas,TableNames);
					goodsInformation.setRowHeight(30);
					goodsInformation.setRowMargin(5);
					goodsInformation.updateUI();
			}
			else//没有消费记录
			{
					if((gu = UserOperation.FindUserInformation(searchGuestNameTextField.getText())) != null)
					{
						TableDatas = gu.getAllGoods();//没有消费记录
						if(TableDatas == null)
						{
							JOptionPane.showMessageDialog(AdminPurchaseHistoryPanel.this, "当前用户没有消费记录！");
						}
					}//没有消费记录
					goodsInformation_TableModel = (DefaultTableModel)goodsInformation.getModel();
					goodsInformation_TableModel.setDataVector(TableDatas,TableNames);
					goodsInformation.setRowHeight(30);
					goodsInformation.setRowMargin(5);
					goodsInformation.updateUI();
			}

		
		}
		}
		catch(Exception ee) {}
	}
	
	public AdminPurchaseHistoryPanel(AdminGUI gui)
	{
		isCenterScrollPaneused = false;
		isNothingLabelused = true;
		
		TableNames = new Vector<String>();
		TableNames.add("名称");
		TableNames.add("价格");
		TableNames.add("生产日期");
		TableNames.add("制造商");
		TableNames.add("折扣率");
		TableNames.add("商品简介");
		TableNames.add("购买日期");
		//页面初始化
		PageNameLabel = new JLabel("客户消费记录管理");
		PageNameLabel.setForeground(new Color(19,146,249));
		PageNameLabel.setFont(PageNameLabel.getFont().deriveFont(30.0f));

		searchGuestNameTextField = new JTextField("请输入用户名、姓名、证件号或手机号",25);
		searchGuestNameTextField.setBorder(BorderFactory.createLineBorder(new Color(140,189,239)));
		//搜索框初始化
		searchItemTextField = new JTextField("请输入商品名称或购买日期",25);
		searchItemTextField.setBorder(BorderFactory.createLineBorder(new Color(140,189,239)));
		//按钮更新
		searchItemButton = new JButton("查询");
		searchItemButton.setBorderPainted(false);
		searchItemButton.setBackground(new Color(140,189,239));
		
		AddButton = new JButton("添加");
		AddButton.setBorderPainted(false);
		AddButton.setBackground(new Color(140,189,239));
				
		DeleteButton = new JButton("删除");
		DeleteButton.setBorderPainted(false);
		DeleteButton.setBackground(new Color(140,189,239));
		DeleteButton.setEnabled(false);
		
		nothingLabel = new JLabel("这里什么也没有……",JLabel.CENTER);
		//面板更新
		CenterNorthPanel = new JPanel();
		CenterNorthPanel_FlowLayout = new FlowLayout();
		CenterNorthPanel.setLayout(CenterNorthPanel_FlowLayout);
		CenterNorthPanel.add(searchGuestNameTextField);
		CenterNorthPanel.add(searchItemTextField);
		CenterNorthPanel.add(searchItemButton);
		CenterNorthPanel.add(AddButton);
		CenterNorthPanel.add(DeleteButton);
		
		TabelCreat(null);
		
		CenterScrollPane = new JScrollPane(goodsInformation);
		CenterScrollPane.setBorder(new EmptyBorder(0,0,0,0));
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
		
		ThisPanel_GridLayout = new GridLayout(1,1,0,0);
		this.setLayout(ThisPanel_GridLayout);
		this.add(MainPanel);
		
		goodsInformation.addMouseListener(new MouseAdapter(){//表单没选中，删除按钮不可修改
            public void mouseReleased(MouseEvent e)
            {
            	if(goodsInformation.getSelectedRow() != -1)
            	{
            		AdminPurchaseHistoryPanel.this.DeleteButton.setEnabled(true);
            		
            	}
            }
        });
		
		this.addMouseListener(new MouseAdapter(){//表单没选中，删除按钮不可修改
            public void mousePressed(MouseEvent e)
            {
            	AdminPurchaseHistoryPanel.this.DeleteButton.setEnabled(false);
            	
            	goodsInformation.clearSelection();
            }
        });
		
		AddButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				if((gu = UserOperation.FindUserInformation(searchGuestNameTextField.getText())) == null)
				{
					JOptionPane.showMessageDialog(AdminPurchaseHistoryPanel.this, "用户信息不存在！");
					return;
				}
				if(gu != null)
				{
					ConsumptionPanel csp = new ConsumptionPanel(gui,gu);
			    	Thread t = new Thread() 
			    	{
			        	public void run() 
			        	{
			            	synchronized(add) 
			            	{
			                	while (csp.isVisible())
			                	{
			                		try 
			                		{
			                			add.wait();
			                		} 
			                		catch (InterruptedException e) 
			                		{
			                			e.printStackTrace();
			                		}
			                	}
			                	csp.dispose();
			                	showTable();
								AdminPurchaseHistoryPanel.this.DeleteButton.setEnabled(false);
							
			            	}
			        	}
			    	};
			    	t.start();
				}
			    
			}
		});
		

		
		DeleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				try//用户不存在，删除按钮
				{
				if((gu = UserOperation.FindUserInformation(searchGuestNameTextField.getText())) == null)
				{
					JOptionPane.showMessageDialog(AdminPurchaseHistoryPanel.this, "用户信息不存在！");
					return;
				}
				if(gu != null )//用户存在，删除按钮
				{
					gu.DeleteGoods(goodsInformation.getValueAt(goodsInformation.getSelectedRow(), 6).toString());
					gu.writeIn();
					JOptionPane.showMessageDialog(AdminPurchaseHistoryPanel.this, "删除成功！");
					showTable();
					DeleteButton.setEnabled(false);
				}
				}
				catch(Exception ex) {}
			}
		});
		

		
		searchItemButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				showTable();//进行搜索
				DeleteButton.setEnabled(false);
			}
		});
		
		searchGuestNameTextField.addFocusListener(new FocusAdapter() {//搜索表单适应性
			public void focusLost(FocusEvent e)
			{
				if(searchGuestNameTextField.getText().equals(""))
					searchGuestNameTextField.setText("请输入用户名、姓名、证件号或手机号");
			}
			
			public void focusGained(FocusEvent e)
			{
				if(searchGuestNameTextField.getText().equals("请输入用户名、姓名、证件号或手机号"))
					searchGuestNameTextField.setText("");
			}
		});
		
		searchItemTextField.addFocusListener(new FocusAdapter() {//搜索表单适应性
			public void focusLost(FocusEvent e)
			{
				if(searchItemTextField.getText().equals(""))
					searchItemTextField.setText("请输入商品名称或购买日期");
			}
			
			public void focusGained(FocusEvent e)
			{
				if(searchItemTextField.getText().equals("请输入商品名称或购买日期"))
					searchItemTextField.setText("");
			}
		});
	}
}
