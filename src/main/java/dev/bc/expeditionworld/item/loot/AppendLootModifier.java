package dev.bc.expeditionworld.item.loot;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.common.loot.LootModifier;
import org.jetbrains.annotations.NotNull;

public class AppendLootModifier extends LootModifier {
	public static final MapCodec<AppendLootModifier> CODEC = RecordCodecBuilder.mapCodec(instance -> codecStart(instance).and(ResourceLocation.CODEC.fieldOf("loot").forGetter(modifier -> modifier.loot)).apply(instance, AppendLootModifier::new));

	private final ResourceLocation loot;

	public AppendLootModifier(LootItemCondition[] conditionsIn, ResourceLocation location) {
		super(conditionsIn);
		this.loot = location;
	}

	@Override
	protected @NotNull ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
		context.getLevel().getServer().reloadableRegistries().getLootTable(ResourceKey.create(Registries.LOOT_TABLE, loot)).getRandomItemsRaw(context, (generatedLoot::add));
		return generatedLoot;
	}

	@Override
	public MapCodec<? extends IGlobalLootModifier> codec() {
		return CODEC;
	}
}
