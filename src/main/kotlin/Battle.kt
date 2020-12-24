import java.util.*

class Battle(private val mineField: MineField, val mines: Int) {

    var x = 0
    var y = 0
    private val regex = Regex("[1-9]")
    var foundMines = 0
    var marksOnField = 0

    fun play(scanner: Scanner) {
        var someX: String
        var someY: String
        var isNumbersCorrect: Boolean
        var stop = false

        while (!stop) {
            println("Set/delete mine marks (x and y coordinates): ")
            someX = scanner.next()
            someY = scanner.next()
            isNumbersCorrect = checkValuesForCorrectness(someX, someY)
            if (isNumbersCorrect) {
                if (checkField()) {
                    mineField.showField()
                }
            }

            if (marksOnField ==foundMines && marksOnField != 0 && foundMines != 0) {
                stop = true
                println("Congratulations! You found all the mines!")
            }
        }


    }

    //check if the input is into [1-9]
    private fun checkValuesForCorrectness(someX: String, someY: String): Boolean {
        return if (!someX.matches(regex) || !someY.matches(regex)) {
            println("This are wrong coordinates!")
            false
        } else {
            x = someX.toInt()
            y = someY.toInt()
            true
        }
    }

    private fun checkField(): Boolean {
        //check if the entered coordinates match the numbered place in the field
        if (MineField.field[y - 1][x - 1].toString().matches(regex)) {
            println("There is a number here!")
            return false
        } else {
            if (MineField.field[y - 1][x - 1] == '.') {
                marksOnField++
                MineField.field[y - 1][x - 1] = '-'
            } else if (MineField.field[y - 1][x - 1] == 'X') {
                marksOnField++
                foundMines++
                MineField.field[y - 1][x - 1] = '*'
            } else if (MineField.field[y - 1][x - 1] == '-') {
                marksOnField--
                MineField.field[y - 1][x - 1] = '.'
            } else if (MineField.field[y - 1][x - 1] == '*') {
                marksOnField--
                foundMines--
                MineField.field[y - 1][x - 1] = 'X'
            }
            return true
        }
    }
}