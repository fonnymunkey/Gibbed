package fonnymunkey.gibbed.mixin.mobends;

import fonnymunkey.gibbed.client.RenderCaptureHandler;
import fonnymunkey.gibbed.mixin.vanilla.ModelRendererInvoker;
import fonnymunkey.gibbed.util.IModelRenderer;
import goblinbob.mobends.core.client.model.ModelPart;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ModelPart.class)
public abstract class ModelPartMixin extends ModelRenderer implements IModelRenderer {
	
	public ModelPartMixin(ModelBase model, String boxNameIn) {
		super(model, boxNameIn);
	}
	
	@Inject(
			method = "renderPart",
			at = @At("HEAD"),
			remap = false
	)
	private void gibbed_mobendsModelPart_renderPart(float scale, CallbackInfo ci) {
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
	private void gibbed_mobendsModelPart_renderJustPart(float scale, CallbackInfo ci) {
		if(!this.isHidden && this.showModel) {
			RenderCaptureHandler.captureRenderer((ModelRenderer)(Object)this, scale);
			this.gibbed$initDefaultStates();
		}
	}
	
	@Unique
	@Override
	public void gibbed$renderSingular(float scale) {
		if(!this.isHidden && this.showModel) {
			if(!((ModelRendererInvoker)(Object)this).gibbed$getCompiled()) {
				((ModelRendererInvoker)(Object)this).gibbed$compileDisplayList(scale);
			}
			GlStateManager.pushMatrix();
			GlStateManager.callList(((ModelRendererInvoker)(Object)this).gibbed$getDisplayList());
			GlStateManager.popMatrix();
		}
	}
}