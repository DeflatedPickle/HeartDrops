package com.deflatedpickle.heartdrops.api

import com.deflatedpickle.heartdrops.init.Item
import net.minecraft.entity.item.EntityItem
import net.minecraft.item.ItemStack
import net.minecraft.potion.PotionUtils
import net.minecraftforge.event.entity.living.LivingDropsEvent
import net.minecraftforge.event.entity.player.EntityItemPickupEvent

enum class HeartType {
    HALF {
        override fun drop(event: LivingDropsEvent, heartList: MutableList<EntityItem>) {
        }

        override fun collect(event: EntityItemPickupEvent) {
        }
    },
    NORMAL {
        override fun drop(event: LivingDropsEvent, heartList: MutableList<EntityItem>) {
            if (event.entityLiving.health < event.entityLiving.maxHealth) {
                heartList.add(EntityItem(
                        event.entity.world,
                        event.entity.posX,
                        event.entity.posY,
                        event.entity.posZ,
                        ItemStack(Item.heart, 1)
                ))
            }
        }
        override fun collect(event: EntityItemPickupEvent) {
            if (event.entityLiving.health + 2 <= event.entityLiving.maxHealth) {
                event.entityPlayer.heal(2f)
            }
            else {
                event.isCanceled = true
            }
        }
    },
    GOLD {
        override fun drop(event: LivingDropsEvent, heartList: MutableList<EntityItem>) {
        }
        override fun collect(event: EntityItemPickupEvent) {
            event.entityPlayer.absorptionAmount = event.entityPlayer.absorptionAmount + 2
        }
    },
    CRYSTAL {
        override fun drop(event: LivingDropsEvent, heartList: MutableList<EntityItem>) {
        }
        override fun collect(event: EntityItemPickupEvent) {
            for (i in PotionUtils.getEffectsFromStack(event.item.item)) {
                event.entityLiving.addPotionEffect(i)
            }
        }
    };

    abstract fun drop(event: LivingDropsEvent, heartList: MutableList<EntityItem>)
    abstract fun collect(event: EntityItemPickupEvent)
}