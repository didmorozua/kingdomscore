package game.model
enum class UnitType(
    val displayName: String,
    val attack: Int,
    val defense: Int,
    val goldCost: Long,
    val woodCost: Long,
    val foodCost: Long,
    val foodUpkeep: Long
) {
    SWORDSMAN("Swordsman", attack = 15, defense = 20, goldCost = 50, woodCost = 10, foodCost = 20, foodUpkeep = 1),
    ARCHER("Archer", attack = 25, defense = 10, goldCost = 60, woodCost = 30, foodCost = 15, foodUpkeep = 1),
    KNIGHT("Knight", attack = 50, defense = 45, goldCost = 150, woodCost = 0, foodCost = 50, foodUpkeep = 2)
}
