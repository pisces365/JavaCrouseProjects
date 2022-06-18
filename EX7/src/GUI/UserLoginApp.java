package GUI;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class UserLoginApp extends JFrame{
	private JPanel mainPanel,bottomPanel,gridPanel;//�������Ƕ��������塢�ײ����
	
	private FlowLayout bottomPanelLayout;//�ײ�������������
	private GridLayout gridPanelLayout;//�м����������񲼾�
	private BorderLayout mainPanelLayout;//�������ñ߽粼��
	
	private JButton confirmButton,cancelButton,quitButton;//ȷ����ȡ�����˳���ť
	
	private JLabel userTypeLabel,userNameLabel,passwordLabel;//���ѡ��ı�ǩ��ť
	
	private JTextField userNameText;//�û����ı���
	private JPasswordField passwordText;//�����
	
	private JComboBox userTypeBox;//����ѡ���
	
	public UserLoginApp()
	{
		//�������б�ǩ��������ǩ���С���ע��ǩ��������
		userTypeLabel = new JLabel("�û�����",JLabel.CENTER);
		userNameLabel = new JLabel("�û�����",JLabel.CENTER);
		passwordLabel = new JLabel("���룺",JLabel.CENTER);
		
		//������ѡ����м���ѧ������ʦѡ��ѡ��
		userTypeBox = new JComboBox();
		userTypeBox.addItem("ѧ���û�");
		userTypeBox.addItem("��ʦ�û�");
		userTypeBox.setSelectedIndex(0);//��ѧ���û�ѡ����Ϊȱʡѡ��
		
		//�����û����ı��������
		userNameText = new JTextField();
		passwordText = new JPasswordField();
		
		//�������а�ť
		confirmButton = new JButton("ȷ��");
		cancelButton = new JButton("ȡ��");
		quitButton = new JButton("�˳�");
		
		//�����ײ���壬�����ʽ���֣���ӵײ���ť
		bottomPanel = new JPanel();
		bottomPanelLayout = new FlowLayout();
		bottomPanel.setLayout(bottomPanelLayout);
		bottomPanel.add(confirmButton);
		bottomPanel.add(cancelButton);
		bottomPanel.add(quitButton);
		
		//�����м���㣬�������񲼾֣�������б�ǩ�������ı���
		gridPanel = new JPanel();
		gridPanelLayout = new GridLayout(3,2,5,5);
		gridPanel.setLayout(gridPanelLayout);
		gridPanel.add(userTypeLabel);
		gridPanel.add(userTypeBox);
		gridPanel.add(userNameLabel);
		gridPanel.add(userNameText);
		gridPanel.add(passwordLabel);
		gridPanel.add(passwordText);
		
		//��������
		mainPanel = new JPanel();
		mainPanelLayout = new BorderLayout();
		mainPanel.setLayout(mainPanelLayout);
		mainPanel.add(gridPanel,"Center");
		mainPanel.add(bottomPanel,"South");
		
		//�����������
		this.setContentPane(mainPanel);
		
		//��Ӱ�ť���¼�������
		confirmButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				if(userNameText.getText().equals(""))//�û���Ϊ��
				{
					JOptionPane.showMessageDialog(null, "�û�������Ϊ��");
				}
				else if(String.valueOf(passwordText.getPassword()).equals(""))//�����Ϊ��
				{
					JOptionPane.showMessageDialog(null, "���벻��Ϊ��");
				}
				else if(userNameText.getText().equals("s")&&String.valueOf(passwordText.getPassword()).equals("s")&&
						userTypeBox.getSelectedItem().toString().equals("ѧ���û�"))//�û���������ȷ���û�����Ϊѧ��
				{
					JOptionPane.showMessageDialog(null, "ѧ���û���½�ɹ�", "��ϲ", -1);
				}
				else if(userNameText.getText().equals("t")&&String.valueOf(passwordText.getPassword()).equals("t")&&
						userTypeBox.getSelectedItem().toString().equals("��ʦ�û�"))//�û���������ȷ���û�����Ϊ��ʦ
				{
					JOptionPane.showMessageDialog(null, "��ʦ�û���½�ɹ�", "��ϲ", -1);
				}
				else//�����������
				{
					JOptionPane.showMessageDialog(null, "�û��������ڻ������벻��ȷ��");
				}
			}
		});
		
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				userNameText.setText("");//�ı����ÿ�
				passwordText.setText("");
			}
		});
		
		quitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				System.exit(0);//��ȫ�˳�ϵͳ
			}
		});
	}
	
	public static void main(String [] args)
	{
		UserLoginApp ula = new UserLoginApp();
		ula.setTitle("�û���¼");
		ula.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ula.setSize(200, 160);
		ula.setVisible(true);
	}
	
}
