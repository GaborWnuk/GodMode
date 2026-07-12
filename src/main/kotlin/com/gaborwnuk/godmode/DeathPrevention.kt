package com.gaborwnuk.godmode

import net.minecraft.server.level.ServerLevel
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.player.Player

/**
 * The heart of the mod: when a player would die while godlike — either personally
 * via /god or globally via the godlike_players gamerule — the death is cancelled
 * and the player is left standing at half a heart.
 */
object DeathPrevention {
	/**
	 * Called when [entity] is about to die. Returns true — after healing the
	 * player back to half a heart — if the death should be cancelled. Each loader
	 * entrypoint hooks this into its own death event.
	 */
	fun tryPreventDeath(entity: LivingEntity): Boolean {
		val player = entity as? Player ?: return false
		val level = player.level() as? ServerLevel ?: return false
		val everyoneIsGodlike = level.gameRules.get(GodModeGameRules.GODLIKE_PLAYERS)
		if (everyoneIsGodlike || player.isGod) {
			player.health = 1.0f
			return true
		}
		return false
	}
}
