/**
 * An object of type Hand represents a hand of cards.  The
 * cards belong to the class Card.  A hand is empty when it
 * is created, and any number of cards can be added to it.
 */



public class Hand {
	
	   private Card[] hand;   // The cards in the hand.
	   private int count; 
	   
	   /**
	    * Create a hand that is initially empty.
	    */
	   public Hand() {
	      hand = new Card[5];
		  count = 0;
	   }
	   
	   /**
	    * Remove all cards from the hand, leaving it empty.
	    */
	   public void clear() {
	      for(int i=0 ; i<hand.length; i++){ hand[i] = null; }
		  count = 0;
	   }
	   
	   /**
	    * Add a card to the hand.  It is added at the end of the current hand.
	    * @param c the non-null card to be added.
	    * @throws NullPointerException if the parameter c is null.
	    */
	   public void addCard(Card c) {
	      
		  for(int i=0 ; i<hand.length; i++){ 
			if (hand[i] == null){
				hand[i] = c;
				count = count + 1;
				break;
			}
		 }

		  
	   }
	   
	   /**
	    * Remove a card from the hand, if present.
	    * @param c the card to be removed.  If c is null or if the card is not in 
	    * the hand, then nothing is done.
	    */
	   public void removeCard(Card c) {

	  for(int i=0 ; i<hand.length; i++){ 
	    if (hand[i]!=null && hand[i].equals(c)){
	      hand[i] = null;
	      count = count-1;
	    }
	  }

	   }
	   
	   /**
	    * Remove the card in a specified position from the hand.
	    * @param position the position of the card that is to be removed, where
	    * positions are starting from zero.
	    * @throws IllegalArgumentException if the position does not exist in
	    * the hand, that is if the position is less than 0 or greater than
	    * or equal to the number of cards in the hand.
	    */
	   public void removeCard(int position) {
	      if (position < 0 || position >= hand.length)
	         throw new IllegalArgumentException("Position does not exist in hand: "
	               + position);
	      hand[position] = null;
	      count --;
	   }	

	   /**
	    * Returns the number of cards in the hand.
	    */
	   public int getCardCount() {
	      return count;
	   }
	   
	   /**
	    * Gets the card in a specified position in the hand.  (Note that this card
	    * is not removed from the hand!)
	    * @param position the position of the card that is to be returned
	    * @throws IllegalArgumentException if position does not exist in the hand
	    */
	   public Card getCard(int position) {
	      if (position < 0 || position >= hand.length)
	         throw new IllegalArgumentException("Position does not exist in hand: "
	               + position);
	       return hand[position];
	   }
	   
	   /**
	    * Sorts the cards in the hand so that cards of the same suit are
	    * grouped together, and within a suit the cards are sorted by value.
	    * Note that aces are considered to have the lowest value, 1.
	    */
	   public void sortBySuit() {
		  int size = count;
		  int nonnull = 0;
		  int index = 0;
		  
	      Card[] newHand = new Card[5];
	      while (size > 0) {
			 if (hand[nonnull] == null) { nonnull = nonnull+1; continue;}
	         int pos = nonnull;  // Position of minimal card.
	         Card c = hand[nonnull];  // Minimal card.
			 
	         for (int i = nonnull+1; i < hand.length; i++) {
	            Card c1 = hand[i];
				if (c1 != null){
					if ( c1.getSuit() < c.getSuit() ||
							(c1.getSuit() == c.getSuit() && c1.getValue() < c.getValue()) ) {
						pos = i;
						c = c1;
					}
				}
	         }
	         hand[pos] = null;
			 size = size - 1;
	         newHand[index++] = c;
			 nonnull = 0;
	      }
	      hand = newHand;
	   }
	   
	   /**
	    * Sorts the cards in the hand so that cards of the same value are
	    * grouped together.  Cards with the same value are sorted by suit.
	    * Note that aces are considered to have the lowest value, 1.
	    */
	   public void sortByValue() {
		  int size = count;
		  int nonnull = 0;
		  int index = 0;
		  
	      Card[] newHand = new Card[5];
	      while (size > 0) {
			 if (hand[nonnull] == null) {
				 nonnull = nonnull+1; continue;
			 }
	         int pos = nonnull;  // Position of minimal card.
	         Card c = hand[nonnull];  // Minimal card.
	         for (int i = nonnull+1; i < hand.length; i++) {
	            
				Card c1 = hand[i];
	            if (c1 != null){
					if ( c1.getValue() < c.getValue() ||
							(c1.getValue() == c.getValue() && c1.getSuit() < c.getSuit()) ) {
						pos = i;
						c = c1;
					}
				}
	         }
	         hand[pos] = null;
			 size = size - 1;
	         newHand[index++] = c;
			 nonnull = 0;
	      }
	      hand = newHand;
	   }
	   
	   public void printHand(){
		   
		   for(int i=0; i<hand.length; i++){
			   if (hand[i] != null){
				   System.out.println(hand[i]);
			   }
		   }
		   System.out.println();
	   }
	   

	   /******************************** Implement your methods here ****************************************/
	 
	   //Returns the number of pairs this hand contains
	   public int numPairs() {
		   Hand.this.sortByValue();
		   int count=1;
		   int pairs=0;
		   int value=hand[0].getValue();
		   for(int i=1; i<hand.length; i++) {
			   if(hand[i] != null) {
				   if(hand[i].getValue() == value) {
					  count++;
				   }
				   else {
					   pairs+=(count/2);
					   value = hand[i].getValue();
					   count=1;
				   }
			   }
		   }
		   pairs+=(count/2);
		   		   
		   return pairs;
	   }

	   //Returns true if this hand has 3 cards that are of the same value
	   public boolean hasTriplet() {
		   Hand.this.sortByValue();
		   int count=1;
		   boolean triplet = false;
		   int value=hand[0].getValue();
		   for(int i=1; i<hand.length; i++) {
			   if(hand[i] != null) {
				   if(hand[i].getValue() == value) {
					  count++; 
				   }
				   else {
					   if(count/3==1) {
						   triplet=true;
					   }
					   value = hand[i].getValue();
					   count=1;
				   }
			   }
		   }
		   if(count/3==1) {
			   triplet=true;
		   }
		   
		   return triplet;
	   }
	    
	   //Returns true if this hand has all cards that are of the same suit
	   public boolean hasFlush() {
		   int suit = hand[0].getSuit();
		   for(int i=1; i<hand.length;i++) {
			   if(hand[i].getSuit()!=suit) {
				   return false;
			   }
		   }
		   return true;
	   }

	   //Returns true if this hand has 5 consecutive cards of any suit
	   public boolean hasStraight() {
		   if(Hand.this.getCardCount() < 5) {
			   return false;
		   }

		   Hand.this.sortByValue();
		   //Hand.this.printHand();
		   int min=hand[0].getValue();
		   
		   for(int i=1; i<hand.length; i++) {
			   if(hand[i].getValue() != min+i) {
				   return false;
			   }
		   }
		   
		   return true;
		   
	   }
	    
	   //Returns true if this hand has a triplet and a pair of different values
	   public boolean hasFullHouse() {//
		   boolean fullH = false;
		   if(!Hand.this.hasTriplet()) {
			   return false;
		   }
		   
		   Hand.this.sortByValue();
		   int count=1;
		   int value=hand[0].getValue();
		   boolean firstPairOrTriplet = true;
		   
		   for(int i=1; i<hand.length; i++) {
			   if(hand[i].getValue() == value) {
				  count++; 
			   }
			   else {
				   if(firstPairOrTriplet) {
					   if((count==3)||(count==2)) {
					   }
					   else{
						   return false;
					   }
					   firstPairOrTriplet = false;
					   value=hand[i].getValue();
					   count=1;
				   }
				   else {
					   if (i!=hand.length-1) {
						   firstPairOrTriplet = false;
					   }
				   }
			   }
		   }
		   if((count==2)||(count==3)) {
			   fullH = true;
		   }
		   
		   return fullH;
	   }
	    
	   //Returns true if this hand has 4 cards that are of the same value
	   public boolean hasFourOfAKind() {
		   boolean fourKind = false;
		   if(Hand.this.getCardCount() < 4) {
			   return false;
		   }
		   		   
		   Hand.this.sortByValue();
		   int max=0;
		   int count=1;
		   int value=hand[0].getValue();
		   for(int i=1; i<hand.length; i++) {
			   if(hand[i].getValue() == value) {
				  count++; 
			   }
			   else {
				   if(count>max) {
					   max=count;
				   }
				   value = hand[i].getValue();
				   count=1;
			   }
		   }
		   if(count>max) {
			   max=count;
		   }
		   
		   if(max>=4) {
			   fourKind = true;
		   }

		   return fourKind;
	   }
	    
	   //Returns the card with the highest value in the hand. When there is
	   //more than one highest value card, you may return any one of them
	   public Card highestValue() {
		   int max=0;
		   int pos=0;
		   for(int i=0; i<hand.length; i++) {
			   if(hand[i] != null) {
				   if(hand[i].getValue() > max) {
					   max=hand[i].getValue();
					   pos=i;
				   }
			   }
		   }
		   return hand[pos];
	   }
	    
	   //Returns the highest valued Card of any pair or triplet found, null if
	   // none. When values are equal, you may return either
	   public Card highestDuplicate() {
		   if(Hand.this.numPairs() == 0) {
			   return null;
		   }
		   
		   Hand.this.sortByValue();
		   int count=1;
		   int max=0;
		   int pos=0;
		   int value=hand[0].getValue();
		   for(int i=1; i<hand.length; i++) {
			   if(hand[i] != null) {
				   if(hand[i].getValue() == value) {
					  count++; 
				   }
				   else {
					   if(count>=2) {
						   if(hand[i-1].getValue() > max) {
							   max=hand[i-1].getValue();
							   pos=i-1;
						   }
					   }
					   value = hand[i].getValue();
					   count=1;
				   }
			   }
		   }
		   if(count>=2) {
			   if(hand[4].getValue() > max) {
				   max=hand[4].getValue();
				   pos=4;
			   }
		   }

		   return hand[pos];
	   }
	    
	   //Returns -1 if the instance hand loses to the parameter hand, 0 if //the hands are equal, and +1 if the instance hand wins over the //parameter hand. Hint: you might want to use some of the methods //above
	   public int compareTo(Hand h) {//
		   int outcome = 0;
		   int player_rank=10;
		   int opponent_rank=10;
		   
		   h.sortByValue();
		   Hand.this.sortByValue();
		   
		   if((h.hasFlush()) && (h.hasStraight())) {
			   opponent_rank=1;
		   }
		   else if(h.hasFourOfAKind()){
			   opponent_rank=2;
		   }
		   else if(h.hasFullHouse()) {
			   opponent_rank=3;
		   }
		   else if(h.hasFlush()) {
			   opponent_rank=4;
		   }
		   else if(h.hasStraight()) {
			   opponent_rank=5;
		   }
		   else if(h.hasTriplet()) {
			   opponent_rank=6;
		   }
		   else if(h.numPairs() == 2) {
			   opponent_rank=7;
		   }
		   else if(h.numPairs() == 1) {
			   opponent_rank=8;
		   }
		   else {
			   opponent_rank=9;
		   }
		   
		   if((Hand.this.hasFlush()) && (Hand.this.hasStraight())) { 
			   player_rank=1;
		   }
		   else if(Hand.this.hasFourOfAKind()){ //next card
			   player_rank=2;
		   }
		   else if(Hand.this.hasFullHouse()) { //trip
			   player_rank=3;
		   }
		   else if(Hand.this.hasFlush()) { //5th
			   player_rank=4;
		   }
		   else if(Hand.this.hasStraight()) {
			   player_rank=5;
		   }
		   else if(Hand.this.hasTriplet()) { //trip --> 4th --> 5th
			   player_rank=6;
		   }
		   else if(Hand.this.numPairs() == 2) { //1st pair --> 2nd pair --> 5th
			   player_rank=7;
		   }
		   else if(Hand.this.numPairs() == 1) { //1st pair --> 3rd --> 4th --> 5th
			   player_rank=8;
		   }
		   else { //1st --> 2nd --> 3rd --> 4th -->5th
			   player_rank=9;
		   }
		   
		   System.out.println("Player Rank: "+player_rank);
		   System.out.println("Opponent Rank:"+opponent_rank);
		   
		   if(player_rank<opponent_rank) {
			   outcome=1;
		   }
		   else if(player_rank>opponent_rank) {
			   outcome=-1;
		   }
		   
		   else if((player_rank==1)&&(opponent_rank==1)) {
			   System.out.println("Tie case: Straight Flush");
			   if(Hand.this.highestValue().getValue()>h.highestValue().getValue()) {
				   outcome=1;
			   }
			   else if(Hand.this.highestValue().getValue()<h.highestValue().getValue()) {
				   outcome=-1;
			   }
			   else outcome=0;
		   }
		   
		   else if((player_rank==2)&&(opponent_rank==2)) {
			   System.out.println("Tie case: Four of a kind");
			   if(Hand.this.highestDuplicate().getValue()>h.highestDuplicate().getValue()) {
				   outcome=1;
			   }
			   else if(Hand.this.highestDuplicate().getValue()<h.highestDuplicate().getValue()) {
				   outcome=-1;
			   }
			   else if(Hand.this.highestDuplicate().getValue()==h.highestDuplicate().getValue()) {
				   int max1=0;
				   int max2=0;
				   if(hand[0].getValue()==Hand.this.highestDuplicate().getValue()) {
					   max1=hand[4].getValue();
				   }
				   else max1=hand[0].getValue();
				   if(h.getCard(0).getValue()==Hand.this.highestDuplicate().getValue()) {
					   max2=h.getCard(4).getValue();
				   }
				   else max2=h.getCard(0).getValue();
				   
				   if(max1>max2) {
					   outcome=1;
				   }
				   else if(max2>max1) {
					   outcome=-1;
				   }
				   else outcome=0;
			   }
		   }
		   
		   else if((player_rank==3)&&(opponent_rank==3)) {
			   System.out.println("Tie case: Full House");
			   int tripMax=0;
			   int tripMax2=0;
			   int pairMax=0;
			   int pairMax2=0;
			   if(hand[2].getValue()==hand[1].getValue()) {
				   tripMax=hand[2].getValue();
				   pairMax=hand[3].getValue();
				   System.out.println("Pair is larger");
			   }
			   else {
				   tripMax=hand[2].getValue();
				   pairMax=hand[1].getValue();
				   System.out.println("Pair is smaller");
			   }
			   
			   if(h.getCard(2).getValue()==h.getCard(1).getValue()) {
				   tripMax2=h.getCard(2).getValue();
				   pairMax2=h.getCard(3).getValue();
				   
				   
			   }
			   else {
				   tripMax2=h.getCard(2).getValue();
				   pairMax2=h.getCard(1).getValue();
				   System.out.println("Pair is smaller");
			   }
			   
			   if(tripMax>tripMax2) {
				   outcome=1;
			   }
			   else if(tripMax<tripMax2) {
				   outcome=-1;
			   }
			   else if(tripMax==tripMax2){
				   if(pairMax>pairMax2) {
					   outcome=1;
				   }
				   else if(pairMax<pairMax2) {
					   outcome=-1;
				   }
				   else outcome=0;
			   }
		   }
		   
		   else if((player_rank==4)&&(opponent_rank==4)) {
			   System.out.println("Tie case: Flush");
			   if(Hand.this.highestValue().getValue()>h.highestValue().getValue()) {
				   outcome=1;
			   }
			   else if(Hand.this.highestValue().getValue()<h.highestValue().getValue()) {
				   outcome=-1;
			   }
			   else if(Hand.this.highestValue().getValue()==h.highestValue().getValue()){			   
				   if(hand[3].getValue() > h.getCard(3).getValue()) {
					   outcome=1;
				   }
				   else if(hand[3].getValue() < h.getCard(3).getValue()) {
					   outcome=-1;
				   }
				   else if(hand[3].getValue() == h.getCard(3).getValue()){
					   if(hand[2].getValue() > h.getCard(2).getValue()) {
						   outcome=1;
					   }
					   else if(hand[2].getValue() < h.getCard(2).getValue()) {
						   outcome=-1;
					   }
					   else if(hand[2].getValue() == h.getCard(2).getValue()){
						   if(hand[1].getValue() > h.getCard(1).getValue()) {
							   outcome=1;
						   }
						   else if(hand[1].getValue() < h.getCard(1).getValue()) {
							   outcome=-1;
						   }
						   else if(hand[1].getValue() == h.getCard(1).getValue()){
							   if(hand[0].getValue() > h.getCard(0).getValue()) {
								   outcome=1;
							   }
							   else if(hand[0].getValue() < h.getCard(0).getValue()) {
								   outcome=-1;
							   }
							   else outcome=0;
						   }
					   }
				   }
				   
			   }
		   }
		   
		   else if((player_rank==5)&&(opponent_rank==5)) {
			   System.out.println("Tie case: Straight");
			   if(Hand.this.highestValue().getValue()>h.highestValue().getValue()) {
				   outcome=1;
			   }
			   else if(Hand.this.highestValue().getValue()<h.highestValue().getValue()) {
				   outcome=-1;
			   }
			   else outcome=0;
		   }
		   
		   else if((player_rank==6)&&(opponent_rank==6)) {
			   System.out.println("Tie case: Triplet");
			   if(Hand.this.highestDuplicate().getValue()>h.highestDuplicate().getValue()) {
				   outcome=1;
			   }
			   else if(Hand.this.highestDuplicate().getValue()<h.highestDuplicate().getValue()) {
				   outcome=-1;
			   }
			   else if(Hand.this.highestDuplicate().getValue()==h.highestDuplicate().getValue()) {
				   if(hand[4].getValue() > h.getCard(4).getValue()) {
					   outcome=1;
				   }
				   else if(hand[4].getValue() < h.getCard(4).getValue()) {
					   outcome=-1;
				   }
				   else if(hand[4].getValue() == h.getCard(4).getValue()){
					   if(hand[3].getValue() > h.getCard(3).getValue()) {
						   outcome=1;
					   }
					   else if(hand[3].getValue() < h.getCard(3).getValue()) {
						   outcome=-1;
					   }
					   else if(hand[3].getValue() == h.getCard(3).getValue()){
						   if(hand[2].getValue() > h.getCard(2).getValue()) {
							   outcome=1;
						   }
						   else if(hand[2].getValue() < h.getCard(2).getValue()) {
							   outcome=-1;
						   }
						   else if(hand[2].getValue() == h.getCard(2).getValue()){
							   if(hand[1].getValue() > h.getCard(1).getValue()) {
								   outcome=1;
							   }
							   else if(hand[1].getValue() < h.getCard(1).getValue()) {
								   outcome=-1;
							   }
							   else if(hand[1].getValue() == h.getCard(1).getValue()){
								   if(hand[0].getValue() > h.getCard(0).getValue()) {
									   outcome=1;
								   }
								   else if(hand[0].getValue() < h.getCard(0).getValue()) {
									   outcome=-1;
								   }
								   else outcome=0;
							   }
						   }
					   }
				   }
			   }
		   }
		   
		   else if((player_rank==7)&&(opponent_rank==7)) {//
			   System.out.println("Tie case: Two Pairs");
			   if(Hand.this.highestDuplicate().getValue()>h.highestDuplicate().getValue()) {
				   outcome=1;
			   }
			   else if(Hand.this.highestDuplicate().getValue()<h.highestDuplicate().getValue()) {
				   outcome=-1;
			   }
			   else if(Hand.this.highestDuplicate().getValue()==h.highestDuplicate().getValue()){
				   if(hand[1].getValue()>h.getCard(1).getValue()) {
					   outcome=1;
				   }
				   else if(hand[1].getValue()<h.getCard(1).getValue()) {
					   outcome=-1;
				   }
				   else if(hand[1].getValue()==h.getCard(1).getValue()) {
					   if(hand[4].getValue() > h.getCard(4).getValue()) {
						   outcome=1;
					   }
					   else if(hand[4].getValue() < h.getCard(4).getValue()) {
						   outcome=-1;
					   }
					   else if(hand[4].getValue() == h.getCard(4).getValue()){
						   if(hand[2].getValue() > h.getCard(2).getValue()) {
							   outcome=1;
						   }
						   else if(hand[2].getValue() < h.getCard(2).getValue()) {
							   outcome=-1;
						   }
						   else if(hand[2].getValue() == h.getCard(2).getValue()){
							   if(hand[0].getValue() > h.getCard(0).getValue()) {
								   outcome=1;
							   }
							   else if(hand[0].getValue() < h.getCard(0).getValue()) {
								   outcome=-1;
							   }
							   else outcome=0;
						   }
					   }
					   
				   }
			   }
			   
		   }
		   
		   else if((player_rank==8)&&(opponent_rank==8)) {
			   System.out.println("Tie case: One Pair");
			   if(Hand.this.highestDuplicate().getValue()>h.highestDuplicate().getValue()) {
				   outcome=1;
			   }
			   else if(Hand.this.highestDuplicate().getValue()<h.highestDuplicate().getValue()) {
				   outcome=-1;
			   }
			   else if(Hand.this.highestDuplicate().getValue()==h.highestDuplicate().getValue()){
				   if(hand[4].getValue() > h.getCard(4).getValue()) {
					   outcome=1;
				   }
				   else if(hand[4].getValue() < h.getCard(4).getValue()) {
					   outcome=-1;
				   }
				   else if(hand[4].getValue() == h.getCard(4).getValue()){
					   if(hand[3].getValue() > h.getCard(3).getValue()) {
						   outcome=1;
					   }
					   else if(hand[3].getValue() < h.getCard(3).getValue()) {
						   outcome=-1;
					   }
					   else if(hand[3].getValue() == h.getCard(3).getValue()){
						   if(hand[2].getValue() > h.getCard(2).getValue()) {
							   outcome=1;
						   }
						   else if(hand[2].getValue() < h.getCard(2).getValue()) {
							   outcome=-1;
						   }
						   else if(hand[2].getValue() == h.getCard(2).getValue()){
							   if(hand[1].getValue() > h.getCard(1).getValue()) {
								   outcome=1;
							   }
							   else if(hand[1].getValue() < h.getCard(1).getValue()) {
								   outcome=-1;
							   }
							   else if(hand[1].getValue() == h.getCard(1).getValue()){
								   if(hand[0].getValue() > h.getCard(0).getValue()) {
									   outcome=1;
								   }
								   else if(hand[0].getValue() < h.getCard(0).getValue()) {
									   outcome=-1;
								   }
								   else outcome=0;
							   }
						   }
					   }
				   }
				   			   
			   }
		   }
		   
		   else if((player_rank==9)&&(opponent_rank==9)) {
			   System.out.println("Tie case: Highest");
			   if(Hand.this.highestValue().getValue()>h.highestValue().getValue()) {
				   outcome=1;
			   }
			   else if(Hand.this.highestValue().getValue()<h.highestValue().getValue()) {
				   outcome=-1;
			   }
			   
			   else if(Hand.this.highestValue().getValue()==h.highestValue().getValue()){			   
				   if(hand[3].getValue() > h.getCard(3).getValue()) {
					   outcome=1;
				   }
				   else if(hand[3].getValue() < h.getCard(3).getValue()) {
					   outcome=-1;
				   }
				   else if(hand[3].getValue() == h.getCard(3).getValue()){
					   if(hand[2].getValue() > h.getCard(2).getValue()) {
						   outcome=1;
					   }
					   else if(hand[2].getValue() < h.getCard(2).getValue()) {
						   outcome=-1;
					   }
					   else if(hand[2].getValue() == h.getCard(2).getValue()){
						   if(hand[1].getValue() > h.getCard(1).getValue()) {
							   outcome=1;
						   }
						   else if(hand[1].getValue() < h.getCard(1).getValue()) {
							   outcome=-1;
						   }
						   else if(hand[1].getValue() == h.getCard(1).getValue()){
							   if(hand[0].getValue() > h.getCard(0).getValue()) {
								   outcome=1;
							   }
							   else if(hand[0].getValue() < h.getCard(0).getValue()) {
								   outcome=-1;
							   }
							   else outcome=0;
						   }
					   }
				   }
				   
			   }
			   else outcome=0;
		   }
		   
		   else {
			   outcome=0;
		   }
		   
		   return outcome;
	   }

}
