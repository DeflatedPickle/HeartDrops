package com.deflatedpickle.heartdrops.init;

import com.deflatedpickle.heartdrops.items.ItemBase;
import net.minecraft.item.Item;

public class ModItems {
    public static Item heart;
    public static Item goldenHeart;

    public static void init() {
        heart = new ItemBase("heart");
        goldenHeart = new ItemBase("golden_heart");
    }
}
