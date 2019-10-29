
public class PlayPoker {

	public static void main(String[] args) {
		
		Hand firstHand = new Hand();	
		firstHand.addCard(new Card(2,0));
		firstHand.addCard(new Card(6,1));
		firstHand.addCard(new Card(6,1));
		firstHand.addCard(new Card(9,1));
		firstHand.addCard(new Card(9,1));
		firstHand.sortByValue();
		firstHand.printHand();
		
		System.out.println("Pairs: "+firstHand.numPairs()); 
		System.out.println("Triplet: "+firstHand.hasTriplet()); 
		System.out.println("Flush: "+firstHand.hasFlush()); //Same Suit 
		System.out.println("Straight: "+firstHand.hasStraight()); //Consecutive Value 
		System.out.println("Full House:"+firstHand.hasFullHouse()); //Pair & Triplet
		System.out.println("4Kind: "+firstHand.hasFourOfAKind());
		System.out.println("Highest Value: "+firstHand.highestValue());
		System.out.println("Highest Dupe: "+firstHand.highestDuplicate());
		System.out.println();
		
		Hand secondHand = new Hand();	
		secondHand.addCard(new Card(2,1));
		secondHand.addCard(new Card(6,0));
		secondHand.addCard(new Card(6,0));
		secondHand.addCard(new Card(9,0));
		secondHand.addCard(new Card(9,0));
		secondHand.sortByValue();
		secondHand.printHand();

		System.out.println("Pairs: "+secondHand.numPairs());
		System.out.println("Triplet: "+secondHand.hasTriplet()); 
		System.out.println("Flush: "+secondHand.hasFlush()); 
		System.out.println("Straight: "+secondHand.hasStraight());
		System.out.println("Full House:"+secondHand.hasFullHouse());
		System.out.println("4Kind: "+secondHand.hasFourOfAKind()); 
		System.out.println("Highest Value: "+secondHand.highestValue());
		System.out.println("Highest Dupe: "+secondHand.highestDuplicate()); 	
		System.out.println();
		
		System.out.println("Who wins?: "+firstHand.compareTo(secondHand));

	}

}
