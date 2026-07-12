package com.gaborwnuk.godmode

import net.minecraft.world.entity.player.Player

/**
 * Whether this player currently has personal god mode enabled. Backed by the
 * loader-specific data attachment declared in the loader entrypoint.
 */
var Player.isGod: Boolean
	//? if fabric {
	get() = getAttachedOrElse(GodModeFabric.GOD_MODE, false)
	set(value) {
		setAttached(GodModeFabric.GOD_MODE, value)
	}
	//?} else {
	/*get() = getData(GodModeNeoForge.GOD_MODE)
	set(value) {
		setData(GodModeNeoForge.GOD_MODE, value)
	}*/
	//?}
