package game.service
import game.model.DynastyManager
import game.model.GameState
class TurnManager(
    private val gameState: GameState,
    private val dynastyManager: DynastyManager,
    private val eventManager: EventManager
) {
    var turnNumber: Int = 1
        private set
    fun processNextTurn() {
        println("\n==========================================")
        println("       🏰 TURN $turnNumber BEGINS 🏰       ")
        println("==========================================")
        // 1. Економічний бонус правителя
        val rulerBonus = dynastyManager.currentRuler.economicBonus
        gameState.gold += rulerBonus
        println("👑 Ruler Bonus: +$rulerBonus Gold collected for treasury.")
        // 2. Випадкова подія
        eventManager.triggerRandomEvent()
        // 3. Підсумковий стан
        println("\n📊 --- Turn $turnNumber Summary ---")
        println("Gold: ${gameState.gold} | Wood: ${gameState.wood} | Food: ${gameState.food}")
        turnNumber++
    }
}
