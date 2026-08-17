package game
import game.model.GameState
import game.model.DynastyManager
import game.service.EventManager
fun main() {
    println("=== Kingdoms Core: Dynasty Test ===")
    val state = GameState()
    val dynastyManager = DynastyManager()
    val eventManager = EventManager(state)
    println("\n--- Kingdom Monarch ---")
    dynastyManager.printRulerInfo()
    println("\n--- Initial Gold: ${state.gold} ---")
    println("\n--- Turn 1 ---")
    eventManager.triggerRandomEvent()
    // Применяем экономический бонус монарха
    val buff = dynastyManager.currentRuler.economicBonus
    state.gold += buff
    println("👑 Ruler's Treasury Collection: +$buff Gold")
    println("Current Gold: ${state.gold}")
}
