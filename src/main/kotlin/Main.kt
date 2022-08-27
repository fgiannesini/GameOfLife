import java.awt.EventQueue

import javax.swing.JFrame
import javax.swing.JTable
import javax.swing.Timer

class SimpleEx(title: String) : JFrame() {

    init {
        createUI(title)
    }

    private fun createUI(title: String) {

        setTitle(title)

        defaultCloseOperation = EXIT_ON_CLOSE
        setSize(400, 300)
        setLocationRelativeTo(null)


        var world = World(
            5, 5, booleanArrayOf(
                false, false, false, false, false,
                false, false, false, false, false,
                false, true, true, true, false,
                false, false, false, false, false,
                false, false, false, false, false,
            )
        )
        val gameOfLife = GameOfLife()

        // Initializing the JTable
        val table = JTable(Array(world.height) { Array(world.width) { "" } }, Array(world.width) { "" })

        updateTable(table, world)

        table.setBounds(30, 40, 200, 300)
        add(table)

        val timer = Timer(1000) {
            world = gameOfLife.run(world)
            updateTable(table, world)
        }
        timer.start()
    }

    private fun updateTable(table: JTable, world: World) {
        for (j in (0 until world.height)) {
            for (i in (0 until world.width)) {
                val valueAt = if (world.valueAt(i, j)) "+" else ""
                table.setValueAt(valueAt, i, j)
            }
        }
    }
}


private fun createAndShowGUI() {

    val frame = SimpleEx("Game Of Life")
    frame.isVisible = true
}

fun main() {
    EventQueue.invokeLater(::createAndShowGUI)
}