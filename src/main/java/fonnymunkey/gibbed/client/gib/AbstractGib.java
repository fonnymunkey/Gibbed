package fonnymunkey.gibbed.client.gib;

public abstract class AbstractGib {
	protected float cX = 0.0F;
	protected float cY = 0.0F;
	protected float cZ = 0.0F;
	protected float dX = 0.0F;
	protected float dY = 0.0F;
	protected float dZ = 0.0F;
	protected float minX = Float.MAX_VALUE;
	protected float minY = Float.MAX_VALUE;
	protected float minZ = Float.MAX_VALUE;
	protected float maxX = -Float.MAX_VALUE;
	protected float maxY = -Float.MAX_VALUE;
	protected float maxZ = -Float.MAX_VALUE;
	
	public AbstractGib() { }
	
	public abstract float cX(float scale);
	
	public abstract float cY(float scale);
	
	public abstract  float cZ(float scale);
	
	public abstract float dX(float scale);
	
	public abstract float dY(float scale);
	
	public abstract float dZ(float scale);
	
	public abstract float offsetX(float scale, float prepScaleX);
	
	public abstract float offsetY(float scale, float prepScaleY);
	
	public abstract float offsetZ(float scale, float prepScaleZ);
	
	public abstract float bbWidth(float scale, float prepScaleX, float prepScaleY, float prepScaleZ);
	
	public abstract float bbHeight(float scale, float prepScaleX, float prepScaleY, float prepScaleZ);
	
	public abstract Object getContextKey();
	
	public abstract float[] getDefaultScales();
	
	public abstract boolean fadeOut();
	
	public abstract boolean flipYaw();
	
	public abstract boolean flipY();
	
	public abstract void render(RenderGib renderGib, EntityGib entityGib, double x, double y, double z, float partialTicks, float[] scales);
	
	public boolean valid() {
		return this.minX != Float.MAX_VALUE && this.minY != Float.MAX_VALUE && this.minZ != Float.MAX_VALUE &&
				this.maxX != -Float.MAX_VALUE && this.maxY != -Float.MAX_VALUE && this.maxZ != -Float.MAX_VALUE;
	}
}