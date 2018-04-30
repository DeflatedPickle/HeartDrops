package com.deflatedpickle.heartdrops.configs;

import com.deflatedpickle.heartdrops.Reference;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid = Reference.MOD_ID)
@Config(modid = Reference.MOD_ID, name = Reference.CONFIG_GENERAL, category = Configuration.CATEGORY_GENERAL)
@Config.LangKey("config.heartdrops.general")
public class GeneralConfig {
    @Config.Name("Drop Range")
    @Config.Comment("The range of hearts that can drop")
    @Config.LangKey("config.heartdrops.dropRange")
    public static Integer dropRange = 3;

    @Config.Name("Use Range")
    @Config.Comment("Changes whether or not the drop range is used.")
    @Config.LangKey("config.heartdrops.useRange")
    public static Boolean useRange = true;

    @Config.Name("Default Drop Amount")
    @Config.Comment("Changes the default amount of hearts that will drop from mobs who don't implement IDropHearts.")
    @Config.LangKey("config.heartdrops.defaultAmount")
    public static Integer defaultAmount = 1;

    public enum When {
        HURT,
        ALWAYS,
        NEVER
    }

    @Config.Name("Drop When")
    @Config.Comment("Changes when the hearts will drop.")
    @Config.LangKey("config.heartdrops.dropWhen")
    public static When dropWhen = When.HURT;

    @Config.Name("Drop On Hardcore")
    @Config.Comment("Changes whether hearts drop on hardcore mode or not.")
    @Config.LangKey("config.heartdrops.dropHardcore")
    public static Boolean dropHardcore = false;

    public enum Difficulty {
        PEACEFUL,
        EASY,
        NORMAL,
        HARD,
        ALL
    }

    @Config.Name("Drop On Difficulty")
    @Config.Comment("Changes what difficulty hearts drop on.")
    @Config.LangKey("config.heartdrops.dropGamemode")
    public static Difficulty dropDifficulty = Difficulty.ALL;

    public enum GameMode {
        SURVIVAL,
        CREATIVE,
        ADVENTURE,
        ALL
    }

    @SubscribeEvent
    public static void onConfigChangedEvent(ConfigChangedEvent.OnConfigChangedEvent event) {
        if (event.getModID().equals(Reference.MOD_ID)) {
            ConfigManager.sync(Reference.MOD_ID, Config.Type.INSTANCE);
        }
    }
}
