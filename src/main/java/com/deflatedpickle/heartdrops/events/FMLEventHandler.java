package com.deflatedpickle.heartdrops.events;

import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class FMLEventHandler {
    @SubscribeEvent
    public void onEntityItemPickupEvent(EntityItemPickupEvent event) {
        ItemStack item = event.getItem().getItem();
        if (item.getUnlocalizedName().equals("item.heartdrops:heart")) {
            item.shrink(1);
        }
    }
}
