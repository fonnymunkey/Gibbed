package fonnymunkey.gibbed.mixin.vanilla;

import fonnymunkey.gibbed.client.RenderCaptureHandler;
import fonnymunkey.gibbed.util.IModelRenderer;
import net.minecraft.client.model.ModelRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(ModelRenderer.class)
public abstract class ModelRendererMixin implements IModelRenderer {
	
	@Shadow
	public boolean isHidden;
	@Shadow
	public boolean showModel;
	@Shadow
	public float rotateAngleX;
	@Shadow
	public float rotationPointX;
	@Shadow
	public float rotationPointY;
	@Shadow
	public float rotationPointZ;
	@Shadow
	public float rotateAngleY;
	@Shadow
	public float rotateAngleZ;
	@Shadow
	public float offsetX;
	@Shadow
	public float offsetY;
	@Shadow
	public float offsetZ;
	
	@Shadow
	public List<ModelRenderer> childModels;
	//blegh
	@Unique
	private boolean gibbed$defaultsSet = false;
	@Unique
	private float gibbed$defaultRotationPointX;
	@Unique
	private float gibbed$defaultRotationPointY;
	@Unique
	private float gibbed$defaultRotationPointZ;
	@Unique
	private float gibbed$defaultRotateAngleX;
	@Unique
	private float gibbed$defaultRotateAngleY;
	@Unique
	private float gibbed$defaultRotateAngleZ;
	@Unique
	private float gibbed$defaultOffsetX;
	@Unique
	private float gibbed$defaultOffsetY;
	@Unique
	private float gibbed$defaultOffsetZ;
	
	@Inject(
			method = "render",
			at = @At("HEAD")
	)
	private void gibbed_vanillaModelRenderer_render(float scale, CallbackInfo ci) {
		if(!this.isHidden && this.showModel) {
			RenderCaptureHandler.captureRenderer((ModelRenderer)(Object)this, scale);
			this.gibbed$initDefaultStates();
		}
	}
	
	@Inject(
			method = "renderWithRotation",
			at = @At("HEAD")
	)
	private void gibbed_vanillaModelRenderer_renderWithRotation(float scale, CallbackInfo ci) {
		if(!this.isHidden && this.showModel) {
			RenderCaptureHandler.captureRenderer((ModelRenderer)(Object)this, scale);
			this.gibbed$initDefaultStates();
		}
	}
	
	@Inject(
			method = "postRender",
			at = @At("HEAD")
	)
	private void gibbed_vanillaModelRenderer_postRender(float scale, CallbackInfo ci) {
		if(!this.isHidden && this.showModel) {
			RenderCaptureHandler.captureRenderer((ModelRenderer)(Object)this, scale);
			this.gibbed$initDefaultStates();
		}
	}
	
	//Actively capturing and storing these values per renderer per new gib requires avoiding caching/pooling to properly handle nested renders
	//Rather use the first frame "default pose" than the added complexity/performance hit
	@Unique
	@Override
	public void gibbed$initDefaultStates() {
		if(!this.gibbed$defaultsSet) {
			this.gibbed$defaultsSet = true;
			this.gibbed$defaultRotationPointX = this.rotationPointX;
			this.gibbed$defaultRotationPointY = this.rotationPointY;
			this.gibbed$defaultRotationPointZ = this.rotationPointZ;
			this.gibbed$defaultRotateAngleX = this.rotateAngleX;
			this.gibbed$defaultRotateAngleY = this.rotateAngleY;
			this.gibbed$defaultRotateAngleZ = this.rotateAngleZ;
			this.gibbed$defaultOffsetX = this.offsetX;
			this.gibbed$defaultOffsetY = this.offsetY;
			this.gibbed$defaultOffsetZ = this.offsetZ;
		}
	}
	
	@Unique
	@Override
	public void gibbed$setToDefaultStates() {
		if(this.gibbed$defaultsSet) {
			this.rotationPointX = this.gibbed$defaultRotationPointX;
			this.rotationPointY = this.gibbed$defaultRotationPointY;
			this.rotationPointZ = this.gibbed$defaultRotationPointZ;
			this.rotateAngleX = this.gibbed$defaultRotateAngleX;
			this.rotateAngleY = this.gibbed$defaultRotateAngleY;
			this.rotateAngleZ = this.gibbed$defaultRotateAngleZ;
			this.offsetX = this.gibbed$defaultOffsetX;
			this.offsetY = this.gibbed$defaultOffsetY;
			this.offsetZ = this.gibbed$defaultOffsetZ;
			if(this.childModels != null) {
				for(ModelRenderer childRenderer : this.childModels) {
					((IModelRenderer)childRenderer).gibbed$setToDefaultStates();
				}
			}
		}
	}
	
	@Unique
	@Override
	public float gibbed$getDefaultRotationPointX() {
		return this.gibbed$defaultsSet ? this.gibbed$defaultRotationPointX : this.rotationPointX;
	}
	
	@Unique
	@Override
	public float gibbed$getDefaultRotationPointY() {
		return this.gibbed$defaultsSet ? this.gibbed$defaultRotationPointY : this.rotationPointY;
	}
	
	@Unique
	@Override
	public float gibbed$getDefaultRotationPointZ() {
		return this.gibbed$defaultsSet ? this.gibbed$defaultRotationPointZ : this.rotationPointZ;
	}
}