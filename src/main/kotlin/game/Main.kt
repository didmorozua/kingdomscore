package game
import game.model.GameState
import game.model.DynastyManager
import game.service.EventManager
import game.service.TurnManager
fun main() {
    println("=== Kingdoms Core: Turn Loop Test ===")
    val state = GameState()
    val dynastyManager = DynastyManager()
    val eventManager = EventManager(state)
    val turnManager = TurnManager(state, dynastyManager, eventManager)
    println("\n--- Kingdom Monarch ---")
    dynastyManager.printRulerInfo()
    // Проганяємо 3 ходи поспіль
    repeat(3) {
        turnManager.processNextTurn()
    }
}
