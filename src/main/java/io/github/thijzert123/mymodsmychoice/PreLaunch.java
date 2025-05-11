package io.github.thijzert123.mymodsmychoice;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.thijzert123.mymodsmychoice.config.ModSet;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.impl.ModContainerImpl;
import settingdust.preloadingtricks.SetupModCallback;
import settingdust.preloadingtricks.fabric.FabricModSetupService;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PreLaunch implements SetupModCallback {
    private static final FabricModSetupService SERVICE = FabricModSetupService.INSTANCE;
    private final ObjectMapper objectMapper;

    public PreLaunch() {
        MyModsMyChoice.LOGGER.info("I'm here before the rest!");

        objectMapper = new ObjectMapper();
        final List<String> modsToDisable = modSetsToMods(loadDisabledModSets());

        FabricLoader.getInstance().getAllMods().forEach((modContainer -> {
            final String modId = modContainer.getMetadata().getId();
            if (modsToDisable.contains(modId)) {
                MyModsMyChoice.LOGGER.info("Removing {}", modId);
                SERVICE.remove((ModContainerImpl) modContainer);
            }
        }));
    }

    private List<String> loadDisabledModSets() {
        final File configFile = MyModsMyChoice.DISABLED_MOD_SETS_CONFIG_PATH.toFile();
        try {
            return objectMapper.readValue(configFile, new TypeReference<>() {});
        } catch (final IOException ioException1) {
            MyModsMyChoice.LOGGER.error("Can't read disabled mod sets config file, creating empty file");
            try {
                final List<String> emptyList = new ArrayList<>();
                objectMapper.writeValue(configFile, emptyList);
                return emptyList;
            } catch (final IOException ioException2) {
                throw new RuntimeException(ioException2);
            }
        }
    }

    // Converts mod sets to individual mods.
    // If a mod set for example had sodium and indium, it would add both to the returned List<String>.
    private List<String> modSetsToMods(final List<String> disabledModSets) {
        final Map<String, ModSet> modSets = loadModSets();
        final List<String> mods = new ArrayList<>();
        for (final String disabledModSet : disabledModSets) {
            mods.addAll(modSets.get(disabledModSet).mods);
        }
        return mods;
    }

    private Map<String, ModSet> loadModSets() {
        final File configFile = MyModsMyChoice.MOD_SETS_CONFIG_PATH.toFile();
        try {
            return objectMapper.readValue(configFile, new TypeReference<>() {});
        } catch (final IOException ioException1) {
            MyModsMyChoice.LOGGER.error("Can't read mod sets config file, creating empty file");
            try {
                final Map<String, ModSet> emptyMap = new HashMap<>();
                objectMapper.writeValue(configFile, emptyMap);
                return emptyMap;
            } catch (final IOException ioException2) {
                throw new RuntimeException(ioException2);
            }
        }
    }
}
