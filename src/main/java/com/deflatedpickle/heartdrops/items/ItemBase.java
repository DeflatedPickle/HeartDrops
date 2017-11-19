package com.deflatedpickle.heartdrops.items;

import com.deflatedpickle.heartdrops.Reference;
import net.minecraft.creativetab.CreativeTabs;
import vazkii.arl.item.ItemMod;

public class ItemBase extends ItemMod {
    public ItemBase(String name) {
        super(name);
        setMaxStackSize(1);
        setCreativeTab(CreativeTabs.MISC);
    }

    @Override
    public String getModNamespace() {
        return Reference.MOD_ID;
    }
}
