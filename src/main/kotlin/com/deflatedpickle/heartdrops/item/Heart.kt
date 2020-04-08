package com.deflatedpickle.heartdrops.item

import com.deflatedpickle.heartdrops.Reference
import com.deflatedpickle.heartdrops.api.HeartType
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.item.Item

open class Heart(name: String, val type: HeartType) : Item() {
    init {
        this.translationKey = "${Reference.MOD_ID}:$name"
        this.maxStackSize = 1
        this.creativeTab = CreativeTabs.MISC
    }
}