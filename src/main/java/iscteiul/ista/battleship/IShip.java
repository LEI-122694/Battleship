/**
 * Represents a ship in the Battleship game and defines the operations that
 * every concrete ship implementation must provide.
 * <p>
 * Implementations must track their occupied positions, provide size and
 * category information, and expose utilities used by the fleet and game
 * management code (position queries, shooting, adjacency checks, etc.).
 */
package iscteiul.ista.battleship;

import java.util.List;

public interface IShip {

    /** Returns the category/name of the ship (e.g. "Galeao", "Fragata"). */
    String getCategory();

    /** Returns the total number of cells occupied by this ship. */
    Integer getSize();

    /** Returns the list of positions occupied by this ship. */
    List<IPosition> getPositions();

    /** Returns the reference position used when the ship was created. */
    IPosition getPosition();

    /** Returns the current bearing/orientation of the ship. */
    Compass getBearing();

    /**
     * Returns true if the ship still has at least one non-hit position.
     *
     * @return true when the ship is not completely sunk
     */
    boolean stillFloating();

    /** Returns the top-most row index among the positions occupied by this ship. */
    int getTopMostPos();

    /** Returns the bottom-most row index among the positions occupied by this ship. */
    int getBottomMostPos();

    /** Returns the left-most column index among the positions occupied by this ship. */
    int getLeftMostPos();

    /** Returns the right-most column index among the positions occupied by this ship. */
    int getRightMostPos();

    /**
     * Checks whether this ship occupies the supplied position.
     *
     * @param pos position to test (must not be null)
     * @return true if one of the ship's positions equals {@code pos}
     */
    boolean occupies(IPosition pos);

    /**
     * Checks whether this ship is too close to another ship (adjacent in any
     * of the surrounding cells of any occupied position).
     *
     * @param other another ship to compare to (must not be null)
     * @return true when any occupied cell of this ship is adjacent to any
     * occupied cell of {@code other}
     */
    boolean tooCloseTo(IShip other);

    /**
     * Checks whether this ship is too close to a given board position.
     *
     * @param pos position to test
     * @return true when any occupied cell of this ship is adjacent to {@code pos}
     */
    boolean tooCloseTo(IPosition pos);

    /**
     * Registers a shot at the provided position; implementations should mark
     * the corresponding occupied position as hit when matching.
     *
     * @param pos position being shot (must not be null)
     */
    void shoot(IPosition pos);
}
