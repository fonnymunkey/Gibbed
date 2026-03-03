package fonnymunkey.gibbed.client;

import fonnymunkey.gibbed.util.Triple;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;
import java.util.*;

//Gross but decent way to handle context based render states
public class RenderCaptureHandler {
	//Use id rather than entity itself for performance
	private static Integer renderCaptureEntity = null;
	private static final Int2ObjectOpenHashMap<Map<Object,Triple<float[],ResourceLocation,ResourceLocation>>> capturedRenderers = new Int2ObjectOpenHashMap<>();
	private static final Set<Integer> lockedRenderers = new HashSet<>();
	//Theoretically shouldn't need to be too large as only rendered dying entities should be captured
	private static final MapPool<Object,Triple<float[],ResourceLocation,ResourceLocation>> MAPPOOL = new MapPool<>(10, 50);
	
	private static boolean capturingScale = false;
	private static float capturedScaleX = 1.0F;
	private static float capturedScaleY = 1.0F;
	private static float capturedScaleZ = 1.0F;
	
	//Layered texture captured separate from base
	private static boolean capturingBaseTexture = false;
	private static ResourceLocation capturedBaseTexture = null;
	private static boolean capturingLayerTexture = false;
	private static ResourceLocation capturedLayerTexture = null;
	
	public static void beginRenderStateCapture(EntityLivingBase entity) {
		renderCaptureEntity = entity.getEntityId();
		capturingScale = false;
		capturedScaleX = 1.0F;
		capturedScaleY = 1.0F;
		capturedScaleZ = 1.0F;
		capturingBaseTexture = false;
		capturedBaseTexture = null;
		capturingLayerTexture = false;
		capturedLayerTexture = null;
	}
	
	public static void endRenderStateCapture() {
		if(renderCaptureEntity != null) lockedRenderers.add(renderCaptureEntity);
		renderCaptureEntity = null;
		capturingScale = false;
		capturedScaleX = 1.0F;
		capturedScaleY = 1.0F;
		capturedScaleZ = 1.0F;
		capturingBaseTexture = false;
		capturedBaseTexture = null;
		capturingLayerTexture = false;
		capturedLayerTexture = null;
	}
	
	public static void captureRenderer(Object renderer, float scale) {
		if(renderCaptureEntity != null && !lockedRenderers.contains(renderCaptureEntity)) {
			Map<Object,Triple<float[],ResourceLocation,ResourceLocation>> map = capturedRenderers.get((int)renderCaptureEntity);
			if(map == null) map = MAPPOOL.retain();
			Triple<float[],ResourceLocation,ResourceLocation> trip = map.get(renderer);
			if(trip != null) {
				if(capturedBaseTexture != null) trip.middle = capturedBaseTexture;
				if(capturedLayerTexture != null) trip.right = capturedLayerTexture;
			}
			else trip = new Triple<>(new float[]{ scale,
													  capturedScaleX, capturedScaleY, capturedScaleZ },
									 capturedBaseTexture,
									 capturedLayerTexture);
			map.put(renderer, trip);
			capturedRenderers.put((int)renderCaptureEntity, map);
		}
	}
	
	public static void captureRendererObj(Object renderer, float scale, float r, float g, float b, float a, float x, float y) {
		if(renderCaptureEntity != null && !lockedRenderers.contains(renderCaptureEntity)) {
			Map<Object,Triple<float[],ResourceLocation,ResourceLocation>> map = capturedRenderers.get((int)renderCaptureEntity);
			if(map == null) map = MAPPOOL.retain();
			Triple<float[],ResourceLocation,ResourceLocation> trip = map.get(renderer);
			if(trip != null) {
				if(capturedBaseTexture != null) trip.middle = capturedBaseTexture;
				if(capturedLayerTexture != null) trip.right = capturedLayerTexture;
			}
			else trip = new Triple<>(new float[]{ scale,
												  capturedScaleX, capturedScaleY, capturedScaleZ,
												  r, g, b, a,
												  x, y },
									 capturedBaseTexture,
									 capturedLayerTexture);
			map.put(renderer, trip);
			capturedRenderers.put((int)renderCaptureEntity, map);
		}
	}
	
	public static void beginScaleCapture() {
		capturingScale = true;
	}
	
	public static void endScaleCapture() {
		capturingScale = false;
	}
	
	public static void captureScale(float scaleX, float scaleY, float scaleZ) {
		if(capturingScale) {
			capturedScaleX = scaleX;
			capturedScaleY = scaleY;
			capturedScaleZ = scaleZ;
		}
	}
	
	public static void beginBaseTextureCapture() {
		capturingBaseTexture = true;
		capturedBaseTexture = null;
		capturedLayerTexture = null;
	}
	
	public static void endBaseTextureCapture() {
		capturingBaseTexture = false;
		capturedBaseTexture = null;
		capturedLayerTexture = null;
	}
	
	public static void beginLayerTextureCapture() {
		capturingLayerTexture = true;
		capturedBaseTexture = null;
		capturedLayerTexture = null;
	}
	
	public static void endLayerTextureCapture() {
		capturingLayerTexture = false;
		capturedBaseTexture = null;
		capturedLayerTexture = null;
	}
	
	public static void captureTexture(ResourceLocation location) {
		if(capturingBaseTexture) capturedBaseTexture = location;
		else if(capturingLayerTexture) capturedLayerTexture = location;
	}
	
	@Nullable
	public static Map<Object,Triple<float[],ResourceLocation,ResourceLocation>> getCapturedRenderers(EntityLivingBase entity) {
		return capturedRenderers.get(entity.getEntityId());
	}
	
	public static void clearCaptures() {
		for(Map.Entry<Integer,Map<Object,Triple<float[],ResourceLocation,ResourceLocation>>> entry : capturedRenderers.entrySet()) {
			if(entry.getValue() != null) MAPPOOL.release(entry.getValue());
		}
		capturedRenderers.clear();
		lockedRenderers.clear();
		renderCaptureEntity = null;
	}
	
	private static class MapPool<L,R> {
		private final Deque<Map<L,R>> pool;
		private final int max;
		
		public MapPool(int initial, int max) {
			this.pool = new ArrayDeque<>(initial);
			this.max = max;
			for(int i = 0; i < initial; i++) {
				this.pool.push(new HashMap<>());
			}
		}
		
		public Map<L,R> retain() {
			if(!this.pool.isEmpty()) return this.pool.pop();
			return new HashMap<>();
		}
		
		public void release(Map<L,R> map) {
			if(this.pool.size() < this.max) {
				map.clear();
				this.pool.push(map);
			}
		}
	}
}