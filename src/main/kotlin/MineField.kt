import java.util.*

class MineField {

    fun putMines(input: Int) {
        val mines = if (input > length * height) {
            length * height
        } else {
            input
        }

        var installed = false
        for (mine in 1..mines) {
            while (!installed) {
                val xMine = Random().nextInt(length)
                val yMine = Random().nextInt(height)
                if (field[xMine][yMine] != 'X') {
                    field[xMine][yMine] = 'X'
                    installed = true
                }
            }
            installed = false
        }
    }


    fun checkAroundPlace(y: Int, x: Int): Int {
        if (outBounds(y, x)) return 0
        var mines = 0
        for (line in -1..1) {
            for (cage in -1..1) {
                if (!outBounds(line + y, cage + x)) {
                    if (field[line + y][cage + x] == 'X' || field[line + y][cage + x] == '*') {
                        mines++
                    }
                }
            }
        }
        return mines
    }

    fun outBounds(y: Int, x: Int): Boolean {
        return y < 0 || x < 0 || y >= height || x >= length
    }

    fun showField() {
        var countRows = 0
        println(" |123456789|")
        println("-|---------|")
        for (line in field.indices) {
            countRows++
            print("$countRows|")
            for (cage in field[line].indices) {
                when {
                    field[line][cage] == 'X' -> {
                        print('.')
                    }
                    field[line][cage] == '-' -> {
                        print('*')
                    }
                    else -> {
                        print(field[line][cage])
                    }
                }
            }
            print("|")
            println()
        }
        println("-|---------|")
    }

    fun showFieldWithMines() {
        var countRows = 0
        println(" |123456789|")
        println("-|---------|")
        for (line in field.indices) {
            countRows++
            print("$countRows|")
            for (cage in field[line].indices) {
                when {
                    field[line][cage] == 'X' -> {
                        print('X')
                    }
                    field[line][cage] == '-' -> {
                        print('.')
                    }
                    field[line][cage] == '*' -> {
                        print('X')
                    }
                    else -> {
                        print(field[line][cage])
                    }
                }
            }
            print("|")
            println()
        }
        println("-|---------|")
    }

    companion object {
        private const val length = 9
        private const val height = 9
        val field = Array(height) {
            Array(length){'.'}
        }
        val revealedPlaces = Array(height) {
            Array(length){false}
        }
    }
}