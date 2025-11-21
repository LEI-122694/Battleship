package iscteiul.ista.battleship;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IPositionTest {

    @Test
    @DisplayName("getRow() deve retornar a linha correta")
    void getRow() {
        IPosition p = new Position(2, 3);
        assertEquals(2, p.getRow(), "getRow deve retornar a linha fornecida no construtor");
    }

    @Test
    @DisplayName("getColumn() deve retornar a coluna correta")
    void getColumn() {
        IPosition p = new Position(4, 1);
        assertEquals(1, p.getColumn(), "getColumn deve retornar a coluna fornecida no construtor");
    }

    @Test
    @DisplayName("equals() deve comparar posições corretamente")
    void testEquals() {
        IPosition a = new Position(0, 0);
        IPosition b = new Position(0, 0);
        IPosition c = new Position(1, 0);

        assertEquals(a, b, "Posições com mesmas coordenadas devem ser iguais");
        assertNotEquals(a, c, "Posições com coordenadas diferentes não devem ser iguais");
        assertNotEquals(new Object(), a, "Comparar com outro tipo deve resultar em desigualdade");
    }

    @Test
    @DisplayName("isAdjacentTo() deve identificar posições adjacentes corretamente")
    void isAdjacentTo() {
        IPosition center = new Position(3, 3);
        IPosition same = new Position(3, 3);
        IPosition up = new Position(2, 3);
        IPosition diag = new Position(2, 2);
        IPosition far = new Position(5, 5);

        assertTrue(center.isAdjacentTo(same), "A mesma posição é considerada adjacente");
        assertTrue(center.isAdjacentTo(up), "Posição acima deve ser adjacente");
        assertTrue(center.isAdjacentTo(diag), "Posição diagonal deve ser adjacente");
        assertFalse(center.isAdjacentTo(far), "Posição distante não deve ser adjacente");
    }

    @Test
    @DisplayName("occupy() deve marcar a posição como ocupada")
    void occupy() {
        IPosition p = new Position(0, 1);
        assertFalse(p.isOccupied());
        p.occupy();
        assertTrue(p.isOccupied(), "Ao ocupar, isOccupied deve retornar true");
    }

    @Test
    @DisplayName("shoot() deve marcar a posição como atingida")
    void shoot() {
        IPosition p = new Position(0, 2);
        assertFalse(p.isHit());
        p.shoot();
        assertTrue(p.isHit(), "Ao disparar, isHit deve retornar true");
    }

    @Test
    @DisplayName("isOccupied() deve refletir estado após occupy()")
    void isOccupied() {
        IPosition p = new Position(7, 7);
        assertFalse(p.isOccupied());
        p.occupy();
        assertTrue(p.isOccupied());
    }

    @Test
    @DisplayName("isHit() deve refletir estado após shoot()")
    void isHit() {
        IPosition p = new Position(6, 6);
        assertFalse(p.isHit());
        p.shoot();
        assertTrue(p.isHit());
    }
}