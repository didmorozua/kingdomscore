package game.service

import game.model.Building
import game.model.BuildingType
import game.model.GameState
import game.model.Ruler
import game.model.Trait

class BuildingManager(
    private val gameState: GameState,
    private val ruler: Ruler
) {
    val buildings = mutableListOf<Building>()

    private val costMultiplier: Double
        get() = if (ruler.trait == Trait.BUILDER) 0.9 else 1.0

    fun construct(type: BuildingType): Boolean {
        val goldCost = (type.goldCost * costMultiplier).toLong()
        val woodCost = (type.woodCost * costMultiplier).toLong()
        val stoneCost = (type.stoneCost * costMultiplier).toLong()

        if (gameState.gold >= goldCost && gameState.wood >= woodCost && gameState.stone >= stoneCost) {
            gameState.gold -= goldCost
            gameState.wood -= woodCost
            gameState.stone -= stoneCost
            buildings.add(Building(type))
            println("Successfully built: ${type.displayName} (Spent: $goldCost Gold, $woodCost Wood, $stoneCost Stone)")
            return true
        } else {
            println("Not enough resources to build ${type.displayName}!")
            return false
        }
    }

    fun upgrade(type: BuildingType): Boolean {
        val building = buildings.find { it.type == type }
        if (building == null) {
            println("Building ${type.displayName} is not built yet!")
            return false
        }

        val upgradeGold = ((type.goldCost * 0.8 * building.level) * costMultiplier).toLong()
        val upgradeWood = ((type.woodCost * 0.8 * building.level) * costMultiplier).toLong()
        val upgradeStone = ((type.stoneCost * 0.8 * building.level) * costMultiplier).toLong()

        if (gameState.gold >= upgradeGold && gameState.wood >= upgradeWood && gameState.stone >= upgradeStone) {
            gameState.gold -= upgradeGold
            gameState.wood -= upgradeWood
            gameState.stone -= upgradeStone
            building.level++
            println("Successfully upgraded: ${type.displayName} to Level ${building.level} (Spent: $upgradeGold Gold, $upgradeWood Wood, $upgradeStone Stone)")
            return true
        } else {
            println("Not enough resources to upgrade ${type.displayName}!")
            return false
        }
    }

    fun printBuildings() {
        println("=== Built Structures (${buildings.size}) ===")
        if (buildings.isEmpty()) {
            println("No buildings constructed.")
        } else {
            buildings.forEach { println("- ${it.type.displayName} (Level ${it.level})") }
        }
    }
}