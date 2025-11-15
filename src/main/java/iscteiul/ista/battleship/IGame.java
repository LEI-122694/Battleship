/**
 *
 */
package iscteiul.ista.battleship;

import java.util.List;

/**
 * Defines the operations of a Battleship game session.
 * <p>
 * Implementations track fired shots, invalid/repeated shot counts, hits and
 * sunk ships and provide utilities for printing the current game state.
 */
public interface IGame {
    /** Fire a shot at the given board position. Returns the ship sunk (if any). */
    IShip fire(IPosition pos);

    /** Returns the list of valid shots that have been fired in this game. */
    List<IPosition> getShots();

    /** Returns the number of repeated shots that were attempted. */
    int getRepeatedShots();

    /** Returns the number of invalid shots that were attempted. */
    int getInvalidShots();

    /** Returns the total number of successful hits recorded. */
    int getHits();

    /** Returns the number of ships that have been sunk. */
    int getSunkShips();

    /** Returns the number of ships still remaining (not sunk). */
    int getRemainingShips();

    /** Prints a board view showing valid shots that have been fired. */
    void printValidShots();

    /** Prints a board view showing the fleet positions. */
    void printFleet();
}
