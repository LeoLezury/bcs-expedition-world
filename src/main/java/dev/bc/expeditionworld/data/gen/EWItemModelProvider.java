package dev.bc.expeditionworld.data.gen;

import dev.bc.expeditionworld.ExpeditionWorld;
import dev.bc.expeditionworld.item.EWItems;
import net.minecraft.data.PackOutput;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class EWItemModelProvider extends ItemModelProvider {
    public EWItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, ExpeditionWorld.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        basicItem(EWItems.SCULK_MINT.get());
    }
}
