package dev.bc.expeditionworld.data.gen;

import dev.bc.expeditionworld.ExpeditionWorld;
import dev.bc.expeditionworld.item.EWItems;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
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
        basicItemWithBlockTexture(EWItems.MOSSFLORA.get());

        basicBlockItem(EWItems.FROZEN_STONE.get());
        basicBlockItem(EWItems.FROZEN_DIRT.get());
        basicBlockItem(EWItems.FROZEN_GRASS_BLOCK.get());
        basicBlockItem(EWItems.ICE_LANTERN.get());
        basicItemWithBlockTexture(EWItems.POINTED_ICE.get());
        basicItemWithBlockTexture(EWItems.FROZEN_GRASS.get());
        basicItemWithBlockTexture(EWItems.ICE_FLOWER.get());
        basicItem(EWItems.FRIGID_GLADIOLUS.get(), blockTextureFromItem(EWItems.FRIGID_GLADIOLUS.get()).withSuffix("_top"));
    }

    private void basicBlockItem(Item item) {
        withExistingParent(name(item), modLoc(ModelProvider.BLOCK_FOLDER + "/" + name(item)));
    }

    private void basicItemWithBlockTexture(Item item) {
        basicItem(item, blockTextureFromItem(item));
    }

    private ItemModelBuilder basicItem(Item item, ResourceLocation texture) {
        return getBuilder(item.toString())
                .parent(new ModelFile.UncheckedModelFile("item/generated"))
                .texture("layer0", texture);
    }

    public ResourceLocation blockTextureFromItem(Item item) {
        ResourceLocation name = key(item);
        return texture(name, ModelProvider.BLOCK_FOLDER);
    }

    public ResourceLocation itemTexture(Item item) {
        ResourceLocation name = key(item);
        return texture(name, ModelProvider.ITEM_FOLDER);
    }

    public ResourceLocation texture(ResourceLocation key, String prefix) {
        return new ResourceLocation(key.getNamespace(), prefix + "/" + key.getPath());
    }

    private ResourceLocation key(Item item) {
        return BuiltInRegistries.ITEM.getKey(item);
    }

    private String name(Item item) {
        return key(item).getPath();
    }
}
