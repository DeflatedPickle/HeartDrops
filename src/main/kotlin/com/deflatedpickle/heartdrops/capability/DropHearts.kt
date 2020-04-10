/* Copyright (c) 2020 DeflatedPickle under the MIT license */

package com.deflatedpickle.heartdrops.capability

import com.deflatedpickle.heartdrops.Reference
import com.deflatedpickle.heartdrops.api.IDropHearts
import com.deflatedpickle.heartdrops.configs.GeneralConfig
import java.util.concurrent.Callable
import net.minecraft.entity.EntityLivingBase
import net.minecraft.nbt.NBTBase
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.util.EnumFacing
import net.minecraft.util.ResourceLocation
import net.minecraftforge.common.capabilities.Capability
import net.minecraftforge.common.capabilities.CapabilityInject
import net.minecraftforge.common.capabilities.CapabilityManager
import net.minecraftforge.common.capabilities.ICapabilitySerializable

object DropHearts {
    val NAME = ResourceLocation(Reference.MOD_ID, "drop_hearts")

    fun isCapable(entity: EntityLivingBase): IDropHearts? = entity.getCapability(Provider.CAPABILITY, null)

    class Implementation : IDropHearts {
        private var dropAmount = 1
        private var dropRange = GeneralConfig.dropRange
        private var dropHearts = true

        override fun getDropAmount(): Int = dropAmount
        override fun getDropRange(): Int = dropRange
        override fun doesDropHearts(): Boolean = dropHearts

        override fun setDropAmount(dropAmount: Int) {
            this.dropAmount = dropAmount
        }

        override fun setDropRange(dropRange: Int) {
            this.dropRange = dropRange
        }

        override fun setDropHearts(dropHearts: Boolean) {
            this.dropHearts = dropHearts
        }
    }

    class Storage : Capability.IStorage<IDropHearts> {
        override fun readNBT(capability: Capability<IDropHearts>?, instance: IDropHearts?, side: EnumFacing?, nbt: NBTBase?) {
            if (instance is Implementation) {
                with(nbt as NBTTagCompound) {
                    with(nbt.getIntArray("int")) {
                        instance.dropAmount = this[0]
                        instance.dropRange = this[1]
                    }
                    instance.setDropHearts(this.getBoolean("dropHearts"))
                }
            } else {
                throw IllegalArgumentException("Can not deserialize to an instance that isn't the default implementation")
            }
        }

        override fun writeNBT(capability: Capability<IDropHearts>?, instance: IDropHearts?, side: EnumFacing?): NBTBase? {
            if (instance != null) {
                return NBTTagCompound().apply {
                    this.setIntArray("int", intArrayOf(instance.dropAmount, instance.dropRange))
                    this.setBoolean("dropHearts", instance.doesDropHearts())
                }
            }
            return null
        }
    }

    class Factory : Callable<IDropHearts> {
        override fun call(): IDropHearts {
            return Implementation()
        }
    }

    class Provider : ICapabilitySerializable<NBTBase> {
        companion object {
            @JvmStatic
            @CapabilityInject(IDropHearts::class)
            lateinit var CAPABILITY: Capability<IDropHearts>
        }

        val INSTANCE = CAPABILITY.defaultInstance

        override fun hasCapability(capability: Capability<*>, facing: EnumFacing?): Boolean = capability == CAPABILITY
        override fun <T : Any> getCapability(capability: Capability<T>, facing: EnumFacing?): T? = if (capability == CAPABILITY) CAPABILITY.cast(this.INSTANCE) else null

        override fun serializeNBT(): NBTBase = CAPABILITY.storage.writeNBT(CAPABILITY, this.INSTANCE, null)!!
        override fun deserializeNBT(nbt: NBTBase) = CAPABILITY.storage.readNBT(CAPABILITY, this.INSTANCE, null, nbt)
    }

    fun register() {
        CapabilityManager.INSTANCE.register(IDropHearts::class.java, Storage(), Factory())
    }
}
