package dev.bc.expeditionworld.client.event;

import dev.bc.expeditionworld.ExpeditionWorld;
import dev.bc.expeditionworld.data.EWBiomes;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.util.Mth;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientTickEvent;

@OnlyIn(Dist.CLIENT)
@EventBusSubscriber(modid = ExpeditionWorld.ID)
public class ClientEvents {
	public static float oldAmbientLightAddition = 0;
	public static float ambientLightAddition = 0;

	@SubscribeEvent
	private static void onClientTick(ClientTickEvent.Post event) {
		oldAmbientLightAddition = ambientLightAddition;
		Minecraft minecraft = Minecraft.getInstance();
		LocalPlayer player = minecraft.player;
		ClientLevel level = minecraft.level;
		if (player != null && level != null) {
			if (level.getBiome(player.blockPosition()).is(EWBiomes.FROZEN_CAVES)) {
				ambientLightAddition += 0.02f;
			}
		}
		ambientLightAddition -= 0.01f;
		if (ambientLightAddition > 0.1) {
			ambientLightAddition = 0.1f;
		}
		if (ambientLightAddition < 0) {
			ambientLightAddition = 0;
		}
	}

	public static float getAmbientLightAddition() {
		float partialTicks = Minecraft.getInstance().getTimer().getGameTimeDeltaPartialTick(Minecraft.getInstance().level != null && Minecraft.getInstance().level.tickRateManager().runsNormally());
		return Mth.lerp(partialTicks, oldAmbientLightAddition, ambientLightAddition);
	}
}
