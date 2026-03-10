package fonnymunkey.gibbed.client.particle;

import net.minecraft.client.particle.Particle;
import net.minecraft.world.World;

public class ParticleBlood extends Particle {
	
	public ParticleBlood(World worldIn, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn, double ySpeedIn, double zSpeedIn) {
		super(worldIn, xCoordIn, yCoordIn, zCoordIn, xSpeedIn, ySpeedIn, zSpeedIn);
		this.particleRed = this.rand.nextFloat() * 0.2F + 0.8F;
		this.particleGreen = 0.0F;
		this.particleBlue = 0.0F;
		this.setParticleTextureIndex(19 + this.rand.nextInt(4));
		this.setSize(0.02F, 0.02F);
		this.particleGravity = 0.8F;
		this.particleMaxAge = (int)(8.0D / (Math.random() * 0.4D + 0.1D));
	}
}