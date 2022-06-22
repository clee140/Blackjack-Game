// The Dealer class will model the dealer in the game.
// The dealer will distribute the cards and will take "hits" if necessary (point value is under 17).
// The dealer wins if their total points is exactly 21, have a higher point total than the player, or the player
// has busted (points > 21) and the dealer hasn't.

import java.util.ArrayList;

public class Dealer {
    private ArrayList<Card> dealerHand;

    public Dealer() {
        dealerHand = new ArrayList<Card>();
    }

    // addCard adds a card to the dealer's hand.
    public void addCard(Card aCard) {
        dealerHand.add(aCard);
    }

    // removeCard removes the first card from the dealer's hand and returns it.
    public Card removeCard() {
        if (dealerHand.size() > 0) {
            return dealerHand.remove(0);
        }
        return null;
    }

    // hasCard returns true if dealer hand has at least 1 card.
    public boolean hasCard() {
        if (dealerHand.size() > 0) {
            return true;
        }
        return false;
    }

    // getHandSum returns the maximum total sum of the dealer's hand.
    // If dealer's hand is greater than 21 and has at least one ace, the ace's value will count as 1.
    public int getHandSum() {
        int totalSum = 0;
        int aceCount = 0;
        for (Card aCard : dealerHand) {
            totalSum += aCard.getRank();
            if (aCard.getRank() == 11) {
                aceCount++;
            }
        }
        for (int i = 0; i < aceCount; i++) {
            if (totalSum > 21) {
                totalSum -= 10;
            }
        }
        return totalSum;
    }

    // Determines if dealer initially wins by having exactly 21 points (Blackjack).
    public boolean hasBlackjack() {
        if (getHandSum() == 21 && dealerHand.size() == 2) {
            return true;
        }
        return false;
    }

    // Determines if the hand has busted if hand is greater than 21.
    public boolean hasBusted() {
        if (getHandSum() > 21) {
            return true;
        }
        return false;
    }

    // Determines if the dealer is allowed to hit.  Dealer must hit if the hand sum is less than 17.
    public boolean allowedToHit() {
        if (getHandSum() < 17) {
            return true;
        }
        return false;
    }

    // Returns the first card of the dealer's hand.
    public Card getFirstCard() {
        return dealerHand.get(0);
    }

    // Convenience method that returns the dealer's hand.
    public String toString() {
        String handStr = "";
        for (Card aCard : dealerHand) {
            handStr += aCard + ", ";
        }
        return handStr;
    }
}