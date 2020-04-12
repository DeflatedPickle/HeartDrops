/* Copyright (c) 2020 DeflatedPickle under the MIT license */

package com.deflatedpickle.heartdrops.item

import com.deflatedpickle.heartdrops.Reference
import com.deflatedpickle.heartdrops.api.HeartType
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.item.Item
import net.minecraft.util.ResourceLocation

open class Heart(name: String, val type: HeartType) : Item() {
    init {
        this.registryName = ResourceLocation(Reference.MOD_ID, name)
        this.unlocalizedName = this.registryName.toString()
        this.maxStackSize = 1
        this.creativeTab = CreativeTabs.MISC
    }
}
