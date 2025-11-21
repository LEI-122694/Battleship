package iscteiul.ista.battleship;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FrigateTest {

    @Test
    @DisplayName("Frigate should have size 4")
    void testSize() {
        Frigate f = new Frigate(Compass.NORTH, new Position(0, 0));
        assertEquals(4, f.getSize());
    }

    @Test
    @DisplayName("Frigate NORTH should occupy four consecutive rows in same column")
    void testNorthConstruction() {
        Frigate f = new Frigate(Compass.NORTH, new Position(2, 3));
        assertEquals(4, f.getPositions().size());
        for (int i = 0; i < 4; i++) {
            assertTrue(f.getPositions().contains(new Position(2 + i, 3)), "Expected position (" + (2 + i) + ",3)");
        }
        assertTrue(f.occupies(new Position(2, 3)));
    }

    @Test
    @DisplayName("Frigate SOUTH should occupy four consecutive rows in same column")
    void testSouthConstruction() {
        Frigate f = new Frigate(Compass.SOUTH, new Position(1, 4));
        assertEquals(4, f.getPositions().size());
        for (int i = 0; i < 4; i++) {
            assertTrue(f.getPositions().contains(new Position(1 + i, 4)));
        }
    }

    @Test
    @DisplayName("Frigate EAST should occupy four consecutive columns in same row")
    void testEastConstruction() {
        Frigate f = new Frigate(Compass.EAST, new Position(5, 2));
        assertEquals(4, f.getPositions().size());
        for (int i = 0; i < 4; i++) {
            assertTrue(f.getPositions().contains(new Position(5, 2 + i)));
        }
        assertEquals(5, f.getTopMostPos());
        assertEquals(5, f.getBottomMostPos());
        assertEquals(2, f.getLeftMostPos());
        assertEquals(5, f.getRightMostPos());
    }

    @Test
    @DisplayName("Frigate WEST should occupy four consecutive columns in same row")
    void testWestConstruction() {
        Frigate f = new Frigate(Compass.WEST, new Position(3, 1));
        assertEquals(4, f.getPositions().size());
        for (int i = 0; i < 4; i++) {
            assertTrue(f.getPositions().contains(new Position(3, 1 + i)));
        }
    }

    @Test
    @DisplayName("Shooting a position that belongs to the Frigate marks it hit and affects stillFloating")
    void testShootAndStillFloating() {
        Frigate f = new Frigate(Compass.EAST, new Position(0, 0));
        // Initially still floating
        assertTrue(f.stillFloating());

        // Shoot all positions
        for (IPosition p : f.getPositions()) {
            assertFalse(p.isHit());
            f.shoot(p);
            assertTrue(p.isHit());
        }

        // After all positions hit, stillFloating should be false
        assertFalse(f.stillFloating());
    }

    @Test
    @DisplayName("tooCloseTo should detect adjacency between ships")
    void testTooCloseTo() {
        Frigate f1 = new Frigate(Compass.EAST, new Position(0, 0));
        Frigate f2 = new Frigate(Compass.EAST, new Position(1, 0)); // adjacent by row
        assertTrue(f1.tooCloseTo(f2));

        Frigate f3 = new Frigate(Compass.EAST, new Position(5, 5));
        assertFalse(f1.tooCloseTo(f3));
    }

}