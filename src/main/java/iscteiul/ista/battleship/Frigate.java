package iscteiul.ista.battleship;

/**
 * Represents a Frigate ("Fragata") ship in the Battleship game.
 * <p>
 * A Frigate occupies four consecutive board cells (size = 4). The exact cells
 * occupied depend on the provided {@link Compass} bearing and the initial
 * {@link IPosition}. This class extends {@link Ship} and initializes the
 * occupied positions in its constructor.
 */
public class Frigate extends Ship {
    /** Size of a Frigate (number of cells it occupies). */
    private static final Integer SIZE = 4;
    /** Localized name for the Frigate. */
    private static final String NAME = "Fragata";

    /**
     * Create and position a new Frigate.
     * <p>
     * The constructor computes and stores the positions occupied by the
     * Frigate according to the given bearing:
     * - NORTH or SOUTH: occupies four cells in consecutive rows starting at
     *   {@code pos.getRow()} and the same column.
     * - EAST or WEST: occupies four cells in consecutive columns starting at
     *   {@code pos.getColumn()} and the same row.
     *
     * @param bearing the bearing where the Frigate points to; must not be null
     * @param pos     initial point for positioning the Frigate (first cell)
     * @throws IllegalArgumentException if {@code bearing} is not a supported value
     */
    public Frigate(Compass bearing, IPosition pos) {
        super(Frigate.NAME, bearing, pos);
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
                throw new IllegalArgumentException("ERROR! invalid bearing for thr frigate");
        }
    }

    /**
     * Returns the size (number of cells) of this ship.
     *
     * @return the size of the frigate (4)
     */
    @Override
    public Integer getSize() {
        return Frigate.SIZE;
    }

}
