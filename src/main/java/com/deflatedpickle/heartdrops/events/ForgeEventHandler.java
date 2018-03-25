package com.deflatedpickle.heartdrops.events;

import com.deflatedpickle.heartdrops.configs.GeneralConfig;
import com.deflatedpickle.heartdrops.init.ModItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ForgeEventHandler {
    @SubscribeEvent
    public void onLivingDeathEvent(LivingDeathEvent event) {
        Entity entity = event.getEntity();
        EntityPlayer player = null;
        Boolean spawnItems = false;

        if (!entity.world.isRemote) {
            if (event.getSource().getImmediateSource() instanceof EntityPlayer) {
                player = (EntityPlayer) event.getSource().getImmediateSource();
            }
            else if(event.getSource().getTrueSource() instanceof EntityPlayer) {
                player = (EntityPlayer) event.getSource().getTrueSource();
            }

            if (player != null) {
                if (GeneralConfig.dropWhen == GeneralConfig.When.HURT) {
                    if (player.getHealth() < player.getMaxHealth()) {
                        spawnItems = true;
                    }
                }
                else if (GeneralConfig.dropWhen == GeneralConfig.When.ALWAYS) {
                    spawnItems = true;
                }

                if (!GeneralConfig.dropHardcore) {
                    if (player.world.getMinecraftServer().isHardcore()) {
                        return;
                    }
                }
            }

            if (!spawnItems) {
                return;
            }

            if (!player.world.getDifficulty().name().equals(GeneralConfig.dropDifficulty.toString()) && !GeneralConfig.dropDifficulty.toString().equals("ALL")) {
                return;
            }

            EntityItem item = new EntityItem(entity.world, entity.posX, entity.posY, entity.posZ, new ItemStack(ModItems.heart, 1));
            entity.world.spawnEntity(item);
        }
    }
}
