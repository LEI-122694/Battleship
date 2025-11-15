package iscteiul.ista.battleship;

/**
 * Represents a Caravel ship in the Battleship game.
 * <p>
 * A Caravel occupies two consecutive board cells (size = 2). The exact cells
 * occupied depend on the provided {@link Compass} bearing and the initial
 * {@link IPosition}. This class extends {@link Ship} and initializes the ship's
 * occupied positions in its constructor.
 */
public class Caravel extends Ship {
    /** Size of a Caravel (number of cells it occupies). */
    private static final Integer SIZE = 2;
    /** Localized name for the Caravel. */
    private static final String NAME = "Caravela";

    /**
     * Create and position a new Caravel.
     * <p>
     * The constructor computes and stores the positions occupied by the
     * Caravel according to the given bearing:
     * - NORTH or SOUTH: occupies two cells in consecutive rows starting at
     *   {@code pos.getRow()} and the same column.
     * - EAST or WEST: occupies two cells in consecutive columns starting at
     *   {@code pos.getColumn()} and the same row.
     *
     * @param bearing the bearing where the Caravel heads to; must not be null
     * @param pos     initial point for positioning the Caravel (first cell)
     * @throws IllegalArgumentException if {@code bearing} is not a supported value
     */
    public Caravel(Compass bearing, IPosition pos) {
        super(Caravel.NAME, bearing, pos);

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
                throw new IllegalArgumentException("ERROR! invalid bearing for the caravel");
        }

    }

    /**
     * Returns the size (number of cells) of this ship.
     *
     * @return the size of the caravel (2)
     */
    @Override
    public Integer getSize() {
        return SIZE;
    }

}
