/* Copyright (c) 2020 DeflatedPickle under the MIT license */

package com.deflatedpickle.heartdrops.item

import com.deflatedpickle.heartdrops.api.HeartType
import net.minecraft.item.ItemStack

abstract class ASpecialHeart(name: String, type: HeartType) : Heart(name, type) {
    override fun hasEffect(stack: ItemStack): Boolean = true

    abstract fun doesDrop(): Boolean
    abstract fun dropChance(): Int
    abstract fun dropMultiplier(): Int
}
