/* Copyright (c) 2020 DeflatedPickle under the MIT license */

package com.deflatedpickle.heartdrops

import com.deflatedpickle.heartdrops.capability.DropHearts
import com.deflatedpickle.heartdrops.config.Config
import com.deflatedpickle.heartdrops.config.GeneralConfig
import com.deflatedpickle.heartdrops.init.Item
import java.util.concurrent.ThreadLocalRandom
import net.alexwells.kottle.FMLKotlinModLoadingContext
import net.minecraftforge.fml.ModLoadingContext
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.config.ModConfig
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent

@Mod(Reference.MOD_ID)
object HeartDrops {
    val loadingContext = ModLoadingContext.get()
    val eventBus = FMLKotlinModLoadingContext.get().modEventBus

    val random: ThreadLocalRandom = ThreadLocalRandom.current()

    init {
        Item.ITEMS.register(eventBus)

        eventBus.addListener<FMLCommonSetupEvent> {
            DropHearts.register()
        }

        ModLoadingContext.get().registerConfig(
                ModConfig.Type.COMMON,
                Config.spec
        )
        GeneralConfig.loadConfig()
    }
}
