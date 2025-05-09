package io.github.thijzert123.mymodsmychoice.config;

import java.util.ArrayList;
import java.util.List;

// Information about a mod. A HashMap<String, ModSet> should be used when serializing. The String is the id in the HasMap.
public class ModSet {
    public String name; // user-friendly name, not the ID
    public String description;

    // Mods that will be disabled at one when disabling this mod set.
    // These are the internally-used IDs, which may not be the same as their Modrinth ID.
    public List<String> mods = new ArrayList<>();

    public ModSet() {}

    public ModSet(final String name, final String description) {
        this.name = name;
        this.description = description;
    }

    public ModSet addMod(final String modId) {
        mods.add(modId);
        return this;
    }
}
