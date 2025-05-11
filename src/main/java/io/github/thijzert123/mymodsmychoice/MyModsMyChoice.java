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
	public static final String DISABLED_MODS_SETS_CONFIG_FILENAME = "disabled_mod_sets.json";
	public static final Path DISABLED_MOD_SETS_CONFIG_PATH = CONFIG_DIR.resolve(DISABLED_MODS_SETS_CONFIG_FILENAME);

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("My Mods, My Choice!");
        try {
            Files.createDirectories(CONFIG_DIR); // TODO check if this is not already done in PreLaunch or if it is still necessary.
        } catch (final IOException ioException) {
            throw new RuntimeException("Couldn't create config directories.", ioException);
        }
    }
}