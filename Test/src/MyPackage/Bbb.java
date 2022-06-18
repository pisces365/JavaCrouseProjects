package MyPackage;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


interface my
{
	
}

class aa implements my
{
	public static void main(String [] args)
	{
		
	}
}

public class Bbb extends aa{
	public static void main(String [] args)
	{
		JFrame jf = new JFrame();
		JPanel jp = new JPanel();
		JButton j1 = new JButton("123");
		JButton j2 = new JButton("1234");
		//jp.setLayout(new BorderLayout());
		jp.add(j1);
		jp.add(j2);
		jf.setContentPane(jp);
		jf.setVisible(true);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
