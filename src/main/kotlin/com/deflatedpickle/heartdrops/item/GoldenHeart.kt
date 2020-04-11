/* Copyright (c) 2020 DeflatedPickle under the MIT license */

package com.deflatedpickle.heartdrops.item

import com.deflatedpickle.heartdrops.api.HeartType
import com.deflatedpickle.heartdrops.config.Config
import com.deflatedpickle.heartdrops.config.GeneralConfig

class GoldenHeart : ASpecialHeart(HeartType.GOLD) {
    override fun doesDrop(): Boolean = Config.goldHeartDrop.get()
    override fun dropChance(): Int = Config.goldHeartChance.get()
    override fun dropMultiplier(): Int = Config.goldHeartLootingMultiplier.get()
}
