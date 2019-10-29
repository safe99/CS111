
public class Player {

	//declare your fields here
	private double balance;
	private Card[] banishedCards;
	Hand hand = new Hand();
	
	//initialize your fields in the constructor
	public Player(double balance){
		this.balance = balance;
	}

	public void deal(Card c){
		hand.addCard(c);
	}

	//Returns an array of Cards that the Player wishes to discard.
	//The game engine will call deal() on this player for each card
	//that exists in the return value. MS2 Instructions: Print the hand to
	//the terminal using System.out.println and ask the user which cards 
	//they would like to discard. The user will first the number of cards they
    //wish to discard, followed by the indices, one at a time, of
	//the card(s) they would like to discard, 
	//Return an array with the appropriate Card objects
	//that have been discarded, and remove the Card objects from this
	//player's hand. Use IO.readInt() for all inputs. In cases of error
	//re-ask the user for input.
	//
	// Example call to discard():
	//
	// This is your hand:
	// 0: Ace of Hearts
	// 1: 2 of Diamonds
	// 2: 5 of Hearts
	// 3: Jack of Spades
	// 4: Ace of Clubs
	// How many cards would you like to discard?
	// 2
	// 1
	// 2
	//
	// The resultant array will contain the 2 of Diamonds and the 5 of hearts in that order
	// This player's hand will now only have 3 cards
	public Card[] discard(){
		int discard = 0;
		hand.sortByValue();
		System.out.println("This is your hand:");
		for(int i=0; i<hand.getCardCount(); i++) {
			System.out.println(i+": "+hand.getCard(i));
		}
		System.out.println();
		System.out.println("How many cards would you like to discard?");
		
		if(hand.hasStraight()||hand.hasFlush()||hand.hasFullHouse()||hand.hasFourOfAKind()) {
			discard = 0;
			System.out.println("No cards has been discarded");
			banishedCards = new Card[0];
			return banishedCards;
		}
		
		else if(hand.hasTriplet()) {
			discard = 2;
			int c=0;
			for(int i=0; i<hand.getCardCount();i++) {
				if(hand.highestDuplicate().getValue()!=hand.getCard(i).getValue()) {
					banishedCards[c] = hand.getCard(i);
					hand.removeCard(i);
					c++;
				}
			}
			System.out.println("2 cards have been discarded");
		}
		
		else if(hand.numPairs()==1) {
			discard = 3;
			int c=0;
			banishedCards = new Card[discard];
			for(int i=0; i<hand.getCardCount();i++) {
				if(hand.highestDuplicate().getValue()!=hand.getCard(i).getValue()) {
					banishedCards[c] = hand.getCard(i);
					hand.removeCard(i);
					c++;
				}
			}
			System.out.println("3 cards have been discarded");
		}
		
		else if(hand.highestValue().getValue()>=11) {
			discard = 4;
			banishedCards = new Card[discard];
			for(int i=0; i<banishedCards.length;i++){
				banishedCards[i] = hand.getCard(i);
				hand.removeCard(i);
			}
			System.out.println("4 cards have been discarded");
		}
		else {
			discard = 5;
			banishedCards = new Card[discard];
			for(int i=0; i<banishedCards.length;i++) {
				banishedCards[i] = hand.getCard(i);
				hand.removeCard(i);
			}
			System.out.println("5 cards have been discarded");
		}
			
		return banishedCards;
	}

	//Returns the amount that this player would like to wager, returns
	//-1.0 to fold hand. Any non zero wager should immediately be deducted
	//from this player's balance. This player's balance can never be below
	// 0 at any time. This player's wager must be >= to the parameter min
	// MS2 Instructions: Show the user the minimum bet via the terminal 
	//(System.out.println), and ask the user for their wager. Use
	//IO.readDouble() for input. In cases of error re-ask the user for 
	//input.
	// 
	// Example call to wager()
	//
	// How much do you want to wager?
	// 200
	//
	// This will result in this player's balance reduced by 200
	
	public double wager(double min){
		double wager=0.0;
		System.out.println("The minimum bet is: "+min);
		if(balance<min) {
			System.out.println("You do not have enough money to bet the minimum, you must fold");
			return -1.0;
		}
		else {
			System.out.println("How much do you want to wager?");
			

			
			if(hand.hasStraight()||hand.hasFlush()||hand.hasFullHouse()||hand.hasFourOfAKind()) {
				if(balance*2/3<=min) {
					wager=min;
				}
				wager=(int)(balance*2/3);
			}
			
			else if(hand.hasTriplet()) {
				int bal = (int) (balance/10);
				int minmin = (int) (min/10);
				wager = (( (int) (Math.random()*(bal-minmin) ) ) +(minmin))*10; //random between min and bal
			}
			
			else if(hand.numPairs()==1) {
				if(balance/4<=min) {
					wager=min;
				}
				wager=(int)(balance/4);
			}
			
			else if(hand.highestValue().getValue()>=11) {
				if(balance/7<=min) {
					wager=min;
				}
				wager=(int)(balance/7);
			}
			else {
				if(balance<min++) {
					wager=min;
				}
				wager=min+1;				
			}
			
			System.out.println("The AI has selected "+wager);
		
			if(wager == 0)
				return -1.0;
		}
		
		balance-=wager;
		
		return wager;
	}

	//Returns this player's hand
	public Hand showHand(){
		return hand;
	}

	//Returns this player's current balance
	public double getBalance(){
		return balance;
	}

	//Increase player's balance by the amount specified in the parameter,
	//then reset player's hand in preparation for next round. Amount will
	//be 0 if player has lost hand
	public void winnings(double amount){
		balance+=amount;
		hand.clear();
	}

}
