/* Copyright (c) 2020 DeflatedPickle under the MIT license */

package com.deflatedpickle.heartdrops

import com.deflatedpickle.heartdrops.capability.DropHearts
import com.deflatedpickle.heartdrops.configs.GeneralConfig
import com.deflatedpickle.heartdrops.init.Item
import net.alexwells.kottle.FMLKotlinModLoadingContext
import net.minecraftforge.common.ForgeConfigSpec
import net.minecraftforge.fml.ModLoadingContext
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.config.ModConfig
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent
import java.util.concurrent.ThreadLocalRandom

@Mod(Reference.MOD_ID)
object HeartDrops {
    val eventBus = FMLKotlinModLoadingContext.get().modEventBus
    val config = ForgeConfigSpec.Builder().configure<Any> { GeneralConfig() }

    val random: ThreadLocalRandom = ThreadLocalRandom.current()

    init {
        Item.ITEMS.register(eventBus)

        eventBus.addListener<FMLCommonSetupEvent> {
            DropHearts.register()
        }

        ModLoadingContext.get().registerConfig(
                ModConfig.Type.COMMON,
                config.right
        )
    }
}
