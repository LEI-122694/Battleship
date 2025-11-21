package iscteiul.ista.battleship;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Galleon unit tests")
class GalleonTest {

    @Test
    @DisplayName("Galleon should have size 5")
    void testSize() {
        Galleon g = new Galleon(Compass.NORTH, new Position(0, 0));
        assertEquals(5, g.getSize());
    }

    @Test
    @DisplayName("Galleon NORTH shape")
    void testNorthShape() {
        Galleon g = new Galleon(Compass.NORTH, new Position(2, 3));
        assertEquals(5, g.getPositions().size());
        assertTrue(g.getPositions().contains(new Position(2, 3)));
        assertTrue(g.getPositions().contains(new Position(2, 4)));
        assertTrue(g.getPositions().contains(new Position(2, 5)));
        assertTrue(g.getPositions().contains(new Position(3, 4)));
        assertTrue(g.getPositions().contains(new Position(4, 4)));
        assertEquals(2, g.getTopMostPos());
        assertEquals(4, g.getBottomMostPos());
    }

    @Test
    @DisplayName("Galleon SOUTH shape")
    void testSouthShape() {
        Galleon g = new Galleon(Compass.SOUTH, new Position(1, 2));
        assertEquals(5, g.getPositions().size());
        assertTrue(g.getPositions().contains(new Position(1, 2)));
        assertTrue(g.getPositions().contains(new Position(2, 2)));
        assertTrue(g.getPositions().contains(new Position(3, 1)));
        assertTrue(g.getPositions().contains(new Position(3, 2)));
        assertTrue(g.getPositions().contains(new Position(3, 3)));
        assertEquals(1, g.getTopMostPos());
        assertEquals(3, g.getBottomMostPos());
    }

    @Test
    @DisplayName("Galleon EAST shape")
    void testEastShape() {
        Galleon g = new Galleon(Compass.EAST, new Position(4, 5));
        assertEquals(5, g.getPositions().size());
        assertTrue(g.getPositions().contains(new Position(4, 5)));
        assertTrue(g.getPositions().contains(new Position(5, 3)));
        assertTrue(g.getPositions().contains(new Position(5, 4)));
        assertTrue(g.getPositions().contains(new Position(5, 5)));
        assertTrue(g.getPositions().contains(new Position(6, 5)));
        assertEquals(4, g.getTopMostPos());
        assertEquals(6, g.getBottomMostPos());
    }

    @Test
    @DisplayName("Galleon WEST shape")
    void testWestShape() {
        Galleon g = new Galleon(Compass.WEST, new Position(3, 2));
        assertEquals(5, g.getPositions().size());
        assertTrue(g.getPositions().contains(new Position(3, 2)));
        assertTrue(g.getPositions().contains(new Position(4, 2)));
        assertTrue(g.getPositions().contains(new Position(4, 3)));
        assertTrue(g.getPositions().contains(new Position(4, 4)));
        assertTrue(g.getPositions().contains(new Position(5, 2)));
    }

    @Test
    @DisplayName("Shooting Galleon and stillFloating behavior")
    void testShootAndStillFloating() {
        Galleon g = new Galleon(Compass.NORTH, new Position(0, 0));
        assertTrue(g.stillFloating());
        // shoot all positions except last
        for (int i = 0; i < g.getPositions().size() - 1; i++) {
            IPosition p = g.getPositions().get(i);
            g.shoot(p);
            assertTrue(p.isHit());
            assertTrue(g.stillFloating());
        }
        // shoot last
        IPosition last = g.getPositions().get(g.getPositions().size() - 1);
        g.shoot(last);
        assertTrue(last.isHit());
        assertFalse(g.stillFloating());
    }

    @Test
    @DisplayName("tooCloseTo detects adjacency between galleon and other ship")
    void testTooCloseTo() {
        Galleon g1 = new Galleon(Compass.NORTH, new Position(0, 0));
        Galleon g2 = new Galleon(Compass.NORTH, new Position(2, 1)); // should be adjacent
        assertTrue(g1.tooCloseTo(g2));

        Galleon g3 = new Galleon(Compass.NORTH, new Position(10, 10));
        assertFalse(g1.tooCloseTo(g3));
    }

}