package game.model
enum class UnitType(
    val displayName: String,
    val goldCost: Long,
    val woodCost: Long,
    val attack: Int,
    val defense: Int
) {
    SWORDSMAN("Swordsman", 50, 10, 15, 20),
    ARCHER("Archer", 40, 25, 25, 5)
}
