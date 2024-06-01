package dev.bc.expeditionworld.item;

import dev.bc.expeditionworld.ExpeditionWorld;
import dev.bc.expeditionworld.block.EWBlocks;
import net.minecraft.core.Direction;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.StandingAndWallBlockItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class EWItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, ExpeditionWorld.ID);

    // sculk mint
    public static final RegistryObject<Item> SCULK_MINT = ITEMS.register("sculk_mint", () -> new Item(new Item.Properties()));

    // mimichest
    public static final RegistryObject<Item> TRAPPED_SOUL = ITEMS.register("trapped_soul", () -> new Item(new Item.Properties()));
    public static final RegistryObject<MimichestKnifeItem> STONE_MIMICHEST_KNIFE = ITEMS.register("stone_mimichest_knife", () -> new MimichestKnifeItem(new Item.Properties().stacksTo(4)));
    public static final RegistryObject<MimichestKnifeItem> BRICK_MIMICHEST_KNIFE = ITEMS.register("brick_mimichest_knife", () -> new MimichestKnifeItem(new Item.Properties().stacksTo(4)));
    public static final RegistryObject<BlockItem> FETTERED_CHEST = ITEMS.register("fettered_chest", () -> new BlockItem(EWBlocks.FETTERED_CHEST.get(), new Item.Properties()));
    public static final RegistryObject<BlockItem> FETTERED_POT = ITEMS.register("fettered_pot", () -> new BlockItem(EWBlocks.FETTERED_POT.get(), new Item.Properties()));
    public static final RegistryObject<BlockItem> MOSSFLORA = ITEMS.register("mossflora", () -> new BlockItem(EWBlocks.MOSSFLORA.get(), new Item.Properties()));

    // frozen caves
    public static final RegistryObject<BlockItem> FROZEN_STONE = ITEMS.register("frozen_stone", () -> new BlockItem(EWBlocks.FROZEN_STONE.get(), new Item.Properties()));
    public static final RegistryObject<BlockItem> FROZEN_STONE_SLAB = ITEMS.register("frozen_stone_slab", () -> new BlockItem(EWBlocks.FROZEN_STONE_SLAB.get(), new Item.Properties()));
    public static final RegistryObject<BlockItem> FROZEN_STONE_STAIRS = ITEMS.register("frozen_stone_stairs", () -> new BlockItem(EWBlocks.FROZEN_STONE_STAIRS.get(), new Item.Properties()));
    public static final RegistryObject<BlockItem> FROZEN_STONE_WALL = ITEMS.register("frozen_stone_wall", () -> new BlockItem(EWBlocks.FROZEN_STONE_WALL.get(), new Item.Properties()));
    public static final RegistryObject<BlockItem> ICE_CRYSTAL_ORE = ITEMS.register("ice_crystal_ore", () -> new BlockItem(EWBlocks.ICE_CRYSTAL_ORE.get(), new Item.Properties()));
    public static final RegistryObject<BlockItem> ICE_CRYSTAL_BLOCK = ITEMS.register("ice_crystal_block", () -> new BlockItem(EWBlocks.ICE_CRYSTAL_BLOCK.get(), new Item.Properties()));
    public static final RegistryObject<StandingAndWallBlockItem> ICE_CRYSTAL_TORCH = ITEMS.register("ice_crystal_torch", () -> new StandingAndWallBlockItem(EWBlocks.ICE_CRYSTAL_TORCH.get(), EWBlocks.ICE_CRYSTAL_WALL_TORCH.get(), new Item.Properties(), Direction.DOWN));
    public static final RegistryObject<BlockItem> FROSTBITE_TNT = ITEMS.register("frostbite_tnt", () -> new BlockItem(EWBlocks.FROSTBITE_TNT.get(), new Item.Properties()));
    public static final RegistryObject<BlockItem> FROZEN_DIRT = ITEMS.register("frozen_dirt", () -> new BlockItem(EWBlocks.FROZEN_DIRT.get(), new Item.Properties()));
    public static final RegistryObject<BlockItem> FROZEN_GRASS_BLOCK = ITEMS.register("frozen_grass_block", () -> new BlockItem(EWBlocks.FROZEN_GRASS_BLOCK.get(), new Item.Properties()));
    public static final RegistryObject<BlockItem> ICE_LANTERN = ITEMS.register("ice_lantern", () -> new BlockItem(EWBlocks.ICE_LANTERN.get(), new Item.Properties()));
    public static final RegistryObject<BlockItem> POINTED_ICE = ITEMS.register("pointed_ice", () -> new BlockItem(EWBlocks.POINTED_ICE.get(), new Item.Properties()));
    public static final RegistryObject<BlockItem> FROZEN_GRASS = ITEMS.register("frozen_grass", () -> new BlockItem(EWBlocks.FROZEN_GRASS.get(), new Item.Properties()));
    public static final RegistryObject<BlockItem> ICE_FLOWER = ITEMS.register("ice_flower", () -> new BlockItem(EWBlocks.ICE_FLOWER.get(), new Item.Properties()));
    public static final RegistryObject<BlockItem> FRIGID_GLADIOLUS = ITEMS.register("frigid_gladiolus", () -> new BlockItem(EWBlocks.FRIGID_GLADIOLUS.get(), new Item.Properties()));

    public static final RegistryObject<Item> ICE_CRYSTAL = ITEMS.register("ice_crystal", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> FROSTBITE_GUNPOWDER = ITEMS.register("frostbite_gunpowder", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> SHARP_ICICLE = ITEMS.register("sharp_icicle", () -> new Item(new Item.Properties()));
    public static final RegistryObject<FrostChargeItem> FROST_CHARGE = ITEMS.register("frost_charge", () -> new FrostChargeItem(new Item.Properties()));
    public static final RegistryObject<FrozenArrowItem> FROZEN_ARROW = ITEMS.register("frozen_arrow", () -> new FrozenArrowItem(new Item.Properties()));
}
