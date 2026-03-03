package fonnymunkey.gibbed.client;

import fonnymunkey.gibbed.client.gib.EntityGib;
import fonnymunkey.gibbed.compat.ModLoadedUtil;
import fonnymunkey.gibbed.compat.ballisticblood.BallisticBloodCompat;
import net.minecraft.entity.EntityLivingBase;

public class ParticleHandler {
	
	public static int getBloodParticleType(EntityLivingBase entity) {
		if(ModLoadedUtil.isBallisticBloodLoaded()) return BallisticBloodCompat.getBloodParticleType(entity);
		return -1;
	}
	
	public static void spawnGibbedParticle(EntityGib gib) {
		if(ModLoadedUtil.isBallisticBloodLoaded()) BallisticBloodCompat.spawnBloodParticle(
				gib.particleType,
				gib.posX, gib.posY, gib.posZ,
				gib.motionX, gib.motionY, gib.motionZ,
				gib.particleScale);
	}
	
	public static void spawnGibLandParticle(EntityGib gib) {
		if(ModLoadedUtil.isBallisticBloodLoaded()) BallisticBloodCompat.spawnBloodParticle(
				gib.particleType,
				gib.posX, gib.posY + 0.1F, gib.posZ,
				0.0F, 0.0F, 0.0F,
				gib.particleScale);
	}
}