package com.gaborwnuk.godmode

//? if neoforge {
/*import com.mojang.serialization.Codec
import net.minecraft.core.registries.Registries
import net.minecraft.world.level.gamerules.GameRule
import net.neoforged.bus.api.IEventBus
import net.neoforged.fml.common.Mod
import net.neoforged.neoforge.attachment.AttachmentType
import net.neoforged.neoforge.common.NeoForge
import net.neoforged.neoforge.event.RegisterCommandsEvent
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent
import net.neoforged.neoforge.registries.DeferredHolder
import net.neoforged.neoforge.registries.DeferredRegister
import net.neoforged.neoforge.registries.NeoForgeRegistries
import java.util.function.Predicate
import java.util.function.Supplier

@Mod(GodMode.MOD_ID)
class GodModeNeoForge(modEventBus: IEventBus) {
	init {
		ATTACHMENTS.register(modEventBus)
		GAME_RULES.register(modEventBus)

		NeoForge.EVENT_BUS.addListener(::onLivingDeath)
		NeoForge.EVENT_BUS.addListener(::onRegisterCommands)

		GodMode.LOGGER.info("God Mode ready — deaths will be politely declined")
	}

	companion object {
		private val ATTACHMENTS: DeferredRegister<AttachmentType<*>> =
			DeferredRegister.create(NeoForgeRegistries.ATTACHMENT_TYPES, GodMode.MOD_ID)

		private val GAME_RULES: DeferredRegister<GameRule<*>> =
			DeferredRegister.create(Registries.GAME_RULE, GodMode.MOD_ID)

		// Per-player god flag, saved with the player and kept across death. Only written to disk when true.
		val GOD_MODE: DeferredHolder<AttachmentType<*>, AttachmentType<Boolean>> =
			ATTACHMENTS.register("god_mode", Supplier {
				AttachmentType.builder(Supplier { false })
					.serialize(Codec.BOOL.fieldOf("value"), Predicate { it })
					.copyOnDeath()
					.build()
			})

		init {
			GAME_RULES.register("godlike_players", Supplier { GodModeGameRules.GODLIKE_PLAYERS })
		}

		private fun onLivingDeath(event: LivingDeathEvent) {
			if (DeathPrevention.tryPreventDeath(event.entity)) event.isCanceled = true
		}

		private fun onRegisterCommands(event: RegisterCommandsEvent) {
			GodModeCommands.register(event.dispatcher)
		}
	}
}
*///?}
