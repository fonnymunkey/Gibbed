package fonnymunkey.gibbed.mixin.vanilla;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import fonnymunkey.gibbed.client.RenderCaptureHandler;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.entity.EntityLivingBase;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(RenderLivingBase.class)
public abstract class RenderLivingBaseMixin {
	
	@WrapOperation(
			method = "prepareScale",
			at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/entity/RenderLivingBase;preRenderCallback(Lnet/minecraft/entity/EntityLivingBase;F)V")
	)
	private void gibbed_vanillaRenderLivingBase_prepareScale(RenderLivingBase<? extends EntityLivingBase> instance, EntityLivingBase entity, float partialTickTime, Operation<Void> original) {
		RenderCaptureHandler.beginScaleCapture();
		original.call(instance, entity, partialTickTime);
		RenderCaptureHandler.endScaleCapture();
	}
	
	//Capture specific textures during layer render as they may be textures not used by the base model
	@WrapMethod(
			method = "renderLayers"
	)
	private void test(EntityLivingBase entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scaleIn, Operation<Void> original) {
		if(entity instanceof EntityLivingBase && !entity.isEntityAlive()) {
			RenderCaptureHandler.endBaseTextureCapture();
			RenderCaptureHandler.beginLayerTextureCapture();
		}
		original.call(entity, limbSwing, limbSwingAmount, partialTicks, ageInTicks, netHeadYaw, headPitch, scaleIn);
		if(entity instanceof EntityLivingBase && !entity.isEntityAlive()) {
			RenderCaptureHandler.endLayerTextureCapture();
		};
	}
}