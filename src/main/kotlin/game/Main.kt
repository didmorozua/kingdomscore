package game
import game.model.GameState
import game.service.LandManager
import game.service.ResourceManager
fun main() {
    println("=== Kingdoms Core: Full Cycle Test ===")
    val state = GameState()
    val landManager = LandManager(state)
    val resourceManager = ResourceManager(state, landManager)
    println("\nInitial State:")
    println("Food: ${state.food}, Wood: ${state.wood}, Gold: ${state.gold}, Population: ${state.population}")
    println("\n--- Turn 1 (Base Land Production) ---")
    resourceManager.produceResources()
    println("\n--- Construction ---")
    landManager.buildOnPlot(1, "farm")
    landManager.buildOnPlot(2, "sawmill")
    println("\n--- Turn 2 (Land + Buildings Production) ---")
    resourceManager.produceResources()
}
