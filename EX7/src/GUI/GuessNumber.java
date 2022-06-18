package GUI;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class GuessNumber extends JFrame
{
	//记录随机数字
	private int num;

	//创建面板和布局
	private JPanel aPanel;
	private FlowLayout aPanel_FlowLayout;
	
	//创建按钮、文本框、标签
	private JButton button;
	 
	private JTextField text;
	
	private JLabel label;
	
	public GuessNumber()
	{
		num = (int)(Math.random()*100);
		//System.out.println(num); 用于后台测试随机数据
		
		label = new JLabel("数字是多少呢？");
		text = new JTextField("",20);
		button = new JButton("对吗？");
		
		//面板中添加创建好的组件
		aPanel = new JPanel();
		aPanel_FlowLayout = new FlowLayout();
		aPanel.setLayout(aPanel_FlowLayout);
		aPanel.add(label);
		aPanel.add(text);
		aPanel.add(button);
		
		//添加按钮的监听
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				try//对于用户输入的非数字文本进行异常处理
				{
					if(Integer.parseInt(text.getText()) > 99 || Integer.parseInt(text.getText()) < 0)
					{
						label.setText("你输入的数字不在范围里！");//非当前数字范围
					}
					else if(Integer.parseInt(text.getText()) > num )
					{
						label.setText("你输入的数字太大啦！");
					}
					else if(Integer.parseInt(text.getText()) < num )
					{
						label.setText("你输入的数字太小啦！");
					}
					else 
					{
						label.setText("没错！这个数字就是" + num);
					}
				}
				catch(NumberFormatException ee)//文本格式异常
				{
					label.setText("你输入的不是数字哦");
				}
			}
		});
		
		//设置内容面板
		this.setContentPane(aPanel);
	}
	
	public static void main(String [ ] args)
	{
		GuessNumber gn = new GuessNumber();
		gn.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//设置默认关闭模式
		gn.setSize(270, 130);
		gn.setVisible(true);
		gn.setTitle("猜数字");
	}
}
