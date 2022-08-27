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
        val actual = world.valueAt(2,1)
        assertTrue(actual)
    }

}