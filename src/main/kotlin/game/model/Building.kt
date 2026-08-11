package game.model
enum class BuildingType(val displayName: String, val goldCost: Long, val woodCost: Long, val stoneCost: Long) {
    GOLD_MINE("Gold Mine", 200, 100, 50),
    LUMBER_MILL("Lumber Mill", 100, 150, 20),
    QUARRY("Stone Quarry", 150, 50, 100)
}
data class Building(val type: BuildingType, var level: Int = 1)
