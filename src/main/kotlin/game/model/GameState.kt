package game.model
data class GameState(
    var gold: Long = 100,
    var wood: Long = 100,
    var stone: Long = 50,
    var food: Long = 100,
    var population: Long = 10
) {
    fun printStatus() {
        println("=== Status: Kingdom ===")
        println("Gold: $gold | Wood: $wood | Stone: $stone | Food: $food | Population: $population")
    }
}
