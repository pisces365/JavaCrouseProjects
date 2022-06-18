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

	//��ǩ ����� ��������ֶ�
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
		int windowWidth = this.getWidth(); //��ô��ڿ�
		int windowHeight = this.getHeight(); //��ô��ڸ�
		Toolkit kit = Toolkit.getDefaultToolkit(); //���幤�߰�
		Dimension screenSize = kit.getScreenSize(); //��ȡ��Ļ�ĳߴ�
		int screenWidth = screenSize.width; //��ȡ��Ļ�Ŀ�
		int screenHeight = screenSize.height; //��ȡ��Ļ�ĸ�
		this.setLocation(screenWidth / 2 - windowWidth / 2, screenHeight / 2 - windowHeight / 2);//���ô��ھ�����ʾ

		
		AddAndEditLabel = new JLabel("��Ʒ��Ϣ����");
		AddAndEditLabel.setBorder(new EmptyBorder(10,0,10,0));
		AddAndEditLabel.setFont(AddAndEditLabel.getFont().deriveFont(14.0f));

		if(items != null)//��������
		{
			this.setTitle("��Ʒ��Ϣ����");
			goodsNumber = new InformationItemAdmin("��Ʒ��ţ�",items.get(0),false);//ʵ����
			goodsName = new InformationItemAdmin("���ƣ�",items.get(1),false);//ʵ����
			manufacturer = new InformationItemAdmin("�����̣�",items.get(2));//ʵ����
			dateOfProduction = new InformationItemAdmin("�������ڣ�",items.get(3),0.1);//ʵ����
			price = new InformationItemAdmin("�۸�(Ԫ)��",items.get(4));//ʵ����
			discount = new InformationItemAdmin("�ۿ���(%)��",0.1,Integer.parseInt(items.get(5)));//ʵ����
			inventory = new InformationItemAdmin("��棺",items.get(6));//ʵ����
			productIntroduction = new InformationItemAdmin("��Ʒ��飺",items.get(7));//ʵ����
			remarks = new InformationItemAdmin("��ע��",items.get(8));//ʵ����
		}
		else//�������
		{
			this.setTitle("��Ʒ��Ϣ���");
			goodsNumber = new InformationItemAdmin("��Ʒ��ţ�",0);//ʵ����
			goodsName = new InformationItemAdmin("���ƣ�",0);//ʵ����
			manufacturer = new InformationItemAdmin("�����̣�",0);//ʵ����
			dateOfProduction = new InformationItemAdmin("�������ڣ�","2010-1-1",0.1);//ʵ����
			price = new InformationItemAdmin("�۸�(Ԫ)��",0);//ʵ����
			discount = new InformationItemAdmin("�ۿ���(%)��",0.1,100);//ʵ����
			inventory = new InformationItemAdmin("��棺",0);//ʵ����
			productIntroduction = new InformationItemAdmin("��Ʒ��飺",0);//ʵ����
			remarks = new InformationItemAdmin("��ע��",0);//ʵ����
		}
		
		BasicInformationPanel_ConfirmButton = new JButton("ȷ��");//ȷ����ť��ʼ��
		BasicInformationPanel_ConfirmButton.setBorderPainted(false);
		BasicInformationPanel_ConfirmButton.setBackground(new Color(140,189,239));

		BasicInformationPanel = new JPanel();//����ʼ��
		BasicInformationPanel_GridLayout = new GridLayout(0,1,5,5);
		BasicInformationPanel.setLayout(BasicInformationPanel_GridLayout);
		BasicInformationPanel.add(AddAndEditLabel);//��������
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
		
		BasicInformationOUTPanel = new JPanel();//����ʼ��
		BasicInformationOUTPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		BasicInformationOUTPanel.add(BasicInformationPanel);
		
		this.setContentPane(BasicInformationOUTPanel);//���ô���Ĭ�����
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setResizable(false);
		//���ô��ڸ�������
		this.setVisible(true);
		
		BasicInformationPanel_ConfirmButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
			synchronized (AdminGoodsSearchPanel.lock)//ͬ������ ��ֹ�����û�и������ݵ�����¾��Լ�����
			{	if(goodsNumber.getText().equals("")||goodsName.getText().equals("")||manufacturer.getText().equals("")||price.getText().equals("")||inventory.getText().equals("")||
							productIntroduction.getText().equals("")||remarks.getText().equals(""))
				{
					JOptionPane.showMessageDialog(AddAndEditPanel.this, "���ݲ���Ϊ�գ�");
				}
				else if(items != null)//��������
				{
					goods go = new goods(goodsNumber.getText(),goodsName.getText(),manufacturer.getText(),dateOfProduction.getDate(),price.getText(),discount.getGoodsName(),inventory.getText(),
							productIntroduction.getText(),remarks.getText());
					goodsOperation.UpdateGoods(go);
					goodsOperation.writeIn();
					JOptionPane.showMessageDialog(AddAndEditPanel.this, "��Ʒ���³ɹ���");
					AddAndEditPanel.this.setVisible(false);
					AdminGoodsSearchPanel.lock.notifyAll();//��ת��
					//AddAndEditPanel.this.dispose();
				}
				else if(goodsOperation.isGoodsExistence(goodsNumber.getText()) == true || goodsOperation.isGoodsExistence(goodsName.getText()) == true )
				{
					JOptionPane.showMessageDialog(AddAndEditPanel.this, "��Ʒ��Ż����ƴ��ڣ�");
				}
				else//�������
				{
					goods go = new goods(goodsNumber.getText(),goodsName.getText(),manufacturer.getText(),dateOfProduction.getDate(),price.getText(),discount.getGoodsName(),inventory.getText(),
							productIntroduction.getText(),remarks.getText());
					goodsOperation.addGoods(go);
					goodsOperation.writeIn();
					JOptionPane.showMessageDialog(AddAndEditPanel.this, "��Ʒ��ӳɹ���");
					AddAndEditPanel.this.setVisible(false);
					AdminGoodsSearchPanel.lock.notifyAll();//��ת��
					//AddAndEditPanel.this.dispose();
				}
			}
		}
		});
	}
}

public class AdminGoodsSearchPanel extends JPanel{
	public static Object lock = new Object();//����ͬ�������
	//�����ֶ�
	private JPanel MainPanel,CenterPanel,CenterNorthPanel;
	private JScrollPane CenterScrollPane;
	
	private BorderLayout MainPanel_BorderLayout;//����
	private BorderLayout CenterPanel_BorderLayout;//����
	private FlowLayout CenterNorthPanel_FlowLayout;//����
	private GridLayout ThisPanel_GridLayout;//����
	
	private JLabel PageNameLabel;
	
	private JButton searchItemButton,DeleteButton,AddButton,EditButton;//��ť
	
	private JTextField searchItemTextField;
	
	private DefaultTableModel goodsInformation_TableModel;
	private JTable goodsInformation;
	private final Vector<String> TableNames;//  {"��Ʒ���","����","������","��������","�۸�","�ۿ���","���","��Ʒ���","��ע"};
	private Vector<Vector<String>> TableDatas;
	
	public AdminGoodsSearchPanel(AdminGUI gui)
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
		TableNames.add("��ע");
		
		TableDatas = goodsOperation.getAllGoods();
		
		goodsInformation_TableModel = new DefaultTableModel(TableDatas,TableNames){
			public boolean isCellEditable(int row, int column)
			{
				return false;
			}
		};
		goodsInformation = new JTable(goodsInformation_TableModel);//���ñ��ģʽ
		goodsInformation.setRowSelectionAllowed(true);//���Ƿ��ѡ
		goodsInformation.setSelectionBackground(new Color(140,189,239));//��ɫ
		goodsInformation.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);//ģʽ
		goodsInformation.setRowHeight(30);//�߶�
		goodsInformation.setRowMargin(5);//�߾�
		goodsInformation.setShowGrid(false);//����
		goodsInformation.setShowHorizontalLines(false);//ˮƽ��
		goodsInformation.setShowVerticalLines(false);//��ֱ��
		goodsInformation.getTableHeader().setBackground(new Color(19,146,249));
		goodsInformation.doLayout();
		goodsInformation.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		goodsInformation.setFillsViewportHeight(true);
		goodsInformation.getTableHeader().setReorderingAllowed(false);//���ñ��ͷ�����϶�
		
		PageNameLabel = new JLabel("�����Ʒ��Ϣ����");//ҳ���ǩ��ʼ��
		PageNameLabel.setForeground(new Color(19,146,249));
		PageNameLabel.setFont(PageNameLabel.getFont().deriveFont(30.0f));

		searchItemTextField = new JTextField("��������Ʒ���ƻ���Ʒ���",30);//�������ʼ��
		searchItemTextField.setBorder(BorderFactory.createLineBorder(new Color(140,189,239)));
		
		searchItemButton = new JButton("��ѯ");//�Ĵ�ť��ʼ��
		searchItemButton.setBorderPainted(false);
		searchItemButton.setBackground(new Color(140,189,239));
		
		AddButton = new JButton("���");//�Ĵ�ť��ʼ��
		AddButton.setBorderPainted(false);
		AddButton.setBackground(new Color(140,189,239));
		
		EditButton = new JButton("�༭");//�Ĵ�ť��ʼ��
		EditButton.setBorderPainted(false);
		EditButton.setBackground(new Color(140,189,239));
		EditButton.setEnabled(false);
		
		DeleteButton = new JButton("ɾ��");//�Ĵ�ť��ʼ��
		DeleteButton.setBorderPainted(false);
		DeleteButton.setBackground(new Color(140,189,239));
		DeleteButton.setEnabled(false);
		
		CenterNorthPanel = new JPanel();//����ʼ��
		CenterNorthPanel_FlowLayout = new FlowLayout();
		CenterNorthPanel.setLayout(CenterNorthPanel_FlowLayout);
		CenterNorthPanel.add(searchItemTextField);//���
		CenterNorthPanel.add(searchItemButton);//���
		CenterNorthPanel.add(AddButton);//���
		CenterNorthPanel.add(EditButton);//���
		CenterNorthPanel.add(DeleteButton);//���
		
		CenterScrollPane = new JScrollPane(goodsInformation);//��������ʼ��
		CenterScrollPane.setBorder(new EmptyBorder(0,0,0,0));
		
		CenterPanel = new JPanel();//����ʼ��
		CenterPanel_BorderLayout = new BorderLayout();
		CenterPanel.setLayout(CenterPanel_BorderLayout);
		CenterPanel.add(CenterNorthPanel,"North");
		CenterPanel.add(CenterScrollPane,"Center");
		
		MainPanel = new JPanel();//����ʼ��
		MainPanel_BorderLayout = new BorderLayout();
		MainPanel.setLayout(MainPanel_BorderLayout);
		MainPanel.add(PageNameLabel,"North");
		MainPanel.add(CenterPanel,"Center");
		MainPanel.setBorder(new EmptyBorder(14,14,0,0));
		
		ThisPanel_GridLayout = new GridLayout(1,1,0,0);
		this.setLayout(ThisPanel_GridLayout);//����Ĭ�ϲ���
		this.add(MainPanel);
		
		goodsInformation.addMouseListener(new MouseAdapter(){
            public void mouseReleased(MouseEvent e)//�����񣬰�ť����
            {
            	if(goodsInformation.getSelectedRow() != -1)
            	{
            		AdminGoodsSearchPanel.this.DeleteButton.setEnabled(true);
            		AdminGoodsSearchPanel.this.EditButton.setEnabled(true);
            	}
            }
        });
		
		this.addMouseListener(new MouseAdapter(){
            public void mousePressed(MouseEvent e)//�������񣬰�ťʧЧ
            {
            	AdminGoodsSearchPanel.this.DeleteButton.setEnabled(false);
            	AdminGoodsSearchPanel.this.EditButton.setEnabled(false);
            	goodsInformation.clearSelection();
            }
        });
		
		AddButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				AddAndEditPanel aaep = new AddAndEditPanel(gui,null);//��������������
			    Thread t = new Thread() 
			    {
			        public void run() 
			        {
			            synchronized(lock) //��������
			            {
			                while (aaep.isVisible())
			                {
			                	try 
			                	{
			                		lock.wait();//�̵߳ȴ�
			                	} 
			                	catch (InterruptedException e) 
			                	{
			                		e.printStackTrace();
			                	}
			                }
			                aaep.dispose();
			                try
							{
			                	if(searchItemTextField.getText().equals("��������Ʒ���ƻ���Ʒ���"))
			                		TableDatas = goodsOperation.getAllGoods();//ȫ����Ʒ
			                	else
			                	{
			                		TableDatas = goodsOperation.FindGoods(searchItemTextField.getText());
			                	}
							}
			                catch(Exception ex) {}//������Ʒ������Ϣ
							goodsInformation_TableModel = (DefaultTableModel)goodsInformation.getModel();
							goodsInformation_TableModel.setDataVector(TableDatas,TableNames);
							goodsInformation.setRowHeight(30);//���ñ��
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
			    Thread t = new Thread() //�����༭���
			    {
			        public void run() 
			        {
			            synchronized(lock) //��������
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
			                	if(searchItemTextField.getText().equals("��������Ʒ���ƻ���Ʒ���"))
			                		TableDatas = goodsOperation.getAllGoods();
			                	else
			                	{
			                		TableDatas = goodsOperation.FindGoods(searchItemTextField.getText());
			                	}
							}
			                catch(Exception ex) {}//������Ʒ��Ϣ
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
				{//ɾ��ѡ���еĵ�һ�ж�Ӧ����Ʒ����
				goodsOperation.DeleteGoods(goodsInformation.getValueAt(goodsInformation.getSelectedRow(), 1).toString(), goodsInformation.getValueAt(goodsInformation.getSelectedRow(), 0).toString());
				goodsOperation.writeIn();
				if(searchItemTextField.getText().equals("��������Ʒ���ƻ���Ʒ���"))
					TableDatas = goodsOperation.getAllGoods();//��ȡȫ������
				else
				{
					TableDatas = goodsOperation.FindGoods(searchItemTextField.getText());
				}
				JOptionPane.showMessageDialog(AdminGoodsSearchPanel.this, "ɾ���ɹ���");//����������Ϣ
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
				if(searchItemTextField.getText().equals("��������Ʒ���ƻ���Ʒ���"))
					TableDatas = goodsOperation.getAllGoods();//��ȡȫ������
				else
				{//��ȡ�ض���Ʒ
					TableDatas = goodsOperation.FindGoods(searchItemTextField.getText());
				}
				goodsInformation_TableModel = (DefaultTableModel)goodsInformation.getModel();//��ȡ��ģʽ
				goodsInformation_TableModel.setDataVector(TableDatas,TableNames);//��������
				goodsInformation.setRowHeight(30);//���±��
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
