package iscteiul.ista.battleship;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DisplayName("Testes para o comportamento genérico de Ship")
public class ShipTest {

    private Ship ship;

    @BeforeEach
    void setUp() {
        ship = new Caravel(Compass.EAST, new Position(3, 4));
    }

    @AfterEach
    void tearDown() {
        ship = null;
    }

    // --------------------------------------------------------------
    // getCategory()
    // --------------------------------------------------------------
    @Test
    @DisplayName("getCategory devolve a categoria correta")
    void testGetCategory() {
        assertEquals("caravela", ship.getCategory().toLowerCase(),
                "Categoria deve ser 'caravela'");
    }

    // --------------------------------------------------------------
    // getBearing()
    // --------------------------------------------------------------
    @Test
    @DisplayName("getBearing devolve a orientação correta")
    void testGetBearing() {
        assertEquals(Compass.EAST, ship.getBearing(),
                "A Caravel deve estar virada a EAST");
    }

    // --------------------------------------------------------------
    // getPosition()
    // --------------------------------------------------------------
    @Test
    @DisplayName("getPosition devolve a posição base usada na criação")
    void testGetPosition() {
        assertEquals(new Position(3, 4), ship.getPosition(),
                "A posição base deve ser (3,4)");
    }

    // --------------------------------------------------------------
    // getPositions()
    // --------------------------------------------------------------
    @Test
    @DisplayName("getPositions devolve as posições corretas ocupadas pelo navio")
    void testGetPositions() {
        assertAll(
                () -> assertEquals(2, ship.getPositions().size(),
                        "Caravel ocupa 2 posições"),
                () -> assertEquals(new Position(3, 4), ship.getPositions().get(0)),
                () -> assertEquals(new Position(3, 5), ship.getPositions().get(1))
        );
    }

    // --------------------------------------------------------------
    // stillFloating()
    // --------------------------------------------------------------
    @Test
    @DisplayName("stillFloating é true enquanto pelo menos uma posição não estiver atingida")
    void testStillFloatingTrue() {
        ship.shoot(new Position(3, 4)); // acertar 1 célula
        assertTrue(ship.stillFloating(), "Ainda deve estar a flutuar");
    }

    @Test
    @DisplayName("stillFloating é false quando todas as posições estão atingidas")
    void testStillFloatingFalse() {
        ship.shoot(new Position(3, 4));
        ship.shoot(new Position(3, 5));
        assertFalse(ship.stillFloating(), "Navio deve estar afundado");
    }

    // --------------------------------------------------------------
    // getTopMostPos / getBottomMostPos
    // --------------------------------------------------------------
    @Test
    @DisplayName("getTopMostPos funciona corretamente")
    void testTopMostPos() {
        assertEquals(3, ship.getTopMostPos());
    }

    @Test
    @DisplayName("getBottomMostPos funciona corretamente")
    void testBottomMostPos() {
        assertEquals(3, ship.getBottomMostPos());
    }

    // --------------------------------------------------------------
    // getLeftMostPos / getRightMostPos
    // --------------------------------------------------------------
    @Test
    @DisplayName("getLeftMostPos funciona corretamente")
    void testLeftMostPos() {
        assertEquals(4, ship.getLeftMostPos());
    }

    @Test
    @DisplayName("getRightMostPos funciona corretamente")
    void testRightMostPos() {
        assertEquals(5, ship.getRightMostPos());
    }

    // --------------------------------------------------------------
    // occupies()
    // --------------------------------------------------------------
    @Test
    @DisplayName("occupies devolve true para células ocupadas")
    void testOccupiesTrue() {
        assertTrue(ship.occupies(new Position(3, 4)));
        assertTrue(ship.occupies(new Position(3, 5)));
    }

    @Test
    @DisplayName("occupies devolve false para células não ocupadas")
    void testOccupiesFalse() {
        assertFalse(ship.occupies(new Position(0, 0)));
    }

    // --------------------------------------------------------------
    // tooCloseTo(IPosition)
    // --------------------------------------------------------------
    @Test
    @DisplayName("tooCloseTo(IPosition) devolve true quando posição está adjacente")
    void testTooCloseToPositionTrue() {
        assertTrue(ship.tooCloseTo(new Position(3, 6)),
                "A posição (3,6) é adjacente à Caravel");
    }

    @Test
    @DisplayName("tooCloseTo(IPosition) devolve false quando posição está distante")
    void testTooCloseToPositionFalse() {
        assertFalse(ship.tooCloseTo(new Position(0, 0)));
    }

    // --------------------------------------------------------------
    // tooCloseTo(IShip)
    // --------------------------------------------------------------
    @Test
    @DisplayName("tooCloseTo(IShip) devolve true quando navios estão adjacentes")
    void testTooCloseToShipTrue() {
        IShip other = new Caravel(Compass.EAST, new Position(3, 6));
        assertTrue(ship.tooCloseTo(other));
    }

    @Test
    @DisplayName("tooCloseTo(IShip) devolve false quando navios estão afastados")
    void testTooCloseToShipFalse() {
        IShip other = new Caravel(Compass.NORTH, new Position(0, 0));
        assertFalse(ship.tooCloseTo(other));
    }

    // --------------------------------------------------------------
    // shoot()
    // --------------------------------------------------------------
    @Test
    @DisplayName("shoot marca posição como atingida quando coincide")
    void testShootHit() {
        ship.shoot(new Position(3, 4));
        assertTrue(ship.getPositions().get(0).isHit());
    }

    @Test
    @DisplayName("shoot não marca nada quando falha")
    void testShootMiss() {
        ship.shoot(new Position(9, 9));
        assertFalse(ship.getPositions().get(0).isHit());
        assertFalse(ship.getPositions().get(1).isHit());
    }

    @Test
    @DisplayName("toString não devolve null e contém informação relevante")
    void testToString() {
        String s = ship.toString();

        assertNotNull(s);

        // Deve conter a categoria (Caravela)
        assertTrue(s.contains("Caravela"));

        // Deve conter as partes 'Linha' e 'Coluna' vindas do Position.toString()
        assertTrue(s.contains("Linha"));
        assertTrue(s.contains("Coluna"));
    }

}
