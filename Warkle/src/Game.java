import java.util.ArrayList;

import javax.swing.JOptionPane;


public class Game {

	Die D1, D2, D3, D4, D5, D6;
	ArrayList<Die> Hand, Kept;
	int Score;
	boolean checkKeep;//farkled;
	
	public Game()
	{
		Hand = new ArrayList<Die>();
		Kept = new ArrayList<Die>();//
		D1 = new Die();
		Hand.add(D1);
		D2 = new Die();
		Hand.add(D2);
		D3 = new Die();
		Hand.add(D3);
		D4 = new Die();
		Hand.add(D4);
		D5 = new Die();
		Hand.add(D5);
		D6 = new Die();
		Hand.add(D6);
		Score = 0;
		attemptRoll();
		checkKeep=true;
		
	}
	
	//returns false if first roll farkles
	public boolean reset(boolean farkled)
	{
		D1.reroll();
		D2.reroll();
		D3.reroll();
		D4.reroll();
		D5.reroll();
		D6.reroll();
		
		D1.setEnabled(true);
		D2.setEnabled(true);
		D3.setEnabled(true);
		D4.setEnabled(true);
		D5.setEnabled(true);
		D6.setEnabled(true);
		
		Kept.clear();//
		
		D1.setKeep(false);
		D2.setKeep(false);
		D3.setKeep(false);
		D4.setKeep(false);
		D5.setKeep(false);
		D6.setKeep(false);
	
		if(farkled)
			Score = 0;
		checkKeep=false;
		boolean i = attemptRoll();
		checkKeep=true;
		return i;
	}
	
	
	public void roundOver(boolean farkled)
	{
		for(int i = 0; i < Hand.size(); i++)
		{
			Hand.get(i).setEnabled(false);
		}
		
		addSubtotalToScore();
		
		if(farkled)
			JOptionPane.showMessageDialog(null, "Points missed out on this round: " + Score + " - Farkled!");
		else
			JOptionPane.showMessageDialog(null, "Points earned this round: " + Score + " - Playin it safe!");
		
	}
	
	
	public int addSubtotalToScore()
	{
		int count = 0;
		
		int ones = 0,twos = 0,threes = 0,fours = 0,fives = 0,sixes = 0;
		for(Die d:Hand)
		{
			if(d.getKeepStatus())
			switch(d.getValue())
			{
			case 1: ones++; break;
			case 2: twos++; break;
			case 3: threes++; break;
			case 4: fours++; break;
			case 5: fives++; break;
			case 6: sixes++; break;
			default: System.out.println("Wtf?!");
			}
		}
		
		int numCounted = ones+twos+threes+fours+fives+sixes;
		
		if(ones<3)
			count+=ones*100;
		else if(ones == 3)
			count+=1000;
		else if(ones == 4)
			count+=2000;
		else if(ones == 5)
			count+=4000;
		else if(ones == 6)
			count+=8000;
		
		if(fives<3)
			count+=fives*50;
		else if(fives == 3)
			count+=500;
		else if(fives == 4)
			count+=1000;
		else if(fives == 5)
			count+=2000;
		else if(fives == 6)
			count+=4000;
	
		if(twos == 3)
			count+=200;
		else if(twos == 4)
			count+=400;
		else if(twos == 5)
			count+=800;
		else if(twos == 6)
			count+=1600;
		
		if(threes == 3)
			count+=300;
		else if(threes == 4)
			count+=600;
		else if(threes == 5)
			count+=1200;
		else if(threes == 6)
			count+=2400;
		
		if(fours == 3)
			count+=400;
		else if(fours == 4)
			count+=800;
		else if(fours == 5)
			count+=1600;
		else if(fours == 6)
			count+=3200;
		
		if(sixes == 3)
			count+=600;
		else if(sixes == 4)
			count+=1200;
		else if(sixes == 5)
			count+=2400;
		else if(sixes == 6)
			count+=4800;
		
		if(ones==1&&twos==1&&threes==1&&fours==1&&fives==1&&sixes==1)
			count = 1000;
		
		Score += count;
		return count;
	}
	//only works for basic 1s, 5s, and 3 of a kind
	public boolean keepsOk()
	{
		//new
		int ones = 0,twos = 0,threes = 0,fours = 0,fives = 0,sixes = 0,
				//kept
				kones = 0,ktwos = 0,kthrees = 0,kfours = 0,kfives = 0,ksixes = 0;
		for(Die d:Hand)
		{
			if(d.getKeepStatus())
			switch(d.getValue())
			{
			case 1: ones++; break;
			case 2: twos++; break;
			case 3: threes++; break;
			case 4: fours++; break;
			case 5: fives++; break;
			case 6: sixes++; break;
			default: System.out.println("Wtf?!");
			}
		}
		for(Die d:Kept)
		{
			switch(d.getValue())
			{
			case 1: kones++; break;
			case 2: ktwos++; break;
			case 3: kthrees++; break;
			case 4: kfours++; break;
			case 5: kfives++; break;
			case 6: ksixes++; break;
			default: System.out.println("Wtf?!");
			}
		}
		//if straight on same roll - YES!
		if(ones==1&&twos==1&&threes==1&&fours==1&&fives==1&&sixes==1&&Kept.isEmpty())
		{
			System.out.println("Hello");
			return true;
		}
		
		
		//if theres 1 or 2 of any non 1/5 dice - NO!
		if((twos>0&&twos<3)||(fours>0&&fours<3)||(threes>0&&threes<3)||(sixes>0&&sixes<3))
			return false;
		//if theres no new keeps - NO!
		if(checkKeep&& (ones+twos+threes+fours+fives+sixes)==Kept.size())
			return false;
		
		return true;
	}
	
	
	//called from roll button
	public boolean attemptRoll()
	{
		int count = 0;
		if(keepsOk())
		{
			for(Die d:Hand)
			{
				//if not keep
				if(!d.getKeepStatus())
				{
					count++;
					d.reroll();
				}
				//if keep
				else
				{
					d.setEnabled(false);
					if(!Kept.contains(d))
						Kept.add(d);
				}
			}
			//if some dice rolled
			if(count>0)
			{
				if(Farkled())
				{
					roundOver(true);
								
					//false == round over;
					return false;
				}
			}
			//if no dice rolled
			else if(count==0)
			{
				addSubtotalToScore();
				
				JOptionPane.showMessageDialog(null, "All 6 dice!? That's a refresh! Current subtotal: " + Score + " - Nice!");
				reset(false);	
			}
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Invalid Keeps!");
			return true;
		}
		
		return true;
	}
		
	public boolean Farkled()
	{
		//new
		int ones = 0,twos = 0,threes = 0,fours = 0,fives = 0,sixes = 0,
				//kept
				kones = 0,ktwos = 0,kthrees = 0,kfours = 0,kfives = 0,ksixes = 0;
		
		for(Die d:Hand)
		{
			if(!d.getKeepStatus())
				switch(d.getValue())
				{
				case 1: ones++; break;
				case 2: twos++; break;
				case 3: threes++; break;
				case 4: fours++; break;
				case 5: fives++; break;
				case 6: sixes++; break;
				default: System.out.println("Wtf?!");
				}
		}
		for(Die d:Kept)
		{
			switch(d.getValue())
			{
			case 1: kones++; break;
			case 2: ktwos++; break;
			case 3: kthrees++; break;
			case 4: kfours++; break;
			case 5: kfives++; break;
			case 6: ksixes++; break;
			default: System.out.println("Wtf?!");
			}
		}
		
			
		if(ones>0)
			return false;
		else if(fives>0)
			return false;
		else if(twos>2 || (twos>0&& ktwos>0))
			return false;
		else if(threes>2 || (threes>0&& kthrees>0))
			return false;
		else if(fours>2 || (fours>0&& kfours>0))
			return false;
		else if(sixes>2 || (sixes>0&& ksixes>0))
			return false;
		//straight
		else if(ones==1&&twos==1&&threes==1&&fours==1&&fives==1&&sixes==1)
			return false;


		return true;
		
	}
	
	/*public ArrayList<ArrayList<Die>> getDiceInfo()
	{
		ArrayList<ArrayList<Die>> dice = new ArrayList<ArrayList<Die>>();
		ArrayList<Die> notKept = new ArrayList<Die>();
		ArrayList<Die> newKept = new ArrayList<Die>();
		ArrayList<Die> oldKept = new ArrayList<Die>();
		
		for(Die d:Hand)
		{
			if(!d.getKeepStatus())
				switch(d.getValue())
				{
				case 1: ones++; break;
				case 2: twos++; break;
				case 3: threes++; break;
				case 4: fours++; break;
				case 5: fives++; break;
				case 6: sixes++; break;
				default: System.out.println("Wtf?!");
			}
		}
		for(Die d:Kept)
		{
				switch(d.getValue())
				{
				case 1: kones++; break;
				case 2: ktwos++; break;
				case 3: kthrees++; break;
				case 4: kfours++; break;
				case 5: kfives++; break;
				case 6: ksixes++; break;
				default: System.out.println("Wtf?!");
				}
		}
		
		return dice;
	}*/
	
}
