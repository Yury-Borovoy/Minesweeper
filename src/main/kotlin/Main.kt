import java.util.*

fun main() {
    val scanner = Scanner(System.`in`)
    println("How many mines do you want on the field?")
    val mines = scanner.nextInt()
    val gamePlace = MineField()
    gamePlace.putMines(mines)
    gamePlace.installNumbersInEmptyPlaces()
    gamePlace.showField()
    val battle = Battle(gamePlace, mines)
    battle.play(scanner)
}
