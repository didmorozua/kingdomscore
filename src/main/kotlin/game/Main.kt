package game
import game.model.GameState
import game.model.UnitType
import game.service.UnitManager
fun main() {
    println("=== Kingdoms Core: Military Test ===")
    val state = GameState()
    val unitManager = UnitManager(state)
    println("\n--- Starting Resources ---")
    println("Gold: ${state.gold}, Wood: ${state.wood}, Food: ${state.food}")
    println("\n--- Recruiting Units ---")
    unitManager.recruitUnit(UnitType.SWORDSMAN, 1)
    unitManager.recruitUnit(UnitType.ARCHER, 1)
    println("\n--- Resources After Recruitment ---")
    println("Gold: ${state.gold}, Wood: ${state.wood}, Food: ${state.food}")
    println("\n--- Army Overview ---")
    unitManager.printArmy()
}
