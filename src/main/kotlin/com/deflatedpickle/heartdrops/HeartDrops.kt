/* Copyright (c) 2020 DeflatedPickle under the MIT license */

package com.deflatedpickle.heartdrops

import com.deflatedpickle.heartdrops.capability.DropHearts
import java.util.concurrent.ThreadLocalRandom
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent

@Mod(
        modid = Reference.MOD_ID,
        name = Reference.NAME,
        version = Reference.VERSION,
        acceptedMinecraftVersions = Reference.ACCEPTED_VERSIONS,
        dependencies = Reference.DEPENDENCIES,
        modLanguageAdapter = Reference.ADAPTER
)
object HeartDrops {
    val random: ThreadLocalRandom = ThreadLocalRandom.current()

    @Mod.EventHandler
    fun preInit(event: FMLPreInitializationEvent) {
        DropHearts.register()
    }
}
