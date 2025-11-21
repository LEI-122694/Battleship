package iscteiul.ista.battleship;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CompassTest {

    @Test
    @DisplayName("Deve retornar o código de direção correto para cada enum")
    void getDirection() {
        // Arrange & Assert
        assertEquals('n', Compass.NORTH.getDirection(), "NORTH deve ter direção 'n'");
        assertEquals('s', Compass.SOUTH.getDirection(), "SOUTH deve ter direção 's'");
        assertEquals('e', Compass.EAST.getDirection(), "EAST deve ter direção 'e'");
        assertEquals('o', Compass.WEST.getDirection(), "WEST deve ter direção 'o'");
        assertEquals('u', Compass.UNKNOWN.getDirection(), "UNKNOWN deve ter direção 'u'");
    }

    @Test
    @DisplayName("toString() deve devolver o código de direção como String")
    void testToString() {
        // Arrange & Assert
        assertEquals("n", Compass.NORTH.toString());
        assertEquals("s", Compass.SOUTH.toString());
        assertEquals("e", Compass.EAST.toString());
        assertEquals("o", Compass.WEST.toString());
        assertEquals("u", Compass.UNKNOWN.toString());
    }

    @Test
    @DisplayName("charToCompass() deve mapear caracteres minúsculos para o enum correspondente e caracteres desconhecidos para UNKNOWN")
    void charToCompass() {
        // known lower-case codes
        assertEquals(Compass.NORTH, Compass.charToCompass('n'));
        assertEquals(Compass.SOUTH, Compass.charToCompass('s'));
        assertEquals(Compass.EAST, Compass.charToCompass('e'));
        assertEquals(Compass.WEST, Compass.charToCompass('o'));

        // unknown or unexpected characters -> UNKNOWN
        assertEquals(Compass.UNKNOWN, Compass.charToCompass('x'));

        // uppercase letters are not recognized by the method and should return UNKNOWN
        assertEquals(Compass.UNKNOWN, Compass.charToCompass('N'));
    }

    @Test
    @DisplayName("values() e valueOf() devem funcionar conforme esperado")
    void valuesAndValueOf() {
        // values()
        Compass[] vals = Compass.values();
        assertEquals(5, vals.length, "Deve existir 5 valores no enum Compass");
        assertArrayEquals(new Compass[]{
                Compass.NORTH,
                Compass.SOUTH,
                Compass.EAST,
                Compass.WEST,
                Compass.UNKNOWN
        }, vals, "Os valores do enum devem estar na ordem esperada");

        // valueOf()
        assertEquals(Compass.WEST, Compass.valueOf("WEST"));
        assertEquals(Compass.UNKNOWN, Compass.valueOf("UNKNOWN"));
        assertThrows(IllegalArgumentException.class, () -> Compass.valueOf("INVALID"), "Nomes inválidos devem lançar IllegalArgumentException");
    }
}