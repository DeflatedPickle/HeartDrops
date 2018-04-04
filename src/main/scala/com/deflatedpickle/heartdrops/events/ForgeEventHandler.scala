package com.deflatedpickle.heartdrops.events

import java.util.Objects

import com.deflatedpickle.heartdrops.configs.GeneralConfig
import com.deflatedpickle.heartdrops.init.ModItems
import net.minecraft.entity.Entity
import net.minecraft.entity.item.EntityItem
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.ItemStack
import net.minecraftforge.event.entity.living.LivingDeathEvent
import net.minecraftforge.event.entity.player.EntityItemPickupEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent

class ForgeEventHandler {
  @SubscribeEvent
  def onEntityItemPickupEvent(event: EntityItemPickupEvent): Unit = {
    val item = event.getItem.getItem

    if (item.getUnlocalizedName == "item.heartdrops:heart") {
      event.getEntityPlayer.heal(2f)
      item.shrink(1)
    }
  }

  @SubscribeEvent
  def onLivingDeathEvent(event: LivingDeathEvent): Unit = {
    val entity = event.getEntity
    var player: EntityPlayer = null
    var spawnItems = false

    if (!entity.world.isRemote) {
      if (event.getSource.getImmediateSource.isInstanceOf[EntityPlayer]) {
        player = event.getSource.getImmediateSource.asInstanceOf[EntityPlayer]
      }
      else {
        if (event.getSource.getTrueSource.isInstanceOf[EntityPlayer]) {
          player = event.getSource.getTrueSource.asInstanceOf[EntityPlayer]
        }
      }

      if (player != null) {
        if (GeneralConfig.dropWhen == GeneralConfig.When.HURT) {
          if (player.getHealth < player.getMaxHealth) {
            spawnItems = true
          }
        }
        else {
          if (GeneralConfig.dropWhen == GeneralConfig.When.ALWAYS) {
            spawnItems = true
          }
        }

        if (!GeneralConfig.dropHardcore) {
          if (Objects.requireNonNull(player.world.getMinecraftServer).isHardcore) {
            return
          }
        }
      }

      if (!spawnItems) {
        return
      }

      if (!(player.world.getDifficulty.name == GeneralConfig.dropDifficulty.toString) && !(GeneralConfig.dropDifficulty.toString == "ALL")) {
        return
      }

      val item: EntityItem = new EntityItem(entity.world, entity.posX, entity.posY, entity.posZ, new ItemStack(ModItems.heart, 1))
      entity.world.spawnEntity(item)
    }
  }
}
