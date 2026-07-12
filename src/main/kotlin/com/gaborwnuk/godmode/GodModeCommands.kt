package com.gaborwnuk.godmode

import com.mojang.brigadier.CommandDispatcher
import net.minecraft.commands.CommandSourceStack
import net.minecraft.commands.Commands
import net.minecraft.commands.arguments.EntityArgument
import net.minecraft.network.chat.Component
import net.minecraft.server.level.ServerPlayer
import net.minecraft.server.permissions.Permissions

/**
 * ```
 * /god                  — toggle god mode for yourself
 * /god <targets>        — toggle god mode for the given players
 * /god check [target]   — report whether a player is currently a god
 * /god list             — list all players with personal god mode enabled
 * ```
 */
object GodModeCommands {
	fun register(dispatcher: CommandDispatcher<CommandSourceStack>) {
		dispatcher.register(
			Commands.literal("god")
				.requires { it.permissions().hasPermission(Permissions.COMMANDS_OWNER) }
				.executes { ctx -> toggle(ctx.source, listOf(ctx.source.playerOrException)) }
				.then(
					Commands.literal("check")
						.executes { ctx -> check(ctx.source, ctx.source.playerOrException) }
						.then(
							Commands.argument("target", EntityArgument.player())
								.executes { ctx -> check(ctx.source, EntityArgument.getPlayer(ctx, "target")) },
						),
				)
				.then(
					Commands.literal("list")
						.executes { ctx -> list(ctx.source) },
				)
				.then(
					Commands.argument("targets", EntityArgument.players())
						.executes { ctx -> toggle(ctx.source, EntityArgument.getPlayers(ctx, "targets")) },
				),
		)
	}

	private fun toggle(source: CommandSourceStack, targets: Collection<ServerPlayer>): Int {
		for (player in targets) {
			player.isGod = !player.isGod
			val key = if (player.isGod) "commands.godmode.enabled" else "commands.godmode.disabled"
			source.sendSuccess({ Component.translatable(key, player.displayName) }, true)
		}
		return targets.size
	}

	private fun check(source: CommandSourceStack, target: ServerPlayer): Int {
		val key = when {
			source.level.gameRules.get(GodModeGameRules.GODLIKE_PLAYERS) -> "commands.godmode.check.gamerule"
			target.isGod -> "commands.godmode.check.god"
			else -> "commands.godmode.check.mortal"
		}
		source.sendSuccess({ Component.translatable(key, target.displayName) }, false)
		return 1
	}

	private fun list(source: CommandSourceStack): Int {
		if (source.level.gameRules.get(GodModeGameRules.GODLIKE_PLAYERS)) {
			source.sendSuccess({ Component.translatable("commands.godmode.list.gamerule") }, false)
		}
		val gods = source.server.playerList.players.filter { it.isGod }
		if (gods.isEmpty()) {
			source.sendSuccess({ Component.translatable("commands.godmode.list.empty") }, false)
		} else {
			val names = gods.joinToString(", ") { it.name.string }
			source.sendSuccess({ Component.translatable("commands.godmode.list", names) }, false)
		}
		return gods.size
	}
}
