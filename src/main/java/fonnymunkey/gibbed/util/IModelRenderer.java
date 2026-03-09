package fonnymunkey.gibbed.util;

public interface IModelRenderer {
	void gibbed$initDefaultStates();
	void gibbed$setToDefaultStates();
	void gibbed$renderSingular(float scale);
	float gibbed$getDefaultRotationPointX();
	float gibbed$getDefaultRotationPointY();
	float gibbed$getDefaultRotationPointZ();
}