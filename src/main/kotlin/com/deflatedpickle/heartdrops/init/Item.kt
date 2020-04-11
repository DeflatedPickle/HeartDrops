/* Copyright (c) 2020 DeflatedPickle under the MIT license */

package com.deflatedpickle.heartdrops.init

import com.deflatedpickle.heartdrops.Reference
import com.deflatedpickle.heartdrops.api.HeartType
import com.deflatedpickle.heartdrops.item.CrystalHeart
import com.deflatedpickle.heartdrops.item.GoldenHeart
import com.deflatedpickle.heartdrops.item.Heart
import net.minecraftforge.fml.RegistryObject
import net.minecraftforge.registries.DeferredRegister
import net.minecraftforge.registries.ForgeRegistries

object Item {
    val ITEMS = DeferredRegister(ForgeRegistries.ITEMS, Reference.MOD_ID)

    val heart: RegistryObject<Heart> = ITEMS.register("heart") { Heart(HeartType.NORMAL) }
    val golden_heart: RegistryObject<GoldenHeart> = ITEMS.register("golden_heart") { GoldenHeart() }
    val half_heart: RegistryObject<Heart> = ITEMS.register("half_heart") { Heart(HeartType.HALF) }
    val crystal_heart = ITEMS.register("crystal_heart") { CrystalHeart() }
}
