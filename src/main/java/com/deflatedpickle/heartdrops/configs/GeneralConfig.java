package com.deflatedpickle.heartdrops.configs;

import com.deflatedpickle.heartdrops.Reference;
import net.minecraft.world.EnumDifficulty;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.jetbrains.annotations.NotNull;

@Mod.EventBusSubscriber(modid = Reference.MOD_ID)
@Config(modid = Reference.MOD_ID, name = Reference.CONFIG_GENERAL)
@Config.LangKey("config.heartdrops.general")
public class GeneralConfig {
    @Config.Comment("The range of hearts that can drop (can be increased with looting)")
    @Config.LangKey("config.heartdrops.dropRange")
    public static Integer dropRange = 1;

    public enum DropAmount {
        SPECIFIC,
        CHANCE,
        UNTIL_FULL_HEALTH,
        PERCENTAGE_OF_MOB_HEALTH
    }

    @Config.Comment("Changes how many hearts will be dropped")
    @Config.LangKey("config.heartdrops.dropAmount")
    public static @NotNull DropAmount dropAmount = DropAmount.UNTIL_FULL_HEALTH;

    public enum When {
        HURT,
        ALWAYS,
        NEVER
    }

    @Config.Comment("Changes when the hearts will drop")
    @Config.LangKey("config.heartdrops.dropWhen")
    public static @NotNull When dropWhen = When.HURT;

    @Config.Comment("Changes whether hearts drop on hardcore mode or not")
    @Config.LangKey("config.heartdrops.dropHardcore")
    public static Boolean dropHardcore = false;

    @Config.Comment("Changes what difficulty hearts drop on")
    @Config.LangKey("config.heartdrops.dropGamemode")
    public static @NotNull EnumDifficulty dropDifficulty = EnumDifficulty.EASY;

    public enum GameMode {
        SURVIVAL,
        CREATIVE,
        ADVENTURE,
        ALL;
    }

    @Config.Comment("Settings for golden hearts")
    @Config.LangKey("config.heartdrops.goldHeartCategory")
    public static @NotNull GoldHeart goldHeart = new GoldHeart();

    public static class GoldHeart {
        @Config.Comment("Whether or not golden hearts will drop")
        @Config.LangKey("config.heartdrops.dropGoldHearts")
        public Boolean drop = true;

        @Config.Comment("The chance for a golden heart to drop")
        @Config.LangKey("config.heartdrops.goldHeartChance")
        public Integer chance = 50;

        @Config.Comment("A multiplier that increases your chance to get a golden heart")
        @Config.LangKey("config.heartdrops.goldHeartLootingMultiplier")
        public Integer lootingMultiplier = 5;
    }


    @Config.Comment("Settings for crystal hearts")
    @Config.LangKey("config.heartdrops.crystalHeartCategory")
    public static @NotNull CrystalHeart crystalHeart = new CrystalHeart();

    public static class CrystalHeart {
        @Config.Comment("Whether or not crystal hearts will drop")
        @Config.LangKey("config.heartdrops.dropcrystalHearts")
        public Boolean drop = true;

        @Config.Comment("The chance for a crystal heart to drop")
        @Config.LangKey("config.heartdrops.crystalHeartChance")
        public Integer chance = 50;

        @Config.Comment("A multiplier that increases your chance to get a crystal heart")
        @Config.LangKey("config.heartdrops.crystalHeartLootingMultiplier")
        public Integer lootingMultiplier = 5;
    }

    @SubscribeEvent
    public static void onConfigChangedEvent(ConfigChangedEvent.OnConfigChangedEvent event) {
        if (event.getModID().equals(Reference.MOD_ID)) {
            ConfigManager.sync(Reference.MOD_ID, Config.Type.INSTANCE);
        }
    }
}
