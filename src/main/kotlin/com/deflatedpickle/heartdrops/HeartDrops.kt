package com.deflatedpickle.heartdrops

import com.deflatedpickle.heartdrops.capability.DropHearts
import com.deflatedpickle.heartdrops.event.ForgeEventHandler
import com.deflatedpickle.heartdrops.init.Item
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.event.FMLInitializationEvent
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import java.util.concurrent.ThreadLocalRandom

@Mod(
        modid = Reference.MOD_ID,
        name = Reference.NAME,
        version = Reference.VERSION,
        acceptedMinecraftVersions = Reference.ACCEPTED_VERSIONS,
        dependencies = Reference.DEPENDENCIES,
        modLanguageAdapter = Reference.ADAPTER
)
object HeartDrops {
    val log: Logger = LogManager.getLogger(Reference.NAME)

    val random: ThreadLocalRandom = ThreadLocalRandom.current()

    @Mod.EventHandler
    fun preInit(event: FMLPreInitializationEvent) {
        DropHearts.register()
    }

    @Mod.EventHandler
    fun init(event: FMLInitializationEvent) {
        log.info("Starting Init.")
        MinecraftForge.EVENT_BUS.register(ForgeEventHandler())
        log.info("Finished Init.")
    }
}