package GUI;

import java.awt.*;
import java.util.Vector;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class InformationItemAdmin extends JPanel
{
	private JLabel name;
	private JTextField text;
	private BoxLayout ThisPanelBoxLayout;
	private JPasswordField password;
	private JRadioButton man,woman;
	private ButtonGroup RadioButtonGroup;
	public JComboBox goodsName;
	
	private JComboBox[] date; 
	
	public String getDate()
	{
		return date[0].getSelectedItem().toString()+"-"+date[1].getSelectedItem().toString()+"-"+date[2].getSelectedItem().toString();
	}
	
	public void setText(String para)
	{
		text.setText(para);
	}
	
	public void setPassword(String para)
	{
		password.setText(para);
	}
	
	public String getText()
	{
		return text.getText();
	}
	
	public String getPassword()
	{
		if(String.valueOf(password.getPassword()).equals(""))
		{
			return null;
		}
		return String.valueOf(password.getPassword());
	}
	
	public String getSex()
	{
		if(man.isSelected() == true)
		{
			return "男";
		}
		else
		{
			return "女";
		}
	}
	
	public String getGoodsName()
	{
		return goodsName.getSelectedItem().toString();
	}
	
	
	public InformationItemAdmin(String JLabelName,double noMeaning,int discount)
	{
		name = new JLabel(JLabelName);
		name.setPreferredSize(new Dimension(100,20));
		this.goodsName = new JComboBox();
		for(int i=5;i<101;i=i+5)
		{
			this.goodsName.addItem(i);
		}
		this.goodsName.setPreferredSize(new Dimension(500,20));
		
		this.goodsName.setSelectedItem(discount);
		
		ThisPanelBoxLayout = new BoxLayout(this,BoxLayout.X_AXIS);
		this.setLayout(ThisPanelBoxLayout);
				
		this.add(name);
		this.add(this.goodsName);
	}
	
	public InformationItemAdmin(String JLabelName,Vector<Vector<String>> goodsName)
	{
		name = new JLabel(JLabelName);
		name.setPreferredSize(new Dimension(100,20));
		this.goodsName = new JComboBox();
		for(int i=0;i<goodsName.size();++i)
		{
			this.goodsName.addItem(goodsName.get(i).get(1));
		}
		this.goodsName.setPreferredSize(new Dimension(500,20));
		
		ThisPanelBoxLayout = new BoxLayout(this,BoxLayout.X_AXIS);
		this.setLayout(ThisPanelBoxLayout);
				
		this.add(name);
		this.add(this.goodsName);
	}
	
	public InformationItemAdmin(String JLabelName,String manORwoman,int JRadioButton)
	{
		name = new JLabel(JLabelName + "                   ");
		man = new JRadioButton("男");
		man.setSelected(true);
		woman = new JRadioButton("女");
		woman.setSelected(false);
		if(manORwoman.equals("男"))
		{
			man.setSelected(true);
			woman.setSelected(false);
		}
		if(manORwoman.equals("女"))
		{
			man.setSelected(false);
			woman.setSelected(true);
		}
		RadioButtonGroup = new ButtonGroup();
		RadioButtonGroup.add(man);
		RadioButtonGroup.add(woman);
		
		ThisPanelBoxLayout = new BoxLayout(this,BoxLayout.X_AXIS);
		this.setLayout(ThisPanelBoxLayout);
				
		this.add(name);
		this.add(man);
		this.add(woman);
	}
	
	public InformationItemAdmin(String JLabelName,String three,double n)//用于选择日期的构造函数
	{
		name = new JLabel(JLabelName);
		name.setPreferredSize(new Dimension(100,20));
		String split[] = three.split("[-]");
		String datesName[] = {"年","月","日"};
		date = new JComboBox[3];
		for(int i=0;i<3;++i)
		{
			date[i] = new JComboBox();
			date[i].setPreferredSize(new Dimension(140,20));
		}
		
		for(int i=1999;i<2040;++i)
			date[0].addItem(i);
		
		for(int i=1;i<13;++i)
			date[1].addItem(i);
		
		if(split[1].equals("1")||split[1].equals("3")||split[1].equals("5")||split[1].equals("7")||
				split[1].equals("8")||split[1].equals("10")||split[1].equals("12"))
		{
			for(int i=1;i<32;++i)
				date[2].addItem(i);
		}
		else if(split[1].equals("2"))
		{
			if((Integer.parseInt(split[0])%4==0&&Integer.parseInt(split[0])%100!=0)
					||Integer.parseInt(split[0])%400==0)
			{
				for(int i=1;i<29;++i)
					date[2].addItem(i);
				
			}
			else
			{
				for(int i=1;i<30;++i)
					date[2].addItem(i);
			}
		}
		else
		{
			for(int i=1;i<31;++i)
				date[2].addItem(i);
		}
		
		date[0].setSelectedIndex(Integer.parseInt(split[0])-1999);
		date[1].setSelectedIndex(Integer.parseInt(split[1])-1);
		date[2].setSelectedIndex(Integer.parseInt(split[2])-1);
		
		ThisPanelBoxLayout = new BoxLayout(this,BoxLayout.X_AXIS);
		this.setLayout(ThisPanelBoxLayout);
				
		this.add(name);
		for(int i=0;i<3;++i)
		{
			JLabel jl = new JLabel(datesName[i]);
			jl.setBorder(new EmptyBorder(0,5,0,5));
			this.add(date[i]);
			this.add(jl);
		}
		
		//监听
		date[1].addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e)
			{
				date[2].removeAllItems();
				if(date[1].getSelectedItem().toString().equals("1")||date[1].getSelectedItem().toString().equals("3")||date[1].getSelectedItem().toString().equals("5")||date[1].getSelectedItem().toString().equals("7")||
						date[1].getSelectedItem().toString().equals("8")||date[1].getSelectedItem().toString().equals("10")||date[1].getSelectedItem().toString().equals("12"))
				{
					for(int i=1;i<32;++i)
						date[2].addItem(i);
				}
				else if(date[1].getSelectedItem().toString().equals("2"))
				{
					if((Integer.parseInt(date[0].getSelectedItem().toString())%4==0&&Integer.parseInt(date[0].getSelectedItem().toString())%100!=0)
							||Integer.parseInt(date[0].getSelectedItem().toString())%400==0)
					{
						for(int i=1;i<29;++i)
							date[2].addItem(i);
					}
					else
					{
						for(int i=1;i<30;++i)
							date[2].addItem(i);
					}
				}
				else
				{
					for(int i=1;i<31;++i)
						date[2].addItem(i);
				}
			}
		});
		
		date[0].addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e)
			{
				date[2].removeAllItems();
				if(date[1].getSelectedItem().toString().equals("1")||date[1].getSelectedItem().toString().equals("3")||date[1].getSelectedItem().toString().equals("5")||date[1].getSelectedItem().toString().equals("7")||
						date[1].getSelectedItem().toString().equals("8")||date[1].getSelectedItem().toString().equals("10")||date[1].getSelectedItem().toString().equals("12"))
				{
					for(int i=1;i<32;++i)
						date[2].addItem(i);
				}
				else if(date[1].getSelectedItem().toString().equals("2"))
				{
					if((Integer.parseInt(date[0].getSelectedItem().toString())%4==0&&Integer.parseInt(date[0].getSelectedItem().toString())%100!=0)
							||Integer.parseInt(date[0].getSelectedItem().toString())%400==0)
					{
						for(int i=1;i<29;++i)
							date[2].addItem(i);
					}
					else
					{
						for(int i=1;i<30;++i)
							date[2].addItem(i);
					}
				}
				else
				{
					for(int i=1;i<31;++i)
						date[2].addItem(i);
				}
			}
		});
	}
	
	public InformationItemAdmin(String JLabelName,int noText)
	{
		name = new JLabel(JLabelName);
		name.setPreferredSize(new Dimension(100,20));
		text = new JTextField();
		text.setPreferredSize(new Dimension(500,20));
		
		ThisPanelBoxLayout = new BoxLayout(this,BoxLayout.X_AXIS);
		this.setLayout(ThisPanelBoxLayout);
				
		this.add(name);
		this.add(text);
	}
	
	public InformationItemAdmin(String JLabelName, String JTextFieldInfo)
	{
		name = new JLabel(JLabelName);
		name.setPreferredSize(new Dimension(100,20));
		text = new JTextField(JTextFieldInfo);
		text.setPreferredSize(new Dimension(500,20));
		
		ThisPanelBoxLayout = new BoxLayout(this,BoxLayout.X_AXIS);
		this.setLayout(ThisPanelBoxLayout);
				
		this.add(name);
		this.add(text);
	}
	
	public InformationItemAdmin(String JLabelName)
	{
		name = new JLabel(JLabelName);
		password = new JPasswordField();
		name.setPreferredSize(new Dimension(100,20));
		password.setPreferredSize(new Dimension(500,20));
		
		ThisPanelBoxLayout = new BoxLayout(this,BoxLayout.X_AXIS);
		this.setLayout(ThisPanelBoxLayout);
		
		this.add(name);
		this.add(password);
	}
	
	public InformationItemAdmin(String JLabelName, String JTextFieldInfo, boolean isEditable)
	{
		name = new JLabel(JLabelName);
		text = new JTextField(JTextFieldInfo);
		text.setEditable(isEditable);
		name.setPreferredSize(new Dimension(100,20));
		text.setPreferredSize(new Dimension(500,20));
		
		ThisPanelBoxLayout = new BoxLayout(this,BoxLayout.X_AXIS);
		this.setLayout(ThisPanelBoxLayout);
		
		this.add(name);
		this.add(text);
	}
}
