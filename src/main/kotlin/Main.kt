import java.awt.Color
import java.awt.Dimension
import java.awt.EventQueue
import java.awt.Font
import java.awt.event.ComponentAdapter
import java.awt.event.ComponentEvent
import javax.swing.JFrame
import javax.swing.JLabel
import javax.swing.JTable
import javax.swing.Timer
import javax.swing.table.DefaultTableCellRenderer


class SimpleEx(title: String) : JFrame() {

    init {
        createUI(title)
    }

    private fun createUI(title: String) {

        val pattern = World(3, 1, booleanArrayOf(true, true, true))
        var world = World(5, 5)
            .addPatternAt(1, 2, pattern)

        val gameOfLife = GameOfLife()

        setSize(400, 400)
        setTitle(title)
        defaultCloseOperation = EXIT_ON_CLOSE
        setLocationRelativeTo(null)

        // Initializing the JTable
        val table = JTable(Array(world.height) { Array(world.width) { "" } }, Array(world.width) { "" })
        table.gridColor = Color(240, 240, 240)
        table.font = Font("Serif", Font.BOLD, 20)
        updateTable(table, world)
        table.addComponentListener(object : ComponentAdapter() {
            override fun componentResized(e: ComponentEvent?) {
                //Get new JTable component size
                val size: Dimension = size

                //Check if height or width is the limiting factor and set cell size accordingly
                val cellSize: Int = if (size.height / world.height > size.width / world.width) {
                    size.width / world.width
                } else {
                    size.height / world.height
                }

                //Set new row height to our new size
                table.rowHeight = cellSize

                val centerRenderer = DefaultTableCellRenderer()
                centerRenderer.horizontalAlignment = JLabel.CENTER
                //Set new column width to our new size
                for (i in 0 until table.columnCount) {
                    val column = table.columnModel.getColumn(i)
                    column.maxWidth = cellSize
                    column.cellRenderer = centerRenderer
                }
            }
        })
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