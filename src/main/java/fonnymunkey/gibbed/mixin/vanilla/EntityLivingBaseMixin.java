package fonnymunkey.gibbed.mixin.vanilla;

import fonnymunkey.gibbed.client.EventHandlerClient;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.entity.EntityLivingBase;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityLivingBase.class)
public abstract class EntityLivingBaseMixin {
	
	@Shadow
	public int deathTime;
	
	//MC/Forge is dumb and doesn't properly post livingdeath for clientside player entities, only the serverside version
	@Inject(
			method = "onDeathUpdate",
			at = @At("HEAD")
	)
	private void gibbed_vanillaEntityLivingBase_onDeathUpdate(CallbackInfo ci) {
		if((EntityLivingBase)(Object)this instanceof AbstractClientPlayer && this.deathTime == 1) {
			EventHandlerClient.addDyingEntityManual((EntityLivingBase)(Object)this);
		}
	}
}