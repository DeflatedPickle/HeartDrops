package com.deflatedpickle.heartdrops.proxy;

import com.deflatedpickle.heartdrops.init.ModItems;

public class ServerProxy implements CommonProxy{
    @Override
    public void preInit() {
        ModItems.init();
    }
}
