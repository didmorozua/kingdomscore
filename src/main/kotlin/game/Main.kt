package game
import game.model.GameState
import game.model.UnitType
import game.service.CombatManager
import game.service.UnitManager
fun main() {
    println("=== Kingdoms Core: Combat Test ===")
    val state = GameState()
    val unitManager = UnitManager(state)
    val combatManager = CombatManager(state, unitManager)
    println("\n--- Recruiting Troops ---")
    unitManager.recruitUnit(UnitType.SWORDSMAN, 2)
    unitManager.printArmy()
    println("\n--- Battle 1: Weak Enemy (Power 20) ---")
    combatManager.attackEnemy(enemyPower = 20, rewardGold = 100)
    println("Current Gold: ${state.gold}")
    println("\n--- Battle 2: Strong Enemy (Power 100) ---")
    combatManager.attackEnemy(enemyPower = 100, rewardGold = 500)
    unitManager.printArmy()
}
