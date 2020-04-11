/* Copyright (c) 2020 DeflatedPickle under the MIT license */

package com.deflatedpickle.heartdrops.item

import com.deflatedpickle.heartdrops.HeartDrops
import com.deflatedpickle.heartdrops.api.HeartType
import com.deflatedpickle.heartdrops.configs.GeneralConfig
import net.minecraft.item.ItemStack
import net.minecraft.potion.EffectInstance
import net.minecraft.potion.PotionUtils
import net.minecraftforge.event.entity.living.LivingDropsEvent
import net.minecraftforge.registries.ForgeRegistries

class CrystalHeart : ASpecialHeart(HeartType.CRYSTAL) {
    override fun doesDrop(): Boolean = GeneralConfig.crystalHeart.drop
    override fun dropChance(): Int = GeneralConfig.crystalHeart.chance
    override fun dropMultiplier(): Int = GeneralConfig.crystalHeart.lootingMultiplier

    companion object {
        fun applyPotion(itemStack: ItemStack): ItemStack {
            // How helpful it is to tell us what's bad...
            // Otherwise, you'd be stuck with good and bad effects
            // I'm not writing more code to filter them out :^)
            ForgeRegistries.POTIONS.values.filter { !it.isInstant && it.isBeneficial }.toList().apply {
                PotionUtils.appendEffects(itemStack, mutableListOf(EffectInstance(
                        this[HeartDrops.random.nextInt(this.size)],
                        // It should last for *enough* time to get use out of it
                        // TODO: Make crystal heart effects further customisable
                        HeartDrops.random.nextInt(20 * 20, 20 * 30)
                )))
            }

            return itemStack
        }
    }
}
