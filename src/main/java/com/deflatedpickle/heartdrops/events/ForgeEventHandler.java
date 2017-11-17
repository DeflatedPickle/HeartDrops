package com.deflatedpickle.heartdrops.events;

import com.deflatedpickle.heartdrops.init.ModItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ForgeEventHandler {
    @SubscribeEvent
    public void onLivingDeathEvent(LivingDeathEvent event) {
        Entity entity = event.getEntity();
        if (!event.getEntity().world.isRemote) {
            EntityItem item = new EntityItem(entity.world, entity.posX, entity.posY, entity.posZ, new ItemStack(ModItems.heart, 1));
            entity.world.spawnEntity(item);
        }
    }
}
