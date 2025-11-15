package iscteiul.ista.battleship;

/**
 * Compass directions used to orient ships on the board.
 * <p>
 * Each enum constant has an associated character code used for I/O and parsing:
 * 'n' (north), 's' (south), 'e' (east), 'o' (west), and 'u' (unknown).
 * <p>
 * Provides utility methods to obtain the direction character and to parse a
 * character into a corresponding {@code Compass} value.
 *
 * @author fba
 */
public enum Compass {
    NORTH('n'), SOUTH('s'), EAST('e'), WEST('o'), UNKNOWN('u');

    private final char c;

    Compass(char c) {
        this.c = c;
    }

    /**
     * Returns the character code that represents this compass direction.
     *
     * @return a single character code ('n','s','e','o','u')
     */
    public char getDirection() {
        return c;
    }

    /**
     * Returns the single-character string representation of this direction.
     *
     * @return the single-char representation of this direction
     */
    @Override
    public String toString() {
        return "" + c;
    }

    /**
     * Converts a single character into the corresponding {@code Compass}
     * enum constant. Accepts lower-case direction codes; any unrecognized
     * character yields {@link #UNKNOWN}.
     *
     * @param ch character code ('n','s','e','o')
     * @return the corresponding {@code Compass} value, or {@code UNKNOWN}
     */
    static Compass charToCompass(char ch) {
        Compass bearing;
        switch (ch) {
            case 'n':
                bearing = NORTH;
                break;
            case 's':
                bearing = SOUTH;
                break;
            case 'e':
                bearing = EAST;
                break;
            case 'o':
                bearing = WEST;
                break;
            default:
                bearing = UNKNOWN;
        }

        return bearing;
    }
}
