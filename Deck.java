// The Deck class will represent a single 52 card deck.
// 13 cards from 4 suits: Clubs, Diamonds, Hearts, Spades.
// Prior to the start of a new game, random cards will be selected and removed from the deck, equivalent to shuffling the
// cards.
// Used cards will be returned to the deck once the round has ended.

import java.util.ArrayList;

public class Deck {
    private ArrayList<Card> deck;

    public Deck() {
        deck = new ArrayList<Card>();
        initializeDeck();
    }

    // initializeDeck creates a standard 52 card deck with 4 suits and 13 cards/suit.
    private void initializeDeck() {
        String[] suits = {"Hearts", "Diamonds", "Clubs", "Spades"};
        String[] faces = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"};
        for (String suit : suits) {
            for (String face : faces) {
                switch (face) {
                    case "Jack":
                    case "Queen":
                    case "King":
                        deck.add(new Card(face, suit, 10));
                        break;
                    case "Ace":
                        deck.add(new Card(face, suit, 11));
                        break;
                    default:
                        deck.add(new Card(face, suit, Integer.parseInt(face)));
                        break;
                }
            }
        }
    }

    // getACard returns a random card and removes it from the deck.
    public Card getACard() {
        if (deck.size() == 0) {
            return null;
        }
        int index = (int) (Math.random() * deck.size());
        return deck.remove(index);
    }

    // Adds card back into the deck.
    public void addCard(Card aCard) {
        deck.add(aCard);
    }

    // Returns the deck size.
    public int getSize() {
        return deck.size();
    }

    // Convenience method that returns a string representation of the cards in the deck.
    public String toString() {
        String deckStr = "";
        for (Card aCard : deck) {
            deckStr += aCard + "\n";
        }
        return deckStr;
    }
}