package iscteiul.ista.battleship;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CarrackTest {

    @Test
    @DisplayName("Deve ter o nome 'Nau' e tamanho 3")
    void testCarrackProperties() {
        // Arrange
        IPosition startPos = new Position(0, 0);

        // Act
        Carrack carrack = new Carrack(Compass.NORTH, startPos);

        // Assert
        assertEquals("Nau", carrack.getCategory(), "O nome do navio deve ser 'Nau'");
        assertEquals(3, carrack.getSize(), "O tamanho da Nau deve ser 3");
    }

    @Test
    @DisplayName("Deve ocupar 3 células verticais (NORTH/SOUTH)")
    void testVerticalPlacement() {
        // Arrange
        int row = 1;
        int col = 1;
        IPosition startPos = new Position(row, col);

        // Act
        // Testamos SOUTH (lógica partilhada com NORTH: incrementa linhas)
        Carrack carrack = new Carrack(Compass.SOUTH, startPos);
        List<IPosition> positions = carrack.getPositions();

        // Assert
        assertEquals(3, positions.size(), "A Nau deve ocupar 3 células");

        // Verifica as 3 posições sequenciais na vertical
        // Posição 1: (1, 1)
        assertEquals(row, positions.get(0).getRow());
        assertEquals(col, positions.get(0).getColumn());

        // Posição 2: (2, 1)
        assertEquals(row + 1, positions.get(1).getRow());
        assertEquals(col, positions.get(1).getColumn());

        // Posição 3: (3, 1)
        assertEquals(row + 2, positions.get(2).getRow());
        assertEquals(col, positions.get(2).getColumn());
    }

    @Test
    @DisplayName("Deve ocupar 3 células horizontais (EAST/WEST)")
    void testHorizontalPlacement() {
        // Arrange
        int row = 5;
        int col = 2;
        IPosition startPos = new Position(row, col);

        // Act
        // Testamos WEST (lógica partilhada com EAST: incrementa colunas)
        Carrack carrack = new Carrack(Compass.WEST, startPos);
        List<IPosition> positions = carrack.getPositions();

        // Assert
        assertEquals(3, positions.size(), "A Nau deve ocupar 3 células");

        // Verifica as 3 posições sequenciais na horizontal
        // Posição 1: (5, 2)
        assertEquals(row, positions.get(0).getRow());
        assertEquals(col, positions.get(0).getColumn());

        // Posição 2: (5, 3)
        assertEquals(row, positions.get(1).getRow());
        assertEquals(col + 1, positions.get(1).getColumn());

        // Posição 3: (5, 4)
        assertEquals(row, positions.get(2).getRow());
        assertEquals(col + 2, positions.get(2).getColumn());
    }
}