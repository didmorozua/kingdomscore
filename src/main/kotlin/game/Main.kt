package game
import game.model.GameState
import game.model.UnitType
import game.service.CombatManager
import game.service.EventManager
import game.service.UnitManager
fun main() {
    println("=== Kingdoms Core: Events & Combat Test ===")
    val state = GameState()
    val unitManager = UnitManager(state)
    val combatManager = CombatManager(state, unitManager)
    val eventManager = EventManager(state)
    println("\n--- Initial State ---")
    println("Gold: ${state.gold}, Wood: ${state.wood}, Food: ${state.food}")
    println("\n--- Triggering Random Events (3 Turns) ---")
    repeat(3) { turn ->
        println("\n--- Turn ${turn + 1} ---")
        eventManager.triggerRandomEvent()
        println("Resources -> Gold: ${state.gold}, Wood: ${state.wood}, Food: ${state.food}")
    }
    println("\n--- Recruitment & Combat ---")
    unitManager.recruitUnit(UnitType.SWORDSMAN, 2)
    unitManager.printArmy()
    combatManager.attackEnemy(enemyPower = 30, rewardGold = 100)
}
