package game.service
import game.model.GameState
import kotlin.random.Random
class EventManager(private val gameState: GameState) {
    fun triggerRandomEvent() {
        val roll = Random.nextInt(1, 101)
        println("=== Kingdom Event ===")
        when {
            roll <= 30 -> {
                println("☀️ Drought! Crop yields dropped. Lost 15 Food.")
                gameState.food = (gameState.food - 15).coerceAtLeast(0)
            }
            roll <= 60 -> {
                println("💰 Merchant Caravan! Trade flourish in the market. Gained +25 Gold.")
                gameState.gold += 25
            }
            roll <= 85 -> {
                println("🌲 Woodcutters' Find! Extra timber collected. Gained +20 Wood.")
                gameState.wood += 20
            }
            else -> {
                println("⚔️ Bandit Raid! Bandits looted the treasury. Lost 20 Gold.")
                gameState.gold = (gameState.gold - 20).coerceAtLeast(0)
            }
        }
    }
}
