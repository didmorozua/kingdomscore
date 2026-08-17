package game.model
enum class TerrainType(
    val displayName: String,
    val foodBonus: Long = 0,
    val woodBonus: Long = 0,
    val goldBonus: Long = 0,
    val dropChance: Double
) {
    PLAINS("Plains", foodBonus = 2, goldBonus = 1, dropChance = 0.38),
    FOREST("Forest", woodBonus = 2, foodBonus = 1, dropChance = 0.24),
    MOUNTAINS("Mountains", goldBonus = 3, dropChance = 0.16),
    LAKE("Lake", foodBonus = 3, dropChance = 0.16),
    RUINS("Ruins", goldBonus = 2, dropChance = 0.06)
}
data class LandPlot(
    val id: Int,
    val terrain: TerrainType,
    var buildingId: String? = null,
    var ruinLooted: Boolean = false
) {
    val hasBuilding: Boolean
        get() = buildingId != null
}
