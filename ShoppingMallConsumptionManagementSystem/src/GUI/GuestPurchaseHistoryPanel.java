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

public class GuestPurchaseHistoryPanel extends JPanel{
	private JPanel MainPanel,CenterPanel,CenterNorthPanel;//面板
	private JScrollPane CenterScrollPane;
	
	private BorderLayout MainPanel_BorderLayout;//布局
	private BorderLayout CenterPanel_BorderLayout;//布局
	private FlowLayout CenterNorthPanel_FlowLayout;//布局
	private GridLayout ThisPanel_GridLayout;//布局
	
	private JLabel PageNameLabel;//标签
	
	private JButton searchItemButton;
	
	private JTextField searchItemTextField;
	
	private DefaultTableModel goodsInformation_TableModel;
	private JTable goodsInformation;
	private final Vector<String> TableNames;
	private Vector<Vector<String>> TableDatas;
	
	public void TableUpdate(Guest ThisGuest)
	{
		if(searchItemTextField.getText().equals("请输入商品名称或购买日期"))
			TableDatas = ThisGuest.getAllGoods();
		else
		{
			TableDatas = ThisGuest.FindGoods(searchItemTextField.getText());
		}
		goodsInformation_TableModel = (DefaultTableModel)goodsInformation.getModel();
		goodsInformation_TableModel.setDataVector(TableDatas,TableNames);
		goodsInformation.setRowHeight(30);
		goodsInformation.setRowMargin(5);
		goodsInformation.updateUI();
	}
	
	public GuestPurchaseHistoryPanel(Guest ThisGuest)
	{
		TableNames = new Vector<String>();
		TableNames.add("名称");
		TableNames.add("价格");
		TableNames.add("生产日期");
		TableNames.add("制造商");
		TableNames.add("折扣率");
		TableNames.add("商品简介");
		TableNames.add("购买日期");
		
		TableDatas = ThisGuest.getAllGoods();//表格
		
		goodsInformation_TableModel = new DefaultTableModel(TableDatas,TableNames);//表格
		goodsInformation = new JTable(goodsInformation_TableModel){
			private static final long serialVersionUID = 2L;
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
		goodsInformation.setRowHeight(30);//表格
		goodsInformation.setRowMargin(5);
		goodsInformation.setShowGrid(false);//表格
		goodsInformation.setShowHorizontalLines(false);
		goodsInformation.setShowVerticalLines(false);//表格
		goodsInformation.getTableHeader().setBackground(new Color(19,146,249));
		goodsInformation.doLayout();//表格
		goodsInformation.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		goodsInformation.setFillsViewportHeight(true);
		goodsInformation.setEnabled(false);//设置表格不可编辑
		goodsInformation.getTableHeader().setReorderingAllowed(false);//设置表格头不可拖动
		//面板
		PageNameLabel = new JLabel("消费记录查询");
		PageNameLabel.setForeground(new Color(19,146,249));
		PageNameLabel.setFont(PageNameLabel.getFont().deriveFont(30.0f));
		//面板
		searchItemTextField = new JTextField("请输入商品名称或购买日期",20);
		searchItemTextField.setBorder(BorderFactory.createLineBorder(new Color(140,189,239)));
		//面板
		searchItemButton = new JButton("查询");
		searchItemButton.setBorderPainted(false);
		searchItemButton.setBackground(new Color(140,189,239));
		//面板
		CenterNorthPanel = new JPanel();
		CenterNorthPanel_FlowLayout = new FlowLayout();
		CenterNorthPanel.setLayout(CenterNorthPanel_FlowLayout);
		CenterNorthPanel.add(searchItemTextField);
		CenterNorthPanel.add(searchItemButton);
		//面板
		CenterScrollPane = new JScrollPane(goodsInformation);
		CenterScrollPane.setBorder(new EmptyBorder(0,0,0,0));
		//面板
		CenterPanel = new JPanel();
		CenterPanel_BorderLayout = new BorderLayout();
		CenterPanel.setLayout(CenterPanel_BorderLayout);
		CenterPanel.add(CenterNorthPanel,"North");
		CenterPanel.add(CenterScrollPane,"Center");
		//面板
		MainPanel = new JPanel();
		MainPanel_BorderLayout = new BorderLayout();
		MainPanel.setLayout(MainPanel_BorderLayout);
		MainPanel.add(PageNameLabel,"North");
		MainPanel.add(CenterPanel,"Center");
		MainPanel.setBorder(new EmptyBorder(14,14,0,0));
		//面板
		ThisPanel_GridLayout = new GridLayout(1,1,0,0);
		this.setLayout(ThisPanel_GridLayout);
		this.add(MainPanel);
		

		
		searchItemButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				TableUpdate(ThisGuest);
			}
		});
		
		searchItemTextField.addFocusListener(new FocusAdapter() {
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
