package iscteiul.ista.battleship;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

@DisplayName("Fleet tests")
class FleetTest {

    Fleet fleet;
    Frigate frigate;
    Galleon galleon;

    @BeforeEach
    void setUp() {
        fleet = new Fleet();
        frigate = new Frigate(Compass.NORTH, new Position(0, 0));
        galleon = new Galleon(Compass.EAST, new Position(5, 5));
    }

    @Nested
    @DisplayName("AddShip tests")
    class AddShipTests {

        @Test
        @DisplayName("Should add a ship successfully if within constraints")
        void testAddShipSuccess() {
            assertTrue(fleet.addShip(frigate));
            assertEquals(1, fleet.getShips().size());
        }

        @Test
        @DisplayName("Should not add ship if fleet is full")
        void testAddShipFleetFull() {
            for (int i = 0; i < IFleet.FLEET_SIZE; i++) {
                fleet.addShip(new Frigate(Compass.NORTH, new Position(i, 0)));
            }
            assertFalse(fleet.addShip(frigate));
        }
    }

    @Nested
    @DisplayName("Query methods tests")
    class QueryTests {

        @BeforeEach
        void addShips() {
            fleet.addShip(frigate);
            fleet.addShip(galleon);
        }

        @Test
        @DisplayName("getShips should return all ships")
        void testGetShips() {
            List<IShip> ships = fleet.getShips();
            assertEquals(2, ships.size());
            assertTrue(ships.contains(frigate));
            assertTrue(ships.contains(galleon));
        }

        @Test
        @DisplayName("getShipsLike should filter by category")
        void testGetShipsLike() {
            List<IShip> galleons = fleet.getShipsLike("Galeao");
            assertEquals(1, galleons.size());
            assertTrue(galleons.contains(galleon));
        }

        @Test
        @DisplayName("getFloatingShips should return only ships not sunk")
        void testGetFloatingShips() {
            List<IShip> floating = fleet.getFloatingShips();
            assertEquals(2, floating.size());

            // sink frigate
            for (IPosition pos : frigate.getPositions()) {
                frigate.shoot(pos);
            }

            floating = fleet.getFloatingShips();
            assertEquals(1, floating.size());
            assertTrue(floating.contains(galleon));
        }

        @Test
        @DisplayName("shipAt should return the ship at a position")
        void testShipAt() {
            IShip s = fleet.shipAt(new Position(0, 0));
            assertEquals(frigate, s);

            IShip s2 = fleet.shipAt(new Position(5, 5));
            assertEquals(galleon, s2);

            IShip s3 = fleet.shipAt(new Position(9, 9));
            assertNull(s3);
        }
    }

    @Nested
    @DisplayName("Collision and boundary tests")
    class ConstraintTests {

        @Test
        @DisplayName("Should not add ship outside board")
        void testAddShipOutsideBoard() {
            Frigate badFrigate = new Frigate(Compass.EAST, new Position(9, 8)); // would go off board
            assertFalse(fleet.addShip(badFrigate));
        }

        @Test
        @DisplayName("Should not add ship too close to existing ship")
        void testAddShipTooClose() {
            fleet.addShip(frigate);
            Frigate closeFrigate = new Frigate(Compass.NORTH, new Position(1, 0)); // adjacent to frigate
            assertFalse(fleet.addShip(closeFrigate));
        }
    }
}
