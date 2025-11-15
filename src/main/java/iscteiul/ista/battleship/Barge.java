/**
 * Represents a Barge ship in the Battleship game.
 * <p>
 * A Barge occupies a single board cell (size = 1). This class is a concrete
 * implementation of {@link Ship} and provides the ship name and size.
 */
package iscteiul.ista.battleship;

/**
 * Represents a Barge ship in the Battleship game.
 * <p>
 * A Barge occupies a single board cell (size = 1). This class is a concrete
 * implementation of {@link Ship} and provides the ship name and size.
 */
public class Barge extends Ship {
    /** Size of a Barge (number of cells it occupies). */
    private static final Integer SIZE = 1;
    /** Localized name for the Barge. */
    private static final String NAME = "Barca";

    /**
     * Create a new Barge.
     *
     * @param bearing the orientation of the barge
     * @param pos     the upper-left position of the barge on the board
     */
    public Barge(Compass bearing, IPosition pos) {
        super(Barge.NAME, bearing, pos);
        getPositions().add(new Position(pos.getRow(), pos.getColumn()));
    }

    /**
     * Returns the size (number of cells) of this ship.
     *
     * @return the size of the barge (1)
     */
    @Override
    public Integer getSize() {
        return SIZE;
    }

}
