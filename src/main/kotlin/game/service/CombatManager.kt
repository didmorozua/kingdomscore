package game.service
import game.model.GameState
import game.model.UnitType
class CombatManager(
    private val gameState: GameState,
    private val unitManager: UnitManager
) {
    fun calculateArmyPower(): Int {
        return unitManager.calculateTotalAttack() + unitManager.calculateTotalDefense()
    }
    fun attackEnemy(enemyPower: Int, rewardGold: Long): Boolean {
        val playerPower = calculateArmyPower()
        println("=== Battle Initiated ===")
        println("Your Army Power: $playerPower | Enemy Power: $enemyPower")
        if (playerPower >= enemyPower) {
            println("🏆 Victory! You defeated the enemy forces.")
            gameState.gold += rewardGold
            println("Gained +$rewardGold Gold as war booty.")
            return true
        } else {
            println("💀 Defeat! Your forces were overwhelmed.")
            applyCasualties()
            return false
        }
    }
    private fun applyCasualties() {
        if (unitManager.army.isEmpty()) return
        println("Casualties sustained: lose 1 unit of each type.")
        val keys = unitManager.army.keys.toList()
        for (type in keys) {
            val current = unitManager.army[type] ?: 0
            if (current > 1) {
                unitManager.army[type] = current - 1
            } else {
                unitManager.army.remove(type)
            }
        }
    }
}
