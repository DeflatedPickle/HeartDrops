package com.deflatedpickle.heartdrops.init;

import com.deflatedpickle.heartdrops.items.ItemBase;
import com.deflatedpickle.heartdrops.items.ItemBottle;
import net.minecraft.item.Item;

public class ModItems {
    public static Item heart;
    public static Item heartBottle;

    public static void init() {
        heart = new ItemBase("heart");
        heartBottle = new ItemBottle();
    }
}
