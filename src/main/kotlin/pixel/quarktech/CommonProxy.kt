package pixel.quarktech

import com.gregtechceu.gtceu.api.GTCEuAPI
import com.gregtechceu.gtceu.api.data.chemical.material.event.MaterialRegistryEvent
import pixel.quarktech.data.QTMaterials
import thedarkcolour.kotlinforforge.KotlinModLoadingContext


object CommonProxy {

    fun init() {
        val eventBus = KotlinModLoadingContext.get().getKEventBus()
        eventBus.addListener(QTMaterials::init)
        eventBus.addListener(::addMaterialRegistries)
    }

    @Suppress("UNUSED_PARAMETER")
    private fun addMaterialRegistries(event: MaterialRegistryEvent) {
        GTCEuAPI.materialManager.createRegistry(QuarkTechMod.ID)
    }

}