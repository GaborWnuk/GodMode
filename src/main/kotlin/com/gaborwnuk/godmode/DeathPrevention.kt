package com.gaborwnuk.godmode

import net.minecraft.server.level.ServerLevel
import net.minecraft.world.entity.player.Player
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent

/**
 * The heart of the mod: when a player would die while godlike — either personally
 * via /god or globally via the godlike_players gamerule — the death is cancelled
 * and the player is left standing at half a heart.
 */
object DeathPrevention {
	fun onLivingDeath(event: LivingDeathEvent) {
		val player = event.entity as? Player ?: return
		val level = player.level() as? ServerLevel ?: return
		val everyoneIsGodlike = level.gameRules.get(GodModeGameRules.GODLIKE_PLAYERS.get())
		if (everyoneIsGodlike || player.isGod) {
			event.isCanceled = true
			player.health = 1.0f
		}
	}
}
