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
	private JScrollPane CenterScrollPane;//�������
	private boolean isCenterScrollPaneused;
	
	private BorderLayout MainPanel_BorderLayout;//�߽粼��
	private BorderLayout CenterPanel_BorderLayout;//�߽粼��
	private FlowLayout CenterNorthPanel_FlowLayout;//��ʽ����
	private GridLayout ThisPanel_GridLayout;//���񲼾�
	
	private JLabel PageNameLabel;//ҳ���ǩ
	
	private JButton searchItemButton,DeleteButton,AddButton;//���ܰ�ť
	
	private JTextField searchGuestNameTextField,searchItemTextField;//������
	private JLabel nothingLabel;//��ǩ
	private boolean isNothingLabelused;
	
	private DefaultTableModel goodsInformation_TableModel;//��Ĭ��ֵ
	private JTable goodsInformation;
	private final Vector<String> TableNames;//��ͷ
	private Vector<Vector<String>> TableDatas;//���
	
	public void TabelCreat(Guest ThisGuest)
	{
		if(ThisGuest != null)//����ʼ��
			TableDatas = ThisGuest.getAllGoods();
		else
			TableDatas = null;
		
		goodsInformation_TableModel = new DefaultTableModel(TableDatas,TableNames){
			public boolean isCellEditable(int row, int column)
			{
				return false;//���ñ�񲻿ɱ༭
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
		goodsInformation.getTableHeader().setBackground(new Color(19,146,249));//��ͷ��ɫ
		goodsInformation.doLayout();
		goodsInformation.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		goodsInformation.setFillsViewportHeight(true);
		goodsInformation.getTableHeader().setReorderingAllowed(false);//���ñ��ͷ�����϶�
	}
	
	public void showTable()
	{
		try
		{if(searchGuestNameTextField.getText().equals("�������û�����������֤���Ż��ֻ���"))
		{	
			if(isCenterScrollPaneused == true)
			{
				CenterPanel.remove(CenterScrollPane);//��û������ʱ���Ƴ�������
				isCenterScrollPaneused = false;
				isNothingLabelused = true;
				CenterPanel.repaint();
				CenterPanel.add(nothingLabel,"Center");
				CenterPanel.revalidate();
			}
			nothingLabel.setText("����ʲôҲû�С���");
		}			
		else
		{
			if((gu = UserOperation.FindUserInformation(searchGuestNameTextField.getText())) == null)
			{
				JOptionPane.showMessageDialog(AdminPurchaseHistoryPanel.this, "�û���Ϣ�����ڣ�");
				if(isCenterScrollPaneused == true)
				{
					CenterPanel.remove(CenterScrollPane);//��Ϣ������ʱ���Ƴ�������
					isCenterScrollPaneused = false;
					isNothingLabelused = true;
					CenterPanel.repaint();
					CenterPanel.add(nothingLabel,"Center");
					CenterPanel.revalidate();
				}
				nothingLabel.setText("����ʲôҲû�С���");
				return;
			}
			if(isNothingLabelused == true)
			{
				CenterPanel.remove(nothingLabel);//���������ݣ��Ƴ���ǩ
				isNothingLabelused = false;
				isCenterScrollPaneused = true;
				CenterPanel.repaint();
				CenterPanel.add(CenterScrollPane,"Center");//��ӱ��
				CenterPanel.revalidate();
			}
			if((gu = UserOperation.FindUserInformation(searchGuestNameTextField.getText())) != null && !searchItemTextField.getText().equals("��������Ʒ���ƻ�������"))
			{
					TableDatas = gu.FindGoods(searchItemTextField.getText());
					if(TableDatas == null)//û��������
					{
						JOptionPane.showMessageDialog(AdminPurchaseHistoryPanel.this, "�ض����Ѽ�¼���������ݣ�");
					}
					goodsInformation_TableModel = (DefaultTableModel)goodsInformation.getModel();
					goodsInformation_TableModel.setDataVector(TableDatas,TableNames);
					goodsInformation.setRowHeight(30);
					goodsInformation.setRowMargin(5);
					goodsInformation.updateUI();
			}
			else//û�����Ѽ�¼
			{
					if((gu = UserOperation.FindUserInformation(searchGuestNameTextField.getText())) != null)
					{
						TableDatas = gu.getAllGoods();//û�����Ѽ�¼
						if(TableDatas == null)
						{
							JOptionPane.showMessageDialog(AdminPurchaseHistoryPanel.this, "��ǰ�û�û�����Ѽ�¼��");
						}
					}//û�����Ѽ�¼
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
		TableNames.add("����");
		TableNames.add("�۸�");
		TableNames.add("��������");
		TableNames.add("������");
		TableNames.add("�ۿ���");
		TableNames.add("��Ʒ���");
		TableNames.add("��������");
		//ҳ���ʼ��
		PageNameLabel = new JLabel("�ͻ����Ѽ�¼����");
		PageNameLabel.setForeground(new Color(19,146,249));
		PageNameLabel.setFont(PageNameLabel.getFont().deriveFont(30.0f));

		searchGuestNameTextField = new JTextField("�������û�����������֤���Ż��ֻ���",25);
		searchGuestNameTextField.setBorder(BorderFactory.createLineBorder(new Color(140,189,239)));
		//�������ʼ��
		searchItemTextField = new JTextField("��������Ʒ���ƻ�������",25);
		searchItemTextField.setBorder(BorderFactory.createLineBorder(new Color(140,189,239)));
		//��ť����
		searchItemButton = new JButton("��ѯ");
		searchItemButton.setBorderPainted(false);
		searchItemButton.setBackground(new Color(140,189,239));
		
		AddButton = new JButton("���");
		AddButton.setBorderPainted(false);
		AddButton.setBackground(new Color(140,189,239));
				
		DeleteButton = new JButton("ɾ��");
		DeleteButton.setBorderPainted(false);
		DeleteButton.setBackground(new Color(140,189,239));
		DeleteButton.setEnabled(false);
		
		nothingLabel = new JLabel("����ʲôҲû�С���",JLabel.CENTER);
		//������
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
		
		ThisPanel_GridLayout = new GridLayout(1,1,0,0);
		this.setLayout(ThisPanel_GridLayout);
		this.add(MainPanel);
		
		goodsInformation.addMouseListener(new MouseAdapter(){//��ûѡ�У�ɾ����ť�����޸�
            public void mouseReleased(MouseEvent e)
            {
            	if(goodsInformation.getSelectedRow() != -1)
            	{
            		AdminPurchaseHistoryPanel.this.DeleteButton.setEnabled(true);
            		
            	}
            }
        });
		
		this.addMouseListener(new MouseAdapter(){//��ûѡ�У�ɾ����ť�����޸�
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
					JOptionPane.showMessageDialog(AdminPurchaseHistoryPanel.this, "�û���Ϣ�����ڣ�");
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
				try//�û������ڣ�ɾ����ť
				{
				if((gu = UserOperation.FindUserInformation(searchGuestNameTextField.getText())) == null)
				{
					JOptionPane.showMessageDialog(AdminPurchaseHistoryPanel.this, "�û���Ϣ�����ڣ�");
					return;
				}
				if(gu != null )//�û����ڣ�ɾ����ť
				{
					gu.DeleteGoods(goodsInformation.getValueAt(goodsInformation.getSelectedRow(), 6).toString());
					gu.writeIn();
					JOptionPane.showMessageDialog(AdminPurchaseHistoryPanel.this, "ɾ���ɹ���");
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
				showTable();//��������
				DeleteButton.setEnabled(false);
			}
		});
		
		searchGuestNameTextField.addFocusListener(new FocusAdapter() {//��������Ӧ��
			public void focusLost(FocusEvent e)
			{
				if(searchGuestNameTextField.getText().equals(""))
					searchGuestNameTextField.setText("�������û�����������֤���Ż��ֻ���");
			}
			
			public void focusGained(FocusEvent e)
			{
				if(searchGuestNameTextField.getText().equals("�������û�����������֤���Ż��ֻ���"))
					searchGuestNameTextField.setText("");
			}
		});
		
		searchItemTextField.addFocusListener(new FocusAdapter() {//��������Ӧ��
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
