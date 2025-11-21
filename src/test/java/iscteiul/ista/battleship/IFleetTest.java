package iscteiul.ista.battleship;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

@DisplayName("IFleet tests via Fleet implementation")
class IFleetTest {

    IFleet fleet;
    Frigate frigate;
    Galleon galleon;

    @BeforeEach
    void setUp() {
        fleet = new Fleet(); // usamos Fleet como implementação concreta
        frigate = new Frigate(Compass.NORTH, new Position(0, 0));
        galleon = new Galleon(Compass.EAST, new Position(5, 5));
    }

    @Nested
    @DisplayName("AddShip tests")
    class AddShipTests {

        @Test
        @DisplayName("Should add a ship via IFleet interface")
        void testAddShip() {
            assertTrue(fleet.addShip(frigate));
            assertEquals(1, fleet.getShips().size());
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
        @DisplayName("getShips returns correct list")
        void testGetShips() {
            List<IShip> ships = fleet.getShips();
            assertEquals(2, ships.size());
            assertTrue(ships.contains(frigate));
            assertTrue(ships.contains(galleon));
        }

        @Test
        @DisplayName("getShipsLike filters by category")
        void testGetShipsLike() {
            List<IShip> galleons = fleet.getShipsLike("Galeao");
            assertEquals(1, galleons.size());
            assertTrue(galleons.contains(galleon));
        }

        @Test
        @DisplayName("getFloatingShips returns only floating ships")
        void testGetFloatingShips() {
            List<IShip> floating = fleet.getFloatingShips();
            assertEquals(2, floating.size());

            // afunda o frigate
            for (IPosition pos : frigate.getPositions()) {
                frigate.shoot(pos);
            }

            floating = fleet.getFloatingShips();
            assertEquals(1, floating.size());
            assertTrue(floating.contains(galleon));
        }

        @Test
        @DisplayName("shipAt returns correct ship or null")
        void testShipAt() {
            IShip s1 = fleet.shipAt(new Position(0, 0));
            assertEquals(frigate, s1);

            IShip s2 = fleet.shipAt(new Position(5, 5));
            assertEquals(galleon, s2);

            IShip s3 = fleet.shipAt(new Position(9, 9));
            assertNull(s3);
        }
    }

    @Nested
    @DisplayName("printStatus test")
    class PrintStatusTest {

        @Test
        @DisplayName("printStatus runs without exceptions")
        void testPrintStatus() {
            fleet.addShip(frigate);
            fleet.addShip(galleon);

            assertDoesNotThrow(() -> fleet.printStatus());
        }
    }
}
