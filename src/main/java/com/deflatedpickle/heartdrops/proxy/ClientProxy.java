package com.deflatedpickle.heartdrops.proxy;

import com.deflatedpickle.heartdrops.init.ModItems;

public class ClientProxy implements CommonProxy{
    @Override
    public void preInit() {
        ModItems.init();
    }
}
