package com.deflatedpickle.heartdrops.events

import net.minecraftforge.event.entity.player.EntityItemPickupEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent

class FMLEventHandler {
  @SubscribeEvent
  def onEntityItemPickupEvent(event: EntityItemPickupEvent): Unit = {
    val item = event.getItem.getItem
    if (item.getUnlocalizedName == "item.heartdrops:heart") {
      event.getEntityPlayer.heal(2f)
      item.shrink(1)
    }
  }
}
