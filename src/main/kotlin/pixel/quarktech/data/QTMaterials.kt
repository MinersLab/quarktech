package pixel.quarktech.data

import com.gregtechceu.gtceu.api.data.chemical.material.Material
import com.gregtechceu.gtceu.api.data.chemical.material.event.MaterialEvent
import com.gregtechceu.gtceu.api.data.chemical.material.stack.MaterialStack
import com.gregtechceu.gtceu.api.fluids.FluidBuilder
import com.gregtechceu.gtceu.common.data.GTMaterials
import pixel.quarktech.QuarkTechMod

object QTMaterials {

    lateinit var Pyrotheum: Material
    lateinit var Cryotheum: Material
    lateinit var Aerotheum: Material
    lateinit var Petrotheum: Material
    lateinit var Mana: Material

    @Suppress("UNUSED_PARAMETER")
    fun init(event: MaterialEvent) {
        Pyrotheum = Material.Builder(QuarkTechMod.id("pyrotheum"))
            .dust(1)
            .liquid(FluidBuilder().temperature(Int.MAX_VALUE).translation(QuarkTechMod.id("blazing_pyrotheum").toLanguageKey("liquid")))
            .color(0xF6D529)
            .componentStacks(
                MaterialStack(GTMaterials.Carbon, 1),
                MaterialStack(GTMaterials.Sulfur, 1),
                MaterialStack(GTMaterials.Redstone, 1),
                MaterialStack(GTMaterials.Blaze, 1)
            )
            .buildAndRegister()
        Cryotheum = Material.Builder(QuarkTechMod.id("cryotheum"))
            .dust(1)
            .liquid(FluidBuilder().temperature(1).translation(QuarkTechMod.id("gelid_cryotheum").toLanguageKey("liquid")))
            .color(0x45FCFF)
            .componentStacks(
                MaterialStack(GTMaterials.Saltpeter, 1),
                MaterialStack(GTMaterials.Ice, 1),
                MaterialStack(GTMaterials.Redstone, 1),
                MaterialStack(GTMaterials.CertusQuartz, 1)
            )
            .buildAndRegister()
        Aerotheum = Material.Builder(QuarkTechMod.id("aerotheum"))
            .dust(1)
            .liquid(FluidBuilder().translation(QuarkTechMod.id("zephyrean_aerotheum").toLanguageKey("liquid")))
            .color(0xCDE3A0)
            .componentStacks(
                MaterialStack(GTMaterials.Stone, 1),
                MaterialStack(GTMaterials.Saltpeter, 1),
                MaterialStack(GTMaterials.Redstone, 1),
                MaterialStack(GTMaterials.CertusQuartz, 1)
            )
            .buildAndRegister()
        Petrotheum = Material.Builder(QuarkTechMod.id("petrotheum"))
            .dust(1)
            .liquid(FluidBuilder().translation(QuarkTechMod.id("tectonic_petrotheum").toLanguageKey("liquid")))
            .color(0x635B60)
            .componentStacks(
                MaterialStack(GTMaterials.Clay, 1),
                MaterialStack(GTMaterials.Obsidian, 1),
                MaterialStack(GTMaterials.Redstone, 1),
                MaterialStack(GTMaterials.Stone, 1)
            )
            .buildAndRegister()
        Mana = Material.Builder(QuarkTechMod.id("mana"))
            .dust(1)
            .liquid(FluidBuilder().translation(QuarkTechMod.id("primal_mana").toLanguageKey("liquid")))
            .color(0x491973)
            .componentStacks(
                MaterialStack(Pyrotheum, 1),
                MaterialStack(Cryotheum, 1),
                MaterialStack(Aerotheum, 1),
                MaterialStack(Petrotheum, 1)
            )
            .buildAndRegister()
    }

}