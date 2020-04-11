/* Copyright (c) 2020 DeflatedPickle under the MIT license */

package com.deflatedpickle.heartdrops.item

import com.deflatedpickle.heartdrops.api.HeartType
import net.minecraft.item.Item
import net.minecraft.item.ItemGroup

open class Heart(val type: HeartType) : Item(Properties().group(ItemGroup.MISC))
