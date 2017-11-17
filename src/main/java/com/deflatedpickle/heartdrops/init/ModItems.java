package com.deflatedpickle.heartdrops.init;

import com.deflatedpickle.heartdrops.items.ItemHeart;
import net.minecraft.item.Item;

public class ModItems {
    public static Item heart;

    public static void init() {
        heart = new ItemHeart();
    }
}
