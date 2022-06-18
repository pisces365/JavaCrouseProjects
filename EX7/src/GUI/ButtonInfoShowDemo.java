package GUI;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class ButtonInfoShowDemo extends JFrame{
	//����������ʽ����
	private JPanel aPanel;
	private FlowLayout aPanel_FlowLayout;
	
	//������ťb1��b2
	private JButton b1,b2;
	 
	//�����ı���
	private JTextField text;
	
	public ButtonInfoShowDemo()
	{
		//���а�ť���ı���ʵ����
		b1 = new JButton("b1");
		b2 = new JButton("b2");
		text = new JTextField("",20);
		
		//�������
		aPanel = new JPanel();
		//��ʽ����ʵ����
		aPanel_FlowLayout = new FlowLayout();//��ʽ����ʵ����
		//�����ʽ����
		aPanel.setLayout(aPanel_FlowLayout);
		//���������
		aPanel.add(b1);
		aPanel.add(b2);
		aPanel.add(text);
		
		//��Ӱ�ť1�ļ������������ť���ı�����ʵ��ť������
		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				text.setText(b1.getText());
			}
		});
		
		//��Ӱ�ť2�ļ������������ť���ı�����ʵ��ť������
		b2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				text.setText(b2.getText());
			}
		});
		
		//�����������
		this.setContentPane(aPanel);
	}
	
	public static void main(String args[])
	{
		ButtonInfoShowDemo bisd  = new ButtonInfoShowDemo();
		bisd.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//����Ĭ�Ϲر�ģʽ
		bisd.setSize(300, 100);
		bisd.setVisible(true);
	}
}
