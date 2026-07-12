package com.gaborwnuk.godmode

import net.minecraft.resources.Identifier
import org.slf4j.LoggerFactory

/**
 * Loader-independent mod constants; the per-loader entrypoints are
 * [GodModeFabric] and [GodModeNeoForge].
 */
object GodMode {
	const val MOD_ID = "godmode"
	val LOGGER = LoggerFactory.getLogger(MOD_ID)!!

	fun id(path: String): Identifier = Identifier.fromNamespaceAndPath(MOD_ID, path)
}
