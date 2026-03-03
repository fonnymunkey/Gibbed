package fonnymunkey.gibbed.mixin.mobends;

import fonnymunkey.gibbed.client.RenderCaptureHandler;
import fonnymunkey.gibbed.util.IModelRenderer;
import goblinbob.mobends.core.client.model.ModelPartExtended;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ModelPartExtended.class)
public abstract class ModelPartExtendedMixin extends ModelRenderer implements IModelRenderer {
	
	public ModelPartExtendedMixin(ModelBase model, String boxNameIn) {
		super(model, boxNameIn);
	}
	
	@Inject(
			method = "renderPart",
			at = @At("HEAD"),
			remap = false
	)
	private void gibbed_mobendsModelPartExtended_renderPart(float scale, CallbackInfo ci) {
		if(!this.isHidden && this.showModel) {
			RenderCaptureHandler.captureRenderer((ModelRenderer)(Object)this, scale);
			this.gibbed$initDefaultStates();
		}
	}
	
	@Inject(
			method = "renderJustPart",
			at = @At("HEAD"),
			remap = false
	)
	private void gibbed_mobendsModelPartExtended_renderJustPart(float scale, CallbackInfo ci) {
		if(!this.isHidden && this.showModel) {
			RenderCaptureHandler.captureRenderer((ModelRenderer)(Object)this, scale);
			this.gibbed$initDefaultStates();
		}
	}
}