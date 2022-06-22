// The Card class represents an ordinary playing card.
// The card has a rank (2-Ace) and a suit (Spades, Hearts, Clubs, Diamonds).
// Each card in the deck has a unique rank and suit.

public class Card {
    private String suit;
    private String face;
    private int rank;

    // Constructor that initializes the suit, face, and rank attributes.
    // The attributes suit, face, and rank attributes can't be updated once they've been initialized.
    public Card(String suit, String face, int rank) {
        this.suit = suit;
        this.face = face;
        this.rank = rank;
    }

    // Getter methods that read the class attributes.
    public String getSuit() {
        return suit;
    }

    public String getFace() {
        return face;
    }

    public int getRank() {
        return rank;
    }

    // Convenience method that returns a string with the rank and suit.
    public String toString() {
        return suit + " of " + face;
    }
}