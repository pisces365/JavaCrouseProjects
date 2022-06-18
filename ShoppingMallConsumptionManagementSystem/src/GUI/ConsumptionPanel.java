package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import goods.RecordsOfConsumption;
import goods.goods;
import goods.goodsOperation;
import people.Guest;

public class ConsumptionPanel extends JFrame
{
	private JLabel AddAndEditLabel;
	private InformationItemAdmin goodsName;//��������
	private InformationItemAdmin manufacturer;//��������
	private InformationItemAdmin dateOfProduction;//��������
	private InformationItemAdmin price;//��������
	private InformationItemAdmin discount;//��������
	private InformationItemAdmin productIntroduction;//��������
	private JButton BasicInformationPanel_ConfirmButton;
	private JPanel BasicInformationPanel;
	private GridLayout BasicInformationPanel_GridLayout;
	private JPanel BasicInformationOUTPanel;
	
	goods go;
	
	public void CTRL(Guest gu)
	{
		this.setSize(650, 420);
		int windowWidth = this.getWidth(); //��ô��ڿ�
		int windowHeight = this.getHeight(); //��ô��ڸ�
		Toolkit kit = Toolkit.getDefaultToolkit(); //���幤�߰�
		Dimension screenSize = kit.getScreenSize(); //��ȡ��Ļ�ĳߴ�
		int screenWidth = screenSize.width; //��ȡ��Ļ�Ŀ�
		int screenHeight = screenSize.height; //��ȡ��Ļ�ĸ�
		this.setLocation(screenWidth / 2 - windowWidth / 2, screenHeight / 2 - windowHeight / 2);//���ô��ھ�����ʾ

		//������
		AddAndEditLabel = new JLabel("��Ʒ����");
		AddAndEditLabel.setBorder(new EmptyBorder(10,0,10,0));
		AddAndEditLabel.setFont(AddAndEditLabel.getFont().deriveFont(14.0f));

		this.setTitle("��Ʒ����");
		//������
		goodsName = new InformationItemAdmin("���ƣ�",goodsOperation.getAllGoods());
		price = new InformationItemAdmin("�۸�","",false);
		dateOfProduction = new InformationItemAdmin("�������ڣ�","",false);
		manufacturer = new InformationItemAdmin("�����̣�","",false);
		discount = new InformationItemAdmin("�ۿ��ʣ�","",false);
		productIntroduction = new InformationItemAdmin("��Ʒ��飺","",false);
		//������
		go = goodsOperation.GetExistenceGoods(goodsName.getGoodsName());
		price.setText(go.getPrice());  
		dateOfProduction.setText(go.getDateOfProduction());  
		manufacturer.setText(go.getManufacturer());  
		discount.setText(go.getDiscount());  
		productIntroduction.setText(go.getProductIntroduction());  
		//������
		BasicInformationPanel_ConfirmButton = new JButton("ȷ��");
		BasicInformationPanel_ConfirmButton.setBorderPainted(false);
		BasicInformationPanel_ConfirmButton.setBackground(new Color(140,189,239));
		//������
		BasicInformationPanel = new JPanel();
		BasicInformationPanel_GridLayout = new GridLayout(0,1,5,5);
		BasicInformationPanel.setLayout(BasicInformationPanel_GridLayout);
		BasicInformationPanel.add(AddAndEditLabel);
		BasicInformationPanel.add(goodsName);
		BasicInformationPanel.add(price);
		BasicInformationPanel.add(dateOfProduction);
		BasicInformationPanel.add(manufacturer);
		BasicInformationPanel.add(discount);
		BasicInformationPanel.add(productIntroduction);
		BasicInformationPanel.add(BasicInformationPanel_ConfirmButton);
		//������
		BasicInformationOUTPanel = new JPanel();
		BasicInformationOUTPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		BasicInformationOUTPanel.add(BasicInformationPanel);
		
		this.setContentPane(BasicInformationOUTPanel);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setResizable(false);
		
		this.setVisible(true);
		
		goodsName.goodsName.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e)//�������
			{
				go = goodsOperation.GetExistenceGoods(goodsName.getGoodsName());
				price.setText(go.getPrice());  
				dateOfProduction.setText(go.getDateOfProduction());  
				manufacturer.setText(go.getManufacturer());  
				discount.setText(go.getDiscount());  
				productIntroduction.setText(go.getProductIntroduction());  
			}
		});
		
		BasicInformationPanel_ConfirmButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
			synchronized (AdminPurchaseHistoryPanel.add)
			{	
				//�������
				{					
					RecordsOfConsumption roc = new RecordsOfConsumption(go);
					gu.addGoods(roc);
					gu.writeIn();
					JOptionPane.showMessageDialog(ConsumptionPanel.this, "����ɹ������Ѽ�¼����ӣ�");
					ConsumptionPanel.this.setVisible(false);
					AdminPurchaseHistoryPanel.add.notifyAll();
				}
			}
		}
		});
	}
	
	public ConsumptionPanel(GuestGUI frame,Guest gu)
	{
		//super(frame);
		//setModal(true);
		CTRL(gu);
	}
	
	public ConsumptionPanel(AdminGUI frame,Guest gu)
	{
		//super(frame);
		//setModal(true);
		CTRL(gu);
	}
}
