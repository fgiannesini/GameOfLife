import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class WorldTest {

    @Test
    fun `should return a cell value`() {
        val world = World(
            4, 3, booleanArrayOf(
                false, false, false, false,
                false, false, true, false,
                false, false, false, false
            )
        )
        val actual = world.valueAt(2, 1)
        assertTrue(actual)
    }

    @Test
    fun `should add a pattern to the world`() {
        val world = World(
            4, 5, booleanArrayOf(
                true, false, false, false,
                false, false, false, false,
                false, false, false, false,
                false, false, false, false,
                false, false, false, false,
            )
        )
        val actualWorld: World = world.addPatternAt(1, 2, World(3, 1, booleanArrayOf(true, true, true)))

        val expected = World(
            4, 5, booleanArrayOf(
                true, false, false, false,
                false, false, false, false,
                false, true, true, true,
                false, false, false, false,
                false, false, false, false,
            )
        )
        assertEquals(expected, actualWorld)
    }
}