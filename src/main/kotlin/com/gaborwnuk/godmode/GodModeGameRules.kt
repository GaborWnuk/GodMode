package com.gaborwnuk.godmode

import com.mojang.brigadier.arguments.BoolArgumentType
import com.mojang.serialization.Codec
import net.minecraft.world.flag.FeatureFlagSet
import net.minecraft.world.level.gamerules.GameRule
import net.minecraft.world.level.gamerules.GameRuleCategory
import net.minecraft.world.level.gamerules.GameRuleType
import net.minecraft.world.level.gamerules.GameRules

object GodModeGameRules {
	/**
	 * When on (the default), every player is immortal regardless of their personal
	 * /god flag. The instance is loader-independent; each loader entrypoint
	 * registers it into the game-rule registry as `godmode:godlike_players`.
	 */
	val GODLIKE_PLAYERS: GameRule<Boolean> = GameRule(
		GameRuleCategory.PLAYER,
		GameRuleType.BOOL,
		BoolArgumentType.bool(),
		GameRules.VisitorCaller { visitor, rule -> visitor.visitBoolean(rule) },
		Codec.BOOL,
		{ value -> if (value) 1 else 0 },
		true,
		FeatureFlagSet.of(),
	)
}
