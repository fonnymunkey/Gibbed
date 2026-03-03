package fonnymunkey.gibbed.mixin.lycanites;

import com.lycanitesmobs.client.obj.ObjModel;
import com.lycanitesmobs.client.obj.ObjObject;
import fonnymunkey.gibbed.client.RenderCaptureHandler;
import net.minecraft.client.renderer.vertex.VertexFormat;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import javax.vecmath.Vector2f;
import javax.vecmath.Vector4f;

@Mixin(ObjModel.class)
public abstract class ObjModelMixin {
	
	@Inject(
			method = "renderGroup(Lcom/lycanitesmobs/client/obj/ObjObject;Ljavax/vecmath/Vector4f;Ljavax/vecmath/Vector2f;Lnet/minecraft/client/renderer/vertex/VertexFormat;)V",
			at = @At("HEAD"),
			remap = false
	)
	private void gibbed_lycanitesObjModel_renderGroup(ObjObject group, Vector4f color, Vector2f textureOffset, VertexFormat vertexFormat, CallbackInfo ci) {
		if(group != null) RenderCaptureHandler.captureRendererObj(group, 1.0F, color.x, color.y, color.z, color.w, textureOffset.x, textureOffset.y);
	}
}