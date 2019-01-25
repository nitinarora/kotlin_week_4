package board

import board.Direction.*

fun createSquareBoard(width: Int): SquareBoard = object : SquareBoard {

    private var cellStore: MutableList<Cell> = mutableListOf()

    init {
        for (i in 1..width) {
            for (j in 1..width) {
                cellStore.add(Cell(i, j))
            }
        }
    }

    override val width: Int
        get() = width

    override fun getCellOrNull(i: Int, j: Int): Cell? =
            cellStore.firstOrNull { cell -> cell.i == i && cell.j == j }

    override fun getCell(i: Int, j: Int): Cell {
        val cellOrNull = getCellOrNull(i, j)
        return when (cellOrNull) {
            null -> throw IllegalArgumentException()
            else -> cellOrNull
        }
    }

    override fun getAllCells(): Collection<Cell> {
        return cellStore.toList().sortedWith(compareBy(Cell::i, Cell::j))
    }

    override fun getRow(i: Int, jRange: IntProgression): List<Cell> {
        val tillRange: Int


        when {
            jRange.step < 0 -> tillRange = if (jRange.first > width) width else jRange.first
            else -> tillRange = if (jRange.last > width) width else jRange.last
        }

        val filter: List<Cell> = cellStore.toList().filter { it.i == i && it.j in 1..tillRange }

        return when {
            jRange.step < 0 -> filter.sortedByDescending({ cell -> cell.j })
            else -> filter
        }
    }

    override fun getColumn(iRange: IntProgression, j: Int): List<Cell> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun Cell.getNeighbour(direction: Direction): Cell? {
        val i = this.i
        val j = this.j

        when (direction) {
            UP -> {
                if (j == 1) return null
                return getCell(i, j - 1)
            }
            DOWN -> {
                if (j < width) return Cell(i, j + 1)
                return null
            }
            LEFT -> {
                if (i == 1) return null
                return Cell(i - 1, j)
            }
            RIGHT -> {
                if (i < width) return Cell(i + 1, j)
                return null
            }
        }
    }

}

fun main() {
    val sb = createSquareBoard(2)
//    println(sb.getCell(1,2))
//    println(sb.getCellOrNull(2,2))
//    println(sb.getCellOrNull(3,2))
//    println(sb.getCell(3,2))
//    println(sb.getAllCells())
//    println(sb.getRow(1, 1..1))
//    println(sb.getRow(1, 1..2))
//    println(sb.getRow(1, 1..3))
    println(sb.getRow(1, 2 downTo 1))
    println(sb.getRow(1, 3 downTo 1))
    println(sb.getRow(2, 1..1))
    println(sb.getRow(2, 1..2))
    println(sb.getRow(2, 2 downTo 1))
    println(sb.getRow(2, 3 downTo 1))
}

fun <T> createGameBoard(width: Int): GameBoard<T> = object : GameBoard<T> {
    override fun get(cell: Cell): T? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun set(cell: Cell, value: T?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun filter(predicate: (T?) -> Boolean): Collection<Cell> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun find(predicate: (T?) -> Boolean): Cell? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun any(predicate: (T?) -> Boolean): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun all(predicate: (T?) -> Boolean): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override val width: Int
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.

    override fun getCellOrNull(i: Int, j: Int): Cell? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getCell(i: Int, j: Int): Cell {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getAllCells(): Collection<Cell> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getRow(i: Int, jRange: IntProgression): List<Cell> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getColumn(iRange: IntProgression, j: Int): List<Cell> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun Cell.getNeighbour(direction: Direction): Cell? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}

