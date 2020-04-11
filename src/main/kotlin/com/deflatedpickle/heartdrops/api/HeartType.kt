/* Copyright (c) 2020 DeflatedPickle under the MIT license */

package com.deflatedpickle.heartdrops.api

import com.deflatedpickle.heartdrops.HeartDrops
import com.deflatedpickle.heartdrops.init.Item
import com.deflatedpickle.heartdrops.item.CrystalHeart
import net.minecraft.entity.item.ItemEntity
import net.minecraft.item.ItemStack
import net.minecraft.potion.EffectInstance
import net.minecraft.potion.PotionUtils
import net.minecraftforge.event.entity.living.LivingDropsEvent
import net.minecraftforge.event.entity.player.EntityItemPickupEvent
import net.minecraftforge.registries.ForgeRegistries

enum class HeartType(val healsBy: Int) {
    HALF(1) {
        override fun drop(event: LivingDropsEvent, amount: Int, heartList: MutableList<ItemEntity>): ItemStack =
            dropAtPlayer(event, ItemStack(Item.half_heart.get()!!), heartList)

        override fun collect(event: EntityItemPickupEvent) {
            if (event.entityLiving.health + healsBy <= event.entityLiving.maxHealth) {
                event.player.heal(healsBy.toFloat())
            } else {
                event.isCanceled = true
            }
        }
    },
    NORMAL(2) {
        override fun drop(event: LivingDropsEvent, amount: Int, heartList: MutableList<ItemEntity>): ItemStack =
            dropAtPlayer(event, ItemStack(Item.heart.get()!!), heartList)

        override fun collect(event: EntityItemPickupEvent) {
            if (event.entityLiving.health + healsBy <= event.entityLiving.maxHealth) {
                event.player.heal(healsBy.toFloat())
            } else {
                event.isCanceled = true
            }
        }
    },
    GOLD(0) {
        override fun drop(event: LivingDropsEvent, amount: Int, heartList: MutableList<ItemEntity>): ItemStack =
            dropAtPlayer(event, ItemStack(Item.golden_heart.get()!!), heartList)

        override fun collect(event: EntityItemPickupEvent) {
            event.player.absorptionAmount = event.player.absorptionAmount + 2
        }
    },
    CRYSTAL(0) {
        override fun drop(event: LivingDropsEvent, amount: Int, heartList: MutableList<ItemEntity>): ItemStack =
            dropAtPlayer(event, CrystalHeart.applyPotion(ItemStack(Item.crystal_heart.get()!!)), heartList)

        override fun collect(event: EntityItemPickupEvent) {
            for (i in PotionUtils.getEffectsFromStack(event.item.item)) {
                event.entityLiving.addPotionEffect(i)
            }
        }
    };

    abstract fun drop(event: LivingDropsEvent, amount: Int, heartList: MutableList<ItemEntity>): ItemStack
    abstract fun collect(event: EntityItemPickupEvent)

    companion object {
        fun dropAtPlayer(event: LivingDropsEvent, stack: ItemStack, heartList: MutableList<ItemEntity>): ItemStack {
            heartList.add(ItemEntity(
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
