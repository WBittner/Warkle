import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;


public class GuiView implements ActionListener{
	

	Game game;
	JFrame mainFrame;
	
	JPanel dicePanel;
	JButton rollButton;
	JButton endButton;

	
	public void createGui()
	{
		mainFrame = new JFrame("Warkle");
		
		JPanel mainPanel = new JPanel(new BorderLayout());
		dicePanel = new JPanel();
		JPanel buttonPanel = new JPanel(new BorderLayout());
		
		rollButton = new JButton("Roll the Di(c)e!");
		rollButton.setActionCommand("roll");
		rollButton.addActionListener(this);
		endButton = new JButton("End the Turn.");
		endButton.setActionCommand("end");
		endButton.addActionListener(this);
		JButton resetButton = new JButton("Reset!");
		resetButton.setActionCommand("reset");
		resetButton.addActionListener(this);
		
		buttonPanel.add(rollButton, BorderLayout.NORTH);
		buttonPanel.add(resetButton, BorderLayout.CENTER);
		buttonPanel.add(endButton, BorderLayout.SOUTH);
		
		mainPanel.add(dicePanel, BorderLayout.CENTER);
		mainPanel.add(buttonPanel, BorderLayout.EAST);
		mainFrame.add(mainPanel);
		
	
		
		game = new Game();
		
		for(int i = 0; i < game.Hand.size(); i++)
		{
			dicePanel.add(game.Hand.get(i));
		}


		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setBounds(500, 300, 500, 120);
		//mainFrame.pack();
		mainFrame.setVisible(true);
	}
	
	
	//isnt checked for first roll...need a new way of accessing gui
	public void actionPerformed(ActionEvent e)
	{
		if("roll".equals(e.getActionCommand()))
		{
			if(!game.attemptRoll())
			{
				rollButton.setEnabled(false);
				endButton.setEnabled(false);
			}
		}
		if("reset".equals(e.getActionCommand()))
		{
			if(game.reset(true))
			{
				rollButton.setEnabled(true);
				endButton.setEnabled(true);
				//mainFrame.repaint();
			}
			else
			{
				rollButton.setEnabled(false);
				endButton.setEnabled(false);
		
			}
		}
		if("end".equals(e.getActionCommand()))
		{
			game.roundOver(false);
			rollButton.setEnabled(false);
			endButton.setEnabled(false);
			
		}
	}
	
	
	
	
}
