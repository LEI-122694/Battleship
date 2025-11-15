package iscteiul.ista.battleship;

import java.util.ArrayList;
import java.util.List;

/**
 * Manages a collection of ships (a fleet) on the Battleship board.
 * <p>
 * The Fleet enforces placement constraints (fits inside the board and does not
 * collide or get too close to existing ships) and provides query and display
 * utilities over the registered {@link IShip} instances.
 */
public class Fleet implements IFleet {
    /**
     * This operation prints all the given ships
     *
     * @param ships The list of ships
     */
    static void printShips(List<IShip> ships) {
        for (IShip ship : ships)
            System.out.println(ship);
    }

    // -----------------------------------------------------

    private List<IShip> ships;

    /**
     * Create an empty Fleet.
     */
    public Fleet() {
        ships = new ArrayList<>();
    }

    /**
     * Returns the list of ships currently in this fleet. The returned list is
     * the internal collection used by the fleet.
     *
     * @return list of ships
     */
    @Override
    public List<IShip> getShips() {
        return ships;
    }

    /*
     * (non-Javadoc)
     *
     * @see battleship.IFleet#addShip(battleship.IShip)
     */
    @Override
    public boolean addShip(IShip s) {
        boolean result = false;
        if ((ships.size() <= FLEET_SIZE) && (isInsideBoard(s)) && (!colisionRisk(s))) {
            ships.add(s);
            result = true;
        }
        return result;
    }

    /*
     * (non-Javadoc)
     *
     * @see battleship.IFleet#getShipsLike(java.lang.String)
     */
    @Override
    public List<IShip> getShipsLike(String category) {
        List<IShip> shipsLike = new ArrayList<>();
        for (IShip s : ships)
            if (s.getCategory().equals(category))
                shipsLike.add(s);

        return shipsLike;
    }

    /*
     * (non-Javadoc)
     *
     * @see battleship.IFleet#getFloatingShips()
     */
    @Override
    public List<IShip> getFloatingShips() {
        List<IShip> floatingShips = new ArrayList<>();
        for (IShip s : ships)
            if (s.stillFloating())
                floatingShips.add(s);

        return floatingShips;
    }

    /*
     * (non-Javadoc)
     *
     * @see battleship.IFleet#shipAt(battleship.IPosition)
     */
    @Override
    public IShip shipAt(IPosition pos) {
        for (int i = 0; i < ships.size(); i++)
            if (ships.get(i).occupies(pos))
                return ships.get(i);
        return null;
    }

    private boolean isInsideBoard(IShip s) {
        return (s.getLeftMostPos() >= 0 && s.getRightMostPos() <= BOARD_SIZE - 1 && s.getTopMostPos() >= 0
                && s.getBottomMostPos() <= BOARD_SIZE - 1);
    }

    private boolean colisionRisk(IShip s) {
        for (int i = 0; i < ships.size(); i++) {
            if (ships.get(i).tooCloseTo(s))
                return true;
        }
        return false;
    }


    /**
     * Prints a concise status of this fleet: lists ships, floating ships and
     * counts per category.
     */
    public void printStatus() {
        printAllShips();
        printFloatingShips();
        printShipsByCategory("Galeao");
        printShipsByCategory("Fragata");
        printShipsByCategory("Nau");
        printShipsByCategory("Caravela");
        printShipsByCategory("Barca");
    }

    /**
     * Prints all the ships of a fleet belonging to a particular category.
     *
     * @param category The category of ships of interest (must not be null)
     */
    public void printShipsByCategory(String category) {
        assert category != null;

        printShips(getShipsLike(category));
    }

    /**
     * Prints all ships in the fleet that are still floating (not sunk).
     */
    public void printFloatingShips() {
        printShips(getFloatingShips());
    }

    /**
     * Prints all the ships in this fleet.
     */
    void printAllShips() {
        printShips(ships);
    }

}
