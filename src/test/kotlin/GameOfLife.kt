class GameOfLife {

    fun run(cells: BooleanArray): BooleanArray {
        return if (cells.count { it } > 2) {
            if(!cells [0]) {
                cells[0] = true
            }
            cells
        } else BooleanArray(9)
    }

}
