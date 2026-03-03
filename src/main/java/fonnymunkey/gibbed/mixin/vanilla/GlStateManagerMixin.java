package fonnymunkey.gibbed.mixin.vanilla;

import fonnymunkey.gibbed.client.RenderCaptureHandler;
import net.minecraft.client.renderer.GlStateManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GlStateManager.class)
public abstract class GlStateManagerMixin {
	
	@Inject(
			method = "scale(FFF)V",
			at = @At("HEAD")
	)
	private static void gibbed_vanillaGlStateManager_scale(float x, float y, float z, CallbackInfo ci) {
		RenderCaptureHandler.captureScale(x, y, z);
	}
}