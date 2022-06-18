package GUI;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import java.awt.event.*;
import goods.*;
import java.util.*;


class AddAndEditPanel extends JFrame
{

	//标签 组件组 面板等组件字段
	private JLabel AddAndEditLabel;
	private InformationItemAdmin goodsNumber;
	private InformationItemAdmin goodsName;
	private InformationItemAdmin manufacturer;
	private InformationItemAdmin dateOfProduction;
	private InformationItemAdmin price;
	private InformationItemAdmin discount;
	private InformationItemAdmin inventory;
	private InformationItemAdmin productIntroduction;
	private InformationItemAdmin remarks;
	private JButton BasicInformationPanel_ConfirmButton;
	private JPanel BasicInformationPanel;
	private GridLayout BasicInformationPanel_GridLayout;
	private JPanel BasicInformationOUTPanel;
	
	public AddAndEditPanel(AdminGUI gui,Vector<String> items)
	{	
		this.setSize(650, 540);
		int windowWidth = this.getWidth(); //获得窗口宽
		int windowHeight = this.getHeight(); //获得窗口高
		Toolkit kit = Toolkit.getDefaultToolkit(); //定义工具包
		Dimension screenSize = kit.getScreenSize(); //获取屏幕的尺寸
		int screenWidth = screenSize.width; //获取屏幕的宽
		int screenHeight = screenSize.height; //获取屏幕的高
		this.setLocation(screenWidth / 2 - windowWidth / 2, screenHeight / 2 - windowHeight / 2);//设置窗口居中显示

		
		AddAndEditLabel = new JLabel("商品信息款项");
		AddAndEditLabel.setBorder(new EmptyBorder(10,0,10,0));
		AddAndEditLabel.setFont(AddAndEditLabel.getFont().deriveFont(14.0f));

		if(items != null)//更新数据
		{
			this.setTitle("商品信息更新");
			goodsNumber = new InformationItemAdmin("商品编号：",items.get(0),false);//实例化
			goodsName = new InformationItemAdmin("名称：",items.get(1),false);//实例化
			manufacturer = new InformationItemAdmin("制造商：",items.get(2));//实例化
			dateOfProduction = new InformationItemAdmin("生产日期：",items.get(3),0.1);//实例化
			price = new InformationItemAdmin("价格(元)：",items.get(4));//实例化
			discount = new InformationItemAdmin("折扣率(%)：",0.1,Integer.parseInt(items.get(5)));//实例化
			inventory = new InformationItemAdmin("库存：",items.get(6));//实例化
			productIntroduction = new InformationItemAdmin("商品简介：",items.get(7));//实例化
			remarks = new InformationItemAdmin("备注：",items.get(8));//实例化
		}
		else//添加数据
		{
			this.setTitle("商品信息添加");
			goodsNumber = new InformationItemAdmin("商品编号：",0);//实例化
			goodsName = new InformationItemAdmin("名称：",0);//实例化
			manufacturer = new InformationItemAdmin("制造商：",0);//实例化
			dateOfProduction = new InformationItemAdmin("生产日期：","2010-1-1",0.1);//实例化
			price = new InformationItemAdmin("价格(元)：",0);//实例化
			discount = new InformationItemAdmin("折扣率(%)：",0.1,100);//实例化
			inventory = new InformationItemAdmin("库存：",0);//实例化
			productIntroduction = new InformationItemAdmin("商品简介：",0);//实例化
			remarks = new InformationItemAdmin("备注：",0);//实例化
		}
		
		BasicInformationPanel_ConfirmButton = new JButton("确定");//确定按钮初始化
		BasicInformationPanel_ConfirmButton.setBorderPainted(false);
		BasicInformationPanel_ConfirmButton.setBackground(new Color(140,189,239));

		BasicInformationPanel = new JPanel();//面板初始化
		BasicInformationPanel_GridLayout = new GridLayout(0,1,5,5);
		BasicInformationPanel.setLayout(BasicInformationPanel_GridLayout);
		BasicInformationPanel.add(AddAndEditLabel);//添加面板项
		BasicInformationPanel.add(goodsNumber);
		BasicInformationPanel.add(goodsName);
		BasicInformationPanel.add(manufacturer);
		BasicInformationPanel.add(dateOfProduction);
		BasicInformationPanel.add(price);
		BasicInformationPanel.add(discount);
		BasicInformationPanel.add(inventory);
		BasicInformationPanel.add(productIntroduction);
		BasicInformationPanel.add(remarks);
		BasicInformationPanel.add(BasicInformationPanel_ConfirmButton);
		
		BasicInformationOUTPanel = new JPanel();//面板初始化
		BasicInformationOUTPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		BasicInformationOUTPanel.add(BasicInformationPanel);
		
		this.setContentPane(BasicInformationOUTPanel);//设置窗口默认面板
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setResizable(false);
		//设置窗口各种属性
		this.setVisible(true);
		
		BasicInformationPanel_ConfirmButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
			synchronized (AdminGoodsSearchPanel.lock)//同步语句块 防止表格在没有更新数据的情况下就自己更新
			{	if(goodsNumber.getText().equals("")||goodsName.getText().equals("")||manufacturer.getText().equals("")||price.getText().equals("")||inventory.getText().equals("")||
							productIntroduction.getText().equals("")||remarks.getText().equals(""))
				{
					JOptionPane.showMessageDialog(AddAndEditPanel.this, "数据不可为空！");
				}
				else if(items != null)//更新数据
				{
					goods go = new goods(goodsNumber.getText(),goodsName.getText(),manufacturer.getText(),dateOfProduction.getDate(),price.getText(),discount.getGoodsName(),inventory.getText(),
							productIntroduction.getText(),remarks.getText());
					goodsOperation.UpdateGoods(go);
					goodsOperation.writeIn();
					JOptionPane.showMessageDialog(AddAndEditPanel.this, "商品更新成功！");
					AddAndEditPanel.this.setVisible(false);
					AdminGoodsSearchPanel.lock.notifyAll();//锁转移
					//AddAndEditPanel.this.dispose();
				}
				else if(goodsOperation.isGoodsExistence(goodsNumber.getText()) == true || goodsOperation.isGoodsExistence(goodsName.getText()) == true )
				{
					JOptionPane.showMessageDialog(AddAndEditPanel.this, "商品编号或名称存在！");
				}
				else//添加数据
				{
					goods go = new goods(goodsNumber.getText(),goodsName.getText(),manufacturer.getText(),dateOfProduction.getDate(),price.getText(),discount.getGoodsName(),inventory.getText(),
							productIntroduction.getText(),remarks.getText());
					goodsOperation.addGoods(go);
					goodsOperation.writeIn();
					JOptionPane.showMessageDialog(AddAndEditPanel.this, "商品添加成功！");
					AddAndEditPanel.this.setVisible(false);
					AdminGoodsSearchPanel.lock.notifyAll();//锁转移
					//AddAndEditPanel.this.dispose();
				}
			}
		}
		});
	}
}

public class AdminGoodsSearchPanel extends JPanel{
	public static Object lock = new Object();//构建同步语句锁
	//属性字段
	private JPanel MainPanel,CenterPanel,CenterNorthPanel;
	private JScrollPane CenterScrollPane;
	
	private BorderLayout MainPanel_BorderLayout;//布局
	private BorderLayout CenterPanel_BorderLayout;//布局
	private FlowLayout CenterNorthPanel_FlowLayout;//布局
	private GridLayout ThisPanel_GridLayout;//布局
	
	private JLabel PageNameLabel;
	
	private JButton searchItemButton,DeleteButton,AddButton,EditButton;//按钮
	
	private JTextField searchItemTextField;
	
	private DefaultTableModel goodsInformation_TableModel;
	private JTable goodsInformation;
	private final Vector<String> TableNames;//  {"商品编号","名称","制造商","生产日期","价格","折扣率","库存","商品简介","备注"};
	private Vector<Vector<String>> TableDatas;
	
	public AdminGoodsSearchPanel(AdminGUI gui)
	{
		TableNames = new Vector<String>();
		TableNames.add("商品编号");
		TableNames.add("名称");
		TableNames.add("制造商");
		TableNames.add("生产日期");
		TableNames.add("价格");
		TableNames.add("折扣率");
		TableNames.add("库存");
		TableNames.add("商品简介");
		TableNames.add("备注");
		
		TableDatas = goodsOperation.getAllGoods();
		
		goodsInformation_TableModel = new DefaultTableModel(TableDatas,TableNames){
			public boolean isCellEditable(int row, int column)
			{
				return false;
			}
		};
		goodsInformation = new JTable(goodsInformation_TableModel);//设置表格模式
		goodsInformation.setRowSelectionAllowed(true);//行是否可选
		goodsInformation.setSelectionBackground(new Color(140,189,239));//颜色
		goodsInformation.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);//模式
		goodsInformation.setRowHeight(30);//高度
		goodsInformation.setRowMargin(5);//边距
		goodsInformation.setShowGrid(false);//窗口
		goodsInformation.setShowHorizontalLines(false);//水平线
		goodsInformation.setShowVerticalLines(false);//垂直线
		goodsInformation.getTableHeader().setBackground(new Color(19,146,249));
		goodsInformation.doLayout();
		goodsInformation.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		goodsInformation.setFillsViewportHeight(true);
		goodsInformation.getTableHeader().setReorderingAllowed(false);//设置表格头不可拖动
		
		PageNameLabel = new JLabel("库存商品信息管理");//页面标签初始化
		PageNameLabel.setForeground(new Color(19,146,249));
		PageNameLabel.setFont(PageNameLabel.getFont().deriveFont(30.0f));

		searchItemTextField = new JTextField("请输入商品名称或商品编号",30);//搜索框初始化
		searchItemTextField.setBorder(BorderFactory.createLineBorder(new Color(140,189,239)));
		
		searchItemButton = new JButton("查询");//四大按钮初始化
		searchItemButton.setBorderPainted(false);
		searchItemButton.setBackground(new Color(140,189,239));
		
		AddButton = new JButton("添加");//四大按钮初始化
		AddButton.setBorderPainted(false);
		AddButton.setBackground(new Color(140,189,239));
		
		EditButton = new JButton("编辑");//四大按钮初始化
		EditButton.setBorderPainted(false);
		EditButton.setBackground(new Color(140,189,239));
		EditButton.setEnabled(false);
		
		DeleteButton = new JButton("删除");//四大按钮初始化
		DeleteButton.setBorderPainted(false);
		DeleteButton.setBackground(new Color(140,189,239));
		DeleteButton.setEnabled(false);
		
		CenterNorthPanel = new JPanel();//面板初始化
		CenterNorthPanel_FlowLayout = new FlowLayout();
		CenterNorthPanel.setLayout(CenterNorthPanel_FlowLayout);
		CenterNorthPanel.add(searchItemTextField);//添加
		CenterNorthPanel.add(searchItemButton);//添加
		CenterNorthPanel.add(AddButton);//添加
		CenterNorthPanel.add(EditButton);//添加
		CenterNorthPanel.add(DeleteButton);//添加
		
		CenterScrollPane = new JScrollPane(goodsInformation);//滚动面板初始化
		CenterScrollPane.setBorder(new EmptyBorder(0,0,0,0));
		
		CenterPanel = new JPanel();//面板初始化
		CenterPanel_BorderLayout = new BorderLayout();
		CenterPanel.setLayout(CenterPanel_BorderLayout);
		CenterPanel.add(CenterNorthPanel,"North");
		CenterPanel.add(CenterScrollPane,"Center");
		
		MainPanel = new JPanel();//面板初始化
		MainPanel_BorderLayout = new BorderLayout();
		MainPanel.setLayout(MainPanel_BorderLayout);
		MainPanel.add(PageNameLabel,"North");
		MainPanel.add(CenterPanel,"Center");
		MainPanel.setBorder(new EmptyBorder(14,14,0,0));
		
		ThisPanel_GridLayout = new GridLayout(1,1,0,0);
		this.setLayout(ThisPanel_GridLayout);//设置默认布局
		this.add(MainPanel);
		
		goodsInformation.addMouseListener(new MouseAdapter(){
            public void mouseReleased(MouseEvent e)//点击表格，按钮可用
            {
            	if(goodsInformation.getSelectedRow() != -1)
            	{
            		AdminGoodsSearchPanel.this.DeleteButton.setEnabled(true);
            		AdminGoodsSearchPanel.this.EditButton.setEnabled(true);
            	}
            }
        });
		
		this.addMouseListener(new MouseAdapter(){
            public void mousePressed(MouseEvent e)//不点击表格，按钮失效
            {
            	AdminGoodsSearchPanel.this.DeleteButton.setEnabled(false);
            	AdminGoodsSearchPanel.this.EditButton.setEnabled(false);
            	goodsInformation.clearSelection();
            }
        });
		
		AddButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				AddAndEditPanel aaep = new AddAndEditPanel(gui,null);//调用添加数据面板
			    Thread t = new Thread() 
			    {
			        public void run() 
			        {
			            synchronized(lock) //锁定操作
			            {
			                while (aaep.isVisible())
			                {
			                	try 
			                	{
			                		lock.wait();//线程等待
			                	} 
			                	catch (InterruptedException e) 
			                	{
			                		e.printStackTrace();
			                	}
			                }
			                aaep.dispose();
			                try
							{
			                	if(searchItemTextField.getText().equals("请输入商品名称或商品编号"))
			                		TableDatas = goodsOperation.getAllGoods();//全部商品
			                	else
			                	{
			                		TableDatas = goodsOperation.FindGoods(searchItemTextField.getText());
			                	}
							}
			                catch(Exception ex) {}//更新商品数据信息
							goodsInformation_TableModel = (DefaultTableModel)goodsInformation.getModel();
							goodsInformation_TableModel.setDataVector(TableDatas,TableNames);
							goodsInformation.setRowHeight(30);//设置表格
							goodsInformation.setRowMargin(5);
							goodsInformation.updateUI();
			            	AdminGoodsSearchPanel.this.DeleteButton.setEnabled(false);
			            	AdminGoodsSearchPanel.this.EditButton.setEnabled(false);
			            }
			        }
			    };
			    t.start();
			}
		});
		
		EditButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				AddAndEditPanel aaep = new AddAndEditPanel(gui,TableDatas.get(goodsInformation.getSelectedRow()));
			    Thread t = new Thread() //弹出编辑面板
			    {
			        public void run() 
			        {
			            synchronized(lock) //锁定操作
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
			                try
							{
			                	if(searchItemTextField.getText().equals("请输入商品名称或商品编号"))
			                		TableDatas = goodsOperation.getAllGoods();
			                	else
			                	{
			                		TableDatas = goodsOperation.FindGoods(searchItemTextField.getText());
			                	}
							}
			                catch(Exception ex) {}//更新商品信息
							goodsInformation_TableModel = (DefaultTableModel)goodsInformation.getModel();
							goodsInformation_TableModel.setDataVector(TableDatas,TableNames);
							goodsInformation.setRowHeight(30);
							goodsInformation.setRowMargin(5);
							goodsInformation.updateUI();
			            	AdminGoodsSearchPanel.this.DeleteButton.setEnabled(false);
			            	AdminGoodsSearchPanel.this.EditButton.setEnabled(false);
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
				{//删除选中行的第一列对应的商品数据
				goodsOperation.DeleteGoods(goodsInformation.getValueAt(goodsInformation.getSelectedRow(), 1).toString(), goodsInformation.getValueAt(goodsInformation.getSelectedRow(), 0).toString());
				goodsOperation.writeIn();
				if(searchItemTextField.getText().equals("请输入商品名称或商品编号"))
					TableDatas = goodsOperation.getAllGoods();//获取全部数据
				else
				{
					TableDatas = goodsOperation.FindGoods(searchItemTextField.getText());
				}
				JOptionPane.showMessageDialog(AdminGoodsSearchPanel.this, "删除成功！");//更新数据信息
				goodsInformation_TableModel = (DefaultTableModel)goodsInformation.getModel();
				goodsInformation_TableModel.setDataVector(TableDatas,TableNames);
				goodsInformation.setRowHeight(30);
				goodsInformation.setRowMargin(5);
				goodsInformation.updateUI();
				DeleteButton.setEnabled(false);
				EditButton.setEnabled(false);
				}
                catch(Exception ex) {}
			}
		});
		
		searchItemButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				if(searchItemTextField.getText().equals("请输入商品名称或商品编号"))
					TableDatas = goodsOperation.getAllGoods();//获取全部数据
				else
				{//获取特定商品
					TableDatas = goodsOperation.FindGoods(searchItemTextField.getText());
				}
				goodsInformation_TableModel = (DefaultTableModel)goodsInformation.getModel();//获取表单模式
				goodsInformation_TableModel.setDataVector(TableDatas,TableNames);//设置数据
				goodsInformation.setRowHeight(30);//更新表格
				goodsInformation.setRowMargin(5);
				goodsInformation.updateUI();
				AdminGoodsSearchPanel.this.DeleteButton.setEnabled(false);
				AdminGoodsSearchPanel.this.EditButton.setEnabled(false);
			}
		});
		
		searchItemTextField.addFocusListener(new FocusAdapter() {
			public void focusLost(FocusEvent e)
			{
				if(searchItemTextField.getText().equals(""))
					searchItemTextField.setText("请输入商品名称或商品编号");
			}
			
			public void focusGained(FocusEvent e)
			{
				if(searchItemTextField.getText().equals("请输入商品名称或商品编号"))
					searchItemTextField.setText("");
			}
		});
	}
}
