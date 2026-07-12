package com.gaborwnuk.godmode

import com.mojang.serialization.Codec
import net.minecraft.world.entity.player.Player
import net.neoforged.neoforge.attachment.AttachmentType
import net.neoforged.neoforge.registries.DeferredHolder
import net.neoforged.neoforge.registries.DeferredRegister
import net.neoforged.neoforge.registries.NeoForgeRegistries
import java.util.function.Predicate
import java.util.function.Supplier

object GodModeAttachments {
	val REGISTER: DeferredRegister<AttachmentType<*>> =
		DeferredRegister.create(NeoForgeRegistries.ATTACHMENT_TYPES, GodMode.MOD_ID)

	/** Per-player god flag, saved with the player and kept across death. Only written to disk when true. */
	val GOD_MODE: DeferredHolder<AttachmentType<*>, AttachmentType<Boolean>> =
		REGISTER.register("god_mode", Supplier {
			AttachmentType.builder(Supplier { false })
				.serialize(Codec.BOOL.fieldOf("value"), Predicate { it })
				.copyOnDeath()
				.build()
		})
}

/** Whether this player currently has personal god mode enabled. */
var Player.isGod: Boolean
	get() = getData(GodModeAttachments.GOD_MODE)
	set(value) {
		setData(GodModeAttachments.GOD_MODE, value)
	}
