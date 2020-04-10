/* Copyright (c) 2020 DeflatedPickle under the MIT license */

package com.deflatedpickle.heartdrops.init

import com.deflatedpickle.heartdrops.api.HeartType
import com.deflatedpickle.heartdrops.item.CrystalHeart
import com.deflatedpickle.heartdrops.item.GoldenHeart
import com.deflatedpickle.heartdrops.item.Heart

object Item {
    val heart = Heart("heart", HeartType.NORMAL)
    val golden_heart = GoldenHeart()
    val half_heart = Heart("half_heart", HeartType.HALF)
    val crystal_heart = CrystalHeart()
}
