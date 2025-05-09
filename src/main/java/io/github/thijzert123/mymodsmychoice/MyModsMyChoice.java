package io.github.thijzert123.mymodsmychoice;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.loader.api.FabricLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class MyModsMyChoice implements ModInitializer {
	public static final String MOD_ID = "MyModsMyChoice";
	public static final Path CONFIG_DIR = FabricLoader.getInstance().getConfigDir().resolve(MOD_ID);
	public static final String MOD_SETS_CONFIG_FILENAME = "mod_sets.json";
	public static final Path MOD_SETS_CONFIG_PATH = CONFIG_DIR.resolve(MOD_SETS_CONFIG_FILENAME);
	public static final String CONFIG_SCREEN_CONFIG_FILENAME = "config_screen.json";
	public static final Path CONFIG_SCREEN_CONFIG_PATH = CONFIG_DIR.resolve(CONFIG_SCREEN_CONFIG_FILENAME);
	public static final String DISABLED_MODS_SETS_CONFIG_FILENAME = "disabled_mods.txt";
	public static final Path DISABLED_MODS_CONFIG_PATH = CONFIG_DIR.resolve(DISABLED_MODS_SETS_CONFIG_FILENAME);

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	// TODO remove
	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		LOGGER.info("My Mods, My Choice!");
        try {
            Files.createDirectories(CONFIG_DIR); // also creates CONFIG_DIR, as CATEGORIES_DIR is a subfolder
        } catch (final IOException ioException) {
            throw new RuntimeException("Couldn't create config directories.", ioException);
        }
    }
}