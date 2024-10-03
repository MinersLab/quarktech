package pixel.quarktech.data.recipe

import com.gregtechceu.gtceu.api.GTCEuAPI
import com.gregtechceu.gtceu.api.GTValues
import com.gregtechceu.gtceu.api.data.chemical.ChemicalHelper
import com.gregtechceu.gtceu.api.data.tag.TagPrefix
import com.gregtechceu.gtceu.common.data.GTRecipeTypes
import com.gregtechceu.gtceu.data.recipe.VanillaRecipeHelper
import net.minecraft.data.recipes.FinishedRecipe
import pixel.quarktech.QuarkTechMod
import pixel.quarktech.data.QTMaterials
import java.util.function.Consumer

object QTRecipes {

    fun init(provider: Consumer<FinishedRecipe>) {
        initMixerRecipes(provider)
        initFuelRecipes(provider)
        initShapelessRecipes(provider)
        initVacuumFreezerRecipes(provider)
        initBlastRecipes(provider)
    }

    fun initBlastRecipes(provider: Consumer<FinishedRecipe>) {
        GTRecipeTypes.BLAST_RECIPES.recipeBuilder(QuarkTechMod.id("dust/pyrotheum"))
            .duration(20 * 5)
            .EUt(GTValues.V[GTValues.EV])
            .outputItems(TagPrefix.dust, QTMaterials.Pyrotheum)
            .inputItems(TagPrefix.dust, QTMaterials.Cryotheum)
            .save(provider)
        GTRecipeTypes.BLAST_RECIPES.recipeBuilder(QuarkTechMod.id("fluid/pyrotheum"))
            .duration(20 * 5)
            .EUt(GTValues.V[GTValues.EV])
            .outputFluids(QTMaterials.Pyrotheum.getFluid(1000))
            .inputFluids(QTMaterials.Cryotheum.getFluid(1000))
            .save(provider)
    }

    fun initVacuumFreezerRecipes(provider: Consumer<FinishedRecipe>) {
        GTRecipeTypes.VACUUM_RECIPES.recipeBuilder(QuarkTechMod.id("dust/cryotheum"))
            .duration(20 * 5)
            .EUt(GTValues.V[GTValues.EV])
            .inputItems(TagPrefix.dust, QTMaterials.Pyrotheum)
            .outputItems(TagPrefix.dust, QTMaterials.Cryotheum)
            .save(provider)
        GTRecipeTypes.VACUUM_RECIPES.recipeBuilder(QuarkTechMod.id("fluid/cryotheum"))
            .duration(20 * 5)
            .EUt(GTValues.V[GTValues.EV])
            .inputFluids(QTMaterials.Pyrotheum.getFluid(1000))
            .outputFluids(QTMaterials.Cryotheum.getFluid(1000))
            .save(provider)
    }

    fun initMixerRecipes(provider: Consumer<FinishedRecipe>) {
        for (material in arrayOf(QTMaterials.Pyrotheum, QTMaterials.Petrotheum, QTMaterials.Cryotheum, QTMaterials.Mana, QTMaterials.Aerotheum)) {
            GTRecipeTypes.MIXER_RECIPES.recipeBuilder(QuarkTechMod.id("dust/${material.name}"))
                .apply {
                    for (component in material.materialComponents) {
                        inputItems(TagPrefix.dust, component.material, component.amount.toInt())
                    }
                }
                .outputItems(
                    TagPrefix.dust,
                    material,
                    material.materialComponents.sumOf { it.amount }.toInt()
                )
                .EUt(GTValues.V[GTValues.HV])
                .duration(20)
                .save(provider)
        }
    }

    fun initFuelRecipes(provider: Consumer<FinishedRecipe>) {
        GTRecipeTypes.STEAM_BOILER_RECIPES.recipeBuilder(QuarkTechMod.id("fuel/blazing_pyrotheum"))
            .inputFluids(QTMaterials.Pyrotheum.getFluid(1))
            .duration(20 * 10)
            .save(provider)
        GTRecipeTypes.LARGE_BOILER_RECIPES.recipeBuilder(QuarkTechMod.id("fuel/blazing_pyrotheum"))
            .inputFluids(QTMaterials.Pyrotheum.getFluid(1))
            .duration(20 * 20)
            .save(provider)
        GTRecipeTypes.COMBUSTION_GENERATOR_FUELS.recipeBuilder(QuarkTechMod.id("fuel/blazing_pyrotheum"))
            .inputFluids(QTMaterials.Pyrotheum.getFluid(1))
            .duration(20 * 20)
            .EUt(-GTValues.V[GTValues.UV])
            .save(provider)
    }

    fun initShapelessRecipes(provider: Consumer<FinishedRecipe>) {
        with(ChemicalHelper.get(TagPrefix.dust, QTMaterials.Pyrotheum)) {
            for (material in GTCEuAPI.materialManager.registeredMaterials) {
                val dust = ChemicalHelper.get(TagPrefix.dust, material)
                val ingot = ChemicalHelper.get(TagPrefix.ingot, material)
                val ingotHot = ChemicalHelper.get(TagPrefix.ingotHot, material)
                if (ingot.isEmpty) continue
                if (!dust.isEmpty) VanillaRecipeHelper.addShapelessRecipe(provider, QuarkTechMod.id("pyrotheum/dust/${material.modid}/${material.name}"), ingot, this, dust)
                if (!ingotHot.isEmpty)  VanillaRecipeHelper.addShapelessRecipe(provider, QuarkTechMod.id("pyrotheum/ingot_hot/${material.modid}/${material.name}"), ingotHot, this, ingot)
            }
        }
        with(ChemicalHelper.get(TagPrefix.dust, QTMaterials.Petrotheum)) {
            for (material in GTCEuAPI.materialManager.registeredMaterials) {
                val dust = ChemicalHelper.get(TagPrefix.dust, material)
                val ingot = ChemicalHelper.get(TagPrefix.ingot, material)
                val ore = ChemicalHelper.get(TagPrefix.ore, material)
                if (dust.isEmpty) continue
                if (!ore.isEmpty) VanillaRecipeHelper.addShapelessRecipe(provider, QuarkTechMod.id("petrotheum/ore/${material.modid}/${material.name}"), dust.copyWithCount(2), this, ore)
                if (!ingot.isEmpty) VanillaRecipeHelper.addShapelessRecipe(provider, QuarkTechMod.id("petrotheum/ingot/${material.modid}/${material.name}"), dust.copyWithCount(1), this, ingot)
            }
        }
        with(ChemicalHelper.get(TagPrefix.dust, QTMaterials.Cryotheum)) {
            for (material in GTCEuAPI.materialManager.registeredMaterials) {
                val ingotHot = ChemicalHelper.get(TagPrefix.ingotHot, material)
                val ingot = ChemicalHelper.get(TagPrefix.ingot, material)
                if (ingotHot.isEmpty || ingot.isEmpty) continue
                VanillaRecipeHelper.addShapelessRecipe(provider, QuarkTechMod.id("cryotheum/${material.modid}/${material.name}"), ingot, this, ingotHot)
            }
        }
    }

}