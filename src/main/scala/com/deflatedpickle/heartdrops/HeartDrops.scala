package com.deflatedpickle.heartdrops

import com.deflatedpickle.heartdrops.proxy.CommonProxy
import net.minecraftforge.common.config.Configuration
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.SidedProxy
import net.minecraftforge.fml.common.Mod.EventHandler
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent
import net.minecraftforge.fml.common.event.FMLInitializationEvent
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger


@Mod(modid = Reference.MOD_ID, name = Reference.NAME, version = Reference.VERSION, acceptedMinecraftVersions = Reference.ACCEPTED_VERSIONS, dependencies = "required-after:autoreglib;", modLanguage = "scala")
object HeartDrops {
  @SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
  var proxy: CommonProxy = _

  var configuration: Configuration = _

  val log: Logger = LogManager.getLogger(Reference.NAME)

  @EventHandler
  def preInit(event: FMLPreInitializationEvent): Unit = {
    log.info("Starting preInit.")
    proxy.preInit(event)
    log.info("Finished preInit.")
  }

  @EventHandler
  def init(event: FMLInitializationEvent): Unit = {
    log.info("Starting Init.")
    proxy.init(event)
    log.info("Finished Init.")
  }
}