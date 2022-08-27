import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class GameOfLifeTest {

    @Test
    fun `should kill a cell if there are no neighbours`() {
        val world = World(
            3, 3, booleanArrayOf(
                false, false, false,
                false, true, false,
                false, false, false
            )
        )
        val newWorld = GameOfLife().run(world)
        assertFalse(newWorld.isAlive(1, 1))
    }

    @Test
    fun `should kill a cell if there is one neighbour`() {
        val world = World(
            3, 3, booleanArrayOf(
                false, false, false,
                true, true, false,
                false, false, false
            )
        )
        val newWorld = GameOfLife().run(world)
        assertFalse(newWorld.isAlive(0, 1))
        assertFalse(newWorld.isAlive(1, 1))
    }

    @Test
    fun `should keep a cell alive if there are two neighbours alive`() {
        val world = World(
            3, 3, booleanArrayOf(
                false, true, false,
                true, true, false,
                false, false, false
            )
        )
        val newWorld = GameOfLife().run(world)
        assertTrue(newWorld.isAlive(1, 0))
        assertTrue(newWorld.isAlive(0, 1))
        assertTrue(newWorld.isAlive(1, 1))
    }

    @Test
    fun `should keep a cell alive if there are three neighbours alive`() {
        val world = World(
            3, 3, booleanArrayOf(
                true, true, false,
                true, true, false,
                false, false, false
            )
        )
        val newWorld = GameOfLife().run(world)
        assertTrue(newWorld.isAlive(0, 0))
        assertTrue(newWorld.isAlive(1, 0))
        assertTrue(newWorld.isAlive(0, 1))
        assertTrue(newWorld.isAlive(1, 1))
    }

    @Test
    fun `should create a cell if it is dead and there are three neighbours alive`() {
        val world = World(
            3, 3, booleanArrayOf(
                false, true, false,
                true, true, false,
                false, false, false
            )
        )
        val newWorld = GameOfLife().run(world)
        assertTrue(newWorld.isAlive(0, 0))
    }

    @Test
    fun `should kill a cell if it is alive and there are more than three neighbours alive`() {
        val world = World(
            3, 3, booleanArrayOf(
                true, true, true,
                true, true, false,
                false, false, false
            )
        )
        val newWorld = GameOfLife().run(world)
        assertFalse(newWorld.isAlive(1, 0))
        assertFalse(newWorld.isAlive(1, 1))
    }

    @Test
    fun `should handle all rules at the same time`() {
        val world = World(
            3, 3, booleanArrayOf(
                false, false, true,
                true, true, false,
                true, true, false
            )
        )
        val newWorld = GameOfLife().run(world)
        val expectedWorld = World(
            3, 3, booleanArrayOf(
                false, true, false,
                true, false, true,
                true, true, false
            )
        )
        assertEquals(newWorld, expectedWorld)
    }
}