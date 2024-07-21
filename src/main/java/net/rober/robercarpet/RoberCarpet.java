package net.rober.robercarpet;

import carpet.CarpetExtension;
import carpet.CarpetServer;
import carpet.settings.SettingsManager;
import net.fabricmc.api.ModInitializer;
import net.rober.robercarpet.register.dispenser.FarmableClayBehavior;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RoberCarpet implements ModInitializer, CarpetExtension {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final String MODID = "robercarpet";
	public static final Logger LOGGER = LogManager.getLogger(MODID);

	private static final SettingsManager mySettingManager = new SettingsManager("1.0.0", MODID,"Rober-Carpet");

	@Override
	public void onInitialize() {
		//? if <1.19
		/*FarmableClayBehavior.init();*/
		RoberCarpet extension = new RoberCarpet();
		CarpetServer.manageExtension(extension);
	}
	@Override
	public void onGameStarted(){
		CarpetServer.settingsManager.parseSettingsClass(RoberCarpetSettings.class);
	}
}
