package fonnymunkey.gibbed.client.particle;

import fonnymunkey.gibbed.client.gib.EntityGib;
import fonnymunkey.gibbed.compat.ModLoadedUtil;
import fonnymunkey.gibbed.compat.ballisticblood.BallisticBloodCompat;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;

public class ParticleHandler {
	
	public static int getBloodParticleType(EntityLivingBase entity) {
		if(ModLoadedUtil.isBallisticBloodLoaded()) return BallisticBloodCompat.getBloodParticleType(entity);
		return -1;
	}
	
	public static void spawnGibbedParticle(EntityGib gib) {
		if(ModLoadedUtil.isBallisticBloodLoaded()) {
			BallisticBloodCompat.spawnBloodParticle(
					gib.particleType,
					gib.posX, gib.posY, gib.posZ,
					gib.motionX, gib.motionY, gib.motionZ,
					gib.particleScale);
		}
		else {
			World world = Minecraft.getMinecraft().world;
			if(world == null || Minecraft.getMinecraft().effectRenderer == null) return;
			int particles = Math.max(1, (int)gib.particleScale);
			for(int i = 0; i < particles; i++) {
				Minecraft.getMinecraft().effectRenderer.addEffect(
						new ParticleBlood(world,
										  gib.posX, gib.posY, gib.posZ,
										  gib.motionX, gib.motionY, gib.motionZ));
			}
		}
	}
	
	public static void spawnGibLandParticle(EntityGib gib) {
		if(ModLoadedUtil.isBallisticBloodLoaded()) {
			BallisticBloodCompat.spawnBloodParticle(
					gib.particleType,
					gib.posX, gib.posY + 0.1F, gib.posZ,
					0.0F, 0.0F, 0.0F,
					gib.particleScale);
		}
		else {
			World world = Minecraft.getMinecraft().world;
			if(world == null || Minecraft.getMinecraft().effectRenderer == null) return;
			int particles = Math.max(1, (int)gib.particleScale);
			for(int i = 0; i < particles; i++) {
				Minecraft.getMinecraft().effectRenderer.addEffect(
						new ParticleBlood(world,
										  gib.posX, gib.posY, gib.posZ,
										  0.0F, 1.0F, 0.0F));
			}
		}
	}
}