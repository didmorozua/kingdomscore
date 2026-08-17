package game.service
import game.model.GameState
import game.model.UnitType
class UnitManager(private val gameState: GameState) {
    val army = mutableMapOf<UnitType, Int>()
    fun recruitUnit(type: UnitType, count: Int): Boolean {
        val totalGoldCost = type.goldCost * count
        val totalWoodCost = type.woodCost * count
        val totalFoodCost = type.foodCost * count
        if (gameState.gold < totalGoldCost || gameState.wood < totalWoodCost || gameState.food < totalFoodCost) {
            println("❌ Not enough resources to recruit $count x ${type.displayName}!")
            return false
        }
        gameState.gold -= totalGoldCost
        gameState.wood -= totalWoodCost
        gameState.food -= totalFoodCost
        army[type] = army.getOrDefault(type, 0) + count
        println("⚔️ Recruited $count x ${type.displayName}")
        return true
    }
    fun calculateTotalAttack(): Int {
        return army.entries.sumOf { (type, count) -> type.attack * count }
    }
    fun calculateTotalDefense(): Int {
        return army.entries.sumOf { (type, count) -> type.defense * count }
    }
    fun getTotalFoodUpkeep(): Long {
        return army.entries.sumOf { (type, count) -> type.foodUpkeep * count }
    }
    fun printArmy() {
        println("=== Kingdom Army ===")
        if (army.isEmpty()) {
            println("No units recruited yet.")
            return
        }
        army.forEach { (type, count) ->
            println("* ${type.displayName}: $count units (Atk: ${type.attack * count}, Def: ${type.defense * count})")
        }
        println("Total Army Power -> ATK: ${calculateTotalAttack()} | DEF: ${calculateTotalDefense()}")
    }
}
