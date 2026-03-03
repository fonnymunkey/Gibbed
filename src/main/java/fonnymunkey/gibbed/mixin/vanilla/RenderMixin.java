package fonnymunkey.gibbed.mixin.vanilla;

import fonnymunkey.gibbed.client.RenderCaptureHandler;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.util.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Render.class)
public abstract class RenderMixin {
	
	@Inject(
			method = "bindTexture",
			at = @At("HEAD")
	)
	private void gibbed_vanillaRender_bindTexture(ResourceLocation location, CallbackInfo ci) {
		RenderCaptureHandler.captureTexture(location);
	}
}