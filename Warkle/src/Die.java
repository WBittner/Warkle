import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JPanel;


public class Die extends JButton implements ActionListener
{
	private int Value;
	private boolean Keep;
	
	public Die()
	{
		reroll();
		Keep = false;
		addActionListener(this);
	}
	
	public Die(int n)
	{
		Value = n;
		Keep = false;
		addActionListener(this);
	}
	
	public int getValue()
	{
		return Value;
	}
	
	public void reroll()
	{
		Random rnd = new Random();
		Value = (rnd.nextInt(6)) +1;
	}
	
	public boolean getKeepStatus()
	{
		return Keep;
	}
	
	public void setKeep(boolean b)
	{
		Keep = b;
	}
	
	
	
	 @Override
	    public void paintComponent(Graphics g) {
	        super.paintComponent(g);

	       //g.drawString(""+Value, 20, 20);
	        setText(""+Value);
	        if(Keep)
	        	this.setBackground(Color.RED);
	        else
	        	this.setBackground(null);
	       
	    }


	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		Keep = !Keep;
		repaint();
	}

}
