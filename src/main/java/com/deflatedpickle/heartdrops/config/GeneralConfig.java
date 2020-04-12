/* Copyright (c) 2020 DeflatedPickle under the MIT license */

package com.deflatedpickle.heartdrops.config;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;
import net.minecraftforge.fml.loading.FMLPaths;

// @Mod.EventBusSubscriber(modid = Reference.MOD_ID)
// @Config(modid = Reference.MOD_ID, name = Reference.CONFIG_GENERAL)
// @Config.LangKey("config.heartdrops.general")
public class GeneralConfig {
  public static void loadConfig() {
    CommentedFileConfig configData =
        CommentedFileConfig.builder(FMLPaths.CONFIGDIR.get().resolve("heartdrops.toml"))
            .sync()
            .autosave()
            .writingMode(WritingMode.REPLACE)
            .build();

    configData.load();
    Config.INSTANCE.getSpec().setConfig(configData);
  }

  // public static Integer dropRange = 1;

  /*  public enum DropAmount {
    SPECIFIC,
    CHANCE,
    UNTIL_FULL_HEALTH,
    PERCENTAGE_OF_MOB_HEALTH
  }*/

  // public static @NotNull DropAmount dropAmount = DropAmount.UNTIL_FULL_HEALTH;

  // public static @NotNull Integer dropAmountValue = 20;

  // public static @NotNull Boolean deriveFromDropped = true;

  // public static @NotNull Boolean dropHalf = true;

  // public static @NotNull Boolean capAtHealth = true;

  /*public enum When {
    HURT,
    ALWAYS,
    NEVER
  }*/

  // public static @NotNull When dropWhen = When.HURT;

  // public static Boolean dropHardcore = false;

  /*public enum Difficulty {
    PEACEFUL,
    EASY,
    NORMAL,
    HARD,
    ALL
  }*/

  // public static @NotNull Difficulty dropDifficulty = Difficulty.ALL;

  /*public enum GameMode {
    SURVIVAL,
    CREATIVE,
    ADVENTURE,
    SPECTATOR,
    ALL
  }*/

  // public static GameMode dropGameMode = GameMode.ALL;

  // @Config.Comment("Settings for red full and half hearts")
  // @Config.LangKey("config.heartdrops.redHeartCategory")
  // public static @NotNull RedHeart redHeart = new RedHeart();

  // public static class RedHeart {
  //   public Boolean drop = true;
  // }

  // @Config.Comment("Settings for golden hearts")
  // @Config.LangKey("config.heartdrops.goldHeartCategory")
  // public static @NotNull GoldHeart goldHeart = new GoldHeart();

  // public static class GoldHeart {
  // public Boolean drop = true;

  // public Integer chance = 1;

  // public Integer lootingMultiplier = 5;
  // }

  // @Config.Comment("Settings for crystal hearts")
  // @Config.LangKey("config.heartdrops.crystalHeartCategory")
  // public static @NotNull CrystalHeart crystalHeart = new CrystalHeart();

  // public static class CrystalHeart {
  // @Config.Comment("Whether or not crystal hearts will drop")
  // @Config.LangKey("config.heartdrops.dropCrystalHearts")
  // public Boolean drop = true;

  // @Config.Comment("The chance for a crystal heart to drop")
  // @Config.LangKey("config.heartdrops.crystalHeartChance")
  // public Integer chance = 1;

  // @Config.Comment("A multiplier that increases your chance to get a crystal heart")
  // @Config.LangKey("config.heartdrops.crystalHeartLootingMultiplier")
  // public Integer lootingMultiplier = 3;
  // }
}
