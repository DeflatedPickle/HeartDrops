package com.deflatedpickle.heartdrops.proxy

import com.deflatedpickle.heartdrops.events.{FMLEventHandler, ForgeEventHandler}
import com.deflatedpickle.heartdrops.init.ModItems
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.fml.common.event.{FMLInitializationEvent, FMLPreInitializationEvent}

class CommonProxy {
  def preInit(event: FMLPreInitializationEvent): Unit = {
    ModItems.init()
  }

  def init(event: FMLInitializationEvent): Unit = {
    MinecraftForge.EVENT_BUS.register(new FMLEventHandler)
    MinecraftForge.EVENT_BUS.register(new ForgeEventHandler)
  }
}
