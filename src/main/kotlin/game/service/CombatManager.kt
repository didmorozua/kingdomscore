package game.service

import game.model.GameState
import game.model.Ruler
import game.model.Trait
import game.model.UnitType

data class Enemy(val name: String, val attack: Int, val defense: Int, val goldLoot: Long)

class CombatManager(
    private val gameState: GameState,
    private val unitManager: UnitManager,
    private val ruler: Ruler? = null
) {
    fun battle(enemy: Enemy): Boolean {
        println("\n=== BATTLE: Kingdom vs ${enemy.name} ===")
        
        var totalAtk = 0
        var totalDef = 0

        unitManager.army.forEach { (type, count) ->
            totalAtk += type.attack * count
            totalDef += type.defense * count
        }

        if (ruler?.trait == Trait.WARRIOR) {
            totalAtk = (totalAtk * 1.10).toInt()
            totalDef = (totalDef * 1.10).toInt()
            println("Warrior Leader Bonus (+10% Power) activated!")
        }

        println("Player Power -> ATK: $totalAtk | DEF: $totalDef")
        println("Enemy Power  -> ATK: ${enemy.attack} | DEF: ${enemy.defense}")

        val victory = totalAtk >= enemy.defense

        if (victory) {
            val loot = enemy.goldLoot
            gameState.gold += loot
            println("VICTORY! You defeated ${enemy.name} and captured $loot Gold.")
            applyCasualties(casualtyRate = 0.2)
            return true
        } else {
            println("\nDEFEAT! Your army was destroyed and enemies captured the kingdom.")
            println("GAME OVER! Your state has fallen.")
            return false
        }
    }

    private fun applyCasualties(casualtyRate: Double) {
        val updatedArmy = mutableMapOf<UnitType, Int>()

        unitManager.army.forEach { (type, count) ->
            val dead = (count * casualtyRate).toInt()
            val remaining = count - dead
            if (remaining > 0) {
                updatedArmy[type] = remaining
            }
            if (dead > 0) {
                println("Casualties: Lost $dead x ${type.displayName}")
            }
        }

        unitManager.army.clear()
        unitManager.army.putAll(updatedArmy)
    }
}