# Blackjack-Game
The program will model a simplified version of the card game, Blackjack. The card game will consist of a player and a
dealer (the computer). A player is dealt cards, called a hand, as is the dealer.

The objective of the game is to get as close to 21, without exceeding that number. A player that goes over
loses the game. The game proceeds as the player is dealt two cards faced up. If the player has exactly 21, called
a Blackjack, the player wins. If the player doesn't have 21, the dealer is dealt two cards, one face up and one
face down.
A player then determines whether to ask the dealer for another card, called a “hit”, or to “stay” with the current
hand. A player may ask for several “hits” without exceeding 21 or "busting". When a player decides to "stay”, the
dealer begins to play.
If the dealer has 21 or a Blackjack, the dealer immediately wins the game. Otherwise, the dealer must “hit” until the
total points the is at least 17, at which point the dealer must “stay.” For this implementation, the dealer will stay
on a soft 17. If the dealer goes over 21 or busts, the game is over and the player wins.
The player with the highest total closest to 21 is the winner.

A player will start with 1000 chips and be allowed to bet. The dealer (house) has unlimited chips. If the player
wins or loses the round, the chip balance carries forward to the next round until there is a new game. The game
ends when the player runs out of chips or quits. A player is given the option of continuing with the same deck or a
new deck.
