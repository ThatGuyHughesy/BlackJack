//Blackjack game between player and dealer.
//@author Conor Hughes

import javax.swing.JOptionPane;
import java.util.Random;

class Deck
{
	public static void main (String [] args)
	{
		User u = new User();
		Dealer d = new Dealer();
		Result r = new Result();
		
		u.getHand();
		u.newHand();
		d.getDeal(u);
		r.getResult(u,d);
	}
}		
		
class User
{
	String [] suit = {"Clubs", "Diamonds", "Hearts", "Spades"};
	String [] value = {"Ace","Two","Three","Four","Five","Six","Seven","Eight","Nine","Jack","Queen","King"};
	int [] deck = {1,2,3,4,5,6,7,8,9,10,10,10,10};
	int [][] dealt = new int [4][13];
	int answer = 0, dealer = 0, deal = 0, total = 0, newCard;
	Object[] options = {"Hit","Stand"};
		
		void getHand()
		{
			int firstCardValue = new Random().nextInt(12);
			int firstCardSuit = new Random().nextInt(3);
			String firstCardName = value[firstCardValue] + " of " + suit[firstCardSuit];
			dealt [firstCardSuit] [firstCardValue] = 1;
			
			int secondCardValue = new Random().nextInt(12);
			int secondCardSuit = new Random().nextInt(3);
			String secondCardName = value[secondCardValue] + " of " + suit[secondCardSuit];
			dealt [secondCardSuit] [secondCardValue] = 1;
			
			total = deck[firstCardValue] + deck[secondCardValue];
			
			if(total == 21)
			{
				JOptionPane.showMessageDialog(null,
					"First Card: " + firstCardName + "\nSecond Hand: " + secondCardName + "\nTotal Hand Value: " + total + "\nBlackjack!\nYOU WIN!",
					"BLACKJACK",
					JOptionPane.PLAIN_MESSAGE);
					System.exit(0);
			}
			
			else
			{
				answer = JOptionPane.showOptionDialog(null,
						"First Card: " + firstCardName + "\nSecond Hand: " + secondCardName + "\nTotal Hand Value: " + total + "\n\nWhat Would You Like To Do?",
							"Your Hand",
						JOptionPane.YES_NO_OPTION,
						JOptionPane.PLAIN_MESSAGE,
						null,
						options, 
						options[0]); 
			}
		}
		
		void newHand()
		{
			while(answer!=1)
			{
				if(answer == 0)
				{
					int newCardValue;
					int newCardSuit ;
					String newCardName;
					
					do
					{
						newCardValue = new Random().nextInt(12);
						newCardSuit = new Random().nextInt(3);
						newCardName = value[newCardValue] + " of " + suit[newCardSuit];
					}
					while(dealt [newCardSuit] [newCardValue] == 1);
					dealt [newCardSuit] [newCardValue] = 1;
					total += deck[newCardValue];
					
					if(total > 21)
					{
						JOptionPane.showMessageDialog(null,
						"New Card: " + newCardName + "\nTotal Hand Value: " + total + "\nBUST!",
							"BUST",
						JOptionPane.PLAIN_MESSAGE);
						System.exit(0);
					}
					
					if(total == 21)
					{
						JOptionPane.showMessageDialog(null,
						"New Card: " + newCardName + "\nTotal Hand Value:  " + total + "\nBlackjack!\nYOU WIN!",
							"BLACKJACK",
						JOptionPane.PLAIN_MESSAGE);
						System.exit(0);
					}
					
					else
					{
					answer = JOptionPane.showOptionDialog(null,
						"New Card: " + newCardName + "\nTotal Hand Value: " + total + "\n\nWould You Like Another Card?",
							"Your Hand",
						JOptionPane.YES_NO_OPTION,
						JOptionPane.PLAIN_MESSAGE,
						null,
						options, 
						options[0]); 
					}
				}
			}
		}
}	

class Dealer
{
	int dealer = 0;

	void getDeal(User u)
	{
		while (dealer < 16)
		//dealer only stands on 17 or more
		{
			int dealerCardValue = 0;
			int dealerCardSuit = 0;
			String dealerCardName;
			
			do
			{
				dealerCardValue = new Random().nextInt(12);
				dealerCardSuit = new Random().nextInt(3);
				dealerCardName = u.value[dealerCardValue] + " of " + u.suit[dealerCardSuit];
			}
			while(u.dealt [dealerCardSuit] [dealerCardValue] == 1);
			u.dealt [dealerCardSuit] [dealerCardValue] = 1;
			dealer += u.deck[dealerCardValue];
			
			JOptionPane.showMessageDialog(null,
			"Dealer's Card: " + dealerCardName + "\nTotal Hand Value: " + dealer,
			"Dealer's Hand",
			JOptionPane.PLAIN_MESSAGE);
		}
		
		if(dealer == 21)
		{
			JOptionPane.showMessageDialog(null,
			"\nBlackjack!\nDEALER WINS!",
			"BLACKJACK",
			JOptionPane.PLAIN_MESSAGE);
			System.exit(0);
		}
			
		if(dealer > 21)
		{
			JOptionPane.showMessageDialog(null,
			"\nBlackjack!\nDEALER BUSTS!\nYOU WIN!",
			"Winner",
			JOptionPane.PLAIN_MESSAGE);
			System.exit(0);
		}
	}
}
		
class Result
{
	void getResult(User u, Dealer d)
	{
		if(u.total == d.dealer)
		{
			JOptionPane.showMessageDialog(null,
			"\nYour Total Hand Value: " + u.total + "\nThe Dealer's Total Hand Value: " + d.dealer + "\n\nYOU DRAW!",
			"Winner",
			JOptionPane.PLAIN_MESSAGE);
			System.exit(0);
		}
				
		if(u.total > d.dealer)
		{
			JOptionPane.showMessageDialog(null,
			"\nYour Total Hand Value: " + u.total + "\nThe Dealer's Total Hand Value: " + d.dealer + "\n\nYOU WIN!",
			"Winner",
			JOptionPane.PLAIN_MESSAGE);
			System.exit(0);
		}
				
		else 
		{
			JOptionPane.showMessageDialog(null,
			"Your Hand Total Value: " + u.total + "\nThe Dealer's Total Hand Value: " + d.dealer + "\n\nDEALER WINS!",
			"Winner",
			JOptionPane.PLAIN_MESSAGE);
			System.exit(0);
		}
	}
}