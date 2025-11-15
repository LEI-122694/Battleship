package iscteiul.ista.battleship;

/**
 * Represents a Carrack ("Nau") ship in the Battleship game.
 * <p>
 * A Carrack occupies three consecutive board cells (size = 3). The exact cells
 * occupied depend on the provided {@link Compass} bearing and the initial
 * {@link IPosition}. This class extends {@link Ship} and sets up the occupied
 * positions in its constructor.
 */
public class Carrack extends Ship {
    /** Size of a Carrack (number of cells it occupies). */
    private static final Integer SIZE = 3;
    /** Localized name for the Carrack. */
    private static final String NAME = "Nau";

    /**
     * Create and position a new Carrack.
     * <p>
     * The constructor computes and stores the positions occupied by the
     * Carrack according to the given bearing:
     * - NORTH or SOUTH: occupies three cells in consecutive rows starting at
     *   {@code pos.getRow()} and the same column.
     * - EAST or WEST: occupies three cells in consecutive columns starting at
     *   {@code pos.getColumn()} and the same row.
     *
     * @param bearing the bearing where the Carrack points to; must not be null
     * @param pos     initial point for positioning the Carrack (first cell)
     * @throws IllegalArgumentException if {@code bearing} is not a supported value
     */
    public Carrack(Compass bearing, IPosition pos) {
        super(Carrack.NAME, bearing, pos);
        switch (bearing) {
            case NORTH:
            case SOUTH:
                for (int r = 0; r < SIZE; r++)
                    getPositions().add(new Position(pos.getRow() + r, pos.getColumn()));
                break;
            case EAST:
            case WEST:
                for (int c = 0; c < SIZE; c++)
                    getPositions().add(new Position(pos.getRow(), pos.getColumn() + c));
                break;
            default:
                throw new IllegalArgumentException("ERROR! invalid bearing for the carrack");
        }
    }

    /**
     * Returns the size (number of cells) of this ship.
     *
     * @return the size of the carrack (3)
     */
    @Override
    public Integer getSize() {
        return Carrack.SIZE;
    }

}
