package MyPackage;
import java.awt.event.*;
import javax.swing.*;
public class FgMenu extends JMenu{
	public FgMenu(String label)
	{
		super(label);
	}
	public FgMenu(String label,int n)
	{
		super(label);
		setMnemonic(n);
	}
}
