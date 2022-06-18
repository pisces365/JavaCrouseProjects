package MyPackage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.*;
//import java.awt.Container;
//import java.awt.GridLayout;
import java.awt.event.*;
import javax.swing.*;
public class Frame {

	static JMenuBar mb =new JMenuBar();
	static FgMenu mFile =new FgMenu("file",KeyEvent.VK_F);
	static JMenuItem miNew =new JMenuItem("new",KeyEvent.VK_N);

	JTextField front=new JTextField(" ",5);
	JTextField back=new JTextField(" ",5);
	JTextField result=new JTextField(" ",5);
	JButton jb= new JButton("equal");

	//public static void main(String args[]) {
		public void main1(String args[]) {
		Frame fr=new Frame();
		JFrame frm =new JFrame("MYframe");
		Container c=frm.getContentPane();
		c.setLayout(new GridLayout(1,6));
		
		frm.setJMenuBar(mb);
		mb.add(mFile);
		mFile.add(miNew);
		
		frm.setSize(500,500);
		frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frm.setVisible(true);
		

		
		JComponent[] JC= {fr.front,
						  new JLabel("+"),
						  fr.back,
						  new JLabel("="),
						  fr.result,
						  fr.jb
		};
		
		for(int i=0;i<JC.length;++i)
		{
			c.add(JC[i]);
		}

		//String a=front.getText();
		//String b=back.getText();
		//result.setText(a+b);

		String a=fr.front.getText();
		String b=fr.back.getText();
		fr.result.setText("3");


	}
	public void Click()
	{
		String a=front.getText();
		String b=back.getText();
		result.setText(a+b);
	}
	
}




