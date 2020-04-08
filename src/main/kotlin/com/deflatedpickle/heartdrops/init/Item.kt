package com.deflatedpickle.heartdrops.init

import com.deflatedpickle.heartdrops.Reference
import com.deflatedpickle.heartdrops.api.HeartType
import com.deflatedpickle.heartdrops.item.CrystalHeart
import com.deflatedpickle.heartdrops.item.GoldenHeart
import com.deflatedpickle.heartdrops.item.Heart
import com.github.upcraftlp.glasspane.api.registry.AutoRegistry

@AutoRegistry(Reference.MOD_ID)
object Item {
    @JvmField
    val heart = Heart("heart", HeartType.NORMAL)

    @JvmField
    val golden_heart = GoldenHeart()

    @JvmField
    val half_heart = Heart("half_heart", HeartType.HALF)

    @JvmField
    val crystal_heart = CrystalHeart()
}