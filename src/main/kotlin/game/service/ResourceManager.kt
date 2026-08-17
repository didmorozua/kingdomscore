package game.service
import game.model.GameState
import kotlin.math.roundToLong
class ResourceManager(
    private val gameState: GameState,
    private val landManager: LandManager
) {
    fun produceResources() {
        var foodGain = 0L
        var woodGain = 0L
        var goldGain = 0L
        // 1. Прибуток від земель та будівель на них
        landManager.plots.forEach { plot ->
            // Бонуси від типу землі
            foodGain += plot.terrain.foodBonus
            woodGain += plot.terrain.woodBonus
            goldGain += plot.terrain.goldBonus
            // Бонуси від будівель
            when (plot.buildingId) {
                "farm" -> foodGain += 2
                "sawmill" -> woodGain += 2
                "market" -> goldGain += 3
            }
        }
        // 2. Споживання їжі населенням (0.35 на 1 людину)
        val foodConsumption = (gameState.population * 0.35).roundToLong()
        // 3. Застосування результатів
        gameState.food = (gameState.food + foodGain - foodConsumption).coerceAtLeast(0)
        gameState.wood += woodGain
        gameState.gold += goldGain
        println("--- Resource Cycle (Tick) ---")
        println("Produced: +$foodGain Food, +$woodGain Wood, +$goldGain Gold")
        println("Population consumed: -$foodConsumption Food")
        println("Current Stock: ${gameState.food} Food, ${gameState.wood} Wood, ${gameState.gold} Gold")
    }
}
