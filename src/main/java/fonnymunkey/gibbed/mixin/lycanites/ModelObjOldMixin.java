package fonnymunkey.gibbed.mixin.lycanites;

import com.lycanitesmobs.client.model.ModelObjOld;
import com.lycanitesmobs.client.renderer.layer.LayerCreatureBase;
import fonnymunkey.gibbed.client.RenderCaptureHandler;
import net.minecraft.entity.Entity;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ModelObjOld.class)
public abstract class ModelObjOldMixin {
	
	@Inject(
			method = "render",
			at = @At(value = "FIELD", target = "Lcom/lycanitesmobs/client/model/ModelObjOld;wavefrontParts:Ljava/util/List;", opcode = Opcodes.GETFIELD),
			remap = false
	)
	private void gibbed_lycanitesModelCreatureObj_render(Entity entity, float time, float distance, float loop, float lookY, float lookX, float scale, LayerCreatureBase layer, boolean animate, CallbackInfo ci) {
		RenderCaptureHandler.beginScaleCapture();
		RenderCaptureHandler.captureScale(scale, scale, scale);
		RenderCaptureHandler.endScaleCapture();
	}
}