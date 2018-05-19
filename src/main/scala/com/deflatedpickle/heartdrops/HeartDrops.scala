package com.deflatedpickle.heartdrops

import java.util.concurrent.ThreadLocalRandom

import com.deflatedpickle.heartdrops.events.ForgeEventHandler
import com.deflatedpickle.heartdrops.init.ModItems
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.common.config.Configuration
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.Mod.EventHandler
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent
import net.minecraftforge.fml.common.event.FMLInitializationEvent
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger

import scala.util.Random


@Mod(modid = Reference.MOD_ID, name = Reference.NAME, version = Reference.VERSION, acceptedMinecraftVersions = Reference.ACCEPTED_VERSIONS, dependencies = "required-after:autoreglib;", modLanguage = "scala")
object HeartDrops {
  var configuration: Configuration = _

  val log: Logger = LogManager.getLogger(Reference.NAME)

  val random: ThreadLocalRandom = ThreadLocalRandom.current()

  @EventHandler
  def preInit(event: FMLPreInitializationEvent): Unit = {
    log.info("Starting preInit.")
    ModItems.init()
    log.info("Finished preInit.")
  }

  @EventHandler
  def init(event: FMLInitializationEvent): Unit = {
    log.info("Starting Init.")
    MinecraftForge.EVENT_BUS.register(new ForgeEventHandler)
    log.info("Finished Init.")
  }
}
