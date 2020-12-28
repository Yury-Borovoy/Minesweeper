import java.util.*

class Battle(private val mineField: MineField, private val mines: Int) {

    private var x = 0
    private var y = 0
    private var action = ""
    private val regex = Regex("[1-9]")
    private var markedPlaces = 0
    private var markedMines = 0

    fun play(scanner: Scanner) {
        var stop = false

        while (!stop) {
            println("Set/unset mines marks or claim a cell as free: ")
            if (installStep(scanner)) {
                when (action) {
                    "free" -> when {
                        MineField.field[y - 1][x - 1] == 'X' -> {
                            stop = true
                            mineField.showFieldWithMines()
                            println("You stepped on a mine and failed!")
                        }
                        MineField.field[y - 1][x - 1] == '.' -> {
                            explorePlace(x - 1, y - 1)
                            mineField.showField()
                            stop = isWin()
                        }
                        MineField.field[y - 1][x - 1] == '-' -> {
                            MineField.field[y - 1][x - 1] = '.'
                            explorePlace(x - 1, y - 1)
                            mineField.showField()
                            stop = isWin()
                        }
                    }
                    "mine" -> {
                        markMine(x - 1, y - 1)
                        if (MineField.field[y - 1][x - 1] == '*') {
                            MineField.revealedPlaces[y - 1][x - 1] = true
                        }
                        mineField.showField()
                        stop = isWin()
                    }
                }
            }
        }
    }

    // Вводим координаты и действие и передаем их в функцию checkNumbersAndAction для
    // проверки корректности
    private fun installStep(scanner: Scanner): Boolean {
        val someX = scanner.next()
        val someY = scanner.next()
        val someAction = scanner.next()
        return checkNumbersAndAction(someX, someY, someAction)
    }

    // Проверяем, корректно ли введены координаты и действие
    private fun checkNumbersAndAction(someX: String, someY: String, someAction: String): Boolean {
        return if (!someX.matches(regex) || !someY.matches(regex)) {
            println("This are wrong coordinates!")
            false
        } else {
            if (someAction == "mine" || someAction == "free") {
                x = someX.toInt()
                y = someY.toInt()
                action = someAction
                true
            } else {
                false
            }
        }
    }

    // Рекурсивная функция: если вокруг указанного места нет мин, то функция помечает это место как '/' и
    // переходит на поиски безминных мест среди тех которые граничат с первоначальной и т.д.
    // Если вокруг обрабатываемых мест есть мины, тогда указывается число мин вокруг этих мест
    private fun explorePlace(x: Int, y: Int) {
        if (mineField.outBounds(y, x)) return                 //если искомое место за пределами поля, выходим из функции
        if (MineField.revealedPlaces[y][x]) return
        MineField.revealedPlaces[y][x] = true
        val mines = mineField.checkAroundPlace(y, x)
        if (mines != 0) {
            MineField.field[y][x] = mines.toString().first()
            return
        } else {
            MineField.field[y][x] = '/'
        }

        explorePlace(x - 1, y - 1)
        explorePlace(x - 1, y + 1)
        explorePlace(x + 1, y - 1)
        explorePlace(x + 1, y + 1)
        explorePlace(x - 1, y)
        explorePlace(x + 1, y)
        explorePlace(x, y - 1)
        explorePlace(x, y + 1)
    }

    private fun markMine(x: Int, y: Int) {
        when {
            MineField.field[y][x] == '.' -> {
                markedPlaces++
                MineField.field[y][x] = '-'
            }
            MineField.field[y][x] == '-' -> {
                markedPlaces--
                MineField.field[y][x] = '.'
            }
            MineField.field[y][x] == '*' -> {
                markedPlaces--
                markedMines--
                MineField.field[y][x] = 'X'
            }
            MineField.field[y][x] == 'X' -> {
                markedPlaces++
                markedMines++
                MineField.field[y][x] = '*'
            }
        }
    }

    // Проверка игры на завершенность: если все помеченные места верны и  равны минам в поле,
    // тогда игрок победил. Но игрок также побеждает, если все почеменные места верны и их сумма
    // с оставшимися неоткрытыми местами равна минам в поле.
    private fun isWin(): Boolean {
        //first check
        if (markedMines == mines) {
            println("Congratulations! You found all the mines!")
            return true
        }
        //second check
        for (line in MineField.field.indices) {
            for (cage in MineField.field[line].indices) {
                if (MineField.field[line][cage] == '.' || MineField.field[line][cage] == '-') {
                    return false
                }
            }
        }
        println("Congratulations! You found all the mines!")
        return true
    }
}