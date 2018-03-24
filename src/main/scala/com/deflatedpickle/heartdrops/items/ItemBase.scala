package com.deflatedpickle.heartdrops.items

import com.deflatedpickle.heartdrops.Reference
import net.minecraft.creativetab.CreativeTabs
import vazkii.arl.item.ItemMod

class ItemBase(val name: String) extends ItemMod(name) {
  setMaxStackSize(1)
  setCreativeTab(CreativeTabs.MISC)

  override def getModNamespace: String = Reference.MOD_ID
}
