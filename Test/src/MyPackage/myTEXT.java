package MyPackage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

public class myTEXT implements ActionListener{
	public static JTextField piscesText= new JTextField("0");
	String num;
	public myTEXT(String num)
	{
		this.num=num;
	}
	public void actionPerformed(ActionEvent e)
	{
		
		piscesText.setText(piscesText.getText()+num);
	}
	
	
}
