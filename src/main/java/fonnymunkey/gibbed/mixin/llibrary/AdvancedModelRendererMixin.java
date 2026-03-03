package fonnymunkey.gibbed.mixin.llibrary;

import fonnymunkey.gibbed.client.RenderCaptureHandler;
import fonnymunkey.gibbed.util.IModelRenderer;
import net.ilexiconn.llibrary.client.model.tools.AdvancedModelRenderer;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AdvancedModelRenderer.class)
public abstract class AdvancedModelRendererMixin extends ModelRenderer implements IModelRenderer {
	
	public AdvancedModelRendererMixin(ModelBase model, String boxNameIn) {
		super(model, boxNameIn);
	}
	
	@Inject(
			method = "render",
			at = @At("HEAD")
	)
	private void gibbed_llibraryAdvancedModelRenderer_render(float scale, CallbackInfo ci) {
		if(!this.isHidden && this.showModel) {
			RenderCaptureHandler.captureRenderer((ModelRenderer)(Object)this, scale);
			this.gibbed$initDefaultStates();
		}
	}
}