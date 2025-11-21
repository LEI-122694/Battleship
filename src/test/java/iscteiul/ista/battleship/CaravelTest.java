package iscteiul.ista.battleship;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CaravelTest {

    @Test
    @DisplayName("Deve ter o nome 'Caravela' e tamanho 2")
    void testCaravelProperties() {
        // Arrange
        IPosition startPos = new Position(0, 0);

        // Act
        Caravel caravel = new Caravel(Compass.NORTH, startPos);

        // Assert
        assertEquals("Caravela", caravel.getCategory(), "O nome deve ser 'Caravela'");
        assertEquals(2, caravel.getSize(), "O tamanho deve ser 2");
    }

    @Test
    @DisplayName("Deve ocupar 2 células verticais quando orientado a NORTH ou SOUTH")
    void testVerticalPlacement() {
        // Arrange
        int startRow = 2;
        int startCol = 5;
        IPosition startPos = new Position(startRow, startCol);

        // Act
        // Testamos NORTH (a lógica é igual para SOUTH no switch case fornecido)
        Caravel caravel = new Caravel(Compass.NORTH, startPos);
        List<IPosition> positions = caravel.getPositions();

        // Assert
        assertEquals(2, positions.size(), "Deve ocupar 2 posições");

        // Primeira posição (A própria 'pos' passada no construtor)
        assertEquals(startRow, positions.get(0).getRow());
        assertEquals(startCol, positions.get(0).getColumn());

        // Segunda posição (Row + 1, mesma Coluna)
        assertEquals(startRow + 1, positions.get(1).getRow(), "A segunda célula deve estar na linha seguinte (vertical)");
        assertEquals(startCol, positions.get(1).getColumn(), "A coluna deve manter-se igual na vertical");
    }

    @Test
    @DisplayName("Deve ocupar 2 células horizontais quando orientado a EAST ou WEST")
    void testHorizontalPlacement() {
        // Arrange
        int startRow = 3;
        int startCol = 3;
        IPosition startPos = new Position(startRow, startCol);

        // Act
        // Testamos EAST (a lógica é igual para WEST no switch case fornecido)
        Caravel caravel = new Caravel(Compass.EAST, startPos);
        List<IPosition> positions = caravel.getPositions();

        // Assert
        assertEquals(2, positions.size(), "Deve ocupar 2 posições");

        // Primeira posição
        assertEquals(startRow, positions.get(0).getRow());
        assertEquals(startCol, positions.get(0).getColumn());

        // Segunda posição (Mesma linha, Coluna + 1)
        assertEquals(startRow, positions.get(1).getRow(), "A linha deve manter-se igual na horizontal");
        assertEquals(startCol + 1, positions.get(1).getColumn(), "A segunda célula deve estar na coluna seguinte (horizontal)");
    }
}