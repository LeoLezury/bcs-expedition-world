package dev.bc.expeditionworld.block;

import dev.bc.expeditionworld.ExpeditionWorld;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public class EWBlockTags {
    public static final TagKey<Block> PREVENTS_MELTING = create("prevents_melting");

    private static TagKey<Block> create(String id) {
        return TagKey.create(Registries.BLOCK, ExpeditionWorld.id(id));
    }
}
