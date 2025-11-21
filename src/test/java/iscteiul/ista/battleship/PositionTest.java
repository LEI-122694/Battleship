package iscteiul.ista.battleship;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PositionTest {

    @Test
    @DisplayName("getRow() deve retornar a linha correta")
    void getRow() {
        Position p = new Position(5, 2);
        assertEquals(5, p.getRow());
    }

    @Test
    @DisplayName("getColumn() deve retornar a coluna correta")
    void getColumn() {
        Position p = new Position(5, 2);
        assertEquals(2, p.getColumn());
    }

    @Test
    @DisplayName("hashCode() deve considerar estado das posições")
    void testHashCode() {
        Position a = new Position(1, 1);
        Position b = new Position(1, 1);
        assertEquals(a.hashCode(), b.hashCode(), "Posições iguais devem ter hashCode igual");

        // Changing occupied/hit state changes hashCode because fields are included
        int before = a.hashCode();
        a.occupy();
        a.shoot();
        int after = a.hashCode();
        assertNotEquals(before, after, "Alterar estado deve mudar hashCode se campos forem considerados");
    }

    @Test
    @DisplayName("equals() deve funcionar corretamente")
    void testEquals() {
        Position a = new Position(2, 3);
        Position b = new Position(2, 3);
        Position c = new Position(3, 2);

        assertEquals(a, b, "Posições com mesmas coordenadas devem ser iguais");
        assertNotEquals(a, c, "Posições com coordenadas diferentes não devem ser iguais");
        assertNotEquals(a, null, "Comparar com null deve retornar diferente");
        assertNotEquals(new Object(), a, "Comparar com outro tipo deve resultar em desigualdade");
    }

    @Test
    @DisplayName("isAdjacentTo() deve identificar adjacências")
    void isAdjacentTo() {
        Position center = new Position(4, 4);
        Position left = new Position(4, 3);
        Position diag = new Position(3, 3);
        Position far = new Position(6, 4);

        assertTrue(center.isAdjacentTo(left));
        assertTrue(center.isAdjacentTo(diag));
        assertFalse(center.isAdjacentTo(far));
    }

    @Test
    @DisplayName("occupy() deve marcar posição como ocupada")
    void occupy() {
        Position p = new Position(0, 0);
        assertFalse(p.isOccupied());
        p.occupy();
        assertTrue(p.isOccupied());
    }

    @Test
    @DisplayName("shoot() deve marcar posição como atingida")
    void shoot() {
        Position p = new Position(0, 0);
        assertFalse(p.isHit());
        p.shoot();
        assertTrue(p.isHit());
    }

    @Test
    @DisplayName("isOccupied() deve refletir estado após occupy()")
    void isOccupied() {
        Position p = new Position(1, 1);
        assertFalse(p.isOccupied());
        p.occupy();
        assertTrue(p.isOccupied());
    }

    @Test
    @DisplayName("isHit() deve refletir estado após shoot()")
    void isHit() {
        Position p = new Position(1, 1);
        assertFalse(p.isHit());
        p.shoot();
        assertTrue(p.isHit());
    }

    @Test
    @DisplayName("toString() deve conter informações de linha e coluna")
    void testToString() {
        Position p = new Position(7, 8);
        String s = p.toString();
        assertTrue(s.contains("Linha") && s.contains("Coluna"));
        assertTrue(s.contains("7") && s.contains("8"));
    }
}