package com.deflatedpickle.heartdrops.config

import net.minecraftforge.common.ForgeConfigSpec
import java.util.*


class ServerConfigBuild {


    fun ServerConfigBuild(builder: ForgeConfigSpec.Builder) {
        builder.push("general")

        builder.pop()
    }
}