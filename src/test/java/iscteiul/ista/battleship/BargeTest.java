package iscteiul.ista.battleship;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BargeTest {

    @Test
    @DisplayName("Deve instanciar a Barge com o nome e tamanho corretos")
    void testBargeProperties() {
        // Arrange
        IPosition startPos = new Position(0, 0);
        Compass bearing = Compass.NORTH;

        // Act
        Barge barge = new Barge(bearing, startPos);

        // Assert
        // Verifica se o nome foi passado corretamente para o super()
        assertEquals("Barca", barge.getCategory(), "O nome do navio deve ser 'Barca'");

        // Verifica se o tamanho é sempre 1
        assertEquals(1, barge.getSize(), "O tamanho da Barca deve ser 1");

        // Verifica se a orientação foi guardada corretamente
        assertEquals(Compass.NORTH, barge.getBearing(), "A orientação deve corresponder à fornecida no construtor");
    }

    @Test
    @DisplayName("Deve ocupar exatamente uma posição nas coordenadas fornecidas")
    void testBargePosition() {
        // Arrange
        int row = 4;
        int col = 7;
        IPosition startPos = new Position(row, col);
        Compass bearing = Compass.EAST;

        // Act
        Barge barge = new Barge(bearing, startPos);
        List<IPosition> positions = barge.getPositions();

        // Assert
        assertNotNull(positions, "A lista de posições não deve ser nula");

        // Como é uma Barca (tamanho 1), a lista deve ter apenas 1 elemento
        assertEquals(1, positions.size(), "A Barca deve ocupar exatamente 1 célula");

        // Verifica se a posição ocupada é exatamente a que foi passada no construtor
        IPosition occupiedPos = positions.get(0);
        assertEquals(row, occupiedPos.getRow(), "A linha deve ser a mesma da posição inicial");
        assertEquals(col, occupiedPos.getColumn(), "A coluna deve ser a mesma da posição inicial");
    }
}