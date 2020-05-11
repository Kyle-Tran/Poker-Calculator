import java.util.Collections;
import java.util.Stack;
import java.util.stream.IntStream;

public class Deck {
    private final Stack<Card> deck;

    private Deck(final boolean shouldShuffle){
        this.deck = initDeck(shouldShuffle);
    }

    /**
     * Initializes a deck using a Stack
     * @return Stack with all cards
     */
    private Stack<Card> initDeck(boolean shouldShuffle){
        final Stack<Card> deck = new Stack<>();
        for (final Card.Suit suit:Card.Suit.values()){
            for (final Card.Rank rank:Card.Rank.values()){
                deck.push(Card.getCard(rank, suit));
            }
        }
        if (shouldShuffle) Collections.shuffle(deck);
        else Collections.sort(deck);
        return deck;
    }

    /**
     * Public method that returns cards off of top of deck
     * @return Top card if present, otherwise returns empty Optional instance
     */
    public Card deal(){
        if (!this.deck.empty()) return this.deck.pop();
        throw new RuntimeException(String.format("Deck is empty ! "));
    }

    /**
     * Print's out the first numCards cards
     */
    public static void main(String[] args){
        //final Deck deck = Deck.newShuffledDeck();
        final Deck deck = Deck.newUnshuffledDeck();
        final int numCards = 52;
        IntStream.range(0, numCards).forEach(value -> System.out.println(deck.deal()));
    }

    /**
     * Choose whether to shuffle deck or not
     */
    public static Deck newShuffledDeck(){return new Deck(true);}
    public static Deck newUnshuffledDeck(){return new Deck(false);}
}
