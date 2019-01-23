package board

import board.Direction.*

fun createSquareBoard(width: Int): SquareBoard = object: SquareBoard {
    override val width: Int
        get() = width

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
        val i = this.i
        val j = this.j

        when(direction) {
            UP -> {
                if(j== 1) return null
                return Cell(i, j-1)
            }
            DOWN -> {
                if(j<width) return Cell(i, j+1)
                return null
            }
            LEFT -> {
                if(i==1) return null
                return Cell(i-1 , j)
            }
            RIGHT -> {
                if(i < width) return Cell(i+1, j)
                return null
            }
        }
    }
}




fun <T> createGameBoard(width: Int): GameBoard<T> = object: GameBoard<T> {
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

