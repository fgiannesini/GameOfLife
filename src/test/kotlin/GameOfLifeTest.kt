import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import kotlin.math.exp

class GameOfLifeTest {

    @Test
    fun `should kill a cell if there are no neighbours`() {
        val cells = BooleanArray(9)
        cells[4] = true
        val newCells = GameOfLife().run(cells)
        assertFalse(newCells[4])
    }

    @Test
    fun `should kill a cell if there is one neighbour`() {
        val cells = BooleanArray(9)
        cells[3] = true
        cells[4] = true
        val newCells = GameOfLife().run(cells)
        assertFalse(newCells[3])
        assertFalse(newCells[4])
    }

    @Test
    fun `should keep a cell alive if there are two neighbours alive`() {
        // 0 1 0
        // 1 1 0
        // 0 0 0
        val cells = BooleanArray(9)
        cells[4] = true
        cells[3] = true
        cells[1] = true
        val newCells = GameOfLife().run(cells)
        assertTrue(newCells[1])
        assertTrue(newCells[3])
        assertTrue(newCells[4])
    }

    @Test
    fun `should keep a cell alive if there are three neighbours alive`() {
        // 1 1 0
        // 1 1 0
        // 0 0 0
        val cells = BooleanArray(9)
        cells[4] = true
        cells[3] = true
        cells[1] = true
        cells[0] = true
        val newCells = GameOfLife().run(cells)
        assertTrue(newCells[0])
        assertTrue(newCells[1])
        assertTrue(newCells[3])
        assertTrue(newCells[4])
    }

    @Test
    fun `should create a cell if it is dead and there are three neighbours alive`() {
        // 0 1 0
        // 1 1 0
        // 0 0 0
        val cells = BooleanArray(9)
        cells[4] = true
        cells[3] = true
        cells[1] = true
        val newCells = GameOfLife().run(cells)
        assertTrue(newCells[0])
    }
}