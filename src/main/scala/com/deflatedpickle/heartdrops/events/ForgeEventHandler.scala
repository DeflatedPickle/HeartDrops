package com.deflatedpickle.heartdrops.events

import java.util.Objects

import com.deflatedpickle.heartdrops.HeartDrops
import com.deflatedpickle.heartdrops.api.IDropHearts
import com.deflatedpickle.heartdrops.configs.GeneralConfig
import com.deflatedpickle.heartdrops.init.ModItems
import net.minecraft.enchantment.{Enchantment, EnchantmentHelper}
import net.minecraft.entity.item.EntityItem
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.entity.projectile.EntityTippedArrow
import net.minecraft.item.ItemStack
import net.minecraftforge.event.entity.living.LivingDeathEvent
import net.minecraftforge.event.entity.player.EntityItemPickupEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent

class ForgeEventHandler {
  private var spawnCounter = 0

  @SubscribeEvent
  def onEntityItemPickupEvent(event: EntityItemPickupEvent): Unit = {
    val item = event.getItem.getItem

    if (item.getUnlocalizedName == "item.heartdrops:heart") {
      spawnCounter = item.getCount

      collectHearts(event, item)
    }
  }

  @SubscribeEvent
  def onLivingDeathEvent(event: LivingDeathEvent): Unit = {
    val entity = event.getEntity
    var player: EntityPlayer = null
    var spawnItems = false
    var lootingLevel = 0

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
        if (player.isInstanceOf[EntityPlayer]) {
          lootingLevel = EnchantmentHelper.getEnchantmentLevel(Enchantment.getEnchantmentByID(21), player.getHeldItemMainhand)
        }

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

      var dropAmount = 1

      if (event.getSource.getImmediateSource.isInstanceOf[IDropHearts]) {
        if (event.getSource.getImmediateSource.asInstanceOf[IDropHearts].doesDropHearts()) {
          dropAmount = event.getSource.getImmediateSource.asInstanceOf[IDropHearts].dropAmount()
        }
      }

      val item: EntityItem = new EntityItem(entity.world, entity.posX, entity.posY, entity.posZ, new ItemStack(ModItems.heart, dropAmount * (lootingLevel + 1)))
      entity.world.spawnEntity(item)
    }
  }

  def collectHearts(event: EntityItemPickupEvent, item: ItemStack): Unit = {
    if (spawnCounter >= 1) {
      event.getEntityPlayer.heal(2f)
      item.shrink(1)

      spawnCounter -= 1
      collectHearts(event, item)
    }
  }
}
