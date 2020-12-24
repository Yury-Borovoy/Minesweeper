import java.util.*

class MineField {

    fun putMines(input: Int) {
        val mines = if (input > length * height) {
            length * height - 1
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

    fun installNumbersInEmptyPlaces() {
        for (line in field.indices) {
            for (cage in field[line].indices) {
                if (field[line][cage] != 'X') {
                    countMinesAroundPlace(line, cage)
                }
            }
        }
    }

    private fun countMinesAroundPlace(line: Int, cage: Int) {
        var count = 0
        when {
            //upper left corner
            line == 0 && cage == 0 -> {
                if (field[line][cage + 1] == 'X') {
                    count++
                }
                if (field[line + 1][cage] == 'X') {
                    count++
                }
                if (field[line + 1][cage + 1] == 'X') {
                    count++
                }
                if (count > 0) {
                    field[line][cage] = count.toString().first()
                }
            }
            //upper right corner
            line == 0 && cage == field[0].size - 1 -> {
                if (field[line][cage - 1] =='X') {
                    count++
                }
                if (field[line + 1][cage] == 'X') {
                    count++
                }
                if (field[line + 1][cage - 1] == 'X') {
                    count++
                }
                if (count > 0) {
                    field[line][cage] = count.toString().first()
                }
            }
            //lower left corner
            line == field.size - 1 && cage == 0 -> {
                if (field[line - 1][cage] =='X') {
                    count++
                }
                if (field[line - 1][cage + 1] == 'X') {
                    count++
                }
                if (field[line][cage + 1] == 'X') {
                    count++
                }
                if (count > 0) {
                    field[line][cage] = count.toString().first()
                }
            }
            //lower right corner
            line == field.size - 1 && cage == field[0].size - 1 -> {
                if (field[line - 1][cage] =='X') {
                    count++
                }
                if (field[line - 1][cage - 1] == 'X') {
                    count++
                }
                if (field[line][cage - 1] == 'X') {
                    count++
                }
                if (count > 0) {
                    field[line][cage] = count.toString().first()
                }
            }
            //left side
            cage == 0 -> {
                if (field[line - 1][cage] =='X') {
                    count++
                }
                if (field[line - 1][cage + 1] == 'X') {
                    count++
                }
                if (field[line][cage + 1] == 'X') {
                    count++
                }
                if (field[line + 1][cage + 1] == 'X') {
                    count++
                }
                if (field[line + 1][cage] == 'X') {
                    count++
                }
                if (count > 0) {
                    field[line][cage] = count.toString().first()
                }
            }
            //right side
            cage == field[0].size - 1 -> {
                if (field[line - 1][cage] =='X') {
                    count++
                }
                if (field[line - 1][cage - 1] == 'X') {
                    count++
                }
                if (field[line][cage - 1] == 'X') {
                    count++
                }
                if (field[line + 1][cage - 1] == 'X') {
                    count++
                }
                if (field[line + 1][cage] == 'X') {
                    count++
                }
                if (count > 0) {
                    field[line][cage] = count.toString().first()
                }
            }
            //upper side
            line == 0 -> {
                if (field[line][cage - 1] =='X') {
                    count++
                }
                if (field[line][cage + 1] == 'X') {
                    count++
                }
                if (field[line + 1][cage - 1] == 'X') {
                    count++
                }
                if (field[line + 1][cage + 1] == 'X') {
                    count++
                }
                if (field[line + 1][cage] == 'X') {
                    count++
                }
                if (count > 0) {
                    field[line][cage] = count.toString().first()
                }
            }
            //lower side
            line == field.size - 1 -> {
                if (field[line][cage - 1] =='X') {
                    count++
                }
                if (field[line][cage + 1] == 'X') {
                    count++
                }
                if (field[line - 1][cage - 1] == 'X') {
                    count++
                }
                if (field[line - 1][cage + 1] == 'X') {
                    count++
                }
                if (field[line - 1][cage] == 'X') {
                    count++
                }
                if (count > 0) {
                    field[line][cage] = count.toString().first()
                }
            }
            //center
            else -> {
                if (field[line][cage - 1] =='X') {
                    count++
                }
                if (field[line][cage + 1] == 'X') {
                    count++
                }
                if (field[line - 1][cage - 1] == 'X') {
                    count++
                }
                if (field[line - 1][cage + 1] == 'X') {
                    count++
                }
                if (field[line - 1][cage] == 'X') {
                    count++
                }
                if (field[line + 1][cage - 1] == 'X') {
                    count++
                }
                if (field[line + 1][cage + 1] == 'X') {
                    count++
                }
                if (field[line + 1][cage] == 'X') {
                    count++
                }
                if (count > 0) {
                    field[line][cage] = count.toString().first()
                }
            }
        }
    }

    fun showField() {
        var countRows = 0
        println(" |123456789|")
        println("-|---------|")
        for (line in field.indices) {
            countRows++
            print("$countRows|")
            for (cage in field[line].indices) {
                if (field[line][cage] == 'X') {
                    print('.')
                } else if (field[line][cage] == '-') {
                    print('*')
                }
                else {
                    print(field[line][cage])
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
    }
}