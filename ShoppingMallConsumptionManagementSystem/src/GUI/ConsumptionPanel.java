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
	private InformationItemAdmin goodsName;//组件组组件
	private InformationItemAdmin manufacturer;//组件组组件
	private InformationItemAdmin dateOfProduction;//组件组组件
	private InformationItemAdmin price;//组件组组件
	private InformationItemAdmin discount;//组件组组件
	private InformationItemAdmin productIntroduction;//组件组组件
	private JButton BasicInformationPanel_ConfirmButton;
	private JPanel BasicInformationPanel;
	private GridLayout BasicInformationPanel_GridLayout;
	private JPanel BasicInformationOUTPanel;
	
	goods go;
	
	public void CTRL(Guest gu)
	{
		this.setSize(650, 420);
		int windowWidth = this.getWidth(); //获得窗口宽
		int windowHeight = this.getHeight(); //获得窗口高
		Toolkit kit = Toolkit.getDefaultToolkit(); //定义工具包
		Dimension screenSize = kit.getScreenSize(); //获取屏幕的尺寸
		int screenWidth = screenSize.width; //获取屏幕的宽
		int screenHeight = screenSize.height; //获取屏幕的高
		this.setLocation(screenWidth / 2 - windowWidth / 2, screenHeight / 2 - windowHeight / 2);//设置窗口居中显示

		//面板更新
		AddAndEditLabel = new JLabel("商品款项");
		AddAndEditLabel.setBorder(new EmptyBorder(10,0,10,0));
		AddAndEditLabel.setFont(AddAndEditLabel.getFont().deriveFont(14.0f));

		this.setTitle("商品购买");
		//面板更新
		goodsName = new InformationItemAdmin("名称：",goodsOperation.getAllGoods());
		price = new InformationItemAdmin("价格：","",false);
		dateOfProduction = new InformationItemAdmin("生产日期：","",false);
		manufacturer = new InformationItemAdmin("制造商：","",false);
		discount = new InformationItemAdmin("折扣率：","",false);
		productIntroduction = new InformationItemAdmin("商品简介：","",false);
		//面板更新
		go = goodsOperation.GetExistenceGoods(goodsName.getGoodsName());
		price.setText(go.getPrice());  
		dateOfProduction.setText(go.getDateOfProduction());  
		manufacturer.setText(go.getManufacturer());  
		discount.setText(go.getDiscount());  
		productIntroduction.setText(go.getProductIntroduction());  
		//面板更新
		BasicInformationPanel_ConfirmButton = new JButton("确定");
		BasicInformationPanel_ConfirmButton.setBorderPainted(false);
		BasicInformationPanel_ConfirmButton.setBackground(new Color(140,189,239));
		//面板更新
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
		//面板更新
		BasicInformationOUTPanel = new JPanel();
		BasicInformationOUTPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		BasicInformationOUTPanel.add(BasicInformationPanel);
		
		this.setContentPane(BasicInformationOUTPanel);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setResizable(false);
		
		this.setVisible(true);
		
		goodsName.goodsName.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e)//组件更新
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
				//添加数据
				{					
					RecordsOfConsumption roc = new RecordsOfConsumption(go);
					gu.addGoods(roc);
					gu.writeIn();
					JOptionPane.showMessageDialog(ConsumptionPanel.this, "购买成功！消费记录已添加！");
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
