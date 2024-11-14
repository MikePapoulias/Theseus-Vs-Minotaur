A turn-based game written in Java language between two players who move on a board, each tasked with completing a specific mission.

# Description

This project, written in Java language, simulates a turn-based game between two characters—players Theseus and the Minotaur—on a board. The board represents a labyrinth within which the two characters can move in turns. The labyrinth is surrounded by walls that neither character can pass through. Additionally, there are walls placed within the labyrinth in a random arrangement for each game (the labyrinth is generated dynamically, but its configuration meets certain conditions). The two players can move horizontally and vertically, but not diagonally, and one tile at a time. On the board, there are also treasures that Theseus is tasked with collecting. Each treasure occupies only one tile of the board. The Minotaur cannot collect the treasures.

Theseus collects a treasure if he lands on the same tile as it. Similarly, the Minotaur kills Theseus if he lands on the same tile as him. The goal of the character Theseus is to collect all the treasures before the Minotaur kills him. If this is achieved, the game ends with Theseus’s victory. The goal of the character Minotaur is to kill Theseus before he manages to collect all the treasures in the labyrinth. The game ends with Minotaur as the winner if he kills Theseus.

The game has a maximum round limit, after which it ends in a draw if Theseus has not collected all the treasures and Minotaur has not killed Theseus.


