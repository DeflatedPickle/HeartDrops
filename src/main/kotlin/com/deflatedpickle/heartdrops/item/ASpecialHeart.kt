/* Copyright (c) 2020 DeflatedPickle under the MIT license */

package com.deflatedpickle.heartdrops.item

import com.deflatedpickle.heartdrops.api.HeartType

abstract class ASpecialHeart(name: String, type: HeartType) : Heart(name, type) {
    abstract fun doesDrop(): Boolean
    abstract fun dropChance(): Int
    abstract fun dropMultiplier(): Int
}
