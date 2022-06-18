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
	private JPanel MainPanel,CenterPanel,CenterNorthPanel;//���
	private JScrollPane CenterScrollPane;
	
	private BorderLayout MainPanel_BorderLayout;//����
	private BorderLayout CenterPanel_BorderLayout;//����
	private FlowLayout CenterNorthPanel_FlowLayout;//����
	private GridLayout ThisPanel_GridLayout;//����
	
	private JLabel PageNameLabel;//��ǩ
	
	private JButton searchItemButton;
	
	private JTextField searchItemTextField;
	
	private DefaultTableModel goodsInformation_TableModel;
	private JTable goodsInformation;
	private final Vector<String> TableNames;
	private Vector<Vector<String>> TableDatas;
	
	public void TableUpdate(Guest ThisGuest)
	{
		if(searchItemTextField.getText().equals("��������Ʒ���ƻ�������"))
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
		TableNames.add("����");
		TableNames.add("�۸�");
		TableNames.add("��������");
		TableNames.add("������");
		TableNames.add("�ۿ���");
		TableNames.add("��Ʒ���");
		TableNames.add("��������");
		
		TableDatas = ThisGuest.getAllGoods();//���
		
		goodsInformation_TableModel = new DefaultTableModel(TableDatas,TableNames);//���
		goodsInformation = new JTable(goodsInformation_TableModel){
			private static final long serialVersionUID = 2L;
			public Component prepareRenderer(TableCellRenderer renderer, int row,int column) 
			{
		        Component component = super.prepareRenderer(renderer, row, column);

		        if (row % 2 == 0) //��row��Ϊcolumn��������Բ�ͬ��ɫ��ʾ
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
		goodsInformation.setRowHeight(30);//���
		goodsInformation.setRowMargin(5);
		goodsInformation.setShowGrid(false);//���
		goodsInformation.setShowHorizontalLines(false);
		goodsInformation.setShowVerticalLines(false);//���
		goodsInformation.getTableHeader().setBackground(new Color(19,146,249));
		goodsInformation.doLayout();//���
		goodsInformation.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		goodsInformation.setFillsViewportHeight(true);
		goodsInformation.setEnabled(false);//���ñ�񲻿ɱ༭
		goodsInformation.getTableHeader().setReorderingAllowed(false);//���ñ��ͷ�����϶�
		//���
		PageNameLabel = new JLabel("���Ѽ�¼��ѯ");
		PageNameLabel.setForeground(new Color(19,146,249));
		PageNameLabel.setFont(PageNameLabel.getFont().deriveFont(30.0f));
		//���
		searchItemTextField = new JTextField("��������Ʒ���ƻ�������",20);
		searchItemTextField.setBorder(BorderFactory.createLineBorder(new Color(140,189,239)));
		//���
		searchItemButton = new JButton("��ѯ");
		searchItemButton.setBorderPainted(false);
		searchItemButton.setBackground(new Color(140,189,239));
		//���
		CenterNorthPanel = new JPanel();
		CenterNorthPanel_FlowLayout = new FlowLayout();
		CenterNorthPanel.setLayout(CenterNorthPanel_FlowLayout);
		CenterNorthPanel.add(searchItemTextField);
		CenterNorthPanel.add(searchItemButton);
		//���
		CenterScrollPane = new JScrollPane(goodsInformation);
		CenterScrollPane.setBorder(new EmptyBorder(0,0,0,0));
		//���
		CenterPanel = new JPanel();
		CenterPanel_BorderLayout = new BorderLayout();
		CenterPanel.setLayout(CenterPanel_BorderLayout);
		CenterPanel.add(CenterNorthPanel,"North");
		CenterPanel.add(CenterScrollPane,"Center");
		//���
		MainPanel = new JPanel();
		MainPanel_BorderLayout = new BorderLayout();
		MainPanel.setLayout(MainPanel_BorderLayout);
		MainPanel.add(PageNameLabel,"North");
		MainPanel.add(CenterPanel,"Center");
		MainPanel.setBorder(new EmptyBorder(14,14,0,0));
		//���
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
					searchItemTextField.setText("��������Ʒ���ƻ�������");
			}
			
			public void focusGained(FocusEvent e)
			{
				if(searchItemTextField.getText().equals("��������Ʒ���ƻ�������"))
					searchItemTextField.setText("");
			}
		});
	}
}
