package com.gaborwnuk.godmode

//? if fabric {
import com.mojang.serialization.Codec
import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.attachment.v1.AttachmentRegistry
import net.fabricmc.fabric.api.attachment.v1.AttachmentType
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback
import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents
import net.minecraft.core.Registry
import net.minecraft.core.registries.BuiltInRegistries

object GodModeFabric : ModInitializer {
	// Per-player god flag, saved with the player and kept across death.
	val GOD_MODE: AttachmentType<Boolean> = AttachmentRegistry.create<Boolean>(GodMode.id("god_mode")) { builder ->
		builder
			.persistent(Codec.BOOL)
			.copyOnDeath()
	}

	override fun onInitialize() {
		Registry.register(BuiltInRegistries.GAME_RULE, GodMode.id("godlike_players"), GodModeGameRules.GODLIKE_PLAYERS)

		ServerLivingEntityEvents.ALLOW_DEATH.register { entity, _, _ ->
			!DeathPrevention.tryPreventDeath(entity)
		}
		CommandRegistrationCallback.EVENT.register { dispatcher, _, _ ->
			GodModeCommands.register(dispatcher)
		}

		GodMode.LOGGER.info("God Mode ready — deaths will be politely declined")
	}
}
//?}
