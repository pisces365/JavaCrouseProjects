package GUI;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class GuessNumber extends JFrame
{
	//��¼�������
	private int num;

	//�������Ͳ���
	private JPanel aPanel;
	private FlowLayout aPanel_FlowLayout;
	
	//������ť���ı��򡢱�ǩ
	private JButton button;
	 
	private JTextField text;
	
	private JLabel label;
	
	public GuessNumber()
	{
		num = (int)(Math.random()*100);
		//System.out.println(num); ���ں�̨�����������
		
		label = new JLabel("�����Ƕ����أ�");
		text = new JTextField("",20);
		button = new JButton("����");
		
		//�������Ӵ����õ����
		aPanel = new JPanel();
		aPanel_FlowLayout = new FlowLayout();
		aPanel.setLayout(aPanel_FlowLayout);
		aPanel.add(label);
		aPanel.add(text);
		aPanel.add(button);
		
		//��Ӱ�ť�ļ���
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				try//�����û�����ķ������ı������쳣����
				{
					if(Integer.parseInt(text.getText()) > 99 || Integer.parseInt(text.getText()) < 0)
					{
						label.setText("����������ֲ��ڷ�Χ�");//�ǵ�ǰ���ַ�Χ
					}
					else if(Integer.parseInt(text.getText()) > num )
					{
						label.setText("�����������̫������");
					}
					else if(Integer.parseInt(text.getText()) < num )
					{
						label.setText("�����������̫С����");
					}
					else 
					{
						label.setText("û��������־���" + num);
					}
				}
				catch(NumberFormatException ee)//�ı���ʽ�쳣
				{
					label.setText("������Ĳ�������Ŷ");
				}
			}
		});
		
		//�����������
		this.setContentPane(aPanel);
	}
	
	public static void main(String [ ] args)
	{
		GuessNumber gn = new GuessNumber();
		gn.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//����Ĭ�Ϲر�ģʽ
		gn.setSize(270, 130);
		gn.setVisible(true);
		gn.setTitle("������");
	}
}
