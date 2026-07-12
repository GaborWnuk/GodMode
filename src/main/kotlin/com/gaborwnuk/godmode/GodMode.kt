package com.gaborwnuk.godmode

import net.neoforged.bus.api.IEventBus
import net.neoforged.fml.common.Mod
import net.neoforged.neoforge.common.NeoForge
import org.slf4j.LoggerFactory

@Mod(GodMode.MOD_ID)
class GodMode(modEventBus: IEventBus) {
	init {
		GodModeAttachments.REGISTER.register(modEventBus)
		GodModeGameRules.REGISTER.register(modEventBus)

		NeoForge.EVENT_BUS.addListener(DeathPrevention::onLivingDeath)
		NeoForge.EVENT_BUS.addListener(GodModeCommands::register)

		LOGGER.info("God Mode ready — deaths will be politely declined")
	}

	companion object {
		const val MOD_ID = "godmode"
		val LOGGER = LoggerFactory.getLogger(MOD_ID)!!
	}
}
