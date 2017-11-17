package com.deflatedpickle.heartdrops.proxy;

import com.deflatedpickle.heartdrops.events.FMLEventHandler;
import com.deflatedpickle.heartdrops.init.ModItems;
import net.minecraftforge.common.MinecraftForge;

public class ClientProxy implements CommonProxy{
    @Override
    public void preInit() {
        ModItems.init();
    }

    @Override
    public void init() {
        // FMLCommonHandler.instance().bus().register(new FMLEventHandler());
        MinecraftForge.EVENT_BUS.register(new FMLEventHandler());
    }
}
