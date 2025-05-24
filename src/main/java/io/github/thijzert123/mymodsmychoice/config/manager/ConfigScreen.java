package io.github.thijzert123.mymodsmychoice.config.manager;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.thijzert123.mymodsmychoice.MyModsMyChoice;
import io.github.thijzert123.mymodsmychoice.config.Category;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ConfigScreen {
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final List<Category> configScreen = load();

    public static List<Category> get() {
        return configScreen;
    }

    private static List<Category> load() {
        final File configFile = MyModsMyChoice.CONFIG_SCREEN_CONFIG_PATH.toFile();
        try {
            return objectMapper.readValue(configFile, new TypeReference<>() {});
        } catch (final IOException ioException1) {
            MyModsMyChoice.LOGGER.error("Can't read config screen config file, creating empty file");
            final List<Category> emptyList = new ArrayList<>();
            try {
                objectMapper.writeValue(configFile, emptyList);
            } catch (final IOException ioException2) {
                MyModsMyChoice.LOGGER.error("Still can't read config screen config file, so no config screen");
            }
            return emptyList;
        }
    }
}
