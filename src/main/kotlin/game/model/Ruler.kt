package game.model
enum class Trait(val displayName: String, val taxBonus: Double) {
    GREEDY("Greedy (+20% Gold)", 0.20),
    WARRIOR("Warrior (+10% Army Power)", 0.0),
    BUILDER("Builder (-10% Construct Cost)", 0.0),
    CHARISMATIC("Charismatic (+15% Income)", 0.15)
}
data class Ruler(
    val name: String,
    var age: Int = 25,
    val trait: Trait
)
