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
	private JPanel MainPanel,CenterPanel,CenterNorthPanel;//���
	private JScrollPane CenterScrollPane;//���
	
	private BorderLayout MainPanel_BorderLayout;//����
	private BorderLayout CenterPanel_BorderLayout;//����
	private FlowLayout CenterNorthPanel_FlowLayout;//����
	private GridLayout ThisPanel_GridLayout;//����
	
	private JLabel PageNameLabel;
	
	private JButton searchItemButton,BuyGoodsButton;//��ť
	
	private JTextField searchItemTextField;//�ı���
	
	private DefaultTableModel goodsInformation_TableModel;
	private JTable goodsInformation;
	private final Vector<String> TableNames;//  {"��Ʒ���","����","������","��������","�۸�","�ۿ���","���","��Ʒ���","��ע"};
	private Vector<Vector<String>> TableDatas;
	
	public GuestGoodsSearchPanel(GuestGUI gui,Guest ThisGuest)
	{
		TableNames = new Vector<String>();
		TableNames.add("��Ʒ���");
		TableNames.add("����");
		TableNames.add("������");
		TableNames.add("��������");
		TableNames.add("�۸�");
		TableNames.add("�ۿ���");
		TableNames.add("���");
		TableNames.add("��Ʒ���");
		TableNames.add("��ע");//��ͷ
		
		TableDatas = goodsOperation.getAllGoods();
		
		goodsInformation_TableModel = new DefaultTableModel(TableDatas,TableNames);
		goodsInformation = new JTable(goodsInformation_TableModel){
			private static final long serialVersionUID = 1L;
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
		goodsInformation.setRowHeight(30);//�������
		goodsInformation.setRowMargin(5);
		goodsInformation.setShowGrid(false);
		goodsInformation.setShowHorizontalLines(false);//�������
		goodsInformation.setShowVerticalLines(false);
		goodsInformation.getTableHeader().setBackground(new Color(19,146,249));
		goodsInformation.doLayout();//�������
		goodsInformation.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		goodsInformation.setFillsViewportHeight(true);
		goodsInformation.setEnabled(false);//���ñ�񲻿ɱ༭
		goodsInformation.getTableHeader().setReorderingAllowed(false);//���ñ��ͷ�����϶�
		//���ó�ʼ��
		PageNameLabel = new JLabel("��Ʒ��Ϣ��ѯ");
		PageNameLabel.setForeground(new Color(19,146,249));
		PageNameLabel.setFont(PageNameLabel.getFont().deriveFont(30.0f));
		//���ó�ʼ��
		searchItemTextField = new JTextField("��������Ʒ���ƻ���Ʒ���",20);
		searchItemTextField.setBorder(BorderFactory.createLineBorder(new Color(140,189,239)));
		searchItemButton = new JButton("��ѯ");
		searchItemButton.setBorderPainted(false);
		searchItemButton.setBackground(new Color(140,189,239));
		//���ó�ʼ��
		BuyGoodsButton = new JButton("������Ʒ");
		BuyGoodsButton.setBorderPainted(false);
		BuyGoodsButton.setBackground(new Color(140,189,239));
		//���ó�ʼ��
		CenterNorthPanel = new JPanel();
		CenterNorthPanel_FlowLayout = new FlowLayout();
		CenterNorthPanel.setLayout(CenterNorthPanel_FlowLayout);
		CenterNorthPanel.add(searchItemTextField);
		CenterNorthPanel.add(searchItemButton);
		CenterNorthPanel.add(BuyGoodsButton);
		//���ó�ʼ��
		CenterScrollPane = new JScrollPane(goodsInformation);
		CenterScrollPane.setBorder(new EmptyBorder(0,0,0,0));
		//���ó�ʼ��
		CenterPanel = new JPanel();
		CenterPanel_BorderLayout = new BorderLayout();
		CenterPanel.setLayout(CenterPanel_BorderLayout);
		CenterPanel.add(CenterNorthPanel,"North");
		CenterPanel.add(CenterScrollPane,"Center");
		//���ó�ʼ��
		MainPanel = new JPanel();
		MainPanel_BorderLayout = new BorderLayout();
		MainPanel.setLayout(MainPanel_BorderLayout);
		MainPanel.add(PageNameLabel,"North");
		MainPanel.add(CenterPanel,"Center");
		MainPanel.setBorder(new EmptyBorder(14,14,0,0));
		//���ó�ʼ��
		ThisPanel_GridLayout = new GridLayout(1,1,0,0);
		this.setLayout(ThisPanel_GridLayout);
		this.add(MainPanel);
		
		BuyGoodsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				ConsumptionPanel csp = new ConsumptionPanel(gui,ThisGuest);//����Ʒ��ť����
			}
		});
		
		searchItemButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				if(searchItemTextField.getText().equals("��������Ʒ���ƻ���Ʒ���"))
					TableDatas = goodsOperation.getAllGoods();//��ȡ������Ʒ
				else
				{
					TableDatas = goodsOperation.FindGoods(searchItemTextField.getText());//��ȡ�ض���������Ʒ
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
					searchItemTextField.setText("��������Ʒ���ƻ���Ʒ���");
			}
			
			public void focusGained(FocusEvent e)
			{
				if(searchItemTextField.getText().equals("��������Ʒ���ƻ���Ʒ���"))
					searchItemTextField.setText("");
			}
		});
	}
}
