package dev.bc.expeditionworld.data.gen;

import dev.bc.expeditionworld.ExpeditionWorld;
import dev.bc.expeditionworld.item.EWItems;
import dev.bc.expeditionworld.item.IceTotemItem;
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
		super(output, ExpeditionWorld.ID, existingFileHelper);
	}

	@Override
	protected void registerModels() {
		basicItem(EWItems.SCULK_MINT.get());

		basicItem(EWItems.TRAPPED_SOUL.get());
		handheld(EWItems.STONE_MIMICHEST_KNIFE.get());
		handheld(EWItems.BRICK_MIMICHEST_KNIFE.get());
		block(EWItems.FETTERED_CHEST.get());
		block(EWItems.FETTERED_POT.get());
		flatBlock(EWItems.MOSSFLORA.get());

		block(EWItems.FROZEN_STONE.get());
		block(EWItems.FROZEN_STONE_SLAB.get());
		block(EWItems.FROZEN_STONE_STAIRS.get());
		wall(EWItems.FROZEN_STONE_WALL.get(), EWItems.FROZEN_STONE.get());
		block(EWItems.ICE_CRYSTAL_ORE.get());
		block(EWItems.ICE_CRYSTAL_BLOCK.get());
		flatBlock(EWItems.ICE_CRYSTAL_TORCH.get());
		block(EWItems.FROSTBITE_TNT.get());
		block(EWItems.FROZEN_DIRT.get());
		block(EWItems.FROZEN_GRASS_BLOCK.get());
		block(EWItems.ICE_LANTERN.get());
		flatBlock(EWItems.POINTED_ICE.get());
		flatBlock(EWItems.FROZEN_GRASS.get());
		flatBlock(EWItems.ICE_FLOWER.get());
		basicItem(EWItems.FRIGID_GLADIOLUS.get(), blockTextureFromItem(EWItems.FRIGID_GLADIOLUS.get()).withSuffix("_top"));

		basicItem(EWItems.ICE_CRYSTAL.get());
		basicItem(EWItems.FROSTBITE_GUNPOWDER.get());
		basicItem(EWItems.SHARP_ICICLE.get());
		basicItem(EWItems.FROST_CHARGE.get());
		basicItem(EWItems.FROZEN_ARROW.get());

		basicItem(EWItems.FRIGID_BEAK.get());
		basicItem(EWItems.FROSTY_MOA_LOOT_BAG.get());
		basicItem(EWItems.MOA_FEATHER.get());
		basicItem(EWItems.MOA_SKULL.get());
		basicItem(EWItems.FROSTY_MOA_EGG.get());
		basicItem(EWItems.MOA_FEATHER_ARROW.get());
		iceTotem(EWItems.TOTEM_OF_ICE.get());

		basicItem(EWItems.COLDPROOF_HAT.get());
		basicItem(EWItems.COLDPROOF_COAT.get());
		basicItem(EWItems.COLDPROOF_LEGGINGS.get());
		basicItem(EWItems.COLDPROOF_BOOTS.get());

		basicItem(EWItems.CRYO_SMITHING_TEMPLATE.get());

		basicItem(EWItems.GLACIER_HELMET.get());
		basicItem(EWItems.GLACIER_CHESTPLATE.get());
		basicItem(EWItems.GLACIER_LEGGINGS.get());
		basicItem(EWItems.GLACIER_BOOTS.get());

		handheld(EWItems.FROSTY_DAGGER.get());
		handheld(EWItems.GLACIER_DAGGER.get());

		bow(EWItems.GLACIER_BOW.get());
	}

	private void iceTotem(Item item) {
		ModelFile brokenModel = withExistingParent(name(item) + "_broken", "item/generated")
			.texture("layer0", itemTexture(item).withSuffix("_broken"));
		basicItem(item).override().predicate(IceTotemItem.BROKEN, 1).model(brokenModel).end();
	}

	private void bow(Item item) {
		ModelFile pull0 = withExistingParent(name(item) + "_pulling_0", "item/bow")
			.texture("layer0", itemTexture(item).withSuffix("_pulling_0"));
		ModelFile pull1 = withExistingParent(name(item) + "_pulling_1", "item/bow")
			.texture("layer0", itemTexture(item).withSuffix("_pulling_1"));
		ModelFile pull2 = withExistingParent(name(item) + "_pulling_2", "item/bow")
			.texture("layer0", itemTexture(item).withSuffix("_pulling_2"));
		withExistingParent(name(item), "item/bow")
			.texture("layer0", itemTexture(item))
			.override().predicate(new ResourceLocation("pulling"), 1).model(pull0).end()
			.override().predicate(new ResourceLocation("pulling"), 1).predicate(new ResourceLocation("pull"), (float) 0.65).model(pull1).end()
			.override().predicate(new ResourceLocation("pulling"), 1).predicate(new ResourceLocation("pull"), (float) 0.9).model(pull2).end();
	}

	private void block(Item item) {
		withExistingParent(name(item), modLoc(ModelProvider.BLOCK_FOLDER + "/" + name(item)));
	}

	private void flatBlock(Item item) {
		basicItem(item, blockTextureFromItem(item));
	}

	private void wall(Item wall, Item stone) {
		getBuilder(name(wall))
			.parent(getExistingFile(mcLoc("block/wall_inventory")))
			.texture("wall", blockTextureFromItem(stone));
	}

	private ItemModelBuilder handheld(Item item) {
		return handheld(item, itemTexture(item));
	}

	private ItemModelBuilder handheld(Item item, ResourceLocation texture) {
		return getBuilder(item.toString())
			.parent(new ModelFile.UncheckedModelFile("item/handheld"))
			.texture("layer0", texture);
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
