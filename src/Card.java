import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Card implements Comparable<Card>{
    private final Suit suit;
    private final Rank rank;
    private final static Map<String, Card> CARDS = initCards();

    /**
     * Card constructor with rank and suit of card
     * @param rank Rank of card
     * @param suit Suit of card
     */
    private Card(final Rank rank, final Suit suit){
        this.rank = rank;
        this.suit = suit;
    }

    /**
     * Initializes map of all cards
     * Key - String in form of "'Rank' of 'Suit'"
     * Value - Card object
     * @return unmodifiable map
     */
    private static Map<String, Card> initCards(){
        //initialize map
        final Map<String, Card> cards = new HashMap<>();
        for (final Suit suit:Suit.values()){
            for (final Rank rank:Rank.values()){
                cards.put(info(rank,suit), new Card(rank, suit));
            }
        }
        return Collections.unmodifiableMap(cards);
    }

    enum Suit{Spades, Hearts, Diamonds,Clubs} //All suits

    enum Rank{ //All ranks and their respective values
        Two(2),
        Three(3),
        Four(4),
        Five(5),
        Six(6),
        Seven(7),
        Eight(8),
        Nine(9),
        Ten(10),
        Jack(11),
        Queen(12),
        King(13),
        Ace(14);
        private final int value;

        Rank (final int value){
            this.value = value;
        }
/*
        public int rankValue(){
            return this.value;
        }*/
    }

    /**
     * Sorts cards by rank from greatest to smallest
     */
    @Override
    public int compareTo(Card card) {
        return Integer.compare(this.rank.value, card.rank.value);
    }

    /**
     * Public method to get a card's data
     * @return Card object
     */
    public static Card getCard(final Rank rank, final Suit suit){
        final Card card = CARDS.get(info(rank, suit));
        if (card != null) return card;
        throw new RuntimeException(String.format("Invalid card ! %s of %s", rank, suit));
    }

    /**
     * Puts card's info into correct string form
     * @return "'Rank' of 'Suit'"
     */
    private static String info(final Rank rank, final Suit suit){
        return String.format("%s of %s", rank, suit);
    }

    /**
     * Overrides toString to ensure correct string printed rather
     * than memory stored for card
     * @return String
     */
    @Override
    public String toString(){return String.format("%s of %s", this.rank, this.suit);}
}
