package fonnymunkey.gibbed.compat.lycanites;

import com.lycanitesmobs.client.model.ModelCreatureObj;
import com.lycanitesmobs.client.model.ModelObjOld;
import com.lycanitesmobs.client.obj.ObjObject;
import com.lycanitesmobs.client.obj.TessellatorModel;
import com.lycanitesmobs.client.renderer.RenderCreature;
import fonnymunkey.gibbed.client.gib.AbstractGib;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.entity.EntityLivingBase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class LycanitesCompat {
	
	public static boolean isRendererLycanite(RenderLivingBase<? extends EntityLivingBase> entityRendererLiving) {
		return entityRendererLiving instanceof RenderCreature;
	}
	
	public static List<AbstractGib> generateGibsForLycanite(RenderLivingBase<? extends EntityLivingBase> entityRendererLiving) {
		if(!isRendererLycanite(entityRendererLiving)) return Collections.emptyList();
		
		ModelBase mainModel = entityRendererLiving.getMainModel();
		TessellatorModel tessellator = null;
		List<ObjObject> objs = null;
		if(mainModel instanceof ModelCreatureObj) {
			tessellator = ((ModelCreatureObj)entityRendererLiving.getMainModel()).wavefrontObject;
			objs = ((ModelCreatureObj)entityRendererLiving.getMainModel()).wavefrontParts;
		}
		else if(mainModel instanceof ModelObjOld) {
			tessellator = ((ModelObjOld)entityRendererLiving.getMainModel()).wavefrontObject;
			objs = ((ModelObjOld)entityRendererLiving.getMainModel()).wavefrontParts;
		}
		
		if(tessellator != null && objs != null && !objs.isEmpty()) {
			List<AbstractGib> gennedGibs = new ArrayList<>();
			for(ObjObject obj : objs) {
				AbstractGib gib = new ObjGib(tessellator, obj, mainModel.textureWidth, mainModel.textureHeight);
				if(gib.valid()) gennedGibs.add(gib);
			}
			return gennedGibs;
		}
		return Collections.emptyList();
	}
}