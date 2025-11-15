/**
 * Represents a cell position on the Battleship board.
 * <p>
 * Implementations store the row/column coordinates and expose operations to
 * query adjacency, occupancy and hit status as well as ways to mark a position
 * as occupied or shot. Positions are value objects used by ships and the game
 * engine to track board state.
 */
package iscteiul.ista.battleship;

/**
 * @author fba
 */
public interface IPosition {
    /** Returns the row index of this position (typically 0-based). */
    int getRow();

    /** Returns the column index of this position (typically 0-based). */
    int getColumn();

    /** Equality based on coordinates (row and column). */
    boolean equals(Object other);

    /**
     * Checks whether this position is adjacent to another position. Adjacent
     * usually means row and column difference no greater than 1.
     */
    boolean isAdjacentTo(IPosition other);

    /** Marks this position as occupied by a ship. */
    void occupy();

    /** Marks this position as having been shot. */
    void shoot();

    /** Returns true if a ship occupies this position. */
    boolean isOccupied();

    /** Returns true if this position has been hit by a shot. */
    boolean isHit();
}
