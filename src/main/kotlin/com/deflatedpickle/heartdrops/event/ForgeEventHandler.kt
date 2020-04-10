/* Copyright (c) 2020 DeflatedPickle under the MIT license */

package com.deflatedpickle.heartdrops.event

import com.deflatedpickle.heartdrops.HeartDrops
import com.deflatedpickle.heartdrops.Reference
import com.deflatedpickle.heartdrops.api.HeartType
import com.deflatedpickle.heartdrops.capability.DropHearts
import com.deflatedpickle.heartdrops.configs.GeneralConfig
import com.deflatedpickle.heartdrops.init.Item
import com.deflatedpickle.heartdrops.item.CrystalHeart
import com.deflatedpickle.heartdrops.item.GoldenHeart
import com.deflatedpickle.heartdrops.item.Heart
import kotlin.math.floor
import net.minecraft.client.renderer.block.model.ModelResourceLocation
import net.minecraft.enchantment.Enchantment
import net.minecraft.enchantment.EnchantmentHelper
import net.minecraft.entity.Entity
import net.minecraft.entity.EntityLiving
import net.minecraft.entity.EntityLivingBase
import net.minecraft.entity.item.EntityItem
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.entity.player.EntityPlayerMP
import net.minecraft.entity.projectile.EntityArrow
import net.minecraft.item.ItemStack
import net.minecraft.potion.PotionEffect
import net.minecraft.potion.PotionUtils
import net.minecraftforge.client.event.ModelRegistryEvent
import net.minecraftforge.client.model.ModelLoader
import net.minecraftforge.event.AttachCapabilitiesEvent
import net.minecraftforge.event.RegistryEvent
import net.minecraftforge.event.entity.item.ItemTossEvent
import net.minecraftforge.event.entity.living.LivingDropsEvent
import net.minecraftforge.event.entity.player.EntityItemPickupEvent
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import net.minecraftforge.fml.common.registry.ForgeRegistries

@Mod.EventBusSubscriber(modid = Reference.MOD_ID)
object ForgeEventHandler {
    @SubscribeEvent
    fun onRegisterItem(event: RegistryEvent.Register<net.minecraft.item.Item>) {
        event.registry.registerAll(
                Item.heart,
                Item.half_heart,
                Item.golden_heart,
                Item.crystal_heart
        )
    }

    @SubscribeEvent
    fun onRegisterModel(event: ModelRegistryEvent) {
        for (item in listOf(
                Item.heart,
                Item.half_heart,
                Item.golden_heart,
                Item.crystal_heart
        )) {
            ModelLoader.setCustomModelResourceLocation(
                    item, 0,
                    ModelResourceLocation(item.registryName, "inventory")
            )
        }
    }

    @SubscribeEvent
    fun onEntityItemPickupEvent(event: EntityItemPickupEvent) {
        if (!event.entity.world.isRemote) {
            val itemStack = event.item.item
            val item = itemStack.item

            if (item is Heart) {
                collectHearts(event, itemStack)
            }
        }
    }

    @SubscribeEvent
    fun onItemTossEvent(event: ItemTossEvent) {
        if (event.entityItem.item.item is CrystalHeart) {
            // How helpful it is to tell us what's bad...
            // Otherwise, you'd be stuck with good and bad effects
            // I'm not writing more code to filter them out :^)
            ForgeRegistries.POTIONS.valuesCollection.filter { !it.isInstant && !it.isBadEffect }.toList().apply {
                PotionUtils.appendEffects(event.entityItem.item, mutableListOf(PotionEffect(
                        this[HeartDrops.random.nextInt(this.size)],
                        // It should last for *enough* time to get use out of it
                        // TODO: Make crystal heart effects further customisable
                        HeartDrops.random.nextInt(20 * 20, 20 * 30)
                )))
            }
        }
    }

    @SubscribeEvent
    fun onLivingDropEvent(event: LivingDropsEvent) {
        var spawnItems = false
        val lootingLevel: Int

        if (GeneralConfig.dropDifficulty != GeneralConfig.Difficulty.ALL &&
                event.entity.world.difficulty.ordinal != GeneralConfig.dropDifficulty.ordinal) return
        if (GeneralConfig.dropGameMode != GeneralConfig.GameMode.ALL &&
                event.source.trueSource is EntityPlayer && ((event.source.trueSource as EntityPlayerMP).interactionManager.gameType.ordinal + 1) == GeneralConfig.dropGameMode.ordinal)
        // They've chosen to install the mod... but never want hearts to drop
        // Totally defeats the purpose of having it, but whatever
            if (GeneralConfig.dropWhen == GeneralConfig.When.NEVER) return
        if (!GeneralConfig.dropHardcore && event.entity.world.minecraftServer!!.isHardcore) return

        if (!event.entity.world.isRemote) {
            val source = event.source.trueSource

            when (GeneralConfig.dropWhen) {
                GeneralConfig.When.HURT -> {
                    if (source is EntityLivingBase && source.health < source.maxHealth) {
                        spawnItems = true
                    } else if (source is EntityArrow) {
                        val entity = source.shootingEntity
                        if (entity is EntityLiving) {
                            if (entity.health < entity.maxHealth) {
                                spawnItems = true
                            }
                        }
                    }
                }
                GeneralConfig.When.ALWAYS -> spawnItems = true
                GeneralConfig.When.NEVER -> return
            }

            if (!spawnItems) return

            lootingLevel = EnchantmentHelper.getEnchantmentLevel(Enchantment.getEnchantmentByID(21)!!, event.entityLiving.heldItemMainhand)

            var dropAmount = 0

            val entity = event.entityLiving
            val dropHearts = DropHearts.isCapable(entity)
            if (dropHearts != null) {
                if (dropHearts.doesDropHearts()) {
                    dropAmount = dropHearts.dropAmount
                }
            }

            val heartList = mutableListOf<EntityItem>()

            var amount = 0

            // Deals with red hearts
            when (GeneralConfig.dropWhen) {
                GeneralConfig.When.HURT -> {
                    amount = when (GeneralConfig.dropAmount) {
                        // User specified amount
                        GeneralConfig.DropAmount.SPECIFIC ->
                            GeneralConfig.dropAmountValue
                        // Random chance using a user specified max amount
                        GeneralConfig.DropAmount.CHANCE ->
                            HeartDrops.random.nextInt(GeneralConfig.dropAmountValue)
                        // Uses a user input for a percentage of the mob health
                        GeneralConfig.DropAmount.PERCENTAGE_OF_MOB_HEALTH ->
                            (GeneralConfig.dropAmountValue / 100f * event.entityLiving.maxHealth).toInt()
                        // Drops enough full hearts to fill to max - 1
                        GeneralConfig.DropAmount.UNTIL_FULL_HEALTH ->
                            floor(((event.source.trueSource as EntityLivingBase).maxHealth - (event.source.trueSource as EntityLivingBase).health)).toInt()
                    }
                }
                // The user doesn't want any of those funky "only hurt" values
                // Give 'em hearts all the time
                GeneralConfig.When.ALWAYS ->
                    HeartType.NORMAL.drop(event, 1, heartList)
                // They don't want hearts at all! Can you believe that?! ;~;
                GeneralConfig.When.NEVER -> {
                }
            }

            // Only after all those calculations, shall we cancel it if they don't want them
            // TODO: Sort this out
            if (!GeneralConfig.redHeart.drop) {
                amount = 0
                heartList.clear()
            }

            var heartCount = 0
            // Takes the drop amount away from floor hearts
            if (GeneralConfig.deriveFromDropped) {
                val entityList = event.entity.world.getEntitiesInAABBexcluding(event.source.trueSource, (event.source.trueSource as EntityLivingBase).entityBoundingBox.grow(10.0)) { it is EntityItem }

                for (entity in entityList) {
                    when (val item = (entity as EntityItem).item.item) {
                        is Heart -> {
                            heartCount += item.type.healsBy
                        }
                    }
                }
            }

            // Caps the amount at the health
            if (GeneralConfig.capAtHealth) {
                amount -= heartCount
            }

            var cache = 0
            if (event.source.trueSource != null) {
                // Drops all the hearts, stepping by 2
                for (i in 1..amount step 2) {
                    HeartType.NORMAL.drop(event,
                            1,
                            heartList)
                    cache = i
                }
            }

            // Drops enough half-hearts to fill
            if (GeneralConfig.dropHalf) {
                // Uses dropped hearts
                if (GeneralConfig.deriveFromDropped) {
                    HeartType.HALF.drop(event,
                            cache,
                            heartList)
                } else {
                    // Drops one if you only need half to fill
                    if ((event.source.trueSource as EntityLivingBase).health + 1 == (event.source.trueSource as EntityLivingBase).maxHealth) {
                        HeartType.HALF.drop(event,
                                1,
                                heartList)
                    }
                }
            }

            // Deals with gold and crystal hearts
            for (i in 0..dropAmount * (lootingLevel + 1)) {
                if (GeneralConfig.goldHeart.drop) {
                    val bound = (GeneralConfig.goldHeart.chance + GeneralConfig.goldHeart.lootingMultiplier - ((lootingLevel + 1) * GeneralConfig.goldHeart.lootingMultiplier)) / (lootingLevel + 1)
                    if (HeartDrops.random.nextInt(0, bound) == 0) {
                        HeartType.GOLD.drop(event, 1, heartList)
                    }
                }

                if (GeneralConfig.crystalHeart.drop) {
                    val bound = (GeneralConfig.crystalHeart.chance + GeneralConfig.crystalHeart.lootingMultiplier - ((lootingLevel + 1) * GeneralConfig.crystalHeart.lootingMultiplier)) / (lootingLevel + 1)
                    if (HeartDrops.random.nextInt(0, bound) == 0) {
                        HeartType.CRYSTAL.drop(event, 1, heartList)
                    }
                }
            }

            for (i in heartList) {
                entity.world.spawnEntity(i)
            }
        }
    }

    fun collectHearts(event: EntityItemPickupEvent, itemStack: ItemStack) {
        val item = itemStack.item

        if (item is Heart) {
            for (i in 0..itemStack.count) {
                item.type.collect(event)
            }
            itemStack.count = 0
        }
    }

    @SubscribeEvent
    fun onAttachCapabilitiesEventEntity(event: AttachCapabilitiesEvent<Entity>) {
        if (event.`object` !is EntityPlayer) {
            event.addCapability(DropHearts.NAME, DropHearts.Provider())
        }
    }
}
