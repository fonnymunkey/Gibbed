package fonnymunkey.gibbed.compat.ballisticblood;

import com.beckadam.ballisticblood.helpers.ClientHelper;
import com.beckadam.ballisticblood.helpers.CommonHelper;
import fonnymunkey.gibbed.config.ConfigHandler;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.Vec3d;

public class BallisticBloodCompat {
	
	public static int getBloodParticleType(EntityLivingBase entity) {
		if(entity == null) return -1;
		if(ConfigHandler.PARTICLES.useGenericBloodType) return 0;
		return CommonHelper.GetParticleTypeForEntity(entity);
	}
	
	public static void spawnBloodParticle(int particleType, double x, double y, double z, double motionX, double motionY, double motionZ, float particleAmountScale) {
		if(particleType != -1) {
			//TODO current version doesn't use source for anything in this method, may need to use fake source to avoid NPE if update changes that
			ClientHelper.spawnProjectiles(particleType, new Vec3d(x, y, z), new Vec3d(motionX, motionY, motionZ), particleAmountScale, null);
		}
	}
}