package game.service
import game.model.GameState
import game.model.UnitType
class UnitManager(private val gameState: GameState) {
    val army = mutableMapOf<UnitType, Int>()
    fun recruit(type: UnitType, amount: Int): Boolean {
        val totalGold = type.goldCost * amount
        val totalWood = type.woodCost * amount
        if (gameState.gold >= totalGold && gameState.wood >= totalWood) {
            gameState.gold -= totalGold
            gameState.wood -= totalWood
            army[type] = army.getOrDefault(type, 0) + amount
            println("Recruited: $amount x ${type.displayName}")
            return true
        } else {
            println("Not enough resources to recruit $amount x ${type.displayName}")
            return false
        }
    }
    fun printArmy() {
        println("=== Kingdom Army ===")
        if (army.isEmpty()) {
            println("No units recruited yet.")
        } else {
            var totalAtk = 0
            var totalDef = 0
            army.forEach { (type, count) ->
                val atk = type.attack * count
                val def = type.defense * count
                totalAtk += atk
                totalDef += def
                println("- ${type.displayName}: $count units (Atk: $atk, Def: $def)")
            }
            println("Total Army Power -> ATK: $totalAtk | DEF: $totalDef")
        }
    }
}
