package fonnymunkey.gibbed.mixin.vanilla;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(Render.class)
public interface RenderInvoker {
	@Invoker(value = "getEntityTexture")
	ResourceLocation gibbed$invokeGetEntityTexture(Entity entity);
}