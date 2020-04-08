package com.deflatedpickle.heartdrops.item

import com.deflatedpickle.heartdrops.api.HeartType
import net.minecraft.item.Item

abstract class ASpecialHeart(name: String, type: HeartType) : Heart(name, type) {
    abstract fun doesDrop(): Boolean
    abstract fun dropChance(): Int
    abstract fun dropMultiplier(): Int
}