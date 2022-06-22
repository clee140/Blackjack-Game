// The Player class will model the player in a Blackjack game.
// The player will play the game until a winner is determined for the round.
// The player wins if their total points is exactly 21, have a higher point total than the dealer, or the dealer
// has busted (points > 21) and the player hasn't.

import java.util.ArrayList;

public class Player {
    private ArrayList<Card> playerHand;

    public Player() {
        playerHand = new ArrayList<Card>();
    }

    // addCard adds a card to the player's hand.
    public void addCard(Card aCard) {
        playerHand.add(aCard);
    }

    // removeCard removes the first card from the player's hand and returns it.
    public Card removeCard() {
        if (playerHand.size() > 0) {
            return playerHand.remove(0);
        }
        return null;
    }

    // hasCard returns true if player hand has at least 1 card.
    public boolean hasCard() {
        if (playerHand.size() > 0) {
            return true;
        }
        return false;
    }

    // getHandSum returns the maximum total sum of the player's hand.
    // If player's hand sum is greater than 21 and has at least one ace, the ace's value will count as 1.
    public int getHandSum() {
        int totalSum = 0;
        int aceCount = 0;
        for (Card aCard : playerHand) {
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

    // Determines if player initially wins by having exactly 21 points (Blackjack).
    public boolean hasBlackjack() {
        if (getHandSum() == 21 && playerHand.size() == 2) {
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

    // Convenience method that returns the player's hand.
    public String toString() {
        String handStr = "";
        for (Card aCard : playerHand) {
            handStr += aCard + ", ";
        }
        return handStr;
    }
}