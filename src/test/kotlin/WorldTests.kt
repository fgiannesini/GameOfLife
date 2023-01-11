import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class WorldTests {
    @Test
    fun `countNeighbors with full true returns correct count`() {
        val world = World(
            3, 3, booleanArrayOf(
                true, true, true,
                true, true, true,
                true, true, true
            )
        )
        assertEquals(8, world.countNeighbors(1, 1))
    }

    @Test
    fun `countNeighbors returns correct count`() {
        val cells = booleanArrayOf(
            false, true, true,
            true, false, true,
            true, true, false
        )
        val world = World(3, 3, cells)
        assertEquals(6, world.countNeighbors(1, 1))
    }

    @Test
    fun `isAlive returns correct value`() {
        val world = World(
            3, 3, booleanArrayOf(
                true, true, true,
                true, true, true,
                true, true, true
            )
        )
        assertTrue(world.isAlive(1, 1))

        val world2 = World(
            3, 3, booleanArrayOf(
                false, true, false,
                false, true, false,
                false, true, false
            )
        )
        assertFalse(world2.isAlive(0, 0))
    }

    @Test
    fun `build returns new world with correct values`() {
        val world = World(
            3, 3, booleanArrayOf(
                true, true, true,
                true, true, true,
                true, true, true
            )
        )
        val newWorld = world.build { _, _ -> false }
        val expectedCells = booleanArrayOf(
            false, false, false,
            false, false, false,
            false, false, false
        )
        assertArrayEquals(expectedCells, newWorld.cells)
    }

    @Test
    fun `addPatternAt returns new world with correct values`() {
        val world = World(
            3, 3, booleanArrayOf(
                false, false, false,
                false, false, false,
                false, false, false
            )
        )
        val pattern = World(
            2, 2, booleanArrayOf(
                true, true,
                true, true
            )
        )
        val newWorld = world.addPatternAt(1, 1, pattern)
        val expectedCells = booleanArrayOf(
            false, false, false,
            false, true, true,
            false, true, true
        )
        assertArrayEquals(expectedCells, newWorld.cells)
    }

    @Test
    fun `equals returns true for same worlds`() {
        val cells = booleanArrayOf(
            false, false, false,
            false, false, false,
            false, false, false
        )
        val world1 = World(3, 3, cells)
        val world2 = World(3, 3, cells)
        assertEquals(world1, world2)
    }

    @Test
    fun `equals returns false for different worlds`() {
        val cells1 = booleanArrayOf(
            false, false, false,
            false, false, false,
            false, false, false
        )
        val world1 = World(3, 3, cells1)
        val cells2 = booleanArrayOf(
            false, false, false,
            false, true, false,
            false, false, false
        )
        val world2 = World(3, 3, cells2)
        assertNotEquals(world1, world2)
    }

    @Test
    fun `valueAt returns correct value`() {
        val cells = booleanArrayOf(
            false, true, false,
            true, true, true,
            false, true, false
        )
        val world = World(3, 3, cells)
        assertFalse(world.valueAt(0, 0))
        assertTrue(world.valueAt(1, 0))
        assertFalse(world.valueAt(2, 0))
        assertTrue(world.valueAt(0, 1))
        assertTrue(world.valueAt(1, 1))
        assertTrue(world.valueAt(2, 1))
        assertFalse(world.valueAt(0, 2))
        assertTrue(world.valueAt(1, 2))
        assertFalse(world.valueAt(2, 2))
    }
}