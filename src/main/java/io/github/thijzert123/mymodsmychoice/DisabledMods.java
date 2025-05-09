package io.github.thijzert123.mymodsmychoice;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DisabledMods {
    public static List<String> load() {
        final List<String> disabledMods = new ArrayList<>();

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
            save(new ArrayList<>());
        }

        return disabledMods;
    }

    public static void save(final List<String> disabledMods) {
        try (final PrintWriter printWriter = new PrintWriter(MyModsMyChoice.DISABLED_MODS_CONFIG_PATH.toFile());) {
            writeDefaultComments(printWriter);
            for (final String disabledMod : disabledMods) {
                printWriter.println(disabledMod);
            }
        } catch (final IOException ioException) {
            MyModsMyChoice.LOGGER.error("Can't write to disabled mods config file.");
        }
    }

    private static void writeDefaultComments(final PrintWriter printWriter) {
        printWriter.println("// Add all the disabled mods here");
        printWriter.println("// You have to use the mod id that is used internally");
        printWriter.println("// Lines like these, that start with // will be ignored");
    }
}
