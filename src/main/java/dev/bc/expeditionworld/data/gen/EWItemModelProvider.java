package dev.bc.expeditionworld.data.gen;

import dev.bc.expeditionworld.ExpeditionWorld;
import dev.bc.expeditionworld.item.EWItems;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class EWItemModelProvider extends ItemModelProvider {
    public EWItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, ExpeditionWorld.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        basicItem(EWItems.SCULK_MINT.get());
        basicItem(EWItems.TRAPPED_SOUL.get());
        basicItem(EWItems.STONE_MIMICHEST_KNIFE.get());
        basicItem(EWItems.BRICK_MIMICHEST_KNIFE.get());
        basicBlockItem(EWItems.FETTERED_CHEST.get());
        basicBlockItem(EWItems.FETTERED_POT.get());
    }

    private void basicBlockItem(Item item) {
        withExistingParent(name(item), modLoc(ModelProvider.BLOCK_FOLDER + "/" + name(item)));
    }

    private ResourceLocation key(Item item) {
        return BuiltInRegistries.ITEM.getKey(item);
    }

    private String name(Item item) {
        return key(item).getPath();
    }
}
