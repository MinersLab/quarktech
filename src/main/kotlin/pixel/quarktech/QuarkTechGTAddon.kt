package pixel.quarktech

import com.gregtechceu.gtceu.api.addon.GTAddon
import com.gregtechceu.gtceu.api.addon.IGTAddon
import com.gregtechceu.gtceu.api.registry.registrate.GTRegistrate
import net.minecraft.data.recipes.FinishedRecipe
import pixel.quarktech.data.recipe.QTRecipes
import java.util.function.Consumer


@GTAddon
class QuarkTechGTAddon : IGTAddon {

    companion object {
        val REGISTRATE = GTRegistrate.create(QuarkTechMod.ID)
    }

    override fun addonModId() = QuarkTechMod.ID
    override fun getRegistrate() = REGISTRATE

    override fun initializeAddon() {}

    override fun addRecipes(provider: Consumer<FinishedRecipe>) {
        QTRecipes.init(provider)
    }

}