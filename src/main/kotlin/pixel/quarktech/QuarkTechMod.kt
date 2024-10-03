package pixel.quarktech

import net.minecraft.resources.ResourceLocation
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent
import net.minecraftforge.fml.event.lifecycle.FMLDedicatedServerSetupEvent
import org.apache.logging.log4j.Level
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import thedarkcolour.kotlinforforge.forge.MOD_BUS
import thedarkcolour.kotlinforforge.forge.runForDist

@Mod(QuarkTechMod.ID)
object QuarkTechMod {

    const val ID = "quarktech"

    val LOGGER: Logger = LogManager.getLogger(ID)

    fun id(path: String) = ResourceLocation(ID, path)

    init {
        runForDist(
            clientTarget = {
                MOD_BUS.addListener(::onClientSetup)
            },
            serverTarget = {
                MOD_BUS.addListener(::onServerSetup)
            }
        )
        CommonProxy.init()
    }

    @Suppress("UNUSED_PARAMETER")
    private fun onClientSetup(event: FMLClientSetupEvent) {
        LOGGER.log(Level.INFO, "Initializing client...")
    }

    @Suppress("UNUSED_PARAMETER")
    private fun onServerSetup(event: FMLDedicatedServerSetupEvent) {
        LOGGER.log(Level.INFO, "Server starting...")
    }

}