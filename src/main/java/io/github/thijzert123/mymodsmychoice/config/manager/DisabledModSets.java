package io.github.thijzert123.mymodsmychoice.config.manager;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.thijzert123.mymodsmychoice.MyModsMyChoice;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class DisabledModSets {
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final File configFile = MyModsMyChoice.DISABLED_MOD_SETS_CONFIG_PATH.toFile();

    public static boolean isModSetDisabled(final String modSetId) {
        return load().contains(modSetId);
    }

    public static void setModSetDisabled(final String modSetId, final boolean disabled) {
        final List<String> disabledModSets = load();
        if (!disabledModSets.contains(modSetId) && disabled) {
            disabledModSets.add(modSetId);
        } else if (!disabled) {
            disabledModSets.remove(modSetId);
        }
        save(disabledModSets);
    }

    public static List<String> load() {
        try {
            return objectMapper.readValue(configFile, new TypeReference<>() {});
        } catch (final IOException ioException) {
            MyModsMyChoice.LOGGER.error("Can't read disabled mod sets config file, creating empty file");
            final List<String> emptyList = new ArrayList<>();
            save(emptyList);
            return emptyList;
        }
    }

    // Overwrites everything
    private static void save(final List<String> disabledMods) {
        try {
            objectMapper.writeValue(configFile, disabledMods);
        } catch (final IOException ioException) {
            MyModsMyChoice.LOGGER.error("Can't write to disabled mods config file.");
        }
    }
}
