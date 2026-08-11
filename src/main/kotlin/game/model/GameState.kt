package game.model
data class GameState(
    val kingdomName: String = "New Kingdom",
    var gold: Long = 1000,
    var wood: Long = 500,
    var stone: Long = 500
) {
    fun printStatus() {
        println("=== Status: $kingdomName ===")
        println("Gold: $gold | Wood: $wood | Stone: $stone")
    }
}
