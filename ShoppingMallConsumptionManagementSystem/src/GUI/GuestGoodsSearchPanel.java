package GUI;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import java.awt.event.*;
import goods.*;
import people.Guest;

import java.util.*;

public class GuestGoodsSearchPanel extends JPanel{
	private JPanel MainPanel,CenterPanel,CenterNorthPanel;//面板
	private JScrollPane CenterScrollPane;//面板
	
	private BorderLayout MainPanel_BorderLayout;//布局
	private BorderLayout CenterPanel_BorderLayout;//布局
	private FlowLayout CenterNorthPanel_FlowLayout;//布局
	private GridLayout ThisPanel_GridLayout;//布局
	
	private JLabel PageNameLabel;
	
	private JButton searchItemButton,BuyGoodsButton;//按钮
	
	private JTextField searchItemTextField;//文本框
	
	private DefaultTableModel goodsInformation_TableModel;
	private JTable goodsInformation;
	private final Vector<String> TableNames;//  {"商品编号","名称","制造商","生产日期","价格","折扣率","库存","商品简介","备注"};
	private Vector<Vector<String>> TableDatas;
	
	public GuestGoodsSearchPanel(GuestGUI gui,Guest ThisGuest)
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
		TableNames.add("备注");//表头
		
		TableDatas = goodsOperation.getAllGoods();
		
		goodsInformation_TableModel = new DefaultTableModel(TableDatas,TableNames);
		goodsInformation = new JTable(goodsInformation_TableModel){
			private static final long serialVersionUID = 1L;
			public Component prepareRenderer(TableCellRenderer renderer, int row,int column) 
			{
		        Component component = super.prepareRenderer(renderer, row, column);

		        if (row % 2 == 0) //将row改为column，则分列以不同颜色显示
		        {  
		          component.setBackground(Color.white);
		        }
		        if (row % 2 == 1) 
		        {
		          component.setBackground(new Color(140,189,239));
		        }
		        return component;
		     }
		};
		goodsInformation.setRowHeight(30);//表格设置
		goodsInformation.setRowMargin(5);
		goodsInformation.setShowGrid(false);
		goodsInformation.setShowHorizontalLines(false);//表格设置
		goodsInformation.setShowVerticalLines(false);
		goodsInformation.getTableHeader().setBackground(new Color(19,146,249));
		goodsInformation.doLayout();//表格设置
		goodsInformation.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		goodsInformation.setFillsViewportHeight(true);
		goodsInformation.setEnabled(false);//设置表格不可编辑
		goodsInformation.getTableHeader().setReorderingAllowed(false);//设置表格头不可拖动
		//设置初始化
		PageNameLabel = new JLabel("商品信息查询");
		PageNameLabel.setForeground(new Color(19,146,249));
		PageNameLabel.setFont(PageNameLabel.getFont().deriveFont(30.0f));
		//设置初始化
		searchItemTextField = new JTextField("请输入商品名称或商品编号",20);
		searchItemTextField.setBorder(BorderFactory.createLineBorder(new Color(140,189,239)));
		searchItemButton = new JButton("查询");
		searchItemButton.setBorderPainted(false);
		searchItemButton.setBackground(new Color(140,189,239));
		//设置初始化
		BuyGoodsButton = new JButton("购买商品");
		BuyGoodsButton.setBorderPainted(false);
		BuyGoodsButton.setBackground(new Color(140,189,239));
		//设置初始化
		CenterNorthPanel = new JPanel();
		CenterNorthPanel_FlowLayout = new FlowLayout();
		CenterNorthPanel.setLayout(CenterNorthPanel_FlowLayout);
		CenterNorthPanel.add(searchItemTextField);
		CenterNorthPanel.add(searchItemButton);
		CenterNorthPanel.add(BuyGoodsButton);
		//设置初始化
		CenterScrollPane = new JScrollPane(goodsInformation);
		CenterScrollPane.setBorder(new EmptyBorder(0,0,0,0));
		//设置初始化
		CenterPanel = new JPanel();
		CenterPanel_BorderLayout = new BorderLayout();
		CenterPanel.setLayout(CenterPanel_BorderLayout);
		CenterPanel.add(CenterNorthPanel,"North");
		CenterPanel.add(CenterScrollPane,"Center");
		//设置初始化
		MainPanel = new JPanel();
		MainPanel_BorderLayout = new BorderLayout();
		MainPanel.setLayout(MainPanel_BorderLayout);
		MainPanel.add(PageNameLabel,"North");
		MainPanel.add(CenterPanel,"Center");
		MainPanel.setBorder(new EmptyBorder(14,14,0,0));
		//设置初始化
		ThisPanel_GridLayout = new GridLayout(1,1,0,0);
		this.setLayout(ThisPanel_GridLayout);
		this.add(MainPanel);
		
		BuyGoodsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				ConsumptionPanel csp = new ConsumptionPanel(gui,ThisGuest);//卖商品按钮监听
			}
		});
		
		searchItemButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				if(searchItemTextField.getText().equals("请输入商品名称或商品编号"))
					TableDatas = goodsOperation.getAllGoods();//获取所有商品
				else
				{
					TableDatas = goodsOperation.FindGoods(searchItemTextField.getText());//获取特定的搜索商品
				}
				goodsInformation_TableModel = (DefaultTableModel)goodsInformation.getModel();
				goodsInformation_TableModel.setDataVector(TableDatas,TableNames);
				goodsInformation.setRowHeight(30);
				goodsInformation.setRowMargin(5);
				goodsInformation.updateUI();
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
