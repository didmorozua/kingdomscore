package game.service
import game.model.GameState
import game.model.LandPlot
import game.model.TerrainType
import kotlin.random.Random
class LandManager(private val gameState: GameState) {
    val plots = mutableListOf<LandPlot>()
    private var nextPlotId = 1
    var expansionCount = 0
        private set
    init {
        repeat(12) {
            plots.add(generateRandomPlot())
        }
    }
    private fun generateRandomPlot(): LandPlot {
        val rand = Random.nextDouble()
        val terrain = when {
            rand < 0.38 -> TerrainType.PLAINS
            rand < 0.62 -> TerrainType.FOREST
            rand < 0.78 -> TerrainType.MOUNTAINS
            rand < 0.94 -> TerrainType.LAKE
            else -> TerrainType.RUINS
        }
        return LandPlot(id = nextPlotId++, terrain = terrain)
    }
    fun getExpansionCost(): Pair<Long, Long> {
        val woodCost = 30L + 20L * expansionCount
        val goldCost = 25L + 15L * expansionCount
        return Pair(woodCost, goldCost)
    }
    fun expandLand(): Boolean {
        val (woodCost, goldCost) = getExpansionCost()
        if (gameState.wood >= woodCost && gameState.gold >= goldCost) {
            gameState.wood -= woodCost
            gameState.gold -= goldCost
            expansionCount++
            repeat(5) {
                plots.add(generateRandomPlot())
            }
            println("Land expanded! Added 5 new plots. Total plots: ${plots.size}")
            return true
        } else {
            println("Not enough resources for land expansion (Requires: $woodCost Wood, $goldCost Gold)")
            return false
        }
    }
    fun buildOnPlot(plotId: Int, buildingId: String): Boolean {
        val plot = plots.find { it.id == plotId }
        if (plot == null) {
            println("Land plot #$plotId not found.")
            return false
        }
        if (plot.hasBuilding) {
            println("Land plot #$plotId is already occupied!")
            return false
        }
        plot.buildingId = buildingId
        println("Successfully built '$buildingId' on plot #$plotId (${plot.terrain.displayName}).")
        if (plot.terrain == TerrainType.RUINS && !plot.ruinLooted) {
            plot.ruinLooted = true
            gameState.gold += 30
            println("Looted ruins! Received +30 Gold bonus.")
        }
        return true
    }
}
