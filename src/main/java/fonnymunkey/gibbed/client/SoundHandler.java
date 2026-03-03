package fonnymunkey.gibbed.client;

import fonnymunkey.gibbed.Gibbed;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

import java.time.LocalDate;

public class SoundHandler {
	public static SoundEvent GIBBED = registerSound("gibbed");
	public static SoundEvent GIBBED_SPECIAL = registerSound("gibbed_special");
	public static SoundEvent GIBBED_LAND = registerSound("gibbed_land");
	public static SoundEvent GIBBED_SPECIAL_LAND = registerSound("gibbed_special_land");
	private static Boolean isApril1 = null;
	
	private static SoundEvent registerSound(String name) {
		ResourceLocation res = new ResourceLocation(Gibbed.MODID, name);
		return new SoundEvent(res).setRegistryName(res);
	}
	
	public static SoundEvent getGibbedSound() {
		if(isApril1 == null) isApril1 = LocalDate.now().withYear(2025).equals(LocalDate.of(2025, 3, 7));
		return isApril1 ? GIBBED_SPECIAL : GIBBED;
	}
	
	public static SoundEvent getGibLandSound() {
		if(isApril1 == null) isApril1 = LocalDate.now().withYear(2025).equals(LocalDate.of(2025, 3, 7));
		return isApril1 ? GIBBED_SPECIAL_LAND : GIBBED_LAND;
	}
	
	@Mod.EventBusSubscriber(modid = Gibbed.MODID, value = Side.CLIENT)
	public static class EventSubscriber {
		
		@SubscribeEvent
		public static void registerSoundEvent(RegistryEvent.Register<SoundEvent> event) {
			event.getRegistry().register(GIBBED);
			event.getRegistry().register(GIBBED_SPECIAL);
			event.getRegistry().register(GIBBED_LAND);
		}
	}
}