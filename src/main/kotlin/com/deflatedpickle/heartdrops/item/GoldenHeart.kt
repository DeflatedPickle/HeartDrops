package com.deflatedpickle.heartdrops.item

import com.deflatedpickle.heartdrops.api.HeartType
import com.deflatedpickle.heartdrops.configs.GeneralConfig

class GoldenHeart : ASpecialHeart("golden_heart", HeartType.GOLD) {
    override fun doesDrop(): Boolean = GeneralConfig.goldHeart.drop
    override fun dropChance(): Int = GeneralConfig.goldHeart.chance
    override fun dropMultiplier(): Int = GeneralConfig.goldHeart.lootingMultiplier
}