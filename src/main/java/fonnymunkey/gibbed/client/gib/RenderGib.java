package fonnymunkey.gibbed.client.gib;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class RenderGib extends Render<EntityGib> {

    public RenderGib(RenderManager manager) {
        super(manager);
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityGib gib) {
        return gib.baseTexture;
    }

    @Override
    public void doRender(EntityGib entityGib, double x, double y, double z, float entityYaw, float partialTicks) {
        if(entityGib.gib == null || entityGib.baseTexture == null || entityGib.scale == 0.0F) return;
        entityGib.gib.render(this, entityGib, x, y, z, partialTicks, entityGib.scales);
    }

    public static class RenderFactory implements IRenderFactory<EntityGib> {
        @Override
        public Render<? super EntityGib> createRenderFor(RenderManager manager) {
            return new RenderGib(manager);
        }
    }
}