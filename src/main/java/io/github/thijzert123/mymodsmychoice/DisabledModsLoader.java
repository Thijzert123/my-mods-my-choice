package io.github.thijzert123.mymodsmychoice;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DisabledModsLoader {
    public static List<String> disabledMods = new ArrayList<>();

    public static void loadDisabledMods() {
        try {
            final Scanner scanner = new Scanner(MyModsMyChoice.DISABLED_MODS_CONFIG_PATH.toFile());
            while (scanner.hasNextLine()) {
                final String line = scanner.nextLine().trim();
                if (!line.startsWith("//") && !line.isBlank()) {
                    disabledMods.add(line);
                }
            }
        } catch (final FileNotFoundException fileNotFoundException) {
            MyModsMyChoice.LOGGER.warn("Disabled mods config file not found. Defaulting to no mods disabled.");
        }
        MyModsMyChoice.LOGGER.info(String.valueOf(disabledMods));
    }
}
