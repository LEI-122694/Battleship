/**
 * Represents a collection of ships (fleet) and provides operations to manage
 * and query ships placed on the Battleship board.
 * <p>
 * Implementations are responsible for enforcing placement constraints (board
 * bounds, no collisions / too-close placement) and exposing query methods used
 * by the game engine and UI tasks.
 */
package iscteiul.ista.battleship;

import java.util.List;

public interface IFleet {
    /** Number of rows/columns of the board. */
    Integer BOARD_SIZE = 10;
    /** Maximum number of ships in a fleet. */
    Integer FLEET_SIZE = 10;

    /** Returns the list of ships currently registered in the fleet. */
    List<IShip> getShips();

    /** Attempts to add a ship to the fleet. Returns true when added. */
    boolean addShip(IShip s);

    /**
     * Returns the ships whose category matches the provided category string.
     *
     * @param category category to filter by
     */
    List<IShip> getShipsLike(String category);

    /** Returns a list with the ships that are still floating (not sunk). */
    List<IShip> getFloatingShips();

    /**
     * Returns the ship occupying the given position or null when none does.
     *
     * @param pos the board position to query
     */
    IShip shipAt(IPosition pos);

    /** Prints a concise status report about the fleet. */
    void printStatus();
}
