package io.github.thijzert123.mymodsmychoice.config.manager;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.thijzert123.mymodsmychoice.MyModsMyChoice;
import io.github.thijzert123.mymodsmychoice.config.ModSet;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ModSets {
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final Map<String, ModSet> modSets = load();

    // Converts mod sets to individual mods.
    // If a mod set for example had sodium and indium, it would add both to the returned List<String>.
    public static List<String> modSetIdsToMods(final List<String> modSetsToConvert) {
        final List<String> mods = new ArrayList<>();
        for (final String modSetToConvert : modSetsToConvert) {
            mods.addAll(modSets.get(modSetToConvert).mods);
        }
        return mods;
    }

    public static Map<String, ModSet> get() {
        return modSets;
    }

    private static Map<String, ModSet> load() {
        final File configFile = MyModsMyChoice.DISABLED_MOD_SETS_CONFIG_PATH.toFile();
        try {
            return objectMapper.readValue(configFile, new TypeReference<>() {});
        } catch (final IOException ioException1) {
            MyModsMyChoice.LOGGER.error("Can't read mod sets config file, creating empty file");
            final Map<String, ModSet> emptyMap = new HashMap<>();
            try {
                objectMapper.writeValue(configFile, emptyMap);
            } catch (final IOException ioException2) {
                MyModsMyChoice.LOGGER.error("Still can't read mod sets config file, so no mod sets");
            }
            return emptyMap;
        }
    }
}
