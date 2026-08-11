package com.strategygame.test

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * Фракції, до яких може належати юніт.
 */
enum class Faction {
    PLAYER,
    ENEMY,
    NEUTRAL
}

/**
 * Координати юніта на ігровій карті.
 */
data class Position(val x: Int, val y: Int)

/**
 * Базові характеристики юніта.
 */
data class UnitStats(
    val maxHp: Int,
    val attackPower: Int,
    val defense: Int,
    val speed: Int, // Дальність ходу за один хід
    val attackRange: Int
)

/**
 * Поточний стан життєдіяльності юніта.
 */
sealed interface UnitState {
    object Idle : UnitState
    object Moving : UnitState
    object Attacking : UnitState
    data class Dead(val killerId: String?) : UnitState
}

/**
 * Базовий клас для всіх ігрових одиниць (юнітів) у покроковій стратегії.
 */
open class TestUnit(
    val id: String,
    val name: String,
    val faction: Faction,
    val baseStats: UnitStats,
    initialPosition: Position
) {
    // Динамічні показники юніта
    var currentHp: Int = baseStats.maxHp
        protected set(value) {
            field = value.coerceIn(0, baseStats.maxHp)
            if (field == 0) {
                die(null)
            }
        }

    var position: Position = initialPosition
        protected set

    var movementPointsLeft: Int = baseStats.speed
        protected set

    var hasAttackedThisTurn: Boolean = false
        protected set

    // Реактивний стан юніта для UI або систем логування
    private val _state = MutableStateFlow<UnitState>(UnitState.Idle)
    val state: StateFlow<UnitState> = _state.asStateFlow()

    val isAlive: Boolean
        get() = currentHp > 0

    /**
     * Початок нового ходу: відновлюємо очки руху та можливість атаки.
     */
    open fun startTurn() {
        if (!isAlive) return
        movementPointsLeft = baseStats.speed
        hasAttackedThisTurn = false
        _state.value = UnitState.Idle
    }

    /**
     * Переміщення юніта на нову позицію.
     */
    open fun move(to: Position, cost: Int): Boolean {
        if (!isAlive || movementPointsLeft < cost) return false
        
        _state.value = UnitState.Moving
        position = to
        movementPointsLeft -= cost
        _state.value = UnitState.Idle
        return true
    }

    /**
     * НАНЕСЕННЯ шкоди іншому юніту.
     */
    open fun attack(target: TestUnit): Boolean {
        if (!isAlive || hasAttackedThisTurn) return false
        
        val distance = Math.abs(position.x - target.position.x) + Math.abs(position.y - target.position.y)
        if (distance > baseStats.attackRange) return false

        _state.value = UnitState.Attacking
        
        // Розрахунок чистої шкоди з урахуванням захисту цілі
        val damage = (baseStats.attackPower - target.baseStats.defense).coerceAtLeast(1)
        target.takeDamage(damage, attackerId = this.id)
        
        hasAttackedThisTurn = true
        _state.value = UnitState.Idle
        return true
    }

    /**
     * ОТРИМАННЯ шкоди юнітом.
     */
    open fun takeDamage(amount: Int, attackerId: String?) {
        if (!isAlive) return
        currentHp -= amount
        if (currentHp <= 0) {
            die(attackerId)
        }
    }

    /**
     * Смерть юніта.
     */
    protected open fun die(killerId: String?) {
        _state.value = UnitState.Dead(killerId)
    }

    override fun toString(): String {
        return "$name [$faction] ($currentHp/${baseStats.maxHp} HP) at $position"
    }
}
