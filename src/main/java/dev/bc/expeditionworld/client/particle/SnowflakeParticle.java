package dev.bc.expeditionworld.client.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SnowflakeParticle extends TextureSheetParticle {
	private final SpriteSet sprites;
	private float rotSpeed;
	private final float spinAcceleration;

	protected SnowflakeParticle(ClientLevel level, double x, double y, double z, double xd, double yd, double zd, SpriteSet spriteSet) {
		super(level, x, y, z);
		this.gravity = 0.225F;
		this.friction = 1.0F;
		this.sprites = spriteSet;
		this.xd = xd + (Math.random() * 2.0D - 1.0D) * (double) 0.05F;
		this.yd = yd + (Math.random() * 2.0D - 1.0D) * (double) 0.05F;
		this.zd = zd + (Math.random() * 2.0D - 1.0D) * (double) 0.05F;
		this.quadSize = 0.1F * (this.random.nextFloat() * this.random.nextFloat() * 1.0F + 1.0F);
		this.lifetime = (int) (16.0D / ((double) this.random.nextFloat() * 0.8D + 0.2D)) + 2;
		this.rotSpeed = (float) Math.toRadians(this.random.nextBoolean() ? -30D : 30D);
		this.spinAcceleration = (float) Math.toRadians(this.random.nextBoolean() ? -5D : 5D);
		this.setSpriteFromAge(spriteSet);
	}

	public ParticleRenderType getRenderType() {
		return ParticleRenderType.PARTICLE_SHEET_OPAQUE;
	}

	public void tick() {
		super.tick();
		this.setSpriteFromAge(this.sprites);
		this.xd *= 0.95F;
		this.yd *= 0.9F;
		this.zd *= 0.95F;
		this.rotSpeed += this.spinAcceleration / 20.0F;
		this.roll += this.rotSpeed / 20.0F;
	}

	@OnlyIn(Dist.CLIENT)
	public static class Provider implements ParticleProvider<SimpleParticleType> {
		private final SpriteSet sprites;

		public Provider(SpriteSet spriteSet) {
			this.sprites = spriteSet;
		}

		public Particle createParticle(SimpleParticleType type, ClientLevel level, double x, double y, double z, double xd, double yd, double zd) {
			SnowflakeParticle snowflakeparticle = new SnowflakeParticle(level, x, y, z, xd, yd, zd, this.sprites);
			snowflakeparticle.setColor(0.923F, 0.964F, 0.999F);
			return snowflakeparticle;
		}
	}
}