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
		u.newCard();
		d.getDeal(u);
		r.getResult(u,d);
	}
}		

class User
{
	//display content on swing
	private String displayTitle;
	private String displayContent;
	String [] suit = {"Clubs", "Diamonds", "Hearts", "Spades"}; //suit names
	String [] value = {"Ace","Two","Three","Four","Five","Six","Seven","Eight","Nine","Jack","Queen","King"}; //card values
	int [] deck = {1,2,3,4,5,6,7,8,9,10,10,10,10}; //card int values
	int [][] dealt = new int [4][13]; //stops cards being dealt twice
	int answer = 0, dealer = 0, deal = 0, total = 0, newCard;
	private Object[] options = {"Hit","Stand"}; //options for JOptionPane
	private int firstCardValue;
	private int firstCardSuit ;
	private String firstCardName;
	private int secondCardValue;
	private int secondCardSuit;
	private String secondCardName;
		
		void getHand()
		{
			//each card is randomly given a suit and value and a string is created to display it
			firstCardValue = new Random().nextInt(12);
			firstCardSuit = new Random().nextInt(3);
			firstCardName = value[firstCardValue] + " of " + suit[firstCardSuit];
			dealt [firstCardSuit] [firstCardValue] = 1; //card has been dealt
			
			//although small probability must make sure the first and second cards aren't the same
			do
			{
				secondCardValue = new Random().nextInt(12);
				secondCardSuit = new Random().nextInt(3);
				secondCardName = value[secondCardValue] + " of " + suit[secondCardSuit];
			}
			while(dealt [secondCardSuit] [secondCardValue] == 1);
			dealt [secondCardSuit] [secondCardValue] = 1;
			
			total = deck[firstCardValue] + deck[secondCardValue]; //total of card's values
			
			if(total == 21)
			{
				displayTitle = "BLACKJACK";
				displayContent = "First Card: " + firstCardName + "\nSecond Card: " + secondCardName + "\nTotal Hand Value: " + total + "\nBlackjack!\nYOU WIN!";
				display();
			}
			
			else
			{
				answer = JOptionPane.showOptionDialog(null,
						"First Card: " + firstCardName + "\nSecond Card: " + secondCardName + "\nTotal Hand Value: " + total + "\n\nWhat Would You Like To Do?",
							"Your Hand",
						JOptionPane.YES_NO_OPTION,
						JOptionPane.PLAIN_MESSAGE,
						null,
						options, 
						options[0]); 
			}
		}
		
		void newCard()//deals new cards if user wants to hit
		{
			while(answer!=1)//loop until user stands
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
					while(dealt [newCardSuit] [newCardValue] == 1);//ensures cards aren't dealt twice
					dealt [newCardSuit] [newCardValue] = 1;
					total += deck[newCardValue];
					
					if(total > 21)
					{
						displayTitle = "BUST";
						displayContent = "New Card: " + newCardName + "\nTotal Hand Value: " + total + "\nBUST!";
						display();
					}
					
					if(total == 21)
					{
						displayTitle = "BLACKJACK";
						displayContent = "New Card: " + newCardName + "\nTotal Hand Value:  " + total + "\nBlackjack!\nYOU WIN!";
						display();
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
		
		void display()//display if bust or blackjack
		{
			JOptionPane.showMessageDialog(null,
			displayContent,
			displayTitle,
			JOptionPane.PLAIN_MESSAGE);
			System.exit(0);
		}
}	

class Dealer
{
	int dealer = 0;
	String dealerDisplay;

	void getDeal(User u)
	{
		while (dealer < 16)
		//dealer only stands on 16 or more
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
			while(u.dealt [dealerCardSuit] [dealerCardValue] == 1);//ensure cards aren't dealt twice
			u.dealt [dealerCardSuit] [dealerCardValue] = 1;
			dealer += u.deck[dealerCardValue];
			
			//display what cards the dealer are dealt
			JOptionPane.showMessageDialog(null,
			"Dealer's Card: " + dealerCardName + "\nTotal Hand Value: " + dealer,
			"Dealer's Hand",
			JOptionPane.PLAIN_MESSAGE);
		}
		
		if(dealer == 21)
		{
			dealerDisplay = "\nBlackjack!\nDEALER WINS!";
			display();
		}
			
		if(dealer > 21)
		{
			dealerDisplay = "\nDEALER BUSTS!\nYOU WIN!";
			display();
		}
	}

	void display()//display if bust or blackjack
	{
			JOptionPane.showMessageDialog(null,
			dealerDisplay,
			"Winner",
			JOptionPane.PLAIN_MESSAGE);
			System.exit(0);
	}
}

//displays result of game if both dealer and user stand
class Result
{
	String winnerDisplay;
	
	void getResult(User u, Dealer d)
	{
		if(u.total == d.dealer)
		{
			winnerDisplay = "\n\nYOU DRAW!";
			display(u, d);
		}
				
		if(u.total > d.dealer)
		{
			winnerDisplay = "\n\nYOU WIN!";
			display(u, d);
		}
				
		else 
		{
			winnerDisplay = "\n\nDEALER WINS!";
			display(u, d);
		}
	}
	
	void display(User u, Dealer d)//display who wins
	{
		JOptionPane.showMessageDialog(null,
		"Your Hand Total Value: " + u.total + "\nThe Dealer's Total Hand Value: " + d.dealer + winnerDisplay,
		"Winner",
		JOptionPane.PLAIN_MESSAGE);
		System.exit(0);
	}
}