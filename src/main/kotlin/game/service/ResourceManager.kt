package game.service
import game.model.BuildingType
import game.model.GameState
import game.model.Ruler
import game.model.Trait
class ResourceManager(
    private val gameState: GameState,
    private val buildingManager: BuildingManager,
    private val ruler: Ruler
) {
    fun produceResources() {
        val incomeMultiplier = when (ruler.trait) {
            Trait.GREEDY -> 1.20
            Trait.CHARISMATIC -> 1.15
            else -> 1.0
        }
        val baseGold = 50L
        val baseWood = 30L
        val baseStone = 15L
        val goldBonus = buildingManager.buildings
            .filter { it.type == BuildingType.GOLD_MINE }
            .sumOf { it.level * 100L }
        val woodBonus = buildingManager.buildings
            .filter { it.type == BuildingType.LUMBER_MILL }
            .sumOf { it.level * 80L }
        val stoneBonus = buildingManager.buildings
            .filter { it.type == BuildingType.QUARRY }
            .sumOf { it.level * 60L }
        val totalGold = ((baseGold + goldBonus) * incomeMultiplier).toLong()
        val totalWood = ((baseWood + woodBonus) * incomeMultiplier).toLong()
        val totalStone = ((baseStone + stoneBonus) * incomeMultiplier).toLong()
        gameState.gold += totalGold
        gameState.wood += totalWood
        gameState.stone += totalStone
        println("--- Income: +$totalGold Gold | +$totalWood Wood | +$totalStone Stone ---")
    }
}
