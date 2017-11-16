package com.deflatedpickle.heartdrops.items;

import com.deflatedpickle.heartdrops.Reference;
import net.minecraft.creativetab.CreativeTabs;
import vazkii.arl.item.ItemMod;

public class ItemHeart extends ItemMod {
    public ItemHeart() {
        super("heart");
        setCreativeTab(CreativeTabs.MISC);
    }

    @Override
    public String getModNamespace() {
        return Reference.MOD_ID;
    }
}
