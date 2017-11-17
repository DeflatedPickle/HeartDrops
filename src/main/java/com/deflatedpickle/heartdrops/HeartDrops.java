package com.deflatedpickle.heartdrops;

import com.deflatedpickle.heartdrops.proxy.CommonProxy;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(modid = Reference.MOD_ID, name = Reference.NAME, version = Reference.VERSION, acceptedMinecraftVersions = Reference.ACCEPTED_VERSIONS, dependencies = "required-after:autoreglib;")
public class HeartDrops {
    @Instance
    public static HeartDrops instance;

    @SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
    private static CommonProxy proxy;

    private static final Logger logger = LogManager.getLogger(Reference.NAME);

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        logger.info("Starting preInit.");
        proxy.preInit();
        logger.info("Finished preInit.");
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        logger.info("Starting Init.");
        proxy.init();
        logger.info("Finished Init.");
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        logger.info("Starting postInit.");
        logger.info("Finished postInit.");
    }
}
