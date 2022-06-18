package GUI;

import Login.*;
import people.UserOperation;

import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.awt.*;
import javax.swing.*;

public class LoginGUI extends JFrame 
{
	
	
	private JPanel CenterLoginPanel,JFramePanel,MainPanel;//面板
	private FlowLayout JFramePanelFlowLayout;//布局
	private GridLayout CenterLoginPanelGridLayout;//布局
	private BorderLayout MainPanelBorderLayout;//布局
	
	private JComboBox CenterLoginPanel_UserTypeComboBox;//选择框
	private JLabel CenterLoginPanel_UserTypeLabel,CenterLoginPanel_UserNameLabel, CenterLoginPanel_PasswordLabel;
	private JTextField CenterLoginPanel_UserNameTextField;//选择框
	private JPasswordField CenterLoginPanel_PasswordTextField;//选择框
	private JButton CenterLoginPanel_LoginButton;//选择框
	
	private JLabel MainPanel_TitleLabel;
	private ImageIcon MainPanel_BackgroundImage;
	private JLabel JLayeredPaneANDMainPanel_backgroundLabel;
	
	private JLayeredPane RecordMainPanel_LayeredPane;
	
	public LoginGUI()
	{
		setSize(500,400);
		int windowWidth = this.getWidth(); //获得窗口宽
		int windowHeight = this.getHeight(); //获得窗口高
		Toolkit kit = Toolkit.getDefaultToolkit(); //定义工具包
		Dimension screenSize = kit.getScreenSize(); //获取屏幕的尺寸
		int screenWidth = screenSize.width; //获取屏幕的宽
		int screenHeight = screenSize.height; //获取屏幕的高
		this.setLocation(screenWidth / 2 - windowWidth / 2, screenHeight / 2 - windowHeight / 2);//设置窗口居中显示
		
		CenterLoginPanel = new JPanel();
		CenterLoginPanelGridLayout = new GridLayout(7,1,20,15);
		CenterLoginPanel.setLayout(CenterLoginPanelGridLayout);
		CenterLoginPanel.add(CenterLoginPanel_UserTypeLabel = new JLabel("用户类型："));
		CenterLoginPanel.add(CenterLoginPanel_UserTypeComboBox = new JComboBox());
		CenterLoginPanel_UserTypeComboBox.addItem("普通用户");
		CenterLoginPanel_UserTypeComboBox.addItem("管理员");
		CenterLoginPanel.add(CenterLoginPanel_UserNameLabel = new JLabel("用户名："));
		CenterLoginPanel.add(CenterLoginPanel_UserNameTextField = new JTextField("请输入用户名",20));
		CenterLoginPanel_UserNameTextField.addFocusListener(new FocusAdapter() {
			public void focusLost(FocusEvent e)
			{
				if(CenterLoginPanel_UserNameTextField.getText().equals(""))
					CenterLoginPanel_UserNameTextField.setText("请输入用户名");//点击和不点击
			}
			
			public void focusGained(FocusEvent e)//点击和不点击
			{
				if(CenterLoginPanel_UserNameTextField.getText().equals("请输入用户名"))
					CenterLoginPanel_UserNameTextField.setText("");
				
			}
		});
		CenterLoginPanel.add(CenterLoginPanel_PasswordLabel = new JLabel("密码："));
		CenterLoginPanel.add(CenterLoginPanel_PasswordTextField = new JPasswordField("请输入密码",20));
		CenterLoginPanel_PasswordTextField.setEchoChar('\0');
		CenterLoginPanel_PasswordTextField.addFocusListener(new FocusAdapter() {
			public void focusLost(FocusEvent e)
			{
				if(String.valueOf(CenterLoginPanel_PasswordTextField.getPassword()).equals(""))
				{
					CenterLoginPanel_PasswordTextField.setEchoChar('\0');//点击和不点击
					CenterLoginPanel_PasswordTextField.setText("请输入密码");//点击和不点击
				}
			}
			
			public void focusGained(FocusEvent e)
			{
				if(String.valueOf(CenterLoginPanel_PasswordTextField.getPassword()).equals("请输入密码"))
				{
					CenterLoginPanel_PasswordTextField.setEchoChar('•');//点击和不点击
					CenterLoginPanel_PasswordTextField.setText("");//点击和不点击
				}
			}
		});
		CenterLoginPanel.add(CenterLoginPanel_LoginButton = new JButton("登录"));
		CenterLoginPanel_LoginButton.setBackground(new Color(163,184,204));
		CenterLoginPanel.setOpaque(false);
		CenterLoginPanel_LoginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				if(CenterLoginPanel_UserTypeComboBox.getSelectedIndex() == 0 && Login.isLoginInformationCorrect(CenterLoginPanel_UserNameTextField.getText(), String.valueOf(CenterLoginPanel_PasswordTextField.getPassword())) == true)
				{
					LoginGUI.this.dispose();
					MainClass mm = new MainClass();//页面设置
					mm.BuildGuestGUI(CenterLoginPanel_UserNameTextField.getText());
				}
				else if(CenterLoginPanel_UserTypeComboBox.getSelectedIndex() == 1 && Login.isAdminLoginInformationCorrect(CenterLoginPanel_UserNameTextField.getText(), String.valueOf(CenterLoginPanel_PasswordTextField.getPassword())) == true)
				{
					LoginGUI.this.dispose();
					MainClass mm = new MainClass();//页面设置
					mm.BuildAdminGUI(CenterLoginPanel_UserNameTextField.getText());
				}
				else
				{
					JOptionPane.showMessageDialog(LoginGUI.this, "用户名或密码错误！请检查后重试/_ \\", "商场VIP消费管理系统-提示", 1);
				}
			}
		});
		
		
		
		//页面设置
		JFramePanel = new JPanel();
		JFramePanelFlowLayout = new FlowLayout(FlowLayout.CENTER,30,30);
		JFramePanel.setLayout(JFramePanelFlowLayout);
		JFramePanel.add(CenterLoginPanel);
		JFramePanel.setOpaque(false);
		
		//页面设置
		MainPanel = new JPanel();
		MainPanelBorderLayout = new BorderLayout();
		MainPanel.setLayout(MainPanelBorderLayout);
		MainPanel.add(MainPanel_TitleLabel = new JLabel("商场VIP消费管理系统",JLabel.CENTER),"North");
		MainPanel_TitleLabel.setFont(MainPanel_TitleLabel.getFont().deriveFont(18.0f));
		MainPanel.add(JFramePanel,"Center");
		MainPanel.setOpaque(false);
		try
		{
			MainPanel_BackgroundImage = new ImageIcon(new File("").getCanonicalPath()+"/image/MainFrameBackground.png");
		}
		catch(IOException ioe) {}
		MainPanel.setBounds(0, 0, 500, 400);
		//标签
		JLayeredPaneANDMainPanel_backgroundLabel = new JLabel(MainPanel_BackgroundImage);
		JLayeredPaneANDMainPanel_backgroundLabel.setBounds(0, 0, 500, 400);
		//面板设置
		RecordMainPanel_LayeredPane = new JLayeredPane();
		RecordMainPanel_LayeredPane.add(JLayeredPaneANDMainPanel_backgroundLabel,JLayeredPane.DEFAULT_LAYER); 
		RecordMainPanel_LayeredPane.add(MainPanel,JLayeredPane.MODAL_LAYER); 
		setContentPane(RecordMainPanel_LayeredPane);
		
		try 
		{
			//windows.WindowsLookAndFeel
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NumbusLookAndFeel");
			SwingUtilities.updateComponentTreeUI(this);
		}
		catch(Exception e) {}
		

	}
}
