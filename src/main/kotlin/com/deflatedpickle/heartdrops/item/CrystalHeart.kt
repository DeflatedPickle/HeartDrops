package com.deflatedpickle.heartdrops.item

import com.deflatedpickle.heartdrops.api.HeartType
import com.deflatedpickle.heartdrops.configs.GeneralConfig

class CrystalHeart : ASpecialHeart("crystal", HeartType.CRYSTAL) {
    override fun doesDrop(): Boolean = GeneralConfig.crystalHeart.drop
    override fun dropChance(): Int = GeneralConfig.crystalHeart.chance
    override fun dropMultiplier(): Int = GeneralConfig.crystalHeart.lootingMultiplier
}