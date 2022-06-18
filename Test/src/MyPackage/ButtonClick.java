package MyPackage;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class ButtonClick {

	public String num;
	public JButton Btn;
	
	public ButtonClick(String num) {
		this.num = num;
		Btn = new JButton(num);
		Btn.addActionListener(new myTEXT(num));

	}

	public static void main (String[] args)
	{
		JFrame Window = new JFrame("¼ÆËãÆ÷");
		Window.setSize(500,500);
		Window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Window.setVisible(true);
		Container c=Window.getContentPane();
		c.setLayout(new GridLayout(5,4));
		
		ButtonClick bc[]= {new ButtonClick("1"),new ButtonClick("2"),new ButtonClick("3"),new ButtonClick("4"),new ButtonClick("5"),new ButtonClick("6"),new ButtonClick("7"),new ButtonClick("8"),new ButtonClick("9")};
		c.add(myTEXT.piscesText);
		for(int i=0;i<bc.length;++i)
		c.add(bc[i].Btn);
		
	}
	
}
