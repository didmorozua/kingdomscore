package game.service
import game.model.GameState
import kotlin.random.Random
class EventManager(private val gameState: GameState) {
    fun triggerRandomEvent() {
        val roll = Random.nextInt(1, 100)
        println("\n📜 === RANDOM EVENT ===")
        when {
            roll <= 30 -> {
                val foundGold = Random.nextLong(100, 300)
                gameState.gold += foundGold
                println("💰 Merchant Guild Tax: Traveling merchants paid +$foundGold Gold in taxes!")
            }
            roll <= 60 -> {
                val lostWood = Random.nextLong(30, 80)
                if (gameState.wood >= lostWood) {
                    gameState.wood -= lostWood
                    println("🔥 Forest Fire: A bad storm caused a fire. Lost -$lostWood Wood!")
                } else {
                    println("🌧️ Heavy Rain: No damage caused.")
                }
            }
            else -> {
                val bonusStone = Random.nextLong(40, 90)
                gameState.stone += bonusStone
                println("⛏️ Rich Vein: Miners discovered a dense rock layer! +$bonusStone Stone.")
            }
        }
    }
}
