package GUI;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class ButtonInfoShowDemo extends JFrame{
	//创建面板和流式布局
	private JPanel aPanel;
	private FlowLayout aPanel_FlowLayout;
	
	//创建按钮b1，b2
	private JButton b1,b2;
	 
	//创建文本框
	private JTextField text;
	
	public ButtonInfoShowDemo()
	{
		//进行按钮和文本的实例化
		b1 = new JButton("b1");
		b2 = new JButton("b2");
		text = new JTextField("",20);
		
		//创建面板
		aPanel = new JPanel();
		//流式布局实例化
		aPanel_FlowLayout = new FlowLayout();//流式布局实例化
		//添加流式布局
		aPanel.setLayout(aPanel_FlowLayout);
		//添加相关组件
		aPanel.add(b1);
		aPanel.add(b2);
		aPanel.add(text);
		
		//添加按钮1的监听器，点击按钮，文本框现实按钮的名字
		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				text.setText(b1.getText());
			}
		});
		
		//添加按钮2的监听器，点击按钮，文本框现实按钮的名字
		b2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				text.setText(b2.getText());
			}
		});
		
		//设置内容面板
		this.setContentPane(aPanel);
	}
	
	public static void main(String args[])
	{
		ButtonInfoShowDemo bisd  = new ButtonInfoShowDemo();
		bisd.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//设置默认关闭模式
		bisd.setSize(300, 100);
		bisd.setVisible(true);
	}
}
