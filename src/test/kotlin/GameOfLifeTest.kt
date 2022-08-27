import org.junit.jupiter.api.Assertions.assertArrayEquals
import org.junit.jupiter.api.Test

class GameOfLifeTest {

    @Test
    fun `should kill a cell if there are no neighbours`() {
        val cells = BooleanArray(9)
        cells[4] = true
        val newCells = GameOfLife().run(cells)
        assertArrayEquals(newCells, BooleanArray(9))
    }

    @Test
    fun `should kill a cell if there is one neighbour`() {
        val cells = BooleanArray(9)
        cells[4] = true
        cells[3] = true
        val newCells = GameOfLife().run(cells)
        val expectedCells = BooleanArray(9)
        assertArrayEquals(newCells, expectedCells)
    }

}