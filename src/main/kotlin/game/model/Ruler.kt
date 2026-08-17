package game.model
enum class Trait(val displayName: String, val taxBonus: Double = 0.0) {
    GREEDY("Greedy (+20% Gold)", 0.20),
    WARRIOR("Warrior (+10% Army Power)", 0.0),
    BUILDER("Builder (-10% Construct Cost)", 0.0),
    CHARISMATIC("Charismatic (+15% Income)", 0.15)
}
data class Ruler(
    val name: String,
    val title: String,
    val militaryBonus: Int,
    val economicBonus: Long,
    val trait: Trait = Trait.WARRIOR
)
class DynastyManager {
    var currentRuler: Ruler = Ruler(
        name = "Arthur I",
        title = "The Just",
        militaryBonus = 10,
        economicBonus = 15,
        trait = Trait.WARRIOR
    )
    fun printRulerInfo() {
        println("👑 Current Ruler: King ${currentRuler.name} ${currentRuler.title}")
        println("   * Trait: ${currentRuler.trait.displayName}")
        println("   * Military Buff: +${currentRuler.militaryBonus} Power")
        println("   * Economic Buff: +${currentRuler.economicBonus} Gold/turn")
    }
}
