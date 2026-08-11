package game
import game.model.BuildingType
import game.model.GameState
import game.model.Ruler
import game.model.Trait
import game.model.UnitType
import game.service.BuildingManager
import game.service.CombatManager
import game.service.Enemy
import game.service.EventManager
import game.service.ResourceManager
import game.service.UnitManager
fun main() {
    println("👑 Kingdoms Core Initialized!")
    val state = GameState()
    val currentRuler = Ruler(name = "King Rostyslav I", age = 42, trait = Trait.BUILDER)
    val buildingManager = BuildingManager(state, currentRuler)
    val resourceManager = ResourceManager(state, buildingManager, currentRuler)
    val unitManager = UnitManager(state)
    val combatManager = CombatManager(state, unitManager)
    val eventManager = EventManager(state)
    println("Ruler: ${currentRuler.name} (Age: ${currentRuler.age}) | Trait: ${currentRuler.trait.displayName}")
    state.printStatus()
    for (turn in 1..3) {
        println("\n=================== TURN $turn ===================")
        // 1. Пасивний видобуток
        resourceManager.produceResources()
        // 2. Випадкова подія
        eventManager.triggerRandomEvent()
        // 3. Дії за розкладом
        when (turn) {
            1 -> {
                buildingManager.construct(BuildingType.GOLD_MINE)
                unitManager.recruit(UnitType.SWORDSMAN, 4)
            }
            2 -> {
                buildingManager.upgrade(BuildingType.GOLD_MINE)
                buildingManager.construct(BuildingType.LUMBER_MILL)
            }
            3 -> {
                val orcRaid = Enemy("Orc Raiders", attack = 120, defense = 180, goldLoot = 500)
                combatManager.battle(orcRaid)
            }
        }
        currentRuler.age++
        state.printStatus()
        buildingManager.printBuildings()
    }
    println("\n👑 End of simulation. Ruler age: ${currentRuler.age}")
}
