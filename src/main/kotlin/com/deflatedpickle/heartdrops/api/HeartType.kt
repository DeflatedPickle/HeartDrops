/* Copyright (c) 2020 DeflatedPickle under the MIT license */

package com.deflatedpickle.heartdrops.api

import com.deflatedpickle.heartdrops.init.Item
import com.deflatedpickle.heartdrops.item.CrystalHeart
import net.minecraft.entity.item.EntityItem
import net.minecraft.item.ItemStack
import net.minecraft.potion.PotionUtils
import net.minecraftforge.event.entity.living.LivingDropsEvent
import net.minecraftforge.event.entity.player.EntityItemPickupEvent

enum class HeartType(val healsBy: Int) {
    HALF(1) {
        override fun drop(event: LivingDropsEvent, amount: Int, heartList: MutableList<EntityItem>): ItemStack =
                dropAtPlayer(event, ItemStack(Item.half_heart), heartList)

        override fun collect(event: EntityItemPickupEvent) {
            if (event.entityLiving.health + healsBy <= event.entityLiving.maxHealth) {
                event.entityPlayer.heal(healsBy.toFloat())
            } else {
                event.isCanceled = true
            }
        }
    },
    NORMAL(2) {
        override fun drop(event: LivingDropsEvent, amount: Int, heartList: MutableList<EntityItem>): ItemStack =
                dropAtPlayer(event, ItemStack(Item.heart), heartList)

        override fun collect(event: EntityItemPickupEvent) {
            if (event.entityLiving.health + healsBy <= event.entityLiving.maxHealth) {
                event.entityPlayer.heal(healsBy.toFloat())
            } else {
                event.isCanceled = true
            }
        }
    },
    GOLD(0) {
        override fun drop(event: LivingDropsEvent, amount: Int, heartList: MutableList<EntityItem>): ItemStack =
                dropAtPlayer(event, ItemStack(Item.golden_heart), heartList)

        override fun collect(event: EntityItemPickupEvent) {
            event.entityPlayer.absorptionAmount = event.entityPlayer.absorptionAmount + 2
        }
    },
    CRYSTAL(0) {
        override fun drop(event: LivingDropsEvent, amount: Int, heartList: MutableList<EntityItem>): ItemStack =
                dropAtPlayer(event, CrystalHeart.applyPotion(ItemStack(Item.crystal_heart)), heartList)

        override fun collect(event: EntityItemPickupEvent) {
            for (i in PotionUtils.getEffectsFromStack(event.item.item)) {
                event.entityLiving.addPotionEffect(i)
            }
        }
    };

    abstract fun drop(event: LivingDropsEvent, amount: Int, heartList: MutableList<EntityItem>): ItemStack
    abstract fun collect(event: EntityItemPickupEvent)

    companion object {
        fun dropAtPlayer(event: LivingDropsEvent, stack: ItemStack, heartList: MutableList<EntityItem>): ItemStack {
            heartList.add(EntityItem(
                    event.entity.world,
                    event.entity.posX,
                    event.entity.posY,
                    event.entity.posZ,
                    stack
            ))

            return stack
        }
    }
}
