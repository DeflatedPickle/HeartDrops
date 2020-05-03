/* Copyright (c) 2020 DeflatedPickle under the MIT license */

package com.deflatedpickle.heartdrops.config

import com.deflatedpickle.heartdrops.util.Difficulty
import com.deflatedpickle.heartdrops.util.DropAmount
import com.deflatedpickle.heartdrops.util.GameMode
import com.deflatedpickle.heartdrops.util.When
import net.minecraftforge.common.ForgeConfigSpec

object Config {
    var spec: ForgeConfigSpec

    val builder = ForgeConfigSpec.Builder()

    var dropRange: ForgeConfigSpec.IntValue
    var dropAmount: ForgeConfigSpec.EnumValue<DropAmount>
    var dropAmountValue: ForgeConfigSpec.IntValue
    var deriveFromDropped: ForgeConfigSpec.BooleanValue
    var dropHalf: ForgeConfigSpec.BooleanValue
    var capAtHealth: ForgeConfigSpec.BooleanValue
    var dropWhen: ForgeConfigSpec.EnumValue<When>
    var dropHardcore: ForgeConfigSpec.BooleanValue
    var dropDifficulty: ForgeConfigSpec.EnumValue<Difficulty>
    var dropGameMode: ForgeConfigSpec.EnumValue<GameMode>

    var redHeartDrop: ForgeConfigSpec.BooleanValue

    var goldHeartDrop: ForgeConfigSpec.BooleanValue
    var goldHeartChance: ForgeConfigSpec.IntValue
    var goldHeartLootingMultiplier: ForgeConfigSpec.IntValue

    var crystalHeartDrop: ForgeConfigSpec.BooleanValue
    var crystalHeartChance: ForgeConfigSpec.IntValue
    var crystalHeartLootingMultiplier: ForgeConfigSpec.IntValue

    init {
        builder.comment("General Settings").push("general")

        // Drop range
        dropRange = builder
                .comment("The range of hearts that can drop (can be increased with looting)")
                .translation("config.heartdrops.dropRange")
                .defineInRange("dropRange", 1, 1, 40)

        // dropAmount
        dropAmount = builder
                .comment("Changes how many hearts will be dropped")
                .translation("config.heartdrops.dropAmount")
                .defineEnum("dropAmount", DropAmount.UNTIL_FULL_HEALTH)

        // dropAmount
        dropAmountValue = builder
                .comment(
                        "The value used for the drop amount",
                        "SPECIFIC = A specific value to drop",
                        "CHANCE = The upper bound of a random chance",
                        "UNTIL_FULL_HEALTH = Nothing",
                        "PERCENTAGE_OF_MOB_HEALTH = The percentage of the health"
                )
                .translation("config.heartdrops.dromAmountValue")
                .defineInRange("dropAmount", 20, 1, 100)

        // deriveFromDropped
        deriveFromDropped = builder
                .comment("Stops dropping hearts if there are enough on the ground to heal you")
                .translation("config.heartdrops.deriveFromDropped")
                .define("deriveFromDropped", true)

        // dropHalf
        dropHalf = builder
                .comment("Drops a half heart if you need it")
                .translation("config.heartdrops.dropHalf")
                .define("dropHalf", true)

        // capAtHealth
        capAtHealth = builder
                .comment("Caps the health dropped at your health")
                .translation("config.heartdrops.capAtHealth")
                .define("capAtHealth", true)

        // dropWhen
        dropWhen = builder
                .comment("Changes when the hearts will drop")
                .translation("config.heartdrops.dropWhen")
                .defineEnum("dropWhen", When.HURT)

        // dropHardcore
        dropHardcore = builder
                .comment("Changes whether hearts drop on hardcore mode or not")
                .translation("config.heartdrops.dropHardcore")
                .define("dropHardcore", true)

        // dropDifficulty
        dropDifficulty = builder
                .comment("Changes what difficulty hearts drop on")
                .translation("config.heartdrops.dropDifficulty")
                .defineEnum("dropDifficulty", Difficulty.ALL)

        // dropGameMode
        dropGameMode = builder
                .comment("Changes the game mode hearts drop on")
                .translation("config.heartdrops.dropGamemode")
                .defineEnum("dropGamemode", GameMode.ALL)

        builder.pop()

        // redHeart
        builder.comment("Red Hearts").push("redHeartCategory")

        // redHeartDrop
        redHeartDrop = builder
                .comment("Whether red full and half hearts will drop")
                .translation("config.heartdrops.dropRedHearts")
                .define("redHeartDrop", true)

        builder.pop()

        // goldHeart
        builder.comment("Gold Hearts").push("goldHeartCategory")

        // goldHeartDrop
        goldHeartDrop = builder
                .comment("Whether or not golden hearts will drop")
                .translation("config.heartdrops.dropGoldHearts")
                .define("goldHeartDrop", true)

        // goldHeartChance
        goldHeartChance = builder
                .comment("The chance for a golden heart to drop")
                .translation("config.heartdrops.goldHeartChance")
                .defineInRange("goldHeartChance", 50, 1, Int.MAX_VALUE)

        // goldHeartLootingMultiplier
        goldHeartLootingMultiplier = builder
                .comment("A multiplier that increases your chance to get a golden heart")
                .translation("config.heartdrops.goldHeartLootingMultiplier")
                .defineInRange("goldHeartLootingMultiplier", 5, 1, Int.MAX_VALUE)

        builder.pop()

        // crystalHeart
        builder.comment("Gold Hearts").push("crystalHeartCategory")

        // crystalHeartDrop
        crystalHeartDrop = builder
                .comment("Whether or not crystalen hearts will drop")
                .translation("config.heartdrops.dropGoldHearts")
                .define("crystalHeartDrop", true)

        // crystalHeartChance
        crystalHeartChance = builder
                .comment("The chance for a crystalen heart to drop")
                .translation("config.heartdrops.crystalHeartChance")
                .defineInRange("crystalHeartChance", 50, 1, Int.MAX_VALUE)

        // crystalHeartLootingMultiplier
        crystalHeartLootingMultiplier = builder
                .comment("A multiplier that increases your chance to get a crystalen heart")
                .translation("config.heartdrops.crystalHeartLootingMultiplier")
                .defineInRange("crystalHeartLootingMultiplier", 5, 1, Int.MAX_VALUE)

        builder.pop()

        spec = builder.build()
    }
}
