// The program will model a simplified version of the card game, Blackjack. The card game will consist of a player and a
// dealer (the computer). A player is dealt cards, called a hand, as is the dealer.
//
// The objective of the game is to get as close to 21, without exceeding that number. A player that goes over
// loses the game. The game proceeds as the player is dealt two cards faced up. If the player has exactly 21, called
// a Blackjack, the player wins. If the player doesn't have 21, the dealer is dealt two cards, one face up and one
// face down.
// A player then determines whether to ask the dealer for another card, called a “hit”, or to “stay” with the current
// hand. A player may ask for several “hits” without exceeding 21 or "busting". When a player decides to "stay”, the
// dealer begins to play.
// If the dealer has 21 or a Blackjack, the dealer immediately wins the game. Otherwise, the dealer must “hit” until the
// total points the is at least 17, at which point the dealer must “stay.” For this implementation, the dealer will stay
// on a soft 17. If the dealer goes over 21 or busts, the game is over and the player wins.
// The player with the highest total closest to 21 is the winner.
//
// A player will start with 1000 chips and be allowed to bet. The dealer (house) has unlimited chips. If the player
// wins or loses the round, the chip balance carries forward to the next round until there is a new game. The game
// ends when the player runs out of chips or quits. A player is given the option of continuing with the same deck or a
// new deck.

import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    private Deck aDeck;
    private Player aPlayer;
    private Dealer aDealer;
    private int playerBank;
    private ArrayList<Card> discardDeck;
    private boolean gameStatus;

    public Game() {
        aDeck = new Deck();  // Initialize playing deck
        discardDeck = new ArrayList<Card>();  // Initialize storage for discarded cards
        aPlayer = new Player();  // Initialize player object
        aDealer = new Dealer();  // Initialize dealer object
        playerBank = 1000;  // Initial chips given to the player
        gameStatus = false;  // Initial game state
    }

    // Discard played cards from the player and dealer hands in a separate discard deck.
    private void useCurrentDeck() {
        while (aPlayer.hasCard()) {
            discardDeck.add(aPlayer.removeCard());
        }
        while (aDealer.hasCard()) {
            discardDeck.add(aDealer.removeCard());
        }
    }

    // hasDiscardCards returns true if discard deck has at least 1 card.
    private boolean hasDiscardCards() {
        if (discardDeck.size() > 0) {
            return true;
        }
        return false;
    }

    // removeDiscardCard removes the first or "top" card from the the discard deck and returns it.
    private Card removeDiscardCard() {
        if (discardDeck.size() > 0) {
            return discardDeck.remove(0);
        }
        return null;
    }

    // Add both the played cards in the current round and the discarded cards from previous games back to the playing deck.
    private void useNewDeck() {
        while (aPlayer.hasCard()) {
            aDeck.addCard(aPlayer.removeCard());
        }

        while (aDealer.hasCard()) {
            aDeck.addCard(aDealer.removeCard());
        }

        while (hasDiscardCards()) {
            aDeck.addCard(removeDiscardCard());
        }
    }

    // Setter method that updated the endGame class attribute.  The game is ended when a winner is determined.  For
    // example, the game is over when the player has busted, so there is no need to evaluate the dealer's hand.
    private void setGameOver(boolean endGame) {
        gameStatus = endGame;
    }

    // Determines if player or dealer has won yet.
    private boolean isGameOver() {
        if (gameStatus) {
            return true;
        }
        return false;
    }

    // Determines if a player's bet is valid. Returns false if bet exceeds the available chips, is not a whole number,
    // or is negative.
    private Boolean isValidBet(int playerBet) {
        if (playerBet > playerBank) {
            System.out.println("Bet exceeds the available chips!");
            return false;
        } else if ((playerBet <= 0)) {
            System.out.println("Bet must be at least 1 or more!");
            return false;
        }
        return true;
    }

    // Prints the player's hand and the corresponding hand value.
    public void printPlayerHand() {
        System.out.println("Player's hand: " + aPlayer);
        System.out.println("Player's hand value: " + aPlayer.getHandSum());
    }

    // Prints the dealer's hand and the corresponding hand value.
    public void printDealerHand() {
        System.out.println("Dealer's hand: " + aDealer);
        System.out.println("Dealer's hand value: " + aDealer.getHandSum());
    }

    // A player will start with 1000 chips and be allowed to bet. The dealer (house) has unlimited chips. If the player
    // wins or loses the round, the chip balance carries forward to the next round until there is a new game. The game
    // ends when the player runs out of chips or quits.
    public void playGame() {
        Scanner input = new Scanner(System.in);
        boolean doAgain = true;

        // Print out the player's starting bank.
        System.out.println("Player bank: " + playerBank);

        while (doAgain) {
            // Allows player to bet chips.
            int playerBet = 0;
            do {
                try {
                    System.out.print("Player bet: ");
                    playerBet = input.nextInt();
                }
                catch (java.util.InputMismatchException e) {
                    System.out.println("Invalid bet value! Try again.");
                    input.nextLine();
                }
            } while (!isValidBet(playerBet));

            // Both Dealer and Player get two cards.
            for (int i = 0; i < 2; i++) {
                aDealer.addCard(aDeck.getACard());
                aPlayer.addCard(aDeck.getACard());
            }

            // Print Dealer and Player hands. Dealer's hand is dealt one card face down and one card face up.
            System.out.println("Dealer's hand: " + aDealer.getFirstCard() + ", FACE_DOWN");
            System.out.println();
            printPlayerHand();

            ////////////////////////////////////////////////////////////////////////////////////////////////////////////
            // Player and Dealer play their hands
            ////////////////////////////////////////////////////////////////////////////////////////////////////////////

            // The following code handles several game scenarios related to the player's turn:
            // 1. Player was dealt Blackjack and Dealer wasn't, the Player wins.
            // 2. Dealer was dealt Blackjack and Player wasn't, the Dealer wins.
            // 3. Both Player and Dealer were dealt Blackjacks, both players push.
            // 4. Player is allowed to hit as many times to maximize the hand value.  If the Player's hand has busted,
            //    the Dealer wins.
            if (aPlayer.hasBlackjack()) {
                if (!aDealer.hasBlackjack()) {
                    printDealerHand();
                    System.out.println("Player has won this game!");
                    playerBank += playerBet;
                } else {
                    printDealerHand();
                    System.out.println("Player and Dealer push!");
                }
                setGameOver(true);
            } else if (aDealer.hasBlackjack()) {
                printDealerHand();
                System.out.println("Dealer has won this game!");
                playerBank -= playerBet;
                setGameOver(true);
            } else {
                // Allow the player to hit or stay as many times to maximize the hand value.
                while (doAgain) {
                    System.out.print("Type 'h' to hit or 's' to stay: ");
                    String nextMove = input.next();
                    if (nextMove.equals("h")) {
                        aPlayer.addCard(aDeck.getACard());
                        printPlayerHand();
                    } else if (nextMove.equals("s")) {
                        doAgain = false;
                    }
                    if (aPlayer.hasBusted()) {
                        doAgain = false;
                        playerBank -= playerBet;
                        setGameOver(true);
                        printDealerHand();
                        System.out.println("Dealer has won this game!");
                    }
                }
            }

            // Neither player or dealer were dealt a Blackjack, so dealer keeps hitting to maximize the hand value until
            // the hand is at least 17, including soft 17. If the dealer has busted, the player wins.
            if (!isGameOver()) {
                doAgain = true;
                while (doAgain) {
                    if (aDealer.allowedToHit()) {
                        printDealerHand();
                        aDealer.addCard(aDeck.getACard());
                    } else {
                        doAgain = false;
                    }
                    if (aDealer.hasBusted()) {
                        doAgain = false;
                        playerBank += playerBet;
                        printDealerHand();
                        System.out.println("The Player has won this game!");
                    }
                }

                // Evaluate both the Dealer and Player's hands to determine which one is the winner.
                // 1. If Player has a higher hand than the Dealer, the Player wins.
                // 2. If Dealer has a higher hand than the Player, the Dealer wins.
                // 3. Both Player and Dealer push if they have the same hand value.
                if (!aPlayer.hasBusted() && !aDealer.hasBusted()) {
                    if (aPlayer.getHandSum() > aDealer.getHandSum()) {
                        playerBank += playerBet;
                        printDealerHand();
                        System.out.println("Player has won this game!");
                    } else if (aDealer.getHandSum() > aPlayer.getHandSum()) {
                        playerBank -= playerBet;
                        printDealerHand();
                        System.out.println("Dealer has won this game!");
                    } else {
                        printDealerHand();
                        System.out.println("Player and Dealer push!");
                    }
                }
            }

            ////////////////////////////////////////////////////////////////////////////////////////////////////////////
            // Evaluate if player can or wants to continue play with the same or new deck
            ////////////////////////////////////////////////////////////////////////////////////////////////////////////

            // Prints out the player's bank and asks the player if they want to play again.
            // If player doesn't have any chips remaining, the game ends.
            System.out.println("Player bank: " + playerBank);
            // The player loses the game if their bank is less than or equal to 0.
            if (playerBank <= 0) {
                setGameOver(true);
                System.out.println("Player has no more chips! The game is over.");
                break;
            }
            // The player has the option of playing with a new deck or using the same deck.
            // If there are not enough cards to complete a game, a new deck will automatically be used.
            System.out.print("Play again: (1 = yes, 2 = no): ");
            doAgain = input.nextInt() == 1;
            if (doAgain) {
                if (aDeck.getSize() <= 15) {
                    System.out.println("There are not enough cards to complete the game. A new deck will be used.");
                    useNewDeck();
                } else {
                    System.out.print("Use new deck: (1 = yes, 2 = no): ");
                    int useNewDeckStatus = input.nextInt();
                    if (useNewDeckStatus == 1) {
                        useNewDeck();
                    } else {
                        useCurrentDeck();
                    }
                    setGameOver(false);
                }
            }
        }
    }
}