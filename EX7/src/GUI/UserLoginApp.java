package GUI;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class UserLoginApp extends JFrame{
	private JPanel mainPanel,bottomPanel,gridPanel;//主面板中嵌套网格面板、底部面板
	
	private FlowLayout bottomPanelLayout;//底部面板采用流布局
	private GridLayout gridPanelLayout;//中间面板采用网格布局
	private BorderLayout mainPanelLayout;//主面板采用边界布局
	
	private JButton confirmButton,cancelButton,quitButton;//确定、取消、退出按钮
	
	private JLabel userTypeLabel,userNameLabel,passwordLabel;//配合选择的标签按钮
	
	private JTextField userNameText;//用户名文本框
	private JPasswordField passwordText;//密码框
	
	private JComboBox userTypeBox;//类型选择框
	
	public UserLoginApp()
	{
		//创建所有标签，并将标签居中、标注标签具体内容
		userTypeLabel = new JLabel("用户类型",JLabel.CENTER);
		userNameLabel = new JLabel("用户名：",JLabel.CENTER);
		passwordLabel = new JLabel("密码：",JLabel.CENTER);
		
		//在类型选择框中加入学生、教师选择选项
		userTypeBox = new JComboBox();
		userTypeBox.addItem("学生用户");
		userTypeBox.addItem("教师用户");
		userTypeBox.setSelectedIndex(0);//将学生用户选项作为缺省选项
		
		//创建用户名文本框、密码框
		userNameText = new JTextField();
		passwordText = new JPasswordField();
		
		//创建所有按钮
		confirmButton = new JButton("确定");
		cancelButton = new JButton("取消");
		quitButton = new JButton("退出");
		
		//创建底部面板，添加流式布局，添加底部按钮
		bottomPanel = new JPanel();
		bottomPanelLayout = new FlowLayout();
		bottomPanel.setLayout(bottomPanelLayout);
		bottomPanel.add(confirmButton);
		bottomPanel.add(cancelButton);
		bottomPanel.add(quitButton);
		
		//创建中间面便，设置网格布局，添加所有标签和所有文本框
		gridPanel = new JPanel();
		gridPanelLayout = new GridLayout(3,2,5,5);
		gridPanel.setLayout(gridPanelLayout);
		gridPanel.add(userTypeLabel);
		gridPanel.add(userTypeBox);
		gridPanel.add(userNameLabel);
		gridPanel.add(userNameText);
		gridPanel.add(passwordLabel);
		gridPanel.add(passwordText);
		
		//添加主面板
		mainPanel = new JPanel();
		mainPanelLayout = new BorderLayout();
		mainPanel.setLayout(mainPanelLayout);
		mainPanel.add(gridPanel,"Center");
		mainPanel.add(bottomPanel,"South");
		
		//设置内容面板
		this.setContentPane(mainPanel);
		
		//添加按钮的事件监听器
		confirmButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				if(userNameText.getText().equals(""))//用户名为空
				{
					JOptionPane.showMessageDialog(null, "用户名不可为空");
				}
				else if(String.valueOf(passwordText.getPassword()).equals(""))//密码框为空
				{
					JOptionPane.showMessageDialog(null, "密码不可为空");
				}
				else if(userNameText.getText().equals("s")&&String.valueOf(passwordText.getPassword()).equals("s")&&
						userTypeBox.getSelectedItem().toString().equals("学生用户"))//用户名密码正确且用户类型为学生
				{
					JOptionPane.showMessageDialog(null, "学生用户登陆成功", "恭喜", -1);
				}
				else if(userNameText.getText().equals("t")&&String.valueOf(passwordText.getPassword()).equals("t")&&
						userTypeBox.getSelectedItem().toString().equals("教师用户"))//用户名密码正确且用户类型为教师
				{
					JOptionPane.showMessageDialog(null, "教师用户登陆成功", "恭喜", -1);
				}
				else//其他所有情况
				{
					JOptionPane.showMessageDialog(null, "用户名不存在或者密码不正确！");
				}
			}
		});
		
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				userNameText.setText("");//文本框置空
				passwordText.setText("");
			}
		});
		
		quitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				System.exit(0);//完全退出系统
			}
		});
	}
	
	public static void main(String [] args)
	{
		UserLoginApp ula = new UserLoginApp();
		ula.setTitle("用户登录");
		ula.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ula.setSize(200, 160);
		ula.setVisible(true);
	}
	
}
