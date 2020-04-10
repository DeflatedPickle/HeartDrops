/* Copyright (c) 2020 DeflatedPickle under the MIT license */

package com.deflatedpickle.heartdrops.item

import com.deflatedpickle.heartdrops.api.HeartType
import com.deflatedpickle.heartdrops.configs.GeneralConfig
import net.minecraft.item.ItemStack

class CrystalHeart : ASpecialHeart("crystal_heart", HeartType.CRYSTAL) {
    override fun hasEffect(stack: ItemStack): Boolean = true

    override fun doesDrop(): Boolean = GeneralConfig.crystalHeart.drop
    override fun dropChance(): Int = GeneralConfig.crystalHeart.chance
    override fun dropMultiplier(): Int = GeneralConfig.crystalHeart.lootingMultiplier
}
