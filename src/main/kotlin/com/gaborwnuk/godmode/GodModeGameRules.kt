package com.gaborwnuk.godmode

import com.mojang.brigadier.arguments.BoolArgumentType
import com.mojang.serialization.Codec
import net.minecraft.core.registries.Registries
import net.minecraft.world.flag.FeatureFlagSet
import net.minecraft.world.level.gamerules.GameRule
import net.minecraft.world.level.gamerules.GameRuleCategory
import net.minecraft.world.level.gamerules.GameRuleType
import net.minecraft.world.level.gamerules.GameRules
import net.neoforged.neoforge.registries.DeferredRegister

object GodModeGameRules {
	val REGISTER: DeferredRegister<GameRule<*>> =
		DeferredRegister.create(Registries.GAME_RULE, GodMode.MOD_ID)

	/** When on (the default), every player is immortal regardless of their personal /god flag. */
	val GODLIKE_PLAYERS = REGISTER.register("godlike_players") { ->
		GameRule(
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
}
