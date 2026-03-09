package fonnymunkey.gibbed.mixin.vanilla;

import net.minecraft.client.model.ModelRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(ModelRenderer.class)
public interface ModelRendererInvoker {
	@Accessor(value = "compiled")
	boolean gibbed$getCompiled();
	@Accessor(value = "displayList")
	int gibbed$getDisplayList();
	@Invoker(value = "compileDisplayList")
	void gibbed$compileDisplayList(float scale);
}